import java.util.*;
import java.lang.Math;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;

public class Matriks{
    static Scanner sc = new Scanner(System.in);
    
    // Deklarasi atribut di matriks
    private int baris, kolom;
    private double[][] isi;
    
    /* *** Konstruktor membentuk Matrix *** */
    Matriks(int b, int k){
        this.baris = b;
        this.kolom = k;
        this.isi = new double[b][k];
    }
/* ********** FUNGSI PRIMITIF ********** */
    int Baris(){
        //untuk mendapatkan baris dari matriks
       return this.baris; 
    }
    int Kolom(){
        // buat mendapatkan kolom dari matriks
        return this.kolom;
    }
    double Isi(int b,int k){
        // untuk mendapatkan isi dari matriks di index baris b dan kolom k
        return this.isi[b][k];
    }
    void ubahIsi(int b,int k, double newValue){
        //IS matriks this terdifinisi, isi sembarang
        //FS matriks this pada baris b dan kolom k bernilai newValue
        this.isi[b][k] = newValue;
    }
    void ubahBaris(int b){
        //IS matriks this terdefinisi
        //FS baris matriks this berubah menjadi b. b harus lebih kecil dari baris sebelumnya, hanya mengubah atribut, tidak mengubah ukuran 
        this.baris = b;
    }
    void ubahKolom(int k){
        //IS matriks this terdefinisi
        //FS kolom matriks this berubah menjadi k. k harus lebih kecil dari baris sebelumnya, hanya mengubah atribut, tidak mengubah ukuran
        this.kolom = k;
    }

    // Copy matriks dan menerima parameter mIn dan mengeluarkan matriks mOut
    Matriks copyMatriks(Matriks mIn){
        //IS matriks mIn dan mOut terdefinisi. mIn memiliki isi.
        //FS mOut menjadi duplikat dari mIn
        Matriks mOut = new Matriks(mIn.Baris(), mIn.Kolom());
        for (int i = 0; i < mOut.Baris(); i++){
            for (int j = 0; j < mOut.Kolom(); j++){
                mOut.ubahIsi(i, j, mIn.Isi(i, j));
            }
        }
        return mOut;
    }

    // Buat tukar baris tukar kolom
    void tukarBaris(int b1, int b2){
        //IS baris matriks this terdefinisi, baris b1 dan baris b2 memiliki isi
        //FS isi matriks di baris b1 ditukar dengan isi dari baris b2
        int j;
        double temp;
        for (j = 0; j < this.kolom; j++){
            temp = this.Isi(b1, j);
            this.ubahIsi(b1, j, this.Isi(b2, j));
            this.ubahIsi(b2, j, temp);
        }
        System.out.println("Menukar baris " + b1 + " dengan " + b2);
    }

    void tukarKolom(int k1, int k2){
        //IS baris matriks this terdefinisi, kolom k1 dan kolom k2 memiliki isi
        //FS isi matriks di baris b1 ditukar dengan isi dari baris b2
        int i;
        double temp;
        for (i = 0; i < this.baris; i++){
            temp = this.Isi(k1, i);
            this.ubahIsi(k1, i, this.Isi(k2, i));
            this.ubahIsi(k2, i, temp);
        }
    }

    boolean semuaBarisNol(int lastIdxBaris, int jmlKolom){
        // Menghasilkan true jika semua elemen pada baris bernilai nol
        //lastidx baris adalah baris yang akan dicek, jmlKolom adalah panjang kolomnya, dihitung mulai dari kolom nol.
        boolean semuaNol = true;
        int j =0;
        while (j < jmlKolom && semuaNol){
            if (this.isi[lastIdxBaris][j] != 0){
                semuaNol = false;
            } else {
                j++;
            }
        }
        return semuaNol;
    }

    boolean semuaKolomNol(int lastIdxKolom, int jmlBaris){
        // Menghasilkan true jika semua elemen pada kolom bernilai nol
        boolean semuaNol = true;
        int j = 0;
        while (j < jmlBaris && semuaNol){
            if (this.isi[j][lastIdxKolom] != 0){
                semuaNol = false;
            } else {
                j++;
            }
        }
        return semuaNol;
    }

    // Fungsi transpose matriks
    static Matriks transpose(Matriks m){
        Matriks temp = new Matriks(m.kolom, m.baris);
        for (int i = 0; i < m.kolom; i++){
            for (int j = 0; j < m.baris; j++){
                temp.ubahIsi(i, j, m.isi[j][i]);
            }
        }
        return temp;
    }

    // Fungsi perkalian matriks
    static Matriks perkalianMatriks(Matriks m1, Matriks m2){
        Matriks hasil = new Matriks(m1.Baris(), m2.Kolom());
        for (int b = 0; b < m1.Baris(); b++){
            for (int k = 0; k < m2.Kolom(); k++){
                double sum = 0;
                for (int j = 0; j < m2.Baris(); j++){
                    sum += m1.Isi(b, j) * m2.Isi(j, k);
                }
                hasil.ubahIsi(b, k, sum);
            }
        }
        return hasil;
    }

