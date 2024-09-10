package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventoRepo extends MongoRepository<Evento, String> {
    boolean existsById(String id);

    @Query("{nombre:  ?0}")
    Optional<Evento> filtrarPorNombre(String nombre);

    @Query("{ciudad:  ?0}")
    Optional<Evento> filtrarPorCiudad(String ciudad);

    @Query("{tipo:  ?0}")
    Optional<Evento> filtrarPorTipo(String tipo);

    @Query("{nombre:  ?0, ciudad: ?1}")
    Optional<Evento> filtrarPorCiudadNombre(String nombre, String ciudad);

    @Query("{nombre:  ?0, tipo: ?1}")
    Optional<Evento> filtrarPorTipoNombre(String nombre, String tipo);

    @Query("{ciudad:  ?0, tipo: ?1}")
    Optional<Evento> filtrarPorTipoCiudad(String ciudad, String tipo);

}
