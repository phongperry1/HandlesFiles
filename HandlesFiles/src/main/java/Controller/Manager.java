package Controller;

import Model.Person;
import View.Validation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Manager {
    Validation vl = new Validation();

    public int menu() {
        System.out.println("1. Find person info.");
        System.out.println("2. Copy Text to new file.");
        System.out.println("3. Exit");
        int choice = vl.checkInputIntLimit(1, 3);
        return choice;
    }

    public void findPersonInfo() {
        String pathFile = vl.checkInputPathFile();
        ArrayList<Person> lp = getListPerson("E:\\Test.txt");

        if (lp == null || lp.isEmpty()) {
            System.out.println("No valid person records found.");
            return;
        }

        double money = vl.checkInputMoney();
        printListPerson(lp, money);
    }

    public void copyNewFile() {
        String pathFileInput = vl.checkInputPathFile();
        String pathFileOutput = vl.checkInputPathFile();
        String content = getNewContent(pathFileInput);
        writeNewContent(pathFileOutput, content);
    }

    public ArrayList<Person> getListPerson(String pathFile) {
        ArrayList<Person> lp = new ArrayList<>();
        File file = new File("E:\\Test.txt");

        if (!file.exists() || !file.isFile()) {
            System.out.println("Invalid file path.");
            return lp; 
        }

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferReader.readLine()) != null) {
                String[] infoPerson = line.split(";");
                if (infoPerson.length == 3) {
                    lp.add(new Person(infoPerson[0], infoPerson[1], getSalary(infoPerson[2])));
                }
            }
            bufferReader.close();
        } catch (IOException e) {
            System.err.println("Can't read file.");
        }

        return lp;
    }

    public double getSalary(String salary) {
        double salaryResult = 0;

        try {
            salaryResult = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            salaryResult = 0;
        }

        return salaryResult; // Removed 'finally' block
    }

    public void printListPerson(ArrayList<Person> lp, double money) {
        System.out.printf("%-25s%-25s%-25s\n", "Name", "Address", "Money");

        for (Person person : lp) {
            if (person.getMoney() >= money) {
                System.out.printf("%-25s%-25s%-21.1f\n", person.getName(), person.getAddress(), person.getMoney());
            }
        }

        if (!lp.isEmpty()) {
            Collections.sort(lp);
            System.out.println("Max: " + lp.get(0).getName());
            System.out.println("Min: " + lp.get(lp.size() - 1).getName());
        }
    }

    public String getNewContent(String pathFileInput) {
        HashSet<String> newContent = new HashSet<>();
        File file = new File("E:\\TestInput.txt");

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String word = sc.next();
                newContent.add(word + " ");
            }

            sc.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Can't read file");
        }

        StringBuilder content = new StringBuilder();
        for (String line : newContent) {
            content.append(line);
        }

        return content.toString();
    }

    public void writeNewContent(String pathFileOutput, String content) {
        try (FileWriter fileWriter = new FileWriter("E:\\TestOutput.txt");
             BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
            bufferWriter.write(content);
            System.out.println("Write successful");
        } catch (IOException e) {
            System.err.println("Can't write file");
        }
    }
}
