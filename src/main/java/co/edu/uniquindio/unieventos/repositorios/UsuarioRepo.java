package co.edu.uniquindio.unieventos.repositorios;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unieventos.modelo.Usuario;


@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {
}


