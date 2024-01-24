# Beymen Test Projesi
Bu proje Beymen alışveriş sitesinde otomasyon testlerini içeren bir Selenium WebDriver projesidir. Projeye ilişkin detaylı bilgiler ve kullanım talimatları aşağıda yer almaktadır.

## Proje Yapısı

Proje aşağıdaki paket ve sınıflardan oluşmaktadır:

- **builders:** Selenium WebDriver'ı başlatmak ve yapılandırmak için DriverBuilds sınıfı.

- **models:** Testlerde kullanılacak veri modellerini içeren 'Number' ve 'Product' sınıfları.

- **pages:** Web sayfalarının elemanlarını ve bu sayfalarda gerçekleştirilen işlemleri içeren sayfa sınıfları bulunmaktadır. `BasketPage`, `CommonPage`, `HomePage`, `SearchPage` ve sınıfları sayfaya özgü işlemleri içerir.

- **test:** BeymenTest sınıfı test senaryolarını içerir. Bu sınıf, tüm alışveriş sürecini simüle eden bir test senaryosu içerir, CommonTest before ve after metodlarını içerir.

- **utils:** Bu bir yardımcı program sınıfları paketidir.
- - `ConfigReader` sınıfı, yapılandırma dosyasından (configuration.properties) verileri okur.
- - `ScereenShotsUtils` ekran görüntüsü almaya yönelik işlemleri içerir.
- - `LoggerUtil` sınıfı, loglama işlemlerini gerçekleştirir.



## Kullanım

### Bağımlılıkları Yükleme:
Projenin çalışması için gerekli bağımlılıklar kurulmalıdır. Bu bağımlılıklar pom.xml dosyasında belirtilir.


### Test Senaryosunu Çalıştırma:
BeymenTest sınıfını çalıştırarak test senaryosunu başlatabilirsiniz. Testin başarılı olup olmadığını görmek için log ve ekran görüntülerini kontrol edebilirsiniz.

### Ekran görüntüleri
Ekran görüntüleri, ilgili adımlarla birlikte "Ekran Görüntüleri" klasörüne kaydedilir. Her adımda alınan ekran görüntülerini inceleyerek testin doğru çalışıp çalışmadığını kontrol edebilirsiniz.

### Logs
Loglar `src/test/logs/mylog.log` dosyasına kaydedilir. Bu log dosyasını inceleyerek test adımlarının durumunu izleyebilirsiniz.

### Yapılandırma Dosyası:
'src/configuration.properties' dosyası proje yapılandırma ayarlarını içerir. Bu dosyadan testin çalıştırılması için başlangıç URL'sini ve diğer yapılandırma ayarlarını düzenleyebilirsiniz.


## Notlar:
Testlerin çalışabilmesi için ChromeDriver'ın yüklemenize gerek yoktur. Bonigarcia kütüphanesi bizim yerimize en uygun sürümü test başlamadan yükleyecektir.
