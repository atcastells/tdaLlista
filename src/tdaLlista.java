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
import java.util.Scanner;
public class tdaLlista {


    /*Variables Globals*/

    /**
     * Constant màxim Array
     */
    public final int CAPACITAT= 3;

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
        int opcio = 0;  //Control switch

        /*Mentre la opció sigui mes menuda que 11*/

        while (opcio < menu.length) {

            /* S'imprimeix el menú i es selecciona una opció*/
            opcio = funcioMenu(menu);

            /*S'executa la opció seleccionada*/
            switch (opcio) {
                case 1:
                       int  pos = readInt("Introdueix una posició entre "+0+" i "+quantitat+": ");
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
                        imprimir("El cognom '"+cognom+"' està a la posició nº: "+posCognom);
                    }
                    break;
                case 3:
                    pos = readInt("Introdueix una posició per recuperar les dades que té: ");
                    cognom = recuperar(pos);    //Cridem la funcio recuperar amb la posició llegida
                    if (cognom.equalsIgnoreCase("null")){
                        imprimir("No hi han dades en aquesta posició.");
                    }
                    else{
                        imprimir("A la posició nº"+pos+" està el cognom: "+cognom);
                    }
                    break;
                case 4:
                   pos = readInt("Introdueix una posició per eliminar les seves dades: ");
                   if ( suprimir(pos)){ //Cridem la funció suprimir amb la posició llegida
                       imprimir("S'ha suprimit la dada correctament");
                   }
                    else{
                       imprimir("No s'ha pogut eliminar la posició.");

                   }
                    break;
                case 5:
                    cognom = readString("Introdueix un cognom per eliminar totes les coincidencies");
                    System.out.print("S'han suprimit "+suprimir_dada(cognom)+" ocurrències.");  //Cridem la funció suprimir_dada amb el cognom introduït anteriorment
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
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("\nPrem enter per continuar... ");
            sc.nextLine();
        }
    }

    /*Funcio Inserir*/

    /**
     *  Aquesta funció insereix un cognom a la posició indicada
     * @param cognom  cognom a inserir
     * @param pos posició per al cognom
     * @return  true si s'ha pogut inserir
     */
    boolean inserir(String cognom, int pos) {
        if(!potInserir(pos)){
            return false;
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
     * Aquesta funció localitza un cognom a la array
     * @param cognom variable que buscarà a la array
     * @return retorna la posició si la troba o -1 sinó
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
     * Aquesta funció recupera el cognom que hi ha a una posició
     * @param pos posició de la qual buscarà el contingut.
     * @return retorna el contingut de la posició o "null" si no ho troba.
     */
    String recuperar(int pos){
        if(pos < quantitat && !isEmpty()){
            return arrayLlista[pos];
        }
        else{
            return "null";
        }
    }

    /*Funció Suprimir*/

    /**
     * Aquesta funció suprimeix el contingut de la posició indicada.
     * @param pos posició a eliminar el contingut
     * @return retorna true si s'ha eliminat el contingut
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
	 * Aquesta funció elimina totes les aparicions d'un cognom
     * @param cognom variable del cognom a eliminar
     * @return retorna el nº de ocurrències eliminades
     */
    int suprimir_dada(String cognom){
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
     * Aquesta funció anul·la la llista (quantitat = 0)
     */
    void anula(){
    quantitat = 0;
    }

    /*Funció primer*/

	/**
     * Aquesta funció retorna el primer element de la llista
     * @return el cognom a posició 0 de la llista.
     */
    String primer(){
    return recuperar(0);
   }

    /*Funció darrer*/

	/**
	 * Aquesta funció retorna el últim element de la llista
     * @return el cognom a posició (quantitat -1) de la llista
     */
    String darrer(){
        return recuperar(quantitat-1);
    }
    /*Funció Imprimir Llista*/

	/**
	 * Aquesta funció imprimeix el contingut de la array
     */
    void imprimirLlista(){
        if(isEmpty()){
            imprimir("La llista està buida");
        }
        else {
            for (int i = 0; i < quantitat; i++) {
                imprimir("#" + i + "\t" + arrayLlista[i]);
            }
        }
    }

    /*Funció ordena*/

	/**
	 * Aquesta funció ordena el contingut de la array lexicogràficament
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

    boolean isFull(){
        return quantitat == CAPACITAT;
    }

    boolean isEmpty(){
        return quantitat == 0;
    }

    /*###############################################
    Funcions auxiliars
    ###############################################*/

    int funcioMenu(String[] menu) {                                     //Retorna la opcio del menu
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
        int opcio = readInt("Introdueix una opció del menú: ");
        return opcio;
    }

    boolean potInserir(int pos) {                                   //Boolea per cuan es pot inserir
        if(quantitat >= 0 && pos <= quantitat){
            return (isEmpty() || !isFull());
        }
        else {
            return false;
        }
    }
    boolean limit(int pos) {                                   //Boolea per cuan no es pot eliminar
        if (pos >= 0 && pos < quantitat) {
            return true;
        } else {
            return false;
        }
    }

    int readInt(String missatge) {                                      //Funció per llegir enters
        int newInt = 0;
        Scanner sc = new Scanner(System.in);
            try {
                imprimir(missatge);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                imprimir("Error d'entrada, introdueix una xifra!\n");
                return (readInt(missatge));
            }
    }

    String readString(String missatge) {                                //Funció per llegir Strings
        Scanner sc = new Scanner(System.in);
       imprimir(missatge);
        String newString = sc.nextLine();
        return newString;
    }

    void imprimir(String text) {             //Funció per imprimir un missatge
        System.out.print(text+"\n");
    }

    void desplaçarLlista(int pos){                  //Funció per lliberar un espai a la array;
        for(int i = quantitat;i > pos;i--){
            arrayLlista[i] = arrayLlista[i-1];
        }
    }
    void eliminarpos(int x){
        for(int i = x ; i < quantitat ;i++){
            arrayLlista[i] = arrayLlista[i+1];
        }
    }
}

