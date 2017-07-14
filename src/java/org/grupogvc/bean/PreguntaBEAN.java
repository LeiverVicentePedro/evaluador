/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.grupogvc.dao.PreguntaDAO;
import org.grupogvc.modelo.Pregunta;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class PreguntaBEAN implements Serializable{
    
    private Pregunta pregunta=new Pregunta();
    private List<Pregunta> listapregunta;
    private List<Pregunta> filterpregunta;
    private List<Pregunta> seleccionpregunta;
    private String accion;

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<Pregunta> getListapregunta() {
        return listapregunta;
    }

    public void setListapregunta(List<Pregunta> listapregunta) {
        this.listapregunta = listapregunta;
    }

    public List<Pregunta> getFilterpregunta() {
        return filterpregunta;
    }

    public void setFilterpregunta(List<Pregunta> filterpregunta) {
        this.filterpregunta = filterpregunta;
    }

    public List<Pregunta> getSeleccionpregunta() {
        return seleccionpregunta;
    }

    public void setSeleccionpregunta(List<Pregunta> seleccionpregunta) {
        this.seleccionpregunta = seleccionpregunta;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    

    

    
    
     public void operarPregunta() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.registrarPregunta();
                this.limpiarPregunta();
                
                break;
            case "Modificar":
                this.modificarPregunta();
                this.limpiarPregunta();
               
                break;
        }
    }
    
     public void limpiarPregunta(){
        this.pregunta.setPregunta(accion);
        this.pregunta.setCategoria(null);
    }
     //--Metodos para Registrar y Modificar
    
    private void registrarPregunta() throws Exception{
        PreguntaDAO preguntadao;
            try{
                preguntadao= new PreguntaDAO();
                preguntadao.registrarPregunta(pregunta);
                this.listarPregunta();
        
            }
            catch(Exception e)
            {
                System.out.println("error en Pregunta BEAN -->Registrar Pregunta"+e);
            }
    }   
    
      public void modificarPregunta() throws Exception{
        PreguntaDAO preguntadao;
            try{
                preguntadao= new PreguntaDAO();
                preguntadao.modificarPregunta(pregunta);
                this.listarPregunta();
            }
            catch(Exception e)
            {
                throw e;
            }
    }
      
     public void listarPregunta() throws Exception{
        PreguntaDAO preguntadao;
        try{
            preguntadao=new PreguntaDAO();
            listapregunta = preguntadao.listarPregunta();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public void elegirDatoPregunta(Pregunta preguntaElegirDato) throws Exception{
        PreguntaDAO preguntadao;
        Pregunta preguntaTemporal;
        try{
            preguntadao= new PreguntaDAO();
            preguntaTemporal=preguntadao.elegirDatoPregunta(preguntaElegirDato);
            
            if(preguntaTemporal != null){
                this.pregunta = preguntaTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
       
}