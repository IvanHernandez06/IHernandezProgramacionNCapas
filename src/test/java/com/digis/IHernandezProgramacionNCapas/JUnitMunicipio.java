
package com.digis.IHernandezProgramacionNCapas;

import com.digis.IHernandezProgramacionNCapas.DAO.MunicipioDAOImplementation;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JUnitMunicipio {
    
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    
    @Test
    public void GetAll(){
        Result result = municipioDAOImplementation.GetAll(1);
        assertTrue(result.correct, "El metodo no fue exitoso");
    }
}
