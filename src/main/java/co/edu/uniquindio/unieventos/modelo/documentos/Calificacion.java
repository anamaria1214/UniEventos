package co.edu.uniquindio.unieventos.modelo.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("calificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Calificacion {

    @Id
    private String id;

    private String idEvento;
    private int calificacion;
    private LocalDateTime fechaCalificacion;

    public Calificacion(String idEvento,  int calificacion) {
        this.idEvento = idEvento;
        this.calificacion = calificacion;
        this.fechaCalificacion = LocalDateTime.now();
    }

}
