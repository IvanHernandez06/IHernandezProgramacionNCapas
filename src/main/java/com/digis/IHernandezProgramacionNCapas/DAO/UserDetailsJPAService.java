/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.JPA.UsuariosJPA;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsJPAService implements UserDetailsService {

    private final IRepositoryUsuario iRepositoryUsuario;

    public UserDetailsJPAService(IRepositoryUsuario iRepositoryUsuario) {
        this.iRepositoryUsuario = iRepositoryUsuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuariosJPA usuarios = iRepositoryUsuario.findByUsername(username);

        return User.withUsername(usuarios.getUsername())
                .password(usuarios.getPassword())
                .roles(usuarios.RolML.getNombre())
                .accountLocked(!(usuarios.getEstatus()== 1))
                .disabled(!(usuarios.getEstatus()== 1))
                .build();

    }
}
