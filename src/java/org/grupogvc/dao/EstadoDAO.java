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
import org.grupogvc.modelo.Estado;

/**
 *
 * @author Jerusalen
 */
public class EstadoDAO extends Conexion{
    
     public void registrarEstado(Estado estadoregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO estado (nombre,estatus) values(?,?)");
          
             consulta.setString(1, estadoregistra.getNombre());
            consulta.setBoolean(2,estadoregistra.getEstatus());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Estado DAO -->Registrar Estado"+"/n");
        throw e;
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
     
     public List<Estado> listarEstado() throws Exception{//uso unico para la vista Area
     List<Estado> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT idestado, nombre, estatus FROM estado");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Estado estado=new Estado();
             estado.setIdestado(resultadoset.getInt("idestado"));
             estado.setNombre(resultadoset.getString("nombre"));
             estado.setEstatus(resultadoset.getBoolean("estatus"));
             
             lista.add(estado);
         }
     }
     catch(Exception e){
          System.out.println("error en Estado DAO -->Listar Estado"+"/n");
          throw e;
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     public Estado elegirDatoEstado(Estado estado) throws Exception{
        Estado estadodos=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT idestado, nombre,estatus FROM estado WHERE idestado=?");
            consulta.setInt(1, estado.getIdestado());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              estadodos= new Estado();
              estadodos.setIdestado(resultadoset.getInt("idestado"));
              estadodos.setNombre(resultadoset.getString("nombre"));
              estadodos.setEstatus(resultadoset.getBoolean("estatus"));
            }
        }
        catch(Exception e){
            System.out.println("error en Estado DAO -->Elegir Estado"+"/n");
            throw e;
        }
        finally{
           this.Cerrar();
        }
        return estadodos;
    }
     
     public void modificarEstado (Estado estadomodificar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE estado SET nombre=?, estatus=? WHERE idestado=?");
            consulta.setString(1, estadomodificar.getNombre());
            consulta.setBoolean(2,estadomodificar.getEstatus());
            consulta.setInt(3,estadomodificar.getIdestado());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Error en Modificar Estado DAO");
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }  
     
    public Estado buscarIdEstado(int idestado) throws Exception{
        Estado estadobusca= new Estado();
        ResultSet resultadosetbusca;
      try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareCall("SELECT * FROM estado WHERE idestado=?");
            consulta.setInt(1,idestado);
            resultadosetbusca=consulta.executeQuery();
            if(resultadosetbusca.next()){
            estadobusca.setIdestado(resultadosetbusca.getInt("idestado"));
            estadobusca.setNombre(resultadosetbusca.getString("nombre"));
            estadobusca.setEstatus(resultadosetbusca.getBoolean("estatus"));
            }
            resultadosetbusca.close();
            
        }
        catch(Exception e){
            System.out.println("error en EstadoDAO->buscarIdEstado "+e);
           throw e; 
        }
        finally{
           this.Cerrar();
        }  
        return estadobusca;
    }
    
}
