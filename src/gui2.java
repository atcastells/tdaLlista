import com.sun.deploy.panel.ExceptionListDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

/**
 * Created by acastells on 11/03/16.
 */
public class gui2 {
	tdaLlista llista = new tdaLlista();
	public static void main(String[] args){

		new gui2().inici();
	}

	void inici(){
		makeFrame();
	}

	void makeFrame(){

		JLabel enter = new JLabel("Posicio: ");
		JLabel string = new JLabel("Cognom: ");
		JTextField entradaInt = new JTextField(10);
		JTextField entradaString = new JTextField(10);
		JTextArea sortida = new JTextArea(100, 50);

		sortida.setText("Sortida de dades");

		sortida.setEditable(false);

		JFrame window = new JFrame("Llista");
		window.setSize(700, 500);
		window.setLayout(new BorderLayout(1,1));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = (JPanel)window.getContentPane();
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(11,1));

		JButton inserir = new JButton("Inserir");
		inserir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(entradaInt.getText() == ""){
					imprimir("Introdueix les dades correctament",sortida);
				}
				else{
					int posicio = Integer.parseInt(entradaInt.getText()) -1;
					String cognom = entradaString.getText();
					if(llista.inserir(cognom,posicio)){
						imprimir("Fet",sortida);
					}
					else{
						imprimir("Posició erronea", sortida);
					}
				}
			}
		});
		buttonsPanel.add(inserir);
		JButton localitzar = new JButton("Localitzar");
		localitzar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (llista.localitzar(entradaString.getText())!= -1){
					imprimir("El cognom "+entradaString.getText()+" està a la posició nº"+llista.localitzar(entradaString.getText())+1, sortida);
				}
				else  {
					imprimir("No s'ha trobat el cognom",sortida);
				}
			}
		});
		buttonsPanel.add(localitzar);
		JButton primer = new JButton("Primer");
		primer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				//System.out.print(llista.primer());
				if(llista.primer() == null){
					imprimir("La llista està buida", sortida);
				}
				else{
					imprimir(llista.primer(),sortida);
				}
			}
		});

		JButton imprimirLlista = new JButton("Imprimir Llista");
		imprimirLlista.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imprimir(llista.tostring(),sortida);
			}
		});
		buttonsPanel.add(imprimirLlista);
		JPanel subPanel = new JPanel();
		subPanel.add(enter);
		subPanel.add(entradaInt);
		subPanel.add(string);
		subPanel.add(entradaString);

		buttonsPanel.add(primer);
		/*panel.add(entradaString, BorderLayout.);
		panel.add(entradaInt, BorderLayout.SOUTH);*/
		panel.add(buttonsPanel, BorderLayout.WEST);
		panel.add(sortida, BorderLayout.EAST);

		panel.add(subPanel, BorderLayout.SOUTH);
		window.setVisible(true);

	}

	int readInt(String text, JTextField entrada, JTextArea sortida) {
		try {
			imprimir(text, sortida);
			int i = Integer.parseInt(entrada.getText());
			return i;
		} catch (NumberFormatException e) {
			imprimir("Error d'entrada, introdueix una xifra!\n",sortida);
			return (readInt(text, entrada, sortida));
		}
	}

	String readString(String text,JTextField entrada, JTextArea sortida){
		imprimir(text, sortida);
		return entrada.getText();
	}

	void imprimir(String t, JTextArea sortida){
		sortida.setText(t);
	}
}
