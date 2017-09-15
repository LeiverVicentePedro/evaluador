/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.modelo;

import java.io.Serializable;

/**
 *
 * @author leiver
 */
public class DatosEvaluante implements Serializable{
    private int id;
    private Personal idevaluante;
    private Categoria norma;
    private int intento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personal getIdevaluante() {
        return idevaluante;
    }

    public void setIdevaluante(Personal idevaluante) {
        this.idevaluante = idevaluante;
    }

    public Categoria getNorma() {
        return norma;
    }

    public void setNorma(Categoria norma) {
        this.norma = norma;
    }

    public int getIntento() {
        return intento;
    }

    public void setIntento(int intento) {
        this.intento = intento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
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
        final DatosEvaluante other = (DatosEvaluante) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
