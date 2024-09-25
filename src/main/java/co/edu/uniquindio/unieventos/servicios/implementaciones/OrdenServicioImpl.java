package co.edu.uniquindio.unieventos.servicios.implementaciones;


import co.edu.uniquindio.unieventos.dto.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import co.edu.uniquindio.unieventos.modelo.documentos.Orden;
import co.edu.uniquindio.unieventos.modelo.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.modelo.vo.DetalleOrden;
import co.edu.uniquindio.unieventos.modelo.vo.Localidad;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdenServicioImpl implements OrdenServicio {

    private final CarritoServicio carritoServicio;
    private final EventoServicio eventoServicio;
    private final OrdenRepo ordenRepo;

    @Override
    public void crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception {

        Carrito carrito = carritoServicio.obtenerCarrito(crearOrdenDTO.idCarrito());
        List<DetalleCarrito> items = carrito.getItems();
        List<DetalleOrden> itemsOrden = new ArrayList<>();
        float total = 0;

        for(DetalleCarrito item : items) {
            Evento evento = eventoServicio.obtenerEventos(item.getIdEvento());
            Localidad localidad = evento.obtenerPorNombre(item.getNombreLocalidad());
            if( ! (localidad.getCapacidadMaxima() > localidad.getEntradasVendidas()+item.getCantidad()) ){
                throw new Exception("No hay aforo disponible para la localidad elegida");
            }else{
                //actualizar entradas vendidas
                total += item.getCantidad()*localidad.getPrecio();
                itemsOrden.add(new DetalleOrden(new ObjectId(), ));
            }
            //validar que el numero de entradas este disponible
        }

        //Buscar el cupon y validar que esté disponible y no esté vencido
        //aplicar el descuento (si se puede)

        Orden orden = new Orden();
        orden.setFecha(LocalDateTime.now());
        orden.setItems(itemsOrden);
        //completar objeto con set


        ordenRepo.save(orden);


    }
}
