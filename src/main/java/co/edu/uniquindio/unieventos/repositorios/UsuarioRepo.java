package co.edu.uniquindio.unieventos.repositorios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniquindio.unieventos.modelo.documentos.Usuario;


@Repository
public interface UsuarioRepo extends MongoRepository<Usuario, String> {


}


