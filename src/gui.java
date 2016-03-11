import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by acastells on 11/03/16.
 */
public class gui {
	public static void main(String[] args) {
		new gui().inici();

	}

	void inici() {
		tdaLlista llista = new tdaLlista();
		String[] menuOptions = {   //Array amb les opcions del menu
				"Inserir",
				"Localitzar",
				"Recuperar",
				"Suprimir",
				"Suprimir dada",
				"Anul·la",
				"Primer",
				"Darrer",
				"Imprimir",
				"Ordena",
				"Test",
				"Surt"
		};
		int opcio = 0;  //Control switch
		final int EXIT_OPTION = menuOptions.length;	// Opcio de sortir

		/* Mentre la opció sigui diferent a la de sortir */
		while (opcio != EXIT_OPTION) {

			/* S'imprimeix el menú i es selecciona una opció*/
			opcio = funcioMenu(menuOptions);

			/*S'executa la opció seleccionada*/
			switch (opcio) {
				// Inserir
				case 1:
					int  pos = readInt("Introdueix una posició entre 1 i "+(llista.quantitat+1)+": ") -1;
					String cognom = readString("Introdueix un Cognom: ");
					if (llista.inserir(cognom, pos)) { //Cridem a la funció inserir amb les dades anteriors
						imprimir("S'ha inserit correctament");
					}
					else {
						imprimir("Posició incorrecta");
					}
					break;

				// Localitzar
				case 2:
					cognom = readString("Introdueix un Cognom per buscar: ");
					int posCognom = llista.localitzar(cognom); //Cridem la funció localitzar amb el cognom introduït i retornem la posició
					if(posCognom == -1){
						imprimir("No s'ha trobat el cognom.");
					}
					else{
						imprimir("El cognom '"+cognom+"' està a la posició nº: "+(posCognom+1));
					}
					break;

				// Recuperar
				case 3:
					pos = readInt("Introdueix una posició per recuperar les dades que té: ")-1;
					cognom = llista.recuperar(pos);    //Cridem la funcio recuperar amb la posició llegida
					if (cognom == null){
						imprimir("No hi han dades en aquesta posició.");
					}
					else{
						imprimir("A la posició nº"+(pos+1)+" està el cognom: "+cognom);
					}
					break;


				case 4:
					pos = readInt("Introdueix una posició per eliminar les seves dades: ")-1;
					if ( llista.suprimir(pos)){ //Cridem la funció suprimir amb la posició llegida
						imprimir("S'ha suprimit la dada correctament");
					}
					else{
						imprimir("No s'ha pogut eliminar la posició.");

					}
					break;
				case 5:
					cognom = readString("Introdueix un cognom per eliminar totes les coincidencies: ");
					System.out.print("S'han suprimit "+llista.suprimirDada(cognom)+" ocurrències.");  //Cridem la funció suprimirDada amb el cognom introduït anteriorment
					break;
				case 6:
					imprimir("Llista anulada.");
					llista.anula();
					break;
				case 7:
					if(llista.primer().equalsIgnoreCase("null"))
						break;
				case 8:
					imprimir(llista.darrer());
					break;
				case 9:
					imprimir(llista.tostring());
					break;
				case 10:
					llista.ordena();   //S'ordena la array lexicogràficament
					break;
				case 11:
					llista.test();
					break;
			}
			Scanner sc = new Scanner(System.in);
			System.out.println("\nPrem enter per continuar... ");
			sc.nextLine();
		}
	}
	/**
	 * Funció que imprimeix les opcions del menú i ens retorna la opció elegida.
	 * @param menu Variable amb la llista de opcions del menú.
	 * @return  retorna un enter amb la opció elegida del menú.
	 */
	int funcioMenu(String[] menu) {
		for (int i = 0; i < menu.length; i++) {
			imprimir("#"+(i+1)+"\t"+menu[i]+"\n");
		}
		int opcio = readInt("Introdueix una opció del menú: ");
		return opcio;
	}

	/******************************************************************************************************************
	Funcions AUX
	 ******************************************************************************************************************/
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
	public void imprimir(String text) {
		System.out.print(text);
	}

	/**
	 * Funció per imprimir enters.
	 * @param x Variable que conté el int a imprimir.
	 */
	void imprimir (int x){
		System.out.print(x);
	}
}
