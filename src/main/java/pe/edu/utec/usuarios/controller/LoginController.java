package pe.edu.utec.usuarios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import pe.edu.utec.usuarios.model.Usuario;
import pe.edu.utec.usuarios.service.UsuarioService;


@Controller
public class LoginController {

    private final UsuarioService service;


    public LoginController(
            UsuarioService service
    ) {
        this.service = service;
    }


    @GetMapping("/")
    public String inicio() {

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String mostrarLogin() {

        return "login";
    }


    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {

        Usuario usuario =
            service.validarLogin(username, password);

        if (usuario == null) {

            model.addAttribute(
                "error",
                "Usuario o contrasena incorrectos"
            );

            return "login";
        }

        session.setAttribute("usuario", usuario);

        return "redirect:/admin";
    }


    @GetMapping("/admin")
    public String panelAdmin(
            HttpSession session
    ) {

        if (session.getAttribute("usuario") == null) {

            return "redirect:/login";
        }

        return "admin";
    }


    @GetMapping("/logout")
    public String logout(
            HttpSession session
    ) {

        session.invalidate();

        return "redirect:/login";
    }
}
