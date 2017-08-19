/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jerusalen
 */
public class Respuesta implements Serializable{
    private Pregunta respuesta;
    private String correcto;
    private String incorrecto1;
    private String incorrecto2;

    public Pregunta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Pregunta respuesta) {
        this.respuesta = respuesta;
    }

    public String getCorrecto() {
        return correcto;
    }

    public void setCorrecto(String correcto) {
        this.correcto = correcto;
    }

    public String getIncorrecto1() {
        return incorrecto1;
    }

    public void setIncorrecto1(String incorrecto1) {
        this.incorrecto1 = incorrecto1;
    }

    public String getIncorrecto2() {
        return incorrecto2;
    }

    public void setIncorrecto2(String incorrecto2) {
        this.incorrecto2 = incorrecto2;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.respuesta);
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
        final Respuesta other = (Respuesta) obj;
        if (!Objects.equals(this.respuesta, other.respuesta)) {
            return false;
        }
        return true;
    }
    
    
    
}
