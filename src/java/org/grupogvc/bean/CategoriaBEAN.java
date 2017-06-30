/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.io.Serializable;
import org.grupogvc.modelo.Categoria;
import java.util.List;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.grupogvc.dao.CategoriaDAO;
import org.primefaces.context.RequestContext;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class CategoriaBEAN implements Serializable {

    //objetos para la usarse en la vista
    Categoria categoria = new Categoria();
    List<Categoria> listaCategoria = new ArrayList();

    //obejto que hace referencia a la clase DAO que interactua con el BEAN
    CategoriaDAO categoriaDao = new CategoriaDAO();
    //getter y setter para poder acceder desde la vista

    //objeto que servira para almacenar los filtros que se realicen
    List<Categoria> filtroCategoria;

    //variable que contedra el nombre de la accion a realizar en el boton
    private String accion;
    //objeto para cuando se selecciona un dato en la tabla
    Categoria categoriaSeleccionada = new Categoria();
    
    //getter y setter de los objetos y listas 
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public CategoriaDAO getCategoriaDao() {
        return categoriaDao;
    }

    public void setCategoriaDao(CategoriaDAO categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    public List<Categoria> getFiltroCategoria() {
        return filtroCategoria;
    }

    public void setFiltroCategoria(List<Categoria> filtroCategoria) {
        this.filtroCategoria = filtroCategoria;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
         this.accion = accion;
    }

    public Categoria getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(Categoria categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }
    
    //metodos que seran utilizados desde la vista
    public void insertarCategoria() {
        try {
            
            categoriaDao.insertarCategoria(categoria);
            this.listarCategorias();
            limpiarCategoria();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro Exitoso!.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaBEAN -> insertarCategoria: " + ex);
        }
    }

    public void modificarCategoria() {
        try {
            categoriaDao.modificarCategoria(categoria);
            limpiarCategoria();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro Modificado!.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception ex) {
            System.out.println("Error en CategoriaBEAN -> modificarCategoria: " + ex);
        }
    }

    public void elegirDatoCategoria(Categoria categoriaElegida) throws Exception {

        try {
            if (categoriaElegida != null) {
                this.categoria = categoriaElegida;
                this.accion = "Modificar";
            }
        } catch (Exception ex) {
            System.out.println("Error en CategoriaBEAN -> elegirDatosCategoria: " + ex);
        }

    }

    public void bajaCategoria(Categoria categoriaBaja) throws Exception {

        try {
            if(categoriaBaja!=null){
            categoria = categoriaBaja;    
            categoria.setEstatus(false);
            categoriaDao.modificarCategoria(categoria);
            this.listarCategorias();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Dato Inhabilitado!.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } catch (Exception ex) {
            System.out.println("Error en CategoriaBEAN -> bajaCategoria: "+ex);
        }
    }

    public void listarCategorias() {
        try {
            listaCategoria = categoriaDao.listarCategoria();
        } catch (Exception ex) {
            System.out.println("Error en CategoriaBEAN -> listarCategorias: " + ex);
        }
    }
    
    public void limpiarCategoria(){
        categoria.setTipo("");
        categoria.setEstatus(null);
    }
    
    public void operarCategoria() throws Exception{
        switch(accion)
        {
            case "Registrar":
                this.insertarCategoria();
                this.limpiarCategoria();
                
                break;
            case "Modificar":
                this.modificarCategoria();
                this.limpiarCategoria();
               
                break;
        }
    }
}
