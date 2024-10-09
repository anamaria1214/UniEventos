    package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.*;
import co.edu.uniquindio.unieventos.exceptions.EventoException;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.modelo.vo.Localidad;
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
    public Evento crearEvento(CrearEventoDTO crearEventoDTO) throws Exception, EventoException {
        if(obtenerEvento(crearEventoDTO.getNombre())!=null){
            throw new EventoException("El evento con el nombre "+crearEventoDTO.getNombre()+" ya existe");
        }
        Evento evento= new Evento(crearEventoDTO.getNombre(),crearEventoDTO.getDescripcion(),crearEventoDTO.getDireccion(), crearEventoDTO.getCiudad(), crearEventoDTO.getFecha(),EstadoEvento.ACTIVO, crearEventoDTO.getTipo(),crearEventoDTO.getImagenPortada(),crearEventoDTO.getImagenLocalidades());
        evento.setLocalidades(crearEventoDTO.getLocalidades());
        return  eventoRepo.save(evento);
    }

    @Override
    public Evento editarEvento(String idEvento, EditarEventoDTO editarEventoDTO) throws EventoException {
        Evento evento= obtenerEvento(idEvento);
         if(evento==null){
            throw new EventoException("El evento no existe");
         }
         evento.setNombre(editarEventoDTO.nombre());
         evento.setDescripcion(editarEventoDTO.descripcion());
         evento.setDireccion(editarEventoDTO.direccion()  );
         evento.setImagenPortada(editarEventoDTO.imagenPortada());
         evento.setImagenLocalidades(editarEventoDTO.imagenLocalidades());
         evento.setLocalidades(editarEventoDTO.localidades());
         //Guardar el cambio
         return  eventoRepo.save(evento);

        }

    @Override
    public Evento eliminarEvento(String id) throws EventoException {
        Evento evento= obtenerEvento(id);
        if (evento==null){
            throw new EventoException("No existe ningún evento con ese código de evento");
        } else if (evento.getEstado().equals(EstadoEvento.INACTIVO)) {
            throw new EventoException("El evento ya se encuentra inactivo");
        }
        evento.setEstado(EstadoEvento.INACTIVO);
        return eventoRepo.save(evento);//Se guarda el cambio de estado
    }

    @Override
    public InformacionEventoDTO obtenerInformacionEvento(String id) throws Exception {

        try{
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
        }catch (Exception e){
            throw new Exception("Evento no encontrado");
        }

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
        List<ItemEventoDTO> respuesta = new ArrayList<>();
        List<Evento> eventos= eventoRepo.filtrarEventos(filtroEventoDTO.nombre(),filtroEventoDTO.tipo().toString(), filtroEventoDTO.ciudad());
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
    public Evento obtenerEvento(String id) throws EventoException {
        return eventoRepo.findById(id).orElseThrow( () -> new EventoException("El evento no existe") );
    }
    @Override
    public Evento getByName(String name) throws EventoException{
        return eventoRepo.getByName(name).orElseThrow(()->new EventoException("El evento " + name+" no existe"));
    }

    @Override
    public List<Evento> getAll() {
        return eventoRepo.findAll();
    }

    @Override
    public void actualizarCapacidadLocalidad(Evento evento, String nombreLocalidad, int entradasVendidas) throws Exception {
        Localidad localidad = evento.getLocalidades().stream().filter(l -> l.getNombre().equals(nombreLocalidad) ).findFirst().orElse(null);
        if(localidad == null){
            throw new Exception("No existe la localidad");
        }

        localidad.setEntradasVendidas(localidad.getEntradasVendidas()+entradasVendidas );
        eventoRepo.save(evento);

    }


}
