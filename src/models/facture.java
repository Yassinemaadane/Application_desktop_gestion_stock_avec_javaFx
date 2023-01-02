package models;

import java.util.Date;

public class facture {
	String ref;
	String dc;
	
	String client;
	String numc;
	Float ttc;
	Float totalr;
	Float reste;
	String mode;
	String status;
	
	
	
	public facture(String ref, String dc, String client, String numc, Float ttc, Float totalr, Float reste, String mode,
			String status) {
		super();
		this.ref = ref;
		this.dc = dc;
		this.client = client;
		this.numc = numc;
		this.ttc = ttc;
		this.totalr = totalr;
		this.reste = reste;
		this.mode = mode;
		this.status = status;
	}



	public String getRef() {
		return ref;
	}



	public void setRef(String ref) {
		this.ref = ref;
	}



	public String getDc() {
		return dc;
	}



	public void setDc(String dc) {
		this.dc = dc;
	}



	public String getClient() {
		return client;
	}



	public void setClient(String client) {
		this.client = client;
	}



	public String getNumc() {
		return numc;
	}



	public void setNumc(String numc) {
		this.numc = numc;
	}



	public Float getTtc() {
		return ttc;
	}



	public void setTtc(Float ttc) {
		this.ttc = ttc;
	}



	public Float getTotalr() {
		return totalr;
	}



	public void setTotalr(Float totalr) {
		this.totalr = totalr;
	}



	public Float getReste() {
		return reste;
	}



	public void setReste(Float reste) {
		this.reste = reste;
	}



	public String getMode() {
		return mode;
	}



	public void setMode(String mode) {
		this.mode = mode;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
