package co.edu.uniquindio.unieventos.dto;
import java.time.LocalDateTime;
import java.util.List;

import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoEvento;

import co.edu.uniquindio.unieventos.modelo.vo.Localidad;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    @NotNull(message="La fecha es obligatoria")
    private LocalDateTime fecha;
    @NotNull(message="El Tipo de evento es obligatorio")
    private TipoEvento tipo;
    @NotBlank(message="La imagen del evento es obligatoria")
    private String imagenPortada;
    @NotBlank(message="La portada del evento es obligatoria")
    private String imagenLocalidades;
    private List<Localidad> localidades;
    public CrearEventoDTO() {
    }
}
