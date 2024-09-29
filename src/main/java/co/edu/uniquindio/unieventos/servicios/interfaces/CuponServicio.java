package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.CuponEnviadoDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.EliminarCuponDTO;
import co.edu.uniquindio.unieventos.exceptions.CuponException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;

public interface CuponServicio {

    Cupon crearCupon(CrearCuponDTO cupon) throws Exception;

    Cupon editarCupon(String id, EditarCuponDTO cuponDTO) throws Exception;

    Cupon eliminarCupon(String id, EliminarCuponDTO cuponDTO) throws Exception;

    float redimirCupon(String codigo, float total) throws Exception;

    String enviarCupon(CuponEnviadoDTO cuponEnviado) throws Exception;

    Cupon getCuponByCodigo(String codigo) throws CuponException;
    
    boolean verificarVigencia(Cupon cuponAux);
}
