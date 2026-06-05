package application;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Yemek;
import yardimci.TemaYonetici;
import yardimci.YemekVeriMerkezi;



public class YemekEklePenceresi extends Application {

    @Override
    public void start(Stage primaryStage) {
        pencereyiGoster(primaryStage);
    }

    public void pencereyiGoster() {
        Stage pencere = new Stage();
        pencereyiGoster(pencere);
    }

    private void pencereyiGoster(Stage pencere) {
        pencere.setTitle("Yemek Ekle");
        
        
        
        // 📅 Tarih seçici
        DatePicker tarihSecici = new DatePicker(LocalDate.now());

        
        

        // --- 1. YEMEK ---
        Label yemek1 = new Label("Yemek Adı:");
        TextField yemek1Adi = new TextField();

        Label kaloriYemek1 = new Label("Kalori:");
        TextField kaloriYemek1Degeri = new TextField();

        Button kaydet1 = new Button("Kaydet");
        kaydet1.setOnAction(e -> {
            String yemekAdi1 = yemek1Adi.getText().trim();
            String kaloriText = kaloriYemek1Degeri.getText().trim();
            try {
                int kalori = Integer.parseInt(kaloriText);
                
                LocalDate secilenTarih = tarihSecici.getValue(); // Tarih alındı
                
                Yemek yeniYemek = new Yemek(yemekAdi1, kalori);
                YemekVeriMerkezi.yemekEkle(yeniYemek);
                System.out.println("Yemek kaydedildi: " + yemekAdi1 + ", " + kalori + " kcal");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Kayıt Başarılı");
                alert.setHeaderText(null);
                alert.setContentText("Yemek başarıyla kaydedildi.");
                alert.showAndWait();
            } catch (NumberFormatException e2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Geçersiz Giriş");
                alert.setHeaderText("Kalori değeri hatalı!");
                alert.setContentText("Sayısal bir kalori değeri girin.");
                alert.showAndWait();
            }
        });

        VBox root = new VBox(10, yemek1, yemek1Adi, kaloriYemek1, kaloriYemek1Degeri, kaydet1);
        root.setPadding(new Insets(20));

        // --- 2. YEMEK ---
        Label yemek2 = new Label("Yemek Adı:");
        TextField yemek2Adi = new TextField();

        Label kaloriYemek2 = new Label("Kalori:");
        TextField kaloriYemek2Degeri = new TextField();

        Button kaydet2 = new Button("Kaydet");
        kaydet2.setOnAction(e -> {
            String yemekAdi2 = yemek2Adi.getText().trim();
            String kaloriText = kaloriYemek2Degeri.getText().trim();
            try {
                int kalori = Integer.parseInt(kaloriText);
                
                LocalDate secilenTarih = tarihSecici.getValue(); // 📅
                
                Yemek yeniYemek = new Yemek(yemekAdi2, kalori);
                YemekVeriMerkezi.yemekEkle(yeniYemek);
                System.out.println("Yemek kaydedildi: " + yemekAdi2 + ", " + kalori + " kcal");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Kayıt Başarılı");
                alert.setHeaderText(null);
                alert.setContentText("Yemek başarıyla kaydedildi.");
                alert.showAndWait();
            } catch (NumberFormatException e2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Geçersiz Giriş");
                alert.setHeaderText("Kalori değeri hatalı!");
                alert.setContentText("Sayısal bir kalori değeri girin.");
                alert.showAndWait();
            }
        });

        VBox root2 = new VBox(10, yemek2, yemek2Adi, kaloriYemek2, kaloriYemek2Degeri, kaydet2);
        root2.setPadding(new Insets(20));

        // --- 3. YEMEK ---
        Label yemek3 = new Label("Yemek Adı:");
        TextField yemek3Adi = new TextField();

        Label kaloriYemek3 = new Label("Kalori:");
        TextField kaloriYemek3Degeri = new TextField();

        Button kaydet3 = new Button("Kaydet");
        kaydet3.setOnAction(e -> {
            String yemekAdi3 = yemek3Adi.getText().trim();
            String kaloriText = kaloriYemek3Degeri.getText().trim();
            try {
                int kalori = Integer.parseInt(kaloriText);
                
                LocalDate secilenTarih = tarihSecici.getValue(); // 📅
                
                Yemek yeniYemek = new Yemek(yemekAdi3, kalori);
                YemekVeriMerkezi.yemekEkle(yeniYemek);
                System.out.println("Yemek kaydedildi: " + yemekAdi3 + ", " + kalori + " kcal");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Kayıt Başarılı");
                alert.setHeaderText(null);
                alert.setContentText("Yemek başarıyla kaydedildi.");
                alert.showAndWait();
            } catch (NumberFormatException e2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Geçersiz Giriş");
                alert.setHeaderText("Kalori değeri hatalı!");
                alert.setContentText("Sayısal bir kalori değeri girin.");
                alert.showAndWait();
            }
        });

        VBox root3 = new VBox(10, yemek3, yemek3Adi, kaloriYemek3, kaloriYemek3Degeri, kaydet3);
        root3.setPadding(new Insets(20));

        // === Pencereyi Kapat Butonu ===
        Button pencereyiKapat = new Button("Pencereyi Kapat");
        pencereyiKapat.setOnAction(e -> pencere.close());

        HBox butonKutusu = new HBox(pencereyiKapat);
        butonKutusu.setAlignment(Pos.CENTER);
        butonKutusu.setPadding(new Insets(10));

        // === Tüm bölümleri birleştirme ===
        VBox anaRoot = new VBox(30);
        anaRoot.getChildren().addAll(root, root2, root3, butonKutusu);
        
        
        
        
        
        anaRoot.getStyleClass().add("yemek-ekle");
        
        
        
        

        ScrollPane scrollpane = new ScrollPane(anaRoot);
        scrollpane.setFitToWidth(true);

        Scene sahne = new Scene(scrollpane, 300, 250);
        TemaYonetici.uygula(sahne);
        pencere.setScene(sahne);
        pencere.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
