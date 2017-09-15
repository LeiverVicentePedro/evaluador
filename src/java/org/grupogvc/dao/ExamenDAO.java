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
import org.grupogvc.modelo.Examen;

/**
 *
 * @author leiver
 */
public class ExamenDAO extends Conexion{
    
    public List listarPreguntas(Categoria categoria) throws Exception{
        List<Examen> listaEvaluacion = new ArrayList<Examen>();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT idpregunta,idrespuesta FROM  respuesta INNER JOIN pregunta ON pregunta.idpregunta=respuesta.idrespuesta WHERE idcategoria=? order by rand() limit ?");
            consulta.setInt(1, categoria.getIdCategoria());
            ResultSet resultado = consulta.executeQuery();
            
            while(resultado.next()){
                Examen evaluacion = new Examen();
                evaluacion.setPregunta(new PreguntaDAO().buscarIdPregunta(resultado.getInt("idpregunta")));
                //evaluacion.setRespuesta(new RespuestaDAO().buscarIdRespuesta(resultado.getInt("idrespuesta")));
                listaEvaluacion.add(evaluacion);
            }
        }catch(Exception ex){
            System.out.println("Error en EvaluacionDAO -> listarPregunta: "+ex);
        }finally{
            this.Cerrar();
        }
        return listaEvaluacion;
    }
}
