
package com.digis.IHernandezProgramacionNCapas.JPADAO;
import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;
import com.digis.IHernandezProgramacionNCapas.ML.Direccion;
public interface IDireccionJPADAO {
    
    Result GetById(int idDireccion);
    
    Result update(Usuarios usuarioML);
    
    Result Add(Usuarios usuario);
    
    Result Update(Direccion direccion, int IdDireccion);
    
    Result GetOne(int IdDireccion);

    Result Delete(int IdDireccion);
}