    // Fungsi determinan matriks
    double det(Matriks m) {
        // Fungsi menerima matriks m terdefinisi dan merupakan matriks persegi
        // Fungsi mengeluarkan determinan matriks menggunakan cara reduksi tanpa mengubah matriks masukan
        /* KAMUS */
        double det = 1;
        Matriks reduksi;
        /* ALGORITMA */
        reduksi = copyMatriks(m);
        reduksi.detReduksiOBE();
        for (int i=0;i<reduksi.Baris();i++) {
            det *= reduksi.Isi(i, i);
        }    
        return det;
    }

    // Fungsi menentukan matriks homogen
    boolean isMatriksHomogen() {
        // Fungsi mengeluarkan true bila matriks homogen dan false bila matriks tidak homogen
        // Matriks homogen adalah matriks dengan kolom terakhir berisi semua 0
        /* KAMUS */
        /* ALGORITMA */
        return (semuaKolomNol(this.Kolom()-1, this.Baris()));
    }
/* ********** KELOMPOK BACA/TULIS ********** */
    // Mengisi matriks
    void isiMatriks() {
        for (int i = 0; i < this.baris; i++){
            for (int j = 0; j < this.kolom; j++) {
                this.isi[i][j] = sc.nextDouble();
            }
        }
    }

    // Menampilkan matriks
    public void displayMatriks(){
    //IS matriks this terdefinisi dan sudah terisi.
    //FS ditampilkan dilayar isi dari matriks this.
        for (int i = 0; i < this.baris; i++){
            for (int j = 0; j < this.kolom; j++) {
                if (this.isi[i][j] == -0.0){ // Menghilangkan -0
                    this.isi[i][j] = Math.abs(-0.0);
                }
                System.out.printf("%.3f ", this.isi[i][j]);
            }
            System.out.println();
        }
    }

    /*PROSEDUR DAN FUNGSI UNTUK INPUT FILE*/
    public static int FileRow(String FileName){
        //mengembalikan jumlah kolom dari file dengan nama FileName. kalo filenya gk ketemu, return -1
        int lines = -1;
        try{
            Scanner reader = new Scanner(new File(FileName));
            lines = 0;
            while (reader.hasNextLine()){
                reader.nextLine();
                lines++;
            }
            reader.close();
        }
        catch (Exception e) {
            sc.next();
            System.out.println("FILENYA GADA WOI BOONG LU(ato bisa juga typo). masukin lgi");
        }
        return lines;
    }
        public static int FileColumn(String FileName){
            //mengembalikan jumlah baris dari suatu file dengan nama FileName. kalo filenya gk ketemu, return -1
            int kolom = -1;
            try{
                Scanner reader = new Scanner(new File(FileName));
                kolom = 1;
                String oneLine = reader.nextLine();
                for(int i = 0;i < oneLine.length();i++){
                    if (oneLine.charAt(i) == ' ')
                    kolom++;
                }
                reader.close();
            
            }
            catch (Exception e) {
                sc.next();
                System.out.println("FILENYA GADA WOI BOONG LU(ato bisa juga typo)");
            }
    
            return kolom;
        }
            
    void ReadMatriksFromFile(String FileName){
        //IS matriks This terdefinisi, belum memiliki isi.
        //FS matriks sudah terisi sesuai dengan isi dari file FileName(pasti augmented)

        try {
            int b = 0;
            int k = 0;
            // isi matriks
            File myObj = new File(FileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextDouble()) {
                this.isi[b][k] = myReader.nextDouble();
                //next data in matriks
                if(k < this.kolom - 1){
                    k++;
                }
                else{
                    k = 0;
                    b++;
                }
            }
            myReader.close();
        } 
        catch (Exception e) {
            sc.next();
            System.out.println("FILENYA GADA WOI BOONG LU(ato bisa juga typo)");
        }
    }


    void outputToFile(int tipePersoalan, double det, String line){
        // IS matriks terdefinisi
        // FS Matriks disalin ke sebuah file txt dengan luaran sesuai spesifikasi persoalan
        // 1 = SPL, 2 = Determinan, 3 = Invers, 4 = Interpolasi, 5 = Regresi
        System.out.print("Masukkan nama file (tanpa .txt dan jangan gunakan spasi): ");
        String filename = sc.next();
        if (tipePersoalan == 1 || tipePersoalan == 4 || tipePersoalan == 5) {
            try{
                FileWriter writer = new FileWriter(filename + ".txt");
                writer.write(line);
                System.out.println("File berhasil disimpan di " + filename + ".txt");
                writer.close();
            } catch (IOException e){
                System.out.println("Terjadi sebuah error");
                e.printStackTrace();
            }  
        } else if (tipePersoalan == 2) {
            try{
                FileWriter writer = new FileWriter(filename + ".txt");
                writer.write("Determinan matriks adalah " + Double.toString(det));
                System.out.println("File berhasil disimpan di " + filename + ".txt");
                writer.close();
            } catch (IOException e){
                System.out.println("Terjadi sebuah error");
                e.printStackTrace();
            }  
        } else if (tipePersoalan == 3){
            try{
                FileWriter writer = new FileWriter(filename + ".txt");
                writer.write("Matriks balikannya adalah\n");
                for (int b = 0; b < this.baris; b++){
                    for (int k = 0; k < this.kolom; k++) {
                        writer.write(Float.toString((float) this.isi[b][k]));
                        if (k != this.kolom - 1){
                            writer.write(" ");
                        }
                    }
                    writer.write("\n");
                }
                System.out.println("File berhasil disimpan di " + filename + ".txt");
                writer.close();
            } catch (IOException e){
                System.out.println("Terjadi sebuah error");
                e.printStackTrace();
            }  
        }
    }

