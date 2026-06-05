package application;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;             // Sahne (ekranda gösterilecek içerik)
import javafx.scene.control.Button;    // Buton bileşeni
import javafx.scene.control.Label;     // Etiket bileşeni
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;       // Dikey kutu yerleşimi (bileşenleri dikey dizer)
import javafx.stage.Stage;             // Uygulamanın penceresi (ana sahne)
import yardimci.EgzersizVeriMerkezi;
import yardimci.TemaYonetici;


public class AnaUygulama extends Application {  
 
  
	 private VBox root;
	 private boolean everest1Aktif = true; // Başlangıçta everest.jpg aktif
	 
    @Override
    public void start(Stage primaryStage) {
    	
         
        // === 1. BAŞLIK ===
        // Uygulama başlığı olarak ekranda görünecek bir Label oluşturulur
        Label baslik = new Label("Kişisel Sağlık ve Fitness Takip Uygulaması");
        
        EgzersizVeriMerkezi.egzersizleriDosyadanYukle("egzersiz_kayitlari.txt");

        

        
        Button egzersizEkle = new Button("Egzersiz Ekle");
        Button yemekEkle = new Button("Yemek Ekle");
        Button raporGoruntule = new Button("Rapor Görüntüle");
        Button yemekRaporu = new Button("Yemek Raporu");

        // Her butonun tıklanma olayına tepki olarak bir mesaj yazdırılır
        egzersizEkle.setOnAction(e -> {
        	// Yeni pencere sınıfından bir nesne oluşturuluyor
        	EgzersizEklePenceresi pencere=new EgzersizEklePenceresi();
        	pencere.pencereyiGoster();
        	System.out.println("Egzersiz ekleme ekranı açılıyor...");
        });
        
        yemekEkle.setOnAction(e -> {
        	YemekEklePenceresi pencere=new YemekEklePenceresi();
        	pencere.pencereyiGoster();
        	System.out.println("Yemek ekleme ekranı açılıyor...");
        });
        
        
        raporGoruntule.setOnAction(e -> {
        	RaporGoruntulePenceresi pencere=new RaporGoruntulePenceresi();
        	pencere.pencereyiGoster();
        	System.out.println("Raporlar görüntüleniyor...");	
        });
        
        yemekRaporu.setOnAction(e -> {
        	YemekRaporPenceresi pencere=new YemekRaporPenceresi();
        	pencere.pencereyiGoster();
        	System.out.println("yemek raporu goruntuleniyor");

        });
        
        
        
        root=new VBox(15);
        
        
        
        Button temaDegistirBtn=new Button("temayi degistir");
        
        temaDegistirBtn.setOnAction(e->{
        	       	
        	 try {
        		 
        		 String resimAdi = everest1Aktif ? "/everestTT/everest2.jpg" : "/everestTT/everest.jpg";

        		 
        		 
                 URL imageUrl = getClass().getResource(resimAdi);    //resim dosyasını yukle
                 if (imageUrl == null) {
                     System.err.println("❌ Resim bulunamadı! "+resimAdi);
                     return;
                 }

                 Image image = new Image(imageUrl.toExternalForm());    //resim nesnesi olusturma
                 
                 BackgroundImage backgroundImage = new BackgroundImage(
                     image,
                     BackgroundRepeat.NO_REPEAT,
                     BackgroundRepeat.NO_REPEAT,
                     BackgroundPosition.CENTER,
                     new BackgroundSize(100, 100, true, true, true, true)
                 );

                 root.setBackground(new Background(backgroundImage));
                 
                 // Bayrağı tersine çevir → Diğerine geç
                 everest1Aktif = !everest1Aktif;           //bir dahaki tıklamada diger resmi ac

                 
                 
             } catch (Exception ex) {
                 ex.printStackTrace();      // Hata detaylarını ekrana yazdırır
             }

        	
        	
        	
        	
        	TemaYonetici.degistir();
        	TemaYonetici.uygula(primaryStage.getScene());
        });
        
        
        
        
        

        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        
       
       

        
        try {
            URL imageUrl = getClass().getResource("/everestTT/everest.jpg");
            if (imageUrl == null) {
                System.err.println("❌ Resim bulunamadı! ");
                return;
            }

            Image image = new Image(imageUrl.toExternalForm());
            System.out.println("✅ Resim yüklendi: " + image.getWidth() + "x" + image.getHeight());

            BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
            );

            root.setBackground(new Background(backgroundImage));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
        
        
        
        
      

        // VBox'a sırayla başlık ve butonlar eklenir
        root.getChildren().addAll(baslik, egzersizEkle, yemekEkle, raporGoruntule,yemekRaporu,temaDegistirBtn);
        
       
        

      
        // Yeni bir sahne (Scene) oluşturuluyor, içine root (VBox) yerleştirilir
        Scene scene = new Scene(root, 400, 400); // 400x300 piksel pencere
        
        
        
        
        //scene.getStylesheets().add(getClass().getResource("/temalar/dark-tema.css").toExternalForm());
      
        
       
        TemaYonetici.uygula(scene);

       
        primaryStage.setTitle("Sağlık ve Fitness Takip Uygulaması");

   
        primaryStage.setScene(scene);

        
        primaryStage.show();
    }
    
    
    
 
   
    
    

  
    public static void main(String[] args) {
        launch(args);
    }
}
