import java.util.ArrayList;

public interface Cluster {

	public void startLattice(int w,int h, int num);
	public void printLattice();
	public void start(ArrayList<Double> I);
	public void run();
	public void stop();
	

}