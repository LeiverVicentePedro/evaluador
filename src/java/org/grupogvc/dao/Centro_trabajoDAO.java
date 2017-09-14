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
import org.grupogvc.modelo.Centro_trabajo;

/**
 *
 * @author Jerusalen
 */
public class Centro_trabajoDAO extends Conexion{
    public void registrarCentro_trabajo(Centro_trabajo centroregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO cent_trab (lugar,idestado,estatus) values(?,?,?)");
          
             consulta.setString(1, centroregistra.getLugar());
            consulta.setInt(2,centroregistra.getEstado().getIdestado());
            consulta.setBoolean(3,centroregistra.getEstatus());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en Centro d DAO -->Registrar EstadoCentro"+"/n");
        throw e;
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
     
     public List<Centro_trabajo> listarCentro() throws Exception{
     List<Centro_trabajo> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT * FROM cent_trab");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Centro_trabajo centro_trabajo=new Centro_trabajo();
             centro_trabajo.setIdcent_trab(resultadoset.getInt("idcent_trab"));
             centro_trabajo.setLugar(resultadoset.getString("lugar"));
             centro_trabajo.setEstado(new EstadoDAO().buscarIdEstado((resultadoset.getInt("idestado"))));
             centro_trabajo.setEstatus(resultadoset.getBoolean("estatus"));
             
             lista.add(centro_trabajo);
         }
     }
     catch(Exception e){
          System.out.println("error en Centro de trabajo DAO -->Listar Centro de trabajo"+"/n");
          throw e;
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     public Centro_trabajo elegirDatoCentro(Centro_trabajo centro) throws Exception{
        Centro_trabajo centrodos=null;
        ResultSet resultadoset;
        
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM cent_trab WHERE idcent_trab=?");
            consulta.setInt(1, centro.getIdcent_trab());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              centrodos= new Centro_trabajo();
              centrodos.setIdcent_trab(resultadoset.getInt("idcent_trab"));
              centrodos.setLugar(resultadoset.getString("lugar"));
              centrodos.setEstado(new EstadoDAO().buscarIdEstado((resultadoset.getInt("idestado"))));
              centrodos.setEstatus(resultadoset.getBoolean("estatus"));
            }
        }
        catch(Exception e){
            System.out.println("error en Centro de trabajo DAO -->Elegir Centro de trabajo"+"/n");
            throw e;
        }
        finally{
           this.Cerrar();
        }
        return centrodos;
    }
     
     public void modificarCentro (Centro_trabajo centromodificar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE cent_trab SET lugar=?,idestado=?,estatus=? WHERE idcent_trab=?");
            consulta.setString(1, centromodificar.getLugar());
            consulta.setInt(2,centromodificar.getEstado().getIdestado());
            consulta.setBoolean(3,centromodificar.getEstatus());
            consulta.setInt(4,centromodificar.getIdcent_trab());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Error en Modificar Centro de trabajo DAO");
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }
     
     public Centro_trabajo buscarIdCentro(int idcentro) throws Exception{
        Centro_trabajo centrobusca= new Centro_trabajo();
        ResultSet resultadosetbusca;
      try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareCall("SELECT * FROM cent_trab WHERE idcent_trab=?");
            consulta.setInt(1,idcentro);
            resultadosetbusca=consulta.executeQuery();
            if(resultadosetbusca.next()){
            centrobusca.setIdcent_trab(resultadosetbusca.getInt("idcent_trab"));
            centrobusca.setLugar(resultadosetbusca.getString("lugar"));
            centrobusca.setEstado(new EstadoDAO().buscarIdEstado((resultadosetbusca.getInt("idestado"))));
            centrobusca.setEstatus(resultadosetbusca.getBoolean("estatus"));
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
        return centrobusca;
    }
     
    
}
