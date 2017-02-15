/**
 * @author: ChenLiwei
 * 2017-02-15
 * Size.java
 * Comments:  this is the first time to see the keyword different with class
 */
package win.chenliwei.javacore.enums;

public enum Size {
	//the below is a special way of Constructor of enum type. For "class", we call the constructor
	//same as the class name, here, it is different, just remember this format;
	SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRALARGE("XL"),EXTRAEXTRALARGE("XXL");
	
	//the above statement means 5 instances of Size generated
	
	private String abbr;
	Size(String abbr){
		this.abbr = abbr; //you can define any number of parameter here
	}
	public String getAbbr(){
		return this.abbr;
	}
	
	//toString function is the most useful one which extends from class Enum
	//default manner is to display the name of the enum name
	@Override public String toString(){
		return this.name() + ",its abbreviation is " + this.abbr;
	}
}

//class EnumClass extends Enum{
//	
//}