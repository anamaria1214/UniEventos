package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.config.JWTUtils;
import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.dto.global.MessageDTO;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.exceptions.PasswordException;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller en el que estaran los metodos a los que puede acceder cualquiera, es decir,  que no necesitan token
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {

    private final CuentaServicio cuentaServicio;
    private final EventoServicio eventoServicio;

    /**
     * Obtener todos los eventos disponibles
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Evento>> getAllDisponibles(){
        return ResponseEntity.ok(eventoServicio.getAllDisponibles());
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


    @PostMapping("/verificar-rol")
    public ResponseEntity<MensajeDTO<String>> verificarRol(@RequestBody String email) {
        String rol = cuentaServicio.obtenerRolPorEmail(email);  // Metodo que retorna el rol del usuario de acuerdo al email ingresado
        return ResponseEntity.ok(new MensajeDTO<>(false, rol));
    }


    @PostMapping("/crear-cuenta")
    public ResponseEntity<MensajeDTO<String>> save(@Valid @RequestBody CrearCuentaRegistroDTO cuentaDTO) throws CuentaException, Exception, PasswordException {
        cuentaServicio.crearCuenta(cuentaDTO);
        String message= "La cuenta ha sido creada con exito";
        return ResponseEntity.ok(new MensajeDTO<>(false, message));
    }

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


}
