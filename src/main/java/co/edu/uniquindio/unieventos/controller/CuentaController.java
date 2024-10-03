package co.edu.uniquindio.unieventos.controller;


import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.dto.global.MessageDTO;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.servicios.implementaciones.CuentaServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuenta/api")
public class CuentaController {
    @Autowired
    CuentaServicio cuentaServicio;

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

    @PostMapping("/crear-cuenta")
    public ResponseEntity<MessageDTO> save(@Valid @RequestBody CrearCuentaRegistroDTO cuentaDTO) throws CuentaException {
        Cuenta cuenta= cuentaServicio.crearCuenta(cuentaDTO);
        String message= "La cuenta ha sido creada con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

    @PutMapping("/editar-cuenta")
    public ResponseEntity<MessageDTO> update(@Valid @RequestBody InfoAdicionalDTO cuentaDTO) throws CuentaException{
        Cuenta cuentaEditada= cuentaServicio.editarCuenta(cuentaDTO);
        String message= "La cuenta ha sido modificada con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

    @DeleteMapping("/eliminar-cuenta/{email}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("email") String email) throws CuentaException {
        cuentaServicio.eliminarCuenta(email);
        String message= "La cuenta ha sido eliminada con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO= cuentaServicio.login(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, tokenDTO));
    }

}
