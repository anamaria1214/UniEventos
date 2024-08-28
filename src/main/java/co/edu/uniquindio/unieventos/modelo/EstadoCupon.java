package co.edu.uniquindio.unieventos.modelo;

public enum EstadoCupon {

    DISPONIBLE(0),NO_DISPONIBLE(1);

    private int id;
    private EstadoCupon(int id){
        this.id=id;
    }
}
