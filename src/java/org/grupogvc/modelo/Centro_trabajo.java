/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.modelo;

/**
 *
 * @author Jerusalen
 */
public class Centro_trabajo {
    private int idcent_trab;
    private String lugar;
    private Estado estado;
    private Boolean estatus;

    public int getIdcent_trab() {
        return idcent_trab;
    }

    public void setIdcent_trab(int idcent_trab) {
        this.idcent_trab = idcent_trab;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        hash = 67 * hash + this.idcent_trab;
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
        final Centro_trabajo other = (Centro_trabajo) obj;
        if (this.idcent_trab != other.idcent_trab) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
