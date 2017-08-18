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
import org.grupogvc.modelo.Pregunta;
import org.grupogvc.modelo.Respuesta;

/**
 *
 * @author Jerusalen
 */
public class RespuestaDAO extends Conexion{
    public void registrarRespuesta(Respuesta respuestaregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO respuesta (idrespuesta,correcto,incorrecto1,incorrecto2) values(?,?,?,?)");
          
            consulta.setInt(1,respuestaregistra.getRespuesta().getIdpregunta());
            consulta.setString(2, respuestaregistra.getCorrecto());
            consulta.setString(3, respuestaregistra.getIncorrecto1());
            consulta.setString(4, respuestaregistra.getIncorrecto2());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Respuesta DAO -->Registrar Respuesta"+"\n");
        throw e;
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
     
     public List<Respuesta> listarPregunta() throws Exception{//
     List<Respuesta> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT * FROM respuesta");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Respuesta respuesta=new Respuesta();
             respuesta.setRespuesta(new PreguntaDAO().buscarIdPregunta((resultadoset.getInt("idrespuesta"))));
             respuesta.setCorrecto(resultadoset.getString("correcto"));
             respuesta.setIncorrecto1(resultadoset.getString("incorrecto1"));
             respuesta.setIncorrecto2(resultadoset.getString("incorrecto2"));
            
             lista.add(respuesta);
         }
     }
     catch(Exception e){
          System.out.println("error en Respuesta DAO -->Listar Respuesta"+"/n");
          throw e;
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     public Respuesta elegirDatoRespuesta(Respuesta respuesta) throws Exception{
        Respuesta respuestados=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM respuesta WHERE idrespuesta=?");
            consulta.setInt(1, respuesta.getRespuesta().getIdpregunta());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              respuestados= new Respuesta();
              respuestados.setRespuesta(new PreguntaDAO().buscarIdPregunta((resultadoset.getInt("idpregunta"))));
              respuestados.setCorrecto(resultadoset.getString("correcto"));
              respuestados.setIncorrecto1(resultadoset.getString("incorrecto1"));
              respuestados.setIncorrecto2(resultadoset.getString("incorrecto2"));
              
            }
        }
        catch(Exception e){
            System.out.println("error en respuesta DAO -->Elegir Respuesta"+"/n");
            throw e;
        }
        finally{
           this.Cerrar();
        }
        return respuestados;
    }
     
     public void modificarRespuesta (Respuesta respuestamodificar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE respuesta SET idrespuesta=?,correcto=?,incorrecto1=?,incorrecto2=? WHERE idrespuesta=?");
            
            consulta.setString(1,respuestamodificar.getCorrecto());
            consulta.setString(2,respuestamodificar.getIncorrecto1());
            consulta.setString(3,respuestamodificar.getIncorrecto2());
            consulta.setInt(4, respuestamodificar.getRespuesta().getIdpregunta());
            
            
            consulta.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Error en Modificar Resultado DAO");
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }
     
     public Respuesta buscarIdRespuesta(int idrespuesta) throws Exception{
        Respuesta respuestabusca= new Respuesta();
        ResultSet resultadosetbusca;
      try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareCall("SELECT * FROM respuesta WHERE idrespuesta=?");
                consulta.setInt(1,idrespuesta);
            resultadosetbusca=consulta.executeQuery();
            if(resultadosetbusca.next()){
           respuestabusca.setRespuesta(new PreguntaDAO().buscarIdPregunta((resultadosetbusca.getInt("idpregunta"))));
              respuestabusca.setCorrecto(resultadosetbusca.getString("correcto"));
              respuestabusca.setIncorrecto1(resultadosetbusca.getString("incorrecto1"));
              respuestabusca.setIncorrecto2(resultadosetbusca.getString("incorrecto2")); }
            resultadosetbusca.close();
            
        }
        catch(Exception e){
            System.out.println("error en PreguntaDAO->buscarIdPregunta "+e);
           throw e; 
        }
        finally{
           this.Cerrar();
        }  
        return respuestabusca;
    }   
     ///esta es la que estoy usando actualmente para registrar pregunta y respuesta lo puse como prueba 
     public Respuesta buscarPregunta(Pregunta pregunta) throws Exception{
        Respuesta respuestabusca= new Respuesta();
        ResultSet resultadosetbusca;
      try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareCall("SELECT * FROM respuesta WHERE idrespuesta=?");
                consulta.setInt(1,pregunta.getIdpregunta());
            resultadosetbusca=consulta.executeQuery();
            if(resultadosetbusca.next()){
           respuestabusca.setRespuesta(new PreguntaDAO().buscarIdPregunta((resultadosetbusca.getInt("idpregunta"))));
              respuestabusca.setCorrecto(resultadosetbusca.getString("correcto"));
              respuestabusca.setIncorrecto1(resultadosetbusca.getString("incorrecto1"));
              respuestabusca.setIncorrecto2(resultadosetbusca.getString("incorrecto2")); }
            resultadosetbusca.close();
            
        }
        catch(Exception e){
            System.out.println("error en PreguntaDAO->buscarIdPregunta "+e);
           throw e; 
        }
        finally{
           this.Cerrar();
        }  
        return respuestabusca;
    }    
}
