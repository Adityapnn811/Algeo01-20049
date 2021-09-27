import java.util.*;
import java.lang.Math;
public class MenuRLB {
    static Scanner sc = new Scanner(System.in);
    public static void submenuRLB(){
        // Pilih input dari terminal atau file
        System.out.println("Input dari terminal(1) atau file(2)?");
        int pilihan = Main.RobustIntInput(1, 2);
        Matriks m;
        if (pilihan == 1){
            // input n, yaitu jumlah baris. Jumlah kolom nya = baris + 1 soalnya augmented
            System.out.print("Masukkan nilai N, yaitu jumlah sampel\n->");
            int n = sc.nextInt();
            System.out.print("Masukkan nilai k, yaitu jumlah peubah x\n->");
            int peubah_x = sc.nextInt();
            //Buat matriks n x n
            m = new Matriks(n, peubah_x + 1);
            // isi matriks dengan x1n, dll, Bentuk matriks augmentedd!!
            m.isiMatriks();
            m.regresiLinierBerganda();
            //m.displayMatriks(); // ngecek
        } else {
            System.out.println("Masukkan nama file yang akan dibaca: ");
            String namaFile = sc.nextLine();
            int baris = Matriks.FileRow(namaFile);
            int kolom = Matriks.FileColumn(namaFile);
            m = new Matriks(baris, kolom);
            m.ReadMatriksFromFile(namaFile);
            m.regresiLinierBerganda();
            //m.displayMatriks(); // ngecek
        }
        //tentuin b0 b1 b2 dkk
        System.out.print("masukkan nilai x yang akan ditaksir nilainya\n->");
        double x = sc.nextDouble();
        m.OBEGaussJordan(m.Baris(), m.Kolom());
        //m.displayMatriks();
        double hasil = 0;
        double temp  = 0;
        System.out.println("hasil taksirannya adalah:");
        System.out.print("y = ");
        String line = "dengan nilai x = " + x + ",\ny = ";

        for(int bk = 0;bk < m.Baris();bk++){
            temp = m.Isi(bk, m.Kolom() - 1);
            if(temp != 0){
                System.out.format("%.2f",temp);
                line += temp;
                if( bk != 0 && temp != 0) {
                    System.out.format("*x^(%d)", bk);
                    line += "*x^" + bk;
                }
                if(bk< m.Baris()-1){
                    System.out.print(" + ");
                    line += " + ";
                }
            }
            hasil = hasil + (m.Isi(bk, bk)*Math.pow(x, bk));
        }
        line =line + " = " + hasil;
        System.out.format("\njika dimasukkan nilai x = %.2f pada rumus tersebut, akan didapat hasilnya yaitu",x);
        System.out.format(" = %.2f\n", hasil);
        m.konfirmOutputkeFile(5, 0, line);
    }
}
