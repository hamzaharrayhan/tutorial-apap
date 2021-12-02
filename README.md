# Tutorial APAP
## Authors
* **Hamzah Daffa Arrayhan** - *1906307302* - *C*

# Lab 1
### What I have learned today
(Masukkan pertanyaan yang diikuti jawaban di setiap nomor, contoh seperti dibawah. Anda
juga boleh menambahkan catatan apapun di bagian ini)
### Github
1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?
Issue digunakan untuk melakukan tracking mengenai pekerjaan yang dilakukan di github saat proses pengembangan. Github issues merupakan sebuah alat yang terintegrasi dengan github repository yang dapat digunakan untuk fokus terhadap tugas-tugas yang penting, serta agar tetap pada rencana.

2. Apa perbedaan dari git merge dan git merge --squash?
Squash merge adalah salah satu opsi dalam melakukan merging di Git yang akan membuat sebuah merge commit dengan hanya 1 parent. File akan digabungkan sebagaimana pada git merge biasa, namun commit metadata akan berubah untuk menunjukan bahwa hanya satu dari parent mengcommit. Hasilnya adalah sebuah commit pada target branch dengan seluruh perubahan pada git merge biasa.
 
3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan
suatu aplikasi?
Lebih mudah dilacak, karena kita mendapatkan semua history dan bukti dari setipa revisi pekerjaan yang dilakukan selama proses pengembangan. Memiliki riwayat dokumen, dimana developer dapat dengan mudah memahami informasi mengenai pekerjaan, juga memiliki dampak bagi developer yang bekerja pada versi terbaru. Fitur branch dan merge juga sangat membantu dalam mengatur pekerjaan individu dan pekerjaan kelompok, sehingga perubahan dan pekerjaan yang dilakukan dapat ditinjau ulang sebelum digabungkan dengan pekerjaan berikutnya ataupun pekerjaan orang lain.

### Spring
4. Apa itu library & dependency?
Library adalah sekumpulan implementasi behavior yang ditulis dalam bahasa pemrograman dan terorganisasi sedemikian rupa agar bisa digunakan oleh banyak program yang tidak memiliki hubungan langsung satu sama lainnya. Sementara itu, jika suatu program  menggunakan suatu library, dapat dikatakan bahwa library tersebut adalah dependency dari program yang menggunakannya. Atau ketika kita memiliki sebuah program dan program tersebut membutuhkan program lain untuk bekerja, maka program lain tersebut adalah dependency.

5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?
Maven adalah sebuah project management tool yang berbasiskan project object model yang digunakan untuk membangun projek, dependency, dan documentation. Maven mensimplifikasi proses buil seperti ANT. Singkatnya, maven adalah alat yang digunakan untuk membangun dan mengatur proyek java menjadi lebih mudah.

6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring
framework?
Selain untuk mengembangkan web, spring framework juga dapat digunakan untuk membangun aplikasi, mengatur integrasi database, mailer, remote access dan lain sebagainya.

7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya
menggunakan @RequestParam atau @PathVariable?
@RequestParam digunakan untuk membaca form data dan mengikat secara otomatis kepada parameter yang ada pada metode yang tersedia. @PathVariable adalah anotasi yang mengindikasikan bahwa parameter method harus terkait dengan URI template variable. Gunakan @RequestParam untuk mendapatkan query parameter, gunakan @PathVariable untuk mengekstrak value dari URI.

### What I did not understand
(tuliskan apa saja yang kurang Anda mengerti, Anda dapat men-_check_ apabila Anda
sudah mengerti dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti)
- [ ] Kenapa saya harus belajar APAP?
- [x] Kenapa?
Karena …
(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu
lebih dalam tentang penulisan README.md di GitHub pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))

# Lab 2
Pertanyaan 1: Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link berikut: http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20 APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx&jumlahStudio=10
Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi

Yang terjadi adalah whitespace error, Karena belum terdapat template untuk di render.

Pertanyaan 2: Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat

@Autowired memungkinkan Spring untuk meresolve dan inject beans yang saling berkolaborasi pada suatu bean. Dengan @autowired, dimungkinkan injeksi dependency otomatis. Oleh karena itu, dengan mendeklarasikan seluruh dependency bean pada file konfigurasi Spring, Spring container dapat melakukan hubungan autowire diantara beans yang berkolaborasi.

Pertanyaan 3: Cobalah untuk menambahkan sebuah Bioskop dengan mengakses link berikut: http://localhost:8080/bioskop/add?idBioskop=1&namaBioskop=Bioskop%20PAPA%20 APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx Apa yang terjadi?
Jelaskan mengapa hal tersebut dapat terjadi.

Gagal membuka halaman template add-bioskop.html dikarenakan tidak sesuai dengan routing di @RequestMapping. Hal tersebut terjadi karena terdapat route yang tidak diisi, yaitu jumlahStudio yang juga terdapat pada parameter method addBioskop yang bersifat required di controller.

Pertanyaan 4: Jika Papa APAP ingin melihat Bioskop dengan nama Bioskop Maung, link apa yang harus diakses?

