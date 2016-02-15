/**
 * Created by acastells on 12/02/16.
 */
import java.util.Scanner;
public class tdaLlista {
    private final int MAXIM = 100;
    String[] llista = new String[MAXIM];
    int total = 0;

    public   static void main(String[] args){
        new tdaLlista().inici();

    }

    void inici(){

        String[] menu = {   //Array amb les opcions del menu
                "1\tInserir",
                "2\tLocalitzar",
                "3\tRecuperar",
                "4\tSuprimir",
                "5\tSuprimir dada",
                "6\tAnul·la",
                "7\tPrimer",
                "8\tDarrer",
                "9\tImprimir",
                "10\tOrdena",
                "11\tSurt"
        };
        int opcio = funcioMenu(menu);
        while(opcio < menu.length){
            switch(opcio){
                case 1:
                    inserir();
                case 2:
                    localitzar();
                case 3:
                    recuperar();
                case 4:
                    suprimir();
                case 5:
                    suprimir_dada();
                case 6:
                    anula();
                case 7:
                    primer();
                case 8:
                    darrer();
                case 9:
                    imprimir();
                case 10:
                    ordena();
            }
        }

    }

    int funcioMenu(String[] menu){  //Retorna la opcio del menu
        for (int i = 0; i < menu.length; i++){
            System.out.println(menu[i]);
        }
        int opcio = readInt("Introdueix una opció del menú: ");
        return opcio;
    }


    /*Funcions auxiliars*/

    int readInt(String missatge){
        Scanner sc = new Scanner(System.in);
        boolean correcte;
        int newInt = 0;
        do{
            System.out.print(missatge);
            correcte = sc.hasNextInt();
            if (correcte){
                newInt = sc.nextInt();
            }
            else{
                sc.nextLine();
            }
        }
        while(!correcte);
        sc.close();
        return newInt;
    }

    String readString(String missatge){
        Scanner sc=new Scanner(System.in);
        System.out.print(missatge);
        String newString= sc.nextLine();
        sc.close();
        return newString;
    }

    boolean llistaBuida(){
        if (total == 0) {
            return true;
        }
        else{
            return false;
        }
    }
}

