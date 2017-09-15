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
 * @author leiver
 */
public class Evaluacion implements Serializable{
    
    private Categoria idevaluacion;
    private String inicio;
    private String fin;
    private String horaIni;
    private String horaFin;
    private int num_preguntas;
    private int intentos;
    
    
    public Categoria getIdevaluacion() {
        return idevaluacion;
    }

    public void setIdevaluacion(Categoria idevaluacion) {
        this.idevaluacion = idevaluacion;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getNum_preguntas() {
        return num_preguntas;
    }

    public void setNum_preguntas(int num_preguntas) {
        this.num_preguntas = num_preguntas;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idevaluacion);
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
        final Evaluacion other = (Evaluacion) obj;
        if (!Objects.equals(this.idevaluacion, other.idevaluacion)) {
            return false;
        }
        return true;
    }

   
    
}
