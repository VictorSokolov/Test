package test;

import java.util.HashMap;
import java.util.regex.*;

public class ParserSeat { // !
    private final static HashMap<String, String> misprints = new HashMap<String, String>() {{
        put("С", "C"); put("Е", "E"); put("Т", "T"); put("Н", "H"); put("У", "Y"); put("О", "O");
        put("Р", "P"); put("Х", "X"); put("А", "A"); put("В", "B"); put("К", "K"); put("М", "M");
    }};
    
     public static String fastReplace(String word) { // !!!
         for (String cyrillicCharacter : misprints.keySet())
             word.replace(cyrillicCharacter, misprints.get(cyrillicCharacter));
         return word;
    }
    
    private static final Pattern p = Pattern.compile(
            "(?:(?<sector>.*?) (?<sectorName>(?:\\p{Lu}|\\d).*?)|(?<sectorWithoutName>.*)) (?<row>Ряд) (?<rowName>.*) (?<seat>Место) (?<seatName>.*)", Pattern.CASE_INSENSITIVE);
    
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
        for (String cyrillicCharacter : misprints.keySet())
            result.setSectorName(fastReplace(result.getSectorName()));
        return result;
    }
}