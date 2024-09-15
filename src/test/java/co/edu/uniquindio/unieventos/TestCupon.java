package co.edu.uniquindio.unieventos;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.EliminarCuponDTO;
import co.edu.uniquindio.unieventos.servicios.implementaciones.CuponServicioImpl;
import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class TestCupon {

    @Mock
    private CuponRepo cuponRepository;

    @InjectMocks
    private CuponServicioImpl cuponService;  //Servicios a probar

    private List<Cupon> datasetCupones;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear un dataset simulado de cupones (al menos 5)
        datasetCupones = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Cupon cupon = new Cupon(
                    "Cupón " + i,
                    10.0f * i,
                    LocalDateTime.now().plusDays(i),
                    "CODIGO" + i,
                    EstadoCupon.DISPONIBLE,
                    TipoCupon.UNICO
            );
            datasetCupones.add(cupon);
        }

        // Simular que el repositorio devuelve este dataset cuando se solicitan todos los cupones
        when(cuponRepository.findAll()).thenReturn(datasetCupones);
    }


    @Test
    public void testEditarCuponExito() throws Exception {
        String codigo = "CODIGO1";
        EditarCuponDTO cuponDTO = new EditarCuponDTO();
        cuponDTO.setNombre("Cupón Actualizado");
        cuponDTO.setDescuento(30.0f);
        cuponDTO.setFechaVencimiento(LocalDateTime.now().plusDays(15));

        // Simular la búsqueda de un cupón por código
        when(cuponRepository.findById(codigo)).thenReturn(Optional.of(datasetCupones.get(0)));

        // Llamar al método de edición
        String resultado = cuponService.editarCupon(codigo, cuponDTO);

        // Verificar que el repositorio guarda los cambios
        verify(cuponRepository, times(1)).save(any(Cupon.class));

        // Verificar que el mensaje sea el esperado
        assertEquals("Se ha actualizado el cupón correctamente", resultado);

        // Verificar que los campos del cupón fueron actualizados
        Cupon cuponActualizado = datasetCupones.get(0);
        assertEquals(cuponDTO.getNombre(), cuponActualizado.getNombre());
        assertEquals(cuponDTO.getDescuento(), cuponActualizado.getDescuento());
        assertEquals(cuponDTO.getFechaVencimiento(), cuponActualizado.getFechaVencimiento());
    }


    @Test
    public void testEliminarCuponExito() throws Exception {
        String codigo = "CODIGO1";  // Código de uno de los cupones del dataset

        // Crear el DTO de eliminación con el nuevo estado
        EliminarCuponDTO eliminarCuponDTO = new EliminarCuponDTO();
        eliminarCuponDTO.setEstado(EstadoCupon.NO_DISPONIBLE);

        // Simular la búsqueda de un cupón por código
        when(cuponRepository.findById(codigo)).thenReturn(Optional.of(datasetCupones.get(0)));

        // Llamar al método de eliminación (cambio de estado)
        String resultado = cuponService.eliminarCupon(codigo, eliminarCuponDTO);

        // Verificar que el repositorio guarda los cambios
        verify(cuponRepository, times(1)).save(any(Cupon.class));

        // Verificar que el mensaje sea el esperado
        assertEquals("Se ha cambiado el estado a NO-DISPONIBLE", resultado);

        // Verificar que el estado del cupón fue cambiado correctamente
        Cupon cuponModificado = datasetCupones.get(0);
        assertEquals(EstadoCupon.NO_DISPONIBLE, cuponModificado.getEstado());
    }

}
