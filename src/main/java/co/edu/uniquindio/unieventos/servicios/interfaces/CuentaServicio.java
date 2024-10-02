package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;

public interface CuentaServicio{

    Cuenta crearCuenta(CrearCuentaRegistroDTO cuenta) throws Exception;
    Cuenta editarCuenta(InfoAdicionalDTO cuenta) throws Exception;
    Cuenta eliminarCuenta(String id) throws Exception;
    InfoAdicionalDTO obtenerInformacionCuenta(String id)  throws Exception;
    String enviarCodigoRecuperacion(String correo);
    String cambioPassword(CambiarPasswordDTO cambiarPassword) throws Exception;
    TokenDTO login(LoginDTO loginDTO) throws Exception;
    Cuenta obtenerCuenta(String id) throws CuentaException;
}
