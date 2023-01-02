package models;

import java.util.Date;

public class cheque {
	
	String ref;
	Date datec;
	Date datee;
	String nclient;
	Integer numc;
	String banque;
	Float mtch,mtr;
	public cheque(String ref, Date datec, Date datee, String nclient, Integer numc, String banque, Float mtch,
			Float mtr) {
		super();
		this.ref = ref;
		this.datec = datec;
		this.datee = datee;
		this.nclient = nclient;
		this.numc = numc;
		this.banque = banque;
		this.mtch = mtch;
		this.mtr = mtr;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public Date getDatec() {
		return datec;
	}
	public void setDatec(Date datec) {
		this.datec = datec;
	}
	public Date getDatee() {
		return datee;
	}
	public void setDatee(Date datee) {
		this.datee = datee;
	}
	public String getNclient() {
		return nclient;
	}
	public void setNclient(String nclient) {
		this.nclient = nclient;
	}
	public Integer getNumc() {
		return numc;
	}
	public void setNumc(Integer numc) {
		this.numc = numc;
	}
	public String getBanque() {
		return banque;
	}
	public void setBanque(String banque) {
		this.banque = banque;
	}
	public Float getMtch() {
		return mtch;
	}
	public void setMtch(Float mtch) {
		this.mtch = mtch;
	}
	public Float getMtr() {
		return mtr;
	}
	public void setMtr(Float mtr) {
		this.mtr = mtr;
	}
	
	

}
