import java.util.ArrayList;
import java.util.Vector;

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

	public void start(Vector I)
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