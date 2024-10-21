package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.exceptions.CarritoException;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.modelo.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.repositorios.CarritoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;
    private final EventoServicio eventoServicio;
    private final CuentaServicio cuentaServicio;

    private Carrito findById(String id) throws CarritoException{
        // Is this findById not working?
        Optional<Carrito> carritoOptional= carritoRepo.findById(id);

        if(carritoOptional.isEmpty()){
            throw new CarritoException("Carrito no encontrado");
        }

        return carritoOptional.get();
    }

    @Override
    public String agregarEventoCarrito(CarritoDTO agregarCarrito) throws  Exception ,CarritoException {
        Carrito carrito= findById(agregarCarrito.idCarrito());
        if(eventoServicio.obtenerEvento(agregarCarrito.idEvento()).obtenerLocalidad(agregarCarrito.nLocalidad())==null){
            throw new CarritoException("La localidad seleccionada no existe");
        }
        int entradasVendidas= eventoServicio.obtenerEvento(agregarCarrito.idEvento()).obtenerLocalidad(agregarCarrito.nLocalidad()).getEntradasVendidas();
        int capacidadMaxima= eventoServicio.obtenerEvento(agregarCarrito.idEvento()).obtenerLocalidad(agregarCarrito.nLocalidad()).getCapacidadMaxima();
        boolean existe = false;
        if(agregarCarrito.nuevaCantidad()+entradasVendidas>capacidadMaxima){
            throw new CarritoException("Se ha excedido la capacidad máxima de entradas");
        }
        for(int i=0;i<carrito.getItems().size() && !existe;i++){
            if(carrito.getItems().get(i).getIdEvento().equals(agregarCarrito.idEvento())){
                if(agregarCarrito.nuevaCantidad()+entradasVendidas<capacidadMaxima){
                    carrito.getItems().get(i).setCantidad(agregarCarrito.nuevaCantidad());
                }
                existe = true;
            }
        }
        if(!existe){
<<<<<<< HEAD
            carrito.getItems().add(new DetalleCarrito(agregarCarrito.nuevaCantidad(), agregarCarrito.nLocalidad(), new ObjectId(agregarCarrito.idEvento())));
=======
            Evento eventoCARRITO= eventoServicio.obtenerEvento(agregarCarrito.idEvento());
            carrito.getItems().add(new DetalleCarrito(agregarCarrito.nuevaCantidad(), agregarCarrito.nLocalidad(),new ObjectId(eventoCARRITO.getId())));
>>>>>>> 3d42869c2d3384b0ae08a115787bd0cf4b2ad5eb
        }
        carritoRepo.save(carrito);
        return "Se agregó el evento de manera exitosa";
    }

    @Override
    public String vaciarCarrito(String id) throws CarritoException {
        try{
            carritoRepo.deleteById(id);
            return "Se vació correctamente";
        }catch (CarritoException c){
            throw new CarritoException("Carrito no encontrado");
        }
    }

    @Override
    public String eliminarEventoCarrito(CarritoDTO eliminarDelCarrito) throws CarritoException {
        Carrito carrito= findById(eliminarDelCarrito.idCarrito());

        for(int i=0;i<carrito.getItems().size();i++){
            if(carrito.getItems().get(i).getIdEvento().equals(eliminarDelCarrito.idEvento())){
                carrito.getItems().remove(i);
                break;
            }
        }
        carritoRepo.save(carrito);
        return "Elemento borrado correctamente";
    }

    @Override
    public List<CarritoDTO> listarElementos(CarritoDTO carritoDTO) throws CarritoException {
        Carrito carritoItems= findById(carritoDTO.idCarrito());

        List<CarritoDTO> respuesta = new ArrayList<>();
        for(int i=0;i<carritoItems.getItems().size();i++){
            respuesta.add(new CarritoDTO(
                    carritoItems.getItems().get(i).getIdEvento().toString(),
                    carritoItems.getId(),
                    carritoItems.getItems().get(i).getCantidad(),
                    carritoItems.getItems().get(i).getNombreLocalidad()
            ));
        }
        return respuesta;

    }

    @Override
    public String editarCantidad(CarritoDTO carritoDTO) throws CarritoException, Exception{
        Carrito carrito= findById(carritoDTO.idCarrito());
        if(eventoServicio.obtenerEvento(carritoDTO.idEvento()).obtenerLocalidad(carritoDTO.nLocalidad())==null){
            throw new CarritoException("La localidad seleccionada no existe");
        }
        for(int i=0;i<carrito.getItems().size();i++){
            if(carrito.getItems().get(i).getIdEvento().equals(carritoDTO.idEvento())){
                int nuevaCantidad= eventoServicio.obtenerEvento(carritoDTO.idEvento()).obtenerLocalidad(carritoDTO.nLocalidad()).getEntradasVendidas()+carritoDTO.nuevaCantidad()-carrito.getItems().get(i).getCantidad();
                if(nuevaCantidad<=eventoServicio.obtenerEvento(carritoDTO.idEvento()).obtenerLocalidad(carritoDTO.nLocalidad()).getCapacidadMaxima()){
                    carrito.getItems().get(i).setCantidad(carritoDTO.nuevaCantidad());
                    break;
                }else{
                    throw new CarritoException("Se ha excedido la capacidad máxima de entradas");
                }

            }
        }
        carritoRepo.save(carrito);
        return "El carrito se ha editado corectamente";
    }

    @Override
    public Carrito obtenerCarrito(String id) throws CarritoException{
        return findById(id);
    }

    @Override
    public void crearCarrito(String id) throws CarritoException {
        if(cuentaServicio.obtenerCuenta(id)!=null){
            Carrito carrito= new Carrito(LocalDateTime.now(),new ObjectId(id), new ArrayList<DetalleCarrito>());
            carritoRepo.save(carrito);
        }else{
            throw new CarritoException("Cuenta no existente");
        }
    }

    @Override
    public void eliminarCarrito(String id) throws CarritoException {
        carritoRepo.deleteById(id);
    }

}
