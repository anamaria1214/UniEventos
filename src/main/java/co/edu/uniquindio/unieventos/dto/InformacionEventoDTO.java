package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import co.edu.uniquindio.unieventos.modelo.vo.Localidad;

import java.time.LocalDateTime;
import java.util.List;

public record InformacionEventoDTO(
        String nombre,
        String descripcion,
        String imagenPortada,
        LocalDateTime fechaEvento,
        String ciudad,
        String direccion,
        TipoEvento tipo,
        List<Localidad> localidades
) {
}
