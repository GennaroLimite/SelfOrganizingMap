import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.RowSorterEvent.Type;

public class DataSetIris extends DataSet
{
	//COSTRUTTORE
	public DataSetIris(String NomeFile) throws IOException
	{
		super(4,"Iris.data");
		System.out.println("DataSet name: " + super.getDatasetName());
		super.setRecord(LoadDataset(NomeFile));
		System.out.println("Record read from dataset: " + super.getRecord() + "\n");
	}
	
	//CARICO DATASET DA NOME
	protected int LoadDataset(String FileName) throws IOException
	{
		int iRecord = 0;
		BufferedReader bufferLetto = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
	      //  String filePath = new File("").getAbsolutePath();
			bufferLetto = new BufferedReader(new FileReader("./src/"+FileName));

			while ((line = bufferLetto.readLine()) != null) 
			{
				if (line.length() > 0) 
				{
					String[] cell = line.split(cvsSplitBy);
					this.addRow(cell);
					iRecord++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferLetto != null) {
				try {
					bufferLetto.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return iRecord;
	}
	
	//AGGIUNGO RIGA
	public void addRow(Object cell[])
	{
		for(int i=0;i<getFeature();i++)
		{
			super.setTabella(i, Double.parseDouble(cell[i].toString()));
		}
	}
}