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
public class EstadisticoResultado {
     private Resultado resultado;
    private Categoria categoria;
    private int promedio;

    public EstadisticoResultado() {
    }

    public EstadisticoResultado(Resultado resultado, Categoria categoria, int promedio) {
        this.resultado = resultado;
        this.categoria = categoria;
        this.promedio = promedio;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
   
    public int getPromedio() {
        return promedio;
    }

    public void setPromedio(int promedio) {
        this.promedio = promedio;
    }

    
    
    
}
