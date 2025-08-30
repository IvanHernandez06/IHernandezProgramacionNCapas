
package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;

public interface IDireccionDAO {
    Result GetId(int idDireccions);
    
    Result DireccionGetByIdDireccion(int idDireccion);
    
    Result AgregarDireccion(Usuarios usuario);
    Result EditarDireccion(Usuarios usuario);
    Result EliminarDireccion(int IdDireccion);
}
