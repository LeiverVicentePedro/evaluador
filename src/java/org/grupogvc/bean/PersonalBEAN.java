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
import org.grupogvc.dao.PersonalDAO;
import org.grupogvc.modelo.Personal;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class PersonalBEAN implements Serializable{
    private Personal personal=new Personal();
    private List<Personal> listapersonal;
    private List<Personal> filterpersonal;
    private List<Personal> seleccionpersonal;
    private String accion;
    
    PersonalDAO personalDao=new PersonalDAO();

    public PersonalDAO getPersonalDao() {
        return personalDao;
    }

    public void setPersonalDao(PersonalDAO personalDao) {
        this.personalDao = personalDao;
    }

    
    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    

    

    public List<Personal> getListapersonal() {
        return listapersonal;
    }

    public void setListapersonal(List<Personal> listapersonal) {
        this.listapersonal = listapersonal;
    }

    public List<Personal> getFilterpersonal() {
        return filterpersonal;
    }

    public void setFilterpersonal(List<Personal> filterpersonal) {
        this.filterpersonal = filterpersonal;
    }

    public List<Personal> getSeleccionpersonal() {
        return seleccionpersonal;
    }

    public void setSeleccionpersonal(List<Personal> seleccionpersonal) {
        this.seleccionpersonal = seleccionpersonal;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
    public void insertarPersona() {
        try {
            
            personalDao.registrarPersonal(personal);
            this.listarPersona();
            limpiarPersonal();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro Exitoso!.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception ex) {
            System.out.println("Error en PersonalBEAN -> insertarPersonal: " + ex);
        }
    }

    public void modificarPersonal() {
        try {
            personalDao.modificarPersonal(personal);
            limpiarPersonal();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro Modificado!.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception ex) {
            System.out.println("Error en PersonalBEAN -> modificarPersonal: " + ex);
        }
    }

    public void elegirDatoPersonal(Personal personaElegida) throws Exception {

        try {
            if (personaElegida != null) {
                this.personal = personaElegida;
                this.accion = "Modificar";
            }
        } catch (Exception ex) {
            System.out.println("Error en PersonalBEAN -> elegirDatosPersonal: " + ex);
        }

    }

    public void bajaPersona(Personal personalBaja) throws Exception {

        try {
            if(personalBaja!=null){
            personal = personalBaja;    
            personal.setEstatus(false);
            personalDao.modificarPersonal(personal);
            this.listarPersona();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Dato Inhabilitado!.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } catch (Exception ex) {
            System.out.println("Error en Persona BEAN -> bajaPersona: "+ex);
        }
    }

    public void listarPersona() {
        try {
            listapersonal = personalDao.listarPersonal();
        } catch (Exception ex) {
            System.out.println("Error en PersonaBEAN -> listarPersona: " + ex);
        }
    }
    
    public void limpiarPersonal(){
        personal.setNombre("");
        personal.setClave("");
        personal.setNombre("");
        personal.setApat("");
        personal.setAmat("");
        personal.setPuesto("");
        personal.setCentro(null);
        personal.setTelefono("");
        personal.setCorreoElectronico("");
        personal.setEstatus(null);
    }
    
    public void operarPersonal() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.insertarPersona();
                this.limpiarPersonal();
                
                break;
            case "Modificar":
                this.modificarPersonal();
                this.limpiarPersonal();
               
                break;
        }
    }
    
}
