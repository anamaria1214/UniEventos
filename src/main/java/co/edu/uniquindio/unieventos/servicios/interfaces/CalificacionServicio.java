package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.CalificacionDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Calificacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalificacionServicio {

    void calificarEvento(CalificacionDTO calificacionDTO) throws Exception;
    double obtenerPromedioCalificaciones(String idEvento) throws Exception;
    List<Calificacion> obtenerCalificacionesEvento(String idEvento);
}