Mengakses route mapping ke http://localhost:8080/bioskop/view?idBioskop=1 setelah melakukan addBioskop.

Pertanyaan 5: Tambahkan 1 contoh Bioskop lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/bioskop/viewall, apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.

Berisikan seluruh isi List bioskop yang sebelumnya telah dibuat
![image](https://user-images.githubusercontent.com/61260701/133473137-c1f7ec66-cb91-4c33-92b8-05d2779950b1.png)

# Lab 3
1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)
@AllArgsConstructor Untuk membuat class constructor yang terikat dengan parameter keseluruhan atribut
@NoArgsConstructor Untuk membuat class constructor yang terikat dengan parameter kosong
@Setter Untuk menghasilkan setter method di suatu class untuk semua atribut yang terdapat didalamnya
@Getter Untuk Untuk membuat getter method di suatu class untuk semua atribut yang terdapat didalamnya
@Entity Adalah sebagai entity di JPA
@Table Adalah sebagai pembentuk tabel SQL

2. Pada class BioskopDB, terdapat method findByNoBioskop, apakah kegunaan dari method
tersebut?
Adalah untuk melakukan pencarian data di database berdasarkan kolom noBioskop

3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn
@JoinColumn digunakan ketika kita ingin menggabungkan sebuah kolom dengan sebuah tabel berbeda
@JoinTable digunakan untuk menggabungkan semua atribut di tabel lain membentuk suatu tabel baru

4. Pada class PenjagaModel, digunakan anotasi @JoinColumn pada atribut bioskop, apa
kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa
perbedaan nullable dan penggunaan anotasi @NotNull
name, adalah sebagai nama kolom yang dijadikan atribut pada tabel PenjagaModel
referencedColumnName, adalah sebagai kolom yang menjadi referensi atau foreign key suatu kolom tersebut
nullable, adalah sebuah penanda bahwa suatu atribut tersebut merupakan referenced ke tabel lain dan boleh bernilai null
Perbedaan nullable dan @NotNull yaitu adalah @NotNull tidak akan dapat mengeksekusi SQL jika terdapat pelanggaran, namun nullable akan tetap berjalan, dan baru gagal jika ada pelanggaran constraintnya.

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER
FetchType.LAZY, melakukan data load berdasarkan data yang dibutuhkan saja
FetchType.EAGER, mengambil semua data yang ada, baik digunakan ataupun tidak
CascadeType.ALL, untuk menandakan bahwa suatu atribut yang dikenakan tindakan seperti (REMOVE, MERGE, REFRESH, dsb) akan mengalami pengaruh ke semua entiti yang memiliki hubungan ke atribut tersebut

# Lab 5
**1.Apa itu Postman? Apa kegunaannya?**

Postman adalah apikasi yang berfungsi untuk melakukan pengujian API. Postman digunakan dalam proses untuk menguji API, beberapa kelebihan Postman yaitu:
Ramah pengguna, memilik tampilan yang mudah digunakan sehingga pengguna dapat menggunakannya dengan mudah. Selain itu juga ada faktor aksesibilitas yang dapat digunakan oleh pengguna melalui berbagai device. Beberapa fungsi dalam Postman yaitu metode HTTP, penyimpanan, Konversi API, juga pembuatan environment pengembangan API.

**2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty.**

@JsonIgnoreProperties
Berguna pada suatu kelas untuk memberikan properti untuk diabaikan

@JsonProperty
Berguna untuk memberikan tanda pengambilan metode yang tidak terstandar untuk properti json

**3. Apa kegunaan atribut WebClient?**
Menyediakan metode untuk mengirimkan data atau menerima data dari lokal, intranet maupun internet yang dapat diidentifikasi oleh URI

# Lab 7
1. Jelaskan apa yang Anda lakukan di latihan dalam satu paragraf per-soal. Berikan screenshot
sebagai ilustrasi dari apa yang Anda jelaskan.
Pada nomor satu, saya menambahkan sebuah const/variabel untuk menghandle delete item dari cart, fungsinya sama seperti function handle add item to cart. Tetapi props inCart dijadikan false.

Untuk nomor 2, dilakukan pengambilan harga dari item yang akan ditambah maupun dihapus dari cart & shop. Kemudian ditambahkan dengan nilai balance pada state. Selanjutnya dilakukan setState untuk balance berdasarkan hasil jumlah atau kurang sebelumnya.

