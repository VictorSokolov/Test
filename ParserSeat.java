package test;

import java.util.HashMap;
// import java.util.Collections; // Это если второй вариант таблицы перекодировки
import java.util.regex.*; // import java.util.regex.Pattern.*; // загружаем компоненты по минимуму

public class ParserSeat {
    private final static HashMap<String, String> misprints = new HashMap<String, String>() {{ // Map это пара: Ключ+Значение, ключ по любому короче значения ?Замена рус./лат.
        put("С", "C");
        put("Е", "E");
        put("Т", "T");
        put("Н", "H");
        put("У", "Y");
        put("О", "O");
        put("Р", "P");
        put("Х", "X");
        put("А", "A");
        put("В", "B");
        put("К", "K");
        put("М", "M");
    }};
    /* Так будет совсем неизменяемая 
    private static final HashMap<Character, Character> misprints; // Character должен быть меньше String, но возможно преобразования типов
    static {
        HashMap aMap = new HashMap();        
        aMap.put("С", "C");        aMap.put("Е", "E");        aMap.put("Т", "T");        aMap.put("Н", "H");
        aMap.put("У", "Y");        aMap.put("О", "O");        aMap.put("Р", "P");        aMap.put("Х", "X");
        aMap.put("А", "A");        aMap.put("В", "B");        aMap.put("К", "K");        aMap.put("М", "M");
        misprints = (HashMap)Collections.unmodifiableMap(aMap);
    }    
    */

    public ParserSeat(){ } // ИМХО зачем пустые строки!? Лучше писать покороче. Любая идея должна излагаться на одной странице - говорили в IBM.Весь код должен помещаться на одном экране - Forth

    public static ResultParser parser(String seatName, long id){
        ResultParser result = new ResultParser(id);
       /*
        Вот в регулярных выражениях я ваащще не рублю, предположу, что исходный код работает правильно
        Matcher m = Pattern.compile("(?:(?<sectorId>.*?) (?<sectorName>(?:\\p{Lu}|\\d).*?)|(?<sectorWithoutName>.*)) (?<rowId>Ряд) (?<rowName>.*) (?<seatId>Место) (?<seatName>.*)").matcher(seatName);
        или
        String s = "(?:(?<sector>.*?) (?<sectorName>(?:\\p{Lu}|\\d).*?)|(?<sectorWithoutName>.*)) (?<row>Ряд) (?<rowName>.*) (?<seat>Место) (?<seatName>.*)";
        Pattern p = Pattern.compile(s, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(seatName);
       */
        Matcher m = Pattern.compile("(?:(?<sectorId>.*?) (?<sectorName>(?:\\p{Lu}|\\d).*?)|(?<sectorWithoutName>.*)) (?<rowId>Ряд) (?<rowName>.*) (?<seatId>Место) (?<seatName>.*)",
                Pattern.CASE_INSENSITIVE).matcher(seatName);
        if (m.find()) { // m.matches()
            if (m.group("sectorWithoutName") != null) {
                result.setSectorId(m.group("sectorWithoutName"));
                result.setSectorName("");
            } else {
                result.setSectorId(m.group("sectorId"));
                result.setSectorName(m.group("sectorName"));
            }
            result.setRowId(m.group("rowId"));
            result.setRowName(m.group("rowName"));
            result.setSeatId(m.group("seatId"));
            result.setSeatName(m.group("seatName"));
        }
       /*
        Если я прав, насчёт Id сектора, ряда и места, то проверять Name не надо
        if (result.getSectorId() == null || result.getRowId() == null || result.getRowName() == null || result.getSeatId() == null || result.getSeatName() == null)
       */
        if (result.getSectorId() == null || result.getRowId() == null || result.getSeatId() == null)
            return  null;
        for (String cyrillicCharacter : misprints.keySet())
            result.setSectorName(result.getSectorName().replace(cyrillicCharacter, misprints.get(cyrillicCharacter)));
        // А, не нужно ли и остальные поля с именами аналогично модифицировать?
        return result;
    }
}