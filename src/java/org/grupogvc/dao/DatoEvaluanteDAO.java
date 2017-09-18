/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.grupogvc.conexion.Conexion;
import org.grupogvc.modelo.DatosEvaluante;

/**
 *
 * @author leiver
 */
public class DatoEvaluanteDAO extends Conexion{
    
    public DatosEvaluante buscaDato(int normaEvaluacion,int idEvaluante) throws Exception{
        DatosEvaluante datoEvaluante = new DatosEvaluante();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("Select * from dato_evaluante where norma=? and idevaluante=?");
            consulta.setInt(1, normaEvaluacion);
            consulta.setInt(2,idEvaluante);
            ResultSet resultado = consulta.executeQuery();
            if(resultado.next()==true){
                datoEvaluante.setId(resultado.getInt("id"));
                datoEvaluante.setIdevaluante(new PersonalDAO().buscarIdPersona(resultado.getInt("idevaluante")));
                datoEvaluante.setNorma(new CategoriaDAO().buscarIdCategoria(resultado.getInt("norma")));
                datoEvaluante.setIntento(resultado.getInt("intentos"));
                
            }
        }catch(Exception ex){
            System.out.println("Error en DatoEvaluante -> buscaDato: "+ex);
        }finally{
            this.Cerrar();
        }
        return datoEvaluante;
    }
    
    public void registrarDatoEvaluante(DatosEvaluante dato) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("INSERT INTO dato_evaluante(idevaluante,norma,intento) VALUES(?,?,?)");
            consulta.setInt(1,dato.getIdevaluante().getIdpersonal());
            consulta.setInt(2,dato.getNorma().getIdCategoria());
            consulta.setInt(3, dato.getIntento());
            consulta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en DatoEvaluanteDAO -> registrarDatoEvaluante: "+ex);
        }finally{
            this.Cerrar();
        }
    }
    
    public void actualizarDatoEvaluante(DatosEvaluante dato) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("UPDATE dato_evaluante SET idevaluante=?, norma=?, intento=? where id=?");
            consulta.setInt(1,dato.getIdevaluante().getIdpersonal());
            consulta.setInt(2,dato.getNorma().getIdCategoria());
            consulta.setInt(3, dato.getIntento());
            consulta.setInt(4, dato.getId());
            consulta.executeUpdate();
        }catch(Exception ex){
            System.out.println("Error en DatoEvaluanteDAO -> registrarDatoEvaluante: "+ex);
        }finally{
            this.Cerrar();
        }
    }
}
