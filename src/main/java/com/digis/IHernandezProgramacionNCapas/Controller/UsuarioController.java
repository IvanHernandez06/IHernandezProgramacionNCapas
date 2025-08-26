package com.digis.IHernandezProgramacionNCapas.Controller;

import com.digis.IHernandezProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.DAO.UsuarioJPADAOImplementation;
import com.digis.IHernandezProgramacionNCapas.ML.Colonia;
import com.digis.IHernandezProgramacionNCapas.ML.Direccion;
import com.digis.IHernandezProgramacionNCapas.ML.ErrorCM;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.RolML;
import com.digis.IHernandezProgramacionNCapas.ML.UsuariosML;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;
    @Autowired
    private DireccionDAOImplementation direccionDAOImplentation;
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

        Result result = usuarioDAOImplementation.GetAll(new UsuariosML("", "", ""));

        model.addAttribute("usuarioBusqueda", new UsuariosML());
        if (result.correct) {
            model.addAttribute("usuarios", result.objects);
        } else {
            model.addAttribute("usuarios", null);
        }
        return "UsuarioIndex";
    }

    @PostMapping
    public String Index(Model model, @ModelAttribute("usuarioBusqueda") UsuariosML usuarioBusqueda) {

        Result result = usuarioDAOImplementation.GetAll(usuarioBusqueda);
//      model.addAttribute("alumnoBusqueda", alumnoBusqueda);
        model.addAttribute("usuarios", result.objects);
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

            model.addAttribute("Usuario", usuario);

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

            model.addAttribute("Direccion", direccionDAOImplentation.GetId(idDireccion));

            model.addAttribute("Usuario", usuario);

        }
        model.addAttribute("Roles", rolDAOImplementation.GetAll().objects);
        model.addAttribute("Paises", paisDAOImplementation.GetAll().objects);
        return "usuarioForm";

    }

    @PostMapping("usuarioForm") // localhost:8081/alumno/add
    public String Add(@ModelAttribute UsuariosML usuario,
            Model model,
            @RequestParam("imagenFile") MultipartFile imagen) {

        if (imagen != null) {
            String nombre = imagen.getOriginalFilename();

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

    
    @RequestMapping("getDireccionById")
    @ResponseBody
    public Result mostrarDireccion(@RequestParam("idDireccion") int IdDireccion, Model model) {
        return direccionDAOImplentation.GetId(IdDireccion);
    }

    
    @GetMapping("getEstadosByIdPais/{IdPais}")
    @ResponseBody // retorne un dato estructurado - JSON
    public Result EstadoByPais(@PathVariable int IdPais) {
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

    @PostMapping("cargamasiva")
    public String CargaMasiva(@RequestParam("archivo") MultipartFile file,
            Model model,
            HttpSession session) {

        String root = System.getProperty("user.dir");
        String rutaArchivo = "/src/main/resources/archivos/";
        String fechaSubida = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
        String rutaFinal = root + rutaArchivo + fechaSubida + file.getOriginalFilename();

        try {
            file.transferTo(new File(rutaFinal));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        if (file.getOriginalFilename().split("\\.")[1].equals("txt")) {
            List<UsuariosML> usuarios = ProcesarTXT(new File(rutaFinal));
            List<ErrorCM> errores = ValidarDatos(usuarios);

            if (errores.isEmpty()) {
                model.addAttribute("listaErrores", errores);
                model.addAttribute("archivoCorrecto", true);
                session.setAttribute("path", rutaFinal);

            } else {
                model.addAttribute("listaErrores", errores);
                model.addAttribute("archivoCorrecto", false);

            }

            //si lista errores diferente de vacio, intentar desplegar lista de errores en carga masiva
        } else {
            // excel

            List<UsuariosML> usuario = ProcesarExcel(new File(rutaFinal));

            List<ErrorCM> errores = ValidarDatos(usuario);

            if (errores.isEmpty()) {

                model.addAttribute("listaErrores", errores);
                model.addAttribute("archivoCorrecto", true);
                session.setAttribute("path", rutaFinal);

            } else {

                model.addAttribute("listaErrores", errores);
                model.addAttribute("archivoCorrecto", false);

            }
        }
        return "CargaMasiva";
    }

    @GetMapping("cargamasiva/procesar")
    public String CargaMasiva(HttpSession session) {
        try {

            String ruta = session.getAttribute("path").toString();
            List<UsuariosML> usuarios;

            if (ruta.split("\\.")[1].equals("txt")) {
                usuarios = ProcesarTXT(new File(ruta));
            } else {
                usuarios = ProcesarExcel(new File(ruta));
            }
            for (UsuariosML usuario : usuarios) {
                usuarioDAOImplementation.Add(usuario);
            }
            session.removeAttribute("path");

        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return "redirect:/Usuario";
    }

    private List<UsuariosML> ProcesarExcel(File file) {

        List<UsuariosML> usuarios = new ArrayList<>();

        try {

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                UsuariosML usuario = new UsuariosML();

                usuario.setUsername(row.getCell(0).toString());
                usuario.setNombre(row.getCell(1).toString());
                usuario.setApellidoPaterno(row.getCell(2).toString());
                usuario.setApellidoMaterno(row.getCell(3).toString());
                usuario.setSexo((row.getCell(4).toString() != null && !row.getCell(4).toString().equals("")) ? row.getCell(4).toString().charAt(0) : ' ');
                usuario.setEmail(row.getCell(5).toString());
                usuario.setPassword(row.getCell(6).toString());

                usuario.setFechaNacimiento(
                        row.getCell(7) == null ? ""
                        : (row.getCell(7).getCellType() == CellType.NUMERIC
                        ? (DateUtil.isCellDateFormatted(row.getCell(7))
                        ? new SimpleDateFormat("yyyy/MM/dd").format(row.getCell(7).getDateCellValue())
                        : new SimpleDateFormat("yyyy/MM/dd").format(DateUtil.getJavaDate(row.getCell(7).getNumericCellValue())))
                        : row.getCell(7).getStringCellValue())
                );

                usuario.setCurp(row.getCell(8).toString());
                DataFormatter dataFormatter = new DataFormatter();
                usuario.setTelefono(row.getCell(9) != null ? dataFormatter.formatCellValue(row.getCell(9)) : "");
                usuario.setCelular(row.getCell(10) != null ? dataFormatter.formatCellValue(row.getCell(10)) : "");

                usuario.RolML = new RolML();
                usuario.RolML.setIdRol(row.getCell(11) != null ? (int) row.getCell(11).getNumericCellValue() : 0);

                Direccion direccion = new Direccion();
                direccion.setCalle(row.getCell(12).toString());
                direccion.setNumExterior(row.getCell(13).toString());
                direccion.setNumInterior(row.getCell(14).toString());

                Colonia colonia = new Colonia();
                colonia.setIdColonia(row.getCell(15) != null ? (int) row.getCell(15).getNumericCellValue() : 0);

                usuarios.add(usuario);

            }
            return usuarios;
        } catch (Exception ex) {
            return null;
        }
    }

    private List<UsuariosML> ProcesarTXT(File file) {

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String linea = "";

            List<UsuariosML> usuarios = new ArrayList<>();

            while ((linea = bufferedReader.readLine()) != null) {

                String[] campos = linea.split("\\|");

                UsuariosML usuario = new UsuariosML();

                usuario.setUsername(campos[0]);
                usuario.setNombre(campos[1]);
                usuario.setApellidoPaterno(campos[2]);
                usuario.setApellidoMaterno(campos[3]);
                usuario.setSexo(campos[4].charAt(0));
                usuario.setEmail(campos[5]);
                usuario.setPassword(campos[6]);
                usuario.setFechaNacimiento(campos[7]);
                usuario.setCurp(campos[8]);
                usuario.setTelefono(campos[9]);
                usuario.setCelular(campos[10]);

                usuario.RolML = new RolML();
                usuario.RolML.setIdRol(Integer.parseInt(campos[11]));

                usuario.Direccion = new ArrayList<>(); //instancias lista de direcciones
                Direccion direccion = new Direccion(); //instancias un modelo direccion
                direccion.setCalle(campos[12]);
                direccion.setNumExterior(campos[13]);
                direccion.setNumInterior(campos[14]);
                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(Integer.parseInt(campos[15]));

                usuario.Direccion.add(direccion); //agregar direccion a la lista de direcciones

                usuarios.add(usuario); // agregar usuario junto direcciones

            }
            return usuarios;
        } catch (Exception ex) {
            System.out.println("error");
            return null;
        }
    }

    private List<ErrorCM> ValidarDatos(List<UsuariosML> usuarios) {
        List<ErrorCM> errores = new ArrayList<>();

        int linea = 1;
        for (UsuariosML usuario : usuarios) {

            if (usuario.getUsername() == null || usuario.getUsername() == "" || !usuario.getUsername().matches("[a-zA-Z]+\\d*")) {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getUsername(), "El Username tiene un campo erroneo, esta vacio y/o no se puede empezar con numero");
                errores.add(errorCM);
            }

            if (usuario.getNombre() == null || usuario.getNombre() == "" || usuario.getNombre().matches(".*\\d.*")) {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getNombre(), "El nombre tiene un campo erroneo, esta vacio o contiene algun numero");
                errores.add(errorCM);
            }

            if (usuario.getApellidoPaterno() == null || usuario.getApellidoPaterno() == "" || usuario.getApellidoPaterno().matches(".*\\d.*")) {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getApellidoPaterno(), "El apellido paterno ampo erroneo, esta vacio o contiene algun numero");
                errores.add(errorCM);
            }

            if (usuario.getApellidoMaterno() == null || usuario.getApellidoMaterno() == "" || usuario.getApellidoMaterno().matches(".*\\d.*")) {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getApellidoMaterno(), "El apellido materno tiene un campo erroneo, esta vacio o contiene algun numero");
                errores.add(errorCM);
            }

            //Faltaaaaa
            if (String.valueOf(usuario.getSexo()) == null || String.valueOf(usuario.getSexo()) == "" || !String.valueOf(usuario.getSexo()).matches("[MF]")) {
                // Si el sexo es vacío o no es 'M' ni 'F'
                ErrorCM errorCM = new ErrorCM(linea, String.valueOf(usuario.getSexo()), "El sexo es un campo erroneo o esta vacio");
                errores.add(errorCM);
            }

            //Formato de email
            if (usuario.getEmail() == null || usuario.getEmail() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getEmail(), "El email tiene un campo erroneo, esta vacio o esta mal el formato");
                errores.add(errorCM);
            }

            //Requisitos
