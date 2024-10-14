package co.edu.uniquindio.unieventos.modelo.documentos;

import co.edu.uniquindio.unieventos.exceptions.LocalidadException;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCuenta;
import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import co.edu.uniquindio.unieventos.modelo.vo.CodigoValidacion;
import co.edu.uniquindio.unieventos.modelo.vo.Localidad;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoEvento;
import co.edu.uniquindio.unieventos.modelo.enums.TipoEvento;
import co.edu.uniquindio.unieventos.modelo.vo.Usuario;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("eventos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Evento {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String nombre;
    private String descripcion;
    private String direccion;
    private String ciudad;
    private LocalDateTime fecha;
    private EstadoEvento estado;
    private TipoEvento tipo;
    private String imagenPortada;
    private String imagenLocalidades;
    private List<Localidad> localidades;
    private double promedioCalificaciones;

    @Builder
    public Evento( String nombre, String descripcion, String direccion, String ciudad, LocalDateTime fecha, EstadoEvento estado, TipoEvento tipo, String imagenPortada, String imagenLocalidades, double promedioCalificaciones) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.estado = estado;
        this.tipo = tipo;
        this.imagenPortada = imagenPortada;
        this.imagenLocalidades = imagenLocalidades;
        this.localidades = new ArrayList<>();
        this.promedioCalificaciones = promedioCalificaciones;
    }

    public Localidad obtenerLocalidad(String nombre) throws LocalidadException {
        for(Localidad localidad: localidades){
            System.out.println("Localidades en Evento.java: " + localidad.getNombre());
            if(localidad.getNombre().equals(nombre)){
                return localidad;
            }else{
                throw new LocalidadException("La localidad: "+localidad.getNombre()+" no existe");
            }
        }
        return null;
    }

}
