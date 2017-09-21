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
import org.grupogvc.dao.EstadoDAO;
import org.grupogvc.modelo.Estado;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class EstadoBEAN implements Serializable{
    
    private Estado estado=new Estado();
    private List<Estado> listaestado;
    private List<Estado> filterestado;
    private List<Estado> seleccionestado;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public List<Estado> getFilterestado() {
        return filterestado;
    }

    public void setFilterestado(List<Estado> filterestado) {
        this.filterestado = filterestado;
    }

    public List<Estado> getSeleccionestado() {
        return seleccionestado;
    }

    public void setSeleccionestado(List<Estado> seleccionestado) {
        this.seleccionestado = seleccionestado;
    }
    
    
    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getListaestado() {
        return listaestado;
    }

    public void setListaestado(List<Estado> listaestado) {
        this.listaestado = listaestado;
    }
    
     public void operarEstado() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.registrarEstado();
                this.limpiarEstado();
                
                break;
            case "Modificar":
                this.modificarEstado();
                this.limpiarEstado();
               
                break;
        }
    }
    
     public void limpiarEstado(){
        this.estado.setNombre("");
        this.estado.setEstatus(Boolean.TRUE);
    }
     //--Metodos para Registrar y Modificar
    
    private void registrarEstado() throws Exception{
        EstadoDAO estadodao;
            try{
                estadodao= new EstadoDAO();
                estadodao.registrarEstado(estado);
                this.listarEstado();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Estado Registrado."));
        
            }
            catch(Exception e)
            {
                System.out.println("error en Estado BEAN -->Registrar Estado"+e);
            }
    }   
    
      public void modificarEstado() throws Exception{
        EstadoDAO estadodao;
            try{
                estadodao= new EstadoDAO();
                estadodao.modificarEstado(estado);
                this.listarEstado();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Estado Modifcado."));
            }
            catch(Exception e)
            {
                throw e;
            }
    }
      
     public void listarEstado() throws Exception{
        EstadoDAO estadodao;
        try{
            estadodao=new EstadoDAO();
            listaestado = estadodao.listarEstado();
             for(Estado picono:listaestado)
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
    
    public void elegirDatoEstado(Estado estadoElegirDato) throws Exception{
        EstadoDAO estadodao;
        Estado estadoTemporal;
        try{
            estadodao= new EstadoDAO();
            estadoTemporal=estadodao.elegirDatoEstado(estadoElegirDato);
            
            if(estadoTemporal != null){
                this.estado = estadoTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
     public void elegirDatoEstadoInhabilitar(Estado estadoDato) throws Exception{
        EstadoDAO estadodao;
        Estado estadoTemporal;
        try{
            estadodao= new EstadoDAO();
            estadoTemporal=estadodao.elegirDatoEstado(estadoDato);
            
            if(estadoTemporal != null){
                this.estado = estadoTemporal;
               }
            
            this.inhabilitarEstado();
            this.listarEstado();
            }
        catch (Exception e){
            throw e;
        }
        
    }
    public void inhabilitarEstado() throws Exception{
        EstadoDAO estadodao;
            try{
                estadodao= new EstadoDAO();
                if(estado.getEstatus()==true){
                estado.setEstatus(false);
                estadodao.modificarEstado(estado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Estado Inhabilitado."));
                    }
                else
                {
                    if(estado.getEstatus()==false){
                estado.setEstatus(true);
                estadodao.modificarEstado(estado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Estado Habilitado."));
                    }
                }
            }
            catch(Exception e)
            {
                throw e;
            }
    }
}
