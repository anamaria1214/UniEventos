package co.edu.uniquindio.unieventos.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record InformacionOrdenCompraDTO(
        String id,
        float total,
        LocalDateTime fecha,
        List<DetalleOrdenDTO> detalleOrden
) {
}
