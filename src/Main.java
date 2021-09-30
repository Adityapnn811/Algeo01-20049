import java.util.*;
import java.io.File;

public class Main{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("HELLOOOO!");
        System.out.println("welcome to matriks calculator, here's a list of stuff i can do!");
        while(true){
            System.out.println("===== MENU UTAMA =====");
            System.out.println("1. Sistem Persamaan Linear");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi Linear Berganda");
            System.out.println("6. Keluar");
            System.out.println("masukan pilihanmu");
            int pilihan = RobustIntInput(1, 6); //note to self, kalau di java harus dikasi nilai dulu(misal 0).
           
            if(pilihan == 1){
                System.out.println("===== MENU SPL =====");
                //pindah ke menu SPL
                MenuSPL.submenuSPL();
            }
            else if(pilihan == 2){
                //pindah ke menu Determinan
                System.out.println("===== MENU Determinan =====");
                MenuDeterminan.SubmenuDeterminan();
            }
            else if(pilihan == 3){
                System.out.println("===== MENU Matriks Balikan =====");
                //pindah ke menu matriks balikan
                MenuInvers.submenuInvers();
            }
            else if(pilihan == 4){
                System.out.println("===== MENU Interpolasi Polinom =====");
                //pindah ke menu interpolasi polinom
                MenuInterpolasi.Interpolasi();
            }
            else if(pilihan == 5){
                System.out.println("===== MENU Regresi Linear Berganda =====");
                MenuRLB.submenuRLB();
            }
            else if(pilihan == 6){
                System.out.println("Thank you for staying here with us. It was indeed fun. See you later!");
                break;
            }
        }//end of while
    }//end of main

    
    public static int RobustIntInput(int Lowest, int Highest){
        //untuk menerima input integer diantara lowest dan highest, kalo lebih dari range itu, atau bukan integer, bakal di loop sampe input bener
        //lowest and highest termasuk ya(jdi input = lowest itu gamasalah).
        int pilihan = 1;
        while(true){
            System.out.print("->");
            try{
                pilihan = sc.nextInt();
                if(pilihan < Lowest || pilihan > Highest){
                    System.out.format("Input diluar range, masukan lagi ya(input antara %d - %d)\n", Lowest,Highest);
                }
                else{
                    break;
                }
            }
            catch(InputMismatchException ime){
                //input bukan integer
                sc.next(); //gangerti gunanya apa, ini liat internet
                System.out.println("input bukan integer, masukan lagi ya");
            }
        }//end of while
        return pilihan;
    }
    public static String RobustFilenameInput(){
        String filename = "";
        System.out.print("->");
        filename = sc.next();
        while(true){
            try{
                // System.out.print("checkpoint");
                Scanner reader = new Scanner(new File(filename)); //ngetes buat ngetrigger error
                // System.out.print("checkpoint2");
                
                reader.close();
                break;
            }
            catch(Exception e){
                System.out.format("Error, mungkin file " + filename + " tidak ditemukan. jangan lupa sertakan extension. coba lagi\n");
                System.out.print("->");
                filename = sc.next();
            }
        }
        return filename;
    }
}