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
import org.grupogvc.modelo.EstadisticoResultado;
import org.grupogvc.modelo.Resultado;

/**
 *
 * @author Jerusalen
 */
public class EstadisticoDAO extends Conexion {
    
     public List<EstadisticoResultado> listaEstadisticoResultado(int anio){
        List<EstadisticoResultado> listaTemporal = null;
        ResultSet resultado;
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("select  idcategoria as categoria, round(avg(calificacion)) as promedio from resultado where year(fecha)=? group by idcategoria asc;");
            
     consulta.setInt(1,anio);
     resultado = consulta.executeQuery();
     listaTemporal = new ArrayList();
     while(resultado.next()){
         EstadisticoResultado resultadoestadistico = new EstadisticoResultado();
         //resultadoestadistico.setResultado(new ResultadoDAO().buscarIdresultado(resultado.getInt("idresultado")));
         resultadoestadistico.setCategoria(new CategoriaDAO().buscarIdCategoria((resultado.getInt("categoria"))));
         resultadoestadistico.setPromedio(resultado.getInt("promedio"));
        
         listaTemporal.add(resultadoestadistico);
         
     } resultado.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listaTemporal;
    }
    
    
}
