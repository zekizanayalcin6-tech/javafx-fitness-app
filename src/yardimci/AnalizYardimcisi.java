package yardimci;

import model.Egzersiz;

import model.Yemek;

import java.time.LocalDate;
import java.util.List;

public class AnalizYardimcisi {

    /**
     * Belirli bir tarihte yapılan egzersizlere göre toplam kalori harcamasını hesaplar.
     * @param tarih Seçilen gün
     * @return Harcanan toplam kalori (int)
     */
	
	public static int gunlukToplamAlinanKalori(LocalDate tarih) {
        List<Yemek> yemekler = YemekVeriMerkezi.getYemeklerByTarih(tarih);
        int toplam = 0;
        for (Yemek y : yemekler) {
            toplam += y.getKalori();
        }
        return toplam;
	}
        
        
        
        
    public static int gunlukToplamHarcananKalori(LocalDate tarih) {
        List<Egzersiz> tumEgzersizler = EgzersizVeriMerkezi.getTumEgzersizler();
        int toplamKalori = 0;

        for (Egzersiz e : tumEgzersizler) {
            if (e.getEgzersizTarihi().equals(tarih)) {
                int dakika = sureyiDakikayaCevir(e.getSure());
                toplamKalori += dakika * 5; // Ortalama 5 kcal/dk yakım
            }
        }

        return toplamKalori;
    }

    // Süreyi dakikaya çeviren yardımcı metod
    private static int sureyiDakikayaCevir(String sure) {
        sure = sure.toLowerCase();
        if (sure.contains("1.5")) return 90;
        if (sure.contains("2")) return 120;
        if (sure.contains("1")) return 60;
        if (sure.contains("45")) return 45;
        if (sure.contains("30")) return 30;
        return 0;
    }
}