    void konfirmOutputkeFile(int tipePersoalan, double det, String line){
        // IS Matriks terdefinisi
        // FS Matriks disalin ke sebuah file txt atau tidak disalin
        char konfirm;
            do{
                System.out.print("Apakah Anda ingin menyimpan hasil ke sebuah file? (y/n)\n->");
                konfirm = sc.next().charAt(0);
                if (konfirm != 'y' && konfirm != 'n'){
                    System.out.println("Input harus berupa y/n");
                }
            } while (konfirm != 'y' && konfirm != 'n');
            if (konfirm == 'y'){
                this.outputToFile(tipePersoalan, det, line);
            }
    }
/* ********** KELOMPOK TAMBAHAN ********** */
/* *** METHOD OBE *** */
    // GAUSS
    void OBEGauss(int b, int k){
        //cari di kolom 1 mana yang enggak 0, terus tuker ke paling atas (sesuaikan pass ke berapa)
        // terus lakuin OBE sampe bawahnya nol semua
        int b_pass = 0, k_pass = 0; //inisiasi pass baris dan kolom
        int b_ganti = 0; 
        double pembagi;
        while (b_pass < b && k_pass < k){
            if (this.Isi(b_ganti, k_pass) == 0){
                if (b_ganti == b - 1){ // biar nilai b_ganti ngga lebih dari b_pass
                    b_ganti = b_pass;
                    k_pass += 1;
                } else{
                    b_ganti = b_ganti + 1;
                }
            } else {
                if (b_ganti != b_pass){
                    this.tukarBaris(b_ganti, b_pass); 
                }
                pembagi = this.Isi(b_pass, k_pass);
                // Jadiin leading one
                for (int j = 0; j < k; j++){
                    this.ubahIsi(b_pass, j, (this.Isi(b_pass, j)/pembagi));
                }
                // Kurangi baris lain
                // cek apakah sudah pass terakhir
                if (b_pass != b-1){
                    for (int i = b_pass + 1; i < b; i++){
                        if (this.isi[i][k_pass] != 0){
                            double pengali = this.isi[i][k_pass];
                            this.isi[i][k_pass] = 0;
                            for (int j = k_pass + 1; j < k; j++){
                                this.isi[i][j] -= this.Isi(b_pass, j) * pengali;
                                if (this.isi[i][j] == -0.0){ // Menghilangkan -0
                                    this.isi[i][j] = Math.abs(-0.0);
                                }
                            }       
                        }
                    }
                }
                b_pass += 1;
                k_pass += 1;
                b_ganti = b_pass;
            }
        }
    }
    
