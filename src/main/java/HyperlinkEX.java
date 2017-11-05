import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.*;

public class HyperlinkEX {
    public static void main(String[] args) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Hyperlinks");
        XSSFCell cell;
        CreationHelper createHelper = workbook.getCreationHelper();
        XSSFCellStyle hlinkstyle = workbook.createCellStyle();
        XSSFFont hlinkfont = workbook.createFont();
        hlinkfont.setUnderline(XSSFFont.U_SINGLE);
        hlinkfont.setColor(new XSSFColor(Color.BLUE));

        hlinkstyle.setFont(hlinkfont);

        //URL Link
        cell = spreadsheet.createRow(1).createCell((short)1);
        cell.setCellValue("URL Link");
        XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress("http://www.tutorialspoint.com/" );
        cell.setHyperlink((XSSFHyperlink) link);
        cell.setCellStyle(hlinkstyle);

        FileOutputStream out = new FileOutputStream(new File("hyperlink.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("hyperlink.xlsx written successfully");
    }
}
