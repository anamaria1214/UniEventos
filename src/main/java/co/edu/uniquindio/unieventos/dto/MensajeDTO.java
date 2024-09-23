package co.edu.uniquindio.unieventos.dto;
//Usado para la transferencia de mensajes para las respuestas de la API
public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
