/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.acceso;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.grupogvc.conexion.Conexion;
import org.grupogvc.dao.Centro_trabajoDAO;
import org.grupogvc.modelo.Personal;

/**
 *
 * @author leiver
 */
public class AccesoDAO extends Conexion{
    
    public Personal accesoPersonal(String clave) throws Exception{
       Personal personal = new Personal();
        try{
             
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("Select * from personal where clave=? and estatus=1");
            consulta.setString(1, clave);
            ResultSet resultado = consulta.executeQuery();
            if(resultado.next()== true){
                personal.setIdpersonal(resultado.getInt("idpersonal"));
             personal.setClave(resultado.getString("clave"));
             personal.setNombre(resultado.getString("nombre"));
             personal.setApat(resultado.getString("apat"));
             personal.setAmat(resultado.getString("amat"));
             personal.setPuesto(resultado.getString("puesto"));
             personal.setCentro(new Centro_trabajoDAO().buscarIdCentro((resultado.getInt("idcent_trab"))));
             personal.setTelefono(resultado.getString("telefono"));
             personal.setCorreoElectronico(resultado.getString("correoElectronico"));
             personal.setEstatus(resultado.getBoolean("estatus"));
             personal.setNivel(resultado.getInt("nivel"));
            }
        }catch(Exception ex){
            System.out.println("Error en AccesoDAO -> accesoPersonal: "+ex);
        }finally{
            this.Cerrar();
        }
        return personal;
    }
}
