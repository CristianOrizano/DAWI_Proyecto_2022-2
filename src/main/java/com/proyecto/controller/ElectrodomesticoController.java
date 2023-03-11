package com.proyecto.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.entity.Boleta;
import com.proyecto.entity.Categoria;
import com.proyecto.entity.Cliente;
import com.proyecto.entity.Detalle;
import com.proyecto.entity.DetalleBoleta;
import com.proyecto.entity.Electrodomestico;
import com.proyecto.entity.Empresa;
import com.proyecto.entity.Usuario;
import com.proyecto.service.BoletaService;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.ClienteService;
import com.proyecto.service.ElectrodomesticoService;
import com.proyecto.service.EmpresaService;
import com.proyecto.utils.Libreria;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/electro")
public class ElectrodomesticoController {
	
	@Autowired
	ElectrodomesticoService serviceelec;
	@Autowired
	CategoriaService sercate;
	@Autowired
	ClienteService serCli;
	
	@Autowired
	EmpresaService serempre;
	
	@Autowired
	private BoletaService servicioBol;
	
	@RequestMapping("/lis")
	public String inicio(Model model) {
		
		List<Electrodomestico> data= serviceelec.listarTodos();
		List<Empresa> empre= serempre.listarTodos();
		
		model.addAttribute("lista",data);
		model.addAttribute("listem",empre);
		
		return "CrudElec";
	}
	
	@RequestMapping("/catalogo")
	public String catalogo(Model model) {
		
		List<Electrodomestico> data= serviceelec.listarTodos();
		
		model.addAttribute("lista",data);
		
		return "Catalogo";
	}
	
	
	@RequestMapping("/selecDeta")
	public String detalle(Model model,@RequestParam("txtcodigo") int cod) {
		
		Electrodomestico ele= serviceelec.buscar(cod);
		model.addAttribute("elec",ele);
		
		return "DetalleSel";
	}
	
	
	@RequestMapping("/AgreCarro")
	public String carro(Model model,@RequestParam("txtcodigo") int cod,
			@RequestParam("cantidad") int can,HttpSession session,RedirectAttributes redirect) {
		
		Electrodomestico ele= serviceelec.buscar(cod);
		
		//declarar arreglo de objetos de la clase Detalle
				List<Detalle> data=null;
				int cantidad;
				double total;
				//validar si existe el atributo de tipo sesión "carrito"
				if(session.getAttribute("carrito")==null){//no existe el atributo
					//crear el arreglo data
					data=new ArrayList<Detalle>();	
					 cantidad= 0;
					 total=0.0;
				}
				else{//existe "carrito""recuperar y guardarlo en data"
					data=(List<Detalle>) session.getAttribute("carrito");
					//recuperar la session
					 total= (double) session.getAttribute("total");
					 cantidad= (int) session.getAttribute("cantidad");
				}

				//crear objeto de la clase Detalle
				Detalle d=new Detalle();
				//setear
				d.setCodigo(ele.getCodigo());
				d.setDescripcion(ele.getNombre());
				d.setPrecio(ele.getPrec());
				d.setCantidad(can);
				d.setNomAr(ele.getNombreArchivo());
				d.setImporte(d.getPrecio()*d.getCantidad());			
				//adicionar objeto "d" dentro del arreglo "data"
				data.add(d);
				//actualizar la cantidad y total
				total += d.getImporte();
				cantidad += d.getCantidad();
				
				//crear atributo de tipo sesión "carrito"
				session.setAttribute("carrito", data);
				session.setAttribute("total",total);
				session.setAttribute("cantidad",cantidad);
			
	     	  Cliente cl = new Cliente();
	  		
	     	model.addAttribute("carrito");
	  		model.addAttribute("cli",cl);
	  		model.addAttribute("cantida");
	  		model.addAttribute("total");
	  	//lo voy a redireccionar por el mensaje agregado automatico
        //return "CarroCompra";
	  		return "redirect:/electro/VistaCarro";
	  		
	}
	
