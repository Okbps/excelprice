
import model.PriceDescriptor;
import ut.FileUtil;
import ut.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
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
            pd.setFileName(getFullNameByRelative(pd.getFileName()));
            service.fillHrefs(pd);
        }

        if(isFile){
            String relativeName = FileUtil.getRandomName("/upload/", req.getParameter("ext"));

            InputStream is = req.getInputStream();
            FileUtil.saveFile(is, getFullNameByRelative(relativeName));
            is.close();

            OutputStream out = resp.getOutputStream();
            out.write(relativeName.getBytes());
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isFile = req.getParameter("file") != null;

        if(isFile) {
            String fullName = getFullNameByRelative(req.getParameter("name"));
            OutputStream out = resp.getOutputStream();
            resp.setContentType(getServletContext().getMimeType(fullName));
            long fileSize = FileUtil.copy(new FileInputStream(fullName), out);
            resp.setContentLength((int) fileSize);

            out.flush();
            out.close();
        }
    }

    private String getFullNameByRelative(String relativeName){
        return getServletContext().getRealPath(relativeName);
    }
}
