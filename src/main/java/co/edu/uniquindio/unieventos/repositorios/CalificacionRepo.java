package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.documentos.Calificacion;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.documentos.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionRepo extends MongoRepository<Calificacion, String> {
    Optional<Calificacion> findByCuentaAndEvento(String idCuenta, String idEvento);
    List<Calificacion> findByEvento(String evento);
}
