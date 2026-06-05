package application;

import java.time.LocalDate;
import java.util.List;

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
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Yemek;
import yardimci.AnalizYardimcisi;
import yardimci.TemaYonetici;
import yardimci.YemekVeriMerkezi;

public class YemekRaporPenceresi extends Application {

    // === Arayüz Bileşenleri ===
    private TextArea yemekListesiAlani;              // Günlük yemeklerin listeleneceği metin kutusu
    private Label toplamKaloriEtiketi;               // Günlük toplam kaloriyi gösterecek etiket
    private DatePicker tarihSecici;                  // Kullanıcının rapor almak için tarih seçtiği alan
    private BarChart<String, Number> grafik;         // Kalorileri gösterecek çubuk grafik
    private XYChart.Series<String, Number> grafikSeri; // Grafikteki veri serisi
    private Label farkEtiketi;

    @Override
    public void start(Stage primaryStage) {
        pencereyiGoster();
    }

    // === Ana pencereyi oluşturan metod ===
    public void pencereyiGoster() {
        Stage pencere = new Stage();
        pencere.setTitle("Yemek Raporu");

        // Kayıtlı yemek verilerini dosyadan oku
        YemekVeriMerkezi.yemekleriYukle();

        //  Tarih seçici bileşeni
        tarihSecici = new DatePicker(LocalDate.now());

        //  Seçilen tarihteki yemeklerin listesi gösterilecek
        yemekListesiAlani = new TextArea();
        yemekListesiAlani.setEditable(false);
        yemekListesiAlani.setPrefHeight(200);

        //  Kalori toplamını gösteren etiket
        toplamKaloriEtiketi = new Label();
        toplamKaloriEtiketi.setStyle("-fx-font-weight: bold;");

        //  Seçilen tarihteki verileri getir
        Button guncelleBtn = new Button("Raporu Getir");
        guncelleBtn.setOnAction(e -> {
            LocalDate secilen = tarihSecici.getValue();
            raporuGuncelle(secilen); // grafiği ve listeyi güncelle
        });

        // Grafik bileşenleri
        CategoryAxis xAxis = new CategoryAxis();      // X ekseni  yemek adları
        xAxis.setLabel("Yemekler");

        NumberAxis yAxis = new NumberAxis();          // Y ekseni  kalori
        yAxis.setLabel("Kalori (kcal)");

        grafik = new BarChart<>(xAxis, yAxis);
        grafik.setTitle("Seçilen Güne Ait Kalori Grafiği");

        grafikSeri = new XYChart.Series<>();
        grafik.getData().add(grafikSeri);

        

        //  Ana düzen (VBox)
        VBox kutu = new VBox(10, tarihSecici, guncelleBtn, yemekListesiAlani, toplamKaloriEtiketi, grafik);
        kutu.setPadding(new Insets(15));
        
        
        kutu.getStyleClass().add("yemek-rapor");
        
        
        
        
        farkEtiketi = new Label();
        farkEtiketi.setStyle("-fx-font-size: 14px; -fx-text-fill: red; -fx-font-weight: bold;");
        kutu.getChildren().add(farkEtiketi);
        
     // Açılışta bugünkü verileri göster
        raporuGuncelle(LocalDate.now());

        
        
        
        
        

        //  Pencereyi hazırla ve göster
        Scene sahne = new Scene(kutu, 600, 500); // ekran genişliği biraz büyütüldü
        
        TemaYonetici.uygula(sahne);
        
        
        pencere.setScene(sahne);
        pencere.show();
    }

    // === Seçilen tarihteki yemekleri listeleyen ve grafiği güncelleyen metod ===
    private void raporuGuncelle(LocalDate tarih) {
        List<Yemek> gunlukYemekler = YemekVeriMerkezi.getYemeklerByTarih(tarih);

        StringBuilder sb = new StringBuilder(); // liste metni için
        int toplamKalori = 0; // günlük toplam kalori

        grafikSeri.getData().clear(); // grafiği temizle

        // Her yemek için metin ve grafik verisi oluştur
        for (Yemek y : gunlukYemekler) {
            sb.append("- ").append(y.getAd())
              .append(" (").append(y.getKalori()).append(" kcal)\n");

            toplamKalori += y.getKalori();

            grafikSeri.getData().add(new XYChart.Data<>(y.getAd(), y.getKalori()));
        }

        // Metin kutusuna yaz
        yemekListesiAlani.setText(sb.toString());

        // Toplam kaloriyi etikete yaz
        toplamKaloriEtiketi.setText("Toplam Kalori: " + toplamKalori + " kcal");
        
        
        
        
        int alinan = AnalizYardimcisi.gunlukToplamAlinanKalori(tarih);
        int harcanan = AnalizYardimcisi.gunlukToplamHarcananKalori(tarih);
        int fark = alinan - harcanan;

        String mesaj;
        if (fark > 0) mesaj = "Bugün " + fark + " kcal fazlalığınız var.";
        else if (fark < 0) mesaj = "Bugün " + (-fark) + " kcal açık verdiniz!";
        else mesaj = "Bugün tam dengedesiniz.";

        farkEtiketi.setText(mesaj);

        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
