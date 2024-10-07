package co.edu.uniquindio.unieventos.servicios.interfaces;


import co.edu.uniquindio.unieventos.dto.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.InformacionOrdenCompraDTO;
import co.edu.uniquindio.unieventos.dto.ItemOrdenDTO;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.exceptions.OrdenException;
import co.edu.uniquindio.unieventos.modelo.documentos.Orden;
import com.mercadopago.resources.preference.Preference;

import java.util.List;
import java.util.Map;

public interface OrdenServicio {

    Orden crearOrden(CrearOrdenDTO crearOrdenDTO) throws Exception;
    Preference realizarPago(String idOrden) throws Exception;
    void recibirNotificacionMercadoPago(Map<String, Object> request);
    void cancelarOrden(String idOrden) throws Exception;
    List<ItemOrdenDTO> obtenerHistorialOrdenes(String idCuenta) throws OrdenException, CuentaException, Exception;
    InformacionOrdenCompraDTO obtenerInformacionOrden(String idOrden)throws OrdenException, Exception;
}
