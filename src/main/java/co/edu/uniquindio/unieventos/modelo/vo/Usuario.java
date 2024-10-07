package co.edu.uniquindio.unieventos.modelo.vo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class    Usuario {

    private String cedula;
    private String nombre;
    private String telefono;
    private String direccion;

    @Builder
    public Usuario(String cedula, String nombre, String telefono, String direccion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
