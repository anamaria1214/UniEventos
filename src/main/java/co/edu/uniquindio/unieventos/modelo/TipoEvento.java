package co.edu.uniquindio.unieventos.modelo;

public enum TipoEvento {
        DEPORTE(0),CONCIERTO(1), CULTURAL(2), MODA(3), BELLEZA(4);

        private int id;
        private TipoEvento(int id){
            this.id=id;
        }

}
