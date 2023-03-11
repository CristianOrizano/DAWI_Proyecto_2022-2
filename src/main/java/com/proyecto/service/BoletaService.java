package com.proyecto.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entity.Boleta;
import com.proyecto.entity.DetalleBoleta;
import com.proyecto.entity.DetalleBoletaPK;
import com.proyecto.entity.Electrodomestico;
import com.proyecto.repository.BoletaRepository;
import com.proyecto.repository.DetalleBoletaRepository;

@Service
public class BoletaService {
	
	@Autowired
	private BoletaRepository repoBol;
	
	@Autowired
	private DetalleBoletaRepository repoDet;
	
	@Autowired
	private ElectrodomesticoService ele;
	
	@Transactional
	public void registrarBoleta(Boleta bean) {
		try {
			//grabar Boleta
			repoBol.save(bean);
			//grabar detalle
			for(DetalleBoleta mhb:bean.getListaDetalleBol()) {
				DetalleBoletaPK pk=new DetalleBoletaPK();
				pk.setNumeroBoleta(bean.getNumeroBoleta());
				pk.setCodigoElec(mhb.getElec().getCodigo());
				mhb.setPk(pk);
				ele.actualizarstock(mhb.getCantidad(),mhb.getElec().getCodigo());
				repoDet.save(mhb);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Boleta> listarTodosbole(){
		return repoBol.findAll();
	}
	
	
	

}
