package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import java.util.List;

public interface EventoServicio {


    Evento crearEvento(CrearEventoDTO crearEventoDTO) throws Exception;

    Evento editarEvento(EditarEventoDTO editarEventoDTO) throws Exception;

    Evento eliminarEvento(String id) throws Exception;

    InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception;

    List<ItemEventoDTO> listarEventos();

    List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO);

    Evento obtenerEventos(String id) throws Exception;

    List<Evento> getAll();

}
