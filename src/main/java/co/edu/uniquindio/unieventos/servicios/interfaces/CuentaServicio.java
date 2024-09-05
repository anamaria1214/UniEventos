package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.CambiarPasswordDTO;
import co.edu.uniquindio.unieventos.dto.InfoAdicionalDTO;
import co.edu.uniquindio.unieventos.dto.CrearCuentaRegistroDTO;
import co.edu.uniquindio.unieventos.dto.LoginDTO;

public interface CuentaServicio{

    String crearCuenta(CrearCuentaRegistroDTO cuenta) throws Exception;
    String editarCuenta(InfoAdicionalDTO cuenta) throws Exception;
    String eliminarCuenta(String id) throws Exception;
    InfoAdicionalDTO obtenerInformacionCuenta(String id)  throws Exception;
    String enviarCodigoRecuperacion(String correo);
    String cambioPassword(CambiarPasswordDTO cambiarPassword) throws Exception;
    String login(LoginDTO loginDTO) throws Exception;
}
