package com.digis.IHernandezProgramacionNCapas.RestController;

import com.digis.IHernandezProgramacionNCapas.JPA.UsuariosJPA;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoRestController {

    @GetMapping("/suma")
    public String Suma(@RequestParam int num1, @RequestParam int num2) {
        return "La suma es " + (num1 + num2);
    }

    @PostMapping("Saludo")
    public String Saludo(@RequestBody UsuariosJPA usuario) {
        return "Hola " + usuario.getNombre();
    }

    @PostMapping("SumaA")
    public String SumaA(@RequestBody List<Integer> numeros) {
        int suma = 0;
        for (int num : numeros) {
            suma += num;
        }
        return "La suma es " + suma;
    }
    
    @PatchMapping("/actualizarNombre/{pos}/{nuevoNombre}")
    public List<String> actualizarNombre(
            @PathVariable int pos,
            @PathVariable String nuevoNombre,
            @RequestBody List<String> nombres) {

        nombres.set(pos, nuevoNombre);
        return nombres;
    }

}
