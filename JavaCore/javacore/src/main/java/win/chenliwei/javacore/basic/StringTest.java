package win.chenliwei.javacore.basic;

import static java.lang.System.out;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
/*
 * this demo is to demonstrate most usage of String class
 */
public class StringTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = ""; // this is a empty string but not a null string
		String s1 = null;
		String s2 = "Test";
		String s3 = new String("Test"); //we usually don't use this way to create a string, though it is essentially same to s2
		
		out.println(s.equals("")); //cannot use == to compare string
		out.println(s != null); //cannot use out.println(s.equals(null)); to compare to null
		
		out.println(s1 == null);
		
		out.println((s == null) || (s.equals("")));
		out.println((s1 == null) || (s1.equals(""))); // this is the best way to test empty string or null string
		
		out.println(s2.equals(s3));
		out.println(s2 != s3); //always keep in mind use equals method but not ==
		
		String s4 = s2 + s3; // use + to concatenate the strings
		@SuppressWarnings("unused")
		String s5 = s2.concat(s3); //we use the way to create s4 for simplicity
		
		s4 = s2 + 3; // firstly integer 3 cast to character 3
		out.println(s4);
		s4 = 2 + 3 + s2; // see the result, actually it depends on the first variable's type, try to avoid it
		out.println(s4); 
		
		out.println(s4.length());
		byte[] byteS4 = s4.getBytes();
		out.println(byteS4.length); // here, 5 characters = 5 bytes
		
		s4 = s4 + "颉"; //default, the string is stored in charset utf8
		out.println(s4.length());
		byteS4 = s4.getBytes();
		out.println(byteS4.length); //here, 6 characters = 8 bytes
		
		//let's switch the charset from utf8 to gb2312
		byteS4 = s4.getBytes("gb2312");
		out.println(byteS4.length); //see, the length changed to 7
		
		//the following is the string API used very often
		s1 = "Apple";
		s2 = "Peanapple";
		s3 = "apple";
		out.println(s1.compareTo(s2)); // return 0 if equals as dictionary order
		out.println(s1.equalsIgnoreCase(s3));
		out.println(s2.indexOf("a"));
		out.println(s2.indexOf("a",3));
		out.println(s2.lastIndexOf("a"));
		out.println(s2.lastIndexOf("a",3)); // in reverse direction from end or start_index to start
		out.println(s2.replace("a", "A"));
		out.println(s2.substring(4));
		out.println(s2.substring(4,7));//Remember the 7 is the first character excluded
		out.println(s1.toLowerCase());
		out.println(s3.toUpperCase());
		
		//Use StringBuilder never use StringBuffer
		//the below code construct all method of String Class
		StringBuilder sb = new StringBuilder();
		for(Method m : String.class.getDeclaredMethods()){
			sb.append(m.toGenericString() + "\n");
		}
		out.println(sb);
	}

}
