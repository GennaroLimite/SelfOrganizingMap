import java.util.ArrayList;
//import java.util.List;

public class SOM implements Cluster{

	private SOMLattice SOML;
	
	// These constants can be changed to play with the learning algorithm
	private static final double START_LEARNING_RATE = 0.07;
	private static final int	NUM_ITERATIONS = 1;
	
	// These two depend on the size of the lattice, so are set later
	private double LATTICE_RADIUS;
	private double TIME_CONSTANT;
	private SOMLattice lattice;
	private ArrayList<Double> inputs;
	private static boolean running;
	
	public SOM() {
		running = false;
	}
	public void startLattice(int w,int h,int num)
	{
		SOML = new SOMLattice(w,h,num);
	}
	
	public void printLattice()
	{
		SOML.printLattice();
	}
	
	private double getNeighborhoodRadius(double iteration) {
		return LATTICE_RADIUS * Math.exp(-iteration/TIME_CONSTANT);
	}
	
	private double getDistanceFalloff(double distSq, double radius) {
		double radiusSq = radius * radius;
		return Math.exp(-(distSq)/(2 * radiusSq));
	}
	
	public void start(ArrayList<Double> i ) {
		if (SOML != null) {
			running = true;
			inputs = i;
		}
	}
	
	public void run() {
		int lw = SOML.getWidth();
		int lh = SOML.getHeight();
		int xstart, ystart, xend, yend;
		double dist, dFalloff;
		// These two values are used in the training algorithm
		LATTICE_RADIUS = Math.max(lw, lh)/2;
		TIME_CONSTANT = NUM_ITERATIONS / Math.log(LATTICE_RADIUS);
		
		int iteration = 0;
		double nbhRadius;
		SOMNode bmu = null, temp = null;
		ArrayList<Double> curInput = null;
		double learningRate = START_LEARNING_RATE;
		
		while (iteration < NUM_ITERATIONS && running) 
		{
			nbhRadius = getNeighborhoodRadius(iteration);
			// For each of the input vectors, look for the best matching
			// unit, then adjust the weights for the BMU's neighborhood				
				curInput = inputs;
				bmu = SOML.getBMU(curInput);
				// We have the BMU for this input now, so adjust everything in
				// it's neighborhood
				
				// Optimization:  Only go through the X/Y values that fall within
				// the radius
				xstart = (int)(bmu.getX() - nbhRadius - 1);
				ystart = (int)(bmu.getY() - nbhRadius - 1);
				xend = (int)(xstart + (nbhRadius * 2) + 1);
				yend = (int)(ystart + (nbhRadius * 2) + 1);
				if (xend > lw) xend = lw;
				if (xstart < 0) xstart = 0;
				if (yend > lh) yend = lh;
				if (ystart < 0) ystart = 0;
				
				for (int x=xstart; x<xend; x++) {
					for (int y=ystart; y<yend; y++) {
						temp = SOML.getNode(x,y);
							dist = bmu.distanceTo(temp);
							if (dist <= (nbhRadius * nbhRadius)) {
								dFalloff = getDistanceFalloff(dist, nbhRadius);
								temp.adjustWeights(curInput, learningRate, dFalloff);
							}
					}
				}
			iteration++;
			learningRate = START_LEARNING_RATE *
							Math.exp(-(double)iteration/NUM_ITERATIONS);
		}
		running = false;
		
	}
	
	public void stop() {
			running = false;
		}
}