package yardimci;

import javafx.scene.Scene;

public class TemaYonetici {

    private static boolean karanlikTema = false;

    public static void uygula(Scene scene) {
    	
    	
    	 scene.getStylesheets().clear();
         if (karanlikTema) {
             scene.getStylesheets().add(TemaYonetici.class.getResource("/temalar/dark-tema.css").toExternalForm());
         } else {
             scene.getStylesheets().add(TemaYonetici.class.getResource("/temalar/light-tema.css").toExternalForm());
         }
    	
    	
    	
    	
      
    }

    public static void degistir() {
        karanlikTema = !karanlikTema;
    }

    public static boolean karanlikMi() {
        return karanlikTema;
    }
}
