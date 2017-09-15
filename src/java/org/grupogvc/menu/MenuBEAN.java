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
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
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

    public MenuModel getMenuPrincipal() {
        return menuPrincipal;
    }

    public void setMenuPrincipal(MenuModel menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }
    
    
    
    @PostConstruct
    public void crearMenu(){
        MenuDAO menuDao = new MenuDAO();
        menuPrincipal = new DefaultMenuModel();
        Personal personal = (Personal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personal");
        try{
            listaMenu = menuDao.listarMenuPorUsuario(personal.getNivel());
            for(Menu menu: listaMenu){
                if(menu.getIdelemento_menu().getTipo_menu().equalsIgnoreCase("submenu")&&menu.getIdelemento_menu().getCodigo_submenu()==0){
                        DefaultSubMenu subMenuPrincipal = new DefaultSubMenu(menu.getIdelemento_menu().getNombre());
                        subMenuPrincipal.setIcon(menu.getIdelemento_menu().getIcono());
                        subMenuPrincipal.setStyle("margin-right: 1em;");
                        for(Menu submenu : listaMenu){
                            if(submenu.getIdelemento_menu().getTipo_menu().equalsIgnoreCase("submenu")&&submenu.getIdelemento_menu().getCodigo_submenu()==menu.getIdelemento_menu().getIdelemento_menu()){
                                DefaultSubMenu subMenu = new DefaultSubMenu(menu.getIdelemento_menu().getNombre());
                        subMenu.setIcon(menu.getIdelemento_menu().getIcono());
                        subMenu.setStyle("margin-right: 1em;");
                        /*nuevo if si existe mas de un nivel de submenu*/
                             menuPrincipal.addElement(subMenu);
                            }else
                                if(submenu.getIdelemento_menu().getCodigo_submenu()==menu.getIdelemento_menu().getIdelemento_menu()){
                                    DefaultMenuItem itemSubMenu = new DefaultMenuItem(submenu.getIdelemento_menu().getNombre());
                                    itemSubMenu.setIcon(submenu.getIdelemento_menu().getIcono());
                                    itemSubMenu.setCommand(submenu.getIdelemento_menu().getVista());
                                   
                                    subMenuPrincipal.addElement(itemSubMenu);
                                } 
                        }
                        menuPrincipal.addElement(subMenuPrincipal);
                }else
                    if(menu.getIdelemento_menu().getTipo_menu().equalsIgnoreCase("item")&&menu.getIdelemento_menu().getCodigo_submenu()==0){
                        DefaultMenuItem itemMenu = new DefaultMenuItem(menu.getIdelemento_menu().getNombre());
                                itemMenu.setIcon(menu.getIdelemento_menu().getIcono());
                                itemMenu.setCommand(menu.getIdelemento_menu().getVista());
                                itemMenu.setStyle("margin-right: 1em;");
                                menuPrincipal.addElement(itemMenu);
                    }
            }
        }catch(Exception ex){
            System.out.println("Error en MenuBEAN -> crearMenu: "+ex);
        }
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}
