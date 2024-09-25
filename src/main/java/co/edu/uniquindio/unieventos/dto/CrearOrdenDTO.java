package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.modelo.vo.DetalleOrden;
import org.bson.types.ObjectId;

import java.util.List;

public record CrearOrdenDTO(
        String idCuenta,
        String idCupon,
        String idCarrito
) {
}
