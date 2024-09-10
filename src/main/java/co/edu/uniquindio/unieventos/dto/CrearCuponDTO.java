package co.edu.uniquindio.unieventos.dto;
import java.time.LocalDateTime;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public record CrearCuponDTO(
        @NotBlank (message="El nombre es obligatorio")
        String nombre,
        @NotBlank (message="El descuento es obligatorio")
        float descuento,
        @NotBlank (message="La fecha de vencimiento del cupón es obligatoria")
        LocalDateTime fechaVencimiento,
        @NotBlank (message="El código es obligatorio")
        String codigo,
        @NotBlank (message="El estado es obligatorio")
        EstadoCupon estado,
        @NotBlank (message="El tipo de cupón es obligatorio")
        TipoCupon tipo)
 {

}
