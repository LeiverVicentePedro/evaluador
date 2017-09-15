/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.acceso;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.grupogvc.modelo.Personal;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class AccesoBEAN implements Serializable {

    private String redireccion;
    private String clave;
    private Personal personal = new Personal();

    public String getRedireccion() {
        return redireccion;
    }
    public String URL(){
        return redireccion;
    }
    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public void accesoSistema() {
        try {
            if (new AccesoDAO().accesoPersonal(clave) != null) {
                personal =new AccesoDAO().accesoPersonal(clave);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("personal", personal);
                System.out.println("nombre: "+personal.getNombre());
                if(personal.getNivel()==1){
                    setRedireccion("principalUno.xhtml");
                }
                if(personal.getNivel()==2){
                    setRedireccion("principalDos.xhtml");
                }
            }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Acceso Denegado","Clave no Valida."));
                }
        } catch (Exception ex) {
            System.out.println("Error en AccesoBEAN -> accesoSistema: " + ex);
        }
    }
    
    public void exite() {
        try {
            FacesContext contexto = FacesContext.getCurrentInstance();
            Personal usuarioVive = (Personal) contexto.getExternalContext().getSessionMap().get("personal");
            if (usuarioVive == null) {
                contexto.getExternalContext().redirect("index.xhtml");
            }
        } catch (Exception ex) {

        }
    }
}
