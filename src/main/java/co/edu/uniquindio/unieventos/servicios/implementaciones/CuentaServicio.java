package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.config.JWTUtils;
import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import co.edu.uniquindio.unieventos.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
@Transactional
public class CuentaServicio implements co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio {

    //Variables
    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;

    public CuentaServicio(CuentaRepo cuentaRepo, JWTUtils jwtUtils){
        this.cuentaRepo=cuentaRepo;
        this.jwtUtils = jwtUtils;
    }
    //Metodos de la cuenta
    @Override
    public Cuenta crearCuenta(CrearCuentaRegistroDTO cuentaDTO) throws CuentaException {

        Cuenta cuenta= new Cuenta();
        cuenta.setEmail(cuentaDTO.correo());
        cuenta.setPassword(encriptarPassword(cuentaDTO.password())); //Se encripta la contraseña
        cuenta.setRol(Rol.CLIENTE);
        cuenta.setFechaRegistro(LocalDateTime.now());
        cuenta.setEstado(EstadoCuenta.ACTIVO);
        // cuenta.setUsuario(idCuenta);
        cuenta.setCodValidacionRegistro(new CodigoValidacion( LocalDateTime.now(), generarCodigoValidacion()));
        return cuentaRepo.save(cuenta);
        //emailServicio.

    }

    @Override
    public Cuenta editarCuenta(InfoAdicionalDTO cuenta) throws CuentaException {

        Cuenta cuentaUsuario=obtenerCuenta(cuenta.id());
        if (cuentaUsuario==null){
            throw new CuentaException("La cuenta no existe");
        }
        //Validacion
        cuentaUsuario.getUsuario().setNombre(cuenta.nombre());
        cuentaUsuario.getUsuario().setDireccion(cuenta.direccion());
        cuentaUsuario.getUsuario().setTelefono(cuenta.telefono());
        cuentaUsuario.setPassword(cuenta.password());

        return cuentaRepo.save(cuentaUsuario);

    }

    @Override
    public Cuenta eliminarCuenta(String id) throws CuentaException {
        Cuenta cuentaUsuario=obtenerCuenta(id);
        if(cuentaUsuario==null){
            throw new CuentaException("La cuenta no existe");
        }

        //Validacion
        cuentaUsuario.setEstado(EstadoCuenta.ELIMINADA);
        return cuentaRepo.save(cuentaUsuario);
    }

    @Override
    public InfoAdicionalDTO obtenerInformacionCuenta(String id) throws Exception {
        return null;
    }

    @Override
    public String enviarCodigoRecuperacion(String correo) {

        Optional<Cuenta> cuentaOpcional= cuentaRepo.buscarEmail(correo);

        Cuenta cUsuario = cuentaOpcional.get();
        String codigoValidacion= generarCodigoValidacion();

        cUsuario.setCodValidacionRegistro(new CodigoValidacion(LocalDateTime.now(), codigoValidacion));

        cuentaRepo.save(cUsuario);

        return "Se ha enviado el código al correo, verifique, es valido por 15 minutos";
    }

    @Override
    public String cambioPassword(CambiarPasswordDTO cambiarPassword) throws Exception {
        Optional<Cuenta> cuentaOpcional= cuentaRepo.buscarEmail(cambiarPassword.email());

        Cuenta cUsuario = cuentaOpcional.get();
        CodigoValidacion codigoValidacion= cUsuario.getCodValidacionPassword();

        if(cUsuario.getCodValidacionPassword()!= null){
            if(cUsuario.getCodValidacionPassword().getCodigo().equals(cambiarPassword.codigoVerificacion())){
                if(codigoValidacion.getFechaCreacion().plusMinutes(15).isBefore(LocalDateTime.now())){
                    cUsuario.setPassword(cambiarPassword.passwordNueva());
                    cuentaRepo.save(cUsuario);
                }else{
                    throw  new Exception("Su código de verificación ya expiró");
                }
            }else{
                throw new Exception("El código es incorrecto");
            }
        }
        return null;
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
            char car= cadena.charAt(i);
            resul+=car;
        }
        return resul;
    }

    private Map<String, Object> construirClaims(Cuenta cuenta) {
        return Map.of(
                "rol", cuenta.getRol(),
                "nombre", cuenta.getUsuario().getNombre(),
                "id", cuenta.getId()
        );
    }


}