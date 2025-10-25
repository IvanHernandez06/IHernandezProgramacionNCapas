package com.digis.IHernandezProgramacionNCapas;

import com.digis.IHernandezProgramacionNCapas.DAO.ColoniaDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitColonia {
    
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;
    
    @Test
    public void GetColonia(){
        Result result = coloniaDAOImplementation.GetAll(1);
        assertTrue(result.correct, "El metodo no fue exitoso");
    }
}
