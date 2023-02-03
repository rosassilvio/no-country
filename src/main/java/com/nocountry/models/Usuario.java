package com.nocountry.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String telefono;
    private String perfil;
    private boolean activo=true;
    private String email;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRol> usuarioRols=new HashSet<>();


    @Override
    public String toString() {
        return "Registro exitoso: " +'.'+ '\n' +
                "NOMBRE: " + nombre +'.'+ '\n' +
                "MAIL: " + email +'.'+ '\n' +
                "Ante cualquier consulta puedes escribir a este correo."
                ;
    }

}
