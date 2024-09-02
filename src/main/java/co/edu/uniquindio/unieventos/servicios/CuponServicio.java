package co.edu.uniquindio.unieventos.servicios;

import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.CuponEnviadoDTO;

public interface CuponServicio {

    String crearCupon(CrearCuponDTO cupon) throws Exception;

    String redimirCupon(String codigo) throws Exception;

    String enviarCupon(CuponEnviadoDTO cuponEnviado) throws Exception;
}
