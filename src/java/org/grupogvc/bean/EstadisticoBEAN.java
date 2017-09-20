/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.grupogvc.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.grupogvc.dao.EstadisticoDAO;
import org.grupogvc.modelo.EstadisticoResultado;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Jerusalen
 */
@ManagedBean
@ViewScoped
public class EstadisticoBEAN {
    private List<EstadisticoResultado> listarestadisticoResultado;
    private List<EstadisticoResultado> listarestadisticofilter;
    private PieChartModel circulo;
    private BarChartModel barra;
    private int anio;

    public EstadisticoBEAN(){
        listarEstadistico();
    }
    
    

   
    public List<EstadisticoResultado> getListarestadisticofilter() {
        return listarestadisticofilter;
    }

    public void setListarestadisticofilter(List<EstadisticoResultado> listarestadisticofilter) {
        this.listarestadisticofilter = listarestadisticofilter;
    }
    
    
    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public List<EstadisticoResultado> getListarestadisticoResultado() {
        return listarestadisticoResultado;
    }

    public void setListarestadisticoResultado(List<EstadisticoResultado> listarestadisticoResultado) {
        this.listarestadisticoResultado = listarestadisticoResultado;
    }

    public PieChartModel getCirculo() {
        return circulo;
    }

    public void setCirculo(PieChartModel circulo) {
        this.circulo = circulo;
    }

    public BarChartModel getBarra() {
        return barra;
    }

    public void setBarra(BarChartModel barra) {
        this.barra = barra;
    }
    
      
    
   
    public void listarEstadistico(){
        EstadisticoDAO estadisticodao;
        try{
            estadisticodao=new EstadisticoDAO();
            listarestadisticoResultado=estadisticodao.listaEstadisticoResultado(anio);
            
        }
        catch(Exception e){
           e.printStackTrace();
        }
        
        
    }
    public void graficar(){
        barra=new BarChartModel();
        for(int i=0;i<listarestadisticoResultado.size(); i++){
            ChartSeries serie=new BarChartSeries();
           serie.setLabel(String.valueOf(listarestadisticoResultado.get(i).getCategoria().getTipo()));
            serie.set("Normas",listarestadisticoResultado.get(i).getPromedio());
            barra.addSeries(serie);
        }
        barra.setTitle("Promedio por Categoría");
        barra.setLegendPosition("ne");
        Axis axis=barra.getAxis(AxisType.X);
       // axis.setLabel("Categoria");
        
       
         Axis axisDos=barra.getAxis(AxisType.Y);
        axisDos.setLabel("Promedio");
        
        
        
            
      
    }
    
}