    // GAUSS JORDAN
    void OBEGaussJordan(int b, int k){
        //cari di kolom 1 mana yang enggak 0, terus tuker ke paling atas (sesuaikan pass ke berapa)
        // terus lakuin OBE sampe bawahnya nol semua
        int b_pass = 0, k_pass = 0; //inisiasi pass baris dan kolom
        this.OBEGauss(b, k);
        // Reduksi menjadi gauss-jordan
        b_pass = b -1; k_pass = 0;
        while (b_pass > -1 && k_pass < k) {
            //cari leading one dari baris paling bawah
            if (this.isi[b_pass][k_pass] != 1) {
                if (k_pass == k - 1) {
                    b_pass -= 1;
                    k_pass = 0;
                } else {
                    k_pass += 1;
                }
            }
            else { //udah ketemu leading one
                for (int i = b_pass - 1; i > -1; i--){
                    if (this.isi[i][k_pass] != 0){
                        double pengali = this.isi[i][k_pass];
                        this.isi[i][k_pass] = 0;
                        for (int j = k_pass + 1; j < k; j++){
                            this.isi[i][j] -= this.Isi(b_pass, j) * pengali;
                            if (this.isi[i][j] == -0.0){
                                this.isi[i][j] = Math.abs(-0.0);
                            } //menghilangkan -0
                        }       
                    }
                }
                b_pass -= 1;
                k_pass = 0;
            }
        }
    }


/* *** PROSEDUR INVERS DENGAN OBE *** */
    void inversMatriksOBE(){
        // Ubah ukuran matriks dan beri matriks identitas, simpan di mTemp
        Matriks mTemp = new Matriks(this.baris, this.kolom * 2);
        for (int i = 0; i < mTemp.Baris(); i++){
            for (int j = 0; j < mTemp.Kolom(); j++){
                // Kalo j dibawah idx col/2 maka isi sama kaya matriks awal
                if (j < mTemp.Kolom()/2) {
                    mTemp.ubahIsi(i, j, this.isi[i][j]);
                } else {
                    if ((j - (mTemp.Kolom()/2)) == i) {
                        mTemp.ubahIsi(i, j, 1);
                    } else {
                        mTemp.ubahIsi(i, j, 0);
                    }
                }
            }
        }
        // Tampilin matriks identitasnya
        System.out.println("\nBeri matriks identitas dengan ukuran yang sama seperti matriks, menjadi");
        mTemp.displayMatriks();
        // Lakukan OBE
        mTemp.OBEGaussJordan(mTemp.Baris(), mTemp.Kolom());
        //Cek apakah ada baris yg 0 semua
        boolean adaBarisNol = false;
        int l = 0;
        while (l < mTemp.Baris() && !adaBarisNol){
            adaBarisNol = mTemp.semuaBarisNol(mTemp.Baris() - 1, mTemp.Kolom()/2);
            l += 1;
        }
        // Tampilin matriks mTemp setelah dilakukan OBE
        System.out.println("\nDengan melakukan OBE Gauss Jordan, kita dapatkan");
        mTemp.displayMatriks();
        if (!adaBarisNol){
            // Pindah matriks yg sebelah kanan identitas
            for (int i = 0; i < this.baris; i++){
                for (int j = 0; j < this.kolom; j++) {
                    this.isi[i][j] = mTemp.Isi(i, j + (mTemp.Kolom()/2));
                    if (this.isi[i][j] == -0.0){
                        this.isi[i][j] = Math.abs(-0.0);
                    }
                }
            }
            System.out.println("\nMatriks balikannya adalah");
            this.displayMatriks();
        } else{
            System.out.println("\nKarena tidak terbentuk matriks identitas di ruas kiri, maka matriks tidak memiliki balikan");
        }
    }

/* *** PROSEDUR INVERS DENGAN ADJOIN *** */
    void inversMatriksAdj(Matriks self){
        // buat matriks adjoin
        Matriks kofaktor = new Matriks(self.Baris(), self.Kolom());
        Matriks adj = new Matriks(self.Baris(), self.Kolom());
        kofaktor.cofactor(self); // Buat cofactor dari matriks awal
        adj = transpose(kofaktor); // transpose cofactor jadi adjoin
        // Tampilin kofaktor
        System.out.println("\nKita buat kofaktor dari matriks, kita dapatkan kofaktornya, yaitu");
        kofaktor.displayMatriks();
        System.out.println("\nKita transpose kofaktor sehingga kita dapatkan adjoin dari matriks, yaitu");
        adj.displayMatriks();
        // Cari determinan pake reduksi obe
        double det = 0;
        for(int k = 0;k < kofaktor.Kolom();k++){
            det += (kofaktor.Isi(0, k) * self.Isi(0, k));
        }
        System.out.println("\nDari matriks kofaktor kita dapatkan determinan matriks, yaitu " + det);
        //Cek apakah determinan = 0
        if (det == 0){
            System.out.println("Karena determinan matriks 0, matriks tidak memiliki balikan.");
        } else {
            // Invers adalah 1/det dikali adj[b][k]
            for (int b = 0; b < this.baris; b++){
                for (int k = 0; k < this.kolom; k++){
                    this.isi[b][k] = (1/det) * adj.Isi(b, k);
                }
            }
            System.out.println("\nCari matriks balikannya dengan mengalikan 1/det dengan matriks kofaktor.");
            System.out.println("Matriks balikannya adalah");
            this.displayMatriks();
        }
    }

