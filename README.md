# Program Pemesanan Makanan Online - Tap and Eat
Hi! Ini adalah proyek kecil untuk pemesanan makanan online demi menuntaskan Tugas I PBO saya. 
<br/> <br/>
Program Java ini dirancang untuk dua jenis user dengan akses yang berbeda, yaitu: <br/>
**1. Admin** diberikan akses untuk: 
     emsp Melihat restoran, menambahkan restoran, dan menghapus restoran. <br/>
**2. Customer** diberikan akses untuk:
     emsp Melihat restoran, membuat pesanan, dan melihat pesanan. <br/>
     
Program ini juga dilengkapi dengan **input validasi pada class Input** untuk memastikan pengguna memberikan input yang sesuai dengan yang dibutuhkan oleh program. <br/> <br/>
Di bawah ini adalah deskripsi program, penjelasan dari bagaimana program akan dijalankan, dan UML dari program ini. Untuk deskripsi kode lebih lengkap dapat dilihat pada kode program yang sudah dicommit sebelumnya (sudah disertakan beberapa comment untuk membantu dalam mengerti cara kerja program). Selamat menyimak :>

<br/>

# Identitas Saya
Nama    : I Gusti Ayu Krisna Kusuma Dewi <br/>
NIM     : 22055501072 <br/>
Matkul  : PBO E <br/>

<br/>

# UML


<br/>

# Bagaimana Program Dijalankan
Program ini dijalankan dengan menerapkan beberapa konsep di bawah ini: <br/>
**1. Penggunaan Class dan Object** <br/>      
**2. Penggunaan Flow Control** (khususnya while loop, for each loop, if-else, dan switch case) <br/>
**3. ArrayList** <br/>
**4. Shallow Copy** <br/>
**5. Inheritance** <br/>
**6. Nested Class** <br/>


# Walkthrough Penggunaan Program
Di bawah ini adalah walkthrough dari penggunaan program Tap and Eat beserta screenshotsnya. Adapun beberapa penjelasan singkat mengenai bagaimana kode akan berjalan.
<br/>
## Log In Menu
<img width="417" alt="image" src="https://user-images.githubusercontent.com/113322119/232229550-4560bf91-5c67-4430-97f0-b5899e4524b2.png">
Pada menu ini, pengguna dapat memilih untuk melakukan log in sebagai Admin atau sebagai Customer. Untuk saat ini, program terbatas hanya pada melakukan log in dan mencocokkan username dan password yang diinputkan oleh pengguna berdasarkan value yang sudah di-hardcoded di program sebelumnya. Pengguna dapat memilih dengan memberi input berupa angka dan akan diberikan validasi jika ternyata input mereka tidak sesuai.
<br/>

## Log In: Administrator dan Customer
<img width="422" alt="image" src="https://user-images.githubusercontent.com/113322119/232229907-88d89c9e-882c-4e7e-98dc-ef485b6b90d0.png">
<img width="425" alt="image" src="https://user-images.githubusercontent.com/113322119/232229933-feac9d63-c280-40e8-a610-ea2457852fde.png">
Setelah memilih ingin melakukan login sebagai siapa di menu sebelumnya, pengguna dapat memberikan input username dan password mereka. Bila benar, maka output seperti pada screenshot di atas akan muncuk dan pengguna akan diarahkan pada menu selanjutnya. Bila salah, maka pengguna akan diberikan instruksi untuk memberikan username dan password yang benar seperti pada screenshot di bawah ini.
<img width="417" alt="image" src="https://user-images.githubusercontent.com/113322119/232230345-e4cbe8ae-546a-4904-a4b1-16f3ed1df1c3.png">

## Menu Akses Administrator
<img width="423" alt="image" src="https://user-images.githubusercontent.com/113322119/232230383-07ecd739-4e39-4242-8ebd-f7123e650008.png">
Setelah melakukan login sebagai Admin, maka pengguna akan diarahkan pada menu Admin seperti pada gambar di atas ini. Admin lantas dapat memilih akses yang diinginkan dengan memberikan input angka.
<br/>

