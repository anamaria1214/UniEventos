package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.exceptions.CarritoException;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import co.edu.uniquindio.unieventos.servicios.interfaces.CalificacionServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoServicio carritoServicio;


    @PostMapping("/agregarEvento")
    public ResponseEntity<MensajeDTO<String>> agregarEventoCarrito(@Valid @RequestBody CarritoDTO agregarCarrito) throws Exception{
        carritoServicio.agregarEventoCarrito(agregarCarrito);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Item agregado exitosamente"));
    }

    @DeleteMapping("/vaciarCarrito/{id}")
    private ResponseEntity<MensajeDTO<String>> vaciarCarrito(@PathVariable String id) throws Exception{
        carritoServicio.vaciarCarrito(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Carrito sin elementos"));
    }
    @DeleteMapping("/eliminarEvento-carrito")
    private ResponseEntity<MensajeDTO<String>> eliminarEventoCarrito(@Valid @RequestBody CarritoDTO eliminarDelCarrito) throws Exception{
       carritoServicio.eliminarEventoCarrito(eliminarDelCarrito);
       return ResponseEntity.ok(new MensajeDTO<>(false, "Carrito eliminado"));
    }
    @GetMapping("/listarElementos-carrito")
    private ResponseEntity<MensajeDTO<List<CarritoDTO>>> listarElementos(@Valid @RequestBody CarritoDTO carritoDTO) throws Exception{
        List<CarritoDTO> carritos= carritoServicio.listarElementos(carritoDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, carritos));
    }

    @PutMapping("/editarCantidad")
    public ResponseEntity<MensajeDTO<String>> editarCantidad(@Valid @RequestBody CarritoDTO carritoDTO) throws Exception{
        try{
            carritoServicio.editarCantidad(carritoDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cantidad evento editado"));
        }catch(CarritoException e){
            return ResponseEntity.ok(new MensajeDTO<>(true, e.getMessage()));
        }

    }

    @PostMapping("/crear-carrito/{id}")
    public ResponseEntity<MensajeDTO<String>> crearCarrito(@PathVariable String id) throws Exception {
        try {
            carritoServicio.crearCarrito(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Carrito creado"));
        } catch (Exception e) {
            return ResponseEntity.ok(new MensajeDTO<>(false, "Error al crear un carrito"));
        }
    }
}
