package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.CalificacionDTO;
import org.springframework.stereotype.Service;

@Service
public interface CalificacionServicio {

    void calificarEvento(CalificacionDTO calificacionDTO) throws Exception;
    double obtenerPromedioCalificaciones(String idEvento) throws Exception;
}
