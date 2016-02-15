/**
 * Created by acastells on 12/02/16.
 */
import java.io.IOException;
import java.util.Scanner;
public class tdaLlista {

    private final int MAXIM = 100;
    String[] llistaArray = new String[MAXIM];
    int total = 0;

    public static void main(String[] args) {
        tdaLlista llistaCognoms = new tdaLlista();
        llistaCognoms.inici();
    }

    public void inici() {
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
        int opcio = 0;
        while (opcio < menu.length) {
            opcio = funcioMenu(menu);
            imprimir("Menu DEBUG");
            switch (opcio) {
                case 1:
                    int posicio = 0;
                    do {
                        posicio = readInt("Introdueix una posició: ");
                    }
                    while (!potInserir(posicio));
                    String cognom = readString("Introdueix un Cognom: ");
                    if (inserir(cognom, posicio)) {
                        imprimir("S'ha inserit correctament");
                        total++;
                    }
            }
            break;
            /*
                case 2:
                    localitzar();
                    break;
                case 3:
                    recuperar();
                    break;
                case 4:
                    suprimir();
                    break;
                case 5:
                    suprimir_dada();
                    break;
                case 6:
                    anula();
                    break;
                case 7:
                    primer();
                    break;
                case 8:
                    darrer();
                    break;
                case 9:
                    imprimir();
                    break;
                case 10:
                    ordena();
                    break;*/
        }
    }

    /*Funcio Inserir*/
    boolean inserir(String cognom, int posicio) {
        if (llistaBuida()) {
            llistaArray[posicio] = cognom;
        }
        else{
            desplaçarLlista(posicio);
            llistaArray[posicio] = cognom;
        }
        return true;
    }

    /*Funcions auxiliars*/
    int funcioMenu(String[] menu) {  //Retorna la opcio del menu
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
        int opcio = readInt("Introdueix una opció del menú: ");
        return opcio;
    }

    boolean potInserir(int posicio) {
        if (total == 0 && posicio == 0 || total > 0 && posicio <= total) {
            return true;
        } else {
            return false;
        }
    }

    int readInt(String missatge) {           //Funció per llegir enters
        Scanner sc = new Scanner(System.in);
        int newInt = 0;
        boolean finalitzat;
        do{
            System.out.print(missatge);
            finalitzat = sc.hasNextInt();
            if(finalitzat){
                newInt = sc.nextInt();
                sc.nextLine();
            }
            else{
                sc.nextLine();
            }

        }
        while(!finalitzat);
        return newInt;
    }

    String readString(String missatge) {     //Funció per llegir Strings
        Scanner sc = new Scanner(System.in);
        System.out.print(missatge);
        String newString = sc.nextLine();
        sc.close();
        return newString;
    }

    boolean llistaBuida() {                  //Funció per saber si la llista està buida
        if (total == 0) {
            return true;
        } else {
            return false;
        }
    }

    void imprimir(String text) {             //Funció per imprimir un missatge
        System.out.println(text);
    }

     void enterContinuar() {
         System.out.println("Prem enter per continuar... ");
     }

    void desplaçarLlista(int posicio){
        for(int i = total;i > posicio;i--){
            llistaArray[i] = llistaArray[i-1];
        }
    }
}

