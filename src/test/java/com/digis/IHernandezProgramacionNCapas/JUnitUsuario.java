package com.digis.IHernandezProgramacionNCapas;

import com.digis.IHernandezProgramacionNCapas.DAO.UsuarioDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.JPADAO.UsuarioJPADAOImplementation;
import com.digis.IHernandezProgramacionNCapas.ML.Colonia;
import com.digis.IHernandezProgramacionNCapas.ML.Direccion;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.RolML;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitUsuario {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    @Autowired
    private UsuarioJPADAOImplementation usuarioJPADAOImplementation;
    
    @Test
    public void GetAll() {
        Result result = usuarioDAOImplementation.GetAll();
        assertTrue(result.correct, "El metodo GetId no fue exitoso");
        assertNull(result.objects, "El valor obtenido es null");
    }

    @Test
    public void GetIdUsuario() {
        Result result = usuarioDAOImplementation.GetDatail(324);
        assertTrue(result.correct, "El metodo GetId no fue exitoso");
        assertNotNull(result.object, "El valor obtenido es null");
    }
    @Test
    public void testGetId() {
        int idUsuario = 324; 
        Result result = usuarioDAOImplementation.GetDatail(idUsuario);

        assertTrue(result.correct, "El método GetId no fue exitoso");
        assertNotNull(result.object, "El usuario obtenido es null");

        Usuarios usuarioObtenido = (Usuarios) result.object;
        assertEquals(324, usuarioObtenido.getIdUsuario(), "El ID de usuario no es correcto");
        assertEquals("MAr", usuarioObtenido.getUsername(), "El nombre de usuario no es correcto");
        assertEquals("Marlene", usuarioObtenido.getNombre(), "El nombre no es correcto");
        assertEquals("Bravo", usuarioObtenido.getApellidoPaterno(), "El apellido paterno no es correcto");
        assertEquals("Reyes", usuarioObtenido.getApellidoMaterno(), "El apellido materno no es correcto");
        assertEquals("Marlene@hotmail.com", usuarioObtenido.getEmail(), "El email no es correcto");

        assertNotNull(usuarioObtenido.getDireccion(), "La dirección no se recuperó correctamente");
        assertEquals(1, usuarioObtenido.getDireccion().size(), "El usuario debería tener una dirección");

        Direccion direccion = usuarioObtenido.getDireccion().get(0);
        assertEquals("Reforma", direccion.getCalle(), "La calle no es correcta");
        assertEquals("10", direccion.getNumInterior(), "El número interior no es correcto");
        assertEquals("5", direccion.getNumExterior(), "El número exterior no es correcto");
    }

    @Test
    public void AddAlumno() throws ParseException {

        Usuarios usuario = new Usuarios();
        usuario.setUsername("MAr");
        usuario.setNombre("Marlene");
        usuario.setApellidoPaterno("Bravo");
        usuario.setApellidoMaterno("Reyes");
        usuario.setEmail("Marlene@hotmail.com");
        usuario.setPassword("12345");
        usuario.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01"));
        usuario.setSexo('F');
        usuario.setTelefono("2722255281");
        usuario.setCelular("2722255281");
        usuario.setCurp("BARP030106");
        usuario.RolML = new RolML();
        usuario.RolML.setIdRol(2);

        usuario.Direccion = new ArrayList<>();
        Direccion direccion = new Direccion();
        direccion.setCalle("Reforma");
        direccion.setNumInterior("5");
        direccion.setNumExterior("10");
        direccion.Colonia = new Colonia();
        direccion.Colonia.setIdColonia(1);
       
        usuario.Direccion.add(direccion);
       
        Result result = usuarioDAOImplementation.Add(usuario);
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMenssage);
    }
    
    @Test
    public void DeleteGetId() {
        Result result = usuarioJPADAOImplementation.Delete(282);
        assertNull(result.object, "El valor obtenido es null");
        assertTrue(result.correct, "El metodo Delete no fue exitoso");
    }
    
    
    @Test
    public void BajaLogica() {
        Result result = usuarioJPADAOImplementation.BajaLogica(324);
        assertNull(result.object, "El valor obtenido es null");
        assertTrue(result.correct, "El metodo Delete no fue exitoso");
    }
    
}

