import java.util.*;
public class MenuRLB {
    static Scanner sc = new Scanner(System.in);
    public static void submenuRLB(){
        // Pilih input dari terminal atau file
        System.out.println("Input dari terminal(1) atau file(2)?");
        int pilihan = Main.RobustIntInput(1, 2);
        Matriks m;
        int peubah_x = 0;
        if (pilihan == 1){
            // input n, yaitu jumlah baris. Jumlah kolom nya = baris + 1 soalnya augmented
            System.out.print("Masukkan nilai k, yaitu jumlah sampel\n->");
            int n = sc.nextInt();
            System.out.print("Masukkan nilai n, yaitu jumlah peubah x\n->");
            peubah_x = sc.nextInt();
            //Buat matriks n x n
            m = new Matriks(n, peubah_x + 1);
            // isi matriks dengan x1n, dll, Bentuk matriks augmentedd!!
            m.isiMatriks();
            m.regresiLinierBerganda();
            // m.displayMatriks(); // ngecek
        } else {
            System.out.println("Masukkan nama file yang akan dibaca: ");
            String namaFile = sc.nextLine();
            int baris = Matriks.FileRow(namaFile);
            int kolom = Matriks.FileColumn(namaFile);
            peubah_x = kolom-1;
            m = new Matriks(baris, kolom);
            m.ReadMatriksFromFile(namaFile);
            m.regresiLinierBerganda();
            // m.displayMatriks(); // ngecek
        }
        //tentuin b0 b1 b2 dkk
        System.out.format("masukkan nilai x sejumlah %d yang akan ditaksir nilainya\n->",peubah_x);
        double[] x = new double[peubah_x + 1];
        x[0] = 1;
        for(int loop = 1;loop <= peubah_x;loop++){
            x[loop] = sc.nextDouble();
        }

        m.OBEGaussJordan(m.Baris(), m.Kolom());
        //m.displayMatriks();
        double hasil = 0;
        double temp  = 0;
        System.out.println("hasil taksirannya adalah:");
        System.out.print("y = ");
        String line = "dengan nilai\n";
        for(int loop = 1;loop <= peubah_x;loop++){
            line = line + "x" + loop + " = "+ x[loop] + "\n";
        }
        line = line + "\ny = ";

        for(int bk = 0;bk < m.Baris();bk++){
            temp = m.Isi(bk, m.Kolom() - 1);
            if(temp != 0){
                System.out.format("%.2f",temp);
                line += temp;
                if( bk != 0 && temp != 0) {
                    System.out.format("*x%d", bk);
                    line += "*x" + bk;
                }
                if(bk< m.Baris()-1){
                    System.out.print(" + ");
                    line += " + ";
                }
            }
            hasil = hasil + (temp*x[bk]);
        }
        line =line + " = " + hasil;
        System.out.format("\njika dimasukkan nilai x pada rumus tersebut, akan didapat hasilnya yaitu");
        System.out.format(" = %.2f\n", hasil);
        m.konfirmOutputkeFile(5, 0, line);
    }
}
