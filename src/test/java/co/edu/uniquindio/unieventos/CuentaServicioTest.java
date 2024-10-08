package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.dto.CrearCuentaRegistroDTO;
import co.edu.uniquindio.unieventos.dto.InfoAdicionalDTO;
import co.edu.uniquindio.unieventos.dto.LoginDTO;
import co.edu.uniquindio.unieventos.dto.ValidarCodigoDTO;
import co.edu.uniquindio.unieventos.dto.CambiarPasswordDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CuentaServicioTest {

    @Autowired
    private CuentaServicio cuentaServicio;

    @Test
    public void crearCuentaTest() {
        CrearCuentaRegistroDTO cuentaDTO = new CrearCuentaRegistroDTO("user123", "test@example.com", "password123");
        assertDoesNotThrow(() -> {
            cuentaServicio.crearCuenta(cuentaDTO);
        });
    }
    @Test
    public void editarCuentaTest() {
        InfoAdicionalDTO cuentaDTO = new InfoAdicionalDTO("miguel.florez@example.com", "Carlos", "Direccion nueva", "123456789");
        assertDoesNotThrow(() -> {
            cuentaServicio.editarCuenta(cuentaDTO);
        });
    }
    @Test
    public void eliminarCuentaTest() {
        String email = "miguel.florez@example.com";
        assertDoesNotThrow(() -> {
            cuentaServicio.eliminarCuenta(email);
        });
    }
    @Test
    public void loginTest() throws Exception {
        LoginDTO loginDTO = new LoginDTO("miguel.florez@example.com", "password123");

        assertDoesNotThrow(() -> {
            cuentaServicio.login(loginDTO);
        });
    }
    @Test
    public void validarCodigoTest() {
        ValidarCodigoDTO validarCodigoDTO = new ValidarCodigoDTO("miguel.florez@example.com", "1234");

        assertDoesNotThrow(() -> {
            cuentaServicio.validarCodig(validarCodigoDTO);
        });
    }
    @Test
    public void cambioPasswordTest() {
        CambiarPasswordDTO cambiarPasswordDTO = new CambiarPasswordDTO("miguel.florez@example.com", "1234", "newPassword");

        assertDoesNotThrow(() -> {
            cuentaServicio.cambioPassword(cambiarPasswordDTO);
        });
    }


}
