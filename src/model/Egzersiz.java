package model;

import java.time.LocalDate;

public class Egzersiz {
	
	
	    private String egzersizAdi;
	    private String saat;
	    private String sure;
	    private LocalDate egzersizTarihİ;
	    private LocalDate kayitTarihi;
	    
	    public Egzersiz(String egzersizAdi,String saat,String sure,LocalDate egzersizTarihi) {
	    	
	    	this.egzersizAdi=egzersizAdi;
	    	this.saat=saat;
	    	this.sure=sure;
	    	this.egzersizTarihİ=egzersizTarihi;
	    	this.kayitTarihi=LocalDate.now();
	    	
	    	
	    }
	    
	    public String getEgzersizAdi() {
	    	return egzersizAdi;
	    }
	    public String getSaat() {
	    	return saat;
	    }
	    
	    public String getSure() {
	    	return sure;
	    }
	    
	    public LocalDate getEgzersizTarihi() {
	    	return egzersizTarihİ;
	    }
	    
	    public LocalDate getKayitTarihi() {
	        return kayitTarihi;
	    }
	    
	    
	    
	    
	    
	    @Override
	    public String toString() {
	        return egzersizTarihİ + "," + egzersizAdi + "," + sure;
	    }

	    
	
	

}
