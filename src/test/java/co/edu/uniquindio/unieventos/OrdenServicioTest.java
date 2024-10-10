package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.dto.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.InformacionOrdenCompraDTO;
import co.edu.uniquindio.unieventos.dto.ItemOrdenDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Orden;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrdenServicioTest {
    @Autowired
    private OrdenServicio ordenServicio;
    @Test
    public void crearOrdenTest() {

        CrearOrdenDTO crearOrdenDTO = new CrearOrdenDTO(
                "6701a8f9baeae320b8bafc01","","6704b1e9baeae320d8cafc02"
        );
        assertDoesNotThrow(()->
        {
            Orden orden = ordenServicio.crearOrden(crearOrdenDTO);
            assertNotNull(orden);
        });

    }
    @Test
    public void cancelarOrdenTest(){
        String id = "6703a8d9baeae320d8bafc04";
        assertDoesNotThrow(()->{
            ordenServicio.cancelarOrden(id);
            List<ItemOrdenDTO> ordenes = ordenServicio.obtenerHistorialOrdenes(id);
            assertNull(ordenes);
        });
    }

    @Test
    public void historial(){
        String id ="";
        assertDoesNotThrow(()->{
           List<ItemOrdenDTO> ordenes = ordenServicio.obtenerHistorialOrdenes(id);
           assertNotNull(ordenes);
        });
    }
    @Test
    public void detalleOrden(){
        String id ="";
        assertDoesNotThrow(()->{
            InformacionOrdenCompraDTO info = ordenServicio.obtenerInformacionOrden(id);
            assertNotNull(info);
        });
    }

}
