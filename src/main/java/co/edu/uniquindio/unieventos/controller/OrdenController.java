package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.dto.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.InformacionOrdenCompraDTO;
import co.edu.uniquindio.unieventos.dto.global.MessageDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Orden;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import jakarta.validation.Valid;
import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/orden")

public class OrdenController {
    @Autowired
    OrdenServicio ordenServicio;

    @GetMapping
    public ResponseEntity<List<Orden>> getAll(){
        return ResponseEntity.ok(ordenServicio.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformacionOrdenCompraDTO> getOne(@PathVariable ("id")String id) throws Exception {
        return ResponseEntity.ok(ordenServicio.obtenerInformacionOrden(id));
    }

    @PostMapping("/save")
    public ResponseEntity<MessageDTO> save(@Valid @RequestBody CrearOrdenDTO crearOrdenDTO) throws Exception {
        Orden nuevaOrden = ordenServicio.crearOrden(crearOrdenDTO);
        String message = "Orden" + nuevaOrden.getId() + " creada con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("id")String id) throws Exception {
        ordenServicio.cancelarOrden(id);
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, "La orden ha sido cancela exitosamente!"));
    }

}
