package utils;

import entities.AnneeMois;
import entities.Journee;
import entities.Parametrage;
import entities.Utilisateur;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionMBean {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("login").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("utilisateurid");
        }
        return null;
    }

    public static Utilisateur getUserAccount() {
        HttpSession session = getSession();
        if (session != null) {
            return (Utilisateur) session.getAttribute("compte");
        }
        return null;
    }

    public static Boolean getSession1() {
        HttpSession session = getSession();
        if (session != null) {
            return (Boolean) session.getAttribute("session");
        }
        return false;
    }

    public static Parametrage getParametrage() {
        HttpSession session = getSession();
        if (session != null) {
            return (Parametrage) session.getAttribute("parametre");
        }
        return null;
    }

    public static List<Long> getAccess() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Long>) session.getAttribute("accesses");
        }
        return null;
    }

    public static Date getDateOuverture() {
        HttpSession session = getSession();
        if (session != null) {
            return (Date) session.getAttribute("date");
        }
        return null;
    }

    public static AnneeMois getMois() {
        HttpSession session = getSession();
        if (session != null) {
            return (AnneeMois) session.getAttribute("mois");
        }
        return null;
    }

    public static Journee getDay() {
        HttpSession session = getSession();
        if (session != null) {
            return (Journee) session.getAttribute("journee");
        }
        return null;
    }
}
