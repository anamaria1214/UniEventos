package co.edu.uniquindio.unieventos.modelo.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Localidad {

    private String nombre;
    private float precio;
    private int entradasVendidas;
    private int capacidadMaxima;
}
