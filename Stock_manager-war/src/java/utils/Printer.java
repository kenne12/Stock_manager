package utils;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class Printer {
    /* 27 */ private static ResourceBundle rs = ResourceBundle.getBundle("langues/langue", FacesContext.getCurrentInstance().getViewRoot().getLocale());
    private static JasperPrint jasperPrint;
    /* 29 */    private static String user = "postgres";
    /* 30 */    private static String password = "admin";
    /* 31 */    public static String driver = "org.postgresql.Driver";
    /* 32 */    private static String url = "jdbc:postgresql://localhost:5432/stock_db_frps";

    public static Connection con;

    public static void print(String path, Map<String, Locale> parameters) throws Exception {
        try {
            /* 51 */ Class.forName(driver);
            /* 52 */ con = DriverManager.getConnection(url, user, password);
            /* 53 */ String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);

            parameters.put("REPORT_LOCALE", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            /*parameters.put("REPORT_RESOURCE_BUNDLE", rs);
            jasperPrint = JasperFillManager.fillReport(reportPath, parameters, con);*/

            /* 59 */ HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            /* 60 */ httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + path.substring(path.lastIndexOf("/"), path.lastIndexOf(".")) + ".pdf");
            /* 61 */ ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            /* 62 */ JasperExportManager.exportReportToPdfStream(jasperPrint, (OutputStream) servletOutputStream);

            /* 64 */ FacesContext.getCurrentInstance().responseComplete();
            /* 65 */        } catch (IOException e) {
            /* 66 */ throw new IOException(e);
            /* 67 */        } catch (JRException e) {
            /* 68 */ throw new JRException(e);
        }
    }
}
