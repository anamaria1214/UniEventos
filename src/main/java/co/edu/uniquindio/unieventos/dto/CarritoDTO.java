package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.modelo.vo.Localidad;

public record CarritoDTO(String idEvento, String idCarrito, int nuevaCantidad, Localidad localidad) {
}
