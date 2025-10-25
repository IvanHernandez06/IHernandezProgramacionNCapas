
package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.ML.Result;
import com.digis.IHernandezProgramacionNCapas.ML.Usuarios;


public interface IUsuarioJPADAO {
    
    Result GetAll();//
    
    Result Add(com.digis.IHernandezProgramacionNCapas.ML.Usuarios usuarioML);//
    
    Result Delete(int IdUsuario);//
    
    Result GetDetail(int idUsuario);//
    
    Result Update(Usuarios usuarioML);

    Result BajaLogica(int IdUsuario);
}
