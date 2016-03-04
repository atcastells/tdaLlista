/**
 * Autors:	Oscar Oliver, Aaron Castells, Adrià Montoro
 * Grup:    4
 * Cas:     4
 * Data inici:          08/02/2016
 * Data finalització:   28/02/2016
 *
 * Aquest programa implementa el TDA llista amb les principals operacions
 * sobre les llistes.
 *
 * Algorisme:
 *   Entrada al bucle principal. Repetir fins seleccionar l'opció de sortir:
 *      Mostrar el menú d'operacions sobre la llista
 *      Lectura de l'opció amb control d'errors
 *      Realitzar l'operació escollida
 *      Realitzar una pausa per veure el resultat de l'operació
 *   Mostrar missatge de comiat
 */

import java.util.Scanner;

public class Llista {
    /* TDA Llista */
    private final int CAPACITAT = 100;					    // Capacitat de la llista
    private String [] arrayLlista = new String[CAPACITAT];	// Array per enmagatzemar els cognoms
    private int quantitat = 0;							    // Nombre d'elements de la llista



    public static void main(String[] args) {
        new Llista().init();
    }

    public void init() {
        /* Declaració de variables */
        Llista llista = new Llista();
        int pos;						    // Una posició de la llista
        String cognom;					    // Un cognom de la llista
        int option = -1;					// Opció del menú
        Scanner sc = new Scanner(System.in);
        String[] menuOptions = {            // Opcions del menú
                "Inserir un element",
                "Localitzar la 1a aparició d'un element",
                "Recuperar l'element d'una posició",
                "Suprimir l'element d'una posició",
                "Eliminar totes les aparicions d'un element",
                "Buidar la llista",
                "Primer element de la llista",
                "Darrer element de la llista",
                "Imprimir la llista",
                "Ordenar la llista lexicogràficament",
                "Inicialitza la llista amb valors per defecte",
                "Test dels metodes",
                "Sortir"
        };
        final int EXIT_OPTION = menuOptions.length;		// Constant per a l'opció de sortir

        String[] testValues = { "Oliver", "Casanova", "Aaron", "Oscar",
                "Montoro", "Castells", "Adria", "Oscar" };




        /* Entrada al bucle principal: repetir fins seleccionar l'opció de sortir */
        while(option != EXIT_OPTION) {

            /* Obtenir l'opcio a realitzar */
            System.out.println(" --- Operacions sobre la llista ---");
            option = menu(menuOptions);
            System.out.println();


        /* Realitzar l'operacio escollida */
            switch (option) {
                // 1. Inserir un element.
                case 1:
                    /* Controlar que la llista no estigui plena */
                    if (isFull()) {
                        System.out.println("La llista està plena.");
                        break;
                    }

                    /* Demanar el cognom a l'usuari */
                    cognom = readString("Introdueix cognom: ");

                    /* Obtenir la posicio d'insercio */
                    pos = readInt("Introdueix posició (de 1 a " + (size()+1) + "): ");
                    pos = pos-1; //restem 1 a pos, per a evitar-li a l'usuari començar des de posicio 0.

                    if (inserir(cognom, pos)) {
                        System.out.println("S'ha inserit correctament");
                    } else {
                        System.out.println("No s'ha pogut inserir");
                    }
                    break;


                // 2. Localitzar la 1a aparició d'un element.
                case 2:
                    if (isEmpty()) {
                        System.out.println("Ho sentim. La llista està buida");
                        break;
                    }
                    /* Demanar el cognom a l'usuari */
                    cognom = readString("Cognom:  ");
                    System.out.println("Holaaa!!!");
                    /* Localitzar la posició */
                    pos = localitzar(cognom);

                     /* Impressió de resultats en funció de si s'ha trobat o no */
                    if (pos != -1)	System.out.println(recuperar(pos) + " està en la posició " + (pos+1));
                    else			System.out.println("Cognom no trobat.");
                    break;


                // 3. Recuperar l'element d'una posició.
                case 3:
                    if (isEmpty()) {
                        System.out.println("Ho sentim. La llista està buida");
                        break;
                    }
                    /* Demanar la posició a l'usuari*/
                    pos = readInt("Introdueix posició (de 1 a " + size() + "): ");
                    pos = pos-1; //restem 1 a pos, per a evitar-li a l'usuari començar des de posicio 0.

                    /* Recuperar el cognom */
                    cognom = recuperar(pos);

                    /* Si s'ha trobat: Imprimir el cognom */
                    if (cognom != null) { System.out.println(cognom); }
                    /* En cas contrari: Mostrar missatge d'error */
                    else { System.out.println("Aquesta posició no és vàlida"); }
                    break;


                // 4. Suprimir l'element d'una posició.
                case 4:
                    if (isEmpty()) {
                        System.out.println("Ho sentim. La llista està buida");
                        break;
                    }
                    /* Obtenir la posicio */
                    pos = readInt("Introdueix posició (de 1 a " + size() + "): ");
                    pos--;

                    /* Controlar que la posicio sigui valida */
                    // Si no es valida:
                    if (pos < 0 || pos > size()-1) {
                        /* Mostrar missatge d'error */
                        System.out.println("Posició no vàlida");
                    }
                    // Si es valida:
                    else {
                        suprimir(pos);
                    }
                    break;


                // 5. Eliminar totes les aparicions d'un element.
                case 5:
                    /* Demanar el cognom a l'usuari */
                    cognom = readString("Cognom:  ");

                    /* Eliminar totes les aparicions del cognom en la llista */
                    int n = suprimirDada(cognom);

                    /* Mostrar missatge informatiu */
                    System.out.println("S'han eliminat totes les aparicions (" + n + ") de " + cognom);
                    break;


                // 6. Buidar la llista.
                case 6:
                    /* Buidar la llista */
                    anula();

                    /* Mostrar missatge informatiu */
                    System.out.println("La llista ha estat borrada");
                    break;


                // 7. Primer element de la llista.
                case 7:
                    /* Obtenir el primer element de la llista */
                    cognom = primer();

                    /* Si s'ha trobat: imprimir el cognom */
                    if (cognom != null) System.out.println(cognom);
                    /* Si no: imprimir missatge informatiu */
                    else				System.out.println("Ho sentim. La llista està buida");
                    break;


                // 8. Darrer element de la llista.
                case 8:
                    /* Obtenir el darrer element de la llista */
                    cognom = darrer();

                    /* Si s'ha trobat: imprimir el cognom */
                    if (cognom != null) System.out.println(cognom);
                    /* Si no: imprimir missatge informatiu */
                    else				System.out.println("Ho sentim. La llista està buida");
                    break;


                // 9. Imprimir la llista.
                case 9:
                    /* Si la llista està buida: missatge informatiu */
                    if (isEmpty()) System.out.println("Ho sentim. La llista està buida.");
                    /* En cas contrari: Imprimir la llista */
                    else imprimirLlista();
                    break;


                // 10. Ordenar la llista lexicogràficament.
                case 10:
                    /* Ordenar la llista lexicogràficament */
                    ordena();

                    /* Imprimir missatge informatiu */
                    System.out.println("La llista s'ha ordenat.");
                    break;


                // 11. Inicialitzar la llista
                case 11:
                    /* Inicialitzar la llista */
                    inicialitzarLlista(testValues);
                    break;

                case 12:
                    System.out.println(test());
            }


            /* Realitzar una pausa per veure el resultat de l'operació */
            if (option != EXIT_OPTION) {
                System.out.print("\nPremer intro <-' per continuar...");
                sc.nextLine();
            }

            // Deixar espais en blanc
            System.out.println();
            System.out.println();
        }

        /* Mostrar missatge de comiat */
        System.out.println(" --- Fi del programa ---");
    }


