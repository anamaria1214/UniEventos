package co.edu.uniquindio.unieventos.modelo.vo;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
public class DetalleOrden {

    private ObjectId id;
    private ObjectId idEvento;
    private String nombreLocalidad;
    private float precio;
    private int cantidad;
}
