package co.edu.uniquindio.unieventos.modelo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;

@AllArgsConstructor
@Getter
public class DetalleOrden {

    private ObjectId id;
    private ObjectId idEvento;
    private String nombreLocalidad;
    private float precio;
    private int cantidad;
}
