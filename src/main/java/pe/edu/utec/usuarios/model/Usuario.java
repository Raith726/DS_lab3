package pe.edu.utec.usuarios.model;

import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
        nullable = false,
        length = 100
    )
    private String nombre;

    @Column(
        nullable = false,
        length = 150,
        unique = true
    )
    private String email;

    @Column(
        nullable = false,
        length = 20
    )
    private String rol;

    @Column(
        nullable = false,
        length = 50,
        unique = true
    )
    private String username;

    @Column(
        nullable = false,
        length = 100
    )
    private String password;


    public Usuario() {
    }


    public Usuario(
            String nombre,
            String email,
            String rol,
            String username,
            String password
    ) {

        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.username = username;
        this.password = password;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getRol() {
        return rol;
    }


    public void setRol(String rol) {
        this.rol = rol;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
