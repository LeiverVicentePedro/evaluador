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
import javax.annotation.PostConstruct;
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
public class CategoriaBEAN implements Serializable{
    //objetos para la usarse en la vista
    Categoria categoria = new Categoria();
    List<Categoria> listaCategoria = new ArrayList();
    
    //obejto que hace referencia a la clase DAO que interactua con el BEAN
    CategoriaDAO categoriaDao = new CategoriaDAO();
    //getter y setter para poder acceder desde la vista

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
    
    
    //metodos que seran utilizados desde la vista
    public void insertarCategoria(){
        try{
            categoriaDao.insertarCategoria(categoria);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro Exitoso!.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }catch(Exception ex){
            System.out.println("Error en CategoriaBEAN -> insertarCategoria: "+ex);
        }
    } 
    @PostConstruct
    public void listarCategorias(){
        try{
            listaCategoria = categoriaDao.listarCategoria();
        }catch(Exception ex){
            System.out.println("Error en CategoriaBEAN -> listarCategorias: "+ex);
        }
    }
}
