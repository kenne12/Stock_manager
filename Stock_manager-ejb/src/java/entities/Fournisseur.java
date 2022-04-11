/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ import javax.xml.bind.annotation.XmlTransient;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Fournisseur.findAll", query = "SELECT f FROM Fournisseur f"), @NamedQuery(name = "Fournisseur.findByIdfournisseur", query = "SELECT f FROM Fournisseur f WHERE f.idfournisseur = :idfournisseur"), @NamedQuery(name = "Fournisseur.findByRaisonSociale", query = "SELECT f FROM Fournisseur f WHERE f.raisonSociale = :raisonSociale"), @NamedQuery(name = "Fournisseur.findByContact1", query = "SELECT f FROM Fournisseur f WHERE f.contact1 = :contact1"), @NamedQuery(name = "Fournisseur.findByContact2", query = "SELECT f FROM Fournisseur f WHERE f.contact2 = :contact2"), @NamedQuery(name = "Fournisseur.findByFax", query = "SELECT f FROM Fournisseur f WHERE f.fax = :fax"), @NamedQuery(name = "Fournisseur.findByAdresse", query = "SELECT f FROM Fournisseur f WHERE f.adresse = :adresse"), @NamedQuery(name = "Fournisseur.findByEmail", query = "SELECT f FROM Fournisseur f WHERE f.email = :email"), @NamedQuery(name = "Fournisseur.findBySiteWeb", query = "SELECT f FROM Fournisseur f WHERE f.siteWeb = :siteWeb")})
/*     */ public class Fournisseur
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Integer idfournisseur;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "raison_sociale")
/*     */   private String raisonSociale;
/*     */   @Size(max = 2147483647)
/*     */   private String contact1;
/*     */   @Size(max = 2147483647)
/*     */   private String contact2;
/*     */   @Size(max = 2147483647)
/*     */   private String fax;
/*     */   @Size(max = 2147483647)
/*     */   private String adresse;
/*     */   @Size(max = 2147483647)
/*     */   private String email;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "site_web")
/*     */   private String siteWeb;
/*     */   @OneToMany(mappedBy = "idfournisseur", fetch = FetchType.LAZY)
/*     */   private List<CommandeFournisseur> commandeFournisseurList;
/*     */   @OneToMany(mappedBy = "idfournisseur", fetch = FetchType.LAZY)
/*     */   private List<Stock> stockList;
/*     */   
/*     */   public Fournisseur() {}
/*     */   
/*     */   public Fournisseur(Integer idfournisseur) {
/*  72 */     this.idfournisseur = idfournisseur;
/*     */   }
/*     */   
/*     */   public Integer getIdfournisseur() {
/*  76 */     return this.idfournisseur;
/*     */   }
/*     */   
/*     */   public void setIdfournisseur(Integer idfournisseur) {
/*  80 */     this.idfournisseur = idfournisseur;
/*     */   }
/*     */   
/*     */   public String getRaisonSociale() {
/*  84 */     return this.raisonSociale;
/*     */   }
/*     */   
/*     */   public void setRaisonSociale(String raisonSociale) {
/*  88 */     this.raisonSociale = raisonSociale;
/*     */   }
/*     */   
/*     */   public String getContact1() {
/*  92 */     return this.contact1;
/*     */   }
/*     */   
/*     */   public void setContact1(String contact1) {
/*  96 */     this.contact1 = contact1;
/*     */   }
/*     */   
/*     */   public String getContact2() {
/* 100 */     return this.contact2;
/*     */   }
/*     */   
/*     */   public void setContact2(String contact2) {
/* 104 */     this.contact2 = contact2;
/*     */   }
/*     */   
/*     */   public String getFax() {
/* 108 */     return this.fax;
/*     */   }
/*     */   
/*     */   public void setFax(String fax) {
/* 112 */     this.fax = fax;
/*     */   }
/*     */   
/*     */   public String getAdresse() {
/* 116 */     return this.adresse;
/*     */   }
/*     */   
/*     */   public void setAdresse(String adresse) {
/* 120 */     this.adresse = adresse;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/* 124 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/* 128 */     this.email = email;
/*     */   }
/*     */   
/*     */   public String getSiteWeb() {
/* 132 */     return this.siteWeb;
/*     */   }
/*     */   
/*     */   public void setSiteWeb(String siteWeb) {
/* 136 */     this.siteWeb = siteWeb;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<CommandeFournisseur> getCommandeFournisseurList() {
/* 141 */     return this.commandeFournisseurList;
/*     */   }
/*     */   
/*     */   public void setCommandeFournisseurList(List<CommandeFournisseur> commandeFournisseurList) {
/* 145 */     this.commandeFournisseurList = commandeFournisseurList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Stock> getStockList() {
/* 150 */     return this.stockList;
/*     */   }
/*     */   
/*     */   public void setStockList(List<Stock> stockList) {
/* 154 */     this.stockList = stockList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 159 */     int hash = 0;
/* 160 */     hash += (this.idfournisseur != null) ? this.idfournisseur.hashCode() : 0;
/* 161 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 167 */     if (!(object instanceof Fournisseur)) {
/* 168 */       return false;
/*     */     }
/* 170 */     Fournisseur other = (Fournisseur)object;
/* 171 */     if ((this.idfournisseur == null && other.idfournisseur != null) || (this.idfournisseur != null && !this.idfournisseur.equals(other.idfournisseur))) {
/* 172 */       return false;
/*     */     }
/* 174 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 179 */     return "entities.Fournisseur[ idfournisseur=" + this.idfournisseur + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Fournisseur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */