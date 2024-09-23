package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.exceptions.CarritoException;

import java.util.List;

public interface CarritoServicio {

    String agregarEventoCarrito(CarritoDTO agregarCarrito) throws Exception;

    String vaciarCarrito(String id) throws Exception;

    String eliminarEventoCarrito(CarritoDTO eliminarDelCarrito) throws Exception;

    List<CarritoDTO> listarElementos(CarritoDTO carritoDTO) throws CarritoException;

    String editarCantidad(CarritoDTO carritoDTO) throws CarritoException;

}
