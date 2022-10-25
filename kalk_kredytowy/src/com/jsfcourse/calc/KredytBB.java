package com.jsfcourse.calc;

import javax.inject.Inject;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import static java.lang.Math.pow;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private Double kwota;
	private Integer okres;
	private Double oprocentowanie;
	private Double rata;

	@Inject
	FacesContext ctx;

	public Double getKwota() {
		return kwota;
	}


	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}


	public Integer getOkres() {
		return okres;
	}


	public void setOkres(Integer okres) {
		this.okres = okres;
	}


	public Double getOprocentowanie() {
		return oprocentowanie;
	}


	public void setOprocentowanie(Double oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}


	public Double getRata() {
		return rata;
	}
	
	public boolean doTheMath() {
		try {
			
			rata = (kwota * ((oprocentowanie / 100) / 12) * (pow(1 + ((oprocentowanie / 100) / 12), okres)) / (pow(1 + ((oprocentowanie / 100) / 12), okres) - 1));
			rata = (double)Math.round(rata * 100d) / 100d;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}
	
	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}
	
	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rata wyniesie: " + rata, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
