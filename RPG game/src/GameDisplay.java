import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GameDisplay extends TestClass{
  
  private Fields[][] fieldList;
  private ArrayList<String> dataList;
  
  
  public GameDisplay(String MAP_LAYOUT_SOURCE, int fieldInARow){
    Path file = Paths.get(MAP_LAYOUT_SOURCE);
    try {
      dataList = new ArrayList<>(Files.readAllLines(file));
      fieldList  = new Fields[fieldInARow][fieldInARow];
      int posXCounter = 0;
      int posYCounter = 0;
      for (int index = 0; index < dataList.size(); index++) {
        String[] rows = (dataList.get(index).split(" "));
        for (int subIndex = 0; subIndex < rows.length; subIndex++) {
          boolean isWall = false;
          if (Integer.parseInt(rows[subIndex]) == 1) {
            isWall = true;
          }
          Fields field = new Fields(posXCounter, posYCounter, isWall);
          fieldList[index][subIndex] = field;
          posXCounter += 72;
        }
        posXCounter = 0;
        posYCounter += 72;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public GameDisplay(){
  }
  
  public Fields[][] getFieldList() {
    return fieldList;
  }
}
