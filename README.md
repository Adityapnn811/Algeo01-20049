# Proyek Aljabar Linier dan Geometri 01
<p>Proyek ini dikerjakan dan dipelihara oleh:</p>
<ul>
   <li>Aditya Prawira Nugroho (13520049)
   <li>Felicia Sutandijo (13520050)
   <li>Christopher Jeffrey (13520055)
</ul>

# Seputar Proyek Ini
## Informasi Umum
<p>Proyek ini dibuat untuk memenuhi syarat dalam mata kuliah aljabar linier dan geometri di Teknik Informatika ITB. Proyek ini memiliki beberapa fitur dan fungsi, yaitu:</p>
<ul>
   <li>Kalkulator sistem persamaan linier dengan metode Gauss, Gauss-Jordan, Kaidah Cramer, dan Invers Matriks
   <li>Kalkulator determinan matriks dengan metode reduksi dan kofaktor
   <li>Kalkulator matriks balikan dengan metode Operasi Baris Elementer dan Adjoin
   <li>Kalkulator interpolasi polinom
   <li>Kalkulator regresi linier berganda
</ul>

## Struktur Direktori
1. `bin` berisi java <em>bytecodes (*.class)</em> dari <em>source code</em> program.
2. `doc` berisi laporan dari proyek 1 mata kuliah aljabar linier dan geometri.
3. `src` berisi <em>source code</em> dari program.
4. `test` berisi input file `.txt` yang berisi matriks dari studi kasus. 

## Lingkungan Pengerjaan
<p>Proyek ini dikerjakan dalam lingkungan:</p>
<ul>
  <li>Java(TM) SE Runtime Environment (build 17+35-LTS-2724)
  <li>javac 17
  <li>Visual Studio Code
</ul>

## Cara Menjalankan Program
1. Pastikan Anda sedang berada di direktori `src`. Jika Anda belum berada di direktori `src`, Anda bisa mencoba mengubah direktori Anda dengan mengetik `cd <direktori src>`, `<direktori src>` adalah direktori folder `src` Anda berada, contoh `C:\Users\user\Aljabar Linier dan Geometri\Tubes 1\Algeo01-20049\src`.
2. Ketikkan ke <em>command line</em>: `javac -d ../bin ./*.java`, perintah itu akan meng-<em>compile</em> semua file dengan ekstensi `.java` ke file bytecodes ke dalam folder `bin`.
3. Kemudian, ketikkan `java -classpath ../bin Main` ke <em>command line</em> untuk menjalankan program `Main`.
