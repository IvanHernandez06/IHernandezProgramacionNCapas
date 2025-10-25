package com.digis.IHernandezProgramacionNCapas;

import com.digis.IHernandezProgramacionNCapas.DAO.RolDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitRol {
    
    @Autowired
    private RolDAOImplementation rolDAOImplementatio;
    
    @Test
    public void GetRol() {
        Result result = rolDAOImplementatio.GetAll();
        assertTrue(result.correct, "El metodo no fue exitoso");
    }
}