## Menu Akses Customer
<img width="427" alt="image" src="https://user-images.githubusercontent.com/113322119/232230621-d9c020c9-ab56-404d-850f-90c3a3b026ec.png">
Setelah melakukan login sebagai Customer, maka pengguna akan diarahkan pada menu Customer seperti pada gambar di atas ini. Customer lantas dapat memilih akses yang diinginkan dengan memberikan input angka.
<br/>

## Lihat Restoran yang Ada
<img width="423" alt="image" src="https://user-images.githubusercontent.com/113322119/232230730-eebe9cbc-db0b-4608-a0d9-e7ce61054a89.png">
Admin dan Customer dapat melihat restoran yang ada seperti pada list yang tertera pada screenshot di atas. Dengan memberikan input berupa id restoran, pengguna dapat melihat makanan dan minuman yang tersedia pada restoran tersebut seperti pada screenshot di bawah ini.
<img width="422" alt="image" src="https://user-images.githubusercontent.com/113322119/232230743-f8da8813-1b91-47aa-9f55-9e4855dcaa6c.png">
<img width="425" alt="image" src="https://user-images.githubusercontent.com/113322119/232230754-0555baac-b46a-4c2f-9801-5f04e4a1f11d.png">
<img width="426" alt="image" src="https://user-images.githubusercontent.com/113322119/232230763-38e7e1f7-4685-4ecf-9e10-64838b78784a.png">
List ini bisa ditambahkan melalui akses admin yaitu Tambahkan Restoran Baru bersamaan dengan menu yang ada pada restoran tersebut ataupun menghapus restoran dengan akses Hapus Restoran yang Ada.
<br/>

## Tambahkan Restoran Baru
### Penambahan Restoran
<img width="418" alt="image" src="https://user-images.githubusercontent.com/113322119/232231146-722e7ecf-385c-498d-8942-4be9ab1a77ef.png">
Pada bagian Tambahkan Restoran Baru, Admin dapat menambahkan restoran dan menu di dalam restoran tersebut. Pertama, Admin diminta untuk memberikan input berupa ID Restoran, Nama Restoran, dan Alamat Restoran seperti pada contoh screenshot di atas.

### Penambahan Menu
<img width="419" alt="image" src="https://user-images.githubusercontent.com/113322119/232231213-fd5fb340-d1ec-4bd6-b8cf-92edcf9d8872.png">
<img width="423" alt="image" src="https://user-images.githubusercontent.com/113322119/232232006-5312cbe8-0c0b-4504-81d4-bd1f1aa4530f.png">
<img width="425" alt="image" src="https://user-images.githubusercontent.com/113322119/232232214-b251cfd4-fdb6-4e05-8850-2ee645ea2e9b.png">
Pembuatan restoran baru dilanjutkan dengan menambahkan menu. Admin dapat memilih terlebih dahulu untuk menambahkan menu Makanan atau Minuman. Jika sudah selesai, Admin dapat memilih selesai. Setelah memilih, Admin dapat memasukkan ID Makanan atau Minuman, Nama Makanan atau Minuman, dan Harga Makanan atau Minuman tersebut dan lalu dikembalikan ke menu penambahan menu hingga pengguna selesai menambahkan menu tersebut dengan memilih Selesai. 
<img width="427" alt="image" src="https://user-images.githubusercontent.com/113322119/232232231-b328f902-b69a-4a22-8b49-32781f7b49ef.png">

### Selesai Menambahkan
<img width="430" alt="image" src="https://user-images.githubusercontent.com/113322119/232232341-803eec45-12de-423a-b8f4-9225fd841e98.png">
Setelah selesai, Admin dapat melanjutkan pembuatan restoran atau dapat berhenti menambahkan restoran. Restoran baru pun akan muncul pada akses Lihat Restoran yang Ada seperti di atas.

