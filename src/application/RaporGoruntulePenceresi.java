package application;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Egzersiz;
import yardimci.EgzersizVeriMerkezi;
import yardimci.TemaYonetici;

public class RaporGoruntulePenceresi extends Application {

    private BarChart<String, Number> grafik;         // Grafik bileşeni
    private XYChart.Series<String, Number> seri;     // Veri serisi
    private DatePicker tarihSecici;                 // Tarih seçici
    private Label toplamSureEtiketi;                // Toplam süre etiketi

    @Override
    public void start(Stage primaryStage) {
        pencereyiGoster();
    }

    public void pencereyiGoster() {
        Stage pencere = new Stage();
        pencere.setTitle("Tarihe Göre Egzersiz Raporu");

        //  Verileri yükle
        EgzersizVeriMerkezi.egzersizleriDosyadanYukle("egzersiz_kayitlari.txt");

        // Tarih seçici
        tarihSecici = new DatePicker(LocalDate.now());

        //  Grafik eksenleri
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Egzersiz Türü"); 

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Yapılma Sayısı");

        grafik = new BarChart<>(xAxis, yAxis);
        grafik.setTitle("Egzersiz Sayısı");
        seri = new XYChart.Series<>();
        grafik.getData().add(seri);

        //  Toplam süre etiketi
        toplamSureEtiketi = new Label();
        toplamSureEtiketi.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Buton  Grafiği ve süreyi güncell
        Button guncelleBtn = new Button("Güncelle");
        guncelleBtn.setOnAction(e -> {
            LocalDate secilen = tarihSecici.getValue();
            grafikVerisiniGuncelle(secilen);
        });

        // Başlangıçta bugünün verileri
        grafikVerisiniGuncelle(LocalDate.now());

        // Görsel düzenleme
        VBox kutu = new VBox(10, tarihSecici, guncelleBtn, grafik, toplamSureEtiketi);
        kutu.setPadding(new Insets(20));
        
        
        
        
        kutu.getStyleClass().add("rapor-goruntule");
        
        

        Scene sahne = new Scene(kutu, 700, 500);
        
        TemaYonetici.uygula(sahne);

        
        pencere.setScene(sahne);
        pencere.show();
    }

    /**
     * Seçilen tarihteki egzersizlerin sayısını ve toplam süresini gösterir.
     */
    private void grafikVerisiniGuncelle(LocalDate tarih) {
        seri.getData().clear(); // Grafiği sıfırla

        Map<String, Integer> sayac = new HashMap<>();
        int toplamDakika = 0;

        List<Egzersiz> liste = EgzersizVeriMerkezi.getTumEgzersizler();
        for (Egzersiz e : liste) {
            if (e.getEgzersizTarihi().equals(tarih)) {
                // Sayıları say
                sayac.put(e.getEgzersizAdi(), sayac.getOrDefault(e.getEgzersizAdi(), 0) + 1);

                // Süreyi dakikaya çevir
                toplamDakika += sureyiDakikayaCevir(e.getSure());
            }
        }

        // Çubuklara veri ekle
        for (String ad : sayac.keySet()) {
            seri.getData().add(new XYChart.Data<>(ad, sayac.get(ad)));
        }

        // Toplam süre etiketi güncelle
        int saat = toplamDakika / 60;
        int dakika = toplamDakika % 60;
        toplamSureEtiketi.setText("Toplam Egzersiz Süresi: " + saat + " saat " + dakika + " dakika");
    }

    /**
     * "1 saat", "1.5 saat", "45 dk" gibi ifadeleri dakikaya çevirir.
     */
    private int sureyiDakikayaCevir(String sure) {
        sure = sure.toLowerCase();
        if (sure.contains("dk")) {
            return Integer.parseInt(sure.replaceAll("[^0-9]", ""));
        } else if (sure.contains("1.5")) {
            return 90;
        } else if (sure.contains("2")) {
            return 120;
        } else if (sure.contains("1")) {
            return 60;
        }
        return 0; // tanınmazsa sıfır
    }

    public static void main(String[] args) {
        launch(args);
    }
}
