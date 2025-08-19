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
import java.util.Base64;
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
import org.springframework.web.multipart.MultipartFile;

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
            model.addAttribute("Roles", rolDAOImplementation.GetAll().objects);

            model.addAttribute("Paises", paisDAOImplementation.GetAll().objects);

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
            @RequestParam(required = false) Integer idDireccion,
            Model model) {

        if (idDireccion == null) {
//            Editar Usuario\
            UsuariosML usuario = new UsuariosML();
            usuario.setIdUsuario(idUsuario);

            usuario.setDireccion(new ArrayList<>());
            Direccion direccion = new Direccion();
            direccion.setIdDireccion(-1);
            usuario.getDireccion().add(direccion);

            model.addAttribute("Usuario", usuarioDAOImplementation.GetId(idDireccion).objects);

        } else if (idDireccion == 0) {
//            Agregar nueva dirección a usuario existente
            UsuariosML usuario = new UsuariosML();
            usuario.setIdUsuario(idUsuario);
            usuario.setDireccion(new ArrayList<>());
            Direccion direccion = new Direccion();
            model.addAttribute("Usuario", usuario);

        } else {
//            Editar dirección existente
            UsuariosML usuario = new UsuariosML();
            usuario.setIdUsuario(idUsuario);
            usuario.Direccion = new ArrayList<>();
            Direccion direccion = new Direccion();
            direccion.setIdDireccion(idDireccion);
            usuario.getDireccion().add(direccion);
            model.addAttribute("Usuario", usuario);

        }
        model.addAttribute("Roles", rolDAOImplementation.GetAll().objects);

        model.addAttribute("Paises", paisDAOImplementation.GetAll().objects);
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
    public String Add(@ModelAttribute UsuariosML usuario,
            Model model,
            @RequestParam("imagenFile") MultipartFile imagen) {
        
        if (imagen != null) {
            String nombre = imagen.getOriginalFilename();
            //archivo.jpg
            //[archivo,jpg]
            String extension = nombre.split("\\.")[1];
            if (extension.equals("jpg")) {
                try {
                    byte[] bytes = imagen.getBytes();

                    String base64Image = Base64.getEncoder().encodeToString(bytes);

                    usuario.setImagen(base64Image);
                } catch (Exception ex) {

                    System.out.println("Error");
                }
            }
        }
        Result result = usuarioDAOImplementation.Add(usuario);
        return "redirect:/Usuario";
    }
    

    @GetMapping("getEstadosByIdPais/{IdPais}")
    @ResponseBody // retorne un dato estructurado - JSON
    public Result EstadoByPais(@PathVariable int IdPais
    ) {
        return estadoDAOImplementation.GetAll(IdPais);
    }

    @GetMapping("getMunicipiosByIdEstados/{IdEstado}")
    @ResponseBody // retorne un dato estructurado - JSON
    public Result MunicipioByEstado(@PathVariable int IdEstado
    ) {
        return municipioDAOImplementation.GetAll(IdEstado);
    }

    @GetMapping("getColoniasByIdMunicipios/{IdMunicipio}")
    @ResponseBody // retorne un dato estructurado - JSON
    public Result ColoniaByMunicipio(@PathVariable int IdMunicipio
    ) {
        return coloniaDAOImplementation.GetAll(IdMunicipio);
    }

    @GetMapping("cargamasiva")
    public String CargaMasiva() {
        return "CargaMasiva";
    }

}
