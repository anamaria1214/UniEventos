package co.edu.uniquindio.unieventos.dto;
import java.time.LocalDateTime;
import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoEvento;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearEventoDTO {

    @NotBlank(message="El nombre del evento es obligatorio")
    private String nombre;
    @NotBlank(message="La descripción del evento es obligatorio")
    private String descripcion;
    @NotBlank(message="La dirección del evento es obligatoria")
    private String direccion;
    @NotBlank(message="La ciudad del evento es obligatorio")
    private String ciudad;
    private LocalDateTime fecha;
    @NotBlank(message="El Tipo de evento es obligatorio")
    private TipoEvento tipo;
    @NotBlank(message="La imagen del evento es obligatoria")
    private String imagenPortada;
    @NotBlank(message="La portada del evento es obligatoria")
    private String imagenLocalidades;

}
