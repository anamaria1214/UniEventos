package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import co.edu.uniquindio.unieventos.modelo.vo.Localidad;

import java.time.LocalDateTime;
import java.util.List;

public record EditarEventoDTO(
        String id, String nombre, String descripcion, String direccion, String ciudad,
        LocalDateTime fecha, TipoEvento tipo, String imagenPortada, String imagenLocalidades,
        List<Localidad> localidades) {

}
