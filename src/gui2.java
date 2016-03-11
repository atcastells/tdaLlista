import javax.swing.*;
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
		JFrame window = new JFrame("Llista");
		window.setSize(300, 500);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = (JPanel)window.getContentPane();
		JPanel buttonsPanel = new JPanel();

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
		panel.add(buttonsPanel);
		window.setVisible(true);
	}
}
