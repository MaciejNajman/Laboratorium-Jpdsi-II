package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String kwota;
	private String okres;
	private String oprocentowanie;
	private Double rata;

	@Inject
	FacesContext ctx;

	public String getKwota() {
		return kwota;
	}


	public void setKwota(String kwota) {
		this.kwota = kwota;
	}


	public String getOkres() {
		return okres;
	}


	public void setOkres(String okres) {
		this.okres = okres;
	}


	public String getOprocentowanie() {
		return oprocentowanie;
	}


	public void setOprocentowanie(String oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}


	public Double getRata() {
		return rata;
	}


	public void setRata(Double rata) {
		this.rata = rata;
	}


	// Go to "showresult" if ok
	public String calc() {
		try {
			//obliczenia
			double kwota = Double.parseDouble(this.kwota);
			double okres = Double.parseDouble(this.okres);
			double oprocentowanie = Double.parseDouble(this.oprocentowanie);
			
			rata = (kwota + (kwota * (oprocentowanie / 100))) / okres;
			rata = (double)Math.round(rata * 100d) / 100d;
			return "showresult";
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return "index";
		}
	}

}
