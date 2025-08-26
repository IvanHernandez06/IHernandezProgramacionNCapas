
package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.UsuariosML;

public interface IUsuarioDAO {
    
    Result GetAll();
    
    Result GetAll(UsuariosML usuario);
    
    
    
    Result GetDatail(int idUsuario);
    
    Result Add(UsuariosML usuario);
    
    
    
    Result GetId(int idUsuario);
    
    Result  Update(UsuariosML usuario);
    
   
    
    
}
