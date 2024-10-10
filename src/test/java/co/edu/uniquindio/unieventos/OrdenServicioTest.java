package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.dto.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Orden;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}
