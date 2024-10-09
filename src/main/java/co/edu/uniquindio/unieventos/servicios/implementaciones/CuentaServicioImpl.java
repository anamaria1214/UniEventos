package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.config.JWTUtils;
import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.exceptions.PasswordException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import co.edu.uniquindio.unieventos.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.unieventos.modelo.vo.Usuario;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
@Transactional
public class CuentaServicioImpl implements CuentaServicio {

    //Variables
    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;
    private final EmailServicio emailServicio;

    public CuentaServicioImpl(CuentaRepo cuentaRepo, JWTUtils jwtUtils, EmailServicio emailServicio){
        this.cuentaRepo=cuentaRepo;
        this.jwtUtils = jwtUtils;
        this.emailServicio = emailServicio;
    }
    //Metodos de la cuenta
    @Override
    public void crearCuenta(CrearCuentaRegistroDTO cuentaDTO) throws CuentaException, Exception,PasswordException {

        Optional<Cuenta> cuentaExistente = cuentaRepo.buscarEmail(cuentaDTO.correo());

        if (cuentaExistente.isPresent()) {
            throw new CuentaException("El correo: " + cuentaDTO.correo() +  " ya está asociado a una cuenta");
        }

        CodigoValidacion codigo = new CodigoValidacion( LocalDateTime.now(), generarCodigoValidacion());

        Cuenta cuenta= new Cuenta();
        cuenta.setEmail(cuentaDTO.correo());
        if(cuentaDTO.password().length()<=8){
            throw new PasswordException("La contraseña debe de constar de mas de 8 caracteres");
        }else{
            cuenta.setPassword(encriptarPassword(cuentaDTO.password())); //Se encripta la contraseña
        }
        cuenta.setRol(cuentaDTO.rol());//Como se pueden crear clientes y administradores, hay que validar esto
        cuenta.setFechaRegistro(LocalDateTime.now());
        cuenta.setEstado(EstadoCuenta.INACTIVO);
        cuenta.setUsuario(new Usuario(cuentaDTO.idUsuario(), null, null, null));
        cuenta.setCodValidacionRegistro(codigo);

        emailServicio.enviarCorreo( new EmailDTO("Código de validación", "El código de validación es: "+codigo.getCodigo(), cuentaDTO.correo()) );
        cuentaRepo.save(cuenta);

    }
    @Override
    public ValidarCodigoDTO validarCodig(ValidarCodigoDTO validarCodigoDTO){
        Cuenta cuenta= getCuentaByEmail(validarCodigoDTO.email());

        //El codigo no coincide con el enviado
        if(!cuenta.getCodValidacionRegistro().getCodigo().equals(validarCodigoDTO.codigo())){
            throw new CuentaException("El código ingresado es incorrecto");
        }

        cuenta.setEstado(EstadoCuenta.ACTIVO);
        cuenta.setCodValidacionRegistro(null);
        cuentaRepo.save(cuenta);
        return validarCodigoDTO; //Retorno para el controller
    }

    @Override
    public Cuenta editarCuenta(InfoAdicionalDTO cuenta) throws CuentaException {

        Cuenta cuentaUsuario=getCuentaByEmail(cuenta.email());
        if (cuentaUsuario==null){
            throw new CuentaException("La cuenta no existe");
        }

        cuentaUsuario.getUsuario().setNombre(cuenta.nombre());
        cuentaUsuario.getUsuario().setDireccion(cuenta.direccion());
        cuentaUsuario.getUsuario().setTelefono(cuenta.telefono());

        return cuentaRepo.save(cuentaUsuario);

    }

    @Override
    public Cuenta eliminarCuenta(String email) throws CuentaException {
        Cuenta cuentaUsuario=getCuentaByEmail(email);
        if(cuentaUsuario==null){
            throw new CuentaException("La cuenta no existe");
        }

        cuentaUsuario.setEstado(EstadoCuenta.ELIMINADA);
        return cuentaRepo.save(cuentaUsuario);
    }

    @Override
    public InfoAdicionalDTO obtenerInformacionCuenta(String id) throws Exception {
        return null;
    }

    @Override
    public void enviarCodigoRecuperacion(String correo) throws Exception{

        Optional<Cuenta> cuentaOpcional= cuentaRepo.buscarEmail(correo);

        Cuenta cUsuario = cuentaOpcional.get();
        String codigoValidacion= generarCodigoValidacion();

        cUsuario.setCodValidacionPassword(new CodigoValidacion(LocalDateTime.now(), codigoValidacion));

        emailServicio.enviarCorreo( new EmailDTO("Código de validación", "El código de validación es: "+codigoValidacion+". Este código tiene una duración de 15 minutos, después de este tiempo, no será valido", correo) );
        cuentaRepo.save(cUsuario);
    }

    @Override
    public void cambioPassword(CambiarPasswordDTO cambiarPassword) throws CuentaException, Exception {
        Optional<Cuenta> cuentaOpcional= cuentaRepo.buscarEmail(cambiarPassword.email());


        Cuenta cUsuario = cuentaOpcional.get();

        CodigoValidacion codigoValidacion= cUsuario.getCodValidacionPassword();
        System.out.println("codigo: "+codigoValidacion);
        if(cUsuario.getCodValidacionPassword()!= null){
            if(cUsuario.getCodValidacionPassword().getCodigo().equals(cambiarPassword.codigoVerificacion())){
                if(codigoValidacion.getFechaCreacion().plusMinutes(15).isAfter(LocalDateTime.now())){
                    cUsuario.setPassword(encriptarPassword(cambiarPassword.passwordNueva()));
                    cuentaRepo.save(cUsuario);
                }else{
                    throw  new CuentaException("Su código de verificación ya expiró");
                }
            }else{
                throw new CuentaException("El código es incorrecto");
            }
        }
    }

    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        Cuenta cuenta = getCuentaByEmail(loginDTO.correo());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if( !passwordEncoder.matches(loginDTO.password(), cuenta.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = construirClaims(cuenta);
        return new TokenDTO( jwtUtils.generarToken(cuenta.getEmail(), map) );
    }

    @Override
    public Cuenta obtenerCuenta(String id) throws CuentaException{
        return cuentaRepo.findById(id).orElseThrow(()->new CuentaException("La cuenta no existe"));
    }

    public Cuenta getCuentaByEmail(String email){
        return cuentaRepo.buscarEmail(email).orElseThrow(()->new CuentaException("La cuenta no existe"));
    }

    public List<Cuenta> getAll(){
        return cuentaRepo.findAll();
    }

    //---------------------Metodos de autenticación y JWT-----------------------------
    public String encriptarPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode( password );
    }


    //--------------------Metodos privados----------------------------------

    private String generarCodigoValidacion(){

        String cadena ="ABCDEFGHIJKMNÑOPQRSTUVWXYZ1234567890";
        String resul="";

        for(int i=0; i<6;i++){
            int indice = (int) (Math.random()*cadena.length());
            char car= cadena.charAt(indice);
            resul+=car;
        }
        return resul;
    }

    private Map<String, Object> construirClaims(Cuenta cuenta) {
        return Map.of(
                "rol", cuenta.getRol(),
                "nombre", cuenta.getEmail(),
                "id", cuenta.getId()
        );
    }


}