package pe.edu.utec.usuarios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import pe.edu.utec.usuarios.model.Usuario;
import pe.edu.utec.usuarios.service.UsuarioService;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;


    public UsuarioController(
            UsuarioService service
    ) {

        this.service = service;
    }


    @GetMapping
    public String listar(
            HttpSession session,
            Model model
    ) {

        if (session.getAttribute("usuario") == null) {

            return "redirect:/login";
        }

        model.addAttribute(
            "usuarios",
            service.listarTodos()
        );

        return "usuarios/lista";
    }


    @GetMapping("/{id}")
    public String detalle(
            @PathVariable Long id,
            HttpSession session,
            Model model
    ) {

        if (session.getAttribute("usuario") == null) {

            return "redirect:/login";
        }

        model.addAttribute(
            "usuario",
            service.obtenerPorId(id)
        );

        return "usuarios/detalle";
    }


    @GetMapping("/nuevo")
    public String nuevo(
            HttpSession session,
            Model model
    ) {

        if (session.getAttribute("usuario") == null) {

            return "redirect:/login";
        }

        model.addAttribute(
            "usuario",
            new Usuario()
        );

        return "usuarios/formulario";
    }


    @PostMapping
    public String guardar(
            @ModelAttribute Usuario usuario,
            HttpSession session,
            Model model
    ) {

        if (session.getAttribute("usuario") == null) {

            return "redirect:/login";
        }

        try {

            service.guardar(usuario);

        } catch (IllegalArgumentException e) {

            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuario", usuario);

            return "usuarios/formulario";
        }

        return "redirect:/usuarios";
    }


    @GetMapping("/{id}/editar")
    public String editar(
            @PathVariable Long id,
            HttpSession session,
            Model model
    ) {

        if (session.getAttribute("usuario") == null) {

            return "redirect:/login";
        }

        model.addAttribute(
            "usuario",
            service.obtenerPorId(id)
        );

        return "usuarios/formulario";
    }


    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @ModelAttribute Usuario usuario,
            HttpSession session,
            Model model
    ) {

        if (session.getAttribute("usuario") == null) {

            return "redirect:/login";
        }

        try {

            service.actualizar(
                id,
                usuario
            );

        } catch (IllegalArgumentException e) {

            usuario.setId(id);

            model.addAttribute("error", e.getMessage());
            model.addAttribute("usuario", usuario);

            return "usuarios/formulario";
        }

        return "redirect:/usuarios";
    }


    @PostMapping("/{id}/eliminar")
    public String eliminar(
            @PathVariable Long id,
            HttpSession session
    ) {

        if (session.getAttribute("usuario") == null) {

            return "redirect:/login";
        }

        service.eliminar(id);

        return "redirect:/usuarios";
    }
}
