package pe.edu.utec.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.edu.utec.usuarios.model.Usuario;
import pe.edu.utec.usuarios.repository.UsuarioRepository;


@Service
public class UsuarioServiceImpl
        implements UsuarioService {

    private final UsuarioRepository repository;


    public UsuarioServiceImpl(
            UsuarioRepository repository
    ) {
        this.repository = repository;
    }


    @Override
    public List<Usuario> listarTodos() {

        return repository.findAll();
    }


    @Override
    public Usuario obtenerPorId(Long id) {

        return repository
                .findById(id)
                .orElseThrow(
                    () -> new IllegalArgumentException(
                        "Usuario no encontrado"
                    )
                );
    }


    @Override
    public Usuario guardar(
            Usuario usuario
    ) {

        validarUsuario(usuario, null);

        return repository.save(usuario);
    }


    @Override
    public Usuario actualizar(
            Long id,
            Usuario datosUsuario
    ) {

        Usuario usuario =
            obtenerPorId(id);

        validarUsuario(datosUsuario, id);

        usuario.setNombre(
            datosUsuario.getNombre()
        );

        usuario.setEmail(
            datosUsuario.getEmail()
        );

        usuario.setRol(
            datosUsuario.getRol()
        );

        usuario.setUsername(
            datosUsuario.getUsername()
        );

        usuario.setPassword(
            datosUsuario.getPassword()
        );

        return repository.save(usuario);
    }


    @Override
    public void eliminar(Long id) {

        Usuario usuario =
            obtenerPorId(id);

        repository.delete(usuario);
    }


    @Override
    public Usuario validarLogin(
            String username,
            String password
    ) {

        return repository
                .findByUsernameAndPassword(username, password)
                .orElse(null);
    }


    private void validarUsuario(
            Usuario usuario,
            Long idActual
    ) {

        if (
            usuario.getNombre() == null ||
            usuario.getNombre().isBlank()
        ) {

            throw new IllegalArgumentException(
                "El nombre es obligatorio"
            );
        }


        if (
            usuario.getEmail() == null ||
            usuario.getEmail().isBlank()
        ) {

            throw new IllegalArgumentException(
                "El email es obligatorio"
            );
        }


        if (
            !usuario.getEmail().contains("@")
        ) {

            throw new IllegalArgumentException(
                "El email no tiene un formato valido"
            );
        }


        Optional<Usuario> existenteEmail =
            repository.findByEmail(
                usuario.getEmail()
            );

        if (
            existenteEmail.isPresent() &&
            !existenteEmail.get().getId().equals(idActual)
        ) {

            throw new IllegalArgumentException(
                "Ya existe un usuario con ese email"
            );
        }


        if (
            usuario.getRol() == null ||
            (!usuario.getRol().equals("admin") &&
             !usuario.getRol().equals("usuario"))
        ) {

            throw new IllegalArgumentException(
                "El rol debe ser admin o usuario"
            );
        }


        if (
            usuario.getUsername() == null ||
            usuario.getUsername().isBlank()
        ) {

            throw new IllegalArgumentException(
                "El username es obligatorio"
            );
        }


        Optional<Usuario> existenteUsername =
            repository.findByUsername(
                usuario.getUsername()
            );

        if (
            existenteUsername.isPresent() &&
            !existenteUsername.get().getId().equals(idActual)
        ) {

            throw new IllegalArgumentException(
                "Ya existe un usuario con ese username"
            );
        }


        if (
            usuario.getPassword() == null ||
            usuario.getPassword().isBlank()
        ) {

            throw new IllegalArgumentException(
                "La contrasena es obligatoria"
            );
        }
    }
}
