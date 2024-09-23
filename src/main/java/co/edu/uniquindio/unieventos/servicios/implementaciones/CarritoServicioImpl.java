package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.exceptions.CarritoException;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import co.edu.uniquindio.unieventos.modelo.vo.DetalleCarrito;
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
    public String agregarEventoCarrito(CarritoDTO agregarCarrito) throws CarritoException {

        Carrito carrito= getById(agregarCarrito.idCarrito());
        carrito.getItems().add(new DetalleCarrito(agregarCarrito.nuevaCantidad(), agregarCarrito.nLocalidad(), agregarCarrito.idEvento()));

        return "Se agregó el evento de manera exitosa";
    }

    @Override
    public String vaciarCarrito(String id) throws Exception {
        carritoRepo.deleteById(id);
        return null;
    }

    @Override
    public String eliminarEventoCarrito(CarritoDTO eliminarDelCarrito) throws Exception {

        Carrito carrito= getById(eliminarDelCarrito.idCarrito());
        for(int i=0;i<carrito.getItems().size();i++){
            if(carrito.getItems().get(i).getIdEvento().equals(eliminarDelCarrito.idEvento())){
                carrito.getItems().remove(i);
            }
        }

        return "Elemento borrado correctamente";
    }

    @Override
    public List<CarritoDTO> listarElementos(CarritoDTO carritoDTO) throws CarritoException {

        Carrito carritoItems= getById(carritoDTO.idCarrito());//Se obtiene el carrito, y en caso de que no exista se maneja la excepcion
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
    public String editarCantidad(CarritoDTO carritoDTO) throws CarritoException {
        Carrito carrito = getById(carritoDTO.idCarrito());

        for (int i = 0; i < carrito.getItems().size(); i++) {
            if (carrito.getItems().get(i).getIdEvento().equals(carritoDTO.idEvento())) {
                carrito.getItems().get(i).setCantidad(carritoDTO.nuevaCantidad());
            }
        }

        return "El carrito se ha editado correctamente";
    }

    private Carrito getById(String id) throws CarritoException {
        return carritoRepo.findById(id)
                .orElseThrow(() -> new CarritoException("No se ha encontrado ningún carrito con el id dado"));
    }

}