    /* PROSEDUR UNTUK MENDAPATKAN DETERMINAN DENGAN CARA OBE */
    void pengubahOBE(int BarisDiubah, int BarisPengubah, double ratio){
        //IS, this terdefinisi.
        //FS, this pada BarisDiubah = BarisDiubah - ratio*BarisPengubah, untuk setiap kolom
        for(int k = 0;k < this.kolom; k++){
            this.isi[BarisDiubah][k] = this.isi[BarisDiubah][k] - (ratio*this.isi[BarisPengubah][k]);
        }
    }
    void detReduksiOBE(){
        // IS matriks this terdefinisi berbentuk kotak dan sudah terisi
        // matriks this berubah menjadi berbentuk 'segitiga atas'
        for(int bk = 0;bk< this.baris;bk++){
            for(int b = bk+1;b < this.baris;b++){
                if (this.isi[b][bk] != 0){
                    double ratio = this.isi[b][bk]/this.isi[bk][bk];
                    this.pengubahOBE(b, bk, ratio);
                }
            }
        }
    }
    void ditutupi(Matriks m, int btutup, int ktutup){
        //IS matriks m berbentuk square, berisi data. matriks this sudah terdefinisi, berukuran[barism - 1][kolomm -1]
        //FS matriks this berisi data dari matriks m yang sudah 'ditutupi' pada baris btutup dan kolom ktutup
        int bIsi,kIsi;
        for(int b = 0; b < this.baris;b++){
            for(int k = 0 ;k < this.kolom;k++){
                if (b >= btutup){
                    bIsi = b+1;
                }
                else
                    bIsi = b;
                if(k >= ktutup){
                    kIsi = k+1;
                }
                else
                    kIsi = k;
                this.isi[b][k] =  m.isi[bIsi][kIsi];
            }
        }
    }

/* *** METHOD DETERMINAN *** */
    /*PROSEDUR UNTUK MENDAPATKAN DETERMINAN DENGAN CARA COFACTOR */
    void cofactor(Matriks m){
        //IS matriks m terdefinisi, berbentuk square dan sudah terisi. matriks this terdefinisi dengan ukuran yang sama dengan matriks m
        //FS matriks this berisi cofactor dari matriks m
        Matriks temp = new Matriks(m.Baris() - 1, m.Kolom() - 1); //untuk menyimpan array 'yang ditutupi'
        double nilai;
        for(int b = 0;b< m.baris;b++){
            for(int k = 0;k < m.kolom;k++){
                nilai = 1;
                // definisikan nilai sebagai determinan dari array yang sudah ditutupi, lengkap dengan plus minusnya
                temp.ditutupi(m,b,k);
                temp.detReduksiOBE();
                for(int bk = 0;bk < temp.Baris();bk++){
                    nilai = nilai* temp.Isi(bk, bk);
                }
                // cek kalo indeks barisnya genap, berarti + dimulai dari kolom 0(genap) (mulai dari 0)
                if(b % 2 == 0){
                    if (k % 2 != 0){
                        nilai = nilai* -1;
                    } else{}
                } else { //kalo indeks barisnya ganjil, berarti + dimulai dari kolom 1(ganjil)
                    if (k % 2 == 0){
                        nilai = nilai* -1;
                    } else{}
                }
                this.isi[b][k] = nilai;
            }
        }
    }

/* *** REGRESI LINIER BERGANDA *** */
    void regresiLinierBerganda(){
        // IS matriks terdefinisi
        // FS terbentuk solusi regresi linier berganda dari matriks
        // nilai baris di sini = nilai sampel
        int baris = this.baris; int kolom = this.kolom;
        double jml;
        Matriks X = new Matriks(baris, kolom - 1);
        Matriks XT = new Matriks(kolom - 1, baris);
        Matriks Y = new Matriks(baris, 1);
        Matriks XTX = new Matriks(baris, kolom);
        Matriks XTY = new Matriks(baris, 1);
        // Isi matriks X dengan matriks this, tanpa kolom terakhir
        for (int b = 0; b < baris; b++){
            for (int k = 0; k < (kolom - 1); k++){
                X.ubahIsi(b, k, this.isi[b][k]);
            }
        }
        // Isi matriks Y dengan indeks kolom = kolom -1
        for (int b = 0; b < baris; b++){
            Y.ubahIsi(b, 0, this.isi[b][kolom - 1]);
        }
        // Transpose matriks X
        XT = transpose(X);
        // Kali XT dengan X dan Y
        XTX = perkalianMatriks(XT, X);
        XTY = perkalianMatriks(XT, Y);
        // ubah ukuran matriks this ke (peubah_x + 1 x peubah_x + 1 ditambah kolom Y) dan alokasi memori matriks baru
        this.ubahBaris(kolom);
        this.ubahKolom(kolom + 1);
        this.isi = new double[this.baris][this.kolom];
        // Ubah isi matriks this yg ukurannya udah diubah
        for (int b = 0; b < this.baris; b++){
            for (int k = 0; k < this.kolom; k++){
                // Cek apabila b = 0 dan k = 0, maka isinya adalah jumlah sampel, dimana jumlah sampel = baris matriks sebelum diubah
                if (b == 0 && k == 0) {
                    this.isi[b][k] = baris;
                } else if (b == 0 && k != 0 && k != this.kolom-1){ // Cek apabila b = 0 dan k != 0 dan k != kolom - 1, maka diisi jumlah X ke-n dan n = k - 1
                    jml = 0;
                    for (int i = 0; i < X.Baris(); i++){
                        jml += X.Isi(i, k - 1);
                    }
                    this.isi[b][k] = jml;
                } else if (k == 0 && b != 0 && k != this.kolom-1){ // Cek apabila k = 0 dan b != 0 dan k != kolom - 1, maka diisi jumlah X ke-n dan n = k - 1
                    jml = 0;
                    for (int i = 0; i < X.Baris(); i++){
                        jml += X.Isi(i, b - 1);
                    }
                    this.isi[b][k] = jml;
                } else if (k == this.kolom - 1){ // Cek apabila indeks k = kolom terakhir
                    // Cek apabila b = 0
                    if (b == 0){
                        jml = 0;
                        for (int i = 0; i < Y.Baris(); i++){
                            jml += Y.Isi(i, 0);
                        }
                        this.isi[b][k] = jml;
                    } else { // Selaim b = 0
                        this.isi[b][k] = XTY.Isi(b - 1, 0);
                    }
                } else {
                    this.isi[b][k] = XTX.Isi(b-1, k-1);
                }
            }
        }
        // Lakukan gauss-Jordan pada matriks
        this.OBEGauss(this.baris, this.kolom);
    }

/* *** METHOD SPL *** */
    // Menggunakan cara Gauss
    String splGauss(){
        /* KAMUS */
        String jwbakhir = "";
        int i = this.baris-1; // indeks matriks yang sedang diproses
        int jumlahx = this.kolom-1;
        double solusix[] = new double[this.kolom-1];
        boolean homogen = this.isMatriksHomogen();

        /* ALGORITMA */

        // eliminasi Gauss
        this.OBEGauss(this.baris, this.kolom);
        jwbakhir += "Matriks setelah dilakukan eliminasi Gauss:\n";
        for (int ii = 0; ii < this.Baris(); ii++){
            for (int j = 0; j < this.Kolom(); j++) {
                if (this.Isi(ii, j) == -0.0){ // Menghilangkan -0
                    this.isi[ii][j] = Math.abs(-0.0);
                }
                jwbakhir += String.format("%.3f ", this.Isi(ii, j));
            }
            jwbakhir += "\n";
        }
        /*TAMBAHAN JEFF */
        //drop kalo ada baris yang isinya 0 semua
        boolean nolSemua = true;
        int JumBarisYgdiDrop = 0;
        for(int loop = 0;loop < this.baris;loop++){
            nolSemua = true;
            for(int kolom = 0;kolom < this.kolom;kolom++){
                if(this.isi[loop][kolom] != 0){
                    nolSemua = false;
                    break;
                }
            }
            if(nolSemua){
                JumBarisYgdiDrop++;
            }
        }
        this.OBEGaussJordan(this.baris, this.kolom);
        this.baris -= JumBarisYgdiDrop;
        /*TAMBAHAN JEFF SELESAI */

        // matriks homogen
        if (homogen) {
            jwbakhir += "SPL memiliki solusi trivial:\n";
            for (int x=1;x<=jumlahx;x++) {
                jwbakhir += String.format("x%d = 0.000; ", x);
            }
            jwbakhir += "\n";
        }

        // drop baris yang isinya semua 0
        while (semuaBarisNol(i, this.kolom)){
            i--;
        }

        // matriks tidak memiliki solusi
        if (semuaBarisNol(i, this.kolom-1) && this.Isi(i, this.kolom-1) != 0 && !homogen) {
            jwbakhir += "SPL tidak memiliki solusi\n";
        } 
        // matriks memiliki solusi tunggal
        else if (i == this.kolom-2) {
            if (!homogen) {
                for (int j=i;j>=0;j--) {
                    double minus = 0;
                    for (int k=j+1;k<jumlahx;k++) {
                        minus += solusix[k]*this.Isi(j,k);
                    }
                    solusix[j] = this.Isi(j, this.kolom-1) - minus;
                }
                
                // print solusi
                jwbakhir += "SPL memiliki solusi tunggal:\n";
                for (int a=0;a<jumlahx;a++) {
                    jwbakhir += String.format("x%d = %.3f; ", a+1,solusix[a]);
                }
                jwbakhir += "\n";
            }
        }
        // matriks memiliki tak hingga solusi
        else{
            // catat x yang mau dijadikan variabel
            boolean haventFoundNumber = true;
            boolean[] is_variabled = new boolean[this.kolom - 1];
            for(int loop = 0;loop < this.kolom-1;loop++){ //initilize dulu, anggep semua harus dibikin variabel
                is_variabled[loop] = true;
            }
            for(int b = 0;b< this.baris;b++){
                haventFoundNumber = true;
                for(int k = 0;k < this.kolom;k++){
                    if(this.isi[b][k] != 0 && haventFoundNumber){
                        haventFoundNumber = false;
                        is_variabled[k] = false; //kalo dia itu yg pertama, dia gk perlu dibikin variabel
                    }
                }
            }
            // print solusi
            jwbakhir += "SPL memiliki tak hingga solusi:\n";
            char temp = 'a';
            jwbakhir += "Misalkan:\n";
            for(int b = 0;b<this.kolom -1;b++){
                temp = 'a';
                temp += b;
                if(is_variabled[b]){
                    jwbakhir += String.format("x%d = %c\n", b+1,temp);
                }
            }
            jwbakhir += "Maka:\n";
            for(int b = 0;b <this.baris;b++){
                for(int k = 0;k< this.kolom;k++){
                    if(this.isi[b][k] != 0 ||  k == this.kolom - 1){
                        if(k != this.kolom -1){//kalo dia bukan sisi augemented    
                            if(!(is_variabled[k])){
                                jwbakhir += String.format("x%d = ",k+1);
                            }
                            else{ //artinya dia variabel
                                temp = 'a';
                                temp += k;
                                jwbakhir += String.format("%.3f%c + ",(-1*this.isi[b][k]), temp);
                            }
                        }
                        else{ //dia sisi augmented
                            jwbakhir += String.format("%.3f",this.isi[b][k]);
                        }
                    }
                }
                jwbakhir += "\n";
            }
            
        }
        while (jwbakhir.charAt((jwbakhir.length())-1) == '\n') {
            jwbakhir = jwbakhir.substring(0,(jwbakhir.length())-1);
        }

        // print solusi
        System.out.printf("%s\n", jwbakhir);

        // return
        return (jwbakhir);
    }

