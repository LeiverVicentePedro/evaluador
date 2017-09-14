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
import org.grupogvc.modelo.Pregunta;

/**
 *
 * @author Jerusalen
 */
public class PreguntaDAO extends Conexion{
    public void registrarPregunta(Pregunta preguntaregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO pregunta (pregunta,idcategoria,estatus) values(?,?,?)");
          
             consulta.setString(1, preguntaregistra.getPregunta());
            consulta.setInt(2,preguntaregistra.getCategoria().getIdCategoria());
            consulta.setBoolean(3, true);
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Pregunta DAO -->Registrar Pregunta"+"/n");
        throw e;
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
     
     public List<Pregunta> listarPregunta() throws Exception{//
     List<Pregunta> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT * FROM pregunta");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Pregunta pregunta=new Pregunta();
             pregunta.setIdpregunta(resultadoset.getInt("idpregunta"));
             pregunta.setPregunta(resultadoset.getString("pregunta"));
             pregunta.setCategoria(new CategoriaDAO().buscarIdCategoria((resultadoset.getInt("idcategoria"))));
             pregunta.setEstatus(resultadoset.getBoolean("estatus"));
             
             lista.add(pregunta);
         }
     }
     catch(Exception e){
          System.out.println("error en Pregunta DAO -->Listar Pregunta"+"/n");
          throw e;
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     public Pregunta elegirDatoPregunta(Pregunta pregunta) throws Exception{
        Pregunta preguntados=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM pregunta WHERE idpregunta=?");
            consulta.setInt(1, pregunta.getIdpregunta());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              preguntados= new Pregunta();
              preguntados.setIdpregunta(resultadoset.getInt("idpregunta"));
              preguntados.setPregunta(resultadoset.getString("pregunta"));
              preguntados.setCategoria(new CategoriaDAO().buscarIdCategoria((resultadoset.getInt("idcategoria"))));
              preguntados.setEstatus(resultadoset.getBoolean("estatus"));
            }
        }
        catch(Exception e){
            System.out.println("error en Pregunta DAO -->Elegir Pregunta"+"\n");
            throw e;
        }
        finally{
           this.Cerrar();
        }
        return preguntados;
    }
     
     public void modificarPregunta (Pregunta preguntamodificar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE pregunta SET pregunta=?,idcategoria=?,estatus=? WHERE idpregunta=?");
            consulta.setString(1, preguntamodificar.getPregunta());
            consulta.setInt(2,preguntamodificar.getCategoria().getIdCategoria());
            consulta.setBoolean(3,preguntamodificar.getEstatus());
            consulta.setInt(4,preguntamodificar.getIdpregunta());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Error en Modificar Pregunta DAO");
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }
     
     public Pregunta buscarIdPregunta(int idpregunta) throws Exception{
        Pregunta preguntabusca= new Pregunta();
        ResultSet resultadosetbusca;
      try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareCall("SELECT * FROM pregunta WHERE idpregunta=?");
            consulta.setInt(1,idpregunta);
            resultadosetbusca=consulta.executeQuery();
            if(resultadosetbusca.next()){
            preguntabusca.setIdpregunta(resultadosetbusca.getInt("idpregunta"));
            preguntabusca.setPregunta(resultadosetbusca.getString("pregunta"));
            preguntabusca.setCategoria(new CategoriaDAO().buscarIdCategoria((resultadosetbusca.getInt("idcategoria"))));
            preguntabusca.setEstatus(resultadosetbusca.getBoolean("estatus"));
            }
            resultadosetbusca.close();
            
        }
        catch(Exception e){
            System.out.println("error en PreguntaDAO->buscarIdPregunta "+e);
           throw e; 
        }
        finally{
           this.Cerrar();
        }  
        return preguntabusca;
    }
     
      public Pregunta buscarIdPreguntaParaRespuesta(String pregunta) throws Exception{
        Pregunta preguntabusca= new Pregunta();
        ResultSet resultadosetbusca;
      try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareCall("SELECT * FROM pregunta WHERE pregunta=?");
            consulta.setString(1,pregunta);
            resultadosetbusca=consulta.executeQuery();
            if(resultadosetbusca.next()){
            preguntabusca.setIdpregunta(resultadosetbusca.getInt("idpregunta"));
            preguntabusca.setPregunta(resultadosetbusca.getString("pregunta"));
            preguntabusca.setCategoria(new CategoriaDAO().buscarIdCategoria((resultadosetbusca.getInt("idcategoria"))));
            preguntabusca.setEstatus(resultadosetbusca.getBoolean("estatus"));
            
            }
            resultadosetbusca.close();
            
        }
        catch(Exception e){
            System.out.println("error en PreguntaDAO->buscarIdPreguntaParaRespuesta "+e);
           throw e; 
        }
        finally{
           this.Cerrar();
        }  
        return preguntabusca;
    }
      public void eliminarPregunta (Pregunta preguntaeliminar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("DELETE FROM pregunta WHERE idpregunta=?");
            consulta.setInt(1,preguntaeliminar.getIdpregunta());
            consulta.executeUpdate();
        }
        catch(Exception e){
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }   
} 