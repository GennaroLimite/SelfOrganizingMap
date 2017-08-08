import java.util.ArrayList;

public class SOMNode
{
	private ArrayList<Double> weights;
	private int xp, yp;
	
	/**
	 * Creates a new instance of SOMNode 
	 * @param numWeights
	 */
	public SOMNode(int numWeights) 
	{
		weights = new ArrayList<Double>();
		for (int x=0; x<numWeights; x++) 
		{
			weights.add(new Double(Math.random()));
		}
	}

	/**
	 * PRENDO IL VALORE
	 * @param z
	 * @return
	 */
	public double getValue(int z)
	{
		return weights.get(z);
	}
	
	/**
	 * SETTO LA X
	 * @param xpos
	 */
	public void setX(int xpos) 
	{
		xp = xpos;
	}
	
	/**
	 * SETTO LA Y
	 * @param ypos
	 */
	public void setY(int ypos) 
	{
		yp = ypos;
	}
	
	/**
	 * PRENDO LA X
	 * @return
	 */
	public int getX() 
	{
		return xp;
	}
	
	/**
	 * PRENDO LA Y
	 * @return
	 */
	public int getY() 
	{
		return yp;
	}
	

	/** Computes the distance to another node.  Used for
	 *  neighborhood determination.  Returns the SQUARE of the distance
	 */
	public double distanceTo(SOMNode n2) 
	{
		int xleg, yleg;
		xleg = getX() - n2.getX();
		xleg *= xleg;
		yleg = getY() - n2.getY();
		yleg *= yleg;
		return xleg + yleg;
	}
	
	/**
	 * SETTO IL PESO
	 * @param w
	 * @param value
	 */
	public void setWeight(int w, double value) 
	{
		if (w >= weights.size())
			return;
		weights.add(value);		
	}
	
	/**
	 * PRENDO IL PESO
	 * @param w
	 * @return
	 */
	public Double getWeight(int w) 
	{
		if (w >= weights.size())
			return 0.0;
		return weights.get(w);
	}
	
	/**
	 * RITORNO IL VETTORE ASSOCIATO A QUEL NODO
	 * @return
	 */
	public ArrayList<Double> getVector() 
	{
		return weights;
	}
	
	/**
	 * AGGIUSTO I PESI DEI VICINI 
	 * @param input
	 * @param learningRate
	 * @param distanceFalloff
	 */
	public void adjustWeights(ArrayList<Double> input, double learningRate, double distanceFalloff)
	{
		double wt, vw;
		for (int w=0; w<weights.size(); w++) 
		{
			wt = (weights.get(w)).doubleValue();
			vw = (input.get(w)).doubleValue();
			wt += distanceFalloff * learningRate * (vw - wt);
			weights.set(w, new Double(wt));
		}
	}
	
	/**
	 * CALCOLO LA DISTANZA EUCLIDEA
	 * @param inputVector
	 * @param v2
	 * @return
	 */
	public double euclideanDist(ArrayList<Double> inputVector , ArrayList<Double> v2) {
		double summation = 0.0, temp;
		for (int x=0; x < weights.size(); x++) {
			temp = ( inputVector.get(x) - v2.get(x));
			temp *= temp;
			summation += temp;
		}
		return summation;
	}
	
}