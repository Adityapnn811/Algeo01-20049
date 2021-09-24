import java.util.*;
import java.lang.Math;
import java.nio.file.Path;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
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
    void ubahBaris(int b){
        this.baris = b;
    }
    void ubahKolom(int k){
        this.kolom = k;
    }

    // Copy matriks dan menerima parameter mIn dan mengeluarkan matriks mOut
    Matriks copyMatriks(Matriks mIn){
        Matriks mOut = new Matriks(mIn.Baris(), mIn.Kolom());
        for (int i = 0; i < mOut.Baris(); i++){
            for (int j = 0; j < mOut.Kolom(); j++){
                ubahIsi(i, j, mIn.Isi(i, j));
            }
        }
        return mOut;
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
    boolean semuaBarisNol(int lastIdxBaris, int jmlKolom){
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

    /* *** PROSEDUR OBE *** */
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
                        }       
                    }
                }
                b_pass -= 1;
                k_pass = 0;
            }
        }
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
        for (int i = 0; i < this.baris; i++){
            for (int j = 0; j < this.kolom; j++) {
                System.out.printf("%.3f ", this.isi[i][j]);
            }
            System.out.println();
        }
    }

    public static int FileRow(String FileName){
        //mengembalikan jumlah kolom dari suatu file. kalo filenya gk ketemu, return -1
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
        catch (FileNotFoundException e) {
            System.out.println("FILENYA GADA WOI BOONG LU(ato bisa juga typo)");
            e.printStackTrace();
        }
        return lines;
    }
    public static int FileColumn(String FileName){
        //mengembalikan jumlah baris dari suatu file. kalo filenya gk ketemu, return -1
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
        catch (FileNotFoundException e) {
            System.out.println("FILENYA GADA WOI BOONG LU(ato bisa juga typo)");
            e.printStackTrace();
        }
        return kolom;
    }

    public static Matriks ReadMatriksFromFile(String FileName, int baris, int kolom){ 
    //inget kalo dari file itu augmented, jdi kalo butuh yg left side only, jangan lupa modif matriksnya sendiri
        Matriks m = new Matriks(baris, kolom);
        try {
            //buat matriksnya dulu
            int b = 0;
            int k = 0;
            // isi matriks
            File myObj = new File(FileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextDouble()) {
              double data = myReader.nextDouble();
              m.ubahIsi(b, k, data);
              //next data in matriks
              if(k < m.Kolom() - 1){
                  k++;
              }
              else{
                  k = 0;
                  b++;
              }
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("FILENYA GADA WOI BOONG LU(ato bisa juga typo)");
            e.printStackTrace();
        }
        return m;
    }

    void outputToFile(){
        try{
            FileWriter writer = new FileWriter("hasil.txt"); 
            for (int b = 0; b < this.baris; b++){
                for (int k = 0; k < this.kolom; k++) {
                    writer.write(Float.toString((float) this.isi[b][k]));
                    if (k != this.kolom - 1){
                        writer.write(" ");
                    }
                }
                writer.write("\n");
            }
            System.out.println("File berhasil disimpan di hasil.txt");
            writer.close();
        } catch (IOException e){
            System.out.println("Terjadi sebuah error");
            e.printStackTrace();
        }
    }

    void konfirmOutputkeFile(){
        char konfirm;
            do{
                System.out.print("Apakah Anda ingin menyimpan hasil ke sebuah file? (y/n)\n->");
                konfirm = sc.next().charAt(0);
                if (konfirm != 'y' && konfirm != 'n'){
                    System.out.println("Input harus berupa y/n");
                }
            } while (konfirm != 'y' && konfirm != 'n');
            if (konfirm == 'y'){
                this.outputToFile();
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
        // Lakukan OBE
        mTemp.OBEGaussJordan(mTemp.Baris(), mTemp.Kolom());
        //Cek apakah ada baris yg 0 semua
        boolean adaBarisNol = false;
        int l = 0;
        while (l < mTemp.Baris() && !adaBarisNol){
            adaBarisNol = mTemp.semuaBarisNol(mTemp.Baris() - 1, mTemp.Kolom()/2);
            l += 1;
        }
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
            System.out.println("Matriks balikannya adalah");
            this.displayMatriks();
        } else{
            System.out.println("Matriks tidak memiliki balikan!");
        }
    }

    /* PROSEDUR INVERS DENGAN ADJOIN */
    void inversMatriksAdj(Matriks self){
        // buat matriks adjoin
        Matriks adj = new Matriks(1, 1);
        adj = FungsiDeterminan.cofactor(self); // Buat cofactor dari matriks awal
        adj = transpose(adj); // transpose cofactor jadi adjoin
        adj.displayMatriks(); // buat ngecek
    }
}
