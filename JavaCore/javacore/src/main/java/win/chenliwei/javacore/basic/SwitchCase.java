package win.chenliwei.javacore.basic;
/*
 * this demo demonstrate why we don't suggest to use switch case clause in the java program
 * because it is so dangerous, consider the output of the below code
 * if you want to use switch, keep in mind the below 2 points
 * 1. use break clause end of each case
 * 2. place the default case at the end of the whole switch
 */
public class SwitchCase {

	public static void main(String[] args) {
		int i = 9;
		switch(i){
		case 1: System.out.print("");
		default: System.out.print("Error");
		case 3: System.out.print("Good");
		case 4: System.out.print("Best");
		}
	}

}