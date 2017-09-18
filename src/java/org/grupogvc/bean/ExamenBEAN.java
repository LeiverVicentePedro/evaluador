/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.grupogvc.dao.ExamenDAO;
import org.grupogvc.dao.ResultadoDAO;
import org.grupogvc.modelo.Evaluacion;
import org.grupogvc.modelo.Examen;
import org.grupogvc.modelo.Personal;
import org.grupogvc.modelo.Resultado;

/**
 *
 * @author leiver
 */
@ManagedBean
@ViewScoped
public class ExamenBEAN implements Serializable{
    
    private int aciertos;
    private int errores;
    private int preguntasActiva;
    private String nombreBoton;
    private List<Examen> listaEvaluacion = new ArrayList<Examen>();
    Evaluacion categoria = (Evaluacion) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("evaluacion");
    Examen examen = new Examen();
    private String respuestaSeleccionada;
    
    private String redireccion;
    /*para asignr de manera aleatoria las repsuestas*/
    private List<String> respuestas= new ArrayList();
    
    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getErrores() {
        return errores;
    }

    public void setErrores(int errores) {
        this.errores = errores;
    }

    public int getPreguntasActiva() {
        return preguntasActiva;
    }

    public void setPreguntasActiva(int preguntasActiva) {
        this.preguntasActiva = preguntasActiva;
    }

    public List<Examen> getListaEvaluacion() {
        return listaEvaluacion;
    }

    public void setListaEvaluacion(List<Examen> listaEvaluacion) {
        this.listaEvaluacion = listaEvaluacion;
    }

    public String getNombreBoton() {
        return nombreBoton;
    }

    public void setNombreBoton(String nombreBoton) {
        this.nombreBoton = nombreBoton;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public String getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(String respuestaSeleccionada) {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }

    public String getRedireccion() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }
    
    
    public List<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }

    

   
    public String URL(){
        return redireccion;
    }
    
    
    
    
    
    
    
    
    public void cargarPreguntas() throws Exception{
        //cargamos la lista con lo que haya en el navegador si es que lo hay
        listaEvaluacion = (List) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("preguntas");
        //iniia el contador y se carga con lo que haya en el navegador si no empezara desde 0
        preguntasActiva=0;
        preguntasActiva = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("contador");
        aciertos = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("aciertos");
        errores = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("errores");
        //si la lista esta vacia la carga con datos de la base
        System.out.println("Vacio: "+listaEvaluacion.isEmpty());
        if(listaEvaluacion.isEmpty()){
            listaEvaluacion = new ExamenDAO().listarPreguntas(categoria);
            //mandamos a vivir al navegador la lista de preguntas
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("preguntas",listaEvaluacion);
        }
        // ponemos una pregunta segun el numero del contador
        respuestaSeleccionada=null;
        examen=listaEvaluacion.get(preguntasActiva);
        /*creamos la lista para las respuestas*/
        respuestas.clear();
        respuestas.add(examen.getRespuesta().getCorrecto());
        respuestas.add(examen.getRespuesta().getIncorrecto1());
        respuestas.add(examen.getRespuesta().getIncorrecto2());
        /*realizamos la mezcla de las respuestas*/
        Collections.shuffle(respuestas);
        
        System.out.println("Contador: "+preguntasActiva);
        System.out.println("numero de pregunta: "+examen.getPregunta().getIdpregunta());
        System.out.println("Aciertos: "+aciertos);
        System.out.println("Errores: "+errores);
        
    }
    
    
    public void evaluando() throws Exception{
        System.out.println("entro a evaluando");
        /*se obtienen datos del navegador si los hay los carga si no los deja como por defecto*/
        //comprobamso si el boton tiene el nombre de siguiente para guardar resultados
        System.out.println("nombre del Boton"+nombreBoton);
        if(nombreBoton.equalsIgnoreCase("Siguiente")){
            //pregutnamso si la respuesta seleccionada es correcta si lo es incrementamos haciertos si no incrementa error
            System.out.println("Entro si el boton es siguente");
            if(examen.getRespuesta().getCorrecto().equalsIgnoreCase(respuestaSeleccionada)){
                aciertos+=1;
                System.out.println("Entro al acierto");
            }else{
                errores+=1;
                System.out.println("Entro al error");
            }
            //incrementamos el numero del contador
            preguntasActiva+=1;
            
            //mandamos al navegador los datos que nos interesan
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("contador",preguntasActiva);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("aciertos",aciertos);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("errores",errores);
            redireccion="examen.xhtml";
        }else //si el nombre del boton es finalizar aca se pondra los calculos para la calificacion
        if(nombreBoton.equalsIgnoreCase("Finalizar")){
           System.out.println("Entro cuando es Finalizar");
           if(examen.getRespuesta().getCorrecto().equalsIgnoreCase(respuestaSeleccionada)){
                aciertos+=1;
                System.out.println("Entro al acierto");
            }else{
                errores+=1;
                System.out.println("Entro al error");
            }
           Personal personal = (Personal) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personal");
           double calificacion = (aciertos*100)/categoria.getNum_preguntas();
           DecimalFormat df = new DecimalFormat("#.##");
           double calificacionFormateada = Double.parseDouble(df.format(calificacion));
           Resultado resultado = new Resultado();
           resultado.setPersona(personal);
           resultado.setRes_acer(aciertos);
           resultado.setRes_inc(errores);
           if(calificacionFormateada>=80.00){
            resultado.setEstatus("Aprobado");
           }else{
               resultado.setEstatus("No Aprovado");
           }
           
           resultado.setFecha(new java.sql.Date(new java.util.Date().getTime()));
           resultado.setCalificacion(calificacionFormateada);
           resultado.setCategoria(categoria.getIdevaluacion());
           new ResultadoDAO().registrarResultado(resultado);
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("resultado",resultado);
           //FacesContext.getCurrentInstance().getExternalContext().redirect("faces/finExamen.xhtml");
           redireccion="finExamen.xhtml";
        }
    }
    
    public void accionBoton(){
        //obtenemos el numero de pregutnas para saber si es siguente o finalizar la evaluacion
        preguntasActiva=0;
        preguntasActiva = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("contador");
        if(preguntasActiva<categoria.getNum_preguntas()-1){//si el contador es emnor al numero de preguntas el boton sera siguente
            nombreBoton="Siguiente";
        }else 
            if(preguntasActiva==categoria.getNum_preguntas()-1){//si es igual sera finalizar
            nombreBoton="Finalizar";
        }
    }
    
}
