/**
 *          Created by acastells on 12/02/16.
 *          Grup  2:    Oscar Oliver, Adrià Montoro, Aaron Castells
 *
 *          Implementació del TDA Llista en java utilitzant funcions
 *
 *          Algorisme:
 *                      S'imprimeix el menú
 *                      S'elegeix una opció del menú
 *                      Mentre la opció sigui mes menuda que 11
 *                           S'executa la opció seleccionada
 *                      Sinó
 *                             Surt
 *
 */
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class tdaLlista {


    /*Variables Globals*/

    /**
     * Constant màxim Array
     */
    public final int CAPACITAT= 100;

    /**
     * Array que conté els cognoms
     */
    public String[] arrayLlista = new String[CAPACITAT];

    /**
     * Variable elements array
     */
    public int quantitat = 0;

    public static void main(String[] args) {
        new tdaLlista().inici();
    }

    public void inici() {
        String[] menuOptions = {   //Array amb les opcions del menu
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
                "11\tTest",
                "12\tSurt"
        };
        int opcio = 0;  //Control switch

        /*Mentre la opció sigui mes menuda que 11*/

        while (opcio < menuOptions.length) {

            /* S'imprimeix el menú i es selecciona una opció*/
            opcio = funcioMenu(menuOptions);

            /*S'executa la opció seleccionada*/
            switch (opcio) {
                case 1:
                       int  pos = readInt("Introdueix una posició entre "+1+" i "+(quantitat+1)+": ") -1;
                    String cognom = readString("Introdueix un Cognom: ");
                    if (inserir(cognom, pos)) { //Cridem a la funció inserir amb les dades anteriors
                        imprimir("S'ha inserit correctament");
                    }
                    else {
                        imprimir("Posició incorrecta");
                    }
                    break;
                case 2:
                    cognom = readString("Introdueix un Cognom per buscar: ");
                    int posCognom = localitzar(cognom); //Cridem la funció localitzar amb el cognom introduït i retornem la posició
                    if(posCognom == -1){
                        imprimir("No s'ha trobat el cognom.");
                    }
                    else{
                        imprimir("El cognom '"+cognom+"' està a la posició nº: "+(posCognom+1));
                    }
                    break;
                case 3:
                    pos = readInt("Introdueix una posició per recuperar les dades que té: ")-1;
                    cognom = recuperar(pos);    //Cridem la funcio recuperar amb la posició llegida
                    if (cognom == null){
                        imprimir("No hi han dades en aquesta posició.");
                    }
                    else{
                        imprimir("A la posició nº"+(pos+1)+" està el cognom: "+cognom);
                    }
                    break;
                case 4:
                   pos = readInt("Introdueix una posició per eliminar les seves dades: ")-1;
                   if ( suprimir(pos)){ //Cridem la funció suprimir amb la posició llegida
                       imprimir("S'ha suprimit la dada correctament");
                   }
                    else{
                       imprimir("No s'ha pogut eliminar la posició.");

                   }
                    break;
                case 5:
                    cognom = readString("Introdueix un cognom per eliminar totes les coincidencies: ");
                    System.out.print("S'han suprimit "+suprimirDada(cognom)+" ocurrències.");  //Cridem la funció suprimirDada amb el cognom introduït anteriorment
                    break;
                case 6:
                    imprimir("Llista anulada.");
                    anula();
                    break;
                case 7:
                   if(primer().equalsIgnoreCase("null"))
                    break;
                case 8:
                    imprimir(darrer());
                    break;
                case 9:
                    imprimirLlista();
                    break;
                case 10:
                    ordena();   //S'ordena la array lexicogràficament
                    break;
                case 11:
                    test();
                    break;
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("\nPrem enter per continuar... ");
            sc.nextLine();
        }
    }

    /*Funcio Inserir*/

    /**
     *  Aquesta funció insereix un cognom a la posició indicada.
     * @param cognom  cognom a inserir.
     * @param pos posició per al cognom.
     * @return  true si s'ha pogut inserir.
     */
    boolean inserir(String cognom, int pos) {
        if(!potInserir(pos)){
            return false;
        }
        if(pos == quantitat){
            arrayLlista[pos] = cognom;
            quantitat++;
            return true;
        }
        else{
            desplaçarLlista(pos);
           arrayLlista[pos] = cognom;
            quantitat++;
            return true;
        }
    }

    /*Funció Localitzar*/

    /**
     * Aquesta funció localitza un cognom a la array.
     * @param cognom variable que buscarà a la array.
     * @return retorna la posició si la troba o -1 sinó.
     */
    int localitzar(String cognom){
        for (int i = 0; i < quantitat; i++){
            if(arrayLlista[i].equalsIgnoreCase(cognom)){
                return i;
            }
        }
        return -1;
    }

    /*Funció recuperar*/

    /**
     * Aquesta funció recupera el cognom que hi ha a una posició.
     * @param pos posició de la qual buscarà el contingut.
     * @return retorna el contingut de la posició o "null" si no ho troba.
     */
    String recuperar(int pos){
        if(!isEmpty() && limit(pos)){
            return arrayLlista[pos];
        }
        else{
            return null;
        }
    }

    /*Funció Suprimir*/

    /**
     * Aquesta funció suprimeix el contingut de la posició indicada.
     * @param pos posició a eliminar el contingut.
     * @return retorna true si s'ha eliminat el contingut.
     */
    boolean suprimir(int pos){
        if (isEmpty() || !limit(pos)){
            return false;
        }
        else{
            if(pos == quantitat){
                quantitat--;
                return true;
            }
            else{
                eliminarpos(pos);
                quantitat--;
                return true;
            }
        }
    }

    /*Funcio Suprimir dada*/

	/**
	 * Aquesta funció elimina totes les aparicions d'un cognom.
     * @param cognom variable del cognom a eliminar.
     * @return retorna el nº de ocurrències eliminades.
     */
    int suprimirDada(String cognom){
        int elements = 0;
        for(int i = (quantitat -1); i>=0;i--){
            if(arrayLlista[i].equalsIgnoreCase(cognom)){
                eliminarpos(i);
                elements++;
                quantitat--;
            }
        }
        return elements;
    }

    /*Funció anul·la*/

	/**
     * Aquesta funció anul·la la llista (quantitat = 0).
     */
    void anula(){
    quantitat = 0;
    }

    /*Funció primer*/

	/**
     * Aquesta funció retorna el primer element de la llista.
     * @return el cognom a posició 0 de la llista.
     */
    String primer(){
    return recuperar(0);
   }

    /*Funció darrer*/

	/**
	 * Aquesta funció retorna el últim element de la llista.
     * @return el cognom a posició (quantitat -1) de la llista.
     */
    String darrer(){
        return recuperar(quantitat-1);
    }
    /*Funció Imprimir Llista*/

	/**
	 * Aquesta funció imprimeix el contingut de la array.
     */
    void imprimirLlista(){
        if(isEmpty()){
            imprimir("La llista està buida");
        }
        else {
            for (int i = 0; i < quantitat; i++) {
                imprimir("#" + (i+1) + "\t" + arrayLlista[i]+"\n");
            }
        }
    }

    /*Funció ordena*/

	/**
	 * Aquesta funció ordena el contingut de la array lexicogràficament.
     */
    void ordena(){
        for (int i = 0; i< quantitat;i++){
            for(int j = i+1; j < quantitat;j++){
                if (arrayLlista[i].compareToIgnoreCase(arrayLlista[j]) > 0){
                    String temp = arrayLlista[j];
                    arrayLlista[j] = arrayLlista[i];
                    arrayLlista[i] = temp;
                }
            }

        }
    }

    /*###############################################
    Funcions aux. tdaLlista
    ###############################################*/

    /**
     * Funció aux. per saber quan la llista esta plena.
     * @return Retorna true si la quantitat de elements es igual a la capacitat de la array.
     */
    boolean isFull(){
        return quantitat == CAPACITAT;
    }

    /**
     * Funció aux. per saber quan la llista està buida.
     * @return Retorna true si la quantitat de elements es 0.
     */
    boolean isEmpty(){
        return quantitat == 0;
    }

    /*###############################################
    Funcions auxiliars
    ###############################################*/


    /**
     * Funció que imprimeix les opcions del menú i ens retorna la opció elegida.
     * @param menu Variable amb la llista de opcions del menú.
     * @return  retorna un enter amb la opció elegida del menú.
     */
    int funcioMenu(String[] menu) {
        for (int i = 0; i < menu.length; i++) {
            imprimir(menu[i]+"\n");
        }
        int opcio = readInt("Introdueix una opció del menú: ");
        return opcio;
    }

    /**
     * Funció que comprova si a la posició que li indiquem es pot inserir una dada.
     * @param pos Variable amb el nº de posició que es vol comprovar.
     * @return Retorna true si la posició compleix les condicions isEmpty i !isFull.
     */
    boolean potInserir(int pos) {
        if(pos >= 0 && pos <= quantitat){
            return (!isFull());
        }
        else {
            return false;
        }
    }

    /**
     * Funció que comprova si es passa del limit de la llista.
     * @param pos   Variable amb el nº de posició que es vol comprovar.
     * @return Retorna true si la posicio es major o igual que 0 i menor que quantitat.
     */
    boolean limit(int pos) {
        if (pos >= 0 && pos < quantitat) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Funció que llegeix un enter i imprimeix un missatge personalitzat.
     * @param missatge Frase per imprimir avans de demanar el enter.
     * @return Retorna el enter introduït.
     */
    int readInt(String missatge) {
        Scanner sc = new Scanner(System.in);
            try {
                imprimir(missatge);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                imprimir("Error d'entrada, introdueix una xifra!\n");
                return (readInt(missatge));
            }
    }

    /**
     * Funció que llegeix un String i imprimeix un missatge personalitzat.
     * @param missatge Frase per imprimir avans de demanar el String.
     * @return Retorna el String introduït.
     */
    String readString(String missatge) {
        Scanner sc = new Scanner(System.in);
       imprimir(missatge);
        String newString = sc.nextLine();
        return newString;
    }

    /**
     * Funció per imprimir text.
     * @param text Variable que conté el text a imprimir.
     */
    void imprimir(String text) {
        System.out.print(text);
    }

    /**
     * Funció per imprimir enters.
     * @param x Variable que conté el int a imprimir.
     */
    void imprimir (int x){
        System.out.print(x);
    }

    /**
     * Funció que allibera un espai a la llista per poder inserir dades en aquesta posició.
     * @param pos Variable amb el nº de posició a alliberar.
     */
    void desplaçarLlista(int pos){
        for(int i = quantitat;i > pos;i--){
            arrayLlista[i] = arrayLlista[i-1];
        }
    }

    /**
     * Funció que elimina una posició de la llista.
     * @param pos Variable amb el nº de posició a eliminar.
     */
    void eliminarpos(int pos){
        for(int i = pos ; i < quantitat ;i++){
            arrayLlista[i] = arrayLlista[i+1];
        }
    }

    boolean test() {
        anula();

		/* Test inserir */

        System.out.println("Testing inserir...");
        Random r = new Random(123);
        for(int i = 0; i< CAPACITAT+3;i++){
            inserir(r.nextInt()+"", 0);
            imprimir("#"+i+"\t"+recuperar(0)+"\n");
            ordena();
        }
        imprimirLlista();
        anula();
        if (inserir("Test", -1)) return false;
        if (inserir("Test", 1)) return false;
        if (!inserir("Test1", 0)) return false;
        if (!inserir("Test2", 1)) return false;
        if (!inserir("Test3", 0)) return false;
        if (!inserir("Test4", 1)) return false;

        System.out.println("Inserir OK");

		/* Test localitzar */
        System.out.println("Testing localitzar...");
        if (localitzar("Test") != -1) return false;
        if (localitzar("Test1") != 2) return false;
        if (localitzar("Test3") != 0) return false;
        if (localitzar("Test4") != 1) return false;
        System.out.println("Localitzar OK");

        /* Test recuperar */
        System.out.println("Testing recuperar...");
        if (recuperar(-5) != null) return false;
        if (!recuperar(0).equals("Test3")) return false;
        if (recuperar(2).equals("Test2")) return false;
        if (recuperar(-5) != null) return false;
        System.out.println("Recuperar OK");

        /* Test suprimir */
        System.out.println("Testing suprimir...");
        if (suprimir(-2)) return false;
        if (suprimir(4)) return false;
        if (!suprimir(3)) return false;
        if (!suprimir(0)) return false;
        System.out.println("Suprimir OK");

        /* Test suprimir dada*/
        System.out.println("Testing suprimirDada...");
        anula();
        inserir("Test1", 0);
        inserir("Test2", 1);
        inserir("Test3", 2);
        inserir("Test2", 3);
        inserir("Test4", 4);

        if (suprimirDada("Test") != 0) return false;
        if (suprimirDada("Test2") != 2) return false;
        if (suprimirDada("Test4") != 1) return false;
        if (suprimirDada("Test1") != 1) return false;
        System.out.println("Suprimir OK");


        /* Test ordenar */
        System.out.println("Testing ordenar...");
        String[] str = {"Test1", "Test2", "Test3", "Test4", "Test5"};
        anula();
        inserir("Test1", 0);
        inserir("Test2", 1);
        inserir("Test3", 2);
        inserir("Test4", 3);
        inserir("Test5", 4);
        ordena();
        for (int i = 0; i < str.length; i++) {
            if (!str[i].equals(arrayLlista[i])) return false;
        }

        anula();
        inserir("Test5", 0);
        inserir("Test4", 1);
        inserir("Test3", 2);
        inserir("Test2", 3);
        inserir("Test1", 4);
        ordena();
        for (int i = 0; i < str.length; i++) {
            if (!str[i].equals(arrayLlista[i])) return false;
        }

        anula();
        inserir("Test4", 0);
        inserir("Test1", 1);
        inserir("Test5", 2);
        inserir("Test3", 3);
        inserir("Test2", 4);
        ordena();
        for (int i = 0; i < str.length; i++) {
            if (!str[i].equals(arrayLlista[i])) return false;
        }
        System.out.println("Ordenar OK");

        anula();
        return true;
    }

}

