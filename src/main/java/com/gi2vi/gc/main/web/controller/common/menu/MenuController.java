package com.gi2vi.gc.main.web.controller.common.menu;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

import com.gi2vi.gc.main.core.util.BaseUrl;

@ManagedBean(name = "menuController")
@RequestScoped
public class MenuController {
	MenuModel menuModel;

	private final String CATALOGO_DEPENDENCIAS = "catalogo/dependencia.xhtml";

	private final HttpServletRequest request;

	public MenuController() {
		request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public MenuModel getMenu() {

		menuModel = new DefaultMenuModel();

		Submenu submenu = new Submenu();
		submenu.setIcon("ui-icon-gear");
		submenu.setLabel("Catalogos");

		MenuItem item = new MenuItem();
		item.setValue("Dependencias");
		item.setUrl(BaseUrl.getBaseURL(request, CATALOGO_DEPENDENCIAS));

		submenu.getChildren().add(item);
		menuModel.addSubmenu(submenu);

		return menuModel;
	}

}
