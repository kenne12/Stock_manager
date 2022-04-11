package utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Annee;
import entities.AnneeMois;
import entities.Client;
import entities.Commande;
import entities.CommandeClient;
import entities.Depense;
import entities.Facture;
import entities.Famille;
import entities.Inventaire;
import entities.Journee;
import entities.LigneCommandeClient;
import entities.LigneMvtStock;
import entities.Ligneinventaire;
import entities.Parametrage;
import entities.Produit;
import entities.Salaire;
import entities.Stock;
import entities.StockProduit;
import entities.Versement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import sessions.CommandeFacadeLocal;
import sessions.StockProduitFacadeLocal;

public class PrintUtils {

    @EJB
    private static CommandeFacadeLocal commandeFacadeLocal;

    @EJB
    private static StockProduitFacadeLocal stockProduitFacadeLocal;

    public static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18.0F, 1);
    public static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0, BaseColor.RED);
    public static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0, BaseColor.BLACK);
    public static Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0, BaseColor.BLUE);
    public static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16.0F, 1);
    public static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 1);

    public static Font setUpFont(float size, int style, BaseColor color) {
        Font font = new Font();
        font.setStyle(style);
        font.setSize(size);
        font.setColor(color);
        return font;
    }

    public static PdfPCell createPdfPCell(String sCell, int colspan, int position, Font font) {
        PdfPCell cell = new PdfPCell((Phrase) new Paragraph(sCell, font));
        cell.setColspan(colspan);
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int colspan, int position) {
        PdfPCell cell = new PdfPCell((Phrase) new Paragraph(sCell));
        cell.setColspan(colspan);
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position, Font font) {
        PdfPCell cell = new PdfPCell((Phrase) new Paragraph(sCell, font));
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position, Font font, int borderLeft, int borderRight, int borderTop, int borderBottom) {
        PdfPCell cell = new PdfPCell((Phrase) new Paragraph(sCell, font));
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        cell.setBorderWidthLeft(borderLeft);
        cell.setBorderWidthRight(borderRight);
        cell.setBorderWidthTop(borderTop);
        cell.setBorderWidthBottom(borderBottom);
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position, int colspan, Font font, int borderLeft, int borderRight, int borderTop, int borderBottom) {
        /*  123 */ PdfPCell cell = new PdfPCell((Phrase) new Paragraph(sCell, font));
        /*  124 */ cell.setColspan(colspan);
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        /*  132 */ cell.setBorderWidthLeft(borderLeft);
        /*  133 */ cell.setBorderWidthRight(borderRight);
        /*  134 */ cell.setBorderWidthTop(borderTop);
        /*  135 */ cell.setBorderWidthBottom(borderBottom);
        /*  136 */ return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position, int colspan, int rowspan, Font font, int borderLeft, int borderRight, int borderTop, int borderBottom) {
        PdfPCell cell = new PdfPCell((Phrase) new Paragraph(sCell, font));
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        cell.setBorderWidthLeft(borderLeft);
        cell.setBorderWidthRight(borderRight);
        cell.setBorderWidthTop(borderTop);
        cell.setBorderWidthBottom(borderBottom);
        return cell;
    }

    public static PdfPCell createPdfPCell(String sCell, int position) {
        PdfPCell cell = new PdfPCell((Phrase) new Paragraph(sCell));
        if (position == 1) {
            cell.setHorizontalAlignment(0);
        } else if (position == 2) {
            cell.setHorizontalAlignment(1);
        } else {
            cell.setHorizontalAlignment(2);
        }
        return cell;
    }

    public static String printWeeklyReport(Date datedebut, Date datefin, List<Solde> soldes) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Rapport_periode_du_" + sdf.format(datedebut) + "_au_" + sdf.format(datefin) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/hebdomadaire/" + fileName));
            rapport.open();
            float[] widths = {0.6F, 3.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            createEntete(rapport, SessionMBean.getParametrage());

            rapport.add((Element) new Paragraph(" "));

            Paragraph titre = new Paragraph("RAPPORT PERIODIQUE D'ACTIVITES [VERSEMENTS / RETRAITS]", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            /*  186 */ titre.setAlignment(1);
            /*  187 */ rapport.add((Element) titre);

            /*  189 */ Paragraph periode = new Paragraph("Période du  " + sdf.format(datedebut) + " au " + sdf.format(datefin), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 6));
            /*  190 */ periode.setAlignment(1);
            /*  191 */ rapport.add((Element) periode);

            /*  193 */ rapport.add((Element) new Paragraph(" "));

            /*  195 */ table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  196 */ table.addCell(createPdfPCell("Nom(s) et prénom(s) du client", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  197 */ table.addCell(createPdfPCell("Montant versé", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  198 */ table.addCell(createPdfPCell("Montant retiré", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  199 */ table.addCell(createPdfPCell("Commissions", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  200 */ table.addCell(createPdfPCell("Frais carnet", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  201 */ table.addCell(createPdfPCell("Solde", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));

            /*  203 */ int sommeVerse = 0;
            /*  204 */ int sommeRetire = 0;
            /*  205 */ int sommeCarnet = 0;
            /*  206 */ int sommeCommission = 0;
            /*  207 */ int solde = 0;
            for (Solde s : soldes) {
                sommeVerse += s.getMontantVerse();
                /*  210 */ sommeRetire += s.getMontantRetire();
                /*  211 */ sommeCommission += s.getCommission();
                /*  212 */ sommeCarnet += s.getCarnet();
                /*  213 */ solde += s.getClient().getSolde();

                /*  215 */ table.addCell(createPdfPCell("" + s.getClient().getNom() + " " + s.getClient().getPrenom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  216 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getMontantVerse()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  217 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getMontantRetire()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  218 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getCommission()) + "", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  219 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getCarnet()) + "", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  220 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getClient().getSolde()) + "", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            }

            /*  223 */ table.addCell(createPdfPCell("Totaux", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  224 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(sommeVerse)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            /*  225 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(sommeRetire)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            /*  226 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(sommeCommission)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            /*  227 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(sommeCarnet)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            /*  228 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(solde)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));

            /*  230 */ rapport.add((Element) table);
            /*  231 */ rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printDailylyReport(Date date, List<Solde> soldes) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Rapport_du_" + sdf.format(date) + ".pdf";
            /*  246 */ Document rapport = new Document();
            /*  247 */ PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/journalier/" + fileName));
            /*  248 */ rapport.open();
            /*  249 */ float[] widths = {0.6F, 3.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
            /*  250 */ PdfPTable table = new PdfPTable(widths);
            /*  251 */ table.setWidthPercentage(100.0F);

            /*  253 */ createEntete(rapport, SessionMBean.getParametrage());

            /*  255 */ rapport.add((Element) new Paragraph(" "));

            /*  257 */ Paragraph titre = new Paragraph("RAPPORT JOURNALIER D'ACTIVITES [VERSEMENTS / RETRAITS]", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            /*  258 */ titre.setAlignment(1);
            /*  259 */ rapport.add((Element) titre);

            /*  261 */ Paragraph periode = new Paragraph("Date : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 6));
            /*  262 */ periode.setAlignment(1);
            /*  263 */ rapport.add((Element) periode);

            /*  265 */ rapport.add((Element) new Paragraph(" "));

            /*  267 */ table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  268 */ table.addCell(createPdfPCell("Nom(s) et prénom(s) du client", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  269 */ table.addCell(createPdfPCell("Montant versé", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  270 */ table.addCell(createPdfPCell("Montant retiré", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  271 */ table.addCell(createPdfPCell("Commissions", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  272 */ table.addCell(createPdfPCell("Frais carnet", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  273 */ table.addCell(createPdfPCell("Solde", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));

            /*  275 */ int sommeVerse = 0;
            /*  276 */ int sommeRetire = 0;
            /*  277 */ int sommeCarnet = 0;
            /*  278 */ int sommeCommission = 0;
            /*  279 */ int solde = 0;
            for (Solde s : soldes) {
                sommeVerse += s.getMontantVerse();
                sommeRetire += s.getMontantRetire();
                sommeCommission += s.getCommission();
                sommeCarnet += s.getCarnet();
                solde += s.getClient().getSolde();

                table.addCell(createPdfPCell("" + s.getClient().getNom() + " " + s.getClient().getPrenom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getMontantVerse()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getMontantRetire()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getCommission()) + "", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getCarnet()) + "", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getClient().getSolde()) + "", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            }

            table.addCell(createPdfPCell("Totaux", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(sommeVerse)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(sommeRetire)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(sommeCommission)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(sommeCarnet)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Integer.valueOf(solde)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));

            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printDailySaleReport(Date date, List<Commande> commandes) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Rapport_du_" + sdf.format(date) + ".pdf";
            /*  318 */ Document rapport = new Document();
            /*  319 */ PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/journalier/" + fileName));
            /*  320 */ rapport.open();
            /*  321 */ float[] widths = {0.7F, 3.5F, 1.1F, 0.7F, 1.7F};
            /*  322 */ PdfPTable table = new PdfPTable(widths);
            /*  323 */ table.setWidthPercentage(100.0F);

            /*  325 */ createEntete(rapport, SessionMBean.getParametrage());

            /*  327 */ rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 2)));

            /*  329 */ Paragraph titre = new Paragraph("RAPPORT JOURNALIER DES VENTES", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            /*  330 */ titre.setAlignment(1);
            /*  331 */ rapport.add((Element) titre);

            /*  333 */ Paragraph periode = new Paragraph("Imprimé le : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /*  334 */ periode.setAlignment(1);
            /*  335 */ rapport.add((Element) periode);

            /*  337 */ rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 2)));

            /*  339 */ table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  340 */ table.addCell(createPdfPCell("Produit", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  341 */ table.addCell(createPdfPCell("Prix", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  342 */ table.addCell(createPdfPCell("Quantité", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  343 */ table.addCell(createPdfPCell("Total", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));

            /*  345 */ Double total = (0.0D);
            /*  346 */ int quantite = 0;
            /*  347 */ int compteur = 1;

            for (Commande c : commandes) {
                /*  350 */ quantite += (c.getQuantite().intValue());
                /*  351 */ total += c.getMontant() * c.getQuantite();
                /*  352 */ table.addCell(createPdfPCell("" + compteur, 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  353 */ table.addCell(createPdfPCell("" + c.getIdproduit().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  354 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(c.getMontant()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  355 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(c.getQuantite()), 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Double.valueOf(c.getMontant() * c.getQuantite())) + "", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                compteur++;
            }

            table.addCell(createPdfPCell("Totaux", 3, 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((quantite)), 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(total), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));

            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printProductReport(List<Produit> produits) {
        String fileName = "";
        try {
            Date date = new Date();
            /*  378 */ SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            /*  379 */ fileName = "Fiche_produits_" + sdf.format(date) + ".pdf";
            /*  380 */ Document rapport = new Document();
            /*  381 */ PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/produits/" + fileName));
            /*  382 */ rapport.open();
            /*  383 */ float[] widths = {0.7F, 1.9F, 3.9F, 1.0F};
            /*  384 */ PdfPTable table = new PdfPTable(widths);
            /*  385 */ table.setWidthPercentage(100.0F);

            /*  387 */ createEntete(rapport, SessionMBean.getParametrage());

            /*  389 */ rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 2)));

            /*  391 */ Paragraph titre = new Paragraph("FICHE DESCRIPTIVE DES PRODUITS", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            /*  392 */ titre.setAlignment(1);
            /*  393 */ rapport.add((Element) titre);

            /*  395 */ Paragraph periode = new Paragraph("Imprimé le : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /*  396 */ periode.setAlignment(1);
            /*  397 */ rapport.add((Element) periode);

            /*  399 */ rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 6.0F, 2)));

            /*  401 */ table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  402 */ table.addCell(createPdfPCell("FAMILLE", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  403 */ table.addCell(createPdfPCell("Produit", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /*  404 */ table.addCell(createPdfPCell("Prix", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));

            /*  406 */ int compteur = 1;

            /*  408 */ for (Produit p : produits) {
                /*  409 */ table.addCell(createPdfPCell("" + compteur, 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  410 */ table.addCell(createPdfPCell("" + p.getIdfamille().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  411 */ table.addCell(createPdfPCell("" + p.getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  412 */ table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(p.getPrixMaximal()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                /*  413 */ compteur++;
            }

            /*  416 */ rapport.add((Element) table);
            /*  417 */ rapport.close();
        } /*  419 */ catch (DocumentException ex) {
            /*  420 */ Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
            /*  421 */        } catch (FileNotFoundException ex) {
            /*  422 */ Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        /*  424 */ return fileName;
    }

    public static String printGeneralStockReport(List<Produit> produits) {
        String fileName = "";
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Fiche_stock_general" + sdf.format(date) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/stock/" + fileName));
            rapport.open();
            float[] widths = {0.5F, 1.5F, 2.9F, 1.0F, 0.8F, 1.2F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            createEntete(rapport, SessionMBean.getParametrage());

            rapport.add((Element) new Paragraph(" "));

            Paragraph titre = new Paragraph("FICHE DU STOCK EN MARCHANDISE", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph periode = new Paragraph("Imprimé le : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            periode.setAlignment(1);
            rapport.add((Element) periode);

            rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Famille", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Produit", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Prix", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Qté", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Total", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            Double total = 0.0D;
            int quantite = 0;
            int compteur = 1;

            for (Produit p : produits) {
                quantite += (p.getQuantite().intValue());
                total += (p.getPrixMaximal() * p.getQuantite());
                table.addCell(createPdfPCell("" + compteur, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + p.getIdfamille().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + p.getNom(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(p.getPrixMaximal()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(p.getQuantite()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Long.valueOf(Math.round(p.getQuantite().doubleValue() * p.getPrixMaximal().doubleValue()))), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                compteur++;
            }

            table.addCell(createPdfPCell("Totaux", 4, 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((quantite)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((total.intValue())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printRecette(AnneeMois anneeMois, List<Journee> journees) {
        String fileName = "";
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Recette_" + anneeMois.getIdmois().getNom() + "_" + anneeMois.getIdannee().getNom() + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/stock/" + fileName));
            rapport.open();
            float[] widths = {1.5F, 3.0F, 2.7f, 2.7F, 2.5F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(75.0F);

            createEntete(rapport, SessionMBean.getParametrage());

            rapport.add((Element) new Paragraph(" "));

            Paragraph titre = new Paragraph("RECETTE DE " + anneeMois.getIdmois().getNom() + "_" + anneeMois.getIdannee().getNom(), new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph periode = new Paragraph("Imprimé le : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            periode.setAlignment(1);
            rapport.add((Element) periode);

            rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Date", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Montant\nEntré", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Montant\nSorti", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Bord", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            int compteur = 1;

            Double sommeSortie = 0.0D;
            Double bord = 0.0D;
            Double sommeEntree = 0.0D;
            for (Journee j : journees) {
                sommeSortie += j.getMontant();
                bord += j.getBord();
                sommeEntree += j.getMontantEntree();

                table.addCell(createPdfPCell("" + compteur, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + sdf.format(j.getDateJour()), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(((int) j.getMontantEntree())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((j.getMontant().intValue())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((j.getBord().intValue())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                compteur++;
            }

            table.addCell(createPdfPCell("Totaux", 2, 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney((sommeEntree.intValue())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney((sommeSortie.intValue())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney((bord.intValue())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printCritickStockReport(List<Produit> produits) {
        String fileName = "";
        try {
            /*  556 */ Date date = new Date();
            /*  557 */ SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            /*  558 */ fileName = "Fiche_stock_critic" + sdf.format(date) + ".pdf";
            /*  559 */ Document rapport = new Document();
            /*  560 */ PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/stock/" + fileName));
            /*  561 */ rapport.open();
            /*  562 */ float[] widths = {0.5F, 1.5F, 2.9F, 1.0F, 0.8F, 1.2F};
            /*  563 */ PdfPTable table = new PdfPTable(widths);
            /*  564 */ table.setWidthPercentage(100.0F);

            /*  566 */ createEntete(rapport, SessionMBean.getParametrage());

            /*  568 */ rapport.add((Element) new Paragraph(" "));

            /*  570 */ Paragraph titre = new Paragraph("FICHE DES PRODUITS EN SOUS-STOCKAGE", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            /*  571 */ titre.setAlignment(1);
            /*  572 */ rapport.add((Element) titre);

            /*  574 */ Paragraph periode = new Paragraph("Imprimé le : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /*  575 */ periode.setAlignment(1);
            /*  576 */ rapport.add((Element) periode);

            /*  578 */ rapport.add((Element) new Paragraph(" "));

            /*  580 */ table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            /*  581 */ table.addCell(createPdfPCell("FAMILLE", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            /*  582 */ table.addCell(createPdfPCell("Produit", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            /*  583 */ table.addCell(createPdfPCell("Prix", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            /*  584 */ table.addCell(createPdfPCell("Qté", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            /*  585 */ table.addCell(createPdfPCell("Total", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            Double total = (0.0D);
            int quantite = 0;
            int compteur = 1;

            for (Produit p : produits) {
                if (p.getQuantite() <= p.getStockCritique()) {
                    quantite += (p.getQuantite().intValue());
                    total += (p.getPrixMaximal() * p.getQuantite());
                    table.addCell(createPdfPCell("" + compteur, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    table.addCell(createPdfPCell("" + p.getIdfamille().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    table.addCell(createPdfPCell("" + p.getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(p.getPrixMaximal()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(p.getQuantite()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Long.valueOf(Math.round(p.getQuantite().doubleValue() * p.getPrixMaximal().doubleValue()))), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    compteur++;
                }
            }

            if (!produits.isEmpty()) {
                table.addCell(createPdfPCell("Totaux", 4, 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((quantite)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(total), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            }
            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printWeeklySaleReport(AnneeMois anneeMois, List<Commande> commandes) {
        String fileName = "";
        try {
            /*  624 */ Date date = new Date();
            /*  625 */ SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            /*  626 */ fileName = "Rapport_de_" + anneeMois.getIdmois().getNom() + "_" + anneeMois.getIdannee().getNom() + ".pdf";
            /*  627 */ Document rapport = new Document();
            /*  628 */ PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/hebdomadaire/" + fileName));
            /*  629 */ rapport.open();
            /*  630 */ float[] widths = {0.7F, 1.2F, 3.4F, 1.2F, 0.9F, 1.4F};
            /*  631 */ PdfPTable table = new PdfPTable(widths);
            /*  632 */ table.setWidthPercentage(100.0F);

            /*  634 */ createEntete(rapport, SessionMBean.getParametrage());

            /*  636 */ rapport.add((Element) new Paragraph(" "));

            /*  638 */ Paragraph titre = new Paragraph("RAPPORT MENSUEL DES VENTES : " + anneeMois.getIdmois().getNom().toUpperCase() + " - " + anneeMois.getIdannee().getNom(), new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            /*  639 */ titre.setAlignment(1);
            /*  640 */ rapport.add((Element) titre);

            /*  642 */ Paragraph periode = new Paragraph("Imprimé le : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /*  643 */ periode.setAlignment(1);
            /*  644 */ rapport.add((Element) periode);

            /*  646 */ rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Date", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("Article", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("P.U", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("Qté", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("Total", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));

            Double total = 0.0;
            int quantite = 0;
            int compteur = 1;

            for (Commande c : commandes) {
                quantite = (quantite + c.getQuantite().intValue());
                total += (c.getMontant() * c.getQuantite());
                table.addCell(createPdfPCell("" + compteur, 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + sdf.format(c.getIdfacture().getDateAchat()), 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + c.getIdproduit().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(c.getMontant()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(c.getQuantite()), 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((c.getMontant() * c.getQuantite())) + "", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                compteur++;
            }

            table.addCell(createPdfPCell("Totaux", 4, 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((quantite)), 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(total), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));

            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printFacture(Facture facture, Parametrage parametrage) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
            fileName = "Facture_" + facture.getIdclient().getNom() + "_" + facture.getNumeroFacture() + "_" + sdf.format(facture.getDateAchat()) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            rapport.open();

            createEntete(rapport, parametrage);

            float[] width_entete = {2.0F, 2.2F, 1.8F, 3.5F, 1.0F, 1.7F};
            PdfPTable entete_1 = new PdfPTable(width_entete);
            entete_1.setWidthPercentage(100.0F);

            entete_1.addCell(createPdfPCell("Date de tirage   : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + sdf.format(new Date()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell("Code du client : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + facture.getIdclient().getCode(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

            entete_1.addCell(createPdfPCell("Heure de tirage : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + sdf1.format(new Date()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell("Nom du client   : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  712 */ entete_1.addCell(createPdfPCell(" " + facture.getIdclient().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  713 */ entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  714 */ entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

            /*  716 */ rapport.add((Element) entete_1);
            /*  717 */ rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            /*  719 */ float[] widths = {1.4F, 3.2F, 0.8F, 1.3F, 0.8F, 1.1F};
            /*  720 */ PdfPTable table = new PdfPTable(widths);
            /*  721 */ table.setWidthPercentage(100.0F);

            /*  723 */ Paragraph titre = new Paragraph("FACTURE N° : " + facture.getNumeroFacture() + " / " + sdf.format(facture.getDateAchat()), new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            /*  724 */ titre.setAlignment(1);
            /*  725 */ rapport.add((Element) titre);

            /*  727 */ Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /*  728 */ rapport.add((Element) ligne);

            /*  732 */ rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("Ref", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Désignation", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Quantité", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Présentation", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("P.U", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Prix total", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            Double quantite = (0.0D);
            Double total = (0.0D);
            int compteur = 1;
            for (Commande c : facture.getCommandeList()) {
                quantite += c.getQuantite();
                total += (c.getMontant() * c.getQuantite());
                table.addCell(createPdfPCell(" " + c.getIdproduit().getCode(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + Utilitaires.formatPrenomMaj(c.getIdproduit().getNom()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(c.getQuantite()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + Utilitaires.formatPrenomMaj(c.getIdproduit().getIdUnite().getNom()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(c.getMontant()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney((c.getMontant() * c.getQuantite())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                compteur++;
            }

            table.addCell(createPdfPCell("Total ", 2, 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell(" " + quantite.intValue(), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell(" ", 3, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            rapport.add((Element) table);

            /*  758 */ float[] width = {2.0F, 1.0F, 0.5F, 2.0F, 2.0F, 1.5F};
            /*  759 */ PdfPTable bilan = new PdfPTable(width);
            /*  760 */ bilan.setWidthPercentage(100.0F);
            /*  761 */ rapport.add((Element) new Paragraph(" "));

            /*  766 */ bilan.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  767 */ bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  768 */ bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  769 */ bilan.addCell(createPdfPCell(" ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  770 */ bilan.addCell(createPdfPCell("Caissier(e)", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  771 */ bilan.addCell(createPdfPCell("NET A PAYER", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 1, 1));

            /*  776 */ bilan.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  777 */ bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  778 */ bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  779 */ bilan.addCell(createPdfPCell(" ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  780 */ bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /*  781 */ bilan.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(total), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 0, 1));

            rapport.add((Element) bilan);

            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printRecuVersement(Versement versement, Parametrage parametrage) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
            fileName = "Recu_versement_" + versement.getIdfacture().getIdclient().getNom() + "_" + versement.getCode() + "_" + sdf.format(versement.getDate()) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            rapport.open();

            createEntete(rapport, parametrage);

            float[] width_entete = {2.0F, 2.0F, 1.8F, 3.8F, 1.0F, 1.0F};
            PdfPTable entete_1 = new PdfPTable(width_entete);
            entete_1.setWidthPercentage(100.0F);

            entete_1.addCell(createPdfPCell("Date de tirage   : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + sdf.format(new Date()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell("Code du client : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + versement.getIdfacture().getIdclient().getCode(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell("District : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

            entete_1.addCell(createPdfPCell("Heure de tirage : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + sdf1.format(new Date()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell("Nom du client   : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + versement.getIdfacture().getIdclient().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

            rapport.add((Element) entete_1);
            rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            float[] widths = {3.2F, 1.5F, 1.2F, 1.2F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            Paragraph titre = new Paragraph("Récu versement N° : " + versement.getCode() + " / " + sdf.format(versement.getDate()), new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            rapport.add((Element) ligne);

            rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("Motif", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Réf facture", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Montant", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Status\nReste", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            table.addCell(createPdfPCell(" Payement facture", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell(" " + versement.getIdfacture().getNumeroFacture(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney((versement.getMontant().intValue())), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            if (versement.getReste() <= 0.0D) {
                table.addCell(createPdfPCell(" Soldé", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            } else {
                table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney((versement.getReste().intValue())), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            }

            rapport.add((Element) table);

            float[] width = {2.0F, 1.0F, 0.5F, 2.0F, 2.0F, 1.5F};
            PdfPTable bilan = new PdfPTable(width);
            bilan.setWidthPercentage(100.0F);
            rapport.add((Element) new Paragraph(" "));

            bilan.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell("VISA DU CAISSIER(E)", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell("   ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

            rapport.add((Element) bilan);

            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printBonCommandeClient(CommandeClient commande, Parametrage parametrage) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
            fileName = "Boncommande_" + commande.getIdclient().getNom() + "_" + sdf.format(commande.getDateCommande()) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            rapport.open();

            createEntete(rapport, parametrage);

            float[] width_entete = {2.0F, 2.3F, 1.8F, 3.8F, 1.0F, 1.0F};
            PdfPTable entete_1 = new PdfPTable(width_entete);
            entete_1.setWidthPercentage(100.0F);

            entete_1.addCell(createPdfPCell("Date de tirage   : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + sdf.format(new Date()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell("Code du client : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + commande.getIdclient().getCode(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

            entete_1.addCell(createPdfPCell("Heure de tirage : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + sdf1.format(new Date()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell("Nom du client   : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" " + commande.getIdclient().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

            rapport.add(entete_1);
            rapport.add(new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            float[] widths = {1.4F, 3.2F, 0.8F, 1.3F, 0.8F, 1.1F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            Paragraph titre = new Paragraph("BON DE COMMANDE N° : " + commande.getCode() + " / " + sdf.format(commande.getDateCommande()), new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            rapport.add((Element) ligne);

            rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("Ref", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Désignation", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Quantité", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Présentation", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("P.U", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            table.addCell(createPdfPCell("Prix total", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            Double quantite = (0.0D);
            Double total = (0.0D);
            int compteur = 1;
            for (LigneCommandeClient l : commande.getLigneCommandeClientList()) {
                quantite += l.getQuantite();
                total += (l.getMontant() * l.getQuantite());
                table.addCell(createPdfPCell(" " + l.getIdproduit().getCode(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + Utilitaires.formatPrenomMaj(l.getIdproduit().getNom()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(l.getQuantite()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + Utilitaires.formatPrenomMaj(l.getIdproduit().getIdUnite().getNom()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(l.getMontant()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney((l.getMontant() * l.getQuantite())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                compteur++;
            }

            rapport.add(table);

            float[] width = {2.0F, 1.0F, 0.5F, 2.0F, 2.0F, 1.5F};
            PdfPTable bilan = new PdfPTable(width);
            bilan.setWidthPercentage(100.0F);
            rapport.add((Element) new Paragraph(" "));

            bilan.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" VALEUR", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 1, 1));

            bilan.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            bilan.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(total), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 0, 1));

            rapport.add((Element) bilan);

            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printBonLivraison(Facture facture, Parametrage parametrage) {
        /*  996 */ String fileName = "";
        try {
            /*  998 */ SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            /*  999 */ SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
            /* 1000 */ SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yy");
            /* 1001 */ fileName = "Bon_Livraison_" + facture.getIdclient().getNom() + "_" + facture.getNumeroFacture() + "_" + sdf.format(facture.getDateAchat()) + ".pdf";
            /* 1002 */ Document rapport = new Document();
            /* 1003 */ PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            /* 1004 */ rapport.open();

            /* 1006 */ createEntete(rapport, parametrage);

            /* 1008 */ float[] width_entete = {2.0F, 2.2F, 1.4F, 3.5F, 1.2F, 2.1F};
            /* 1009 */ PdfPTable entete_1 = new PdfPTable(width_entete);
            /* 1010 */ entete_1.setWidthPercentage(100.0F);

            /* 1012 */ entete_1.addCell(createPdfPCell("Date de tirage   : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1013 */ entete_1.addCell(createPdfPCell(" " + sdf.format(new Date()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1014 */ entete_1.addCell(createPdfPCell("Code du client : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1015 */ entete_1.addCell(createPdfPCell(" " + facture.getIdclient().getCode(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1016 */ entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1017 */ entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

            /* 1019 */ entete_1.addCell(createPdfPCell("Heure de tirage : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1020 */ entete_1.addCell(createPdfPCell(" " + sdf1.format(new Date()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1021 */ entete_1.addCell(createPdfPCell("Nom du client   : ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1022 */ entete_1.addCell(createPdfPCell(" " + facture.getIdclient().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1023 */ entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1024 */ entete_1.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

            /* 1026 */ rapport.add((Element) entete_1);
            /* 1027 */ rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            /* 1029 */ float[] widths = {1.3F, 3.0F, 1.0F, 1.0F, 1.0F, 1.7F};
            /* 1030 */ PdfPTable table = new PdfPTable(widths);
            /* 1031 */ table.setWidthPercentage(100.0F);

            /* 1033 */ Paragraph titre = new Paragraph("BON DE LIVRAISON N° : " + facture.getNumeroFacture() + " / " + sdf.format(facture.getDateAchat()), new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            /* 1034 */ titre.setAlignment(1);
            /* 1035 */ rapport.add((Element) titre);

            /* 1037 */ Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /* 1038 */ rapport.add((Element) ligne);

            /* 1040 */ rapport.add((Element) new Paragraph(" "));

            /* 1042 */ table.addCell(createPdfPCell("Ref", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            /* 1043 */ table.addCell(createPdfPCell("Désignation", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            /* 1045 */ table.addCell(createPdfPCell("Qté", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            /* 1046 */ table.addCell(createPdfPCell("Présent", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            /* 1047 */ table.addCell(createPdfPCell("Perempt", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            /* 1048 */ table.addCell(createPdfPCell("N° de Lot", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            /* 1052 */ Double quantite = (0.0D);
            /* 1053 */ Double total = (0.0D);
            /* 1054 */ int compteur = 1;
            for (Commande c : facture.getCommandeList()) {
                /* 1056 */ quantite += (c.getQuantite());
                /* 1057 */ total += (c.getMontant() * c.getQuantite());
                /* 1058 */ table.addCell(createPdfPCell(" " + c.getIdproduit().getCode().toUpperCase(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                /* 1059 */ table.addCell(createPdfPCell(" " + Utilitaires.formatPrenomMaj(c.getIdproduit().getNom()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

                /* 1061 */ table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(c.getQuantite()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                /* 1062 */ table.addCell(createPdfPCell(" " + Utilitaires.formatPrenomMaj(c.getIdproduit().getIdUnite().getNom()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

                try {
                    Date d = c.getIdlot().getDatePeremption();
                    table.addCell(createPdfPCell(" " + sdf3.format(d), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                } catch (Exception e) {
                    table.addCell(createPdfPCell(" / ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                }
                table.addCell(createPdfPCell(" " + Utilitaires.formatPrenomMaj(c.getIdlot().getNumeroLot()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

                compteur++;
            }

            rapport.add((Element) table);

            float[] width = {1.0F, 2.0F, 0.5F, 2.0F, 2.0F, 1.5F};
            PdfPTable bilan = new PdfPTable(width);
            bilan.setWidthPercentage(100.0F);
            rapport.add((Element) new Paragraph(" "));

            bilan.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1088 */ bilan.addCell(createPdfPCell("LE MAGASINIER", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1089 */ bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1090 */ bilan.addCell(createPdfPCell(" ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1091 */ bilan.addCell(createPdfPCell("LE CLIENT", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1092 */ bilan.addCell(createPdfPCell("Valeur du bon", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 1, 1));

            /* 1097 */ bilan.addCell(createPdfPCell(" ", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1098 */ bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1099 */ bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1100 */ bilan.addCell(createPdfPCell(" ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1101 */ bilan.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
            /* 1102 */ bilan.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(total), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 0, 1));

            /* 1107 */ rapport.add((Element) bilan);

            /* 1109 */ rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printInventaireDetaille(Inventaire inventaire, Parametrage parametrage) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yy");
            fileName = "Fiche_inventaire_" + inventaire.getCode() + "_" + sdf.format(new Date()) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            rapport.open();

            createEntete(rapport, parametrage);

            rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            List<Famille> familles = new ArrayList<>();
            List<Produit> produits = new ArrayList<>();

            for (Ligneinventaire l : inventaire.getLigneinventaireList()) {
                if (!familles.contains(l.getIdlot().getIdproduit().getIdfamille())) {
                    familles.add(l.getIdlot().getIdproduit().getIdfamille());
                }

                if (!produits.contains(l.getIdlot().getIdproduit())) {
                    produits.add(l.getIdlot().getIdproduit());
                }
            }

            Double qte = (0.0D);
            Double sommeTotal = (0.0D);
            for (Famille f : familles) {

                float[] width_entete_1 = {3.0F, 3.0F, 3.0F};
                PdfPTable entete_1 = new PdfPTable(width_entete_1);
                entete_1.setWidthPercentage(100.0F);

                entete_1.addCell(createPdfPCell("Inventaire : " + f.getNom().toUpperCase(), 2, 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 1), 1, 1, 1, 0));

                entete_1.addCell(createPdfPCell("" + parametrage.getNomStructure().toUpperCase(), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 0, 0, 1));
                entete_1.addCell(createPdfPCell("Au " + sdf3.format(inventaire.getDateInventaire()), 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 1));
                entete_1.addCell(createPdfPCell("Tenu de compte : ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));

                rapport.add((Element) entete_1);

                rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 8.0F, 0)));

                float[] width_entete_2 = {3.0F, 2.0F, 1.5F, 1.0F, 1.0F};
                PdfPTable entete_2 = new PdfPTable(width_entete_2);
                entete_2.setWidthPercentage(100.0F);

                entete_2.addCell(createPdfPCell("SMS - Stock Manager System 3.0 : ", 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                entete_2.addCell(createPdfPCell("Date de tirage  " + sdf3.format(new Date()), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                entete_2.addCell(createPdfPCell("à : " + sdf1.format(new Date()), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                entete_2.addCell(createPdfPCell("Page : ", 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                entete_2.addCell(createPdfPCell(" / ", 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

                rapport.add((Element) entete_2);
                rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 8.0F, 0)));

                float[] arrayOfFloat1 = {2.0F, 2.2F, 1.2F, 1.2F, 1.2F, 1.0F, 1.0F, 1.0F};
                PdfPTable pdfPTable1 = new PdfPTable(arrayOfFloat1);
                pdfPTable1.setWidthPercentage(100.0F);

                pdfPTable1.addCell(createPdfPCell("Réference ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell("Designation\nNuméro de lot / Péremption /Fabrication ", 2, 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell("Prix ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell("Qté théorique", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell("Qté physique", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell("Ecart", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));

                Double s_qte = (0.0D);
                for (Produit p : produits) {
                    if (p.getIdfamille().equals(f)) {

                        List<Ligneinventaire> lTemps = new ArrayList<>();
                        for (Ligneinventaire l : inventaire.getLigneinventaireList()) {
                            if (p.equals(l.getIdlot().getIdproduit())) {
                                lTemps.add(l);
                            }
                        }

                        pdfPTable1.addCell(createPdfPCell(" " + p.getCode(), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 0, 1));
                        pdfPTable1.addCell(createPdfPCell(" " + p.getNom(), 1, 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));
                        pdfPTable1.addCell(createPdfPCell(" ", 1, 4, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));

                        double count = 0.0D;

                        for (Ligneinventaire l : inventaire.getLigneinventaireList()) {
                            if (l.getIdlot().getIdproduit().equals(p)) {

                                count++;
                                if (count == 1.0D) {
                                    pdfPTable1.addCell(createPdfPCell(" ", 1, 1, lTemps.size(), new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 0, 1));
                                }

                                pdfPTable1.addCell(createPdfPCell(" " + l.getIdlot().getNumeroLot().toUpperCase(), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));

                                try {
                                    Date d = l.getIdlot().getDatePeremption();
                                    pdfPTable1.addCell(createPdfPCell(" " + sdf3.format(d), 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));
                                } catch (Exception e) {
                                    pdfPTable1.addCell(createPdfPCell(" / ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));
                                }

                                try {
                                    Date d = l.getIdlot().getDateFabrication();
                                    pdfPTable1.addCell(createPdfPCell(" " + sdf3.format(d), 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));
                                } catch (Exception e) {
                                    pdfPTable1.addCell(createPdfPCell(" / ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));
                                }
                                pdfPTable1.addCell(createPdfPCell(" " + l.getIdlot().getPrixVente(), 3, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));
                                pdfPTable1.addCell(createPdfPCell(" " + l.getQuantiteLogique(), 3, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));
                                pdfPTable1.addCell(createPdfPCell(" " + l.getQuantitePhysique(), 3, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));
                                pdfPTable1.addCell(createPdfPCell(" " + l.getEcart(), 3, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));
                                qte += (l.getQuantitePhysique());
                                s_qte += (l.getQuantitePhysique());

                                sommeTotal += (+l.getPrix() * l.getQuantitePhysique());
                            }
                        }
                    }
                }

                pdfPTable1.addCell(createPdfPCell("Sous Total ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell(" ", 2, 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell(" ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell(" ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell(" " + s_qte.intValue(), 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
                pdfPTable1.addCell(createPdfPCell(" " + sommeTotal.intValue(), 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));

                rapport.add((Element) pdfPTable1);

                rapport.newPage();
            }

            float[] width_entete_3 = {1.5F, 1.5F, 1.5F, 1.5F, 1.5F, 1.0F, 1.0F, 1.0F};
            PdfPTable entete_3 = new PdfPTable(width_entete_3);
            entete_3.setWidthPercentage(100.0F);

            entete_3.addCell(createPdfPCell("Totaux ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 1, 1, 1));
            entete_3.addCell(createPdfPCell(" ", 2, 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
            entete_3.addCell(createPdfPCell(" ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
            entete_3.addCell(createPdfPCell(" ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
            entete_3.addCell(createPdfPCell(" " + qte.intValue(), 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));
            entete_3.addCell(createPdfPCell(" ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 1, 1));

            rapport.add((Element) entete_3);

            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printInventaire(Inventaire inventaire, Parametrage parametrage) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yy");
            fileName = "Fiche_inventaire_" + inventaire.getCode() + "_" + sdf.format(new Date()) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            rapport.open();

            createEntete(rapport, parametrage);

            rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            List<Famille> familles = new ArrayList<>();
            for (Ligneinventaire l : inventaire.getLigneinventaireList()) {
                if (!familles.contains(l.getIdlot().getIdproduit().getIdfamille())) {
                    familles.add(l.getIdlot().getIdproduit().getIdfamille());
                }
            }

            Double qteTotal = 0.0D;
            Double qteTTotal = 0.0D;
            Double sommeTotal = 0.0D;
            int count = 0;
            for (Famille f : familles) {
                count += 1;
                float[] width_entete_1 = {3.0F, 3.0F, 3.0F};
                PdfPTable entete_1 = new PdfPTable(width_entete_1);
                entete_1.setWidthPercentage(100.0F);

                entete_1.addCell(createPdfPCell("Inventaire : " + f.getNom().toUpperCase(), 2, 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 1), 1, 1, 1, 0));

                entete_1.addCell(createPdfPCell("" + parametrage.getNomStructure().toUpperCase(), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 1, 0, 0, 1));
                entete_1.addCell(createPdfPCell("Au " + sdf3.format(inventaire.getDateInventaire()), 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 1));
                entete_1.addCell(createPdfPCell("Tenu de compte : ", 2, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 1, 0, 1));

                rapport.add(entete_1);

                rapport.add(new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 8.0F, 0)));

                float[] width_entete_2 = {3.0F, 2.0F, 1.5F, 1.0F, 1.0F};
                PdfPTable entete_2 = new PdfPTable(width_entete_2);
                entete_2.setWidthPercentage(100.0F);

                entete_2.addCell(createPdfPCell("SMS - Stock Manager System 3.0 : ", 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                entete_2.addCell(createPdfPCell("Date de tirage  " + sdf3.format(new Date()), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                entete_2.addCell(createPdfPCell("à : " + sdf1.format(new Date()), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                entete_2.addCell(createPdfPCell("Page : ", 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                entete_2.addCell(createPdfPCell(" / ", 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

                rapport.add(entete_2);
                rapport.add(new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 8.0F, 0)));

                float[] arrayOfFloat1 = {2.0F, 2.2F, 1.2F, 1.2F, 1.2F, 1.0F, 1.0F, 1.0F};
                PdfPTable pdfPTable1 = new PdfPTable(arrayOfFloat1);
                pdfPTable1.setWidthPercentage(100.0F);

                pdfPTable1.addCell(createPdfPCell("Réference ", 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                pdfPTable1.addCell(createPdfPCell("Designation\nNuméro de lot / Péremption /Fabrication ", 3, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                pdfPTable1.addCell(createPdfPCell("Prix ", 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                pdfPTable1.addCell(createPdfPCell("Qté théorique", 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                pdfPTable1.addCell(createPdfPCell("Qté physique", 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                pdfPTable1.addCell(createPdfPCell("Ecart", 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

                Double qteGroup = 0.0D;
                Double sommeGroup = 0.0D;
                Double qteTGroup = 0d;

                List<Ligneinventaire> lTemps = new ArrayList<>();
                for (Ligneinventaire l : inventaire.getLigneinventaireList()) {
                    if (f.getIdfamille().equals(l.getIdlot().getIdproduit().getIdfamille().getIdfamille())) {
                        lTemps.add(l);
                    }
                }

                for (Ligneinventaire l : lTemps) {
                    pdfPTable1.addCell(createPdfPCell(" " + l.getIdlot().getIdproduit().getNom(), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    pdfPTable1.addCell(createPdfPCell(" " + l.getIdlot().getNumeroLot().toUpperCase(), 1, 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    try {
                        Date d = l.getIdlot().getDatePeremption();
                        pdfPTable1.addCell(createPdfPCell(" " + sdf3.format(d), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    } catch (Exception e) {
                        pdfPTable1.addCell(createPdfPCell(" / ", 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    }

                    try {
                        Date d = l.getIdlot().getDateFabrication();
                        pdfPTable1.addCell(createPdfPCell(" " + sdf3.format(d), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    } catch (Exception e) {
                        pdfPTable1.addCell(createPdfPCell(" / ", 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    }
                    pdfPTable1.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(l.getIdlot().getPrixVente().intValue()), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    pdfPTable1.addCell(createPdfPCell(" " + l.getQuantiteLogique().intValue(), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    pdfPTable1.addCell(createPdfPCell(" " + l.getQuantitePhysique().intValue(), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    pdfPTable1.addCell(createPdfPCell(" " + l.getEcart().intValue(), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

                    qteGroup += l.getQuantitePhysique();
                    qteTGroup += l.getQuantiteLogique();
                    sommeGroup += (l.getPrix() * l.getQuantitePhysique());
                }
                qteTotal += qteGroup;
                qteTTotal += qteTGroup;
                sommeTotal += sommeGroup;

                pdfPTable1.addCell(createPdfPCell("Sous Total ", 4, 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                pdfPTable1.addCell(createPdfPCell(" ", 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                pdfPTable1.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(qteTGroup.intValue()), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                pdfPTable1.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(qteGroup.intValue()), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                pdfPTable1.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(sommeGroup.intValue()), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

                rapport.add(pdfPTable1);
                if (count != familles.size()) {
                    rapport.newPage();
                }
                inventaire.getLigneinventaireList().removeAll(lTemps);
            }

            float[] width_entete_3 = {2.0F, 2.2F, 1.2F, 1.2F, 1.2F, 1.0F, 1.0F, 1.0F};
            PdfPTable entete_3 = new PdfPTable(width_entete_3);
            entete_3.setWidthPercentage(100.0F);
            entete_3.addCell(createPdfPCell(" ****************** ", 8, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            entete_3.addCell(createPdfPCell("Totaux ", 4, 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            entete_3.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(sommeTotal.intValue()), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            entete_3.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(qteTTotal.intValue()), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            entete_3.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(qteTotal.intValue()), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            entete_3.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(qteTotal.intValue() - qteTTotal.intValue()), 1, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

            rapport.add(entete_3);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printHistoric(List<Facture> factures, Parametrage parametrage, String title, String nom_fichier) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fileName = nom_fichier;
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            rapport.open();

            createEntete(rapport, parametrage);

            float[] widths = {2.0F, 4.5F, 2.2F, 2.0F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            Paragraph titre = new Paragraph(title, new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            rapport.add((Element) ligne);

            rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("Date", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Client", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("N° facture", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Montant", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            Double total = 0.0D;
            for (Facture f : factures) {
                total += f.getMontantTtc();
                table.addCell(createPdfPCell("" + sdf.format(f.getDateAchat()), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + Utilitaires.formatPrenomMaj(f.getIdclient().getNom()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + f.getNumeroFacture().toLowerCase(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(f.getMontantTtc()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            }
            table.addCell(createPdfPCell("Total", 3, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(total.intValue()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printHistoric(List<Journee> journees, List<Stock> stocks, List<Facture> factures, Parametrage parametrage, String title, String nom_fichier) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fileName = nom_fichier;
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            rapport.open();

            createEntete(rapport, parametrage);

            float[] widths = {2.0F, 4.5F, 2.2F, 2.0F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            Paragraph titre = new Paragraph(title, new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            rapport.add((Element) ligne);

            rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("Date", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Client", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("N° facture", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Montant", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            double qteSortantT = 0;
            Double total = 0.0D;
            for (Journee j : journees) {
                List<Facture> fTemp = getFacture(j, factures);
                if (!fTemp.isEmpty()) {
                    factures.removeAll(fTemp);
                    table.addCell(createPdfPCell(" " + sdf.format(j.getDateJour()), 4, 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    Double totalGroup = 0d;
                    Double qteSortant = 0d;
                    for (Facture f : fTemp) {

                        if (f.getCommandeList().isEmpty()) {
                            f.setCommandeList(commandeFacadeLocal.findByFacture(f));
                        }

                        for (Commande c : f.getCommandeList()) {
                            qteSortantT += c.getQuantite();
                            qteSortant += c.getQuantite();
                        }

                        totalGroup += f.getMontantTtc();
                        total += f.getMontantTtc();
                        table.addCell(createPdfPCell("" + sdf.format(f.getDateAchat()), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        table.addCell(createPdfPCell("" + Utilitaires.formatPrenomMaj(f.getIdclient().getNom()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        table.addCell(createPdfPCell("" + f.getNumeroFacture().toLowerCase(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(f.getMontantTtc()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    }
                    table.addCell(createPdfPCell("Sous - Total", 3, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(totalGroup.intValue()) + " / " + qteSortant.intValue(), 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                }
            }

            table.addCell(createPdfPCell("Total Période : ", 3, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((total.intValue())) + " / " + qteSortantT, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            table.addCell(createPdfPCell("", 4, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell(" Entrée", 4, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            Double qteEntrantT = 0d;
            Double totalEntree = 0.0D;
            for (Journee j : journees) {
                List<Stock> sTemp = getStock(j, stocks);
                if (!sTemp.isEmpty()) {
                    stocks.removeAll(sTemp);
                    table.addCell(createPdfPCell(" " + sdf.format(j.getDateJour()), 4, 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    Double totalGroup = 0d;
                    Double qteEntrant = 0d;
                    for (Stock s : sTemp) {

                        if (s.getStockProduitList().isEmpty()) {
                            s.setStockProduitList(stockProduitFacadeLocal.findByStock(s));
                        }

                        for (StockProduit sp : s.getStockProduitList()) {
                            qteEntrant += sp.getQuantite();
                            qteEntrantT += sp.getQuantite();
                        }
                        totalGroup += s.getMontant();
                        totalEntree += s.getMontant();
                        table.addCell(createPdfPCell("" + sdf.format(s.getDateAchat()), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        table.addCell(createPdfPCell("" + Utilitaires.formatPrenomMaj(s.getIdfournisseur().getRaisonSociale()), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        table.addCell(createPdfPCell("" + s.getCode().toLowerCase(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getMontant()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    }
                    table.addCell(createPdfPCell("Sous - Total", 3, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(totalGroup.intValue()) + " / " + qteEntrant.intValue(), 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                }
            }

            table.addCell(createPdfPCell("Total Période : ", 3, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((totalEntree.intValue())) + " / " + qteEntrantT.intValue(), 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            table.addCell(createPdfPCell(" ", 4, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Total entrée / sortie : " + qteEntrantT + " / " + qteSortantT, 4, 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    private static List<Facture> getFacture(Journee j, List<Facture> factures) {
        List<Facture> list = new ArrayList<>();
        for (Facture f : factures) {
            if (f.getDateAchat().equals(j.getDateJour())) {
                list.add(f);
            }
        }
        return list;
    }

    private static List<Stock> getStock(Journee j, List<Stock> stocks) {
        List<Stock> list = new ArrayList<>();
        for (Stock s : stocks) {
            if (s.getDateAchat().equals(j.getDateJour())) {
                list.add(s);
            }
        }
        return list;
    }

    public static String printHistoric(List<Facture> factures, Parametrage parametrage, String title, String nom_fichier, Client client, String date) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fileName = nom_fichier;
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            rapport.open();

            createEntete(rapport, parametrage);

            /* 1435 */ float[] widths = {2.0F, 2.0F, 2.0F, 2.0F, 2.0F};
            /* 1436 */ PdfPTable table = new PdfPTable(widths);
            /* 1437 */ table.setWidthPercentage(90.0F);

            /* 1439 */ Paragraph titre = new Paragraph(title, new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            /* 1440 */ titre.setAlignment(1);
            /* 1441 */ rapport.add((Element) titre);

            /* 1443 */ Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /* 1444 */ rapport.add((Element) ligne);

            /* 1446 */ rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 5.0F, 2)));

            /* 1448 */ Paragraph ligne_client = new Paragraph("Client : " + client.getNom() + " " + client.getPrenom(), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /* 1449 */ rapport.add((Element) ligne_client);

            rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 5.0F, 2)));

            table.addCell(createPdfPCell("Date", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("N° facture", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Montant ttc", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Montant payé", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Reste", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            double total = 0;
            double total_paye = 0;
            double total_reste = 0;
            for (Facture f : factures) {
                total += f.getMontantTtc();
                total_paye += f.getMontantPaye();
                total_reste += f.getReste();
                table.addCell(createPdfPCell("" + sdf.format(f.getDateAchat()), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + f.getNumeroFacture().toLowerCase(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(f.getMontantTtc()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(f.getMontantPaye()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(f.getReste()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
            }
            table.addCell(createPdfPCell("Total", 2, 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((total)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((total_paye)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((total_reste)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    private static List<LigneMvtStock> filterMvtStock(Produit p, List<LigneMvtStock> ligneMvtStocks) {
        List<LigneMvtStock> results = new ArrayList<>();
        for (LigneMvtStock l : ligneMvtStocks) {
            if (p.equals(l.getIdlot().getIdproduit())) {
                results.add(l);
            }
        }
        return results;
    }

    public static String printMouvementMensuel(List<LigneMvtStock> ligneMvtStocks, List<Produit> produits, Parametrage parametrage, String title, String nom_fichier) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yy");
            fileName = nom_fichier;
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/facture/" + fileName));
            rapport.open();

            createEntete(rapport, parametrage);

            int conteur = 0;

            for (Produit p : produits) {

                List<LigneMvtStock> ligne = filterMvtStock(p, ligneMvtStocks);
                if (!ligne.isEmpty()) {

                    ligneMvtStocks.removeAll(ligne);

                    Paragraph titre = new Paragraph("FICHE DE STOCK N° : " + (conteur + 1), new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 1));
                    titre.setAlignment(1);
                    rapport.add((Element) titre);

                    rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 9.0F, 0)));

                    float[] widthsEntete = {1.4F, 3.0F, 3.0F, 1.4F};
                    PdfPTable tableEntete = new PdfPTable(widthsEntete);
                    tableEntete.setWidthPercentage(100.0F);

                    tableEntete.addCell(createPdfPCell("Nom(DCI)", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("" + p.getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("CMM", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("--", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

                    tableEntete.addCell(createPdfPCell("Dosage", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("" + p.getQteDosage(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("Niveau Maximum", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("--", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

                    tableEntete.addCell(createPdfPCell("Forme", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("" + p.getIdforme().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("Niveau Minimum", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("--", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

                    tableEntete.addCell(createPdfPCell("Conditionnement", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("--", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("Stock de sécurité", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));
                    tableEntete.addCell(createPdfPCell("--", 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0), 0, 0, 0, 0));

                    rapport.add((Element) tableEntete);

                    rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 7.0F, 0)));

                    float[] widths = {1.0F, 1.9F, 1.9F, 1.4f, 1.4F, 2.0F, 1.4F, 2.0F, 1.4F, 1.6F};
                    PdfPTable table = new PdfPTable(widths);
                    table.setWidthPercentage(100.0F);

                    rapport.add((Element) new Paragraph("MOUVEMENTS DE STOCK", new Font(Font.FontFamily.TIMES_ROMAN, 9.0F, 0)));
                    rapport.add((Element) new Paragraph(" ", new Font(Font.FontFamily.TIMES_ROMAN, 7.0F, 0)));

                    table.addCell(createPdfPCell("Date", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("Numéro de lot", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("Date de péremption", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("Qte A", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("Qte E", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("Provenance", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("Qté S", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("Destination", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("Reste", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
                    table.addCell(createPdfPCell("Observation", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

                    for (LigneMvtStock l : ligne) {

                        table.addCell(createPdfPCell("" + sdf.format(l.getIdMvtStock().getDateMvt()), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        table.addCell(createPdfPCell("" + l.getIdlot().getNumeroLot(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

                        try {
                            Date d = l.getIdlot().getDatePeremption();
                            table.addCell(createPdfPCell(" " + sdf2.format(d), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        } catch (Exception e) {
                            table.addCell(createPdfPCell(" / ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        }

                        table.addCell(createPdfPCell("" + l.getQuantiteAvant().intValue(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));

                        if (l.getQuantiteEntree() == 0.0D) {
                            table.addCell(createPdfPCell(" / ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                            table.addCell(createPdfPCell(" / ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        } else {
                            table.addCell(createPdfPCell("" + l.getQuantiteEntree(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                            table.addCell(createPdfPCell(" " + l.getFournisseur(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        }

                        if (l.getQuantiteSortie() == 0.0D) {
                            table.addCell(createPdfPCell(" / ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                            table.addCell(createPdfPCell(" / ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        } else {
                            table.addCell(createPdfPCell(" " + l.getQuantiteSortie().intValue(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                            table.addCell(createPdfPCell(" " + l.getClient(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        }

                        table.addCell(createPdfPCell(" " + l.getReste(), 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                        table.addCell(createPdfPCell(" ", 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                    }
                    rapport.add((Element) table);
                    rapport.newPage();
                }
                conteur++;
            }

            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printStock(Stock stock, List<StockProduit> stockProduits) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Stock_" + stock.getCode() + "_" + sdf.format(stock.getDateAchat()) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/stock/" + fileName));
            rapport.open();
            float[] widths = {0.6F, 3.0F, 1.0F, 0.8F, 1.2F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            createEntete(rapport, SessionMBean.getParametrage());

            rapport.add((Element) new Paragraph(" "));

            /* 1628 */ Paragraph titre = new Paragraph("APPROVISIONNEMENT EN STOCK : " + stock.getCode(), new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            /* 1629 */ titre.setAlignment(1);
            /* 1630 */ rapport.add((Element) titre);

            /* 1632 */ Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /* 1633 */ rapport.add((Element) ligne);

            /* 1635 */ Paragraph nom = new Paragraph("Fournisseur : " + stock.getIdfournisseur().getRaisonSociale(), new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 2));
            /* 1636 */ rapport.add((Element) nom);

            /* 1638 */ Paragraph telephoneClient = new Paragraph("Contact : " + stock.getIdfournisseur().getContact1(), new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 2));
            /* 1639 */ rapport.add((Element) telephoneClient);

            /* 1641 */ Paragraph dateAchat = new Paragraph("Date : " + sdf.format(stock.getDateAchat()), new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 2));
            /* 1642 */ rapport.add((Element) dateAchat);

            /* 1644 */ rapport.add((Element) new Paragraph(" "));

            /* 1646 */ table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /* 1647 */ table.addCell(createPdfPCell("Produit", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /* 1648 */ table.addCell(createPdfPCell("Prix d'achat", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /* 1649 */ table.addCell(createPdfPCell("Quantité", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            /* 1650 */ table.addCell(createPdfPCell("Total", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));

            Double quantite = (0.0D);
            Double total = (0.0D);
            int compteur = 1;
            for (StockProduit s : stockProduits) {
                quantite += s.getQuantite();
                total += (+s.getPrixAcquisition() * s.getQuantite());

                table.addCell(createPdfPCell("" + compteur, 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + s.getIdproduit().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getPrixAcquisition()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getQuantite()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                Double value = (s.getPrixAcquisition() * s.getQuantite());
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((value.intValue())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                compteur++;
            }

            table.addCell(createPdfPCell("Totaux", 3, 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(quantite), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((total.intValue())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));

            rapport.add((Element) table);

            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printStock(List<StockProduit> stockProduits) {
        String fileName = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Stock_general_" + sdf.format(new Date()) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/capital/" + fileName));
            rapport.open();
            float[] widths = {0.6F, 3.0F, 1.0F, 0.8F, 1.2F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            createEntete(rapport, SessionMBean.getParametrage());

            rapport.add((Element) new Paragraph(" "));

            Paragraph titre = new Paragraph("APPROVISIONNEMENT GENERAL EN STOCK  ", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            rapport.add((Element) ligne);

            rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("Produit", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("Prix d'achat", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("Quantité", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("Total", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));

            Double quantite = (0.0D);
            Double total = (0.0D);
            int compteur = 1;
            for (StockProduit s : stockProduits) {
                quantite += (+s.getQuantite());
                total += (+s.getPrixAcquisition() * s.getQuantite());
                table.addCell(createPdfPCell("" + compteur, 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + s.getIdproduit().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getPrixAcquisition()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(s.getQuantite()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((s.getPrixAcquisition() * s.getQuantite())), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                compteur++;
            }

            table.addCell(createPdfPCell("Totaux", 3, 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(quantite), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(Utilitaires.arrondiNDecimales(total, 0)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));

            rapport.add((Element) table);

            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printCustomerList(List<Client> clients) {
        String fileName = "";
        try {
            /* 1748 */ SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            /* 1749 */ fileName = "Liste_des_client_du_" + sdf.format(new Date()) + ".pdf";
            /* 1750 */ Document rapport = new Document();

            /* 1752 */ PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/client/" + fileName));
            /* 1753 */ rapport.open();
            /* 1754 */ float[] widths = {0.6F, 1.5F, 1.5F, 1.0F, 1.0F, 1.0F};
            /* 1755 */ PdfPTable table = new PdfPTable(widths);
            /* 1756 */ table.setWidthPercentage(100.0F);

            /* 1758 */ createEntete(rapport, SessionMBean.getParametrage());

            /* 1760 */ rapport.add((Element) new Paragraph(" "));

            /* 1762 */ Paragraph titre = new Paragraph("LISTE DES CLIENTS", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            /* 1763 */ titre.setAlignment(1);
            /* 1764 */ rapport.add((Element) titre);

            /* 1766 */ Paragraph periode = new Paragraph("Date : " + sdf.format(new Date()), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 6));
            /* 1767 */ periode.setAlignment(1);
            /* 1768 */ rapport.add((Element) periode);

            /* 1770 */ rapport.add((Element) new Paragraph(" "));

            /* 1772 */ table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("Nom(s)", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("Prenom(s)", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("N° CNI", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("CONTACT", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("SOLDE", 2, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));

            int solde = 0;
            for (Client c : clients) {
                solde += c.getSolde();
                table.addCell(createPdfPCell("" + c.getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + c.getPrenom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));

                table.addCell(createPdfPCell("" + c.getContact() + "", 1, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(c.getSolde()) + "", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            }
            table.addCell(createPdfPCell("Liquidité en caisse : ", 4, 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((solde)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));

            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static void createEntete(Document document, Parametrage parametrage) throws DocumentException {
        try {
            /* 1806 */ Paragraph entreprise = new Paragraph(parametrage.getNomStructure(), new Font(Font.FontFamily.TIMES_ROMAN, 16.0F, 1));
            /* 1807 */ entreprise.setAlignment(1);
            /* 1808 */ document.add((Element) entreprise);

            /* 1810 */ Paragraph entreprise1 = new Paragraph("" + parametrage.getDescriptif(), new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 1));
            /* 1811 */ entreprise1.setAlignment(1);
            /* 1812 */ document.add((Element) entreprise1);

            /* 1814 */ Paragraph entreprise2 = new Paragraph("" + parametrage.getLocalisation(), new Font(Font.FontFamily.TIMES_ROMAN, 9.0F, 1));
            /* 1815 */ entreprise2.setAlignment(0);

            /* 1818 */ Paragraph adresse2 = new Paragraph("B.P.: " + parametrage.getBoitePostale() + "   Tél.: " + parametrage.getContact1() + " / " + parametrage.getContact2() + "   Fax : " + parametrage.getFax(), new Font(Font.FontFamily.TIMES_ROMAN, 9.0F, 2));
            /* 1819 */ adresse2.setAlignment(1);
            /* 1820 */ document.add((Element) adresse2);

            /* 1822 */ Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            /* 1823 */ document.add((Element) ligne);

        } /* 1837 */ catch (Exception exception) {
        }
    }

    public static String printSynthesis(AnneeMois anneeMois, List<Commande> commandes, List<Depense> depenses, List<Salaire> salaires, List<Stock> stocks, String titre1) {
        String fileName = "";
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Synthese_de_" + anneeMois.getIdmois().getNom() + "_" + anneeMois.getIdannee().getNom() + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/hebdomadaire/" + fileName));
            rapport.open();
            float[] widths = {0.9F, 1.5F, 3.4F, 1.0F, 0.7F, 1.4F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            createEntete(rapport, SessionMBean.getParametrage());

            rapport.add((Element) new Paragraph(" "));

            Paragraph titre = new Paragraph(titre1, new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph periode = new Paragraph("Imprimé le : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            periode.setAlignment(1);
            rapport.add((Element) periode);

            rapport.add((Element) new Paragraph(" "));

            Double totalVente = (0.0D);
            Double totalSalaire = (0.0D);
            Double totalDepense = (0.0D);
            Double totalStock = (0.0D);

            for (Commande c : commandes) {
                totalVente += (c.getMontant() * c.getQuantite());
            }

            for (Stock s : stocks) {
                totalStock = (totalStock + s.getMontant());
            }

            for (Depense d : depenses) {
                totalDepense = (totalDepense + d.getMontant());
            }

            for (Salaire s : salaires) {
                totalSalaire += (s.getMontant());
            }

            Paragraph st = new Paragraph("Dépenses : ", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            rapport.add((Element) st);

            createLine(rapport);

            Paragraph st1 = new Paragraph("Total des approvisionnements : " + JsfUtil.formaterStringMoney(totalStock) + " ;", new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 2));
            rapport.add((Element) st1);

            Paragraph st2 = new Paragraph("Total des salaires : " + JsfUtil.formaterStringMoney(totalSalaire) + " ;", new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 2));
            rapport.add((Element) st2);

            Paragraph st3 = new Paragraph("Total des dépenses de fonctionnement : " + JsfUtil.formaterStringMoney(totalDepense) + " ;", new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 2));
            rapport.add((Element) st3);

            Paragraph st6 = new Paragraph("Totaux : " + JsfUtil.formaterStringMoney(Double.valueOf(totalDepense + totalStock + totalSalaire)), new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0));
            rapport.add((Element) st6);

            rapport.add((Element) new Paragraph(" "));

            Paragraph st8 = new Paragraph("Recettes : ", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            rapport.add((Element) st8);

            createLine(rapport);

            Paragraph st5 = new Paragraph("Total des ventes : " + JsfUtil.formaterStringMoney(totalVente) + " ;", new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 2));
            rapport.add((Element) st5);

            rapport.add((Element) new Paragraph(" "));

            createLine(rapport);

            Paragraph st9 = new Paragraph("Résulat net " + anneeMois.getIdmois().getNom() + " - " + anneeMois.getIdannee().getNom() + " : " + JsfUtil.formaterStringMoney(Double.valueOf(totalVente.doubleValue() - totalDepense.doubleValue() + totalStock.doubleValue() + totalSalaire.doubleValue())), new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 1));
            rapport.add((Element) st9);

            createLine(rapport);

            rapport.add((Element) new Paragraph(" "));

            createSignatureZone(rapport);

            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static String printSynthesisAnnuel(Annee annee, List<AnneeMois> anneeMoises, Map mapVente, Map mapAchat, Map mapSalaire, Map mapDepense, String titre1) {
        String fileName = "";
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Synthese_de_" + annee.getNom() + "_" + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/hebdomadaire/" + fileName));
            rapport.open();
            float[] widths = {0.9F, 1.5F, 3.4F, 1.0F, 0.7F, 1.4F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            createEntete(rapport, SessionMBean.getParametrage());

            rapport.add((Element) new Paragraph(" "));

            Paragraph titre = new Paragraph(titre1, new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph periode = new Paragraph("Imprimé le : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            periode.setAlignment(1);
            rapport.add((Element) periode);

            rapport.add((Element) new Paragraph(" "));

            Double totalVenteA = (0.0D);
            Double totalSalaireA = (0.0D);
            Double totalDepenseA = (0.0D);
            Double totalStockA = (0.0D);

            for (AnneeMois an : anneeMoises) {

                Paragraph mois = new Paragraph("" + Utilitaires.formatPrenomMaj(an.getIdmois().getNom()) + " " + an.getIdannee().getNom(), new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 3));
                rapport.add((Element) mois);

                createLine(rapport);

                Double double_1 = 0.0D;
                Double double_2 = 0.0D;
                Double totalDepense = 0.0D;
                Double totalStock = (0.0D);

                for (Commande c : (List<Commande>) mapVente.get(an)) {
                    double_1 += (c.getMontant() * c.getQuantite());
                    totalVenteA += (c.getMontant() * c.getQuantite());
                }

                for (Stock s : (List<Stock>) mapAchat.get(an)) {
                    totalStock += (s.getMontant());
                    totalStockA += (s.getMontant());
                }

                for (Depense d : (List<Depense>) mapDepense.get(an)) {
                    totalDepense += (d.getMontant());
                    totalDepenseA += (d.getMontant());
                }

                for (Salaire s : (List<Salaire>) mapSalaire.get(an)) {
                    double_2 += (s.getMontant());
                    totalSalaireA += (s.getMontant());
                }

                Paragraph st = new Paragraph("Dépenses : ", new Font(Font.FontFamily.TIMES_ROMAN, 13.0F, 3));
                rapport.add((Element) st);
                rapport.add((Element) new Paragraph(" "));

                Paragraph st1 = new Paragraph("Total des approvisionnements : " + JsfUtil.formaterStringMoney(totalStock) + " ;", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
                rapport.add((Element) st1);

                Paragraph st2 = new Paragraph("Total des salaires : " + JsfUtil.formaterStringMoney(double_2) + " ;", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
                rapport.add((Element) st2);

                Paragraph st3 = new Paragraph("Total des dépenses de fonctionnement : " + JsfUtil.formaterStringMoney(totalDepense) + " ;", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
                rapport.add((Element) st3);

                Paragraph st6 = new Paragraph("Totaux : " + JsfUtil.formaterStringMoney(Double.valueOf(totalDepense + totalStock + double_2)), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
                rapport.add((Element) st6);

                rapport.add((Element) new Paragraph(" "));

                Paragraph st8 = new Paragraph("Recettes : ", new Font(Font.FontFamily.TIMES_ROMAN, 13.0F, 3));
                rapport.add((Element) st8);

                Paragraph st5 = new Paragraph("Total des ventes  " + JsfUtil.formaterStringMoney(double_1) + " ; ", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
                rapport.add((Element) st5);

                rapport.add((Element) new Paragraph(" "));

                Paragraph st9 = new Paragraph("Résulat net : " + JsfUtil.formaterStringMoney((double_1 - totalDepense + totalStock + double_2)), new Font(Font.FontFamily.TIMES_ROMAN, 13.0F, 3));
                rapport.add((Element) st9);

                createLine(rapport);

                rapport.add((Element) new Paragraph(" "));
            }

            Paragraph totalVente = new Paragraph("Total des ventes " + Utilitaires.formatPrenomMaj(annee.getNom()) + " : " + JsfUtil.formaterStringMoney(totalVenteA), new Font(Font.FontFamily.TIMES_ROMAN, 13.0F, 3));
            rapport.add((Element) totalVente);

            Paragraph totalAchat = new Paragraph("Total des achats " + Utilitaires.formatPrenomMaj(annee.getNom()) + " : " + JsfUtil.formaterStringMoney(totalStockA), new Font(Font.FontFamily.TIMES_ROMAN, 13.0F, 3));
            rapport.add((Element) totalAchat);

            Paragraph totalSalaire = new Paragraph("Total des salaires " + Utilitaires.formatPrenomMaj(annee.getNom()) + " : " + JsfUtil.formaterStringMoney(totalSalaireA), new Font(Font.FontFamily.TIMES_ROMAN, 13.0F, 3));
            rapport.add((Element) totalSalaire);

            Paragraph totalAutre = new Paragraph("Total des autres dépenses  " + Utilitaires.formatPrenomMaj(annee.getNom()) + " : " + JsfUtil.formaterStringMoney(totalDepenseA), new Font(Font.FontFamily.TIMES_ROMAN, 13.0F, 3));
            rapport.add((Element) totalAutre);

            rapport.add((Element) new Paragraph(" "));

            createLine(rapport);

            Paragraph resultatNet = new Paragraph("Résultat net  " + Utilitaires.formatPrenomMaj(annee.getNom()) + " : " + JsfUtil.formaterStringMoney(Double.valueOf(totalVenteA - totalDepenseA + totalStockA + totalSalaireA)), new Font(Font.FontFamily.TIMES_ROMAN, 13.0F, 3));
            rapport.add((Element) resultatNet);

            createLine(rapport);

            rapport.add((Element) new Paragraph(" "));

            createSignatureZone(rapport);

            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }

    public static void createLine(Document document) throws DocumentException {
        Paragraph ligne = new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
        document.add((Element) ligne);
    }

    public static void createSignatureZone(Document document) throws DocumentException {
        Paragraph signature1 = new Paragraph("                                                                                        --------------Comptable------------------------Directeur-------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
        document.add((Element) signature1);

        Paragraph signature2 = new Paragraph("                                                                                        *                                                 *                                              *", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
        document.add((Element) signature2);

        Paragraph signature4 = new Paragraph("                                                                                        *                                                 *                                              *", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
        document.add((Element) signature4);

        Paragraph signature5 = new Paragraph("                                                                                        *                                                 *                                              *", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
        document.add((Element) signature5);

        Paragraph signature6 = new Paragraph("                                                                                        *                                                 *                                              *", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
        document.add((Element) signature6);

        Paragraph signature7 = new Paragraph("                                                                                        *                                                 *                                              *", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
        document.add((Element) signature7);

        Paragraph signature3 = new Paragraph("                                                                                        ----------------------------------------------------------------------------", new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
        document.add((Element) signature3);
    }

    public static String printInventoryReport(List<Produit> produits) {
        String fileName = "";
        try {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            fileName = "Fiche_inventaire_stock" + sdf.format(date) + ".pdf";
            Document rapport = new Document();
            PdfWriter.getInstance(rapport, new FileOutputStream(Utilitaires.path + "/reports/stock/" + fileName));
            rapport.open();
            float[] widths = {0.5F, 1.5F, 2.9F, 1.0F, 0.7F, 1.3F, 0.8F};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100.0F);

            createEntete(rapport, SessionMBean.getParametrage());

            rapport.add((Element) new Paragraph(" "));

            Paragraph titre = new Paragraph("FICHE D'INVENTAIRE DU STOCK", new Font(Font.FontFamily.TIMES_ROMAN, 14.0F, 5));
            titre.setAlignment(1);
            rapport.add((Element) titre);

            Paragraph periode = new Paragraph("Imprimé le : " + sdf.format(date), new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 2));
            periode.setAlignment(1);
            rapport.add((Element) periode);

            rapport.add((Element) new Paragraph(" "));

            table.addCell(createPdfPCell("N°", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("FAMILLE", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Produit", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Prix", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Qté", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Total", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));
            table.addCell(createPdfPCell("Quantité\n physique", 2, new Font(Font.FontFamily.TIMES_ROMAN, 11.0F, 0)));

            Double total = (0.0D);
            int quantite = 0;
            int compteur = 1;

            for (Produit p : produits) {
                quantite += (p.getQuantite().intValue());
                total += (p.getPrixMaximal() * p.getQuantite());
                table.addCell(createPdfPCell("" + compteur, 2, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + p.getIdfamille().getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + p.getNom(), 1, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(p.getPrixMaximal()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney(p.getQuantite()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell("" + JsfUtil.formaterStringMoney((Math.round(p.getQuantite() * p.getPrixMaximal()))), 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                table.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 10.0F, 0)));
                compteur++;
            }

            table.addCell(createPdfPCell("Totaux", 4, 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0)));
            table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney((quantite)), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell(" " + JsfUtil.formaterStringMoney(total.intValue()), 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            table.addCell(createPdfPCell(" ", 3, new Font(Font.FontFamily.TIMES_ROMAN, 12.0F, 0, BaseColor.BLUE)));
            rapport.add((Element) table);
            rapport.close();
        } catch (DocumentException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, (Throwable) ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(utils.PrintUtils.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return fileName;
    }
}
