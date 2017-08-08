import java.util.ArrayList;

public class DataMining {
	Cluster cluster;
	private DataSet dataset;
	
	/**
	 * COSTRUTTORE
	 * @param dataset
	 */
	public DataMining(DataSet dataset)
	{
		this.dataset = dataset;
	}
	
	public void selezionaFeature(ArrayList<Integer> featureSelezionate)
	{		
		//crea un dataset con solo le caratteristiche selezionate
		dataset = new DataSet(dataset.getTab(), featureSelezionate, dataset.getRecord() );
		System.out.println("DataSet delle feauture selezionate:\n");
		//Stampa dataset selezionato
		dataset.printDataset();

	}
	
	/**
	 * PASSA I RECORD DEL DATASET SELEZIONATO ALLA CLASSA TEST
	 * @param i
	 * @return
	 */
	public ArrayList <Double> passaRecord(int i)
	{
		return dataset.getRecord(i);
	}
	
	public void setCluster(Cluster cluster)
	{
		this.cluster = cluster;
	}
	
	public void set(int w, int h,int num)
	{
		cluster.startLattice(w, h, num);
	}
	
	public void print()
	{
		cluster.printLattice();
	}
	
	public void start(ArrayList<Double> I)
	{
		cluster.start(I);
	}
	
	public void run()
	{
		cluster.run();
	}
	
	public void stop()
	{
		cluster.stop();
	}
}