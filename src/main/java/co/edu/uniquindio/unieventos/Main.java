package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.repositorios.UsuarioRepo;
import co.edu.uniquindio.unieventos.modelo.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Main {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrarTest(){
        Usuario usuario = Usuario.builder()
                .cedula("1213444")
                .nombre("Pepito perez")
                .direccion("Calle 12 # 12-12")
                .telefono("3012223333")
                .build();


        //Guardamos el usuario en la base de datos
        Usuario registro = usuarioRepo.save( usuario );


        //Verificamos que se haya guardado validando que no sea null
        Assertions.assertNotNull(registro);
    }



}