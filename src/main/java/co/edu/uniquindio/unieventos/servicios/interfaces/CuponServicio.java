package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.CuponEnviadoDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.EliminarCuponDTO;

public interface CuponServicio {

    String crearCupon(CrearCuponDTO cupon) throws Exception;

    String editarCupon(String id, EditarCuponDTO cuponDTO) throws Exception;

    String eliminarCupon(String id, EliminarCuponDTO cuponDTO) throws Exception;

    String redimirCupon(String codigo) throws Exception;

    String enviarCupon(CuponEnviadoDTO cuponEnviado) throws Exception;

 
}
