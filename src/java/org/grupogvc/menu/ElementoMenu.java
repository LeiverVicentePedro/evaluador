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
public class ElementoMenu {
    private int idelemento_menu;
    private String nombre;
    private String tipo_menu;
    private int codigo_submenu;
    private boolean estatus;
    private String icono;
    private String vista;

    public int getIdelemento_menu() {
        return idelemento_menu;
    }

    public void setIdelemento_menu(int idelemento_menu) {
        this.idelemento_menu = idelemento_menu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_menu() {
        return tipo_menu;
    }

    public void setTipo_menu(String tipo_menu) {
        this.tipo_menu = tipo_menu;
    }

    public int getCodigo_submenu() {
        return codigo_submenu;
    }

    public void setCodigo_submenu(int codigo_submenu) {
        this.codigo_submenu = codigo_submenu;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idelemento_menu;
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
        final ElementoMenu other = (ElementoMenu) obj;
        if (this.idelemento_menu != other.idelemento_menu) {
            return false;
        }
        return true;
    }
    
    
}
