package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.CambiarPasswordDTO;
import co.edu.uniquindio.unieventos.dto.CrearCuentaRegistroDTO;
import co.edu.uniquindio.unieventos.dto.InfoAdicionalDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import co.edu.uniquindio.unieventos.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepo cuentaRepo;

    public CuentaServicioImpl(CuentaRepo cuentaRepo){
        this.cuentaRepo=cuentaRepo;
    }

    @Override
    public String crearCuenta(CrearCuentaRegistroDTO cuentaDTO) throws Exception {

        Cuenta cuenta= new Cuenta();
        cuenta.setEmail(cuentaDTO.correo());
        cuenta.setPassword(cuentaDTO.password());
        cuenta.setRol(Rol.CLIENTE);
        cuenta.setFechaRegistro(LocalDateTime.now());
        cuenta.setEstado(EstadoCuenta.INACTIVO);
       // cuenta.setUsuario(idUsuario);
        cuenta.setCodValidacionRegistro(new CodigoValidacion( LocalDateTime.now(), generarCodigoValidacion()));
        cuentaRepo.save(cuenta);

        return null;
    }

    private String generarCodigoValidacion(){
        return "";
    }
    @Override
    public String editarCuenta(InfoAdicionalDTO cuenta) throws Exception {
        return null;
    }

    @Override
    public String eliminarCuenta(String id) throws Exception {
        return null;
    }

    @Override
    public InfoAdicionalDTO obtenerInformacionCuenta(String id) throws Exception {
        return null;
    }

    @Override
    public String enviarCodigoRecuoeracion(String correo) {
        return null;
    }

    @Override
    public String cambioPassword(CambiarPasswordDTO cambiarPassword) throws Exception {
        return null;
    }

    @Override
    public String login(String correo, String password) throws Exception {
        return null;
    }
}
