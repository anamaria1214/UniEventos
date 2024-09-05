package co.edu.uniquindio.unieventos.modelo.documentos;

import co.edu.uniquindio.unieventos.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import co.edu.uniquindio.unieventos.modelo.vo.Usuario;
import lombok.*;
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

    private Usuario usuario;
    private String email;
    private String password;
    private CodigoValidacion codValidacionPassword;
    private CodigoValidacion codValidacionRegistro;
    private LocalDateTime fechaRegistro;
    private Rol rol;
    private EstadoCuenta estado;

    @Builder
    public Cuenta(Usuario usuario, String email, String password, CodigoValidacion codValidacionPassword, CodigoValidacion codValidacionRegistro, LocalDateTime fechaRegistro, Rol rol, EstadoCuenta estado) {
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.codValidacionPassword = codValidacionPassword;
        this.codValidacionRegistro = codValidacionRegistro;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
        this.estado = estado;
    }
}
