package model;

import java.time.LocalDate;

public class Yemek {
    private String ad;
    private int kalori;
    private LocalDate tarih;
    private LocalDate kayitTarihi;


    public Yemek(String ad, int kalori) {
        this.ad = ad;
        this.kalori = kalori;
        this.tarih = LocalDate.now(); // otomatik olarak bugünün tarihi
        this.kayitTarihi = LocalDate.now(); // <-- yeni
    }
    
    
    // Tarihi dışarıdan alabilen constructor
    public Yemek(String ad, int kalori, LocalDate tarih) {
        this.ad = ad;
        this.kalori = kalori;
        this.tarih = tarih;
        this.kayitTarihi = LocalDate.now(); // <-- yeni
    }
    
    
    

    public String getAd() {
        return ad;
    }

    public int getKalori() {
        return kalori;
    }

    public LocalDate getTarih() {
        return tarih;
    }
    public LocalDate getKayitTarihi() {
        return kayitTarihi;
    }


    @Override
    public String toString() {
        return tarih + "," + ad + "," + kalori;
    }
}

