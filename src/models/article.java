package models;

public class article {
public String ref;
 public String des;
 public String Nom;
 public Float pa,pv,valeur;
 public String libelle;
 Boolean enstock;
 Integer qte;
public article(String ref, String des,  Float pa, Float pv, String libelle,String Nom, Boolean enstock,Integer qte,Float valeur) {
	super();
	this.ref = ref;
	this.des = des;
	this.Nom = Nom;
	this.pa = pa;
	this.pv = pv;
	this.valeur = valeur;
	this.libelle = libelle;
	this.enstock = enstock;
	this.qte = qte;
}
public Float getValeur() {
	return valeur;
}
public void setValeur(Float valeur) {
	this.valeur = valeur;
}
public Boolean getEnstock() {
	return enstock;
}
public void setEnstock(Boolean enstock) {
	this.enstock = enstock;
}
public Integer getQte() {
	return qte;
}
public void setQte(Integer qte) {
	this.qte = qte;
}
public article() {
	super();
}
public String getRef() {
	return ref;
}
public void setRef(String ref) {
	this.ref = ref;
}
public String getDes() {
	return des;
}
public void setDes(String des) {
	this.des = des;
}
public String getNom() {
	return Nom;
}
public void setNom(String Nom) {
	this.Nom = Nom;
}
public Float getPa() {
	return pa;
}
public void setPa(Float pa) {
	this.pa = pa;
}
public Float getPv() {
	return pv;
}
public void setPv(Float pv) {
	this.pv = pv;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}


}
