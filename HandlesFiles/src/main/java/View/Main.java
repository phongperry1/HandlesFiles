package View;

import Controller.Manager;



public class Main {

    public static void main(String[] args) {
        Manager mn = new Manager();
        while (true) {            
            int choice = mn.menu();
            switch (choice) {
                case 1:
                    mn.findPersonInfo();
                    break;
                case 2:
                    mn.copyNewFile();
                    break;
                case 3:
                    return;
            }
        }
    }
}
