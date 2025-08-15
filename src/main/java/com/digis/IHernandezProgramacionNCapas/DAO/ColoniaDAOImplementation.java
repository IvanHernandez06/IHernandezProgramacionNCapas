package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.ML.Colonia;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ColoniaDAOImplementation implements IColoniaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplace;

    @Override
    public Result GetAll(int IdMunicipio) {
        Result result = new Result();
        try {
            jdbcTemplace.execute("{CALL GetIdColonia(?,?)}",
                    (CallableStatementCallback<Boolean>) callableStatement -> {

                        callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                        callableStatement.setInt(2, IdMunicipio);

                        callableStatement.execute();

                        ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                        result.objects = new ArrayList<>();

                        while (resultSet.next()) {

                            Colonia colonia = new Colonia();
                            colonia.setIdColonia(resultSet.getInt("IdColonia"));
                            colonia.setNombre(resultSet.getString("Nombre"));

                            result.objects.add(colonia);
                        }

                        result.correct = true;
                        return true;
                    });

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }

        return result;
    }

}
