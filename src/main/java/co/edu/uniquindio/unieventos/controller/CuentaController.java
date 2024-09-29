package co.edu.uniquindio.unieventos.controller;


import co.edu.uniquindio.unieventos.dto.CrearCuentaRegistroDTO;
import co.edu.uniquindio.unieventos.dto.InfoAdicionalDTO;
import co.edu.uniquindio.unieventos.dto.global.MessageDTO;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.servicios.implementaciones.CuentaServicioImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    CuentaServicioImpl cuentaServicio;

    @GetMapping
    public ResponseEntity<List<Cuenta>> getAll(){
        return ResponseEntity.ok(cuentaServicio.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getOneById(@PathVariable("id") String id){
        return ResponseEntity.ok(cuentaServicio.obtenerCuenta(id));
    }

    /**
     * Metodo en el que se obtiene una cuenta dado su correo
     * @param email
     * @return
     */
    @GetMapping("/{email}")
    public ResponseEntity<Cuenta> getOneByEmail(@PathVariable("email" ) String email){
        return ResponseEntity.ok(cuentaServicio.getCuentaByEmail(email));
    }

    @PostMapping
    public ResponseEntity<MessageDTO> save(@Valid @RequestBody CrearCuentaRegistroDTO cuentaDTO) throws CuentaException {
        Cuenta cuenta= cuentaServicio.crearCuenta(cuentaDTO);
        String message= "La cuenta ha sido creada con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

    @PutMapping
    public ResponseEntity<MessageDTO> update(@Valid @RequestBody InfoAdicionalDTO cuentaDTO) throws CuentaException{
        Cuenta cuentaEditada= cuentaServicio.editarCuenta(cuentaDTO);
        String message= "La cuenta ha sido modificada con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("id") String id) throws CuentaException {
        Cuenta cuentaEliminar= cuentaServicio.eliminarCuenta(id);
        String message= "La cuenta ha sido eliminada con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

}
