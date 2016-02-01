package test;

import java.util.HashMap;
import java.util.regex.*;

public class ParserSeat { // !
    private final static HashMap<Character, Character> misprints = new HashMap<Character, Character>() {{
        // java.util.HashMap<K,V> Type Parameters: K - the type of keys maintained by this map V - the type of mapped values
        // Получается мы русский на английский будем менять?
        put('С', 'C'); put('Е', 'E'); put('Т', 'T'); put('Н', 'H'); put('У', 'Y'); put('О', 'O');
        put('Р', 'P'); put('Х', 'X'); put('А', 'A'); put('В', 'B'); put('К', 'K'); put('М', 'M');
    }};
     private static final Pattern p = Pattern.compile(
            "(?:(?<sector>.*?) (?<sectorName>(?:\\p{Lu}|\\d).*?)|(?<sectorWithoutName>.*)) (?<row>Ряд) (?<rowName>.*) (?<seat>Место) (?<seatName>.*)", Pattern.CASE_INSENSITIVE);
     
     public static String fastReplace(String word) {
         String r ="";
         for(char c : word.toCharArray())
             r += misprints.containsKey(c) ? misprints.get(c) : c;
         return r;
    }
   public static String pureRepleace(String word) {
       String r = "";
         r = word.replaceAll("С", word);    r = word.replaceAll("Е", word);     r = word.replaceAll("Т", word);     r = word.replaceAll("Н", word);
         r = word.replaceAll("У", word);    r = word.replaceAll("О", word);     r = word.replaceAll("Р", word);     r = word.replaceAll("Х", word);
         r = word.replaceAll("А", word);    r = word.replaceAll("В", word);     r = word.replaceAll("К", word);     r = word.replaceAll("М", word);
         return r;
    }
   
    public ParserSeat(){ }

    public static ResultParser parser(String seatName, long id) {
        ResultParser result = new ResultParser(id);        
        Matcher m = p.matcher(seatName);
        if (m.find())
            if (m.group("sectorWithoutName") != null) {
                result.setSectorId(m.group("sectorWithoutName")); result.setSectorName("");
            } else {
                result.setSectorId(m.group("sectorId")); result.setSectorName(m.group("sectorName"));
                result.setRowId(m.group("rowId"));   result.setRowName(m.group("rowName"));
                result.setSeatId(m.group("seatId")); result.setSeatName(m.group("seatName"));
            }
        if (result.getSectorId() == null || result.getRowId() == null || result.getRowName() == null || result.getSeatId() == null || result.getSeatName() == null)
            return  null;
        result.setSectorName(fastReplace(result.getSectorName()));
        return result;
    }
}