    /************************************************************
    *   METODES DEL TDA LLISTA                                  *
    *************************************************************/

    /**
     * 1. Inserir un element a la llista en una posició determinada.
     */
    boolean inserir(String cognom, int posicio) {
        /* Controlar que no estigui plena i que la posició sigui vàlida */
        if (isFull() || posicio < 0 || posicio > quantitat) {
            return false;
        }

        /* Desplaçar els elements a partir de la posicio a inserir un lloc cap a la dreta */
        for (int i = quantitat; i > posicio; i--) {		//recorrer la llista des del primer lloc buit fins la posicio desitjada
            arrayLlista[i] = arrayLlista[i-1];				//movem l'element de la posicio X-1 a la posicio X.
        }

        /* Inserir el cognom */
        arrayLlista[posicio] = cognom;

        /* Incrementar el comptador de cognoms a la llista */
        quantitat++;

        return true;
    }

    /**
     * 2. Localitzar la posició de la primera aparició de l'element en la llista
     */
    int localitzar(String cognom) {
        /* Buscar el cognom dins la llista i desar la posició */
        int pos = -1;		// Valor per defecte si no es troba

        int i = 0;
        boolean found = false;
        while (!found && i < quantitat) {
            if (cognom.compareToIgnoreCase(arrayLlista[i]) == 0) {
                pos = i;
                found = true;
            }
            i++;
        }

        return pos;
    }

    /**
     * 3. Recuperar l'element de la posició indicada en la llista.
     */
    String recuperar(int pos) {
        if (!isEmpty() && (pos >= 0 && pos < quantitat)) {
            return arrayLlista[pos];
        }
        else return null;
    }

