package co.edu.uniquindio.unieventos.modelo.documentos;

import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("cupones")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cupon {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String nombre;
    private float descuento;
    private LocalDateTime fechaVencimiento;
    private String codigo;
    private EstadoCupon estado;
    private TipoCupon tipo;

    public Cupon(String nombre, float descuento, LocalDateTime fechaVencimiento, String codigo, EstadoCupon estado, TipoCupon tipo){
        this.nombre= nombre;
        this.descuento= descuento;
        this.fechaVencimiento= fechaVencimiento;
        this.codigo=codigo;
        this.estado= estado;
        this.tipo= tipo;
    }

}