    // Menggunakan cara Gauss-Jordan
    String splGaussJordan() {
        /* KAMUS */
        String jwbakhir = "";
        int i = this.baris-1; // indeks matriks yang sedang diproses
        int jumlahx = this.kolom-1;
        boolean homogen = this.isMatriksHomogen();

        /* ALGORITMA */

        // eliminasi Gauss-Jordan
        this.OBEGaussJordan(this.baris, this.kolom);
        System.out.println("Matriks setelah dilakukan eliminasi Gauss-Jordan:");
        for (int ii = 0; ii < this.Baris(); ii++){
            for (int j = 0; j < this.Kolom(); j++) {
                if (this.Isi(ii, j) == -0.0){ // Menghilangkan -0
                    this.isi[ii][j] = Math.abs(-0.0);
                }
                jwbakhir += String.format("%.3f ", this.Isi(ii, j));
            }
            jwbakhir += "\n";
        }
        /*TAMBAHAN JEFF */
        //drop kalo ada baris yang isinya 0 semua
        boolean nolSemua = true;
        int JumBarisYgdiDrop = 0;
        for(int loop = 0;loop < this.baris;loop++){
            nolSemua = true;
            for(int kolom = 0;kolom < this.kolom;kolom++){
                if(this.isi[loop][kolom] != 0){
                    nolSemua = false;
                    break;
                }
            }
            if(nolSemua){
                JumBarisYgdiDrop++;
            }
        }
        this.baris -= JumBarisYgdiDrop;
        /*TAMBAHAN JEFF SELESAI */

        // matriks homogen
        if (homogen) {
            jwbakhir += "SPL memiliki solusi trivial:\n";
            for (int x=1;x<=jumlahx;x++) {
                jwbakhir += String.format("x%d = 0.000; ", x);
            }
            jwbakhir += "\n";
        }

        // drop baris yang isinya semua 0
        while (semuaBarisNol(i, this.kolom)){
            i--;
        }

        // matriks tidak memiliki solusi
        if (semuaBarisNol(i, this.kolom-1) && this.Isi(i, this.kolom-1) != 0 && !homogen) {
            jwbakhir += "SPL tidak memiliki solusi";
        } 
        // matriks memiliki solusi tunggal
        else if (i == jumlahx-1) {
            if (!homogen) {        
                // print solusi
                jwbakhir += "SPL memiliki solusi tunggal:\n";
                for (int a=0; a<jumlahx; a++) {
                    jwbakhir += String.format("x%d = %.3f; ", a+1, this.Isi(a, this.Kolom()-1));
                }
                jwbakhir += "\n";
            }
        }
        // matriks memiliki tak hingga solusi
        else{
            // catat x yang mau dijadikan variabel
            boolean haventFoundNumber = true;
            boolean[] is_variabled = new boolean[this.kolom - 1];
            for(int loop = 0;loop < this.kolom-1;loop++){ //initilize dulu, anggep semua harus dibikin variabel
                is_variabled[loop] = true;
            }
            for(int b = 0;b< this.baris;b++){
                haventFoundNumber = true;
                for(int k = 0;k < this.kolom;k++){
                    if(this.isi[b][k] != 0 && haventFoundNumber){
                        haventFoundNumber = false;
                        is_variabled[k] = false; //kalo dia itu yg pertama, dia gk perlu dibikin variabel
                    }
                }
            }
            // print solusi
            jwbakhir += "SPL memiliki tak hingga solusi:\n";
            char temp = 'a';
            jwbakhir += "Misalkan:\n";
            for(int b = 0;b<this.kolom -1;b++){
                temp = 'a';
                temp += b;
                if(is_variabled[b]){
                    jwbakhir += String.format("x%d = %c\n", b+1,temp);
                }
            }
            jwbakhir += "Maka:\n";
            for(int b = 0; b<this.baris; b++) {
                for(int k = 0; k<this.kolom; k++) {
                    if(this.isi[b][k] != 0 || k == this.kolom-1){
                        if(k != this.kolom-1) { //kalo dia bukan sisi augemented
                            if(!(is_variabled[k])) {
                                jwbakhir += String.format("x%d = ",k+1);
                            }
                            else{ //artinya dia variabel
                                temp = 'a';
                                temp += k;
                                jwbakhir += String.format("%.3f%c + ",(-1*this.isi[b][k]), temp);
                            }
                        } else { //dia sisi augmented
                            jwbakhir += String.format("%.3f",this.isi[b][k]);
                        }
                    }
                }
                jwbakhir += "\n";
            }
            
            while (jwbakhir.charAt((jwbakhir.length())-1) == '\n') {
                jwbakhir = jwbakhir.substring(0,(jwbakhir.length())-1);
            }
        }
        // print solusi
        System.out.printf("%s\n", jwbakhir);

        // return
        return (jwbakhir);
    }