    /**
     * 4. Suprimeix de la llista l'element que es troba en la posició indicada
     */
    boolean suprimir(int pos) {
        if (pos < 0 || pos > size()-1) return false;

        /* Desplaçar els elements a partir de la posicio següent a eliminar un lloc cap a l'esquerra */
        for (int i = pos; i < quantitat-1; i++) {	//recorrer la llista des de pos fins el penultim element.
            arrayLlista[i] = arrayLlista[i+1];					//movem l'element de la posicio X+1 a la posicio X.
        }
        /* Decrementar el comptador de cognoms a la llista */
        quantitat--;
        return true;
    }

    /**
     * 5. Elimina totes les aparicions d'un element en la llista
     */
    int suprimirDada(String cognom) {
        int n = 0;
        /* Recorrer tota la llista */
        for (int i = 0; i < quantitat; i++) {
            /* Comparar cada element amb el cognom i, si son iguals: */
            if (cognom.compareToIgnoreCase(arrayLlista[i]) == 0) {
                /* Desplaçar els elements a partir de la posicio següent a eliminar un lloc cap a l'esquerra */
                for (int j = i; j < quantitat-1; j++) {	//recorrer la llista des de l'element actual fins el penultim.
                    arrayLlista[j] = arrayLlista[j+1];		//movem l'element de la posicio X+1 a la posicio X.
                }
                /* Decrementar el comptador de cognoms de la llista */
                quantitat--;
                n++;
            }
        }
        return n;
    }

    /**
     * 6. Buida la llista
     */
    void anula() {
        quantitat = 0;
    }

    /**
     * 7. Primer element de la llista
     */
    String primer() {
        return recuperar(0);
    }

    /**
     * 8. Darrer element de la llista
     */
    String darrer() {
        return recuperar(quantitat-1);
    }

    /**
     * 9. Imprimir la llista
     */
    void imprimirLlista() {
        for (int i = 0; i < quantitat; i++) {
            System.out.println(" " + (i+1) + ". " + arrayLlista[i]);
        }
    }

    /**
     * 10. Ordenar la llista lexicograficament
     */
    void ordena() {
        // Comparar l'element de cada posició (X) amb els elements de les
        // següents posicions majors (X+1, X+2, ..., quantitat-1), i si
        // l'element d'X és major lexicograficament, intercanviar-los.

        String aux;
        /* Recórrer tota la llista */
        for (int i = 0; i < quantitat; i++) {
            /* Recórrer la llista des de la següent posició (i+1) */
            for (int j = i+1; j < quantitat; j++) {
                /* Si l'element 'i' > l'element 'j', intercanviar-los */
                if (arrayLlista[i].compareToIgnoreCase(arrayLlista[j]) > 0) {
                    aux = arrayLlista[i];
                    arrayLlista[i] = arrayLlista[j];
                    arrayLlista[j] = aux;
                }
            }
        }
    }

    /**
     * 11. Inicialitza la llista
     * @param values els valors amb els que s'inicialitzarà la llista
     */
    void inicialitzarLlista(String[] values) {
        anula();
        for (int i = 0; i < values.length; i++) {
            inserir(values[i], i);
        }
    }



    /**
     * Comprova si la llista està plena
     */
    boolean isFull() {
        return quantitat == CAPACITAT;
    }

    /**
     * Comprova si la llista està buida
     */
    boolean isEmpty() {
        return quantitat == 0;
    }

    /**
     * Retorna la quantitat d'elements que conté la llista
     */
    int size() { return quantitat; }


    /************************************************************
    *   METODES PER A LA GUI                                    *
    *************************************************************/

    /**
     * Menú d'opcions
     */
    int menu(String[] options) {
        int option;

        for (int i = 0; i < options.length; i++) {
            if ( (i+1) < 10) System.out.print(" ");
            System.out.println(" " + (i+1) + ". " + options[i]);
        }
        System.out.println();
        do {
            option = readInt("Introdueix opció: ");
        } while (option < 1 || option > options.length);
        return option;
    }

    /**
     * Llegeix un enter des de teclat
     */
    int readInt(String inputMessage) {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        boolean succes;
        do {
            System.out.print(inputMessage);
            succes = sc.hasNextInt();
            if (succes) input = sc.nextInt();
            sc.nextLine();
        } while (!succes);
        return input;
    }

    /**
     * Llegeix una cadena de text (String)
     */
    String readString(String inputMessage) {
        Scanner sc = new Scanner(System.in);
        System.out.print(inputMessage);
        String input = sc.nextLine();
        return input;
    }

	/*************************************************************
	 *   Test Llista                                             *
	 *************************************************************/
	boolean test() {
		anula();

		/* Test inserir */
		System.out.println("Testing inserir...");
for(int i = 0; i < CAPACITAT+3; i++) {
  inserir("Testasldkjaskd", 0);
}
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


