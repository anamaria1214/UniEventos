package co.edu.uniquindio.unieventos.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("cuentas")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private ObjectId idString;
    private String email;
    private String password;
    private CodigoValidacion codValidacionPassword;
    private CodigoValidacion codValidacionRegistro;
    private LocalDateTime fechaRegistro;
    private Rol rol;
    private EstadoCuenta estado;
}
