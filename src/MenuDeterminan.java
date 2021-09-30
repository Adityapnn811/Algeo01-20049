import java.util.*;
public class MenuDeterminan{

    static Scanner sc = new Scanner(System.in); 
    public static void SubmenuDeterminan(){
        int pilihan;
        System.out.println("Pilih submenu determinan");
        System.out.println("1. Metode reduksi");
        System.out.println("2. Metode kofaktor");
        System.out.println("3. cancel kak maap typo");
        System.out.println("masukkan pilihan anda(1,2 atau 3): ");
        pilihan = Main.RobustIntInput(1, 3);
        // masukkan matriks, bukan matriks augmented
        if(pilihan == 3){
            System.out.println("woke babai");
        }
        else{
            System.out.print("masukkan matriks, mau dari terminal(1) atau dari file(2)?\n");
            int pilihanInputMatriks = Main.RobustIntInput(1, 2);
            int N;
            Matriks m;
            if(pilihanInputMatriks == 1){
                System.out.println("masukkan ukuran matriks (1 angka saja, bukan augmented): ");
                N = Main.RobustIntInput(1, 10000);
                m = new Matriks(N, N);
                m.isiMatriks();
            }
            else{
                System.out.print("masukkan nama file");
                String filename = Main.RobustFilenameInput();
                int baris = Matriks.FileRow(filename);
                int kolom = Matriks.FileColumn(filename);
                m = new Matriks(baris, kolom);
                m.ReadMatriksFromFile(filename);
                m.ubahKolom(m.Kolom() - 1);
            }
            
            if(m.Kolom() != m.Baris()){
                System.out.println("matriks tidak square, tidak bisa hitung determinan");
            }
            else{
                if (pilihan == 1){ //hitung determinan dengan OBE  
                    System.out.println("matriks setelah di lakukan OBE:");
                    m.detReduksiOBE();
                    m.displayMatriks();
                    System.out.print("Determinannya: ");
                    double hasil  = 1;
                    for(int b = 0;b< m.Baris();b++){
                        System.out.print(m.Isi(b,b));
                        if (b < m.Baris() - 1){
                            System.out.print(" * ");
                        }
                        else{
                            System.out.print(" = ");
                        }
                        hasil *= m.Isi(b,b);
                    }
                    if (hasil == -0.0){ // Menghilangkan -0
                        hasil = Math.abs(-0.0);
                    }
                    System.out.println(hasil);
                    m.konfirmOutputkeFile(2, hasil, "dummy");
                }
                if(pilihan == 2){ //hitung dengan cofactor
                    Matriks mCof = new Matriks(m.Baris(), m.Kolom());
                    mCof.cofactor(m);
                    System.out.println("berikut matriks cofactornya:");
                    mCof.displayMatriks();
                    System.out.println("mau menggunakan cofactor baris(1) atau kolom(2)?");
                    int pilihanbk = sc.nextInt();
                    if (pilihanbk == 1){ //pake baris start
                        System.out.println("mau baris ke berapa(mulai dari 0):");
                        int bariske = sc.nextInt();
                        System.out.print("determinan: = ");
                        double hasil = 0;
                        for(int k = 0;k < mCof.Kolom();k++){
                            System.out.print(mCof.Isi(bariske, k));
                            System.out.print("*");
                            System.out.print(m.Isi(bariske, k));
                            if (k < mCof.Kolom() -1 ){
                                System.out.print(" + ");
                            }
                            else{
                                System.out.print(" = ");
                            }
                            hasil += (mCof.Isi(bariske,k)*m.Isi(bariske, k));
                        }
                        if (hasil == -0.0){ // Menghilangkan -0
                            hasil = Math.abs(-0.0);
                        }
                        System.out.println(hasil);
                        m.konfirmOutputkeFile(2, hasil, "dummy");
                    }//pake baris end
                    if (pilihanbk == 2){ //pake kolom start
                        System.out.println("mau kolom ke berapa(mulai dari 0):");
                        int kolomke = sc.nextInt();
                        System.out.print("determinan: = ");
                        double hasil = 0;
                        for(int b = 0;b < mCof.Baris();b++){
                            System.out.print(mCof.Isi(b, kolomke));
                            System.out.print("*");
                            System.out.print(m.Isi(b, kolomke));
                            if (b < mCof.Baris() -1 ){
                                System.out.print(" + ");
                            }
                            else{
                                System.out.print(" = ");
                            }
                            hasil = hasil + (mCof.Isi(b,kolomke)*m.Isi(b, kolomke));
                        }
                        if (hasil == -0.0){ // Menghilangkan -0
                            hasil = Math.abs(-0.0);
                        }
                        System.out.println(hasil);
                        m.konfirmOutputkeFile(2, hasil, "dummy");
                    }//pake kolom end
                }
            }
        }
    }
}