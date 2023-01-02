package models;

public class Singleton {

	    private static Singleton instance;
	  private  boolean etat=false;

	    private Singleton() {}

	    public static Singleton getInstance() {
	        if (instance == null) {
	            instance = new Singleton();
	        }
	        return instance;
	    }

		public boolean isEtat() {
			return etat;
		}

		public void setEtat(boolean etat) {
			this.etat = etat;
		}

	   

	 
	}