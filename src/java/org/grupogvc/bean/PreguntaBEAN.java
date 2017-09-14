/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.grupogvc.dao.PreguntaDAO;
import org.grupogvc.dao.RespuestaDAO;
import org.grupogvc.modelo.Pregunta;
import org.grupogvc.modelo.Respuesta;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class PreguntaBEAN implements Serializable{
    
    private Pregunta pregunta=new Pregunta();
    Respuesta respuesta = new Respuesta();
    private List<Pregunta> listapregunta;
    private List<Pregunta> filterpregunta;
    private List<Pregunta> seleccionpregunta;
    private List<Respuesta> listarespuesta;
    private String accion;
   
    
    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
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
        this.limpiarPregunta();
        this.accion = accion;
    }

    public List<Respuesta> getListarespuesta() {
        return listarespuesta;
    }

    public void setListarespuesta(List<Respuesta> listarespuesta) {
        this.listarespuesta = listarespuesta;
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
        this.pregunta.setPregunta("");
        this.pregunta.setCategoria(null);
        this.respuesta.setRespuesta(null);
        this.respuesta.setCorrecto("");
        this.respuesta.setIncorrecto1("");
        this.respuesta.setIncorrecto2("");
    }
     //--Metodos para Registrar y Modificar
    
    private void registrarPregunta() throws Exception{
        PreguntaDAO preguntadao;
        RespuestaDAO respuestadao;
            try{
                preguntadao= new PreguntaDAO();
                respuestadao= new RespuestaDAO();
                
                preguntadao.registrarPregunta(pregunta);
                
                Pregunta preguntaTem=preguntadao.buscarIdPreguntaParaRespuesta(pregunta.getPregunta());
               
               
                respuesta.setRespuesta(preguntaTem);
                respuestadao.registrarRespuesta(respuesta);

            
                this.listarPregunta();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Datos Registrados."));
            }
            catch(Exception e)
            {
                System.out.println("error en Pregunta BEAN -->Registrar Pregunta"+e);
            }
    }   
    
      public void modificarPregunta() throws Exception{
        PreguntaDAO preguntadao;
        RespuestaDAO respuestadao;
            try{
                preguntadao= new PreguntaDAO();
                respuestadao= new RespuestaDAO();
                
                preguntadao.modificarPregunta(pregunta);
               respuestadao.modificarRespuesta(respuesta);
                this.listarPregunta();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Datos Modificados."));
            }
            catch(Exception e)
            {
                System.out.println("error en Pregunta BEAN -->Modificar Pregunta"+e);
            }
    }
      
     public void listarPregunta() throws Exception{
        PreguntaDAO preguntadao;
        try{
            preguntadao=new PreguntaDAO();
            listapregunta = preguntadao.listarPregunta();
            for(Pregunta picono:listapregunta)
            {
                if(picono.getEstatus()==true)
                {
                    picono.setIcono("fa fa-ban");
                }
                else
                {
                    picono.setIcono("fa fa-check");
                }
            }
        }
        catch(Exception e){
            throw e;
        }
    }
       public void listaRespuesta() throws Exception{
        RespuestaDAO respuestadao;
        try{
            respuestadao=new RespuestaDAO();
            listarespuesta = respuestadao.listarRespuesta();
        }
        catch(Exception e){
            throw e;
        }
    }
     
    
    public void elegirDatoPregunta(Pregunta preguntaElegirDato) throws Exception{
        PreguntaDAO preguntadao;
        RespuestaDAO respuestadao;
        Pregunta preguntaTemporal;
        try{
            preguntadao= new PreguntaDAO();
            respuestadao=new RespuestaDAO();
            preguntaTemporal=preguntadao.elegirDatoPregunta(preguntaElegirDato);
            respuesta=respuestadao.elegirDatoRespuesta(preguntaElegirDato);
            
            
            if(preguntaTemporal != null){
                this.pregunta = preguntaTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
    public void elegirDatoPreguntaInhabilitar(Pregunta preguntaElegirDato) throws Exception{
        PreguntaDAO preguntadao;
        Pregunta preguntaTemporal;
        try{
            preguntadao= new PreguntaDAO();
            preguntaTemporal=preguntadao.elegirDatoPregunta(preguntaElegirDato);
            
            if(preguntaTemporal != null){
                this.pregunta = preguntaTemporal;
               }
            this.inhabilitarPregunta();
            this.listarPregunta();
            }
        catch (Exception e){
            throw e;
        }
        
    }
    public void inhabilitarPregunta() throws Exception{
        PreguntaDAO preguntadao;
            try{
                preguntadao= new PreguntaDAO();
                if(pregunta.getEstatus()==true){
                pregunta.setEstatus(false);
                preguntadao.modificarPregunta(pregunta);
                    }
                else
                {
                    if(pregunta.getEstatus()==false){
                pregunta.setEstatus(true);
                preguntadao.modificarPregunta(pregunta);
                    }
                }
            }
            catch(Exception e)
            {
                throw e;
            }
    }
    
    
     
      public void eliminarPregunta(Pregunta preguntaEliminar) throws Exception{
        PreguntaDAO preguntadao;
        RespuestaDAO respuestadao;
            try{
                preguntadao= new PreguntaDAO();
                respuestadao= new RespuestaDAO();
                
                respuestadao.eliminarRespuesta(preguntaEliminar);
                preguntadao.eliminarPregunta(preguntaEliminar);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Datos Eliminados"));
                this.listarPregunta();
            }
            catch(Exception e)
            {
                System.out.println("error en Pregunta BEAN -->Eliminar Pregunta"+e);
            }
    }
       
}
