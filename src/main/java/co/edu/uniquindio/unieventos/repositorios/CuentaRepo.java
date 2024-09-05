package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends MongoRepository<Cuenta, String> {

    @Query("{email:  ?0, password: ?1}")
    Optional<Cuenta> validarDatosLogin(String email, String password);

    @Query("{email:  ?0}")
    Optional<Cuenta> buscarEmail(String email);
}
