package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.dto.ItemEventoDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.modelo.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.modelo.vo.Localidad;
import co.edu.uniquindio.unieventos.repositorios.CarritoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;

    public CarritoServicioImpl(CarritoRepo carritoRepo) {
        this.carritoRepo = carritoRepo;
    }

    public Carrito devolverCarrito(String id) throws Exception{
        try{
            Optional<Carrito> carritoOptional= carritoRepo.findById(id);
            return carritoOptional.get();
        }catch(Exception e){
            throw new Exception("Carrito no encontrado");
        }

    }

    @Override
    public String agregarEventoCarrito(CarritoDTO agregarCarrito) throws Exception {

        Carrito carrito= devolverCarrito(agregarCarrito.idCarrito());

        carrito.getItems().add(new DetalleCarrito(agregarCarrito.nuevaCantidad(), agregarCarrito.nLocalidad(), agregarCarrito.idEvento()));

        return "Se agregó el evento de manera exitosa";
    }

    @Override
    public String vaciarCarrito(String id) throws Exception {

        carritoRepo.deleteById(id);

        return "Se vacio el carrito";
    }

    @Override
    public String eliminarEventoCarrito(CarritoDTO eliminarDelCarrito) throws Exception {

        Carrito carrito= devolverCarrito(eliminarDelCarrito.idCarrito());

        for(int i=0;i<carrito.getItems().size();i++){
            if(carrito.getItems().get(i).getIdEvento().equals(eliminarDelCarrito.idEvento())){
                carrito.getItems().remove(i);
            }
        }

        return "Elemento borrado correctamente";
    }

    @Override
    public List<CarritoDTO> listarElementos(CarritoDTO carritoDTO) throws Exception {

        List<CarritoDTO> respuesta = new ArrayList<>();
        Carrito carritoItems= devolverCarrito(carritoDTO.idCarrito());

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
    public String editarCantidad(CarritoDTO carritoDTO) throws Exception {

        Carrito carrito= devolverCarrito(carritoDTO.idCarrito());

        for(int i=0;i<carrito.getItems().size();i++){
            if(carrito.getItems().get(i).getIdEvento().equals(carritoDTO.idEvento())){
                carrito.getItems().get(i).setCantidad(carritoDTO.nuevaCantidad());
            }
        }

        return "El carrito se ha editado corectamente";
    }
}
