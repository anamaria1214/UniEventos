package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EliminarCuponDTO {
    @NotNull(message="El estado es obligatorio")
    EstadoCupon estado;

    public EliminarCuponDTO(EstadoCupon estadoCupon) {
        this.estado = estadoCupon;
    }
}
