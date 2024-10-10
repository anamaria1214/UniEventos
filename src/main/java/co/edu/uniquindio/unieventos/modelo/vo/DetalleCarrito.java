package co.edu.uniquindio.unieventos.modelo.vo;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class DetalleCarrito {

    private int cantidad;
    private String nombreLocalidad;
    private ObjectId idEvento;


}
