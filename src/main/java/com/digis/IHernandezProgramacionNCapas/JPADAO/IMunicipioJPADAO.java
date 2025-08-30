
package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.ML.Result;


public interface IMunicipioJPADAO {
    
    Result GetAll();

    Result GetByIdEstado(int IdEstado);
    
}
