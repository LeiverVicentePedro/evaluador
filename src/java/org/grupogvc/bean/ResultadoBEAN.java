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
import org.grupogvc.dao.ResultadoDAO;
import org.grupogvc.modelo.Resultado;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class ResultadoBEAN implements Serializable {
    private Resultado resultado=new Resultado();
    private List<Resultado> listaresultado;
    private List<Resultado> filterresultado;
    private List<Resultado> seleccionresultado;
    private String accion;

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public List<Resultado> getListaresultado() {
        return listaresultado;
    }

    public void setListaresultado(List<Resultado> listaresultado) {
        this.listaresultado = listaresultado;
    }

    public List<Resultado> getFilterresultado() {
        return filterresultado;
    }

    public void setFilterresultado(List<Resultado> filterresultado) {
        this.filterresultado = filterresultado;
    }

    public List<Resultado> getSeleccionresultado() {
        return seleccionresultado;
    }

    public void setSeleccionresultado(List<Resultado> seleccionresultado) {
        this.seleccionresultado = seleccionresultado;
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
                this.registrarResultado();
                this.limpiarResultado();
                
                break;
            
        }
    }
    
     public void limpiarResultado(){
        this.resultado.setPersona(null);
        this.resultado.setRes_acer(0);
        this.resultado.setRes_inc(0);
        this.resultado.setEstatus("");
        this.resultado.setIntentos("");
        
       
    }
     //--Metodos para Registrar y Modificar
    
    private void registrarResultado() throws Exception{
        ResultadoDAO resultadodao;
            try{
                resultadodao= new ResultadoDAO();
                resultadodao.registrarResultado(resultado);
                this.listarResultado();
                
            }
            catch(Exception e)
            {
                System.out.println("error en Resultado BEAN -->Registrar Resultado"+e);
            }
    }   
    
    
      
     public void listarResultado() throws Exception{
        ResultadoDAO resultadodao;
        try{
            resultadodao=new ResultadoDAO();
            listaresultado = resultadodao.listarResultado();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public void elegirDatoResultado(Resultado resultadoElegirDato) throws Exception{
        ResultadoDAO resultadodao;
        Resultado resultadoTemporal;
        try{
            resultadodao= new ResultadoDAO();
            resultadoTemporal=resultadodao.elegirDatoResultado(resultadoElegirDato);
            
            if(resultadoTemporal != null){
                this.resultado = resultadoTemporal;
                this.accion="Modificar";
            }
            }
        catch (Exception e){
            throw e;
        }
        
    }
    
  
    
}
