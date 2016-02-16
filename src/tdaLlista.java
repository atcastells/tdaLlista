/**
 * Created by acastells on 12/02/16.
 *
 * //Mateu es raro
 */
import java.util.Scanner;
public class tdaLlista {

    public final int MAXIM = 100;
    public String[] llistaArray = new String[MAXIM];
    public int total = 0;

    public static void main(String[] args) {
        new tdaLlista().inici();
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
            switch (opcio) {
                case 1:
                    int posicio;
                    do {
                        posicio = readInt("Introdueix una posició entre "+0+" i "+total+": ");
                    }
                    while (!potInserir(posicio));
                    String cognom = readString("Introdueix un Cognom: ");
                    if (inserir(cognom, posicio)) {
                        imprimir("S'ha inserit correctament");
                    }
                    break;

                case 2:
                    cognom = readString("Introdueix un Cognom per buscar: ");
                    int posicioCognom = localitzar(cognom);
                    if(posicioCognom == -1){
                        imprimir("No s'ha trobat el cognom.");
                    }
                    else{
                        imprimir("El cognom '"+cognom+"' està a la posició nº: "+posicioCognom);
                    }
                    break;
                case 3:
                    posicio = readInt("Introdueix una posició per recuperar les dades que té: ");
                    cognom = recuperar(posicio);
                    if (cognom.equalsIgnoreCase(null)){

                    }
                    else{
                        imprimir("A la posició nº"+posicio+" estaba el cognom: "+cognom);
                    }
                    break;/*
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
                */case 9:
                    imprimirArray();
                    break;/*
                case 10:
                    ordena();
                    break;*/
            }
        }
    }

    /*Funcio Inserir*/
    boolean inserir(String cognom, int posicio) {
        if(!potInserir(posicio)){
            return false;
        }
        if (llistaBuida()) {
            llistaArray[posicio] = cognom;
            total++;
            return true;
        }
        else{
            desplaçarLlista(posicio);
            llistaArray[posicio] = cognom;
            total++;
            return true;
        }

    }

    /*Funció Imprimir Array*/
    void imprimirArray(){
        if(llistaBuida()){
            imprimir("La llista està buida");
        }
        else {
            for (int i = 0; i < total; i++) {
                imprimir("#" + i + "\t" + llistaArray[i]);
            }
        }
        enterContinuar();
    }

    /*Funció Localitzar*/
    int localitzar(String cognom){
        int posicio = 0;
        for (int i = 0; i < total; i++){
            if(llistaArray[i].equalsIgnoreCase(cognom)){
                return i;
            }
        }
        return -1;
    }

    /*Funció recuperar*/
    String recuperar(int posicio){
        if(posicio < total && !llistaBuida()){
            return llistaArray[posicio];
        }
        else{
            return null;
        }
    }
    /*Funcions auxiliars*/
    int funcioMenu(String[] menu) {                                     //Retorna la opcio del menu
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
        int opcio = readInt("Introdueix una opció del menú: ");
        return opcio;
    }

    boolean potInserir(int posicio) {                                   //Boolea per cuan no es pot inserir
        if (total == 0 && posicio == 0 || total > 0 && posicio <= total || total == MAXIM) {
            return true;
        } else {
            return false;
        }
    }

    int readInt(String missatge) {                                      //Funció per llegir enters
        int newInt;
        Scanner entrada = new Scanner(System.in);
        boolean preparat = entrada.hasNextInt();
        do {
            imprimir(missatge);
            newInt = entrada.nextInt();
        }
        while (!preparat);
        return newInt;
    }

    String readString(String missatge) {                                //Funció per llegir Strings
        Scanner sc = new Scanner(System.in);
        System.out.print(missatge);
        String newString = sc.nextLine();
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
         Scanner sc = new Scanner(System.in);
         System.out.println("Prem enter per continuar... ");
         sc.nextLine();
     }

    void desplaçarLlista(int posicio){
        for(int i = total;i > posicio;i--){
            llistaArray[i] = llistaArray[i-1];
        }
    }
}

