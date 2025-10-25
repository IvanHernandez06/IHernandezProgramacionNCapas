
package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.JPA.UsuariosJPA;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRepositoryUsuario extends JpaRepository<UsuariosJPA, Integer> {
    UsuariosJPA findByUsername(String UserName);
}
