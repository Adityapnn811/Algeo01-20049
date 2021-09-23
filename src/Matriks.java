import java.util.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors

public class Matriks{
    static Scanner sc = new Scanner(System.in);
    
    // Deklarasi atribut di matriks
    private int baris, kolom;
    private double[][] isi;
    
  /* *** Konstruktor membentuk Matrix *** */
    public Matriks(int b, int k){
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
    void ubahBaris(int b){
        this.baris = b;
    }
    void ubahKolom(int k){
        this.kolom = k;
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
    public void displayMatriks(){
        for (int i = 0; i < this.baris; i++){
            for (int j = 0; j < this.kolom; j++) {
                System.out.printf("%.2f ", this.isi[i][j]);
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
            System.out.println("FILENYA GADA WOI BOONG LU row(ato bisa juga typo)");
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
            System.out.println("FILENYA GADA WOI BOONG LU col(ato bisa juga typo)");
            e.printStackTrace();
        }
        return kolom;
    }

    public static Matriks ReadMatriksFromFile(String FileName,int baris, int kolom){ 
    //inget kalo dari file itu augmented, jdi kalo butuh yg left side only, jangan lupa modif matriksnya sendiri
        System.out.println("tessssssss");
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
}