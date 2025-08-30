
package com.digis.IHernandezProgramacionNCapas.JPADAO;

import com.digis.IHernandezProgramacionNCapas.ML.Result;


public interface IColoniaJPADAO {
    
    
    Result GetAll();

    Result GetByIdMunicipio(int IdMunicipio);
}
