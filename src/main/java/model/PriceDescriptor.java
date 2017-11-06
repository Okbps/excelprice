package model;

import java.util.List;

public class PriceDescriptor {
    private String fileName;
    private List<HrefDescriptor> hrefs;

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
}
