import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            //initializing your JSON source
            File myObj = new File("region_descriptions.formatted.json");
            FileWriter newJsonWriter = new FileWriter("destination.json");
            FileWriter largeLinesWriter = new FileWriter("large lines.json");
            FileWriter statWriter = new FileWriter("stats.txt");
            Scanner myReader = new Scanner(myObj);
            String lineSeparator = System.getProperty("line.separator");
            ArrayList<Integer> largeLinesSymbolsAmountCollection = new ArrayList<>();
            int lineCounter = 0;
            int largeLineCounter = 0;
            while (myReader.hasNextLine()/* && (lineCounter < 1000000)*/) {
                lineCounter ++;
                String currentLine = myReader.nextLine();
                if(currentLine.length() > 150) {
                    largeLinesSymbolsAmountCollection.add(currentLine.length());
                    largeLineCounter++;
                    largeLinesWriter.write(currentLine + lineSeparator);
                    System.out.println("Number of symbols in a large line: " + currentLine.length());
                    statWriter.write("Number of symbols in a large line: " + currentLine.length() + lineSeparator);
                }
                newJsonWriter.write(currentLine + lineSeparator);
            }
            System.out.println("Number of lines more than 150 symbols: " + largeLineCounter);
            statWriter.write("Number of lines more than 150 symbols: " + largeLineCounter + lineSeparator);
            System.out.println("Number of lines in source JSON file: " + lineCounter);
            statWriter.write("Number of lines in source JSON file: " + lineCounter + lineSeparator);
            Iterator largeLinesSymbolsAmountCollectionIterator = largeLinesSymbolsAmountCollection.listIterator();
            Integer maxNumberOfSymbolsInLine = 0;
            while (largeLinesSymbolsAmountCollectionIterator.hasNext()) {
                Integer currentNumber = (Integer) largeLinesSymbolsAmountCollectionIterator.next();
                if(currentNumber > maxNumberOfSymbolsInLine)
                    maxNumberOfSymbolsInLine = currentNumber;
            }
            System.out.println("Max line length: " + maxNumberOfSymbolsInLine);
            statWriter.write("Max line length: " + maxNumberOfSymbolsInLine + lineSeparator);
            myReader.close();
            newJsonWriter.close();
            largeLinesWriter.close();
            statWriter.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
