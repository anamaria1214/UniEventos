package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarritoServicioTest {

    @Autowired
    private CarritoServicio carritoServicio;

    @Test
    public void agregarEventoCarritoTest() {
        CarritoDTO agregarCarritoDTO = new CarritoDTO("6402b8e9baeae320c8bafc05", "6704b1e9baeae320d8cafc01", 4, "VIP");

        assertDoesNotThrow(() -> {
            carritoServicio.agregarEventoCarrito(agregarCarritoDTO);
        });
    }

    @Test
    public void vaciarCarritoTest() {
        String carritoId = "6704b1e9baeae320d8cafc01";

        assertDoesNotThrow(() -> {
            carritoServicio.vaciarCarrito(carritoId);
        });
    }

    @Test
    public void eliminarEventoCarritoTest() {
        CarritoDTO eliminarCarritoDTO = new CarritoDTO("6704b1e9baeae320d8cafc01", "eventoId123", 0, "VIP");

        assertDoesNotThrow(() -> {
            carritoServicio.eliminarEventoCarrito(eliminarCarritoDTO);
        });
    }

    @Test
    public void listarElementosTest() {
        CarritoDTO carritoDTO = new CarritoDTO(null, "6704b1e9baeae320d8cafc01", 0, null);

        List<CarritoDTO> items = carritoServicio.listarElementos(carritoDTO);

        assertNotNull(items);
        assertTrue(items.size() > 0);
    }
    @Test
    public void editarCantidadTest() {
        CarritoDTO carritoDTO = new CarritoDTO("6402b8e9baeae320c8bafc05", "6704b1e9baeae320d8cafc01", 10, "VIP");

        assertDoesNotThrow(() -> {
            carritoServicio.editarCantidad(carritoDTO);
        });
    }

    @Test
    public void crearCarritoTest() {
        String cuentaId = "6701a8f9baeae320b8bafc01";

        assertDoesNotThrow(() -> {
            carritoServicio.crearCarrito(cuentaId);
        });
    }

}
