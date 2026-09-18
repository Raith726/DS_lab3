package pe.edu.utec.usuarios.service;

import java.util.List;

import pe.edu.utec.usuarios.model.Usuario;


public interface UsuarioService {

    List<Usuario> listarTodos();

    Usuario obtenerPorId(Long id);

    Usuario guardar(Usuario usuario);

    Usuario actualizar(
        Long id,
        Usuario usuario
    );

    void eliminar(Long id);

    Usuario validarLogin(
        String username,
        String password
    );
}
