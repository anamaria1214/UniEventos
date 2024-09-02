package co.edu.uniquindio.unieventos;

import co.edu.uniquindio.unieventos.modelo.documentos.Usuario;
import co.edu.uniquindio.unieventos.repositorios.UsuarioRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUsuario {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrarTest(){
        //Creamos el usuario con sus propiedades
        Usuario usuario = Usuario.builder()
                .cedula("1213444")
                .nombre("Pepito perez")
                .direccion("Calle 12 # 12-12")
                .telefono("3012223333")
                .build();

        Usuario registro = usuarioRepo.save( usuario );

        //assertNotNull(registro);
    }

}
