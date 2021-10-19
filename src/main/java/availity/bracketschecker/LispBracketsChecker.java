package availity.bracketschecker;

/*
Coding exercise:
    You are tasked to write a checker that validates the parentheses of a LISP code.
    Write a program (in Java or JavaScript) which takes in a string as an input
    and returns true if all the parentheses in the string are properly closed and nested.
 */

public class LispBracketsChecker {
    public static void main(String[] args) {

        System.out.println(isBracketsBalanced("(list 1 2 3)") );            // true
        System.out.println(isBracketsBalanced("(list 1 2 (quote foo))") );  // true
        System.out.println(isBracketsBalanced("((+ 2 2 )") );               // false
    }

    public static boolean isBracketsBalanced(String txt) {
        if ( (txt == null) || (txt.length()  <= 1)) {
            return false;
        }
        int count = 0;
        for ( char ch: txt.toCharArray()) {
            switch (ch) {
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
            }
        }
        return 0 == count;
    }
}
