import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		JTextArea sortida = new JTextArea(10, 10);
		sortida.setText("Escriu algo");
		sortida.setEnabled(false);
		JFrame window = new JFrame("Llista");
		window.setSize(500, 500);
		window.setLayout(new BorderLayout(1,1));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = (JPanel)window.getContentPane();
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(0,1));

		JButton inserir = new JButton("Inserir");
		inserir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {

			}
		});
		buttonsPanel.add(inserir);
		JButton localitzar = new JButton("Localitzar");
		localitzar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {

			}
		});
		buttonsPanel.add(localitzar);
		JButton primer = new JButton("Primer");
		primer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.print(llista.primer());


			}
		});
		buttonsPanel.add(primer);
		panel.add(buttonsPanel, BorderLayout.WEST);
		panel.add(sortida, BorderLayout.EAST);
		window.setVisible(true);
	}
}
