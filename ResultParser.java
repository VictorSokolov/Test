package test;

public class ResultParser {
  private long id; // Вероятно, id не может не существовать поэтому лучше конструктор с заданием значения id сразу и финализация переменной
  private String                // Вероятно пары значений соответствуют друг-другу. Id обязателен, имя (описание) может отсутствовать
          sectorId, sectorName, // сектор
          rowId,    rowName,    // ряд
          seatId,   seatName;   // место

public ResultParser(){ }                          // Конструктор по умолчанию
public ResultParser(long IdRP){ id = IdRP; } // Конструктор с параметром, возможно объекты без Id не имеют смысла		  
  
  public void setId(long id) { this.id = id; } // возможно id устанавливается единожды, при создании и теоретически не должно меняться final, тогда метод не нужен
  public long getId() { return id; }
  
  public void setSectorId(String sctrId) { this.sectorId = normStr(sctrId); }
  public String getSectorId() { return sectorId; }

  public void setSectorName(String sctrName) { this.sectorName = normStr(sctrName); } // Лучше сразу приводить к единообразию при вводе и не преобразовывать при каждой проверке
  public String getSectorName() { return sectorName; }

  public void setRowId(String rwId) { this.rowId = normStr(rwId); }
  public String getRowId() { return rowId; }
  
  public void setRowName(String rwName) { this.rowName = normStr(rwName); } // Лучше сразу приводить к единообразию при вводе и не преобразовывать при каждой проверке
  public String getRowName() { return rowName; }
  
  public void setSeatId(String stId) { this.seatId = normStr(stId); } // Лучше сразу приводить к единообразию при вводе и не преобразовывать при каждой проверке
  public String getSeatId() { return seatId; }
  
  public void setSeatName(String stName) { this.seatName = normStr(stName); } // Лучше сразу приводить к единообразию при вводе и не преобразовывать при каждой проверке
  public String getSeatName() { return seatName; }

  public String getFullSector() { return sectorId != null ? toString(sectorId, sectorName) : "";} // Думаю этот метод ничем не должен отличаться от следующих, а все необходимое можно собрать в методе toString()
  public String getFullRow() { return rowId != null ? toString(rowId, rowName) : ""; }
  public String getFullSeat() { return seatId != null ? toString(seatId, seatName) : ""; }

  private String normStr(String str) { return str.toLowerCase(); } // Общий для всех полей вид. Лучше сразу приводить к какому-то единообразию при вводе
  private String toString(String prm, String prmNm) { return prm + " " + prmNm; }  
}