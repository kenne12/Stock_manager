package filter;

import entities.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "FilterPrivellege", urlPatterns = {"/parametre/*", "/securite/*", "/operation/*", "/analyse/*"})
public class FilterPrivellege
        implements Filter, Serializable {

    Properties properties;
    private static final boolean debug = true;
    /*  35 */    Utilisateur utilisateur = new Utilisateur();

    /*  40 */    private FilterConfig filterConfig = null;

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        /*  48 */ log("FilterPrivellege:DoBeforeProcessing");
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        /*  55 */ log("FilterPrivellege:DoAfterProcessing");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*  71 */ HttpServletRequest hRequest = (HttpServletRequest) request;
        /*  72 */ HttpServletResponse hResponse = (HttpServletResponse) response;
        /*  73 */ HttpSession session = hRequest.getSession();

        /*  76 */ if (session.getAttribute("compte") != null) {

            /*  83 */ List<String> allAccesses = (List<String>) session.getAttribute("allAccess");

            /*  85 */ if (allAccesses.contains(hRequest.getRequestURI())) {
                /*  86 */ List<Long> privileges = (List<Long>) session.getAttribute("accesses");

                /*  88 */ if (!privileges.isEmpty()) {
                    /*  89 */ boolean drapeau = false;

                    /*  91 */ if (privileges.contains(Long.valueOf(1L))) {
                        /*  92 */ drapeau = true;
                    }

                    /*  95 */ if (drapeau) {
                        /*  96 */ chain.doFilter(request, response);
                    } else {
                        /*  98 */ List<String> access = (List<String>) session.getAttribute("access");
                        /*  99 */ if (access.contains(hRequest.getRequestURI())) {
                            /* 100 */ chain.doFilter(request, response);
                        } else {
                            /* 102 */ request.getRequestDispatcher("/erreuracces.html?faces-redirect=true").forward(request, response);
                        }
                    }
                } else {
                    /* 106 */ request.getRequestDispatcher("/erreuracces.html?faces-redirect=true").forward(request, response);
                }
            } else {
                /* 109 */ chain.doFilter(request, response);
            }
        } else {
            /* 112 */ request.getRequestDispatcher("/login.html?faces-redirect=true").forward(request, response);
        }
    }

    public FilterConfig getFilterConfig() {
        /* 120 */ return this.filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        /* 129 */ this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        /* 146 */ this.filterConfig = filterConfig;
        /* 147 */ if (filterConfig != null) {
            /* 149 */ log("FilterPrivellege:Initializing filter");
        }
    }

    public String toString() {
        /* 159 */ if (this.filterConfig == null) {
            /* 160 */ return "FilterPrivellege()";
        }
        /* 162 */ StringBuilder sb = new StringBuilder("FilterPrivellege(");
        /* 163 */ sb.append(this.filterConfig);
        /* 164 */ sb.append(")");
        /* 165 */ return sb.toString();
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
    }

    public static String getStackTrace(Throwable t) {
        /* 173 */ String stackTrace = null;
        try {
            /* 175 */ StringWriter sw = new StringWriter();
            /* 176 */ PrintWriter pw = new PrintWriter(sw);
            /* 177 */ t.printStackTrace(pw);
            /* 178 */ pw.close();
            /* 179 */ sw.close();
            /* 180 */ stackTrace = sw.getBuffer().toString();
            /* 181 */        } catch (Exception exception) {
        }

        /* 183 */ return stackTrace;
    }

    public void log(String msg) {
        /* 187 */ this.filterConfig.getServletContext().log(msg);
    }

    public String getPropertyValue(String key) {
        try {
            /* 193 */ if (key == null) {
                /* 194 */ System.out.println("=============== key null  ++++++++++++++++++++");
            }
            /* 196 */ if (key == "") {
                /* 197 */ System.out.println("=============== key empty  ++++++++++++++++++++");
            }
            /* 199 */ if (this.properties == null) {
                /* 200 */ System.out.println("=============== properties empty  ++++++++++++++++++++");
            }
            /* 202 */ String propValue = this.properties.getProperty(key);

            /* 204 */ System.out.println("key is: " + key);
            /* 205 */ System.out.println("Property value is: " + propValue);
            /* 206 */ return propValue;
            /* 207 */        } catch (Exception ex) {
            /* 208 */ return null;
        }
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\filter\FilterPrivellege.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
