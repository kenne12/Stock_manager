package utils;

import entities.Lot;
import entities.Mouchard;
import entities.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sessions.MouchardFacadeLocal;

public class Utilitaires {

    private static final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    public static final String path = servletContext.getRealPath("");
    public static final String repertoireParDefaultNotesTrim = "rapport/notes/trimestriel";
    public static final String chemin = servletContext.getContextPath();

    public static void saveOperation(MouchardFacadeLocal mouchardFacadeLocal, String action, Utilisateur utilisateur) {
        try {
            Mouchard traceur = new Mouchard();
            traceur.setIdmouchard(mouchardFacadeLocal.nextVal());
            traceur.setAction(action);
            traceur.setIdutilisateur(utilisateur);
            traceur.setDate(new Date());
            traceur.setHeure(new Date());
            mouchardFacadeLocal.create(traceur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getExtension(String nomFichier) {
        int taille = nomFichier.length();
        String extension = "";
        for (int i = 0; i < taille; i++) {
            if (nomFichier.charAt(i) == '.') {
                extension = "";
            } else {
                extension = extension + nomFichier.charAt(i);
            }
        }
        return extension;
    }

    public static boolean estExtensionImage(String extension) {
        if (extension == null || extension.equals("")) {
            return false;
        }
        String ext = extension.toUpperCase();
        if (ext.equals("JPG")) {
            return true;
        }
        if (ext.equals("JPEG")) {
            return true;
        }
        if (ext.equals("GIF")) {
            return true;
        }
        if (ext.equals("PNG")) {
            return true;
        }
        if (ext.equals("BMP")) {
            return true;
        }
        return false;
    }

    public static boolean estFichierImage(String nom) {
        String extension = getExtension(nom);
        if (extension == null || extension.equals("")) {
            return false;
        }
        String ext = extension.toUpperCase();
        if (ext.equals("JPG")) {
            return true;
        }
        if (ext.equals("JPEG")) {
            return true;
        }
        if (ext.equals("GIF")) {
            return true;
        }
        if (ext.equals("PNG")) {
            return true;
        }
        if (ext.equals("BMP")) {
            return true;
        }
        return false;
    }

    public static boolean handleFileUpload(FileUploadEvent event, String absoluteSavePath) {
        try {
            OutputStream saveFile = new FileOutputStream(new File(absoluteSavePath));
            InputStream in = event.getFile().getInputstream();
            byte[] buff = new byte[8];
            int n;
            while ((n = in.read(buff)) >= 0) {
                saveFile.write(buff);
                buff = new byte[8];
            }
        } catch (IOException ex) {
            FacesMessage message = new FacesMessage("Error", "Error While uploading " + event.getFile().getFileName());
            FacesContext.getCurrentInstance().addMessage(null, message);
            Logger.getLogger(utils.Utilitaires.class.getName()).log(Level.SEVERE, (String) null, ex);
            return false;
        }
        return true;
    }

    public static boolean handleFileUpload(UploadedFile file, String absoluteSavePath) {
        try {
            OutputStream saveFile = new FileOutputStream(new File(absoluteSavePath));
            InputStream in = file.getInputstream();
            byte[] buff = new byte[8];
            int n;
            while ((n = in.read(buff)) >= 0) {
                saveFile.write(buff);
                buff = new byte[8];
            }
        } catch (IOException ex) {
            FacesMessage message = new FacesMessage("Error", "Error While uploading " + file.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, message);
            Logger.getLogger(utils.Utilitaires.class.getName()).log(Level.SEVERE, (String) null, ex);
            return false;
        }
        return true;
    }

    public static boolean CopierFichier(File Source, File Destination) {
        boolean resultat = false;

        FileInputStream filesource = null;
        FileOutputStream fileDestination = null;
        try {
            filesource = new FileInputStream(Source);
            fileDestination = new FileOutputStream(Destination);
            byte[] buffer = new byte[1000];
            int nblecture;
            while ((nblecture = filesource.read(buffer)) != -1) {
                fileDestination.write(buffer, 0, nblecture);
                buffer = new byte[8];
            }
            resultat = true;
        } catch (FileNotFoundException nf) {
            nf.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            try {
                filesource.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fileDestination.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultat;
    }

    public static Double arrondiNDecimales(double x, int n) {
        double pow = Math.pow(10.0D, n);
        return Math.floor(x * pow) / pow;
    }

    public static String formatPrenomMaj(String prenom) {
        if (prenom.isEmpty()) {
            return " ";
        }
        char prenLettre = '0';

        prenLettre = prenom.charAt(0);

        String leReste = prenom.substring(1, prenom.length());

        String lettre1 = String.valueOf(prenLettre);

        leReste = leReste.toLowerCase();

        return lettre1.toUpperCase() + leReste;
    }

    public static boolean isAccess(Long menu) {
        if (SessionMBean.getAccess().isEmpty()) {
            return false;
        }
        if (SessionMBean.getAccess().contains(1L)) {
            return true;
        }
        if (SessionMBean.getAccess().contains(menu)) {
            return true;
        }
        return false;
    }

    public static boolean isDayClosed() {
        return SessionMBean.getDay().getFermetture();
    }

    public static void permuteDate(Date date1, Date date2) {
    }

    public static boolean checkBenefice(double prix_achat, double prix_vente) {
        if (prix_vente >= prix_achat) {
            return true;
        }
        return false;
    }

    public static String genererCodeFacture(String code, Long nextPayement) {
        final String pattern = StringUtils.leftPad(nextPayement.toString(), 7, '0');
        return code + "-" + pattern;
    }

    public static String genererCodeStock(String code, Long nextPayement) {
        final String pattern = StringUtils.leftPad(nextPayement.toString(), 4, '0');
        return code + "-" + pattern;
    }

    public static String genererCodeInventaire(String code, Long nextPayement) {
        final String pattern = StringUtils.leftPad(nextPayement.toString(), 3, '0');
        return code + "-" + pattern;
    }

    public static Integer duration(Date date1, Date date2) {
        DateTime dateDebut = new DateTime("" + (date1.getYear() + 1900) + "-" + (date1.getMonth() + 1) + "-" + date1.getDate() + "");
        DateTime dateFin = new DateTime("" + (date2.getYear() + 1900) + "-" + (date2.getMonth() + 1) + "-" + date2.getDate() + "");

        Integer nbjr = Days.daysBetween(dateDebut, dateFin).getDays();
        return nbjr;
    }

    public static List<Lot> filterNotPeremptLot(List<Lot> lots) {
        List<Lot> resultat = new ArrayList<>();
        try {
            for (Lot l : lots) {
                try {
                    if (l.getDatePeremption() != null) {
                        if ((new Date()).before(l.getDatePeremption())) {
                            resultat.add(l);
                        }
                        continue;
                    }
                    resultat.add(l);
                } catch (Exception e) {
                    resultat.add(l);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }
}
