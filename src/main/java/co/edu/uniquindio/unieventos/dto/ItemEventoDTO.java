package co.edu.uniquindio.unieventos.dto;

import java.time.LocalDateTime;

public record ItemEventoDTO(String urlImagenPoster,
                            String nombre,
                            LocalDateTime fecha,
                            String direccion) {
}
