package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.exceptions.CarritoException;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import co.edu.uniquindio.unieventos.modelo.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.repositorios.CarritoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;
    private final EventoServicio eventoServicio;

    public CarritoServicioImpl(CarritoRepo carritoRepo, EventoServicio eventoServicio) {
        this.carritoRepo = carritoRepo;
        this.eventoServicio = eventoServicio;
    }

    private Carrito findById(String id) throws CarritoException{
        try{
            Optional<Carrito> carritoOptional= carritoRepo.findById(id);
            return carritoOptional.get();
        }catch (CarritoException e) {
            throw new CarritoException("Carrito no encontrado");
        }
    }

    @Override
    public String agregarEventoCarrito(CarritoDTO agregarCarrito) throws  Exception ,CarritoException {
        Carrito carrito= findById(agregarCarrito.idCarrito());
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
            carrito.getItems().add(new DetalleCarrito(agregarCarrito.nuevaCantidad(), agregarCarrito.nLocalidad(), agregarCarrito.idEvento()));
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

        return "Elemento borrado correctamente";
    }

    @Override
    public List<CarritoDTO> listarElementos(CarritoDTO carritoDTO) throws CarritoException {
        Carrito carritoItems= findById(carritoDTO.idCarrito());

        List<CarritoDTO> respuesta = new ArrayList<>();
        for(int i=0;i<carritoItems.getItems().size();i++){
            respuesta.add(new CarritoDTO(
                    carritoItems.getItems().get(i).getIdEvento(),
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
        return "El carrito se ha editado corectamente";
    }

    @Override
    public Carrito obtenerCarrito(String id) throws CarritoException{
        try{
            return findById(id);
        }catch (CarritoException e){
            throw new CarritoException("Carrito no encontrado");
        }

    }

}
