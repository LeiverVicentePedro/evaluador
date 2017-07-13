/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.dao;

import java.sql.PreparedStatement;
import org.grupogvc.conexion.Conexion;
import org.grupogvc.modelo.Resultado;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jerusalen
 */
public class ResultadoDAO extends Conexion{
    public void registrarResultado(Resultado resultadoregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO resultado (idpersonal,res_acer,res_inc,estatus,intentos,fecha,calificacion,idcategoria) values(?,?,?,?,?,?,?,?)");
          
             consulta.setInt(1, resultadoregistra.getPersona().getIdpersonal());
            consulta.setInt(2,resultadoregistra.getRes_acer());
            consulta.setInt(3,resultadoregistra.getRes_inc());
            consulta.setString(4,resultadoregistra.getEstatus());
            consulta.setString(5,resultadoregistra.getIntentos());
            consulta.setDate(6,(Date)resultadoregistra.getFecha());
            consulta.setDouble(7,resultadoregistra.getCalificacion());
            consulta.setInt(8,resultadoregistra.getCategoria().getIdCategoria());
            
           
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Resultado DAO -->Registrar Resultado Registro"+"/n");
        throw e;
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
     
     public List<Resultado> listarResultado() throws Exception{//uso unico para la vista Area
     List<Resultado> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT * FROM resultado");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Resultado resultado=new Resultado();
             resultado.setIdresultado(resultadoset.getInt("idresultado"));
             resultado.setPersona(new PersonalDAO().buscarIdPersona((resultadoset.getInt("idpersonal"))));
             resultado.setRes_acer(resultadoset.getInt("res_acer"));
             resultado.setRes_inc(resultadoset.getInt("res_inc"));
             resultado.setEstatus(resultadoset.getString("estatus"));
             resultado.setEstatus(resultadoset.getString("estatus"));
             resultado.setIntentos(resultadoset.getString("intentos"));
             resultado.setFecha(resultadoset.getDate("fecha"));
             resultado.setCalificacion(resultadoset.getDouble("calificacion"));
             resultado.setCategoria(new CategoriaDAO().buscarIdCategoria((resultadoset.getInt("idcategoria"))));
             lista.add(resultado);
         }
     }
     catch(Exception e){
          System.out.println("error en Resultado DAO -->Listar Resultado"+"/n");
          throw e;
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     public Resultado elegirDatoResultado(Resultado resultado) throws Exception{
        Resultado resultadodos=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM resultado WHERE idresultado=?");
            consulta.setInt(1, resultado.getIdresultado());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              resultadodos= new Resultado();
              resultadodos.setIdresultado(resultadoset.getInt("idresultado"));
             resultadodos.setPersona(new PersonalDAO().buscarIdPersona((resultadoset.getInt("idpersonal"))));
             resultadodos.setRes_acer(resultadoset.getInt("res_acer"));
             resultadodos.setRes_inc(resultadoset.getInt("res_inc"));
             resultadodos.setEstatus(resultadoset.getString("estatus"));
             resultadodos.setEstatus(resultadoset.getString("estatus"));
             resultadodos.setIntentos(resultadoset.getString("intentos"));
             resultadodos.setFecha(resultadoset.getDate("fecha"));
             resultadodos.setCalificacion(resultadoset.getDouble("calificacion"));
             resultadodos.setCategoria(new CategoriaDAO().buscarIdCategoria((resultadoset.getInt("idcategoria"))));
            }
        }
        catch(Exception e){
            System.out.println("error en Resultado DAO -->Elegir Resultado"+"/n");
            throw e;
        }
        finally{
           this.Cerrar();
        }
        return resultadodos;
    }
     
   
     
     public Resultado buscarIdresultado(int idresultadito) throws Exception{
        Resultado resultadobusca= new Resultado();
        ResultSet resultadosetbusca;
      try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareCall("SELECT * FROM resultado WHERE idresultado=?");
            consulta.setInt(1,idresultadito);
            resultadosetbusca=consulta.executeQuery();
            if(resultadosetbusca.next()){
             resultadobusca.setIdresultado(resultadosetbusca.getInt("idresultado"));
             resultadobusca.setPersona(new PersonalDAO().buscarIdPersona((resultadosetbusca.getInt("idpersonal"))));
             resultadobusca.setRes_acer(resultadosetbusca.getInt("res_acer"));
             resultadobusca.setRes_inc(resultadosetbusca.getInt("res_inc"));
             resultadobusca.setEstatus(resultadosetbusca.getString("estatus"));
             resultadobusca.setEstatus(resultadosetbusca.getString("estatus"));
             resultadobusca.setIntentos(resultadosetbusca.getString("intentos"));
             resultadobusca.setFecha(resultadosetbusca.getDate("fecha"));
             resultadobusca.setCalificacion(resultadosetbusca.getDouble("calificacion"));
             resultadobusca.setCategoria(new CategoriaDAO().buscarIdCategoria((resultadosetbusca.getInt("idcategoria"))));
            }
            resultadosetbusca.close();
            
        }
        catch(Exception e){
            System.out.println("error en Centro de TrabajoDAO->buscarIdCentro "+e);
           throw e; 
        }
        finally{
           this.Cerrar();
        }  
        return resultadobusca;
    }
     
    
}