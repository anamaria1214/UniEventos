package co.edu.uniquindio.unieventos.modelo.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Localidad {

    private String nombre;
    private float precio;
    private int entradasVendidas;
    private int capacidadMaxima;
}
