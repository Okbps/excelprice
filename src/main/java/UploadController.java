import model.PriceDescriptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class UploadController extends HttpServlet {
    private ExcelService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream in = req.getInputStream();
        PriceDescriptor pd = JsonUtil.readJson(in, PriceDescriptor.class);
        in.close();

        service = new ExcelService();
        service.fillHrefs(pd);
    }
}
