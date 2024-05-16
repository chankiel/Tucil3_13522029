import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Dict{
    private static HashMap<String,Boolean> dict;
    static{
        dict = new HashMap<>();
        try{
            File dictFile = new File("dictionary.txt");
            Scanner dictScanner = new Scanner(dictFile);
            while (dictScanner.hasNextLine()) {
                String data = dictScanner.nextLine();
                dict.put(data, true);
            }
            dictScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }

    public static boolean checkWord(String word){
        return dict.containsKey(word);
    }
}