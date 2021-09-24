import java.util.*;

public class Main{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int pilMenu;
        System.out.println("Pilih menu yang Anda inginkan:");
        pilMenu = sc.nextInt();
        if (pilMenu == 1){
            MenuDeterminan.SubmenuDeterminan();
        } else if(pilMenu == 2){
            MenuInvers.submenuInvers();
        }
    }
}
