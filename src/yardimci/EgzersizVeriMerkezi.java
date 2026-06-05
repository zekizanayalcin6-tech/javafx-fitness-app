package yardimci;

import java.util.ArrayList;

import java.util.List;
import model.Egzersiz; // Eğer modeldeyse bu şekilde import edilir

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Bu sınıf, tüm egzersiz kayıtlarını hafızada saklamak için merkezi bir yapıdır.
 * Diğer sınıflar bu sınıfı kullanarak egzersiz ekleyebilir veya tüm kayıtları alabilir.
 */
public class EgzersizVeriMerkezi {

    // Egzersiz kayıtlarını tutan statik liste
    private static final List<Egzersiz> egzersizListesi = new ArrayList<>();

    // Yeni egzersiz kaydı eklemek için metod
    public static void egzersizEkle(Egzersiz egzersiz) {
        egzersizListesi.add(egzersiz);
    }

    // Tüm kayıtları liste olarak döndürür
    public static List<Egzersiz> getTumEgzersizler() {
        return new ArrayList<>(egzersizListesi); // Orijinal listeyi dışarı vermiyoruz
    }

    // Tüm kayıtları siler (rapor temizleme gibi işlemler için)
    public static void temizle() {
        egzersizListesi.clear();
    }
    
    public static void egzersizleriDosyayaYaz(String dosyaAdi) {
        try (FileWriter yazici = new FileWriter(dosyaAdi)) {
            for (Egzersiz egzersiz : egzersizListesi) {
                yazici.write(egzersiz.toString() + "\n");
            }
            System.out.println("Dosyaya yazıldı: " + dosyaAdi);
        } catch (IOException e) {
            System.err.println("Yazma hatası: " + e.getMessage());
        }
    }
    
    public static void egzersizleriDosyadanYukle(String dosyaAdi) {
    	 egzersizListesi.clear(); // Her yüklemede listeyi temizle
    	    try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaAdi))) {
    	        String satir;
    	        while ((satir = okuyucu.readLine()) != null) {
    	            String[] parcalar = satir.split(",");
    	            if (parcalar.length == 3) {
    	                LocalDate tarih = LocalDate.parse(parcalar[0].trim());
    	                String ad = parcalar[1].trim();
    	                String sure = parcalar[2].trim();
    	                Egzersiz e = new Egzersiz(ad, "", sure, tarih); // saat boş bırakılıyor çünkü dosyada yok
    	                egzersizListesi.add(e);
    	            }
    	        }
    	        System.out.println("Dosyadan veriler yüklendi: " + dosyaAdi);
    	    } catch (Exception e) {
    	        System.err.println("Dosyadan okuma hatası: " + e.getMessage());
    	    }
    }
    
    
    
    
    
}

