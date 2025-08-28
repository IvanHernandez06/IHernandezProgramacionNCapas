
package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;

public interface IUsuarioDAO {
    
    Result GetAll();
    
    Result GetAll(Usuarios usuario);
    
    
    
    Result GetDatail(int idUsuario);
    
    Result Add(Usuarios usuario);
    
    
    
    Result GetId(int idUsuario);
    
    Result  Update(Usuarios usuario);
    
   
    
    
}
