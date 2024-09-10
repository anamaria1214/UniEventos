package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class EditarCuponDTO {
    @NotBlank(message="El nombre es obligatorio")
    String nombre;
    @NotBlank (message="El descuento es obligatorio")
    float descuento;
    @NotBlank (message="La fecha de vencimiento del cupón es obligatoria")
    LocalDateTime fechaVencimiento;
    @NotBlank (message="El estado es obligatorio")
    TipoCupon tipo;
}
