/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.io.FilenameUtils;
import org.grupogvc.dao.ReferenciaDAO;
import org.grupogvc.modelo.Referencia;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author leiver
 */
@ManagedBean
@SessionScoped
public class ReferenciaBEAN implements Serializable{
    
    //variables y objetos usados en la vista para las referencias
    private Referencia referencia = new Referencia();
    private List<Referencia> listaReferencia = new ArrayList();
    
    //objeto para las selecciones
    private Referencia referenciaSeleccionada = new Referencia();
    
    //lista para los filtros
    private List<Referencia> filtroReferencia = new ArrayList();
    
    //objeto de la Referencia DAO para poder acceder a los datos
    private ReferenciaDAO referenciaDao = new ReferenciaDAO();
    
    //varialbe para establecer accion de boton en el dialog de la vista
    private String accion;
    
    //variable que tiene la ruta raiz de donde se guardaran los archivos de las referencias
    String rutaDirectorio="C:\\servidorgvc\\";
    
    //esta variable es unsada para guardar el contenido del archivo para subir
    private UploadedFile archivo;
    
    //getter y setter para cada varialbe y objeto utilizado en la vista
    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    public List<Referencia> getListaReferencia() {
        return listaReferencia;
    }

    public void setListaReferencia(List<Referencia> listaReferencia) {
        this.listaReferencia = listaReferencia;
    }

    public Referencia getReferenciaSeleccionada() {
        return referenciaSeleccionada;
    }

    public void setReferenciaSeleccionada(Referencia referenciaSeleccionada) {
        this.referenciaSeleccionada = referenciaSeleccionada;
    }

    public List<Referencia> getFiltroReferencia() {
        return filtroReferencia;
    }

    public void setFiltroReferencia(List<Referencia> filtroReferencia) {
        this.filtroReferencia = filtroReferencia;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }
    
    
    
    //metodo utilizado para agregar una referencia
    public void agregarReferencia(){
        try{
            referenciaDao.insertarReferencia(referencia);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Registro Exitoso!.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }catch(Exception ex){
         System.out.println("Error en ReferenciaBEAN -> agregarReferencia: "+ex);   
        }
    }
    
    //metodo para listar los elementos existentes como referencias
    public void listarTodasReferencias(){
        try{
            listaReferencia = referenciaDao.listarReferencia();
        }catch(Exception ex){
            System.out.println("Error en ReferenciaBEAN -> listarTodasReferencias: "+ex);
        }
    }
    
    public void guardarArchivo()throws IOException{
        try{
            String rutaDirectorio = this.rutaDirectorio+referencia.getIdCategoria().getTipo();
            File directorio = new File(rutaDirectorio);
            if(!directorio.exists()){
                System.out.println("Ruta que no existe: "+directorio);
               directorio.mkdirs();
               UploadedFile subirArchivo=getArchivo();
               byte[] bytes=null;
               if (null!=subirArchivo) {
                bytes = subirArchivo.getContents();
                String archivoNombre = FilenameUtils.getName(subirArchivo.getFileName());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(rutaDirectorio+"\\"+archivoNombre)));
                stream.write(bytes);
                stream.close();
            }
            }else{
                System.out.println("Ruta que existe: "+directorio);
                 UploadedFile subirArchivo=getArchivo();
               byte[] bytes=null;
               if (null!=subirArchivo) {
                bytes = subirArchivo.getContents();
                String archivoNombre = FilenameUtils.getName(subirArchivo.getFileName());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(rutaDirectorio+"\\"+archivoNombre)));
                stream.write(bytes);
                stream.close();
               }
            }
        }catch(Exception ex){
            System.out.println("Error en ReferenciaBEAN -> guardarArchivo: "+ex);
        }
    }  
    
}
