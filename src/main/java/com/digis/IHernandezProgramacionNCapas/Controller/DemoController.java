package com.digis.IHernandezProgramacionNCapas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller // define una clase como controlador
@RequestMapping("demo") // para definir una ruta base del controlado
public class DemoController {
    
    @GetMapping("HolaMundo")
    public String HolaMundo(){
        return "HolaMundo";
    }
    
    @GetMapping("/persona/{persona}")
    public String Persona(@PathVariable String persona, Model model){
        
        model.addAttribute("persona", persona);
        
        return "HolaMundo";
    }
}
