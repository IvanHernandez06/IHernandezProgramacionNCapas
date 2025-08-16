package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.ML.Estado;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOImplementation implements IEstadoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll(int IdPais) {
        Result result = new Result();
        try {
            jdbcTemplate.execute
            ("{CALL GetIdEstado(?,?)}",
                    (CallableStatementCallback<Boolean>) 
                            callableStatement->{
            
                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(2, IdPais);

                callableStatement.execute();
                
                ResultSet resultSet= (ResultSet) callableStatement.getObject(1);
                
                result.objects=new ArrayList<>();
                
                while(resultSet.next()){
                    
                    Estado estado = new Estado();
                    estado.setIdEstado(resultSet.getInt("IdEstado"));
                    estado.setNombre(resultSet.getString("Nombre"));
                    
                    result.objects.add(estado);
                }
                
                result.correct = true;  
            return true;
            });
            

        } catch (Exception e) {
            result.correct=false;
            result.errorMenssage=e.getLocalizedMessage();
            result.e=e;
        }

        return result;

    }

}
