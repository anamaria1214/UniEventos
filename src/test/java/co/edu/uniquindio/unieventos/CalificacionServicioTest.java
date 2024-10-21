package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.dto.CalificacionDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CalificacionServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalificacionServicioTest {

    @MockBean
    private CalificacionServicio calificacionServicio;

    @Test
    public void testCalificarEvento() throws Exception {
        CalificacionDTO calificarEventoDTO = new CalificacionDTO("6701a8f9baeae320b8bafc01", "6402b8e9baeae320c8bafc01", 4);
        assertDoesNotThrow(() -> {
            calificacionServicio.calificarEvento(calificarEventoDTO);
        });
    }

    @Test
    public void testObtenerPromedioCalificaciones() throws Exception {
        String idEvento = "6702b8e9baeae320c8bafc01";
        double promedio = calificacionServicio.obtenerPromedioCalificaciones(idEvento);
        assertEquals(0, promedio, 0.01);
    }

}
