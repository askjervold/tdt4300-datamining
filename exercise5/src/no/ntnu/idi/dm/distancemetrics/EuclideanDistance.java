package no.ntnu.idi.dm.distancemetrics;

/**
 * Implements the L2 or Euclidean metric.
 * 
 */
public class EuclideanDistance {

	public double distance(double[] vector1, double[] vector2) {
		double dist = 0;
		int len = vector1.length;
		if (vector2.length != len) return -1; // If the vectors aren't the same size, there's something wrong
		
		for (int i=0; i < len; i++) {
			double temp = vector2[i] - vector1[i];
			dist += Math.pow(temp, 2);
		}
		
		return Math.sqrt(dist);
	}

}
