/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.menu;

/**
 *
 * @author leiver
 */
public class Menu {
    private int idmenu;
    private int nivel_usuaio;
    private ElementoMenu idelemento_menu;

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public int getNivel_usuaio() {
        return nivel_usuaio;
    }

    public void setNivel_usuaio(int nivel_usuaio) {
        this.nivel_usuaio = nivel_usuaio;
    }

    public ElementoMenu getIdelemento_menu() {
        return idelemento_menu;
    }

    public void setIdelemento_menu(ElementoMenu idelemento_menu) {
        this.idelemento_menu = idelemento_menu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idmenu;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Menu other = (Menu) obj;
        if (this.idmenu != other.idmenu) {
            return false;
        }
        return true;
    }
    
    
}