	@RequestMapping("/VistaCarro")
	public String carro(Model model,HttpSession session) {
	
		if(session.getAttribute("cantidad")==null ) {
		    
			session.setAttribute("total",0.0);
			session.setAttribute("cantidad",0);

			model.addAttribute("cantida");
	  		model.addAttribute("total");

		}
		
		Cliente cl = new Cliente();

		model.addAttribute("cli",cl);
  	

		return "CarroCompra";
	}
	
	@RequestMapping("/eliminarElec")
	public String eliminar(Model model,@RequestParam("txtcodi") int cod,HttpSession session){
		//recuperar atributo "carrito"
		List<Detalle> data=(List<Detalle>) session.getAttribute("carrito");
		 double total= (double) session.getAttribute("total");
		 int cantidad= (int) session.getAttribute("cantidad");
		
		//bucle
		for(Detalle d:data){
			if(d.getCodigo()==cod) {			
				data.remove(d);
				total-=d.getImporte();
				cantidad-=d.getCantidad();
				break;
			}
		}
		session.setAttribute("total",total);
		session.setAttribute("cantidad",cantidad);
		
		  Cliente cl = new Cliente();

		  model.addAttribute("cantida");
	  		model.addAttribute("total");
	  		model.addAttribute("cli",cl);
	       return "CarroCompra";
	}
	
	
	@RequestMapping("/BuscarCliente")
	public String buscar(Model model){
        List<Cliente> data= serCli.listarTodos();
		
		model.addAttribute("lista",data);
		
		return "ListaClienteAg";
	}
	
	
	@RequestMapping("/AgregarCliente")
	public String AgregaCliente(Model model,@RequestParam("txtcodi") int cod,HttpSession session){
		
        Cliente cl = serCli.buscar(cod);
		model.addAttribute("cli",cl);
		
		return "CarroCompra";
	}

	@RequestMapping("/principal")
	public String principal() {
		
		return "inicio";
	}
	
	//Insertar cabecera y detalle
	@RequestMapping("/InsertarBoleta")
	public String insertar(
				@RequestParam("codicli") int codCli,
				@SessionAttribute("CODIGOUSUARIO") int codUsu,
				HttpSession session,Model model,RedirectAttributes redirect) {
		
	
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(new Date());
			
			Boleta bol=new Boleta();
			bol.setFechaEmision(new SimpleDateFormat("yyyy-MM-dd").parse(date));
			
			double to= (double) session.getAttribute("total");
			bol.setMonto(to);
			Cliente c=new Cliente();
			c.setCodigo(codCli);
			Usuario u=new Usuario();
			u.setCodigo(codUsu);
			bol.setCliente(c);
			bol.setUsuario(u);
			//
			List<Detalle> data=(List<Detalle>) session.getAttribute("carrito");
			//
			List<DetalleBoleta> lista=new ArrayList<DetalleBoleta>();
			//
			for(Detalle d:data) {
				DetalleBoleta detabol=new DetalleBoleta();
				Electrodomestico el=new Electrodomestico();
				el.setCodigo(d.getCodigo());
				
				detabol.setElec(el);
				detabol.setPrecio(d.getPrecio());
				detabol.setCantidad(d.getCantidad());
				lista.add(detabol);
			}
			//
			bol.setListaDetalleBol(lista);
		
			servicioBol.registrarBoleta(bol);
			data.clear();
			session.setAttribute("carrito", data);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		 session.setAttribute("total",0.0);
		 session.setAttribute("cantidad",0);
		  Cliente cl = new Cliente();
		  redirect.addFlashAttribute("MENSAJE","Transaccion Realizada");
		  
	  		model.addAttribute("cli",cl);
	  		
		return "redirect:/electro/VistaCarro";
	}
	
	
	
