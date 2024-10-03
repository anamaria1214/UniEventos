package co.edu.uniquindio.unieventos.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record InfoAdicionalDTO(@NotBlank String email,
                               @Length(max=10) String telefono,
                               String direccion,
                               @NotBlank @Length(max=30) String nombre) {

}
