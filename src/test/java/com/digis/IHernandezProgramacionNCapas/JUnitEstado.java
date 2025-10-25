/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis.IHernandezProgramacionNCapas;

import com.digis.IHernandezProgramacionNCapas.DAO.EstadoDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitEstado {
    
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    
    @Test
    public void GetAll(){
        Result result = estadoDAOImplementation.GetAll(1);
        assertTrue(result.correct, "El metodo no fue exitoso");
    }
}
