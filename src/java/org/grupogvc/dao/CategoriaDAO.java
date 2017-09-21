/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.grupogvc.conexion.Conexion;
import org.grupogvc.modelo.Categoria;

/**
 *
 * @author leiver
 */
public class CategoriaDAO extends Conexion{
    //metodo que inserta una categoria a la tabla categoria
    public void insertarCategoria(Categoria categoria) throws Exception{
        try{
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO categoria(tipo,estatus) VALUES(?,?)");
            inserta.setString(1, categoria.getTipo());
            inserta.setBoolean(2, categoria.getEstatus());
            inserta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en CategoriaDAO -> insertarCategoria "+ex);
        }finally{
            this.Cerrar();
        }
    }
    //meotodo que modifica cualquier valor de un registro en la tabla categoria
    public void modificarCategoria(Categoria categoria) throws Exception{
        try{
            this.Conectar();
            PreparedStatement modifica = this.getConexion().prepareStatement("UPDATE categoria SET tipo=?, estatus=? WHERE idcategoria=?");
            modifica.setString(1, categoria.getTipo());
            modifica.setBoolean(2, categoria.getEstatus());
            modifica.setInt(3, categoria.getIdCategoria());
            modifica.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en CategoriaDAO -> modificarCategoria "+ex);
        }finally{
            this.Cerrar();
        }
    }
    //lista de todas las categorias
    public List<Categoria> listarCategoria() throws Exception{
         List<Categoria> listaCategorias = new ArrayList();
        try{
            this.Conectar();
           PreparedStatement listar = this.getConexion().prepareStatement("SELECT * FROM categoria");
           ResultSet resultado = listar.executeQuery();
           while(resultado.next()){
               Categoria categoria = new Categoria();
               
               categoria.setIdCategoria(resultado.getInt("idcategoria"));
               categoria.setTipo(resultado.getString("tipo"));
               categoria.setEstatus(resultado.getBoolean("estatus"));
               
               listaCategorias.add(categoria);
           }
            
        }catch(Exception ex){
            
        }finally{
            this.Cerrar();
        }
        return listaCategorias;
    }
    
    public Categoria buscarIdCategoria(int idCategoria) throws Exception{
        Categoria categoria = new Categoria();
        ResultSet resultado;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM categoria WHERE idcategoria=?");
            consulta.setInt(1, idCategoria);
            resultado = consulta.executeQuery();
            if(resultado.next()){
                categoria.setIdCategoria(resultado.getInt("idcategoria"));
                categoria.setTipo(resultado.getString("tipo"));
                categoria.setEstatus(resultado.getBoolean("estatus"));
            }
             resultado.close();
        }catch(Exception ex){
            System.out.println("Error en CategoriaDAO -> buscarIdCategoria: "+ex);
        }finally{
            this.Cerrar();
        }
        return categoria;
    }
 
}
