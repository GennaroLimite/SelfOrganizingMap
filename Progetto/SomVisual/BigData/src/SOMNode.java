import java.util.ArrayList;
import java.util.Vector;

public class SOMNode
{
	private Vector weights;
	private int xp, yp;
	
	/**
	 * Creates a new instance of SOMNode 
	 * @param numWeights
	 */
	public SOMNode(int numWeights) 
	{
		weights = new Vector();
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
		return ((Double)weights.elementAt(w)).doubleValue();
	}
	
	/**
	 * RITORNO IL VETTORE ASSOCIATO A QUEL NODO
	 * @return
	 */
	public Vector getVector() 
	{
		return weights;
	}
	
	/**
	 * AGGIUSTO I PESI DEI VICINI 
	 * @param input
	 * @param learningRate
	 * @param distanceFalloff
	 */
	public void adjustWeights(Vector input, double learningRate, double distanceFalloff)
	{
		double wt, vw;
		for (int w=0; w<weights.size(); w++) 
		{
			wt = ((Double)weights.elementAt(w)).doubleValue();
			vw = ((Double)input.elementAt(w)).doubleValue();
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
	public double euclideanDist(Vector inputVector , Vector v2) {
		double summation = 0.0, temp;
		for (int x=0; x < weights.size(); x++) {
			temp = ((Double)inputVector.elementAt(x)).doubleValue() - ((Double)v2.elementAt(x)).doubleValue();
			temp *= temp;
			summation += temp;
		}
		return summation;
	}
	
}