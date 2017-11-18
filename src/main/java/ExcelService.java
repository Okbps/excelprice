import java.awt.Color;
import java.io.*;
import java.net.URL;

import model.HrefDescriptor;
import model.PriceDescriptor;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.Document;

class ExcelService {
    void fillHrefs(PriceDescriptor pd) throws IOException {
        if(pd.getHrefs().isEmpty()){
            return;
        }

        FileInputStream input = new FileInputStream(new File(pd.getFileName()));
        XSSFWorkbook workbook = new XSSFWorkbook(input);

        XSSFSheet sheet = workbook.getSheetAt(0);
        CreationHelper helper = workbook.getCreationHelper();
        XSSFCell cell;

        XSSFCellStyle hlinkstyle = null;

        for(HrefDescriptor hd: pd.getHrefs()) {
            if(hd.getHref()!=null) {
                cell = sheet.getRow(hd.getTop() - 1).getCell(pd.getCodeCol() - 1);

                String stringFormat = cell.getCellStyle().getDataFormatString();
                CellNumberFormatter fmt = new CellNumberFormatter(stringFormat);
                String code = fmt.format(cell.getNumericCellValue());

                cell.setCellType(CellType.STRING);
                cell.setCellValue(code);
                XSSFHyperlink link = (XSSFHyperlink) helper.createHyperlink(HyperlinkType.URL);
                link.setAddress(hd.getHref());
                cell.setHyperlink(link);

                if(hlinkstyle==null) {
                    hlinkstyle = (XSSFCellStyle) cell.getCellStyle().clone();
                    XSSFFont hlinkfont = workbook.createFont();
                    hlinkfont.setUnderline(XSSFFont.U_SINGLE);
                    hlinkfont.setColor(new XSSFColor(Color.BLUE));
                    hlinkfont.setFontHeight(hlinkstyle.getFont().getFontHeight());
                    hlinkfont.setFontName(hlinkstyle.getFont().getFontName());
                    hlinkstyle.setFont(hlinkfont);
                }

                cell.setCellStyle(hlinkstyle);
            }

            if(hd.getImgHref()!=null){
                InputStream imgInput = new URL(hd.getImgHref()).openStream();
                int imgInd = workbook.addPicture(imgInput, Document.PICTURE_TYPE_PNG);
                imgInput.close();

                Drawing drawing = sheet.createDrawingPatriarch();

                ClientAnchor anchor = helper.createClientAnchor();
                anchor.setCol1(pd.getImgCol()-1);
                anchor.setCol2(pd.getImgCol()-1);
                anchor.setRow1(hd.getTop()-1);
                anchor.setRow2(hd.getTop()-1);

                Picture pic = drawing.createPicture(anchor, imgInd);
                pic.resize(1, 1);
            }
        }

        FileOutputStream output = new FileOutputStream(pd.getFileName());
        workbook.write(output);
        output.close();
        workbook.close();
        input.close();
    }
}
