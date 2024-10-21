package co.edu.uniquindio.unieventos.servicios.implementaciones;


import co.edu.uniquindio.unieventos.dto.CalificacionDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Calificacion;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.repositorios.CalificacionRepo;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CalificacionServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalTime.now;

@Service
@Transactional
public class CalificacionServicioImpl implements CalificacionServicio {

    @Autowired
    private CalificacionRepo calificacionRepo;
    @Autowired
    private CuentaServicio cuentaServicio;
    @Autowired
    private EventoServicio eventoServicio;
    @Autowired
    private EventoRepo eventoRepo;

    public CalificacionServicioImpl(EventoRepo eventoRepo,CalificacionRepo calificacionRepo,CuentaServicio cuentaServicio,EventoServicio eventoServicio) {
        this.calificacionRepo = calificacionRepo;
        this.eventoServicio= eventoServicio;
        this.cuentaServicio=cuentaServicio;
        this.eventoRepo= eventoRepo;
    }

    @Override
    public List<Calificacion> obtenerCalificacionesEvento(String idEvento) {
        List<Calificacion> listaCalificacion = calificacionRepo.findAll();
        List<Calificacion> aux = new ArrayList<>();
        for (Calificacion calificacion : listaCalificacion) {
            if (calificacion.getIdEvento().equals(idEvento)) {
                aux.add(calificacion);
            }
        }
        return aux;
    }
    @Override
    public void calificarEvento(CalificacionDTO calificacionDTO) throws Exception {

        if (calificacionDTO.puntuacion() < 1 || calificacionDTO.puntuacion() > 5) {
            throw new Exception("La puntuación debe estar entre 1 y 5");
        }

        Calificacion nuevaCalificacion = new Calificacion(calificacionDTO.idEvento(), calificacionDTO.puntuacion());
        Evento evento = eventoServicio.obtenerEvento(calificacionDTO.idEvento());

        double promedioNuevo = (evento.getPromedioCalificaciones() + calificacionDTO.puntuacion())/obtenerCalificacionesEvento(calificacionDTO.idEvento()).size();
        evento.setPromedioCalificaciones(promedioNuevo);
        eventoRepo.save(evento);
        calificacionRepo.save(nuevaCalificacion);
    }
    @Override
    public double obtenerPromedioCalificaciones(String idEvento) throws Exception {
        if(eventoServicio.obtenerEvento(idEvento)==null){
            throw new Exception("Evento no encontrado");
        }
        return eventoServicio.obtenerEvento(idEvento).getPromedioCalificaciones();
    }



}

