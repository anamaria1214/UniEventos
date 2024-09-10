package co.edu.uniquindio.unieventos.modelo.documentos;

import co.edu.uniquindio.unieventos.modelo.vo.DetalleCarrito;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("carritos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Carrito {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private LocalDateTime fechaUltimaAct;
    private ObjectId idCuenta;
    private List<DetalleCarrito> items;

    @Builder
    public Carrito(LocalDateTime fechaUltimaAct, ObjectId idCuenta, List<DetalleCarrito> items) {
        this.fechaUltimaAct = fechaUltimaAct;
        this.idCuenta = idCuenta;
        this.items = items;
    }
}
