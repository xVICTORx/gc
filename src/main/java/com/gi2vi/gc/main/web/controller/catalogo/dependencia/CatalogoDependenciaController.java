package com.gi2vi.gc.main.web.controller.catalogo.dependencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

import com.gi2vi.gc.main.core.manager.DependenciaManager;
import com.gi2vi.gc.main.core.manager.EstadoManager;
import com.gi2vi.gc.main.persistence.model.Dependencia;
import com.gi2vi.gc.main.persistence.model.Estado;

@ManagedBean(name = "catalogoDependencia")
@SessionScoped
public class CatalogoDependenciaController implements Serializable {

	private static final long serialVersionUID = 8879131062460176113L;

	@ManagedProperty(value = "#{lazyDependenciaDataModel}")
	private LazyDataModel<Dependencia> lazyDependenciaDataModel;

	@ManagedProperty(value = "#{dependenciaManager}")
	private DependenciaManager dependenciaManager;

	@ManagedProperty(value = "#{estadoManager}")
	private EstadoManager estadoManager;

	private Dependencia dependencia = new Dependencia();
	private ArrayList<SelectItem> estados = new ArrayList<SelectItem>();

	public void setLazyDependenciaDataModel(
			LazyDataModel<Dependencia> lazyDependenciaDataModel) {
		this.lazyDependenciaDataModel = lazyDependenciaDataModel;
	}

	public LazyDataModel<Dependencia> getLazyDependenciaDataModel() {
		return lazyDependenciaDataModel;
	}

	public void setDependenciaManager(DependenciaManager dependenciaManager) {
		this.dependenciaManager = dependenciaManager;
	}

	public DependenciaManager getDependenciaManager() {
		return dependenciaManager;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public EstadoManager getEstadoManager() {
		return estadoManager;
	}

	public void setEstadoManager(EstadoManager estadoManager) {
		this.estadoManager = estadoManager;
	}

	public List<SelectItem> getEstados() {
		estados.clear();
		estados.add(new SelectItem(null, "Seleccione..."));
		for(Estado estado: estadoManager.getAll()){
			estados.add(new SelectItem(estado.getIdEstado(), estado.getNombre()));
		}
		return estados;
	}
	
	public void setNombreDependencia(String nombre) {
		dependencia.setNombre(nombre);
	}
	
	public String getNombreDependencia() {
		return dependencia != null? dependencia.getNombre() : "";
	}
	
	public void setTelefonoDependencia(String telefono) {
		dependencia.setTelefono(telefono);
	}
	
	public String getTelefonoDependencia() {
		return dependencia != null? dependencia.getTelefono() : "";
	}
	
	public void setDireccionDependencia(String direccion) {
		dependencia.setDireccion(direccion);
	}
	
	public String getDireccionDependencia() {
		return dependencia != null? dependencia.getDireccion() : "";
	}
	
	public void setCpDependencia(String cp) {
		dependencia.setCp(cp);
	}
	
	public String getCpDependencia() {
		return dependencia != null? dependencia.getCp() : "";
	}
	
	public void setMunicipioDependencia(String municipio) {
		dependencia.setMunicipio(municipio);
	}
	
	public String getMunicipioDependencia() {
		return dependencia != null? dependencia.getMunicipio() : "";
	}
	
	public void setEstadoDependencia(Integer idEstado) {
		Estado estado = estadoManager.getById(idEstado);
		dependencia.setEstado(estado);
	}
	
	public Integer getEstadoDependencia() {
		return dependencia != null && dependencia.getEstado() != null? dependencia.getEstado().getIdEstado() : null;
	}
	
	public void editListener(RowEditEvent event) {
		try {
			dependenciaManager.save((Dependencia)event.getObject());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se han guardado con exito los cambios!", "Se han guardado con exito los cambios!"));
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Algo fallo al guardar!", "Algo fallo al guardar!"));
		}
	}
	
	public void deleteListener() {
		try {
			dependenciaManager.delete(dependencia);
			clearFrm();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se elimino correctamente!", "Se elimino correctamente!"));
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Algo fallo al eliminar!", "Algo fallo al eliminar!"));
		}
	}
	
	public void saveDependencia(){
		try {
			dependenciaManager.save(dependencia);
			clearFrm();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado con exito la dependencia nueva!", "Se ha guardado con exito la dependencia nueva!"));
		} catch(Exception e) {
			e.printStackTrace();			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Algo fallo al guardar!", "Algo fallo al guardar!"));
		}
	}
	
	public void clearFrm() {
		dependencia = new Dependencia();
	}
}
