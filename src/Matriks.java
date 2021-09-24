import java.util.*;

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
    /* *** Fungsi primitif dan basic lain *** */
    int Baris(){
        // buat print baris dari matriks 
       return this.baris; 
    }
    int Kolom(){
        // buat print kolom dari matriks 
        return this.kolom;
    }
    double Isi(int b,int k){
        // buat print isi[b][k] dari matriks
        return this.isi[b][k];
    }
    void ubahIsi(int b,int k, double newValue){
        // buat nggganti isi[b][k] menjadi new value
        this.isi[b][k] = newValue;
    }
    void copyMatriks(Matriks mIn, Matriks mOut){
        mOut = new Matriks(mIn.Baris(), mIn.Kolom());
        for (int i = 0; i < mOut.Baris(); i++){
            for (int j = 0; j < mOut.Kolom(); j++){
                ubahIsi(i, j, mIn.Isi(i, j));
            }
        }
    }

    // Buat tukar baris tukar kolom
    void tukarBaris(int b1, int b2){
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
        int i;
        double temp;
        for (i = 0; i < this.baris; i++){
            temp = this.Isi(k1, i);
            this.ubahIsi(k1, i, this.Isi(k2, i));
            this.ubahIsi(k2, i, temp);
        }
    }

    // Mengecek apakah semua elemen pada baris = 0
    boolean semuaBarisNol(int b, int k){
        boolean semuaNol = true;
        for (int i = 0; i < b; i++){
            for (int j = 0; j < k; j++){
                if (this.isi[i][j] != 0){
                    semuaNol = false;
                }
            }
        }
        return semuaNol;
    }

    // Fungsi OBE
    void OBEGaussJordan(int b, int k){
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
                // System.out.println("Setelah membuat leadin one");
                // this.displayMatriks();
                // Kurangi baris lain
                // cek apakah sudah pass terakhir
                if (b_pass != b-1){
                    for (int i = b_pass + 1; i < b; i++){
                        if (this.isi[i][k_pass] != 0){
                            double pengali = this.isi[i][k_pass];
                            this.isi[i][k_pass] = 0;
                            for (int j = k_pass + 1; j < k; j++){
                                this.isi[i][j] -= this.Isi(b_pass, j) * pengali;
                            }       
                        }
                    }
                }
                // System.out.println("Setelah mengurangi baris lain");
                // this.displayMatriks();
                b_pass += 1;
                k_pass += 1;
                b_ganti = b_pass;
            }
        }
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
                        }       
                    }
                }
                b_pass -= 1;
                k_pass = 0;
            }
        }
    }

    /* PROSEDUR OBE GAUSS */
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
                // System.out.println("Setelah membuat leadin one");
                // this.displayMatriks();
                // Kurangi baris lain
                // cek apakah sudah pass terakhir
                if (b_pass != b-1){
                    for (int i = b_pass + 1; i < b; i++){
                        if (this.isi[i][k_pass] != 0){
                            double pengali = this.isi[i][k_pass];
                            this.isi[i][k_pass] = 0;
                            for (int j = k_pass + 1; j < k; j++){
                                this.isi[i][j] -= this.Isi(b_pass, j) * pengali;
                            }       
                        }
                    }
                }
                // System.out.println("Setelah mengurangi baris lain");
                // this.displayMatriks();
                b_pass += 1;
                k_pass += 1;
                b_ganti = b_pass;
            }
        }
    }

    /* ********** KELOMPOK BACA/TULIS ********** */
    void isiMatriks() {
        for (int i = 0; i < this.baris; i++){
            for (int j = 0; j < this.kolom; j++) {
                this.isi[i][j] = sc.nextDouble();
            }
        }
    }

    // Menampilkan matriks
    void displayMatriks(){
        for (int i = 0; i < this.baris; i++){
            for (int j = 0; j < this.kolom; j++) {
                System.out.printf("%.3f ", this.isi[i][j]);
            }
            System.out.println();
        }
    }

    /* PROSEDUR INVERS DENGAN OBE */
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
        // Lakukan OBE
        mTemp.OBEGaussJordan(mTemp.Baris(), mTemp.Kolom());
        // mTemp.displayMatriks(); // buat ngecek
        //Cek apakah ada baris yg 0 semua
        boolean adaBarisNol = false;
        int l = 0;
        while (l < mTemp.Baris() && !adaBarisNol){
            adaBarisNol = mTemp.semuaBarisNol(mTemp.Baris(), mTemp.Kolom()/2);
            l += 1;
        }
        if (!adaBarisNol){
            // Pindah matriks yg sebelah kanan identitas
            for (int i = 0; i < this.baris; i++){
                for (int j = 0; j < this.kolom; j++) {
                    this.isi[i][j] = mTemp.Isi(i, j + (mTemp.Kolom()/2));
                    if (this.isi[i][j] == -0){
                        this.isi[i][j] *= -1;
                    }
                }
            }
            System.out.println("Matriks balikannya adalah");
            this.displayMatriks();
        } else{
            System.out.println("Matriks tidak memiliki balikan!");
        }
        // this.displayMatriks(); // buat ngecek
    }

    /* PROSEDUR INVERS DENGAN ADJOIN */
    void inversMatriksAdj(){
        
    }
}

