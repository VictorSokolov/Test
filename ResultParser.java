package test;

public class ResultParser {
  private long id; // Вероятно, id не может не существовать поэтому лучше конструктор с заданием значения id сразу и финализация переменной
  private String                // Вероятно пары значений соответствуют друг-другу. Id обязателен, имя (описание) может отсутствовать
          sectorId, sectorName, // сектор
          rowId,    rowName,    // ряд
          seatId,   seatName;   // место
  // Не должны ли места иметь дополнительные данные в описании? Например, номер ближайшего выхода!?

public ResultParser(){ }                          // Конструктор по умолчанию
public ResultParser(long IdRP){ this.id = IdRP; } // Конструктор с параметром, возможно объекты без Id не имеют смысла
		  
  // ИМХО зачем пустые строки!? Лучше писать покороче. Любая идея должна излагаться на одной странице - говорили в IBM.Весь код должен помещаться на одном экране - Forth
  public void setId(long id) { this.id = id; } // возможно id устанавливается единожды, при создании и теоретически не должно меняться final, тогда метод не нужен
  public long getId() { return this.id; }
  
 /*
  Лучше сразу приводить к единообразию при вводе и не преобразовывать каждый раз при проверке
  А, ещё лучше создать множества допустимых значений, для каждого поля (если это возможно).
  Например, public enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY } Можно коллекцию в .ini файле
 */
 /*
  Если *Id соответствует *Name, то правильнее:
  public void setSector(String SectorId, String SectorName) { sectorId = normStr(SectorId); sectorName = normStr(SectorName); }
  public String getSectorId()   { return this.sectorId; }
  public String getSectorName() { return this.sectorName; }
 */ 
  public void setSectorId(String sctrId) { this.sectorId = normStr(sctrId); }
  public String getSectorId() { return this.sectorId; }

  public void setSectorName(String sctrName) { this.sectorName = normStr(sctrName); } // Лучше сразу приводить к единообразию при вводе и не преобразовывать при каждой проверке
  public String getSectorName() { return this.sectorName; }

  public void setRowId(String rwId) { this.rowId = normStr(rwId); }
  public String getRowId() { return this.rowId; }
  
  public void setRowName(String rwName) { this.rowName = normStr(rwName); } // Лучше сразу приводить к единообразию при вводе и не преобразовывать при каждой проверке
  public String getRowName() { return this.rowName; }
  
  public void setSeatId(String stId) { this.seatId = normStr(stId); } // Лучше сразу приводить к единообразию при вводе и не преобразовывать при каждой проверке
  public String getSeatId() { return this.seatId; }
  
  public void setSeatName(String stName) { this.seatName = normStr(stName); } // Лучше сразу приводить к единообразию при вводе и не преобразовывать при каждой проверке
  public String getSeatName() { return this.seatName; }
  
/*
  Думаю сектор, ряд или место может быть без имени (с пустым именем), но без идентификатора быть не может, а тогда условие можно упростить
  public String getFullSector() { return sector != null && sectorName != null ? toString(sector, sectorName) : "";} // Думаю этот метод ничем не должен отличаться от следующих, а все необходимое можно собрать в методе toString()
  public String getFullRow() { return row != null && rowName != null ? toString(row, rowName) : ""; }
  public String getFullSeat() { return seat != null && seatName != null ? toString(seat, seatName) : ""; }
*/
  // Практически ? врядли отличается от if
  public String getFullSector() { return this.sectorId != null ? toString(this.sectorId, this.sectorName) : "";} // Думаю этот метод ничем не должен отличаться от следующих, а все необходимое можно собрать в методе toString()
  public String getFullRow() { return this.rowId != null ? toString(this.rowId, this.rowName) : ""; }
  public String getFullSeat() { return this.seatId != null ? toString(this.seatId, this.seatName) : ""; }

  private String normStr(String str) { return str.toLowerCase(); } // Общий для всех полей вид. Лучше сразу приводить к какому-то единообразию при вводе
  private String toString(String prm, String prmNm) { // Единообразное форматирование данных. Возможно метод надо развить до вывода всей информации объекта
	return prm + " " + prmNm; // Или return formatter.format("%S%n %S%n", prm, prmNm) (тут можно ещё учесть Locale); но тогда надо будет догрузить библиотеку
  }  
}
