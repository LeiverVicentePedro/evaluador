/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.grupogvc.dao.ReferenciaDAO;
import org.grupogvc.modelo.Referencia;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author leiver
 */

@ManagedBean
@ViewScoped
public class ReferenciaBEAN implements Serializable{
    
    private Referencia referencia = new Referencia();
    private List<Referencia> listaReferencia;
    private List<Referencia> filtroReferencia;
    
    private String rutaDirectorio="C:\\servidorgvc\\";
    
    private String accion;
    
    private UploadedFile archivo;
    
    private ReferenciaDAO referenciaDao = new ReferenciaDAO();
    
    private List<Referencia> referenciaSeleccionada;
    
    private StreamedContent archivoDescarga;
    
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

    public List<Referencia> getFiltroReferencia() {
        return filtroReferencia;
    }

    public void setFiltroReferencia(List<Referencia> filtroReferencia) {
        this.filtroReferencia = filtroReferencia;
    }

    public String getRutaDirectorio() {
        return rutaDirectorio;
    }

    public void setRutaDirectorio(String rutaDirectorio) {
        this.rutaDirectorio = rutaDirectorio;
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

    public List<Referencia> getReferenciaSeleccionada() {
        return referenciaSeleccionada;
    }

    public void setReferenciaSeleccionada(List<Referencia> referenciaSeleccionada) {
        this.referenciaSeleccionada = referenciaSeleccionada;
    }

    public StreamedContent getArchivoDescarga() {
        return archivoDescarga;
    }

    public void setArchivoDescarga(StreamedContent archivoDescarga) {
        this.archivoDescarga = archivoDescarga;
    }
    
    
    
    public void listatTodasReferencias(){
        try{
            listaReferencia = referenciaDao.listarReferencia();
        }catch(Exception ex){
            System.out.println("Error en ReferenciaBEAN -> listarTodasReferencias: "+ex);
        }
    }
    
    public void guardarArchivo()throws IOException{
        try {
            String rutaDirectorio = this.rutaDirectorio + referencia.getIdCategoria().getTipo();
            File directorio = new File(rutaDirectorio);
            if (!directorio.exists()) {
                System.out.println("Ruta que no existe: " + directorio);
                directorio.mkdirs();
                UploadedFile subirArchivo = getArchivo();
                byte[] bytes = null;
                if (null != subirArchivo) {
                    bytes = subirArchivo.getContents();
                    String archivoNombre = FilenameUtils.getName(subirArchivo.getFileName());
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(rutaDirectorio + "\\" + archivoNombre)));
                    stream.write(bytes);
                    stream.close();
                    referencia.setReferencia(rutaDirectorio + "\\" + archivoNombre);
                    referenciaDao.insertarReferencia(referencia);
                }
            } else {
                System.out.println("Ruta que existe: " + directorio);
                UploadedFile subirArchivo = getArchivo();
                byte[] bytes = null;
                if (null != subirArchivo) {
                    bytes = subirArchivo.getContents();
                    String archivoNombre = FilenameUtils.getName(subirArchivo.getFileName());
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(rutaDirectorio + "\\" + archivoNombre)));
                    stream.write(bytes);
                    stream.close();
                    referencia.setReferencia(rutaDirectorio + "\\" + archivoNombre);
                    referenciaDao.insertarReferencia(referencia);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error en ReferenciaBEAN -> guardarArchivo: " + ex);
        }
    } 
    /*
    * tipos de datos
    * WORD              aplication/msword
    * JPEG IMAGEN       image/jpeg
    * VIDEO MPEG        video/mpeg
    * PDF               aplication/pdf
    * POWER POINT       aplication/vnd.ms-powerpoint
    * RAR archive	application/x-rar-compressed
    * Microsoft Excel	application/vnd.ms-excel
    * Comma-separated values (CSV)	text/csv
    * AVI: Audio Video Interleave	video/x-msvideo
    * MP4 Video         video/mp4
    */
    public void descargar(Referencia referencia){
         try{
            System.out.println("Se esta Descargando...");
            System.out.println("Extencion de l archivo: "+getExtencion(referencia.getReferencia()));
            String tipoArchivo;
            switch(getExtencion(referencia.getReferencia())){
                case "pdf": tipoArchivo="application/pdf"; break;
                case "docx": tipoArchivo="application/msword"; break;
                case "doc": tipoArchivo="application/msword"; break;
                case "ppt": tipoArchivo="application/vnd.ms-powerpoint"; break;
                case "pptx": tipoArchivo="application/vnd.ms-powerpoint"; break;
                case "csv": tipoArchivo="text/csv"; break;
                case "xls": tipoArchivo="application/vnd.ms-excel"; break;
                case "xlsx": tipoArchivo="application/vnd.ms-excel"; break;
                case "mp4": tipoArchivo="video/mp4"; break;
                case "avi": tipoArchivo="video/x-msvideo"; break;
                case "jpeg": tipoArchivo="image/jpeg"; break;
                case "jpg": tipoArchivo="image/jpeg"; break;
                case "mpeg": tipoArchivo="video/mpeg"; break;
                case "rar": tipoArchivo="application/x-rar-compressed"; break;
                default: tipoArchivo="ninguno"; break;
            }
            if(!tipoArchivo.equalsIgnoreCase("ninguno")){
                System.out.println("Tipo MIME: "+tipoArchivo);
                System.out.println("Nombre archivo: "+ getNombreArchivo(referencia.getReferencia()));
           
                File ficheroXLS = new File(referencia.getReferencia());
                FacesContext ctx = FacesContext.getCurrentInstance();
                FileInputStream fis = new FileInputStream(ficheroXLS);
                byte[] bytes = new byte[1000];
                int read = 0;

                if (!ctx.getResponseComplete()) {
                String fileName = ficheroXLS.getName();
                //String contentType = "application/vnd.ms-excel";
                String contentType = tipoArchivo;
                HttpServletResponse response =(HttpServletResponse) ctx.getExternalContext().getResponse();
                response.setContentType(contentType);
                response.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
                ServletOutputStream out = response.getOutputStream();

                while ((read = fis.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

                out.flush();
                out.close();
                System.out.println("\nDescargado\n");
                ctx.responseComplete();
                }
            
    
            }else{
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Advertencia", "Referencia Rota informe a su Administrador."));
            }
        }catch(Exception ex){
                System.out.println("Error en ReferenciaBEAN -> descargar: "+ex);
            }
    }
    
    public String getExtencion(String archivo){
        int index = archivo.lastIndexOf('.');
        if(index == -1){
            return "";
        }else{
            return archivo.substring(index+1);
        }
    }
    public String getNombreArchivo(String archivo){
        int index = archivo.lastIndexOf('\\');
        if(index == -1){
            return "";
        }else{
            return archivo.substring(index+1);
        }
    }
    
    public void eliminarReferencia(Referencia referencia){
        try{
            File archivo = new File(referencia.getReferencia());
            if(archivo.delete()){
                referenciaDao.eliminarReferencia(referencia);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Informacion", "Referencia Eliminada."));
            }else{
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "Referencia no Eliminada."));
            }
        }catch(Exception ex){
            System.out.println("Error en ReferenciaBEAN -> eliminarArchivo: "+ex);
        }
    }
}
