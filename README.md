# DESAIN DAN PEMROGRAMAN BERORIENTASI OBJEK 
```
Mata Kuliah Pemrograman Berorientasi Objek (IK290).
Mata Kuliah Wajib Kurikulum (MKWK) Semester Genap.
Fakultas Pendidikan Matematika dan Ilmu Pengetahuan Alam.
Departement Of Computer Science Education. 
Program Studi Ilmu Komputer.

(2658) ROSA ARIANI SUKAMTO, S.T., M.T.
       NIP. 19810918 200912 2 003
       Penata III/c
       Lektor
```
> IBNU ADENG KURNIA - 2101769 - KOM4C2 - © 2023 Univ. Pendidikan Indonesia

## Pert-12. Software Architectural Pattern: MVVM – Model-View-ViewModel

*Repositori ini dibuat sebagai dokumentasi fortofolio pengerjaan Latihan Praktikum 12 (LP12), kemudian setelah selesai pengerjaan ini mahasiswa diharapkan :*
```
a. Mahasiswa mampu memahami konsep Object Oriented Programming (OOP) digunakan untuk penyelesaian permasalahan-permasalahan yang ada.
b. Mahasiswa mampu memahami perbedaan antara Class dan object, implementasi class dan object, method dalam berbagai bahasa (C++, java, PHP, pyhton) serta type data nya.
c. Kasus-kasus implementasi Graphical User Interface (GUI) dengan menggunakan MVVM. 
d. Mahasiswa mampu memahami konsep Graphical User Interface (GUI) dalam Pemrograman Berorientasi Objek.
e. Mahasiswa mampu memahami konsep Graphical User Interface (GUI) MVVM.
```

> Saya Ibnu Adeng Kurnia NIM 2101769 mengerjakan Latihan Praktikum 12 (LP12) dalam mata kuliah Desain dan Pemrograman Berorientasi Objek C2 2023
	untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. 
	Aamiin.

*Design ini disusun dan/atau digunakan hanya untuk lingkungan sendiri.
	Tidak untuk menjadi konsumsi/kepentingan umum.
	Hanya untuk melengkapi tugas DPBO 2023.*

### **Alasan**
Dengan menggunakan konsep MVP dipandang untuk model ini dianggap mudah untuk diimplementasikan sebagaimana dijelaskan, model berisi proses bisnis dan data terkait lainnya, seperti aturan permainan, dan skor. View menampilkan elemen-elemen visual dan menerima masukan dari pengguna. Presenter sebagai penghubung antara Model dan View. Sehingga untuk itu dipandang untuk mudah dipakai dimodel ini. Sehingga memberikan value dan keuntungan, diantara yang menjadi keuntungan menggunakan MVP ialah sebagai berikut :
1. Dengan MVP dapat membantu dalam mempertahankan kode yang terorganisir, mempermudah pemeliharaan, dan meningkatkan modularitas game.
2. Dalam pengembangan game yang kompleks, struktur MVP memungkinkan tim pengembang untuk dengan mudah menambahkan atau mengubah fitur-fitur game tanpa harus mempengaruhi komponen lainnya.
3. Presenter berperancsebagai penghubung antara Model dan View, sehingga jika terjadi perubahan pada Model atau View, Presenter dapat diubah dengan relatif mudah tanpa mengubah proses bisnis game.
4. MVP memungkinkan pengujian yang lebih mudah dan efektif. 

### **Perbedaan secara struktural dari LP dan TMD**
Terdapat perbedaan terkait penggunaan model pada LP sebelumnya dengan TMD. Untuk LP sebelumnya saya menggunakan model MVP *(Model View Presenter)* dan untuk TMD ini saya menggunakan model MVVM *(Model View ViewModel)*, mengapa? Berikut beberapa alasan saya menggunakan MVVM:
1. Jika dilihat dari Data Binding, satu fitur kunci dalam MVVM adalah perubahan pada data ViewModel secara otomatis akan diperbarui di View terkait, sehingga dengan MVVM,ketika terjadi perubahan maka akan  terjadi secara otomatis dan meminimalkan penulisan kode tambahan untuk memperbarui tampilan seperti tampilan data permainan, seperti poin, skor, standing, dll).
2. Tes Unit yang Lebih Mudah.
   Dalam MVVM, *ViewModel* berfungsi sebagai jembatan antara Model dan View,  tentunya ini memudahkan pengujian unit, di mana *ViewModel* dapat diuji secara terpisah tanpa melibatkan *View* atau infrastruktur permainan yang kompleks, sehingga pengujian lebih fokus dan mempercepat siklus pengembangan game.
