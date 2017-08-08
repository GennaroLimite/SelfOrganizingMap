import java.util.ArrayList;
import java.util.Vector;

public class SOMLattice 
{
	private int width, height;
	private SOMNode[][] matrix;
	
	/** Creates a new instance of SOMLattice,
	 *  which is a 2x2 array of SOMNodes. For now, it
	 *  assumes an input vector of three values, and
	 *  randomly initializes the array as such.
	 */
	public SOMLattice(int w, int h, int num) 
	{
		width = w;
		height = h;
		matrix = new SOMNode[width][height];
		for (int x=0; x<w; x++) {
			for (int y=0; y<h; y++) {
				matrix[x][y] = new SOMNode(num);
				matrix[x][y].setX(x);
				matrix[x][y].setY(y);
			}
		}
	}
	
	/**
	 * STAMPO LA MATRICE DI NEURONI
	 */
	public void printLattice()
	{
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				System.out.print(matrix[i][j].getVector()+" ");
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * Returns the SOMNode at the given point (x,y)
	 * @param x
	 * @param y
	 * @return
	 */
	public SOMNode getNode(int x, int y) 
	{
		return matrix[x][y];
	}

	/**
	 * PRENDO LA LARGHEZZA DELLA MATRICE
	 * @return
	 */
	public int getWidth() 
	{
		return width;
	}
	/**
	 * PRENDO L'ALTEZZA DELLA MATRICE
	 * @return
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/** Finds the best matching unit for the given
	 *  inputVector
	 */
	public SOMNode getBMU(Vector inputVector) 
	{
		// Start out assuming that 0,0 is our best matching unit
		SOMNode bmu = matrix[0][0];
		
		double bestDist = 1000.0;
		double curDist;
		
		// Now step through the entire matrix and check the euclidean distance
		// between the input vector and the given node
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				//System.out.println("Il vettore di input e' : " + inputVector + "\n");
				
				curDist = bmu.euclideanDist(inputVector,matrix[x][y].getVector());
				if (curDist < bestDist) {
					// If the distance from the current node to the input vector
					// is less than the distance to our current BMU, we have a 
					// new BMU
					bmu = matrix[x][y];
					bestDist = curDist;
				}
			}
		}
		return bmu;
	}
	
}