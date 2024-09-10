    package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.repositorios.EventoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventoServicioImpl implements EventoServicio {

    private final EventoRepo eventoRepo;

    public EventoServicioImpl(EventoRepo eventoRepo) {
        this.eventoRepo = eventoRepo;
    }

    @Override
    public String crearEvento(CrearEventoDTO crearEventoDTO) throws Exception {
        Evento evento= new Evento(crearEventoDTO.getNombre(),crearEventoDTO.getDescripcion(),crearEventoDTO.getDireccion(), crearEventoDTO.getCiudad(), crearEventoDTO.getFecha(),EstadoEvento.ACTIVO, crearEventoDTO.getTipo(),crearEventoDTO.getImagenPortada(),crearEventoDTO.getImagenLocalidades());
        Evento eventoCreado =  eventoRepo.save(evento);
        return "Se ha creado el evento correctamente ";
    }

    @Override
    public String editarEvento(String id) throws Exception {
        return "";
    }


     public String editarEvento(EditarEventoDTO editarEventoDTO) throws Exception {
        Optional<Evento> eventoOpcional = eventoRepo.findById(editarEventoDTO.id());
         if(eventoOpcional.isEmpty()){
            throw new Exception("El evento no existe");
         }
         Evento evento = eventoOpcional.get();
         evento.setNombre(editarEventoDTO.nombre());
         evento.setDescripcion(editarEventoDTO.descripcion());
         evento.setDireccion(editarEventoDTO.direccion()  );
         evento.setImagenPortada(editarEventoDTO.imagenPortada());
         evento.setImagenLocalidades(editarEventoDTO.imagenLocalidades());
         evento.setLocalidades(editarEventoDTO.localidades());
         
         return "Evento editado correctamente";

        }

    @Override
    public String eliminarEvento(String id) throws Exception {

        Optional<Evento> eventoOptional= eventoRepo.findById(id);

        Evento evento= eventoOptional.get();
        evento.setEstado(EstadoEvento.INACTIVO);
        return "El evento se ha eliminado correctamente";
    }

    @Override
    public InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception {

        Optional<Evento> eventoOpcional= eventoRepo.findById(id);


        Evento evento= eventoOpcional.get();
        InformacionEventoDTO infoEvento= new InformacionEventoDTO(
                evento.getNombre(),
                evento.getDescripcion(),
                evento.getImagenPortada(),
                evento.getFecha(),
                evento.getCiudad(),
                evento.getDireccion(),
                evento.getTipo(),
                evento.getLocalidades()
        );

        return infoEvento;
    }

    @Override
    public List<ItemEventoDTO> listarEventos() {
        List<Evento> eventos = eventoRepo.findAll();
        List<ItemEventoDTO> respuesta = new ArrayList<>();

        for(Evento evento : eventos){
            respuesta.add(new ItemEventoDTO(
                    evento.getImagenPortada(),
                    evento.getNombre(),
                    evento.getFecha(),
                    evento.getDireccion()
            ));
        }

        return respuesta;

    }

    @Override
    public List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroEventoDTO) {

        return null;
    }
}
