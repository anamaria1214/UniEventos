package co.edu.uniquindio.unieventos.servicios.interfaces;


import com.mercadopago.resources.preference.Preference;

import java.util.Map;

public interface OrdenServicio {

    Preference realizarPago(String idOrden) throws Exception;
    void recibirNotificacionMercadoPago(Map<String, Object> request);

}
