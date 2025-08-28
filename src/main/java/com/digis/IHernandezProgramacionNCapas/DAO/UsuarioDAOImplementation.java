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
public class UsuarioDAOImplementation implements IUsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Result GetAll() {

        Result result = new Result();

        try {

            jdbcTemplate.execute("{CALL UsuarioDireccionGetAll(?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.execute();
                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                result.objects = new ArrayList<>();

                while (resultSet.next()) {

                    int idUsuario = resultSet.getInt("idUsuario");
                    if (!result.objects.isEmpty() && idUsuario == ((Usuarios) (result.objects.get(result.objects.size() - 1))).getIdUsuario()) {
                        Direccion direccion = new Direccion();
                        direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumInterior(resultSet.getString("NumInterior"));
                        direccion.setNumExterior(resultSet.getString("NumExterior"));

                        direccion.Colonia = new Colonia();
                        direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                        direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                        direccion.Colonia.Municipio = new Municipio();
                        direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                        direccion.Colonia.Municipio.Estado = new Estado();
                        direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                        direccion.Colonia.Municipio.Estado.Pais = new Pais();
                        direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                        ((Usuarios) (result.objects.get(result.objects.size() - 1))).Direccion.add(direccion);

                    } else {
                        Usuarios usuario = new Usuarios();

                        usuario.setImagen(resultSet.getString("Imagen"));
                        usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                        usuario.setUsername(resultSet.getString("Username"));
                        usuario.setNombre(resultSet.getString("NombreUsuario"));
                        usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                        usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                        usuario.setEmail(resultSet.getString("Email"));
                        usuario.setPassword(resultSet.getString("Password"));
//                        usuario.setFechaNacimiento(resultSet.getString("FechaNacimiento"));
                        usuario.setSexo(resultSet.getString("Sexo").charAt(0));
                        usuario.setTelefono(resultSet.getString("Telefono"));
                        usuario.setCelular(resultSet.getString("Celular"));
                        usuario.setCurp(resultSet.getString("Curp"));

                        int IdDireccion = 0;
                        if ((IdDireccion = resultSet.getInt("IdDireccion")) != 0) {

                            usuario.Direccion = new ArrayList<>();

                            Direccion direccion = new Direccion();
                            direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumInterior(resultSet.getString("NumInterior"));
                            direccion.setNumExterior(resultSet.getString("NumExterior"));

                            direccion.Colonia = new Colonia();
                            direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                            direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                            direccion.Colonia.Municipio = new Municipio();
                            direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                            direccion.Colonia.Municipio.Estado = new Estado();
                            direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                            direccion.Colonia.Municipio.Estado.Pais = new Pais();
                            direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                            usuario.Direccion.add(direccion);
                        }
                        result.objects.add(usuario);
                    }
                }

                result.correct = true;
                return 1;

            }
            );

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;
        }
        return result;
    }

    @Override
    public Result GetAll(Usuarios usuario) {

        Result result = new Result();

        try {

            jdbcTemplate.execute("{CALL UsuarioDireccionGetAllQuery(?,?,?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.setString(1, usuario.getNombre());
                callableStatement.setString(2, usuario.getApellidoPaterno());
                callableStatement.setString(3, usuario.getApellidoMaterno());
                callableStatement.registerOutParameter(4, java.sql.Types.REF_CURSOR);
                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(4);

                result.objects = new ArrayList<>();

                while (resultSet.next()) {

                    int idUsuario = resultSet.getInt("idUsuario");
                    if (!result.objects.isEmpty() && idUsuario == ((Usuarios) (result.objects.get(result.objects.size() - 1))).getIdUsuario()) {
                        Direccion direccion = new Direccion();
                        direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                        direccion.setCalle(resultSet.getString("Calle"));
                        direccion.setNumInterior(resultSet.getString("NumInterior"));
                        direccion.setNumExterior(resultSet.getString("NumExterior"));

                        direccion.Colonia = new Colonia();
                        direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                        direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                        direccion.Colonia.Municipio = new Municipio();
                        direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                        direccion.Colonia.Municipio.Estado = new Estado();
                        direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                        direccion.Colonia.Municipio.Estado.Pais = new Pais();
                        direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                        ((Usuarios) (result.objects.get(result.objects.size() - 1))).Direccion.add(direccion);

                    } else {
                        Usuarios usuarioBD = new Usuarios();

                        usuarioBD.setImagen(resultSet.getString("Imagen"));
                        usuarioBD.setIdUsuario(resultSet.getInt("IdUsuario"));
                        usuarioBD.setUsername(resultSet.getString("Username"));
                        usuarioBD.setNombre(resultSet.getString("NombreUsuario"));
                        usuarioBD.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                        usuarioBD.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                        usuarioBD.setEmail(resultSet.getString("Email"));
                        usuarioBD.setPassword(resultSet.getString("Password"));
                        usuarioBD.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                        usuarioBD.setSexo(resultSet.getString("Sexo").charAt(0));
                        usuarioBD.setTelefono(resultSet.getString("Telefono"));
                        usuarioBD.setCelular(resultSet.getString("Celular"));
                        usuarioBD.setCurp(resultSet.getString("Curp"));

                        int IdDireccion = 0;
                        if ((IdDireccion = resultSet.getInt("IdDireccion")) != 0) {

                            usuarioBD.Direccion = new ArrayList<>();

                            Direccion direccion = new Direccion();
                            direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumInterior(resultSet.getString("NumInterior"));
                            direccion.setNumExterior(resultSet.getString("NumExterior"));

                            direccion.Colonia = new Colonia();
                            direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                            direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                            direccion.Colonia.Municipio = new Municipio();
                            direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                            direccion.Colonia.Municipio.Estado = new Estado();
                            direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                            direccion.Colonia.Municipio.Estado.Pais = new Pais();
                            direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                            usuarioBD.Direccion.add(direccion);
                        }
                        result.objects.add(usuarioBD);
                    }
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

    @Override
    public Result GetDatail(int idUsuario) {
        Result result = new Result();
        try {

            jdbcTemplate.execute("{CALL DireccionGetByIdUsuarios(?,?)}", (CallableStatementCallback<Integer>) callableStatement -> {

                callableStatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                callableStatement.setInt(2, idUsuario);

                callableStatement.execute();

                ResultSet resultSet = (ResultSet) callableStatement.getObject(1);

                if (resultSet.next()) {
                    Usuarios usuario = new Usuarios();

                    usuario.setImagen(resultSet.getString("Imagen"));

                    usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                    usuario.setUsername(resultSet.getString("Username"));
                    usuario.setNombre(resultSet.getString("NombreUsuario"));
                    usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                    usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                    usuario.setEmail(resultSet.getString("Email"));
                    usuario.setPassword(resultSet.getString("Password"));
                    usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                    usuario.setSexo(resultSet.getString("Sexo").charAt(0));
                    usuario.setTelefono(resultSet.getString("Telefono"));
                    usuario.setCelular(resultSet.getString("Celular"));
                    usuario.setCurp(resultSet.getString("Curp"));

                    int IdDireccion = 0;
                    if ((IdDireccion = resultSet.getInt("IdDireccion")) != 0) {

                        usuario.Direccion = new ArrayList<>();
                        do {
                            Direccion direccion = new Direccion();
                            direccion.setIdDireccion(resultSet.getInt("IdDireccion"));
                            direccion.setCalle(resultSet.getString("Calle"));
                            direccion.setNumInterior(resultSet.getString("NumInterior"));
                            direccion.setNumExterior(resultSet.getString("NumExterior"));

                            direccion.Colonia = new Colonia();
                            direccion.Colonia.setNombre(resultSet.getString("NombreColonia"));
                            direccion.Colonia.setCodigoPostal(resultSet.getString("CodigoPostal"));

                            direccion.Colonia.Municipio = new Municipio();
                            direccion.Colonia.Municipio.setNombre(resultSet.getString("NombreMunicipio"));

                            direccion.Colonia.Municipio.Estado = new Estado();
                            direccion.Colonia.Municipio.Estado.setNombre(resultSet.getString("NombreEstado"));

                            direccion.Colonia.Municipio.Estado.Pais = new Pais();
                            direccion.Colonia.Municipio.Estado.Pais.setNombre(resultSet.getString("NombrePais"));

                            usuario.Direccion.add(direccion);
                        } while (resultSet.next());
                    }
                    result.object = usuario;
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

    //corregir Fecha
    @Override
    public Result Add(Usuarios usuario) {
        Result result = new Result();

        try {
            result.correct = jdbcTemplate.execute("CALL usuariodireccionadd (?,?,?,?,?,?,?,to_date(?,'YYYY/MM/DD'),?,?,?,?,?,?,?,?,?)",
                    (CallableStatementCallback<Boolean>) callablestatement -> {

                        callablestatement.setString(1, usuario.getImagen());
                        callablestatement.setString(2, usuario.getUsername());
                        callablestatement.setString(3, usuario.getNombre());
                        callablestatement.setString(4, usuario.getApellidoPaterno());
                        callablestatement.setString(5, usuario.getApellidoMaterno());
                        callablestatement.setString(6, usuario.getEmail());
                        callablestatement.setString(7, usuario.getPassword());

//                        callablestatement.setString(8, usuario.getFechaNacimiento());
                        callablestatement.setString(9, String.valueOf(usuario.getSexo()));
                        callablestatement.setString(10, usuario.getTelefono());
                        callablestatement.setString(11, usuario.getCelular());
                        callablestatement.setString(12, usuario.getCurp());
                        callablestatement.setInt(13, usuario.RolML.getIdRol());

                        callablestatement.setString(14, usuario.Direccion.get(0).getCalle());
                        callablestatement.setString(15, usuario.Direccion.get(0).getNumInterior());
                        callablestatement.setString(16, usuario.Direccion.get(0).getNumExterior());
                        callablestatement.setInt(17, usuario.Direccion.get(0).Colonia.getIdColonia());

                        int isCorrect = callablestatement.executeUpdate();

                        if (isCorrect == -1) {

                            return true;
                        }

                        return false;
                    });

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;

        }

        return result;

    }

    @Override
    public Result GetId(int idUsuario) {
        Result result = new Result();
        try {
            result.correct = jdbcTemplate.execute("CALL UsuariosGetId (?,?)",
                    (CallableStatementCallback<Boolean>) callablestatement -> {
                        callablestatement.registerOutParameter(1, java.sql.Types.REF_CURSOR);
                        callablestatement.setInt(2, idUsuario);
                        callablestatement.execute();

                        ResultSet resultSet = (ResultSet) callablestatement.getObject(1);

                        if (resultSet.next()) {
                            Usuarios usuario = new Usuarios();

                            usuario.setIdUsuario(resultSet.getInt("IdUsuario"));
                            usuario.setUsername(resultSet.getString("Username"));
                            usuario.setNombre(resultSet.getString("Nombre"));
                            usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                            usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                            usuario.setEmail(resultSet.getString("Email"));
                            usuario.setPassword(resultSet.getString("Password"));
                            usuario.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
                            usuario.setSexo(resultSet.getString("Sexo").charAt(0));
                            usuario.setTelefono(resultSet.getString("Telefono"));
                            usuario.setCelular(resultSet.getString("Celular"));
                            usuario.setCurp(resultSet.getString("Curp"));

                           
                            
                            result.object = usuario;
                            result.correct = true;

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

    //corregir Fecha
    public Result Update(Usuarios usuario) {
        Result result = new Result();
        try {
            result.correct = jdbcTemplate.execute("CALL UpdateUsuarios (?,?, ?, ?,?, ? ,to_date(?,'DD/MM/YYYY'), ?, ?, ?, ?, ? , ?)",
                    (CallableStatementCallback<Boolean>) callablestatement -> {

                        callablestatement.setString(1, usuario.getUsername());
                        callablestatement.setString(2, usuario.getNombre());
                        callablestatement.setString(3, usuario.getApellidoPaterno());
                        callablestatement.setString(4, usuario.getApellidoMaterno());
                        callablestatement.setString(5, usuario.getEmail());
                        callablestatement.setString(6, usuario.getPassword());
//                        callablestatement.setString(7, usuario.getFechaNacimiento());
                        callablestatement.setString(8, String.valueOf(usuario.getSexo()));
                        callablestatement.setString(9, usuario.getTelefono());
                        callablestatement.setString(10, usuario.getCelular());
                        callablestatement.setString(11, usuario.getCurp());

                        callablestatement.setInt(12, usuario.RolML.getIdRol());
                        callablestatement.setInt(13, usuario.getIdUsuario());

                        return false;
                    });

        } catch (Exception e) {
            result.correct = false;
            result.errorMenssage = e.getLocalizedMessage();
            result.e = e;

        }

        return result;
    }

}
