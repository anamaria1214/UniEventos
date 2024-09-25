package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;

import java.util.List;

public interface CarritoServicio {

    String agregarEventoCarrito(CarritoDTO agregarCarrito) throws Exception;

    String vaciarCarrito(String id) throws Exception;

    String eliminarEventoCarrito(CarritoDTO eliminarDelCarrito) throws Exception;

    List<CarritoDTO> listarElementos(CarritoDTO carritoDTO) throws Exception;

    String editarCantidad(CarritoDTO carritoDTO) throws Exception;

}
