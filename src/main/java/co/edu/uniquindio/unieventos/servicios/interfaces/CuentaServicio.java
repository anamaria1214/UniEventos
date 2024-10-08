package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;

public interface CuentaServicio{

    void crearCuenta(CrearCuentaRegistroDTO cuenta) throws Exception;
    ValidarCodigoDTO validarCodig(ValidarCodigoDTO validarCodigoDTO);
    Cuenta editarCuenta(InfoAdicionalDTO cuenta) throws CuentaException;
    Cuenta eliminarCuenta(String id) throws Exception;
    InfoAdicionalDTO obtenerInformacionCuenta(String id)  throws Exception;
    void enviarCodigoRecuperacion(String correo) throws Exception;
    void cambioPassword(CambiarPasswordDTO cambiarPassword) throws Exception;
    TokenDTO login(LoginDTO loginDTO) throws Exception;
    Cuenta obtenerCuenta(String id) throws CuentaException;
}
