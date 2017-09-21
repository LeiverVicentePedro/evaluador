/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

/**
 *
 * @author Jerusalen
 */

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.grupogvc.dao.Centro_trabajoDAO;
import org.grupogvc.modelo.Centro_trabajo;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class Centro_trabajoBEAN  implements Serializable{
    
    private Centro_trabajo centro=new Centro_trabajo();
    private List<Centro_trabajo> listacentro;
    private List<Centro_trabajo> filtercentro;
    private List<Centro_trabajo> seleccioncentro;
    private String accion;

    public Centro_trabajo getCentro() {
        return centro;
    }

    public void setCentro(Centro_trabajo centro) {
        this.centro = centro;
    }

    public List<Centro_trabajo> getListacentro() {
        return listacentro;
    }

    public void setListacentro(List<Centro_trabajo> listacentro) {
        this.listacentro = listacentro;
    }

    public List<Centro_trabajo> getFiltercentro() {
        return filtercentro;
    }

    public void setFiltercentro(List<Centro_trabajo> filtercentro) {
        this.filtercentro = filtercentro;
    }

    public List<Centro_trabajo> getSeleccioncentro() {
        return seleccioncentro;
    }

    public void setSeleccioncentro(List<Centro_trabajo> seleccioncentro) {
        this.seleccioncentro = seleccioncentro;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    

    
    
     public void operarCentro() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.registrarCentro();
                this.limpiarCentro();
                
                break;
            case "Modificar":
                this.modificarCentro();
                this.limpiarCentro();
               
                break;
        }
    }
    
     public void limpiarCentro(){
        this.centro.setLugar("");
        this.centro.setEstado(null);
        this.centro.setEstatus(Boolean.TRUE);
    }
     //--Metodos para Registrar y Modificar
    
    private void registrarCentro() throws Exception{
        Centro_trabajoDAO centrodao;
            try{
                centrodao= new Centro_trabajoDAO();
                centrodao.registrarCentro_trabajo(centro);
                this.listarCentro();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Centro de Tabajo Registrado."));
            }
            catch(Exception e)
            {
                System.out.println("error en Centro de trabajo BEAN -->Registrar Centro"+e);
            }
    }   
    
      public void modificarCentro() throws Exception{
        Centro_trabajoDAO centrodao;
            try{
                centrodao= new Centro_trabajoDAO();
                centrodao.modificarCentro(centro);
                this.listarCentro();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Centro de Trabajo Modificado."));
            }
            catch(Exception e)
            {
                throw e;
            }
    }
      
     public void listarCentro() throws Exception{
        Centro_trabajoDAO centrodao;
        try{
            centrodao=new Centro_trabajoDAO();
            listacentro = centrodao.listarCentro();
             for(Centro_trabajo picono:listacentro)
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
    
    public void elegirDatoCentro(Centro_trabajo centroElegirDato) throws Exception{
        Centro_trabajoDAO centrodao;
        Centro_trabajo centroTemporal;
        try{
            centrodao= new Centro_trabajoDAO();
            centroTemporal=centrodao.elegirDatoCentro(centroElegirDato);
            
            if(centroTemporal != null){
                this.centro = centroTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
    public void elegirDatoCategoriaInhabilitar(Centro_trabajo centro) throws Exception{
        Centro_trabajoDAO centrodao;
        Centro_trabajo centroTemporal;
        try{
            centrodao= new Centro_trabajoDAO();
            centroTemporal=centrodao.elegirDatoCentro(centro);
            
            if(centroTemporal != null){
                this.centro = centroTemporal;
               }
            
            this.inhabilitarTrabajo();
            this.listarCentro();
            }
        catch (Exception e){
            throw e;
        }
        
    }
    public void inhabilitarTrabajo() throws Exception{
        Centro_trabajoDAO centrodao;
            try{
                centrodao= new Centro_trabajoDAO();
                if(centro.getEstatus()==true){
                centro.setEstatus(false);
                centrodao.modificarCentro(centro);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Centro de Trabajo Inhabilitado."));
                    }
                else
                {
                    if(centro.getEstatus()==false){
                centro.setEstatus(true);
                centrodao.modificarCentro(centro);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Centro de Trabajo Habilitado."));
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("inhabilitar"+e);
            }
    }
    
}

