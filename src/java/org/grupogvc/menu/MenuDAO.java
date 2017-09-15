/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.grupogvc.conexion.Conexion;

/**
 *
 * @author leiver
 */
public class MenuDAO extends Conexion{
    
    public List<Menu> listarMenuPorUsuario(int nivelUsuario) throws Exception{
        List<Menu> listaMenu = new ArrayList<>();
        ResultSet resultado;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("Select * from menu where nivel_usuario=?");
            consulta.setInt(1, nivelUsuario);
            resultado = consulta.executeQuery();
            
            while(resultado.next()){
                Menu menu = new Menu();
                menu.setIdmenu(resultado.getInt("idmenu"));
                menu.setNivel_usuaio(resultado.getInt("nivel_usuario"));
                menu.setIdelemento_menu(new ElementoMenuDAO().buscarElementoMenuPorId(resultado.getInt("idelemento_menu")));
                listaMenu.add(menu);
            }
        
        }catch(Exception ex){
            System.out.println("Error en MenuDAO -> listarMenuPorUsuario: "+ex);
        }finally{
            this.Cerrar();
        }
        return listaMenu;
    }
}
