
package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.ML.Result;


public interface IUsuarioJPADAO {
    
    Result GetAll();
    
    Result Add(com.digis.IHernandezProgramacionNCapas.ML.Usuarios usuarioML);
    
    Result Delete (int IdUsuario);
}
