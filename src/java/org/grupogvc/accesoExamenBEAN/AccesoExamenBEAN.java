/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.accesoExamenBEAN;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.grupogvc.dao.EvaluacionDAO;
import org.grupogvc.modelo.Categoria;
import org.grupogvc.modelo.Evaluacion;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class AccesoExamenBEAN {
    
    Categoria categoria = new Categoria();
    private String redireccion;
    
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String URL() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }
    
    
    public void verificaEvaluacion(){
        try{
            Evaluacion evaluacion = new EvaluacionDAO().buscaEvaluacionPorId(categoria.getIdCategoria());
            System.out.println("objeto "+evaluacion.getIdevaluacion());
            if(evaluacion.getIdevaluacion()==null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"No Permitido","Evaluacion no Encotrada para esta Norma."));
                redireccion="accesoExamen.xhtml";
            }else 
                if(evaluacion.getIdevaluacion()!=null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("evaluacion", evaluacion);
                redireccion ="examen.xhtml";
                
            }
        }catch(Exception ex){
            System.out.println("Error en AccesoExamenBEAN -> verificarEvaluacion: "+ex);
        }
    }
}
