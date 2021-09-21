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
                System.out.printf("%.2f ", this.isi[i][j]);
            }
            System.out.println();
        }
    }
}