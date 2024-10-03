package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.dto.global.LocalidadDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ItemOrdenDTO(String urlImagenEvento1, String nombresEvento1, LocalDateTime fecha1, String localidadEvento1, int cantidadEvento1, float total) {
}
