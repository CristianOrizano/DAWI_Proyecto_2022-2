package com.proyecto.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.proyecto.entity.Boleta;
import com.proyecto.entity.Detalle;
import com.proyecto.entity.DetalleBoleta;
import com.proyecto.entity.Electrodomestico;
import com.proyecto.entity.Empresa;
import com.proyecto.service.BoletaService;
import com.proyecto.service.DetalleBoletaService;
import com.proyecto.service.EmpresaService;
import com.proyecto.utils.Libreria;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/detalle")
public class DetalleController {
	
	@Autowired
	DetalleBoletaService boledet;
	@Autowired
	BoletaService boletaser;
	
	@RequestMapping("/reporte")
	public void inicio(Model model,@RequestParam("txtcodigo") int codb,HttpServletResponse response) {		
		try {
			//obtener todos los medicamentos
			List<DetalleBoleta> data= boledet.listarDetallesfi(codb);
			List<Detalle> listade=new ArrayList<Detalle>();
			
			for(DetalleBoleta d:data) {
				Detalle detabol=new Detalle();
				
				detabol.setCodigo(d.getBoleta().getNumeroBoleta());
				detabol.setDescripcion(d.getElec().getNombre());
				detabol.setPrecio(d.getPrecio());
				detabol.setCantidad(d.getCantidad());
				listade.add(detabol);
			}
			//acceder al reporte "reporte_medicamento"
			File file = ResourceUtils.getFile("classpath:Electro.jrxml");
			//origen de datos convertir data a tipo JRBeanCollectionDatasource
			JRBeanCollectionDataSource info= new JRBeanCollectionDataSource(listade);
			//invocar al metodo generar reporte 
			JasperPrint print= Libreria.generarReporte(file, info);
			//salida en el navegador en formato pdf
			response.setContentType("application/pdf");
			//salida del flujo
			OutputStream salida= response.getOutputStream();
			//exportar a pdf
			JasperExportManager.exportReportToPdfStream(print, salida);
				
		} catch (Exception e) {
			System.out.println("Error:"+e);
			e.printStackTrace();
		}
			
	}
	
	
	@RequestMapping("/listaventa")
	public String venta(Model model) {		
		List<Boleta> data= boletaser.listarTodosbole();
		
		
		model.addAttribute("lista",data);
		
		return "ListaReporte";
	}

}
