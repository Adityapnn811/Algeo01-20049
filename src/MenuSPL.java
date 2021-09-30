import java.util.*;
public class MenuSPL {
    static Scanner sc = new Scanner(System.in);
    public static void submenuSPL(){
        /* KAMUS */
        String jwbakhir = "";
        int pilihan, pilihanInputMatriks;
        // input pilihan
        System.out.println("Pilih metode penyelesaian SPL:");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        pilihan = Main.RobustIntInput(1, 4);
        // input cara input
        System.out.println("Pilih cara input matriks:");
        System.out.println("1. Dari console");
        System.out.println("2. Dari file");
        pilihanInputMatriks = Main.RobustIntInput(1, 2);
        
        int b,k;
        Matriks m;
        // input dari console
        if (pilihanInputMatriks == 1) {
            System.out.println("Masukkan matriks dalam bentuk augmented.");
            System.out.println("Input jumlah baris matriks:");
            System.out.print("->");
            b = sc.nextInt();
            System.out.println("Input jumlah kolom matriks:");
            System.out.print("->");
            k = sc.nextInt();
            m = new Matriks(b, k);
            System.out.println("Input isi matriks");
            m.isiMatriks();

            /* SPL */
            if (pilihan == 1) {
                jwbakhir = m.splGauss();
            } else if (pilihan == 2) {
                jwbakhir = m.splGaussJordan();
            } else if (pilihan == 3) {
                jwbakhir = m.splInvers();
            } else if (pilihan == 4) {
                jwbakhir = m.splCramer();
            }
            m.konfirmOutputkeFile(1,0,jwbakhir);
        } else if (pilihanInputMatriks == 2) {
            System.out.println("Masukkan nama file yang akan dibaca: ");
            String namaFile = sc.nextLine();
            m = new Matriks(Matriks.FileRow(namaFile), Matriks.FileColumn(namaFile));
            m.ReadMatriksFromFile(namaFile);

            /* SPL */
            if (pilihan == 1) {
                jwbakhir = m.splGauss();
            } else if (pilihan == 2) {
                jwbakhir = m.splGaussJordan();
            } else if (pilihan == 3) {
                jwbakhir = m.splInvers();
            } else if (pilihan == 4) {
                jwbakhir = m.splCramer();
            }
            m.konfirmOutputkeFile(1,0,jwbakhir);
        }
    }
}