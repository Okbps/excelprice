package model;

import java.util.List;

public class PriceDescriptor {
    private String fileName;
    private List<HrefDescriptor> hrefs;
    private short imgCol;
    private short codeCol;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<HrefDescriptor> getHrefs() {
        return hrefs;
    }

    public void setHrefs(List<HrefDescriptor> hrefs) {
        this.hrefs = hrefs;
    }

    public short getImgCol() {
        return imgCol;
    }

    public void setImgCol(short imgCol) {
        this.imgCol = imgCol;
    }

    public short getCodeCol() {
        return codeCol;
    }

    public void setCodeCol(short codeCol) {
        this.codeCol = codeCol;
    }
}
