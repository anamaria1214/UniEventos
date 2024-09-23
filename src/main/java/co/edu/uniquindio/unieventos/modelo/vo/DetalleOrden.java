package co.edu.uniquindio.unieventos.modelo.vo;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleOrden {

    private String id;
    private ObjectId idUsuario;
    private ObjectId idLocalidad;
    private float precio;
    private int cantidad;
}
