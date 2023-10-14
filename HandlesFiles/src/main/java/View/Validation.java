
package View;

import java.util.Scanner;


public class Validation {
    public int checkInputIntLimit(int min, int max) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                if(result < min || result > max) {
                    throw new NumberFormatException();
                    
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again");
            }
        }
            
        
    
    }
    
    public String checkInputPathFile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter path file: ");
        while(true) {
            String result = sc.nextLine().trim();
            if(result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again");
            } else {
                return result;
            }
        }
    }
    
    public double checkInputMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter money: ");
        while(true) {
            try {
                double result = Double.parseDouble(sc.nextLine());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Must be input double");
                System.out.print("Enter again: ");
            }
        }
    }
}
