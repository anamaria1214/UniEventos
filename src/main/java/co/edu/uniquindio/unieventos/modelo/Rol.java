package co.edu.uniquindio.unieventos.modelo;

public enum Rol {
    CLIENTE(0),ADMINISTRADOR(1);

    private int id;
    private Rol(int id){
        this.id=id;
    }
}
