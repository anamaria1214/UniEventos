package co.edu.uniquindio.unieventos.controller;


import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.dto.global.MessageDTO;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.exceptions.PasswordException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.servicios.implementaciones.CuentaServicioImpl;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuenta")
public class CuentaController {

    private final CuentaServicio cuentaServicio;

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getOneById(@PathVariable("id") String id){
        return ResponseEntity.ok(cuentaServicio.obtenerCuenta(id));
    }

    @PostMapping("/validar-codigo")
    public ResponseEntity<MessageDTO> validarCodigo(@Valid @RequestBody ValidarCodigoDTO validarCodigoDTO) throws CuentaException{
        try {
            cuentaServicio.validarCodig(validarCodigoDTO);
            String message= "Cuenta activada con exito";
            return ResponseEntity.ok(new MessageDTO(HttpStatus.OK,message));
        }catch (CuentaException cx){
            return ResponseEntity.badRequest().body(new MessageDTO(HttpStatus.BAD_REQUEST,cx.getMessage()));
        }
    }

    @PostMapping("/crear-cuenta")
    public ResponseEntity<MensajeDTO<String>> save(@Valid @RequestBody CrearCuentaRegistroDTO cuentaDTO) throws CuentaException, Exception, PasswordException {
        cuentaServicio.crearCuenta(cuentaDTO);
        String message= "La cuenta ha sido creada con exito";
        return ResponseEntity.ok(new MensajeDTO<>(false, message));
    }

    @PutMapping("/editar-cuenta")
    public ResponseEntity<MessageDTO> update(@Valid @RequestBody InfoAdicionalDTO cuentaDTO) throws CuentaException{
        Cuenta cuentaEditada= cuentaServicio.editarCuenta(cuentaDTO);
        String message= "La cuenta ha sido modificada con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }

    @DeleteMapping("/eliminar-cuenta/{email}")
    public ResponseEntity<MessageDTO> delete(@PathVariable("email") String email) throws CuentaException, Exception {
        cuentaServicio.eliminarCuenta(email);
        String message= "La cuenta ha sido eliminada con exito";
        return ResponseEntity.ok(new MessageDTO(HttpStatus.OK, message));
    }
    /**
     * Los comento para debatir sobre si deberian de ser publicos, ya que si no se acuerdq la contraseña no puede tener un token y no podra editar la cuenta
    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception {

        TokenDTO tokenDTO= cuentaServicio.login(loginDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, tokenDTO));
    }

    @PutMapping("/enviarCodigoPassword/{correo}")
    public ResponseEntity<MensajeDTO<String>> enviarCodigoRecuperacion(@PathVariable("correo") String correo) throws Exception {
        cuentaServicio.enviarCodigoRecuperacion(correo);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Envio del correo exitoso"));
    }

    @PostMapping("/cambiarPassword")
    public ResponseEntity<MensajeDTO<String>> cambioPassword(@Valid @RequestBody CambiarPasswordDTO cambiarPassword) throws Exception {
        cuentaServicio.cambioPassword(cambiarPassword);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Se cambio la contraseña exitosamente"));
    }
    **/
}
