/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis.IHernandezProgramacionNCapas.DAO;

import com.digis.IHernandezProgramacionNCapas.ML.Result;

/**
 *
 * @author digis
 */
public interface IDireccionDAO {
    Result GetId(int idDireccions);
    
    Result DireccionGetByIdDireccion(int idDireccion);
}
