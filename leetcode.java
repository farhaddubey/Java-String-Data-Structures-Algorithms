import java.util.Map;
import java.util.HashMap; 

public class leetcode{
    public static void main(String[] args){
        String s="Hello";
        int len=s.length(); 
        char ch=s.charAt(0);
        String sub1=s.substring(2); // Returns the entire substring starting from the index 2--> Suffix Part of String 
        String sub2=s.substring(1, 4); // Returns "ell" 
        boolean isEqual=s.equals("Hello"); // true/false 
        boolean isEqualIgnoreCase = s.equalsIgnoreCase("hello"); // Returns true; 
        int result=s.compareTo("World"); // compares hello with the "World"
        int index1=s.indexOf('e'); // Returns 1 the 1st index of the character 
        int index2=s.indexOf("ee"); // 
        int lastIndexOf=(s.lastIndexOf('l')); // Returns 3 
        int lastIndexOf=s.lastIndexOf("ll"); // Returns 3
        boolean starts=s.startsWith("He"); // Returns true;
        boolean ends=s.endsWith("lo"); // Returns true;
        String lower=s.toLowerCase(); // Returns hello; 
        String upper =s.toUpperCase(); // Returns Upper;
        String trimmed ="   Hello   ".trim(); // Returns Hello 
        String replacedChar =s.replace('l', 'p'); // Returns 'Heppo'
        String replacedStr = s.replace("ll", "yy"); // "Heyyo" 
        String str="apple, orange, banana"; 
        String[] fruits=str.split(","); 
        String concatStr=s.concat(" Wolrd"); // Returns Hello World 
        boolean empty=s.isEmpty();
        String joined=String.join(", ", "apple", "orange", "banana");
        boolean isMatch=s.matches("[A-Za-z]+"); // REturns true if the string contains only alphabets 
        char[] chars=s.toCharArray(); 
        String str1=new String("Hello");
        String str2=str1.intern(); // Interns the string 
        String formatted =String.format("Name: %s, Age: %d, ", "John", 25);
    }
}

