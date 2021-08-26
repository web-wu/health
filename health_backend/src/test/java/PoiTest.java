import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.List;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PoiTest {

    @Test
    public void readExcel() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook("C:\\wu\\poi.xlsx");
            XSSFSheet sheet = workbook.getSheetAt(0);
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();

            List<String[]> list = new ArrayList<>();
            for (int i = firstRowNum; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                short firstCellNum = row.getFirstCellNum();
                short lastCellNum = row.getLastCellNum();
                String[] cells = new String[lastCellNum];
                for (int j = firstCellNum; j <= lastCellNum; j++) {
                    cells[j] = row.getCell(j).getStringCellValue();
                }
                list.add(cells);
            }
        } catch (Exception e) {

        }
    }
}*/
