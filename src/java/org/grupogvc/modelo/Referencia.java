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
public class Referencia implements Serializable{
    //objetos que se hacen referencia a la tabla Referencia en la base de datos
    private int idReferencia;
    private String descripcion;
    private String referencia;
    private Categoria idCategoria;

    //getter y setter para las variables anteriores.
    public int getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idReferencia;
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
        final Referencia other = (Referencia) obj;
        if (this.idReferencia != other.idReferencia) {
            return false;
        }
        return true;
    }
    
}
