import java.util.*;
public class MenuRLB {
    static Scanner sc = new Scanner(System.in);
    public static void submenuRLB(){
        // Pilih input dari terminal atau file
        System.out.println("Input dari terminal(1) atau file(2)?");
        int pilihan = Main.RobustIntInput(1, 2);
        if (pilihan == 1){
            // input n, yaitu jumlah baris. Jumlah kolom nya = baris + 1 soalnya augmented
            System.out.print("Masukkan nilai N, yaitu jumlah sampel\n->");
            int n = sc.nextInt();
            System.out.print("Masukkan nilai k, yaitu jumlah peubah x\n->");
            int peubah_x = sc.nextInt();
            //Buat matriks n x n
            Matriks m = new Matriks(n, peubah_x + 1);
            // isi matriks dengan x1n, dll, Bentuk matriks augmentedd!!
            m.isiMatriks();
            m.regresiLinierBerganda();
            m.displayMatriks(); // ngecek
        } else {
            System.out.println("Masukkan nama file yang akan dibaca: ");
            String namaFile = sc.nextLine();
            int baris = Matriks.FileRow(namaFile);
            int kolom = Matriks.FileColumn(namaFile);
            Matriks m = new Matriks(baris, kolom);
            m.ReadMatriksFromFile(namaFile);
            m.regresiLinierBerganda();
            m.displayMatriks(); // ngecek
        }
    }
}
