import java.util.*;
public class MenuSPL {
    static Scanner sc = new Scanner(System.in);
    public static void submenuSPL(){
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
                m.splGauss();
            } else if (pilihan == 2) {
                m.splGaussJordan();
            } else if (pilihan == 3) {
                m.splInvers();
            } else if (pilihan == 4) {
                m.splCramer();
            }

            //m.konfirmOutputkeFile();
        } else if (pilihanInputMatriks == 2) {
            System.out.println("Masukkan nama file yang akan dibaca: ");
            String namaFile = sc.nextLine();
            m = new Matriks(Matriks.FileRow(namaFile), Matriks.FileColumn(namaFile));
            m.ReadMatriksFromFile(namaFile);

            /* SPL */
            if (pilihan == 1) {
                m.splGauss();
            }
            //m.konfirmOutputkeFile();
        }
    }
    //jeffrey coba kerja
    // private void PilihanSatuDua(Matriks m){
    // //IS matriks m sudah berisi matriks inputan dari user, berbentuk augmented.
    // //FS melakukan perhitungan dengan gauss atau gauss jordan, trus bisa ngecover semua tipe output. m nya rusak ya, jdi datanya keubah2 dalemnya
    // //aku pisah pilihan satu dua karena kalo pilihan 3 4, ngeceknya beda(keknya ngecek pake determinan)
    //     m.OBEGaussJordan(m.Baris(), m.Kolom());
    //     m.displayMatriks();
    //     int MatriksType = 0; // 0 artinya punya 1 jawaban, 1 punya banyak jawaban, 2 gapunya jawaban
    //     int BarisEfektif = m.Baris();
    //     for(int b = 0;b< m.Baris();b++){
    //         if(m.semuaBarisNol(b, m.Kolom() - 1)){
    //             if(m.Isi(b, m.Kolom()-1) == 0)
    //             BarisEfektif -= 1;
    //             else{//berarti bentuknya 0 0 0 1, gk punya jawaban
    //                 System.out.println("gapunya jawaban");
    //                 MatriksType = 2;
    //                 break;
    //             }
    //         }
    //     }
    //     m.ubahBaris(BarisEfektif);
    //     if (MatriksType != 2){
    //         if(m.Baris() == m.Kolom() - 1){//kalo jumlah baris dan kolom sama(augmented sidenya gk termasuk), berarti dia punya satu jawaban
    //             System.out.println("punya satu jawaban, hasilnya adalah:");
    //             MatriksType = 0;
    //             for(int b = 0;b< m.Baris();b++){
    //                 System.out.format("x(%d) = %d\n", b+1, m.Isi(b, m.Kolom()-1));
    //             }
    //         }
    //         else{//punya banyak jawaban
    //             System.out.println("punya banyak jawaban,");
    //             m.displayMatriks();
    //         }
    //     }
    // }//end of pilihan satu dua
}