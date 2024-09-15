package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.dto.ItemEventoDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.modelo.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.modelo.vo.Localidad;
import co.edu.uniquindio.unieventos.repositorios.CarritoRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;

    public CarritoServicioImpl(CarritoRepo carritoRepo) {
        this.carritoRepo = carritoRepo;
    }

    @Override
    public String agregarEventoCarrito(CarritoDTO agregarCarrito) throws Exception {

        Optional<Carrito> carritoOptional= carritoRepo.findById(agregarCarrito.idCarrito());

        Carrito carrito= carritoOptional.get();

        carrito.getItems().add(new DetalleCarrito(agregarCarrito.nuevaCantidad(), agregarCarrito.nLocalidad(), agregarCarrito.idEvento()));

        return "Se agregó el evento de manera exitosa";
    }

    @Override
    public String vaciarCarrito(String id) throws Exception {
        return null;
    }

    @Override
    public String eliminarEventoCarrito(CarritoDTO eliminarDelCarrito) throws Exception {

        Optional<Carrito> carritoOptional= carritoRepo.findById(eliminarDelCarrito.idCarrito());

        Carrito carrito= carritoOptional.get();

        for(int i=0;i<carrito.getItems().size();i++){
            if(carrito.getItems().get(i).getIdEvento().equals(eliminarDelCarrito.idEvento())){
                carrito.getItems().remove(i);
            }
        }


        return null;
    }

    @Override
    public List<CarritoDTO> listarElementos(CarritoDTO carritoDTO) throws Exception {

        Optional<Carrito> carritoOptional= carritoRepo.findById(carritoDTO.idCarrito());
        List<CarritoDTO> respuesta = new ArrayList<>();
        Carrito carritoItems= carritoOptional.get();

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

        Optional<Carrito> carritoOptional= carritoRepo.findById(carritoDTO.idCarrito());

        return null;
    }
}
