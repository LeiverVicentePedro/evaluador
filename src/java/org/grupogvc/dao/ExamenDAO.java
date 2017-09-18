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
import org.grupogvc.modelo.Examen;

/**
 *
 * @author leiver
 */
public class ExamenDAO extends Conexion{
    
    public List listarPreguntas(Evaluacion evaluacion) throws Exception{
        List<Examen> listaEvaluacion = new ArrayList<Examen>();
        try{
            this.Conectar();
            PreparedStatement consulta = this.getConexion().prepareStatement("SELECT idpregunta,idrespuesta FROM  respuesta INNER JOIN pregunta ON pregunta.idpregunta=respuesta.idrespuesta WHERE idcategoria=? order by rand() limit ?");
            consulta.setInt(1, evaluacion.getIdevaluacion().getIdCategoria());
            consulta.setInt(2, evaluacion.getNum_preguntas());
            
            ResultSet resultado = consulta.executeQuery();
            
            while(resultado.next()){
                Examen examen = new Examen();
                examen.setPregunta(new PreguntaDAO().buscarIdPregunta(resultado.getInt("idpregunta")));
                examen.setRespuesta(new RespuestaDAO().buscarIdRespuesta(resultado.getString("idrespuesta")));
                listaEvaluacion.add(examen);
            }
        }catch(Exception ex){
            System.out.println("Error en ExamenDAO -> listarPregunta: "+ex);
        }finally{
            this.Cerrar();
        }
        return listaEvaluacion;
    }
}
