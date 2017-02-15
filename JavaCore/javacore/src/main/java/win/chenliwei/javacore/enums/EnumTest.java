/**
 * @author: ChenLiwei
 * 2017-02-15
 * EnumTest.java
 * Comments: it is to demonstrate the usage of enum class in java
 */
package win.chenliwei.javacore.enums;

import java.util.Arrays;

public class EnumTest {

	public static void main(String[] args) {
		System.out.println(Size.SMALL);
		System.out.println(Arrays.toString(Size.values()));
		System.out.println(Size.valueOf("SMALL"));
		
		System.out.println(Size.SMALL.getAbbr());
	}

}


