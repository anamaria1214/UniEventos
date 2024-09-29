package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCuenta {

    @Autowired
    private CuentaRepo cuentaRepo;

    @Test
    public void registrarTest(){

        Cuenta cuenta = Cuenta.builder().email("hola").build();
        cuentaRepo.save(cuenta);
    }



}
