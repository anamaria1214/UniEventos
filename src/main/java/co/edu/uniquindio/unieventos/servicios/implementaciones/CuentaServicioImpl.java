package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.config.JWTUtils;
import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import co.edu.uniquindio.unieventos.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Map;

@Service
@Transactional
public class CuentaServicioImpl implements CuentaServicio {

    //Variables
    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;

    public CuentaServicioImpl(CuentaRepo cuentaRepo, JWTUtils jwtUtils){
        this.cuentaRepo=cuentaRepo;
        this.jwtUtils = jwtUtils;
    }
    //Metodos de la cuenta
    @Override
    public String crearCuenta(CrearCuentaRegistroDTO cuentaDTO) throws Exception {

        Cuenta cuenta= new Cuenta();
        cuenta.setEmail(cuentaDTO.correo());
        cuenta.setPassword(cuentaDTO.password());
        cuenta.setRol(Rol.CLIENTE);
        cuenta.setFechaRegistro(LocalDateTime.now());
        cuenta.setEstado(EstadoCuenta.INACTIVO);
        // cuenta.setUsuario(idCuenta);
        cuenta.setCodValidacionRegistro(new CodigoValidacion( LocalDateTime.now(), generarCodigoValidacion()));
        cuentaRepo.save(cuenta);

        //emailServicio.

        return null;
    }

    @Override
    public String editarCuenta(InfoAdicionalDTO cuenta) throws Exception {

        Optional<Cuenta> cuentaOpcional= cuentaRepo.findById(cuenta.id());

        //Validacion

        Cuenta cuentaUsuario= cuentaOpcional.get();
        cuentaUsuario.getUsuario().setNombre(cuenta.nombre());
        cuentaUsuario.getUsuario().setDireccion(cuenta.direccion());
        cuentaUsuario.getUsuario().setTelefono(cuenta.telefono());
        cuentaUsuario.setPassword(cuenta.password());


        return "Cuenta editada exitosamente";
    }

    @Override
    public String eliminarCuenta(String id) throws Exception {

        Optional<Cuenta> cuentaOpcional= cuentaRepo.findById(id);

        //Validacion

        Cuenta cuentaUsuario= cuentaOpcional.get();
        cuentaUsuario.setEstado(EstadoCuenta.ELIMINADA);
        return "Se eliminó correctamente";
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
    private Cuenta obtenerCuenta(String id) throws CuentaException{
        return cuentaRepo.findById(id).orElseThrow(()->new CuentaException("La cuenta no existe"));
    }
    private Cuenta getCuentaByEmail(String email){
        return cuentaRepo.buscarEmail(email).orElseThrow(()->new CuentaException("La cuenta no existe"));
    }
    private Map<String, Object> construirClaims(Cuenta cuenta) {
        return Map.of(
                "rol", cuenta.getRol(),
                "nombre", cuenta.getUsuario().getNombre(),
                "id", cuenta.getId()
        );
    }


}