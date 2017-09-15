/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.grupogvc.modelo.Examen;

/**
 *
 * @author leiver
 */
public class ExamenBEAN implements Serializable{
    
    private int aciertos;
    private int errores;
    private int preguntasActiva;
    
    private List<Examen> listaEvaluacion = new ArrayList<Examen>();
    
}
