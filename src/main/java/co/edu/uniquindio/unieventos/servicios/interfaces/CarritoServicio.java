package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.AgregarAlCarritoDTO;
import co.edu.uniquindio.unieventos.dto.CarritoDTO;

import java.util.List;

public interface CarritoServicio {

    String agregarEventoCarrito(AgregarAlCarritoDTO agregarCarrito) throws Exception;

    String vaciarCarrito(String id) throws Exception;

    String eliminarEventoCarrito(AgregarAlCarritoDTO eliminarDelCarrito) throws Exception;

    List<CarritoDTO> listarElementos() throws Exception;

    String editarCantidad(CarritoDTO carritoDTO) throws Exception;

}
