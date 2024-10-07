package co.edu.uniquindio.unieventos;


import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.EliminarCuponDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CuponServicioTest {
    @Autowired
    private CuponServicio cuponServicio;

    @Test
    public void crearCuponTest() {
        String nombre="Descuento Winter";
        float descuento= 12.0f;
        LocalDateTime fechaVto=LocalDateTime.of(2024, 11, 23, 23, 59);
        String codigo="WWE01";
        TipoCupon tipoCupon= TipoCupon.UNICO;
        CrearCuponDTO crearCuponDTO= new CrearCuponDTO(nombre,descuento,fechaVto,codigo,tipoCupon);
        assertDoesNotThrow(()->{
            cuponServicio.crearCupon(crearCuponDTO);
        });
    }

    @Test
    public void editarCuponTest() {
        //Se define el id del cupon
        String id= "66fe1502e8c93d4a1cf5d842";
        EditarCuponDTO editarCuponDTO = new EditarCuponDTO(
            "Descuento Halloween",
                12.5f,
                LocalDateTime.of(2024, 12, 31, 0, 0),
                TipoCupon.MULTIPLE);
        //Se espera que funcione
        assertDoesNotThrow(()->{
            cuponServicio.editarCupon(id, editarCuponDTO);
        });
    }

    @Test
    public void eliminarCuponTest() {
        String idCupon= "66fdf5e67ff62e1f58ce8616";
        assertDoesNotThrow(()->{
           cuponServicio.eliminarCupon(idCupon);
        });

    }
    @Test
    public void listarCuponesTest(){
        List<Cupon> listaCupones= cuponServicio.getAll(); //Obtenemos la lista de cupones
        //Hay 6 elementos en la lista, debemos validar esto
        assertEquals(6,listaCupones.size());
    }

}
