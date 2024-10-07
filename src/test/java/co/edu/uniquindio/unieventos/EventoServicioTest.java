package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.dto.CrearEventoDTO;
import co.edu.uniquindio.unieventos.dto.EditarEventoDTO;
import co.edu.uniquindio.unieventos.dto.ItemEventoDTO;
import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import co.edu.uniquindio.unieventos.modelo.vo.Localidad;
import co.edu.uniquindio.unieventos.servicios.interfaces.EventoServicio;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class EventoServicioTest {
    @Autowired
    EventoServicio eventoServicio;

    @Test
    public void crearEventoTest() {
        List<Localidad> localidades = new ArrayList<>();
        localidades.add(new Localidad("Platea", 12000,300,500));
        localidades.add(new Localidad("Oriente", 15500,300,600));
        CrearEventoDTO crearEventoDTO = new CrearEventoDTO(
                "Concierto de Rock",
                "Un gran concierto con bandas famosas.",
                "Calle Falsa 123",
                "Bogotá",
                LocalDateTime.of(2024, 12, 15, 20, 0),
                TipoEvento.CONCIERTO,
                "imagen_portada.jpg",
                "imagen_localidades.jpg",
                localidades
        );
        assertDoesNotThrow(()->{
            eventoServicio.crearEvento(crearEventoDTO);
        });
    }

    @Test
    public void EditarEvento(){
        String idEvento= "67036d34db050c01415e8cb6";//Evento que se va a actualizar
        //Nuevos atributos
        List<Localidad> localidades = new ArrayList<>();
        localidades.add(new Localidad("Platea", 12000,300,500));
        localidades.add(new Localidad("Oriente", 15500,300,600));
        String nombre= "Concierto de Rock";
        String descripcion= "Concierto de Rock en Español";
        String direccion= "5ta Avenida, portal Americas";
        String ciudad= "Bogotá";
        LocalDateTime fecha= LocalDateTime.of(2024, 12, 15, 20, 0);
        TipoEvento tipoEvento= TipoEvento.CONCIERTO;
        String imagenPortada=  "imagen_portada.jpg";
        String imagenLocalidades=  "imagen_localidades.jpg";

        //Instancia de la clase
        EditarEventoDTO editarEventoDTO= new EditarEventoDTO(nombre,descripcion,direccion,ciudad,fecha,tipoEvento,imagenPortada,imagenLocalidades,localidades);
        assertDoesNotThrow(()->{
            eventoServicio.editarEvento(idEvento, editarEventoDTO);
        });
    }

    @Test
    public void eliminarEventoTest(){
        String idEvento= "6402b8e9baeae320c8bafc01";
        assertDoesNotThrow(()->{
           eventoServicio.eliminarEvento(idEvento);
        });
    }

    @Test
    public void listarEventos(){
        List<ItemEventoDTO> listaEventos= eventoServicio.listarEventos();
        assertEquals(6,listaEventos.size());
    }

}
