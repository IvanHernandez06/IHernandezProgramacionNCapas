package com.digis.IHernandezProgramacionNCapas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class Login {

    @GetMapping("/login")
    public String login() {
        return "login"; // Spring busca login.html en templates
    }       
    @GetMapping("/RecuperarContraseña")
    public String recuperar() {
        return "RecuperarContraseña"; // Spring busca login.html en templates
    }

}
