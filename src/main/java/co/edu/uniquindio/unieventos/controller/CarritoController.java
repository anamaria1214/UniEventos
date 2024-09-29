package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoServicio carritoServicio;

    @PostMapping("/agregarEvento")
    private String agregarEventoCarrito(@Valid @RequestBody CarritoDTO agregarCarrito) throws Exception{
        return null;
    }

    @DeleteMapping("/vaciarCarrito/{id}")
    private String vaciarCarrito(@PathVariable String id) throws Exception{
        return null;
    }
    @DeleteMapping("/eliminarEvento-carrito")
    private String eliminarEventoCarrito(@Valid @RequestBody CarritoDTO eliminarDelCarrito) throws Exception{
        return null;
    }
    @GetMapping("/listarElementos-carrito")
    private List<CarritoDTO> listarElementos(@Valid @RequestBody CarritoDTO carritoDTO) throws Exception{
        return null;
    }

    @PutMapping("/editarCantidad")
    public String editarCantidad(@Valid @RequestBody CarritoDTO carritoDTO) throws Exception{
        return null;
    }
}
