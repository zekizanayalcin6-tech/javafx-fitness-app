package application;


import java.net.URL;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Egzersiz;
import yardimci.EgzersizVeriMerkezi;
import yardimci.TemaYonetici;

public class EgzersizEklePenceresi extends Application {

   // private static Stage geciciStage = null;// Tek bir pencerenin birden fazla açılmasını engellemek için kullanılabilecek geçici değişken
        

    @Override
    public void start(Stage primaryStage) {
        // launch() ile çağrıldığında burası çalışır
        pencereyiGoster(primaryStage);
    }

    public void pencereyiGoster() {
        // AnaUygulama dan çağrıldığında burası çalışır
        Stage pencere = new Stage();
        pencereyiGoster(pencere);
    }

    private void pencereyiGoster(Stage pencere) {
        pencere.setTitle("Egzersiz Seçimi");

        FlowPane kartlar = new FlowPane();
        kartlar.setHgap(15);  //yatay bosluk
        kartlar.setVgap(15);
        kartlar.setPadding(new Insets(10));
        kartlar.setPrefWrapLength(700);        //Pane içindeki elemanlar toplamda 700 piksel genişliği geçerse, yeni satıra geçilir.

        String[] egzersizAdlari = {"Koşu", "Bisiklet", "Yoga", "Fitness", "HIIT", "Pilates"};
        String[] resimDosyalari = {"kosuResmi.jpg", "bisikletResmi.jpg", "yogaResmi.jpg", "fitnessResmi.jpg", "HIITresmi.jpg", "pilatesResmi.jpg"};
        
     
        

        for (int i = 0; i < egzersizAdlari.length; i++) {
            VBox kart = new VBox(8);
            kart.setPadding(new Insets(10));
            kart.setAlignment(Pos.CENTER);

            Label ad = new Label(egzersizAdlari[i]);

            ImageView resim = new ImageView();
            resim.setFitWidth(180);
            resim.setFitHeight(120);
            URL url = getClass().getResource("/resimlerr/" + resimDosyalari[i]);
            if (url != null) {
                resim.setImage(new Image(url.toExternalForm()));
            }else {
				System.out.println("resim bulunamadi "+resimDosyalari[i]);
			}
            

            ComboBox<String> saatBox = new ComboBox<>();
            saatBox.getItems().addAll("08:00", "10:00", "14:00", "18:00", "20:00");
            saatBox.setValue("10:00");

            ComboBox<String> sureBox = new ComboBox<>();
            sureBox.getItems().addAll("45 dk", "1 saat", "1.5 saat", "2 saat");
            sureBox.setValue("1 saat");

            kart.setOnMouseClicked((MouseEvent e) -> {
                if (kart.getStyle().contains("yellow")) {
                    kart.setStyle("");
                } else {
                    kart.setStyle("-fx-border-color: yellow; -fx-border-width: 3px;-fx-border-radius: 10px;");
                }
            });

            kart.getChildren().addAll(ad, resim, saatBox, sureBox);
            kartlar.getChildren().add(kart);             // Ana listeye ekle
        }
        
        
        kartlar.getStyleClass().add("egzersiz-eklekart");
        
        
        
        

        ScrollPane kaydirici = new ScrollPane(kartlar);
        kaydirici.setFitToWidth(true);
        
          
        
        
        DatePicker tarihSecici=new DatePicker(LocalDate.now());

        Button kaydet = new Button("Kaydet");
        kaydet.setOnAction(e -> {
        	
        	 for (javafx.scene.Node kartNode : kartlar.getChildren()) {
        		 LocalDate secilenTarih = tarihSecici.getValue();
            	 System.out.println("Seçilen tarih: " + secilenTarih);
        	        if (kartNode instanceof VBox kart && kart.getStyle().contains("yellow")) {
        	            Label adLabel = (Label) kart.getChildren().get(0);
        	            ComboBox<String> saatBox = (ComboBox<String>) kart.getChildren().get(2);
        	            ComboBox<String> sureBox = (ComboBox<String>) kart.getChildren().get(3);

        	            String ad = adLabel.getText();
        	            String saat = saatBox.getValue();
        	            String sure = sureBox.getValue();

        	            Egzersiz egzersiz = new Egzersiz(ad, saat, sure,secilenTarih);
        	            EgzersizVeriMerkezi.egzersizEkle(egzersiz);
        	        }
        	    }
        	 
        	

        	    	 
        	 EgzersizVeriMerkezi.egzersizleriDosyayaYaz("egzersiz_kayitlari.txt");

        	
      
            System.out.println("Seçilen egzersizler kaydedildi.");
            pencere.close();
            
            
                      
            
        });
        
        
        
             

        VBox ana = new VBox(10,tarihSecici, kaydirici, kaydet);
        ana.setPadding(new Insets(10));
        ana.setAlignment(Pos.CENTER);
        
        
        
        

     //  Stil sınıfı ekleniyor
     ana.getStyleClass().add("egzersiz-ekle");        
        
        
        

        Scene sahne = new Scene(ana, 800, 600);
        
        TemaYonetici.uygula(sahne);

        
        pencere.setScene(sahne);
        pencere.show();
    }

    public static void main(String[] args) {
        launch(args); // Bağımsız olarak başlatma
    }
}


