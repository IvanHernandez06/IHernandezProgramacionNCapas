package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.ML.Colonia;
import com.digis.IHernandezProgramacionNCapas.ML.Direccion;
import com.digis.IHernandezProgramacionNCapas.ML.Estado;
import com.digis.IHernandezProgramacionNCapas.ML.Municipio;
import com.digis.IHernandezProgramacionNCapas.ML.Pais;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DireccionDAOImplementation implements IDireccionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplace;

    @Override
    public Result GetId(int idDireccion) {

        Result result = new Result();
        try {
            jdbcTemplace.execute("{CALL GetByIdDireccion(?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(2, idDireccion);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                if (resultSet.next()) {

                    Direccion direccion = new Direccion();
                    direccion.setIdDireccion(resultSet.getInt("idDireccion"));
                    direccion.setCalle(resultSet.getString("Calle"));
                    direccion.setNumInterior(resultSet.getString("NumInterior"));
                    direccion.setNumExterior(resultSet.getString("NumExterior"));

                    direccion.Colonia = new Colonia();
                    direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
                    direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                    direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                    direccion.Colonia.Municipio = new Municipio();
                    direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
                    direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                    direccion.Colonia.Municipio.Estado = new Estado();
                    direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
                    direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                    direccion.Colonia.Municipio.Estado.Pais = new Pais();
                    direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
                    direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                    result.object = direccion;

                }
                result.correct = true;
                return 1;
            });

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }

        return result;
    }
//
//    @Override
//    public Result DireccionGetByIdDireccion(int idDireccion) {
//        Result result = new Result();
//
//        try {
//            jdbcTemplace.execute("{CALL GetByIdDireccion(?, ?)}", (CallableStatementCallback<Integer>) callableStatement -> {
//
//                callableStatement.setInt(1, idDireccion);
//                callableStatement.registerOutParameter(2, java.sql.Types.REF_CURSOR);
//
//                callableStatement.execute();
//
//                ResultSet resultSet = (ResultSet) callableStatement.getObject(2); //Casteo
//
//                result.objects = new ArrayList<>(); //Se pone afuera del while porque si no, va a estar reescribiendo la lista
//
//                while (resultSet.next()) {
//
//                    Direccion direccion = new Direccion();
//
//                    direccion.setIdUsuario(resultSet.getInt("IdUsuario"));
//
//                    direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
//                    direccion.setCalle(resultSet.getString("Calle"));
//                    direccion.setNumInterior(resultSet.getString("NumeroInterior"));
//                    direccion.setNumExterior(resultSet.getString("NumeroExterior"));
//
//                    direccion.Colonia = new Colonia();
//                    direccion.Colonia.setIdColonia(resultSet.getInt("IdColonia"));
//                    direccion.Colonia.setNombre(resultSet.getString("NombreColonia")); //Alias
//                    direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));
//                    //direccion.Colonia.setIdMunicipio(resultSet.getInt("IdMunicipio"));
//
//                    direccion.Colonia.Municipio = new Municipio();
//
//                    direccion.Colonia.Municipio.setIdMunicipio(resultSet.getInt("IdMunicipio"));
//                    direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));
//
//                    direccion.Colonia.Municipio.Estado = new Estado();
//
//                    direccion.Colonia.Municipio.Estado.setIdEstado(resultSet.getInt("IdEstado"));
//                    direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));
//
//                    direccion.Colonia.Municipio.Estado.Pais = new Pais();
//
//                    direccion.Colonia.Municipio.Estado.Pais.setIdPais(resultSet.getInt("IdPais"));
//                    direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));
//
//                    result.object = direccion;
//                }
//
//                result.correct = true;
//
//                return 1;
//            });
//
//        } catch (Exception e) {
//            result.correct = false;
//            result.errorMenssage = e.getLocalizedMessage();
//            result.e = e;
//        }
//
//        return result;
//    }

    @Override
    public Result EditarDireccion(Usuarios usuario) {
        Result result = new Result();

        try {
            jdbcTemplace.execute("{CALL DireccionUpdate(?, ?, ?, ?, ?, ?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setString(1, usuario.Direccion.get(0).getCalle());
                callableStatement.setString(2, usuario.Direccion.get(0).getNumInterior());
                callableStatement.setString(3, usuario.Direccion.get(0).getNumExterior());
                callableStatement.setInt(4, usuario.Direccion.get(0).Colonia.getIdColonia());

                callableStatement.setInt(5, usuario.Direccion.get(0).getIdDireccion());
                callableStatement.setInt(6, usuario.getIdUsuario());

                callableStatement.execute();

                int status = callableStatement.getUpdateCount();

                if (status == -1) {
                    result.correct = true;
                }

                return 1;
            });

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }

        return result;
    }

    @Override
    public Result AgregarDireccion(Usuarios usuario) {
        Result result = new Result();

        try {
            result.correct = jdbcTemplace.execute("CALL AddDireccion(?, ?, ?, ?, ?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                callableStatement.setString(1, usuario.Direccion.get(0).getCalle());
                callableStatement.setString(2, usuario.Direccion.get(0).getNumInterior());
                callableStatement.setString(3, usuario.Direccion.get(0).getNumExterior());
                callableStatement.setInt(4, usuario.Direccion.get(0).Colonia.getIdColonia());
                callableStatement.setInt(5, usuario.getIdUsuario());

                int isCorrect = callableStatement.executeUpdate();

                if (isCorrect == -1) {
                    return true;
                }

                return true;

            });

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }

        return result;
    }

    @Override
    public Result EliminarDireccion(int IdDireccion) {
        Result result = new Result();
        try {
            result.correct = jdbcTemplace.execute("CALL DeleteDireccion(?)", (CallableStatementCallback<Boolean>) callableStatement -> {

                //callableStatement.setInt(1, usuario.Direcciones.get(0).getIdDireccion());
                callableStatement.setInt(1, IdDireccion);
                callableStatement.executeUpdate();

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
