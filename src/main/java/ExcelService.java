import java.awt.*;
import java.io.*;

import model.HrefDescriptor;
import model.PriceDescriptor;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.*;

public class ExcelService {
    public static void main(String[] args) throws Exception {
        System.out.println("hyperlink.xlsx written successfully");
    }

    void fillHrefs(PriceDescriptor pd) throws IOException {
        FileInputStream in = new FileInputStream(new File(pd.getFileName()));
        XSSFWorkbook workbook = new XSSFWorkbook(in);

        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        CreationHelper createHelper = workbook.getCreationHelper();
        XSSFCell cell;

        XSSFCellStyle hlinkstyle = workbook.createCellStyle();
        XSSFFont hlinkfont = workbook.createFont();
        hlinkfont.setUnderline(XSSFFont.U_SINGLE);
        hlinkfont.setColor(new XSSFColor(Color.BLUE));

        hlinkstyle.setFont(hlinkfont);

        for(HrefDescriptor hd: pd.getHrefs()) {
            cell = spreadsheet.getRow(hd.getTop()-1).getCell(hd.getLeft()-1);
            XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);
            link.setAddress(hd.getHref());
            cell.setHyperlink(link);
            cell.setCellStyle(hlinkstyle);
        }

        FileOutputStream out = new FileOutputStream(pd.getFileName());
        workbook.write(out);
        out.close();
        workbook.close();
        in.close();
    }
}
