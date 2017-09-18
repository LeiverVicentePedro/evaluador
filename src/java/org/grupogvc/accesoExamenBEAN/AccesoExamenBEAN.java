/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.accesoExamenBEAN;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.grupogvc.dao.DatoEvaluanteDAO;
import org.grupogvc.dao.EvaluacionDAO;
import org.grupogvc.modelo.Categoria;
import org.grupogvc.modelo.Evaluacion;
import org.grupogvc.modelo.Personal;

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

    public void verificaEvaluacion() {
        try {
            Evaluacion evaluacion = new EvaluacionDAO().buscaEvaluacionPorId(categoria.getIdCategoria());
            Personal personal = (Personal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personal");
            System.out.println("objeto " + evaluacion.getIdevaluacion());
            if (evaluacion.getIdevaluacion() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No Permitido", "Evaluacion no Encotrada para esta Norma."));
                redireccion = "accesoExamen.xhtml";
            } else if (evaluacion.getIdevaluacion() != null) {
                if (evaluacion.getInicio().compareTo(new SimpleDateFormat("dd/MM/yyyy").format(new Date())) <= 0 && evaluacion.getFin().compareTo(new SimpleDateFormat("dd/MM/yyyy").format(new Date())) >= 0) {
                    if (evaluacion.getHoraIni().compareTo(new SimpleDateFormat("HH:mm:ss").format(new Date())) <= 0 && new SimpleDateFormat("HH:mm:ss").format(new Date()).compareTo(evaluacion.getHoraFin()) <= 0) {
                        if (evaluacion.getIntentos() == 0) {
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("evaluacion", evaluacion);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("contador", 0);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aciertos", 0);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("errores", 0);
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("preguntas", new ArrayList<>());
                            redireccion = "examen.xhtml";
                        } else {
                            if (evaluacion.getIntentos() > new DatoEvaluanteDAO().buscaDato(categoria.getIdCategoria(), personal.getIdpersonal()).getIntento()) {
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("evaluacion", evaluacion);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("contador", 0);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aciertos", 0);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("errores", 0);
                                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("preguntas", new ArrayList<>());
                                redireccion = "examen.xhtml";
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Intentos Excedido", "Informese con el Administrador del Sistema."));
                                redireccion = "accesoExamen.xhtml";
                            }
                        }

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Esta Evaluacion no Esta Permitido para esta Hora."));
                        redireccion = "accesoExamen.xhtml";
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Esta Evaluacion no Esta Permitido para este Día."));
                    redireccion = "accesoExamen.xhtml";
                }

            }
        } catch (Exception ex) {
            System.out.println("Error en AccesoExamenBEAN -> verificarEvaluacion: " + ex);
        }
    }
}
