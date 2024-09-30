package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoServicio carritoServicio;

    @PostMapping("/agregarEvento")
    public ResponseEntity<MensajeDTO<String>> agregarEventoCarrito(@Valid @RequestBody CarritoDTO agregarCarrito) throws Exception{
        carritoServicio.agregarEventoCarrito(agregarCarrito);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta creada exitosamente"));
    }

    @DeleteMapping("/vaciarCarrito/{id}")
    private String vaciarCarrito(@PathVariable String id) throws Exception{
        carritoServicio.vaciarCarrito(id);
        return "Carrito sin elementos";
    }
    @DeleteMapping("/eliminarEvento-carrito")
    private String eliminarEventoCarrito(@Valid @RequestBody CarritoDTO eliminarDelCarrito) throws Exception{
       carritoServicio.eliminarEventoCarrito(eliminarDelCarrito);
       return "Carrito eliminado";
    }
    @GetMapping("/listarElementos-carrito")
    private List<CarritoDTO> listarElementos(@Valid @RequestBody CarritoDTO carritoDTO) throws Exception{
        return carritoServicio.listarElementos(carritoDTO);
    }

    @PutMapping("/editarCantidad")
    public String editarCantidad(@Valid @RequestBody CarritoDTO carritoDTO) throws Exception{
        carritoServicio.editarCantidad(carritoDTO);
        return "Se he editado correctamente";
    }
}
