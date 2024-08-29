package co.edu.uniquindio.unieventos.modelo;

public enum EstadoCuenta {

        ACTIVO(0),INACTIVO(1), ELIMINADA(2);

        private int id;
        private EstadoCuenta(int id){
            this.id=id;
        }

}
