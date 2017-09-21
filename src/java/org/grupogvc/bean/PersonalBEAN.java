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
import org.grupogvc.dao.PersonalDAO;
import org.grupogvc.modelo.Personal;

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
    private Personal cargaUsuario=new Personal();
    
    PersonalDAO personalDao=new PersonalDAO();

    public Personal getCargaUsuario() {
        return cargaUsuario;
    }

    public void setCargaUsuario(Personal cargaUsuario) {
        this.cargaUsuario = cargaUsuario;
    }
    
    

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
        this.limpiarPersonal();
        this.accion = accion;
    }
    
    
    public void insertarPersona() {
        try {
            
            personalDao.registrarPersonal(personal);
            this.listarPersona();
            limpiarPersonal();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Personal Registrado."));
        } catch (Exception ex) {
            System.out.println("Error en PersonalBEAN -> insertarPersonal: " + ex);
        }
    }
    
    public void cargarDatosUsuario() throws Exception{
        FacesContext contexto = FacesContext.getCurrentInstance(); //paraq entrar ql dom del navegador
            Personal usuarioVive = (Personal) contexto.getExternalContext().getSessionMap().get("personal");//llamo a  la etiqueta usuario que es un objeto que ya debe

        cargaUsuario=new PersonalDAO().buscarIdPersona(usuarioVive.getIdpersonal());
    }

    public void modificarPersonal() {
        try {
            personalDao.modificarPersonal(personal);
            limpiarPersonal();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Personal Modifcado."));
        } catch (Exception ex) {
            System.out.println("Error en PersonalBEAN -> modificarPersonal: " + ex);
        }
    }
    public void modificarUsuario() {//este metodo se usa para la vista Usuario(modificar sus datos)
        try {
            
            personalDao.modificarPersonal(cargaUsuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Datos Guardados."));
        } catch (Exception ex) {
            System.out.println("Error en PersonalBEAN -> modificarUsuario: " + ex);
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

    public void listarPersona() {
        try {
            listapersonal = personalDao.listarPersonal();
             for(Personal picono:listapersonal)
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
        personal.setNivel(0);
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
    public void elegirDatoPersonalInhabilitar(Personal personaDato) throws Exception{
        PersonalDAO personadao;
        Personal personaTemporal;
        try{
            personadao= new PersonalDAO();
            personaTemporal=personadao.elegirDatoPersonal(personaDato);
            
            if(personaTemporal != null){
                this.personal = personaTemporal;
               }
            
            this.inhabilitarPersona();
            this.listarPersona();
            }
        catch (Exception e){
            throw e;
        }
        
    }
    public void inhabilitarPersona() throws Exception{
        PersonalDAO perdao;
            try{
                perdao= new PersonalDAO();
                if(personal.getEstatus()==true){
                personal.setEstatus(false);
                perdao.modificarPersonal(personal);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Personal Inhabilitado."));
                    }
                else
                {
                    if(personal.getEstatus()==false){
                personal.setEstatus(true);
                perdao.modificarPersonal(personal);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Información", "Personal Habilitado."));
                    }
                }
            }
            catch(Exception e)
            {
                throw e;
            }
    }
    
}
