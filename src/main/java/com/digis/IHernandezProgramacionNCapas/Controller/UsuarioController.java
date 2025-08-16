package com.digis.IHernandezProgramacionNCapas.Controller;

import com.digis.IHernandezProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.ML.Direccion;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.UsuariosML;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    @Autowired
    private RolDAOImplementation rolDAOImplementation;
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @GetMapping
    public String Index(Model model) {

        Result result = usuarioDAOImplementation.GetAll();
        if (result.correct) {
            model.addAttribute("usuarios", result.objects);
        } else {
            model.addAttribute("usuarios", null);
        }
        return "UsuarioIndex";
    }

    @GetMapping("/action/{idUsuario}")
    public String add(@PathVariable("idUsuario") int idUsuario, Model model) {

        if (idUsuario == 0) {

            model.addAttribute("Direccion", rolDAOImplementation.GetAll().objects);
            model.addAttribute("Usuario", new UsuariosML());

            return "usuarioForm";
        } else {
            Result result = usuarioDAOImplementation.GetDatail(idUsuario);

            if (result.correct) {
                model.addAttribute("usuario", result.object);
            } else {
                return "Error";
            }
            return "usuarioDetail";
        }
    }

    @GetMapping("formEditable")
    public String formEditable(
            @RequestParam int idUsuario,
            @RequestParam(required = false) Integer IdDireccion,
            Model model) {

        UsuariosML usuario = new UsuariosML();
            usuario.setIdUsuario(idUsuario);

        if (IdDireccion == null) {
            // Editar Usuario
            Result result = usuarioDAOImplementation.XXXXX(idUsuario);
            // Aquí podrías cargar datos completos si lo deseas
        } else if (IdDireccion == 0) {
            // Agregar dirección
            // Puedes preparar una dirección vacía si lo necesitas
        } else {
            // Editar dirección existente
            // Aquí podrías cargar la dirección por su ID
            // Ejemplo: usuario.setDireccion(servicioDireccion.getById(IdDireccion));
        }

        model.addAttribute("Usuario", usuario);
        return "usuarioForm";
    }
    
    

    @GetMapping("usuarioForm") // localhost:8081/alumno/add
    public String add(Model model) {

        model.addAttribute("Roles", rolDAOImplementation.GetAll().objects);

        model.addAttribute("Paises", paisDAOImplementation.GetAll().objects);

        model.addAttribute("Usuario", new UsuariosML());

        return "usuarioForm";
    }

    @PostMapping("usuarioForm") // localhost:8081/alumno/add
    public String Add(@ModelAttribute UsuariosML usuario) {
        Result result = usuarioDAOImplementation.Add(usuario);
        return "redirect:/Usuario";
    }

    @GetMapping("getEstadosByIdPais/{IdPais}")
    @ResponseBody // retorne un dato estructurado - JSON
    public Result EstadoByPais(@PathVariable int IdPais) {
        return estadoDAOImplementation.GetAll(IdPais);
    }

    @GetMapping("getMunicipiosByIdEstados/{IdEstado}")
    @ResponseBody // retorne un dato estructurado - JSON
    public Result MunicipioByEstado(@PathVariable int IdEstado) {
        return municipioDAOImplementation.GetAll(IdEstado);
    }

    @GetMapping("getColoniasByIdMunicipios/{IdMunicipio}")
    @ResponseBody // retorne un dato estructurado - JSON
    public Result ColoniaByMunicipio(@PathVariable int IdMunicipio) {
        return coloniaDAOImplementation.GetAll(IdMunicipio);
    }

}