	@RequestMapping("/reporte")
	public void inicio(HttpServletResponse response,HttpSession session) {
		try {
			//obtener todos los medicamentos
			List<Detalle> data= (List<Detalle>) session.getAttribute("carrito");
			//acceder al reporte "reporte_medicamento"
			File file = ResourceUtils.getFile("classpath:Electro.jrxml");
			//origen de datos convertir data a tipo JRBeanCollectionDatasource
			JRBeanCollectionDataSource info= new JRBeanCollectionDataSource(data);
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

	
	@RequestMapping("/grabar")
	public String grabar(Model model,@RequestParam("codigo") int cod,
						 @RequestParam("categoria") int codcate,@RequestParam("descripcion") String des,
					     @RequestParam("stock")int sto,@RequestParam("precio") double pre,
					     @RequestParam("marca")String mar,@RequestParam("nomfoto") String nom,
					     RedirectAttributes redirect) {
		try {
			//crear objeto
			Electrodomestico m=new Electrodomestico();
			Categoria c=new Categoria();
			c.setCodigocate(codcate);
			//setear
			m.setCodigo(cod);
			m.setCate(c);
			m.setNombre(des);
			m.setStock(sto);
			m.setPrec(pre);
			m.setMarca(mar);
			m.setNombreArchivo(nom);
			m.setEstado(1);
					
			serviceelec.grabar(m);
			
			if(cod==0) {
				//crear atributo
				//este atributo solo va a servir cuando este redireccionando a una ruta "redirect:/medicamento/lis"
				//porq luego desaparece cuando lo lee en la pagina
				redirect.addFlashAttribute("MENSAJE","Registro exitoso");
			}else {
				//crear atributo
				//este atributo solo va a servir cuando este redireccionando a una ruta "redirect:/medicamento/lis"
				//porq luego desaparece cuando lo lee en la pagina
				redirect.addFlashAttribute("MENSAJE","Actualizado exitoso");
			}
			
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","error exitoso");
			System.out.println("error al grabar: "+e.getMessage());
		}
		//cuando grabe invoque al controlador y su metodo listar
		return "redirect:/electro/lis";
	}
	
	@RequestMapping("/buscar")
	//al poner esto el valor de retorno del metodo se convierte en json
	@ResponseBody
	//convertir ese medicamento que retorna aun json
	public Electrodomestico buscar(@RequestParam("codigo") int cod) {
		Electrodomestico m= serviceelec.buscar(cod); 
		System.out.println("nombre=="+m.getNombreArchivo());
		
		return m;
	}
	
	
	//metodo eliminar
		@RequestMapping("/eliminar")
		public String Eliminar(@RequestParam("codigo") int cod,RedirectAttributes redirect) {	
			try {
				serviceelec.eliminar(cod);
				redirect.addFlashAttribute("MENSAJE","Eliminado exitoso");
				
			} catch (Exception e) {
				redirect.addFlashAttribute("MENSAJE","error eliminar");
				System.out.println("error al eliiminar"+e.getMessage());
			}	
			return "redirect:/electro/lis";
		}
	
	//-----------
		@RequestMapping("/buscarPorEmpresa")
		@ResponseBody
		public List<Categoria> buscarPorEmpresa(@RequestParam("codigo") int cod) {
			
			List<Categoria> data= sercate.listarcateporEmpre(cod);
			return data;
		}
		
		@RequestMapping("/subir-archivo") 
		//recuperamos la caja data y almcaceno en archivo
	     public String subirArchivo(@RequestParam("data")MultipartFile archivo,
	    		 @RequestParam("codigo") Integer cod,
	 			RedirectAttributes redirect) throws IOException {
			
			//guardo el nombre de la imagen en una varible
			String nomArchivo =archivo.getOriginalFilename();
			
			//necesito los archivos de la img pero en byte(ya que las imagnes tienes byte)
			byte[] bytes=archivo.getBytes();
			//
			String ruta="C://ZClinica//DatosImg//";
			//generar archivo
			Files.write(Paths.get(ruta+nomArchivo), bytes);	
			serviceelec.actualizarIMG(nomArchivo, cod);
			redirect.addFlashAttribute("MENSAJE","Foto actualizada");
			return "redirect:/electro/lis";
		}

}
