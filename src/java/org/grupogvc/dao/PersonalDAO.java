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
import org.grupogvc.modelo.Personal;

/**
 *
 * @author Jerusalen
 */
public class PersonalDAO extends Conexion{
    public void registrarPersonal(Personal personaregistra) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("INSERT INTO personal (clave,nombre,apat,amat,puesto,idcent_trab,telefono,correoElectronico,estatus,nivel) values(?,?,?,?,?,?,?,?,?,?)");
          
             consulta.setString(1, personaregistra.getClave());
             consulta.setString(2, personaregistra.getNombre());
             consulta.setString(3, personaregistra.getApat());
             consulta.setString(4, personaregistra.getAmat());
             consulta.setString(5, personaregistra.getPuesto());
             consulta.setInt(6, personaregistra.getCentro().getIdcent_trab());
             consulta.setString(7, personaregistra.getTelefono());
             consulta.setString(8, personaregistra.getCorreoElectronico());
            consulta.setBoolean(9,personaregistra.getEstatus());
            consulta.setInt(10, personaregistra.getNivel());
           
            consulta.executeUpdate();
        }
        catch(Exception e){
        System.out.println("error en PersonalDAO -->Registrar Personal"+"/n");
        throw e;
       // System.out.println("error en DAO"); 
        }
     finally{
           this.Cerrar();
        }
    }
     
     public List<Personal> listarPersonal() throws Exception{//uso unico para la vista Area
     List<Personal> lista;
        ResultSet resultadoset;
     try{
         this.Conectar();
         PreparedStatement consulta=this.getConexion().prepareCall("SELECT * FROM personal");
         resultadoset= consulta.executeQuery();
         lista =new ArrayList();
         while(resultadoset.next()){
             Personal persona=new Personal();
             persona.setIdpersonal(resultadoset.getInt("idpersonal"));
             persona.setClave(resultadoset.getString("clave"));
             persona.setNombre(resultadoset.getString("nombre"));
             persona.setApat(resultadoset.getString("apat"));
             persona.setAmat(resultadoset.getString("amat"));
             persona.setPuesto(resultadoset.getString("puesto"));
             persona.setCentro(new Centro_trabajoDAO().buscarIdCentro((resultadoset.getInt("idcent_trab"))));
             persona.setTelefono(resultadoset.getString("telefono"));
             persona.setCorreoElectronico(resultadoset.getString("correoElectronico"));
             persona.setEstatus(resultadoset.getBoolean("estatus"));
             
             lista.add(persona);
         }
     }
     catch(Exception e){
          System.out.println("error en Personal DAO -->Listar Personal"+"/n");
          throw e;
     }
     finally{
         this.Cerrar();
     }
     return lista;
    }
     
     public Personal elegirDatoPersonal(Personal persona) throws Exception{
        Personal personados=null;
        ResultSet resultadoset;
        try{
            this.Conectar();
             PreparedStatement consulta= this.getConexion().prepareStatement("SELECT * FROM personal WHERE idpersonal=?");
            consulta.setInt(1, persona.getIdpersonal());
            resultadoset = consulta.executeQuery();
            while(resultadoset.next())
            {
              personados= new Personal();
              personados.setIdpersonal(resultadoset.getInt("idpersonal"));
             personados.setClave(resultadoset.getString("clave"));
             personados.setNombre(resultadoset.getString("nombre"));
             personados.setApat(resultadoset.getString("apat"));
             personados.setAmat(resultadoset.getString("amat"));
             personados.setPuesto(resultadoset.getString("puesto"));
             personados.setCentro(new Centro_trabajoDAO().buscarIdCentro((resultadoset.getInt("idcent_trab"))));
             personados.setTelefono(resultadoset.getString("telefono"));
             personados.setCorreoElectronico(resultadoset.getString("correoElectronico"));
             personados.setEstatus(resultadoset.getBoolean("estatus"));
            }
        }
        catch(Exception e){
            System.out.println("error en Personal DAO -->Elegir Personal"+"/n");
            throw e;
        }
        finally{
           this.Cerrar();
        }
        return personados;
    }
     
     public void modificarPersonal (Personal personamodificar) throws Exception{
        try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareStatement("UPDATE personal SET clave=?,nombre=?,apat=?,amat=?,puesto=?,idcent_trab=?,telefono=?,correoElectronico=?,estatus=? WHERE idpersonal=?");
             consulta.setString(1, personamodificar.getClave());
             consulta.setString(2, personamodificar.getNombre());
             consulta.setString(3, personamodificar.getApat());
             consulta.setString(4, personamodificar.getAmat());
             consulta.setString(5, personamodificar.getPuesto());
             consulta.setInt(6, personamodificar.getCentro().getIdcent_trab());
             consulta.setString(7, personamodificar.getTelefono());
             consulta.setString(8, personamodificar.getCorreoElectronico());
            consulta.setBoolean(9,personamodificar.getEstatus());
             consulta.setInt(10, personamodificar.getIdpersonal());
            
            consulta.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Error en Modificar Personal DAO");
           throw e; 
        }
        finally{
           this.Cerrar();
        }
    }
     
     public Personal buscarIdPersona(int idpersonal) throws Exception{
        Personal personabusca= new Personal();
        ResultSet resultadosetbusca;
      try{
            this.Conectar();
            PreparedStatement consulta= this.getConexion().prepareCall("SELECT * FROM personal WHERE idpersonal=?");
            consulta.setInt(1,idpersonal);
            resultadosetbusca=consulta.executeQuery();
            if(resultadosetbusca.next()){
            personabusca.setIdpersonal(resultadosetbusca.getInt("idpersonal"));
             personabusca.setClave(resultadosetbusca.getString("clave"));
             personabusca.setNombre(resultadosetbusca.getString("nombre"));
             personabusca.setApat(resultadosetbusca.getString("apat"));
             personabusca.setAmat(resultadosetbusca.getString("amat"));
             personabusca.setPuesto(resultadosetbusca.getString("puesto"));
             personabusca.setCentro(new Centro_trabajoDAO().buscarIdCentro((resultadosetbusca.getInt("idcent_trab"))));
             personabusca.setTelefono(resultadosetbusca.getString("telefono"));
             personabusca.setCorreoElectronico(resultadosetbusca.getString("correoElectronico"));
             personabusca.setEstatus(resultadosetbusca.getBoolean("estatus"));
            }
            resultadosetbusca.close();
            
        }
        catch(Exception e){
            System.out.println("error en PersonaDAO->buscarIdPersona "+e);
           throw e; 
        }
        finally{
           this.Cerrar();
        }  
        return personabusca;
    }
     
    
}
