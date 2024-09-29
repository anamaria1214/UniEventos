package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.controller.CuponController;
import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import static org.mockito.ArgumentMatchers.any;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import co.edu.uniquindio.unieventos.servicios.implementaciones.CuponServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@WebMvcTest(CuponController.class)
public class CuponControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuponServicioImpl cuponServicio;  // Mockear el servicio

    @MockBean
    private CuponRepo cuponRepo;

    @InjectMocks
    private CuponController cuponController;

    @Autowired
    private ObjectMapper objectMapper; // Inyección del ObjectMapper configurado


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cuponController).build();
    }

    /**
     * Método para crear un dataset con 5 registros de cupones
     * @return lista de cupones
     */
    private List<Cupon> crearDatasetCupones() {
        return Arrays.asList(
                new Cupon("Descuento10", 10.0f, LocalDateTime.now().plusDays(30), "CODIGO10", EstadoCupon.DISPONIBLE, TipoCupon.UNICO),
                new Cupon("Descuento20", 20.0f, LocalDateTime.now().plusDays(30), "CODIGO20", EstadoCupon.DISPONIBLE, TipoCupon.UNICO),
                new Cupon("Descuento30", 30.0f, LocalDateTime.now().plusDays(30), "CODIGO30", EstadoCupon.DISPONIBLE, TipoCupon.UNICO),
                new Cupon("Descuento40", 40.0f, LocalDateTime.now().plusDays(30), "CODIGO40", EstadoCupon.DISPONIBLE, TipoCupon.MULTIPLE),
                new Cupon("Descuento50", 50.0f, LocalDateTime.now().plusDays(30), "CODIGO50", EstadoCupon.DISPONIBLE, TipoCupon.MULTIPLE)
        );
    }

    @Test
    public void testGetAllCupones() throws Exception {
        List<Cupon> cupones = crearDatasetCupones();

        // Mockear el comportamiento del servicio
        when(cuponServicio.getAll()).thenReturn(cupones);

        // Ejecutar la petición HTTP GET y verificar el resultado
        mockMvc.perform(get("/cupon")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].nombre", is(cupones.get(0).getNombre())))
                .andExpect(jsonPath("$[1].nombre", is(cupones.get(1).getNombre())));

        verify(cuponServicio, times(1)).getAll();
    }

    @Test
    public void testGetCuponById() throws Exception {
        String codigo = "CODIGO10";
        Cupon cupon = crearDatasetCupones().get(0);

        when(cuponServicio.getCuponByCodigo(codigo)).thenReturn(cupon);

        mockMvc.perform(get("/cupon/{id}", codigo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is(cupon.getNombre())))
                .andExpect(jsonPath("$.codigo", is(cupon.getCodigo())));

        verify(cuponServicio, times(1)).getCuponByCodigo(codigo);
    }

    @Test
    public void testSaveCupon() throws Exception {
        CrearCuponDTO cuponDTO = new CrearCuponDTO("Descuento15", 15.0f, LocalDateTime.now().plusDays(30), "CODIGO15",TipoCupon.UNICO);
        Cupon cupon = new Cupon("Descuento15", 15.0f, cuponDTO.getFechaVencimiento(), cuponDTO.getCodigo(), EstadoCupon.DISPONIBLE, cuponDTO.getTipo());

        when(cuponServicio.crearCupon(eq(cuponDTO))).thenReturn(cupon);

        mockMvc.perform(post("/cupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuponDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", containsString("ha sido guardado con exito")));

        verify(cuponServicio, times(1)).crearCupon(any(CrearCuponDTO.class));

    }

    @Test
    public void testUpdateCupon() throws Exception {
        String codigo = "CODIGO10";
        EditarCuponDTO editarCuponDTO = new EditarCuponDTO("NuevoNombre", 25.0f, LocalDateTime.now().plusDays(30), TipoCupon.UNICO);
        Cupon cupon = crearDatasetCupones().get(0);
        cupon.setNombre(editarCuponDTO.getNombre());
        cupon.setDescuento(editarCuponDTO.getDescuento());

        when(cuponServicio.editarCupon(eq(codigo), any(EditarCuponDTO.class))).thenReturn(cupon);

        mockMvc.perform(put("/cupon/{id}", codigo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editarCuponDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", containsString("ha sido actualizado con exito")));

        verify(cuponServicio, times(1)).editarCupon(eq(codigo), any(EditarCuponDTO.class));
    }

    @Test
    public void testDeleteCupon() throws Exception {
        String codigo = "CODIGO10";

        when(cuponServicio.eliminarCupon(eq(codigo))).thenReturn(crearDatasetCupones().get(0));

        mockMvc.perform(delete("/cupon/{id}", codigo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", containsString("ha sido eliminado con exito")));

        verify(cuponServicio, times(1)).eliminarCupon(eq(codigo));
    }
}
