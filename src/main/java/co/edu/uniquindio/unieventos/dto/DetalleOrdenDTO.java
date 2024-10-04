package co.edu.uniquindio.unieventos.dto;

public record DetalleOrdenDTO(
        String nombreEvento,
        String nombreLocalidad,
        int numeroEntradas,
        float precioIndividual
) {
}