Untuk nomor 3 hanya dilakukan penambahan komponen alert() bawaan dari react.js, sebelumnya diberikan conditional bila balance tidak sesuai kondisi yang seharusnya.
![image](https://user-images.githubusercontent.com/61260701/143251243-a1f95867-18db-4196-b084-3d63eaa6fe87.png)

2. Menurut pemahaman kamu selama pengerjaan tutorial ini, apa perbedaan antara state dan props?
State berperan sebagai penyimpanan data sementara dari suatu function/class component di javascript, sementara props bertindak seperti constructor dari suatu function/class component.

3. Menurut kamu, apakah sebaiknya kita menggunakan component (e.g. List, Item) dalam React? sebutkan alasannya.
Iya, karena react mendukung code modular atau reusable component. Dengan hal itu, kita dapat membuat komponen-komponen kecil yang dapat digunakan kembali sehingga merampingkan project serta lebih sedikit kode.

4. Apa perbedaan class component dan functional component?
Class component adalah metode penggunaan konsep OOP dalam javascript dan merupakan cara lama yang digunakan dalam react. Sedangkan functional component dikenalkan pada versi baru react dan memberikan simplifikasi pemanggilan state daripada class component, dengan menggunakan react hook seperti useState dan useEffect.
![image](https://user-images.githubusercontent.com/61260701/144418779-6c6364d4-2ac2-4a32-8fc7-b35877400885.png)
![image](https://user-images.githubusercontent.com/61260701/144418854-e6b8d654-0aaf-498a-8ca6-018bf2ef72b1.png)
![image](https://user-images.githubusercontent.com/61260701/144418888-661b83ac-96a8-4f63-8bb0-b660cfeb2f3f.png)
![image](https://user-images.githubusercontent.com/61260701/144418903-80efe903-e0ea-44ba-8454-a62145fa6123.png)
![image](https://user-images.githubusercontent.com/61260701/144418919-0bd7fb24-e969-4d5b-9386-850baa6c7475.png)

5. Dalam react, apakah perbedaan component dan element?
React element adalah objek yang merepresentasikan DOM seperti dalam konsep HTML seperti div, h1, button, dan sebagainya. Sementara itu component adalah sekumpulan element yang membentuk aplikasi kita, dan kumpulan komponen ini digunakan dalam view di project.

Ceritakan langkah - langkah yang kalian lakukan untuk solve LATIHAN no.1, dan mengapa kalian melakukan langkah - langkah tersebut?
Langkah yang dilakukan adalah dengan cara menambahkan this.setState kembali dan menset semua state seperti id, title, price, description, category, dan quantity di reset untuk yang string menjadi "" dan apabila integer atau angka menjadi 0. Hal ini akan mengubah state menjadi nilai tersebut dan menjadi nilai kosong ketika dipanggil handler untuk add Item di lain waktu.


Jelaskan fungsi dari async dan await! 
Fungsi async adalah fungsi yang dideklarasikan dengan kata ‘async’, dan kata kunci ‘await’ diperlukan untuk digunakan di dalamnya. Kata kunci ‘async’ dan ‘await’ memungkinkan perilaku berbasis promises asinkron untuk dapat ditulis dengan gaya penulisan yang lebih teratur, menghindari kebutuhan untuk mengkonfigurasi rantai promise secara eksplisit.

Fungsi async dapat berisi nol atau lebih ekspresi await. Ekspresi await membuat fungsi yang mengembalikan promise berperilaku seolah-olah mereka sinkron dengan menunggu eksekusi hingga promise yang dikembalikan dipenuhi atau ditolak. Nilai yang diselesaikan dari promise diperlakukan sebagai nilai return dari ekspresi await. Penggunaan async dan await memungkinkan penggunaan blok try/catch di sekitar kode async.

Masukkan jawaban dari Screenshot yang diperintahkan di halaman 9 pada Component Lifecycle pada pertanyaan ini. 

Jelaskan fungsi dari componentDidMount, shouldComponentUpdate, componentDidUpdate, componentWillReceiveProps, componentWillUnmount. 
Notes : Penjelasan harus mencantumkan “kapan fungsi dipanggil” dan “use case apa saja yang biasanya menggunakan lifecycle method tersebut”.

- componentDidMount : Fungsi ini akan dipanggil ketika seluruh component nya akan siap untuk dilakukan rendering, use casenya adalah ketika kita ingin mengetahui kapan dan apakah komponen tersebut dibuat.
- shouldComponentUpdate & componentDidUpdate: Fungsi shouldComponentUpdate ini berguna untuk memberi tahu bahwa output komponen terpengaruh atau mengalami perubahan. Fungsi ini memiliki keterkaitan dengan componentDidUpdate. Penggunaan properti ini adalah ketika menekan button atau mengisi form, jika mengembalikan true, maka akan memanggil componentDidUpdate, serta contoh kasusnya ketika melakukan submit add item, terjadi penambahan pada list item.
- componentDidUpdate : fungsi ini berguna untuk melakukan DOM terhadap komponen yang baru saja diperbarui, contoh use case tersebut adalah ketika komponen tersebut baru saja diperbarui.
- componentWillReceiveProps : fungsi ini berguna untuk mengupdate status terhadap perubahan properti, contoh use case dari fungsi ini adalah ketika terjadi pengubahan komponen.
- componentWillUnmount : componentWillUnmount akan dipanggil jika sebuah component akan dihapus atau disingkirkan dari tampilan. Contoh kasusnya adalah ketika kita melakukan aktivitas delete pada sebuah component, sehingga component itu akan disingkirkan dari tampilan pengguna.
