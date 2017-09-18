/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.reporte;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.grupogvc.modelo.Resultado;

/**
 *
 * @author Jerusalen
 */
public class ResultadoImpreso {
      public void exportarPDFSolicitud(Resultado resultadoI) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        String servicioSolicitado = "";

        parametros.put("normaEvaluacion", resultadoI.getCategoria().getTipo().toUpperCase());
        String calif = String.valueOf(resultadoI.getCalificacion());
        parametros.put("calificacion", calif);
        parametros.put("nombre", resultadoI.getPersona().getNombre().toUpperCase() + " " + resultadoI.getPersona().getApat().toUpperCase() + " " + resultadoI.getPersona().getAmat().toUpperCase());
        String fecha = String.valueOf(resultadoI.getFecha());
        parametros.put("fecha", fecha);
               
        File archivo = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/ReporteGVC.jasper"));
        JasperPrint imprimirArchivo = JasperFillManager.fillReport(archivo.getPath(), parametros, new JREmptyDataSource());

        HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        respuesta.addHeader("Content-Disposition", "attachment; filename=\"Comprobante_Resultado.pdf\";");
        ServletOutputStream stream = respuesta.getOutputStream();

        JasperExportManager.exportReportToPdfStream(imprimirArchivo, stream);

        FacesContext.getCurrentInstance().responseComplete();
    }

    
}