    // Menggunakan matriks balikan
    String splInvers() {
        /* KAMUS */
        String jwbakhir;
        double det = 1;
        Matriks solusi;
        Matriks reduksi;
        int jumlahx = this.Kolom()-1;
        Matriks hasil = new Matriks(this.Baris(), 1);

        /* ALGORITMA */
        if (this.Baris() != jumlahx) {
            jwbakhir = "SPL tidak memiliki solusi tunggal";
        } else {
            // pisah matriks augmented menjadi 2 matriks (bentuk Ax=B)
            for (int i=0; i < hasil.Baris(); i++){
                hasil.ubahIsi(i, 0, this.Isi(i, this.Kolom()-1));
            }
            this.kolom -= 1;

            // cari determinan matriks
            reduksi = copyMatriks(this);
            reduksi.detReduksiOBE();
            for (int i=0;i<reduksi.Baris();i++) {
                det *= reduksi.Isi(i, i);
            }

            if (det == 0) {
                jwbakhir = "SPL tidak memiliki solusi tunggal";
            } else {
                // invers matriks
                Matriks kofaktor = new Matriks(this.Baris(), this.Kolom());
                Matriks adj = new Matriks(this.Baris(), this.Kolom());
                kofaktor.cofactor(this); // Buat cofactor dari matriks awal
                adj = transpose(kofaktor); // transpose cofactor jadi adjoin
                for (int b = 0; b < this.baris; b++){
                    for (int k = 0; k < this.kolom; k++){
                        this.isi[b][k] = (1/det) * adj.Isi(b, k);
                    }
                }
                jwbakhir = "Matriks hasil invers adalah:\n";
                for (int i = 0; i < this.Baris(); i++){
                    for (int j = 0; j < this.Kolom(); j++) {
                        if (this.Isi(i, j) == -0.0){ // Menghilangkan -0
                            this.ubahIsi(i, j, Math.abs(-0.0));
                        }
                        jwbakhir += String.format("%.3f ", this.Isi(i, j));
                    }
                    jwbakhir += "\n";
                }

                // matriks invers * matriks hasil
                solusi = perkalianMatriks(this, hasil);

                // print solusi
                jwbakhir += "SPL memiliki solusi:\n";
                for (int i=0;i<solusi.Baris();i++) {
                    jwbakhir += String.format("x%d = %.3f; ", i+1, solusi.Isi(i, 0));
                }
            }    
        }
        // print solusi
        System.out.printf("%s\n", jwbakhir); 

        //return
        return (jwbakhir);
    }

