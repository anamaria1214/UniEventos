package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;

public record FiltroEventoDTO(String nombre,
                              TipoEvento tipo,
                              String ciudad) {
}