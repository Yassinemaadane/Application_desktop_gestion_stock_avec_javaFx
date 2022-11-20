package models;


public class client {
    Integer idc;
    String cin,nom,prenom,adresse,numc;


    public client(Integer idc, String cin, String nom, String prenom, String adresse, String numc) {
        this.idc = idc;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numc = numc;
    }

    public Integer getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumc() {
        return numc;
    }

    public void setNumc(String numc) {
        this.numc = numc;
    }
}
