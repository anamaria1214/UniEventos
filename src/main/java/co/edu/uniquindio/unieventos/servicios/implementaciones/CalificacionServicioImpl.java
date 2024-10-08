package co.edu.uniquindio.unieventos.servicios.implementaciones;


import co.edu.uniquindio.unieventos.dto.CalificacionDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Calificacion;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.repositorios.CalificacionRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CalificacionServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;

import java.util.List;
import java.util.Optional;

import static java.time.LocalTime.now;

public class CalificacionServicioImpl implements CalificacionServicio {

     private CalificacionRepo calificacionRepo;
     private CuentaServicio cuentaServicio;
     private EventoServicio eventoServicio;

     public void calificarEvento(CalificacionDTO calificacionDTO) throws Exception {
         Cuenta cuenta = cuentaServicio.getCuentaByEmail(calificacionDTO.emailUsuario());
         if(cuenta==null){
             throw new Exception("Cuenta no existente");
         }

            if (calificacionDTO.puntuacion() < 1 || calificacionDTO.puntuacion() > 5) {
                throw new Exception("La puntuación debe estar entre 1 y 5");
            }

            Optional<Calificacion> calificacionExistente = calificacionRepo.findByIdCuentaAndIdEvento(cuenta.getId(), calificacionDTO.idEvento());

            if (calificacionExistente.isPresent()) {
                Calificacion calificacion = calificacionExistente.get();
                calificacion.setCalificacion(calificacion.getCalificacion());
            } else {
                Calificacion nuevaCalificacion = new Calificacion(cuenta.getId(), calificacionDTO.idEvento(), calificacionDTO.puntuacion());
                calificacionRepo.save(nuevaCalificacion);
            }
        }

        public double obtenerPromedioCalificaciones(String idEvento) throws Exception {
            List<Calificacion> calificaciones = calificacionRepo.findByIdEvento(idEvento);

            return calificaciones.stream()
                    .mapToInt(Calificacion::getCalificacion)
                    .average()
                    .orElse(0.0);
        }
    }

