package co.edu.uniquindio.unieventos.exceptions;

import co.edu.uniquindio.unieventos.dto.global.MessageDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Orden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Clase que sirve para la centralización y manejo de las execpiones a la hora de lanzar la respuesta
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manejo de la excepción de los eventos
     * @param ex
     * @return
     */
    @ExceptionHandler(EventoException.class)
    public ResponseEntity<MessageDTO> handleEventoException(EventoException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(HttpStatus.BAD_REQUEST, message));
    }

    /**
     * Manejo de la excepción de los cupones
     * @param ex
     * @return
     */
    @ExceptionHandler(CuponException.class)
    public ResponseEntity<MessageDTO> handleCuponException(CuponException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(CarritoException.class)
    public ResponseEntity<MessageDTO> handleCarritoException(CarritoException ex) {
        String message = ex.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(HttpStatus.BAD_REQUEST, message));
    }

    @ExceptionHandler(CuentaException.class)
    public ResponseEntity<MessageDTO> handleCuentaException(CuentaException cx) {
        String message = cx.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(HttpStatus.BAD_REQUEST, message));
    }
    @ExceptionHandler(OrdenException.class)
    public ResponseEntity<MessageDTO> handleOrdenException(OrdenException ox){
        String message = ox.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(HttpStatus.BAD_REQUEST, message));
    }


}
