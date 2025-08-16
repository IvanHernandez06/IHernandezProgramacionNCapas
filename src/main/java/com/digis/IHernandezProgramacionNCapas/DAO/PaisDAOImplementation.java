package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.ML.Pais;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaisDAOImplementation implements IPais {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            jdbcTemplate.execute("{Call PaisGetAll(?)}",
                    (CallableStatementCallback<Boolean>) callableStatement -> {
                
                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                        
                callableStatement.execute();
                
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);
                        
                result.objects = new ArrayList<>();
                
                while (resultSet.next()) {                    
                    
                    Pais pais = new Pais();
                    pais.setIdPais(resultSet.getInt("IdPais"));
                    pais.setNombre(resultSet.getString("Nombre"));
                    
                    
                      
                    
                    result.objects.add(pais);
                    
                }
                
                return true;
            });

        } catch (Exception e) {
            result.correct= true;
            result.errorMenssage = e.getLocalizedMessage();
            result.e=e;
        }

        return result;
    }
}
