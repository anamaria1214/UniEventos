package co.edu.uniquindio.unieventos.modelo.enums;

public enum EstadoEvento {
    ACTIVO(0),INACTIVO(1);

    private int id;
    private EstadoEvento(int id){
        this.id=id;
    }
}
