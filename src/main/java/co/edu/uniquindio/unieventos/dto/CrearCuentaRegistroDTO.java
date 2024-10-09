package co.edu.uniquindio.unieventos.dto;

import co.edu.uniquindio.unieventos.modelo.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CrearCuentaRegistroDTO (@NotBlank @Length(max=20) String idUsuario,
                                      @NotBlank (message= "El correo es obligatorio") @Email String correo,
                                      @NotBlank @Length (min=8) String password,
                                      @NotNull Rol rol)
{}
