package com.digis.IHernandezProgramacionNCapas.Controller;

import com.digis.IHernandezProgramacionNCapas.Service.ActivacionCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/activar-cuenta")
public class ActivacionCuentaController {

    @Autowired
    private ActivacionCuentaService activacionCuentaService;

    @GetMapping
    public String activarCuenta(@RequestParam String token, Model model) {
        String mensaje = activacionCuentaService.activarCuenta(token);

        model.addAttribute("mensaje", mensaje);
        model.addAttribute("tipoMensaje", mensaje.contains("exitosamente") ? "success" : "error");
        return "login"; // Spring busca login.html en templates
    }
}
