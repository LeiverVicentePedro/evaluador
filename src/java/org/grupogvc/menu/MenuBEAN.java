/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.menu;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.grupogvc.modelo.Personal;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class MenuBEAN implements Serializable{
    
    private MenuModel menuPrincipal;
    private List<Menu> listaMenu;
    
    @PostConstruct
    public void crearMenu(){
        MenuDAO menuDao = new MenuDAO();
        menuPrincipal = new DefaultMenuModel();
        Personal personal = (Personal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personal");
        try{
        }catch(Exception ex){
            System.out.println("Error en MenuBEAN -> crearMenu: "+ex);
        }
    }
}
