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
import org.grupogvc.modelo.Referencia;

/**
 *
 * @author leiver
 */
public class ReferenciaDAO extends Conexion{
    
    //metodo que realiza la insercion a la base de datos de el objeto con la referencia que se va a subir.
    public void insertarReferencia(Referencia referencia) throws Exception{
        try{
            this.Conectar();
           PreparedStatement insertar = this.getConexion().prepareStatement("INSERT INTO referencia(descripcion,referencia,idcatalogo) VALUES(?,?,?)");
           insertar.setString(1, referencia.getDescripcion());
           insertar.setString(2, referencia.getReferencia());
           insertar.setInt(3, referencia.getIdCategoria().getIdCategoria());
           insertar.executeUpdate();
           
        }catch(Exception ex){
            System.out.println("Error en ReferenciaDAO -> insertarReferencia: "+ex);
        }finally{
            this.Cerrar();
        }
    }
    //metodo que regresa una lista con los objetos de las referencias encontradas
    public List<Referencia> listarReferencia() throws Exception{
        List<Referencia> listaCategoria= new ArrayList(); 
        try{
           this.Conectar();
           PreparedStatement consultar = this.getConexion().prepareStatement("SELECT * FROM referencia;");
           ResultSet resultado = consultar.executeQuery();
           while(resultado.next()){
               Referencia referencia = new Referencia();
               referencia.setIdReferencia(resultado.getInt("idreferencia"));
               referencia.setDescripcion(resultado.getString("descripcion"));
               referencia.setReferencia(resultado.getString("referencia"));
               referencia.setIdCategoria(new CategoriaDAO().buscarIdCategoria(resultado.getInt("idcatalogo")));
               listaCategoria.add(referencia);
           }
        }catch(Exception ex){
            System.out.println("Error en ReferenciaDAO -> listarReferencia: "+ex);
        }finally{
            this.Cerrar();
        }
        return listaCategoria;
    }
    
    public void eliminarReferencia(Referencia referencia) throws Exception{
        try{
            this.Conectar();
            PreparedStatement elimina = this.getConexion().prepareStatement("DELETE FROM referencia WHERE idreferencia=?");
            elimina.setInt(1, referencia.getIdReferencia());
            elimina.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en ReferenciaDAO -> eliminarReferencia: "+ex);
        }finally{
            this.Cerrar();
        }
    }
}
