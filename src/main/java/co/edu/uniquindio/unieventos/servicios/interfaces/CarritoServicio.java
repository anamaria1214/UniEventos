package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.exceptions.CarritoException;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;

import java.util.List;

public interface CarritoServicio {

    String agregarEventoCarrito(CarritoDTO agregarCarrito) throws CarritoException, Exception;

    String vaciarCarrito(String id) throws CarritoException;

    String eliminarEventoCarrito(CarritoDTO eliminarDelCarrito) throws CarritoException;

    List<CarritoDTO> listarElementos(CarritoDTO carritoDTO) throws CarritoException;

    String editarCantidad(CarritoDTO carritoDTO) throws CarritoException, Exception;

    Carrito obtenerCarrito(String id) throws CarritoException;

    void crearCarrito(String id) throws CarritoException;
}
