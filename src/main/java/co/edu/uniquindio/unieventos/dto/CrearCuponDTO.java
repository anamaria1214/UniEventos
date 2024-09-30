package co.edu.uniquindio.unieventos.dto;
import java.time.LocalDateTime;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data

public class CrearCuponDTO {
         @NotBlank (message="El nombre es obligatorio")
         String nombre;
         @NotNull(message="El descuento es obligatorio")
         float descuento;
         @NotNull (message="La fecha de vencimiento del cupón es obligatoria")
         LocalDateTime fechaVencimiento;
         @NotBlank (message="El código es obligatorio")
         String codigo;
         @NotNull (message="El estado es obligatorio")
         TipoCupon tipo;


    public CrearCuponDTO() {
    }

    public CrearCuponDTO(String nombre, float descuento, LocalDateTime fechaVencimiento, String codigo, TipoCupon tipo) {
        this.nombre = nombre;
        this.descuento = descuento;
        this.fechaVencimiento = fechaVencimiento;
        this.codigo = codigo;
        this.tipo = tipo;
    }
}
