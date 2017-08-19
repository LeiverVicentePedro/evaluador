/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.modelo;

import java.util.Date;

/**
 *
 * @author Jerusalen
 */
public class Resultado {
    private int idresultado;
    private Personal persona;
    private int res_acer;
    private int res_inc;
    private String estatus;
    private String intentos;
    private Date fecha;
    private Double calificacion;
    private Categoria categoria;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idresultado;
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
        final Resultado other = (Resultado) obj;
        if (this.idresultado != other.idresultado) {
            return false;
        }
        return true;
    }

    public int getIdresultado() {
        return idresultado;
    }

    public void setIdresultado(int idresultado) {
        this.idresultado = idresultado;
    }

    public Personal getPersona() {
        return persona;
    }

    public void setPersona(Personal persona) {
        this.persona = persona;
    }


    public int getRes_acer() {
        return res_acer;
    }

    public void setRes_acer(int res_acer) {
        this.res_acer = res_acer;
    }

    public int getRes_inc() {
        return res_inc;
    }

    public void setRes_inc(int res_inc) {
        this.res_inc = res_inc;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getIntentos() {
        return intentos;
    }

    public void setIntentos(String intentos) {
        this.intentos = intentos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

   
    
    
    
}
