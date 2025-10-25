
package com.digis.IHernandezProgramacionNCapas;

import com.digis.IHernandezProgramacionNCapas.DAO.PaisDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitPais {
    
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    
    @Test
    public void GetAll(){
        Result result = paisDAOImplementation.GetAll();
        assertTrue(result.correct, "El metodo no fue exitoso");
    }
}
