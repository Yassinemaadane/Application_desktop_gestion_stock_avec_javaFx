package models;

public class produitFacture {
	String des;
	Float prix;
	Integer qte;
	Float tottc;
	Float remise,Tva;
	public produitFacture(String des, Float prix, Integer qte,Float tottc,Float remise,Float Tva) {
		super();
		this.des = des;
		this.prix = prix;
		this.qte = qte;
		this.tottc = tottc;
		this.remise = remise;
		this.Tva = Tva;
		
	}
	public Float getTottc() {
		return tottc;
	}
	public Float getRemise() {
		return remise;
	}
	public void setRemise(Float remise) {
		this.remise = remise;
	}
	public Float getTva() {
		return Tva;
	}
	public void setTva(Float tva) {
		Tva = tva;
	}
	public void setTottc(Float tottc) {
		this.tottc = tottc;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Float getPrix() {
		return prix;
	}
	public void setPrix(Float prix) {
		this.prix = prix;
	}
	public Integer getQte() {
		return qte;
	}
	public void setQte(Integer qte) {
		this.qte = qte;
	}
	

}
