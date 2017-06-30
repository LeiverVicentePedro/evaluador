/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.modelo;

import java.io.Serializable;

/**
 *
 * @author Jerusalen
 */
public class Estado implements Serializable {
    private int idestado;
    private String nombre;
    private Boolean estatus;

    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idestado;
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
        final Estado other = (Estado) obj;
        if (this.idestado != other.idestado) {
            return false;
        }
        return true;
    }
    
    
    
}
