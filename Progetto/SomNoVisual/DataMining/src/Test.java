import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.PrintStream;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class Test extends JFrame {
	private int num;
	private DataSet Iris;
	private DataMining data;
	private JPanel contentPane;
	private ArrayList<Integer> SelectedFeature = new ArrayList<Integer>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() throws IOException{
		setTitle("DataMining");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 409);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bottoneCaricoIris = new JButton("Carica dataset completo");
		bottoneCaricoIris.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		bottoneCaricoIris.setBounds(358, 12, 239, 25);
		contentPane.add(bottoneCaricoIris);
		
		JLabel label1 = new JLabel("\tSelezione delle caratteristiche del DataSet");
		label1.setBounds(325, 49, 321, 15);
		contentPane.add(label1);
		
		JCheckBox checkBox_lun_sep = new JCheckBox("Lunghezza Sepalo");

		checkBox_lun_sep.setBounds(28, 97, 187, 23);
		contentPane.add(checkBox_lun_sep);
		
		JCheckBox checkBox_lar_sep = new JCheckBox("Larghezza Sepalo");

		checkBox_lar_sep.setBounds(268, 97, 187, 23);
		contentPane.add(checkBox_lar_sep);
		
		JCheckBox checkBox_lun_pet = new JCheckBox("Lunghezza Petalo");

		checkBox_lun_pet.setBounds(502, 97, 187, 23);
		contentPane.add(checkBox_lun_pet);
		
		JCheckBox checkBox_lar_pet = new JCheckBox("Larghezza Petalo");

		checkBox_lar_pet.setBounds(746, 97, 187, 23);
		contentPane.add(checkBox_lar_pet);
		
		JButton bottoneCaricoData = new JButton("Carica DataSet");

		bottoneCaricoData.setBounds(410, 158, 153, 25);
		contentPane.add(bottoneCaricoData);
		
		JLabel label2 = new JLabel("Quale algoritmo di Clustering si vuole scegliere?");
		label2.setBounds(309, 210, 398, 15);
		contentPane.add(label2);
		
		JButton bottoneSOM = new JButton("SOM");

		bottoneSOM.setBounds(268, 264, 117, 25);
		contentPane.add(bottoneSOM);
		
		JButton bottoneKmeans = new JButton("K-MEANS");
		bottoneKmeans.setBounds(572, 264, 117, 25);
		contentPane.add(bottoneKmeans);
		
		JButton bottoneFine = new JButton("Fine");

		bottoneFine.setBounds(816, 334, 117, 25);
		contentPane.add(bottoneFine);
		
		bottoneCaricoIris.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e2) {
				try {
					Iris = new DataSetIris("Iris.data");
				} catch (Exception e) {
					
				}
			}
		});
		
		checkBox_lun_sep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e2) {
				try {
					SelectedFeature.add(0);
				} catch (Exception e) {
					
				}
			}
		});
		
		checkBox_lar_sep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e2) {
				try {
					SelectedFeature.add(1);
				} catch (Exception e) {
					
				}
			}
		});
		
		checkBox_lun_pet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e2) {
				try {
					SelectedFeature.add(2);
				} catch (Exception e) {
					
				}
			}
		});
		
		checkBox_lar_pet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e2) {
				try {
					SelectedFeature.add(3);
				} catch (Exception e) {
					
				}
			}
		});
		
		bottoneCaricoData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e2) {
				try {
					data = new DataMining(Iris);
					data.selezionaFeature(SelectedFeature);
				} catch (Exception e) {
					
				}
			}
		});
		
		bottoneSOM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e2) {
				try {
					data.setCluster(new SOM());
					num=SelectedFeature.size();
					data.set(10, 10, num);
					System.out.println("Lattice\n");
					data.print();
					//CREO IL VETTORE DI DOUBLE DA MANDARE ALL'ALGORIMO
					ArrayList<Double> I = new ArrayList<Double>();
					//CICLO FOR PER PASSARE ALLA FUNZIONE getBMU I VETTORI DI INPUT
					for(int i=0;i<Iris.getRecord();i++)
					{
						//PRENDO L'i-ESIMO VETTORE DI INPUT
						I=data.passaRecord(i);
						data.start(I);
						data.run();
						data.stop();	
					}
					System.out.println("Lattice dopo il SOM\n");
					data.print();
				} catch (Exception e) {
					
				}
			}
		});
		
		bottoneFine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
}
}