# Kişisel Sağlık ve Fitness Takip Uygulaması 

Java ve JavaFX kullanılarak geliştirilmiş, kullanıcıların günlük egzersiz ve beslenme (kalori) verilerini takip edip görsel raporlar alabildiği masaüstü (GUI) uygulamasıdır. Proje, Nesne Yönelimli Programlama (OOP) prensiplerine ve katmanlı mimariye uygun olarak tasarlanmıştır.

## 🚀 Özellikler
* **Egzersiz Takibi:** Yapılan egzersizlerin (Koşu, Yoga, HIIT vb.) tarih, süre ve saat bilgileriyle sisteme eklenmesi.
* **Beslenme ve Kalori Takibi:** Günlük tüketilen öğünlerin ve alınan kalorilerin kaydedilmesi.
* **Görsel Raporlama ve Analiz:** JavaFX BarChart kullanılarak günlük kalori alımı/harcaması hesaplamaları ve egzersiz dağılımlarının grafiksel analizi.
* **Veri Kalıcılığı:** Veritabanı simülasyonu olarak `.txt` dosyaları (File I/O) üzerinden dinamik veri okuma ve yazma işlemleri.
* **Tema Desteği:** Kullanıcı deneyimini artırmak için dinamik CSS tabanlı Aydınlık/Karanlık (Light/Dark) tema geçişi.

## 🛠️ Kullanılan Teknolojiler
* **Programlama Dili:** Java
* **Arayüz (GUI):** JavaFX, CSS
* **Veri Yönetimi:** Dosya İşlemleri (File I/O)
* **Geliştirme Ortamı:** Eclipse IDE

## 📂 Proje Mimarisi
Projenin sürdürülebilir olması adına kodlar, görevlerine göre paketlere ayrılmıştır:
* `model`: Temel veri yapıları (Egzersiz, Yemek nesneleri).
* `yardimci`: Veri işleme merkezleri, analiz algoritmaları ve tema yönetimi.
* `application`: Ana uygulama döngüsü ve arayüz (Pencere) sınıfları.
