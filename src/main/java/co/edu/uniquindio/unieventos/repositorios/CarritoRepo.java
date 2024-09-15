package co.edu.uniquindio.unieventos.repositorios;


import co.edu.uniquindio.unieventos.modelo.documentos.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepo extends MongoRepository<Carrito, String> {


}