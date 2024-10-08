package co.edu.uniquindio.unieventos.servicios.interfaces;

import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.exceptions.EventoException;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import java.util.List;

public interface EventoServicio {


    Evento crearEvento(CrearEventoDTO crearEventoDTO) throws Exception,EventoException;

    Evento editarEvento(String idEvento, EditarEventoDTO editarEventoDTO) throws Exception;

    Evento eliminarEvento(String id) throws Exception;

    InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception;

    List<ItemEventoDTO> listarEventos();

    List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO);

    Evento obtenerEvento(String id) throws EventoException;

    Evento getByName(String name) throws EventoException;

    List<Evento> getAll();

    List<Evento> getAllDisponibles() throws EventoException;

    void actualizarCapacidadLocalidad(Evento evento, String nombreLocalidad, int entradasVendidas) throws Exception;
}
