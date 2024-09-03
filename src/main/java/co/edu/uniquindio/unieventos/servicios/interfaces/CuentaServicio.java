package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.CambiarPasswordDTO;
import co.edu.uniquindio.unieventos.dto.InfoAdicionalDTO;
import co.edu.uniquindio.unieventos.dto.CrearCuentaRegistroDTO;

public interface CuentaServicio{

    String crearCuenta(CrearCuentaRegistroDTO cuenta) throws Exception;
    String editarCuenta(InfoAdicionalDTO cuenta) throws Exception;
    String eliminarCuenta(String id) throws Exception;
    InfoAdicionalDTO obtenerInformacionCuenta(String id)  throws Exception;
    String enviarCodigoRecuoeracion(String correo);
    String cambioPassword(CambiarPasswordDTO cambiarPassword) throws Exception;
    String login(String correo, String password) throws Exception;
}
