/**
 * @author: ChenLiwei
 * 2017-02-20
 * StaticInnerClassTest.java
 * Comments: Sometimes, we need a inner class to hide it to outside
 * and at same time, inner class doesn't need to visit the outer class variables
 * if this case, we could use static inner class just as a static variable.
 */
package win.chenliwei.javacore.innerclass;

public class StaticInnerClassTest {

	public static void main(String[] args) {
		ArrayAlg.Pair p = new ArrayAlg().minmax(new double[]{3.2,3,3.5,666,34343.545,0.344});
		System.out.println("the max of them is : " + p.getMax());
		System.out.println("the min of them is : " + p.getMin());
	}
	
}

class ArrayAlg{
	public static class Pair{
		private double min;
		private double max;
		public Pair(double min, double max) {
			this.min = min;
			this.max = max;
		}
		public double getMin() {
			return min;
		}
		public double getMax() {
			return max;
		}
	}
	public Pair minmax(double[] array){
		double max = Double.MIN_VALUE;
		double min = Double.MAX_VALUE;
		for(double d : array){
			if(d > max) max = d;
			if(d < min) min = d;
		}
		return new Pair(min,max);
	}
}
