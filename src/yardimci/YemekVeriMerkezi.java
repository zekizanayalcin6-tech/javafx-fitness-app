package yardimci;

import model.Yemek;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

import java.time.LocalDate;


public class YemekVeriMerkezi {
    private static final String DOSYA_ADI = "yemek_kayitlari.txt";
    private static List<Yemek> yemekListesi = new ArrayList<>();

    public static void yemekEkle(Yemek yemek) {
        yemekListesi.add(yemek);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DOSYA_ADI, true))) {
            bw.write(yemek.toString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Dosyaya yazma hatası: " + e.getMessage());
        }
    }

    public static void yemekleriYukle() {
        yemekListesi.clear();
        try (Scanner sc = new Scanner(new File(DOSYA_ADI))) {
            while (sc.hasNextLine()) {
                String satir = sc.nextLine();
                String[] parcalar = satir.split(",");
                if (parcalar.length == 3) {
                	 LocalDate tarih = LocalDate.parse(parcalar[0]); // ilk parça tarih
                     String ad = parcalar[1];
                     int kalori = Integer.parseInt(parcalar[2]);
                     Yemek y = new Yemek(ad, kalori, tarih);
                     yemekListesi.add(y);
                }
            }
        } catch (Exception e) {
            System.err.println("Dosya okuma hatası: " + e.getMessage());
        }
    }

    public static List<Yemek> getTumYemekler() {
        return yemekListesi;
    }
    public static List<Yemek> getYemeklerByTarih(LocalDate tarih) {
        List<Yemek> sonuc = new ArrayList<>();
        for (Yemek y : yemekListesi) {
            if (y.getTarih().equals(tarih)) {
                sonuc.add(y);
            }
        }
        return sonuc;
    }
    
    
    public static Map<LocalDate, Integer> getGunlukKaloriToplamlari(){
    	
    	Map<LocalDate,Integer> gunlukKaloriler= new HashMap<>();
    	
    	for(Yemek y:yemekListesi) {
    		LocalDate tarih=y.getTarih();
    		int mevcut=gunlukKaloriler.getOrDefault(tarih, 0);
    		gunlukKaloriler.put(tarih, mevcut+y.getKalori());
    	}
    	return gunlukKaloriler;
    			
    }
    
    
    
    

}