3. Pemisahan yang Lebih Jelas.
   Dalam MVVM, *ViewModel* berfungsi sebagai perantara antara Model (data dan logika bisnis) dan View (antarmuka pengguna). Ini menciptakan pemisahan yang jelas antara tanggung jawab masing-masing komponen.
4. Dukungan untuk Pengembangan Antarplatform *(Cross-platform)*.
   MVVM dapat memfasilitasi pengembangan game yang bersifat antarplatform.

Tentunya secara keseluruhan terkait model yang digunakan baik itu di LP dan TMD terjadi perbedaan seperti yang telah dikatakan diatas, namun tentunya jika dipandang dari segi struktural terdapat beberapa perbedaan yang signifikan, karena untuk struktur TMD ini tentunya lebih kompleks dari LP, diantaranya :
1. Struktur LP terdiri dari :
   - Model
     - GameInterface
     - GameObject
     - Player
     - RandomBox
     - Synchronization
   - View
     - Display
   - Presenter
     - Controller
     - Game
     - Handler
       
2. Struktur TMD terdiri dari :
   - Model
     - DB : Kelas yang menghubungkan ke database.
     - Experience : Model dari database dan berisi query-query yang diperlukan.
     - GameObject : Kelas abstract dari object-object yang ada digame tersebut dan terdapat abstract methods yang akan mengupdate setiap object per detik dan terdapat rander juga yang akan menampilkan ke layar user/player/pengguna game.
     - ID : enum untuk membedakan antara setiap object.
     - Floor : Penanda object yang ada dibawah, ketika player menyentuh ke bagian bawah maka akan menambah nilai fail bahkan ketika berulang akan game over.
     - Obstacle : Rintangan dari player
     - Player : Player yang dimainkan oleh user.
   - View
     - Menu : menmapilkan tabel yang ada didalam database yang berisi data player
     - Windows : Sebagai penghubung dari gam eini, dan game ini akan ditampilkan di windoes ini.
   - ViewModel
     - Handler : Untuk mengelola game-game object, sehingga jika ada perubahan data/update data cukup memanggil game handler
     - BGM : Memulai dan memberhentikan BGM yang diputar (lebih tepatnya bagian ini adalah musiknya).
     - ExperienceProccess : Kelas ini berfungsi untuk untuk menghubungkan database dari Model ke View.
     - Game : Bagian ini merupakan bagian utama dari dari game yang tentunya berisi semua proses bisnis yang ada digame tersebut dari awal hingga game selesai.
     - Key Input : Untuk meng-*handle* keyboard input, ada beberapa *action* diantaranya W -> atas, A -> kiri, D -> kanan san Space : berhenti.
    
### **Catatan**
- *Jika terjadi hal kesalahan teknis/program error atau lainnya, dengan hormat mohon untuk melakukan validasi kepada saya, karena tentunya hal tersebut dimungkinkan terjadi diluar prakiraan/dugaan. Demikian, harap untuk menjadi maklum. Atas Perhatian dan Kerjasamanya diucapkan terima kasih*

Barakallahu Fiikum.

**Hatur Nuhun.**

**Majalaya, 31 Mei 2023 M / 11 Dzulkaidah 1444 H.**

**Majalaya, 21 Juni 2023 M / 02 Dzulhijjah 1444 H.** (Edisi revisi)

#### [Copyright © 2023. IBNU ADENG KURNIA.](https://me-qr.com/id/entry/vcard/MjuIan4)
###### Univ. Pendidikan Indonesia.
###### All Rights Reserved.




![creativecommands-](https://github.com/ibnuadeng03/TP3C2DPBO2023/assets/100753882/496f7f8e-4888-4bb2-8cd5-11f70c2425c8)

Ciptaan disebarluaskan di bawah [Lisensi Creative Commons Atribusi-NonKomersial-TanpaTurunan 4.0 Internasional.](http://creativecommons.org/licenses/by-nc-nd/4.0/)
