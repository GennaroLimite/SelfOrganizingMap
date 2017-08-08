
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author  alanter
 */
public class SOMDemoApp extends javax.swing.JFrame {

	private int num;
	private int latticeWidth = 40;
	private int latticeHeight = 40;
	

	private DataMining data;
	private SOM trainer;
	private SOMLattice lattice;
	private Vector inputVectors;
	
	/** Creates new form SOMDemo */
	public SOMDemoApp(int n) {
		initComponents();
		Vector tempVec;
		num=n;
		lattice = new SOMLattice(latticeWidth, latticeHeight,num);
		System.out.println("LATTICE\n");
		lattice.printLattice();
		renderPanel.registerLattice(lattice);
		trainer = new SOM();
		trainer.setLattice(lattice);
		inputVectors = new Vector();

		// Make some colors.  Red, Green, Blue, Yellow, Purple, Black,
		// white, and gray
		tempVec = new Vector();
		tempVec.addElement(new Double(1));
		tempVec.addElement(new Double(0));
		tempVec.addElement(new Double(0));
		inputVectors.addElement(tempVec);
		tempVec = new Vector();
		tempVec.addElement(new Double(0));
		tempVec.addElement(new Double(1));
		tempVec.addElement(new Double(0));
		inputVectors.addElement(tempVec);
		tempVec = new Vector();
		tempVec.addElement(new Double(0));
		tempVec.addElement(new Double(0));
		tempVec.addElement(new Double(1));
		inputVectors.addElement(tempVec);
		tempVec = new Vector();
		tempVec.addElement(new Double(1));
		tempVec.addElement(new Double(1));
		tempVec.addElement(new Double(0));
		inputVectors.addElement(tempVec);
		tempVec = new Vector();
		tempVec.addElement(new Double(1));
		tempVec.addElement(new Double(0));
		tempVec.addElement(new Double(1));
		inputVectors.addElement(tempVec);
		tempVec = new Vector();
		tempVec.addElement(new Double(0));
		tempVec.addElement(new Double(1));
		tempVec.addElement(new Double(1));
		inputVectors.addElement(tempVec);
		tempVec = new Vector();
		tempVec.addElement(new Double(0));
		tempVec.addElement(new Double(0));
		tempVec.addElement(new Double(0));
		inputVectors.addElement(tempVec);
		tempVec = new Vector();
		tempVec.addElement(new Double(1));
		tempVec.addElement(new Double(1));
		tempVec.addElement(new Double(1));
		inputVectors.addElement(tempVec);
		tempVec = new Vector();
		tempVec.addElement(new Double(0.5));
		tempVec.addElement(new Double(0.5));
		tempVec.addElement(new Double(0.5));
		inputVectors.addElement(tempVec);

	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 */
    private void initComponents() {//GEN-BEGIN:initComponents
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        renderPanel = new LatticeRenderer();
        ControlsPanel = new javax.swing.JPanel();
        btnRetrain = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setTitle("SOM Demo");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setDividerSize(5);
        jSplitPane1.setEnabled(false);
        jPanel1.setLayout(new java.awt.CardLayout());

        renderPanel.setBackground(new java.awt.Color(0, 0, 0));
        renderPanel.setFont(new java.awt.Font("Dialog", 0, 11));
        renderPanel.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel1.add(renderPanel, "card2");

        jSplitPane1.setLeftComponent(jPanel1);

        btnRetrain.setText("Retrain Map");
        btnRetrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrainActionPerformed(evt);
            }
        });

        ControlsPanel.add(btnRetrain);

        jButton1.setText("Stop Training");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ControlsPanel.add(jButton1);

        jSplitPane1.setRightComponent(ControlsPanel);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(550, 400));
        setLocation((screenSize.width-550)/2,(screenSize.height-400)/2);
    }//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		trainer.stop();
	}//GEN-LAST:event_jButton1ActionPerformed

	private void btnRetrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrainActionPerformed
		trainer.stop();
		lattice = new SOMLattice(latticeWidth, latticeHeight,num);
		trainer.setTraining(lattice, inputVectors, renderPanel);
		renderPanel.registerLattice(lattice);
		trainer.start(inputVectors);
		trainer.run();
	}//GEN-LAST:event_btnRetrainActionPerformed

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
		System.exit(0);
	}//GEN-LAST:event_exitForm


	public void go() {
		BufferedImage i = renderPanel.getImage();
		renderPanel.registerLattice(lattice);
		renderPanel.render(lattice, 0);
		trainer.setTraining(lattice, inputVectors, renderPanel);
		trainer.start(inputVectors);
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LatticeRenderer renderPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton btnRetrain;
    private javax.swing.JPanel ControlsPanel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}