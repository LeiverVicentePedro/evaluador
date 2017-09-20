/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.grupogvc.dao.DatoEvaluanteDAO;
import org.grupogvc.dao.EvaluacionDAO;
import org.grupogvc.modelo.Evaluacion;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class EvaluacionBEAN {
    Evaluacion evaluacion = new org.grupogvc.modelo.Evaluacion();
    
    List<org.grupogvc.modelo.Evaluacion> listaEvaluacion = new ArrayList<>();
    List<org.grupogvc.modelo.Evaluacion> filtroEvaluacion = new ArrayList<>();
    Evaluacion evaluacionSeleccionada = new Evaluacion();
    
    private String accion;
    
    EvaluacionDAO evaluacionDao = new EvaluacionDAO();
    
    /*variables para parceo de las fechas*/
    private Date inicio;
    private Date fin;
    
    
    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public List<org.grupogvc.modelo.Evaluacion> getListaEvaluacion() {
        return listaEvaluacion;
    }

    public void setListaEvaluacion(List<Evaluacion> listaEvaluacion) {
        this.listaEvaluacion = listaEvaluacion;
    }

    public List<org.grupogvc.modelo.Evaluacion> getFiltroEvaluacion() {
        return filtroEvaluacion;
    }

    public void setFiltroEvaluacion(List<Evaluacion> filtroEvaluacion) {
        this.filtroEvaluacion = filtroEvaluacion;
    }

    public Evaluacion getEvaluacionSeleccionada() {
        return evaluacionSeleccionada;
    }

    public void setEvaluacionSeleccionada(Evaluacion evaluacionSeleccionada) {
        this.evaluacionSeleccionada = evaluacionSeleccionada;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }
    
    
   
    public void listarEvaluacion(){
        try{
            System.out.println("entro a listar Evaluacion");
           listaEvaluacion = evaluacionDao.listarEvaluacion();
        }catch(Exception ex){
            System.out.println("Error en EvaluacionBEAN -> listarEvaluacion: "+ex);
        }
    }
    
    public void agregarEvaluacion(){
        try{
           evaluacion.setInicio(new SimpleDateFormat("dd/MM/yyyy").format(inicio));
           evaluacion.setFin(new SimpleDateFormat("dd/MM/yyyy").format(fin));
           
           if(new EvaluacionDAO().buscaEvaluacionPorId(evaluacion.getIdevaluacion().getIdCategoria()).getIdevaluacion()==null){
                evaluacionDao.agregarEvaluacion(evaluacion);
                new DatoEvaluanteDAO().eliminarEvaluantes(evaluacion.getIdevaluacion().getIdCategoria());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion", "Evaluacion Agregada."));
           }else{
                evaluacionDao.actualizarEvaluacion(evaluacion);
                new DatoEvaluanteDAO().eliminarEvaluantes(evaluacion.getIdevaluacion().getIdCategoria());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion", "Evaluacion Actualizada."));
           }
            
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Ocurrio un Error al Registrar Evaluación."));
            System.out.println("Error en EvaluacionBEAN -> agregarEvaluacion: "+ex);
        }
    }
    
    public void actualizarEvaluacion(){
        try{
            evaluacion.setInicio(new SimpleDateFormat("dd/MM/yyyy").format(inicio));
           evaluacion.setFin(new SimpleDateFormat("dd/MM/yyyy").format(fin));
            evaluacionDao.actualizarEvaluacion(evaluacion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion", "Evaluación Modificada."));
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso", "Ocurrio un Error al Modifcar Evaluación."));
            System.out.println("Error en EvaluacionBEAN -> actualizarrEvaluacion: "+ex);
        }
    }
    
    public void eligeOpcion(Evaluacion evaluacion){
        try{
            if(evaluacion!=null){
                this.evaluacion = evaluacion;
                setInicio(new SimpleDateFormat("dd/MM/yyyy").parse(this.evaluacion.getInicio()));
                setFin(new SimpleDateFormat("dd/MM/yyyy").parse(this.evaluacion.getFin()));
                accion="Modificar";
            }
        }catch(Exception ex){
            System.out.println("Error en EvaluacionBEAN -> elegirOpcion: "+ex);
        }
    }
    
    
    
    public void operarEvaluacion() throws Exception{
        switch(accion)
        {
            case "Generar":
                this.agregarEvaluacion();
                this.listarEvaluacion();
                this.limpiarEvaluacion();
                break;
            case "Modificar":
                this.actualizarEvaluacion();
                this.listarEvaluacion();
                this.limpiarEvaluacion();
               
                break;
        }
    }
    
    public void limpiarEvaluacion(){
        evaluacion.setIntentos(0);
        evaluacion.setIdevaluacion(null);
        inicio=null;
        fin=null;
        evaluacion.setHoraIni("");
        evaluacion.setHoraFin("");
        evaluacion.setNum_preguntas(0);
    }
}
