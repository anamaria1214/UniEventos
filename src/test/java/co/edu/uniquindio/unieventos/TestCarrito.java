package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.dto.CarritoDTO;
import co.edu.uniquindio.unieventos.repositorios.CarritoRepo;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CarritoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCarrito {

    @Autowired
    private CarritoRepo carritoRepo;
    @Autowired
    private CarritoServicio carritoServicio;

    @Test
    public void crearCarrito() throws Exception {
        CarritoDTO carrito= new CarritoDTO("2","3",23, "Localidad1");
        carritoServicio.agregarEventoCarrito(carrito);
    }
}