### Batal Menambahkan
<img width="432" alt="image" src="https://user-images.githubusercontent.com/113322119/232232281-fdfb8d02-7db5-4708-8b6a-30eaf9e5829e.png">
Bila ternyata Admin belum memasukkan menu apapun dan memilih Selesai, maka program akan memberikan pertanyaan seperti pada screenshot di atas dan dibawa ke menu akses Admin kembali jika benar Admin membatalkan penambahan restoran.

## Hapus Restoran yang Ada
<img width="424" alt="image" src="https://user-images.githubusercontent.com/113322119/232233422-d5c390d9-8b5d-4972-a8ab-32b088ecbcbd.png">
<img width="425" alt="image" src="https://user-images.githubusercontent.com/113322119/232233543-15b9ecca-721b-450f-bec0-bfc8b2c10a5f.png">
Admin dapat menghapus restoran yang ada dengan memberikan input ID Resto seperti pada contoh di atas. Setelahnya, restoran tersebut akan dihapus dan tidak dapat dilihat pada list restoran.

## Buat Pesanan Baru
<img width="419" alt="image" src="https://user-images.githubusercontent.com/113322119/232236190-65b77956-71b1-4f2f-bc1a-bcbe2dee90cd.png">
Customer memiliki akses untuk membuat pesanan baru. Pertama, customer akan memilih restoran tempat dia ingin memesan dengan memberikan input berupa ID Resto yang dipilih. Setelahnya, program akan meminta customer untuk memberi input jaraknya dengan restoran yang mana akan digunakan untuk menghitung biaya pengantaran.

### Memilih Menu
<img width="426" alt="image" src="https://user-images.githubusercontent.com/113322119/232236272-5a103060-fb3c-4ed6-846a-6f772304183d.png">
<img width="421" alt="image" src="https://user-images.githubusercontent.com/113322119/232236363-3b2a0325-7bc0-4791-9e98-ece7263ab049.png">
Setelah memilih restoran, Customer dapat lanjut memilih menu dengan memilih menu makanan, minuman, atau selesai memesan seperti pada screenshot di atas. Customer dapat menambahkan makanan atau minuman yang diinginkan dengan menginput id menu yang tertera dan memasukkan jumlah menu yang diinginkan. <br/>
<img width="557" alt="image" src="https://user-images.githubusercontent.com/113322119/232237134-65388330-59d7-4b17-8688-8e568fe03afb.png">
Setelah selesai, Customer akan mendapatkan preview total harga saat ini dan selanjutnya akan diarahkan ke menu pada screenshot pertama pada saat memilih menu dan dapat kembali menambahkan pesanan atau pun selesai melakukan pemesanan.

### Pembayaran
<img width="552" alt="image" src="https://user-images.githubusercontent.com/113322119/232237267-921c740f-50f5-49f8-a4f2-d4cf11fd95fa.png">
<img width="558" alt="image" src="https://user-images.githubusercontent.com/113322119/232237320-5d36e015-c8bb-46ad-85b6-525dc023dca3.png">
Jika sudah selesai, Customer akan diarahkan ke bagian pembayaran seperti screenshot di atas ini. Customer dapat memasukkan nominal pembayarannya dan mendapatkan kembalian bila ada. Setelahnya, Customer akan diarahkan untuk kembali ke menu akses Customer.

### Membatalkan Pemesanan
<img width="428" alt="image" src="https://user-images.githubusercontent.com/113322119/232237368-8a707118-e326-4c9b-97d1-6548c20cc6a0.png">
Bila ternyata Customer memilih menyelesaikan pemesanan tanpa menambahkan pesanan apapun, maka pemesanan bisa dianggap batal seperti pada screenshot di atas ini.

## Lihat Riwayat Pemesanan
<img width="561" alt="image" src="https://user-images.githubusercontent.com/113322119/232237441-f9ebcecb-bf36-4049-858e-bfe5a8d6b9bd.png">
<img width="556" alt="image" src="https://user-images.githubusercontent.com/113322119/232237456-9411765e-a163-4584-98b5-799c435f7b92.png">
Customer dapat melihat pemesanan yang telah dilakukan sebelumnya melalui menu Lihat Riwayat Pemesanan seperti pada screenshot di atas.

