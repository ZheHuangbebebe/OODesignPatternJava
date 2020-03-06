package example;

import java.util.Scanner;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();
        String s = new String();
        for(int i = 0; i < words.length(); i++) {
        	s = s + words.charAt(i);
        	if(words.charAt(i) == ' ' || i == words.length() - 1) {
        		System.out.println(s);
        		s = "";
        	}
        }
    }
}
