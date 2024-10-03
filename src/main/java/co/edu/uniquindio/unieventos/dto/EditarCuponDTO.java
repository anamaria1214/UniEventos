package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Data

public class EditarCuponDTO {
    @NotBlank(message="El nombre es obligatorio")
    String nombre;
    @NotNull(message="El descuento es obligatorio")
    float descuento;
    @NotNull (message="La fecha de vencimiento del cupón es obligatoria")
    LocalDateTime fechaVencimiento;
    @NotNull (message="El estado es obligatorio")
    TipoCupon tipo;

    public EditarCuponDTO() {
    }

    public EditarCuponDTO(String nombre, float descuento, LocalDateTime fechaVTO, TipoCupon tipoCupon) {
        this.nombre=nombre;
        this.descuento=descuento;
        this.fechaVencimiento=fechaVTO;
        this.tipo=tipoCupon;

    }
}
