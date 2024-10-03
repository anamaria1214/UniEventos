package co.edu.uniquindio.unieventos.controller;


import co.edu.uniquindio.unieventos.dto.CrearEventoDTO;
import co.edu.uniquindio.unieventos.dto.EditarEventoDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.servicios.implementaciones.EventoServicioImpl;
import co.edu.uniquindio.unieventos.dto.global.MessageDTO;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    EventoServicioImpl eventoServicio;

    /**
     * Metodo para obtener toda la lista de eventos disponibles
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Evento>> getAll(){
        return ResponseEntity.ok(eventoServicio.getAll());
    }

    /**
     * Metodo para obtener un evento dado el id
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getOne(@PathVariable("id") String id) throws Exception {
        return ResponseEntity.ok(eventoServicio.obtenerEvento(id));
    }


    @PostMapping("/save")
    public ResponseEntity<MessageDTO> save(@Valid @RequestBody CrearEventoDTO crearEventoDTO) throws Exception {
        Evento eventoNuevo= eventoServicio.crearEvento(crearEventoDTO);
        String message= "Evento "+ eventoNuevo.getNombre() +" ha sido guardado con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> update(@Valid @RequestBody EditarEventoDTO eventoEditar) throws Exception {
        Evento eventoEditado= eventoServicio.editarEvento(eventoEditar);
        String message= "El evento "+ eventoEditado.getNombre() +" ha sido actualizado con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("id") String id) throws Exception {
        Evento evento= eventoServicio.eliminarEvento(id);
        String message= "El evento  ha cambiado su estado a NO-DISPONIBLE";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

}
