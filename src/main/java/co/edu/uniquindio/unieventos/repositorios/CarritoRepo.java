package co.edu.uniquindio.unieventos.repositorios;


import co.edu.uniquindio.unieventos.modelo.Carrito;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepo extends MongoRepository<Carrito, String> {
}