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
import org.grupogvc.modelo.Evaluacion;

/**
 *
 * @author leiver
 */
public class EvaluacionDAO extends Conexion{
    
    public void agregarEvaluacion(Evaluacion evaluacion) throws Exception{
        try{
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("INSERT INTO evaluacion(idevaluacion,inicio,fin,horaIni,horaFin,num_preguntas,intentos) VALUES(?,?,?,?,?,?,?)");
            inserta.setInt(1,evaluacion.getIdevaluacion().getIdCategoria());
            inserta.setString(2,evaluacion.getInicio());
            inserta.setString(3, evaluacion.getFin());
            inserta.setString(4, evaluacion.getHoraIni());
            inserta.setString(5,evaluacion.getHoraFin());
            inserta.setInt(6,evaluacion.getNum_preguntas());
            inserta.setInt(7,evaluacion.getIntentos());
           
            inserta.executeUpdate();
            
        }catch(Exception ex){
            System.out.println("Error en EvaluacionDAO -> agregarEvaluacion: "+ex);
        }finally{
            this.Cerrar();
        }
    }
    
    public void actualizarEvaluacion(Evaluacion evaluacion) throws Exception{
        try{
            this.Conectar();
            PreparedStatement inserta = this.getConexion().prepareStatement("UPDATE evaluacion SET inicio=?,fin=?,horaIni=?,horaFin=?,num_preguntas=?,intentos=? where idevaluacion=?");
            inserta.setString(1,evaluacion.getInicio());
            inserta.setString(2, evaluacion.getFin());
            inserta.setString(3, evaluacion.getHoraIni());
            inserta.setString(4,evaluacion.getHoraFin());
            inserta.setInt(5,evaluacion.getNum_preguntas());
            inserta.setInt(6,evaluacion.getIntentos());
            
            inserta.setInt(7,evaluacion.getIdevaluacion().getIdCategoria());
            inserta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en EvaluacionDAO -> actualizarEvaluacion: "+ex);
        }finally{
            this.Cerrar();
        }
    }
    
    public Evaluacion buscaEvaluacionPorId(int idEvaluacion) throws Exception{
        Evaluacion evaluacion = new Evaluacion();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT *  FROM evaluacion WHERE idevaluacion=?");
            consulta.setInt(1, idEvaluacion);
            ResultSet resultado = consulta.executeQuery();
           
            if(resultado.next()==true){
               evaluacion.setIdevaluacion(new CategoriaDAO().buscarIdCategoria(resultado.getInt("idevaluacion")));
               evaluacion.setInicio(resultado.getString("inicio"));
               evaluacion.setFin(resultado.getString("fin"));
               evaluacion.setHoraIni(resultado.getString("horaIni"));
               evaluacion.setHoraFin(resultado.getString("horaFin"));
               evaluacion.setNum_preguntas(resultado.getInt("num_preguntas"));
               evaluacion.setIntentos(resultado.getInt("intentos"));
               
            }
  
        }catch(Exception ex){
          System.out.println("Error en EvaluacionDAO -> buscaEvaluacionPorId: "+ex);
        }finally{
            this.Cerrar();
        }
        return evaluacion;
    }
    
    
    
    public List listarEvaluacion() throws Exception{
        List<Evaluacion> listaEvaluacion = new ArrayList<Evaluacion>();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT * FROM evaluacion");
            ResultSet resultado = consulta.executeQuery();
            
             while(resultado.next()){
                 Evaluacion evaluacion = new Evaluacion();
               evaluacion.setIdevaluacion(new CategoriaDAO().buscarIdCategoria(resultado.getInt("idevaluacion")));
               evaluacion.setInicio(resultado.getString("inicio"));
               evaluacion.setFin(resultado.getString("fin"));
               evaluacion.setHoraIni(resultado.getString("horaIni"));
               evaluacion.setHoraFin(resultado.getString("horaFin"));
               evaluacion.setNum_preguntas(resultado.getInt("num_preguntas"));
               evaluacion.setIntentos(resultado.getInt("intentos"));
               
               listaEvaluacion.add(evaluacion);
            }
        }catch(Exception ex){
            System.out.println("Error en EvaluacionDAO -> listarEvaluacion: "+ex);
        }finally{
            this.Cerrar();
        }
        return listaEvaluacion;
    }
    
}
