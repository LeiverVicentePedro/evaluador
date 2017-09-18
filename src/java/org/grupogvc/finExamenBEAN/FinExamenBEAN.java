/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.finExamenBEAN;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import org.grupogvc.modelo.Resultado;
import org.grupogvc.reporte.ResultadoImpreso;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class FinExamenBEAN {
    private Resultado resultado = new Resultado();

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }
    
    public void llenaResultado(){
        resultado = (Resultado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("resultado");
    }
    
    public void descargarComprobante(){
        try{
        
        new ResultadoImpreso().exportarPDFSolicitud(resultado);
        }catch(Exception ex){
         System.out.println("Error en Solicitud_mcBEAN -> crearPDF: "+ex);
        }
    }
}
