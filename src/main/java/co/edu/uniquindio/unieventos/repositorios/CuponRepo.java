package co.edu.uniquindio.unieventos.repositorios;

import co.edu.uniquindio.unieventos.modelo.Cupon;
import co.edu.uniquindio.unieventos.modelo.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepo extends MongoRepository<Cupon, String> {
}