package co.edu.uniquindio.unieventos.modelo;

public enum TipoCupon {

    MULTIPLE(0),UNICO(1);

    private int id;
    private TipoCupon(int id){
        this.id=id;
    }
}
