package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.CambiarPasswordDTO;
import co.edu.uniquindio.unieventos.dto.CrearCuentaRegistroDTO;
import co.edu.uniquindio.unieventos.dto.InfoAdicionalDTO;
import co.edu.uniquindio.unieventos.dto.LoginDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import co.edu.uniquindio.unieventos.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepo cuentaRepo;


    public CuentaServicioImpl(CuentaRepo cuentaRepo){
        this.cuentaRepo=cuentaRepo;
    }

    private Cuenta obtenerCuenta(String id) throws Exception{

        Optional<Cuenta> cuentaOpcional= cuentaRepo.findById(id);

        return cuentaOpcional.get();

    }
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
                    throw  new Exception("Sucódigo de verificación ya expiró");
                }
            }else{
                throw new Exception("El código es incorrecto");
            }
        }
        return null;
    }

    @Override
    public String login(LoginDTO loginDTO) throws Exception {
        Optional<Cuenta> cuentaOpcional= cuentaRepo.validarDatosLogin(loginDTO.correo(), loginDTO.password());
        return "TOKEN_JWT";
    }

    @Override
    public String encriptarPassword(String password) throws Exception {
        return null;
    }
}
