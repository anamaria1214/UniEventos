package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.config.JWTUtils;
import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.dto.global.MessageDTO;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.exceptions.PasswordException;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CalificacionServicio;
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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {

    private final CuentaServicio cuentaServicio;
    private final EventoServicio eventoServicio;
    private final CalificacionServicio calificacionServicio;
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


    @PostMapping("/verificar-rol/{email}")
    public ResponseEntity<MensajeDTO<String>> verificarRol(@PathVariable String email) {
        System.out.println("Buscando cuenta para el email: " + email); // Log para ver el email recibido
        try {
            String rol = cuentaServicio.obtenerRolPorEmail(email);
            return ResponseEntity.ok(new MensajeDTO<>(false, rol));
        } catch (CuentaException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, e.getMessage()));
        }
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

    @PostMapping("/calificar")
    public ResponseEntity<MensajeDTO<String>> calificarEvento(@Valid @RequestBody CalificacionDTO calificacionDTO) throws Exception {
        calificacionServicio.calificarEvento(calificacionDTO);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Evento calificado correctamente"));
    }
    @GetMapping("/obtenerPromedio/{idEvento}")
    public ResponseEntity<MensajeDTO<Double>> obtenerPromedioCalificaciones(String idEvento) throws Exception {
        double promedio= calificacionServicio.obtenerPromedioCalificaciones(idEvento);
        return ResponseEntity.ok(new MensajeDTO<>(false, promedio));
    }

}
