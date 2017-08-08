import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DataSet
{
	//PRIVATE
	private int Feature;
	private int Record;
	private ArrayList<Integer> UsedFeature;
	private String DatasetName;
	private ArrayList<ArrayList<Double>> Matrix ;
	
	/**
	 * PRENDERE LA CELLA DESIDERATA
	 * @param i
	 * @param j
	 * @return
	 */
 	public double getCell(int i, int j)
 	{
 		return Matrix.get(j).get(i);
 	}
	
	/**
	 * INIZIALIZZAZIONE DELLA MATRICE
	 */
	public void MatrixInizializer()
	{
		Matrix = new ArrayList<ArrayList<Double>>();
		UsedFeature = new ArrayList<Integer>();
		for(int i=0;i<Feature;i++)
		{
			Matrix.add(new ArrayList<Double>());
			UsedFeature.add(i);
		}
	}
	
	/**
	 * SETTO IL DATASET COMPLETO
	 * @param Feature
	 * @param Nome
	 */
	public DataSet(int Feature, String Nome)
	{
		setFeature(Feature);
		setDatasetName(Nome);
		MatrixInizializer();
	}

	/**
	 * SETTO LA TABELLA(MATRICE) RIGUARDANTE IL DATASET
	 * @param Column
	 * @param Val
	 */
	public void setTab(int Column, Double Val)
	{
		Matrix.get(Column).add(Val);
	}
	
	/**
	 * SETTO LE FEATURE
	 * @param Feature
	 */
	public void setFeature (int Feature)
	{
		this.Feature = Feature;
		return;
	}
	
	/**
	 * SETTO IL NOME DEL DATASET
	 * @param DatasetName
	 */
	public void setDatasetName(String DatasetName)
	{
		this.DatasetName = DatasetName;
		return;
	}
	
	/**
	 * PRENDO LA TABELLA
	 * @return
	 */
	public ArrayList<ArrayList<Double>> getTab()
	{
		return this.Matrix;
	}
	
	/**
	 * STAMPO IL DATASET
	 */
	public void printDataset()
	{
		for(int i=0;i<Record;i++)
		{
			for(int j=0;j<Feature;j++)
			{
				System.out.print(Matrix.get(j).get(i)+" ");
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * PRENDO IL NOME DEL DATASER
	 * @return
	 */
	public String getDatasetName()
	{
		return this.DatasetName;
	}
	
	/**
	 * SETTO I RECORD
	 * @param Record
	 */
	public void setRecord(int Record)
	{
		this.Record = Record;
		return;
	}
	
	/**
	 * PRENDO I RECORD
	 * @return
	 */
	public int getRecord()
	{
		return this.Record;
	}
	
	/**
	 * PRENDO QUANTE FEATURE CI SONO
	 * @return
	 */
	public int getFeature() {
		return this.Feature;
	}
	
	/**
	 * SETTO LA TABELLA
	 * @param Colonna
	 * @param Valore
	 */
	public void setTabella(int Colonna, Double Valore) 
	{
		Matrix.get(Colonna).add(Valore);
	}
	
	/**
	 * PRENDO IL RECORD
	 * @param iRandom
	 * @return
	 */
	public ArrayList<Double> getRecord(int iRandom)
	{
		ArrayList<Double> record = new ArrayList<Double>();
		for(int i=0;i<Feature;i++)
			record.add(Matrix.get(i).get(iRandom));
		return record;
	}
	
	/**
	 * COSTRUISCO IL DATASET A PARTIRE DALLE FEATURE SELEZIONATE
	 * @param MatInput
	 * @param SelectedFeature
	 * @param Record
	 */
	public DataSet(ArrayList<ArrayList<Double>> MatInput, ArrayList<Integer> SelectedFeature, int Record)
	{
		Matrix = new ArrayList<ArrayList<Double>>();
		this.UsedFeature = SelectedFeature;
		this.Record = Record;
		this.setFeature(SelectedFeature.size());
		
		for(int i=0;i<SelectedFeature.size();i++)
			setColumn(MatInput.get(SelectedFeature.get(i)));
	}
	
	/**
	 * SETTO LE COLONNE
	 * @param ValueColoumn
	 */
	public void setColumn(ArrayList<Double> ValueColoumn)
	{
		this.Matrix.add(ValueColoumn);
	}
	
}