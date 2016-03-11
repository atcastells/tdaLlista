/**
 *          Creat pel grup 2 de M03 el 04/03/2016.
 *          Grup  2:    Oscar Oliver, Adrià Montoro, Aaron Castells
 *
 *          Implementació del TDA Llista en java utilitzant funcions.
 *
 *          Versió: 1.0
 *
 *          Algorisme:
 *                      S'imprimeix el menú
 *                      S'elegeix una opció del menú
 *                      Mentre la opció sigui diferent a la de sortir
 *                           S'executa la opció seleccionada
 *                      Sinó
 *                             Surt
 *
 */
 
import java.util.InputMismatchException;
import java.util.Scanner;

public class tdaLlista {

	/* Variables del TDA Llista */

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
			addPosition(pos);
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
				removePosition(pos);
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
				removePosition(i);
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
	String tostring (){
		String llista = "";
		for (int i = 0; i < quantitat; i++){
			llista +="#"+i+"\t"+recuperar(i) + "\n";
		}
		return llista;
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
	 * Funció que allibera un espai a la llista per poder inserir dades en aquesta posició.
	 * @param pos Variable amb el nº de posició a alliberar.
	 */
	void addPosition(int pos){
		for(int i = quantitat;i > pos;i--){
			arrayLlista[i] = arrayLlista[i-1];
		}
	}

	/**
	 * Funció que elimina una posició de la llista.
	 * @param pos Variable amb el nº de posició a eliminar.
	 */
	void removePosition(int pos){
		for(int i = pos ; i < quantitat ;i++){
			arrayLlista[i] = arrayLlista[i+1];
		}
	}


	/**
	 * Funció que comprova la llista en busca de posibles sortides de memoria a les arrays i altres errors.
	 * @return Retorna true si el test s'ha superat correctament
	 */

	boolean test() {
		anula();

		/* Test inserir */
		System.out.println("Testing inserir...");
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

