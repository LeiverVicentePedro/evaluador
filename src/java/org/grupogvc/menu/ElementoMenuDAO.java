/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.grupogvc.conexion.Conexion;

/**
 *
 * @author leiver
 */
public class ElementoMenuDAO extends Conexion{
    
    public ElementoMenu buscarElementoMenuPorId(int idElementoMenu) throws Exception{
        ElementoMenu elementoMenu = new ElementoMenu();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("Select * from elemento_menu where idelemento_menu=?");
            consulta.setInt(1,idElementoMenu);
            ResultSet resultado = consulta.executeQuery();
            if(resultado.next()== true){
                elementoMenu.setIdelemento_menu(resultado.getInt("idelemento_menu"));
                elementoMenu.setNombre(resultado.getString("nombre"));
                elementoMenu.setTipo_menu(resultado.getString("tipo_menu"));
                elementoMenu.setCodigo_submenu(resultado.getInt("codigo_submenu"));
                elementoMenu.setEstatus(resultado.getBoolean("estatus"));
                elementoMenu.setIcono(resultado.getString("icono"));
                elementoMenu.setVista(resultado.getString("vista"));
            }
        }catch(Exception ex){
            System.out.println("Error en ElementoMenuDAO -> buscarElementoMenuPorId: "+ex);
        }finally{
            this.Cerrar();
        }
        return elementoMenu;
    }
}
