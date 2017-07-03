/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.grupogvc.dao.ReferenciaDAO;
import org.grupogvc.modelo.Referencia;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class ReferenciaBEAN implements Serializable{
    
    //variables y objetos usados en la vista para las referencias
    private Referencia referencia = new Referencia();
    private List<Referencia> listaReferencia = new ArrayList();
    
    //objeto para las selecciones
    private Referencia referenciaSeleccionada = new Referencia();
    
    //lista para los filtros
    private List<Referencia> filtroReferencia = new ArrayList();
    
    //objeto de la Referencia DAO para poder acceder a los datos
    private ReferenciaDAO referenciaDao = new ReferenciaDAO();
    
    //varialbe para establecer accion de boton en el dialog de la vista
    private String accion;

    //getter y setter para cada varialbe y objeto utilizado en la vista
    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    public List<Referencia> getListaReferencia() {
        return listaReferencia;
    }

    public void setListaReferencia(List<Referencia> listaReferencia) {
        this.listaReferencia = listaReferencia;
    }

    public Referencia getReferenciaSeleccionada() {
        return referenciaSeleccionada;
    }

    public void setReferenciaSeleccionada(Referencia referenciaSeleccionada) {
        this.referenciaSeleccionada = referenciaSeleccionada;
    }

    public List<Referencia> getFiltroReferencia() {
        return filtroReferencia;
    }

    public void setFiltroReferencia(List<Referencia> filtroReferencia) {
        this.filtroReferencia = filtroReferencia;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    //metodo utilizado para agregar una referencia
    public void agregarReferencia(){
        try{
            referenciaDao.insertarReferencia(referencia);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro Exitoso!.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }catch(Exception ex){
         System.out.println("Error en ReferenciaBEAN -> agregarReferencia: "+ex);   
        }
    }
    
    //metodo para listar los elementos existentes como referencias
    public void listarTodasReferencias(){
        try{
            listaReferencia = referenciaDao.listarReferencia();
        }catch(Exception ex){
            System.out.println("Error en ReferenciaBEAN -> listarTodasReferencias: "+ex);
        }
    }
    
}
