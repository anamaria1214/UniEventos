package co.edu.uniquindio.unieventos.servicios.interfaces;


import co.edu.uniquindio.unieventos.dto.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Orden;
import com.mercadopago.resources.preference.Preference;

import java.util.Map;

public interface OrdenServicio {

    Orden crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception;
    Preference realizarPago(String idOrden) throws Exception;
    void recibirNotificacionMercadoPago(Map<String, Object> request);
}