    // Kaidah Cramer
    String splCramer() {
        /* KAMUS */
        String jwbakhir;
        double detMatriks;
        int jumlahx = this.Kolom()-1;
        double dets[] = new double[jumlahx];
        Matriks hasil = new Matriks(this.Baris(), 1);
        Matriks temp;

        /* ALGORITMA */
        if (this.Baris() != jumlahx) {
            jwbakhir = "SPL tidak memiliki solusi tunggal";
        } else {
            // pisah matriks augmented menjadi 2 matriks (bentuk Ax=B)
            for (int i=0; i<hasil.Baris(); i++){
                hasil.ubahIsi(i, 0, this.Isi(i, this.Kolom()-1));
            }
            this.kolom -= 1;

            // cari determinan matriks
            detMatriks = det(this);

            if (detMatriks == 0) {
                jwbakhir = "SPL tidak memiliki solusi tunggal";
            } else {
                // cari determinan x1, x2, x3,...
                for (int i=0; i<this.Kolom(); i++) { // iterasi kolom
                    temp = copyMatriks(this);
                    for (int j=0; j<this.Baris(); j++) { // iterasi baris
                        temp.ubahIsi(j, i, hasil.Isi(j, 0));
                    }
                    dets[i] = det(temp);
                }

                // print solusi
                jwbakhir = String.format("Determinan matriks utama = %.3f\nDeterminan x:\n", detMatriks);
                for (int i=0; i<jumlahx; i++) {
                    jwbakhir += String.format("Dx%d = %.3f; ", i+1, dets[i]);
                }
                jwbakhir += "\nSolusi SPL dapat dihitung dengan cara membagi determinan x ke-x dengan determinan utama. Maka,\n";
                for (int i=0; i<jumlahx; i++) {
                    jwbakhir += String.format("x%d = %.3f; ", i+1, (dets[i]/detMatriks));
                }
            }
        }
        // print solusi
        System.out.printf("%s\n", jwbakhir); 

        // return
        return (jwbakhir);
    }
}