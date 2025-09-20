package com.agpfdev.board.controllers;

import com.agpfdev.board.dtos.user.UserRegisterDTO;
import com.agpfdev.board.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registrar-usuario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new UserRegisterDTO("", "", "", "",
                "", "", null));
        return "users/form-registro";
    }


    @PostMapping("/registrar-usuario")
    public String salvarUsuario(@Valid @ModelAttribute("usuario") UserRegisterDTO usuario,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) return "usuario-form";

        userService.registrarUsuario(usuario);

        model.addAttribute("mensagem", "Usuário registrado com sucesso!");
        return "users/usuario-sucesso";
    }

}
