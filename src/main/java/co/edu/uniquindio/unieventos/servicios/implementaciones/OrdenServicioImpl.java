package co.edu.uniquindio.unieventos.servicios.implementaciones;


import co.edu.uniquindio.unieventos.dto.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.ItemOrdenDTO;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.exceptions.OrdenException;
import co.edu.uniquindio.unieventos.modelo.documentos.*;
import co.edu.uniquindio.unieventos.modelo.vo.DetalleCarrito;
import co.edu.uniquindio.unieventos.modelo.vo.DetalleOrden;
import co.edu.uniquindio.unieventos.modelo.vo.Localidad;
import co.edu.uniquindio.unieventos.modelo.vo.Pago;
import co.edu.uniquindio.unieventos.repositorios.OrdenRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdenServicioImpl implements OrdenServicio {

    private final CarritoServicio carritoImpl;
    private final EventoServicio eventoServicio;
    private final OrdenRepo ordenRepo;
    private final CuponServicio cuponServicio;
    private final CuentaServicio cuentaServicio;

    @Override
    public Orden crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception {

        Carrito carrito = carritoImpl.obtenerCarrito(crearOrdenDTO.idCarrito());
        List<DetalleCarrito> items = carrito.getItems();
        List<DetalleOrden> itemsOrden = new ArrayList<>();
        float total = 0;

        for (DetalleCarrito item : items) {
            Evento evento = eventoServicio.obtenerEvento(item.getIdEvento());
            Localidad localidad = evento.obtenerLocalidad(item.getNombreLocalidad());
            if (!(localidad.getCapacidadMaxima() > localidad.getEntradasVendidas() + item.getCantidad())) {
                throw new Exception("No hay aforo disponible para la localidad elegida");
            } else {
                total += item.getCantidad() * localidad.getPrecio();
                itemsOrden.add(new DetalleOrden(new ObjectId(), item.getIdEvento(), item.getNombreLocalidad(), localidad.getPrecio(), item.getCantidad()));
                eventoServicio.actualizarCapacidadLocalidad(evento, item.getNombreLocalidad(), item.getCantidad());
            }
        }
        Cupon cupon = cuponServicio.getCuponByCodigo(crearOrdenDTO.idCupon());
        if (cupon != null) {
            if (cuponServicio.verificarVigencia(cupon)) {
                total = total * cupon.getDescuento() + total;
            }
        }
        Orden orden = new Orden();
        orden.setFecha(LocalDateTime.now());
        orden.setItems(itemsOrden);
        orden.setIdCuenta(carrito.getIdCuenta());
        orden.setTotal(total);
        return ordenRepo.save(orden);

    }
    @Override
    public void cancelarOrden(String idOrden) throws Exception {
        Orden orden  = obtenerOrden(idOrden);
        List<DetalleOrden> items = orden.getItems();
        for(DetalleOrden item : items){
            Evento evento= eventoServicio.obtenerEvento(item.getIdEvento());
            Localidad localidad = evento.obtenerLocalidad(item.getNombreLocalidad());
            int vendidas = localidad.getEntradasVendidas()-item.getCantidad();
            eventoServicio.actualizarCapacidadLocalidad(evento, item.getNombreLocalidad(), vendidas);
        }
        ordenRepo.deleteById(idOrden);
    }

    @Override
    public List<ItemOrdenDTO> obtenerHistorialOrdenes(String idCuenta) throws CuentaException {
        Cuenta cuenta = cuentaServicio.obtenerCuenta(idCuenta);
        if(cuenta == null){
            throw new CuentaException("No existe una cuenta con ese ID");
        }
        return List.of();
    }

    public Preference realizarPago(String idOrden) throws Exception {
        Orden ordenGuardada = obtenerOrden(idOrden);
        List<PreferenceItemRequest> itemsPasarela = new ArrayList<>();

        for(DetalleOrden item : ordenGuardada.getItems()){

            Evento evento = eventoServicio.obtenerEvento(item.getIdEvento().toString());
            Localidad localidad = evento.obtenerLocalidad(item.getNombreLocalidad());

            PreferenceItemRequest itemRequest =
                    PreferenceItemRequest.builder()
                            .id(evento.getId())
                            .title(evento.getNombre())
                            .pictureUrl(evento.getImagenPortada())
                            .categoryId(evento.getTipo().name())
                            .quantity(item.getCantidad())
                            .currencyId("COP")
                            .unitPrice(BigDecimal.valueOf(localidad.getPrecio()))
                            .build();


            itemsPasarela.add(itemRequest);
        }

        MercadoPagoConfig.setAccessToken("ACCESS_TOKEN");


        // Configurar las urls de retorno de la pasarela (Frontend)
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("URL PAGO EXITOSO")
                .failure("URL PAGO FALLIDO")
                .pending("URL PAGO PENDIENTE")
                .build();


        // Construir la preferencia de la pasarela con los ítems, metadatos y urls de retorno
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .backUrls(backUrls)
                .items(itemsPasarela)
                .metadata(Map.of("id_orden", ordenGuardada.getId()))
                .notificationUrl("https://dfb5-189-50-209-152.ngrok-free.app")
                .build();


        // Crear la preferencia en la pasarela de MercadoPago
        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);


        // Guardar el código de la pasarela en la orden
        ordenGuardada.setCodigoPasarela( preference.getId() );
        ordenRepo.save(ordenGuardada);


        return preference;

    }


    @Override
    public void recibirNotificacionMercadoPago(Map<String, Object> request) {
        try {
            // Obtener el tipo de notificación
            Object tipo = request.get("type");
            // Si la notificación es de un pago entonces obtener el pago y la orden asociada
            if ("payment".equals(tipo)) {
                // Capturamos el JSON que viene en el request y lo convertimos a un String
                String input = request.get("data").toString();
                // Extraemos los números de la cadena, es decir, el id del pago
                String idPago = input.replaceAll("\\D+", "");
                // Se crea el cliente de MercadoPago y se obtiene el pago con el id
                PaymentClient client = new PaymentClient();
                Payment payment = client.get( Long.parseLong(idPago) );


                // Obtener el id de la orden asociada al pago que viene en los metadatos
                String idOrden = payment.getMetadata().get("id_orden").toString();


                // Se obtiene la orden guardada en la base de datos y se le asigna el pago
                Orden orden = obtenerOrden(idOrden);
                Pago pago = crearPago(payment);
                orden.setPago(pago);
                ordenRepo.save(orden);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //private methods
    private Orden obtenerOrden(String id) throws OrdenException {
        return ordenRepo.findById(id).orElseThrow(()->new OrdenException("No existe la orden"));
    }

    private Pago crearPago(Payment payment) {
        Pago pago = new Pago();
        pago.setId(payment.getId().toString());
        pago.setFecha( payment.getDateCreated().toLocalDateTime() );
        pago.setEstado(payment.getStatus());
        pago.setDetalleEstado(payment.getStatusDetail());
        pago.setTipoPago(payment.getPaymentTypeId());
        pago.setMoneda(payment.getCurrencyId());
        pago.setCodigoAutorizacion(payment.getAuthorizationCode());
        pago.setValorTransaccion(payment.getTransactionAmount().floatValue());
        return pago;
    }

}
