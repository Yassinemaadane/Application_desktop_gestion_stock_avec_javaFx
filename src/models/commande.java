package models;

public class commande {
String refA,des;
Float pht,mht,mtva,mr,mttc;
Integer qte;
public commande(String refA, String des, Float pht, Float mht, Float mtva, Float mr, Float mttc, Integer qte) {
	super();
	this.refA = refA;
	this.des = des;
	this.pht = pht;
	this.mht = mht;
	this.mtva = mtva;
	this.mr = mr;
	this.mttc = mttc;
	this.qte = qte;
}
public String getRefA() {
	return refA;
}
public void setRefA(String refA) {
	this.refA = refA;
}
public String getDes() {
	return des;
}
public void setDes(String des) {
	this.des = des;
}
public Float getPht() {
	return pht;
}
public void setPht(Float pht) {
	this.pht = pht;
}
public Float getMht() {
	return mht;
}
public void setMht(Float mht) {
	this.mht = mht;
}
public Float getMtva() {
	return mtva;
}
public void setMtva(Float mtva) {
	this.mtva = mtva;
}
public Float getMr() {
	return mr;
}
public void setMr(Float mr) {
	this.mr = mr;
}
public Float getMttc() {
	return mttc;
}
public void setMttc(Float mttc) {
	this.mttc = mttc;
}
public Integer getQte() {
	return qte;
}
public void setQte(Integer qte) {
	this.qte = qte;
}

}
