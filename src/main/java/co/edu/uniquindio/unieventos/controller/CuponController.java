package co.edu.uniquindio.unieventos.controller;


import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.EliminarCuponDTO;
import co.edu.uniquindio.unieventos.dto.global.MessageDTO;
import co.edu.uniquindio.unieventos.exceptions.CuponException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;
import co.edu.uniquindio.unieventos.servicios.implementaciones.CuponServicioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/cupon")
public class CuponController {


    @Autowired
    CuponServicioImpl cuponServicio;

    /**
     * Metodo para obtener todos los cupones disponibles
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Cupon>> getAll(){
        return ResponseEntity.ok(cuponServicio.getAll());
    }

    /**
     * Metodo para obtener un cupon dado el id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cupon> getOne(@PathVariable ("id") String id){
        return ResponseEntity.ok(cuponServicio.getCuponByCodigo(id));
    }

    /**
     * Metodo con el que se crea un nuevo cupon y se envia el mensaje de exito
     * @param cuponDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/save")
    public ResponseEntity<MessageDTO> save(@Valid @RequestBody CrearCuponDTO cuponDTO) throws Exception {
        Cupon nuevoCupon= cuponServicio.crearCupon(cuponDTO);
        String message= "Cupón "+ nuevoCupon.getNombre() +" ha sido guardado con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }

    /**
     *Metodo para poder editar los atributos del cupon seleccionado
     * @param id
     * @param cuponDTO
     * @return
     * @throws Exception
     */
    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> update(@PathVariable ("id") String id,@Valid @RequestBody EditarCuponDTO cuponDTO) throws CuponException {
        Cupon cuponEditado = cuponServicio.editarCupon(id,cuponDTO);
        String message= "Cupón "+ cuponEditado.getNombre() +" ha sido actualizado con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }

    /**
     * Metodo con el que "eliminamos" el cupon
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("id") String id) throws Exception {
        Cupon cuponEliminar= cuponServicio.eliminarCupon(id);
        String message= "Cupón "+ cuponEliminar.getNombre() +" ha sido eliminado con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
    }




}