//            if (usuario.getPassword() == null || usuario.getPassword() == "" || !usuario.getPassword().matches("^(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=.{8,})")) {
//                ErrorCM errorCM = new ErrorCM(linea, usuario.getPassword(), "La password tiene un campo erroneo, esta vacio o no contiene los requisitos(minimo 8 caracteres,un especial,una mayuscula)");
//                errores.add(errorCM);
//            }
            //Formato de fecha
            if (usuario.getFechaNacimiento() == null || usuario.getFechaNacimiento() == "" || !usuario.getFechaNacimiento().matches("^\\d{4}/\\d{2}/\\d{2}$")) {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getFechaNacimiento(), "La Fecha de Nacimiento tiene un campo erroneo, esta vacio o su formato esta mal YYYY/MM/DD");
                errores.add(errorCM);
            }

            //Comparacion con Apellidos Nombre y Fecha
            if (usuario.getCurp() == null || usuario.getCurp() == "") {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getCurp(), "La curp tiene un campo erroneo, esta vacio o no tiene relacion con sus datos ya registrados");
                errores.add(errorCM);
            }

            //Digitos 10
            if (usuario.getTelefono() == null || usuario.getTelefono() == "" || usuario.getTelefono().length() <= 10 || !usuario.getTelefono().matches("\\d+")) {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getTelefono(), "La telefono tiene un campo erroneo, esta vacio o num minimo de 10 digitos");
                errores.add(errorCM);
            }

            if (usuario.getCelular() == null || usuario.getCelular() == "" || usuario.getCelular().length() <= 10 || !usuario.getCelular().matches("\\d+")) {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getCelular(), "El celular tiene un campo erroneo, esta vacio o num minimo de 10 digitos");
                errores.add(errorCM);
            }

            if (usuario.getRolML() == null || usuario.getRolML().getIdRol() == 0 || usuario.getRolML().getIdRol() > 4) {
                ErrorCM errorCM = new ErrorCM(linea, usuario.getTelefono(), "La telefono tiene un campo erroneo, esta vacio o num minimo de 10 digitos");
                errores.add(errorCM);
            }

            linea++;
        }
        return errores;
    }
}
