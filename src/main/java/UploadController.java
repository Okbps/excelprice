
import model.PriceDescriptor;
import ut.FileUtil;
import ut.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadController extends HttpServlet {
    private ExcelService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isDescription = req.getParameter("description") != null;
        boolean isFile = req.getParameter("file") != null;

        if(isDescription) {
            InputStream in = req.getInputStream();
            PriceDescriptor pd = JsonUtil.readJson(in, PriceDescriptor.class);
            in.close();

            service = new ExcelService();
            service.fillHrefs(pd);
        }

        if(isFile){
            InputStream is = req.getInputStream();
            String fileName = FileUtil.getResourcePath("uploaded/1.xslx");
            FileUtil.saveFile(is, fileName);
            is.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("path")!=null) {
            OutputStream out = resp.getOutputStream();
            String absPath = UploadController.class.getClassLoader().getResource("").getPath();
            out.write(absPath.getBytes());
            out.close();
        }
    }
}
