package com.digis.IHernandezProgramacionNCapas;

import com.digis.IHernandezProgramacionNCapas.DAO.DireccionDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.ML.Colonia;
import com.digis.IHernandezProgramacionNCapas.ML.Direccion;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitDireccion {

    @Autowired
    private DireccionDAOImplementation direccionDAOImplementation;

    @Test
    public void GetIdDireccion() {
        int idDireccion = 4;
        Result result = direccionDAOImplementation.GetId(idDireccion);
        assertTrue(result.correct, "El metodo GetId no fue exitoso");
        assertNotNull(result.object, "El valor obtenido es null");
    }

    @Test
    void testEditarDireccionSuccess() {
        Usuarios usuario = new Usuarios();
        usuario.setIdUsuario(324);
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(204);
        direccion.setCalle("Calle Actualizada");
        direccion.setNumInterior("15");
        direccion.setNumExterior("25");
        direccion.setColonia(new Colonia());
        direccion.getColonia().setIdColonia(2);
        usuario.setDireccion(Collections.singletonList(direccion));

        // Ejecutar el método EditarDireccion
        Result result = direccionDAOImplementation.EditarDireccion(usuario);

        // Verificar que la actualización fue exitosa
        assertTrue(result.correct);
        assertNull(result.errorMenssage);
    }

    @Test
    void AddDireccion() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Nogal");
        direccion.setNumInterior("5");
        direccion.setNumExterior("10");
        direccion.Colonia = new Colonia();
        direccion.Colonia.setIdColonia(1);
        Usuarios usuario = new Usuarios();
        usuario.setIdUsuario(324);
        usuario.setDireccion(new ArrayList<>());
        usuario.getDireccion().add(direccion);

        Result result = direccionDAOImplementation.AgregarDireccion(usuario);
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMenssage);
    }

    @Test
    public void DeleteIdDireccion() {
        Result result = direccionDAOImplementation.EliminarDireccion(207);
        assertNull(result.object, "El valor obtenido es null");
        assertTrue(result.correct, "El metodo Delete no fue exitoso");
    }

}
