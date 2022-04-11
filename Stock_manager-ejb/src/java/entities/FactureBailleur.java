/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "facture_bailleur")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "FactureBailleur.findAll", query = "SELECT f FROM FactureBailleur f"), @NamedQuery(name = "FactureBailleur.findByIdFactureBailleur", query = "SELECT f FROM FactureBailleur f WHERE f.idFactureBailleur = :idFactureBailleur"), @NamedQuery(name = "FactureBailleur.findByDateFacturation", query = "SELECT f FROM FactureBailleur f WHERE f.dateFacturation = :dateFacturation"), @NamedQuery(name = "FactureBailleur.findByDateDebut", query = "SELECT f FROM FactureBailleur f WHERE f.dateDebut = :dateDebut"), @NamedQuery(name = "FactureBailleur.findByDateFin", query = "SELECT f FROM FactureBailleur f WHERE f.dateFin = :dateFin"), @NamedQuery(name = "FactureBailleur.findByObject", query = "SELECT f FROM FactureBailleur f WHERE f.object = :object"), @NamedQuery(name = "FactureBailleur.findByCode", query = "SELECT f FROM FactureBailleur f WHERE f.code = :code"), @NamedQuery(name = "FactureBailleur.findByMontantTotal", query = "SELECT f FROM FactureBailleur f WHERE f.montantTotal = :montantTotal"), @NamedQuery(name = "FactureBailleur.findByPourcentageDist", query = "SELECT f FROM FactureBailleur f WHERE f.pourcentageDist = :pourcentageDist"), @NamedQuery(name = "FactureBailleur.findByMontantTaxee", query = "SELECT f FROM FactureBailleur f WHERE f.montantTaxee = :montantTaxee"), @NamedQuery(name = "FactureBailleur.findByDestinataire", query = "SELECT f FROM FactureBailleur f WHERE f.destinataire = :destinataire"), @NamedQuery(name = "FactureBailleur.findByPourcentageStockage", query = "SELECT f FROM FactureBailleur f WHERE f.pourcentageStockage = :pourcentageStockage"), @NamedQuery(name = "FactureBailleur.findBySommeEnLettre", query = "SELECT f FROM FactureBailleur f WHERE f.sommeEnLettre = :sommeEnLettre"), @NamedQuery(name = "FactureBailleur.findByMontantStock", query = "SELECT f FROM FactureBailleur f WHERE f.montantStock = :montantStock")})
/*     */ public class FactureBailleur
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   @Column(name = "id_facture_bailleur")
/*     */   private Long idFactureBailleur;
/*     */   @Column(name = "date_facturation")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateFacturation;
/*     */   @Column(name = "date_debut")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateDebut;
/*     */   @Column(name = "date_fin")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateFin;
/*     */   @Size(max = 2147483647)
/*     */   private String object;
/*     */   @Size(max = 2147483647)
/*     */   private String code;
/*     */   @Column(name = "montant_total")
/*     */   private Double montantTotal;
/*     */   @Column(name = "pourcentage_dist")
/*     */   private Double pourcentageDist;
/*     */   @Column(name = "montant_taxee")
/*     */   private Double montantTaxee;
/*     */   @Size(max = 2147483647)
/*     */   private String destinataire;
/*     */   @Column(name = "pourcentage_stockage")
/*     */   private Double pourcentageStockage;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "somme_en_lettre")
/*     */   private String sommeEnLettre;
/*     */   @Column(name = "montant_stock")
/*     */   private Double montantStock;
/*     */   @OneToMany(mappedBy = "idfactureBailleur", fetch = FetchType.LAZY)
/*     */   private List<LigneFactBailleur> ligneFactBailleurList;
/*     */   @JoinColumn(name = "idmois", referencedColumnName = "id_annee_mois")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private AnneeMois idmois;
/*     */   @JoinColumn(name = "idbailleur", referencedColumnName = "idbailleur")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Bailleur idbailleur;
/*     */   @JoinColumn(name = "idfamille", referencedColumnName = "idfamille")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Famille idfamille;
/*     */   @OneToMany(mappedBy = "idfactureBailleur", fetch = FetchType.LAZY)
/*     */   private List<FactureBailleurArticle> factureBailleurArticleList;
/*     */   
/*     */   public FactureBailleur() {}
/*     */   
/*     */   public FactureBailleur(Long idFactureBailleur) {
/* 105 */     this.idFactureBailleur = idFactureBailleur;
/*     */   }
/*     */   
/*     */   public Long getIdFactureBailleur() {
/* 109 */     return this.idFactureBailleur;
/*     */   }
/*     */   
/*     */   public void setIdFactureBailleur(Long idFactureBailleur) {
/* 113 */     this.idFactureBailleur = idFactureBailleur;
/*     */   }
/*     */   
/*     */   public Date getDateFacturation() {
/* 117 */     return this.dateFacturation;
/*     */   }
/*     */   
/*     */   public void setDateFacturation(Date dateFacturation) {
/* 121 */     this.dateFacturation = dateFacturation;
/*     */   }
/*     */   
/*     */   public Date getDateDebut() {
/* 125 */     return this.dateDebut;
/*     */   }
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/* 129 */     this.dateDebut = dateDebut;
/*     */   }
/*     */   
/*     */   public Date getDateFin() {
/* 133 */     return this.dateFin;
/*     */   }
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/* 137 */     this.dateFin = dateFin;
/*     */   }
/*     */   
/*     */   public String getObject() {
/* 141 */     return this.object;
/*     */   }
/*     */   
/*     */   public void setObject(String object) {
/* 145 */     this.object = object;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 149 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 153 */     this.code = code;
/*     */   }
/*     */   
/*     */   public Double getMontantTotal() {
/* 157 */     return this.montantTotal;
/*     */   }
/*     */   
/*     */   public void setMontantTotal(Double montantTotal) {
/* 161 */     this.montantTotal = montantTotal;
/*     */   }
/*     */   
/*     */   public Double getPourcentageDist() {
/* 165 */     return this.pourcentageDist;
/*     */   }
/*     */   
/*     */   public void setPourcentageDist(Double pourcentageDist) {
/* 169 */     this.pourcentageDist = pourcentageDist;
/*     */   }
/*     */   
/*     */   public Double getMontantTaxee() {
/* 173 */     return this.montantTaxee;
/*     */   }
/*     */   
/*     */   public void setMontantTaxee(Double montantTaxee) {
/* 177 */     this.montantTaxee = montantTaxee;
/*     */   }
/*     */   
/*     */   public String getDestinataire() {
/* 181 */     return this.destinataire;
/*     */   }
/*     */   
/*     */   public void setDestinataire(String destinataire) {
/* 185 */     this.destinataire = destinataire;
/*     */   }
/*     */   
/*     */   public Double getPourcentageStockage() {
/* 189 */     return this.pourcentageStockage;
/*     */   }
/*     */   
/*     */   public void setPourcentageStockage(Double pourcentageStockage) {
/* 193 */     this.pourcentageStockage = pourcentageStockage;
/*     */   }
/*     */   
/*     */   public String getSommeEnLettre() {
/* 197 */     return this.sommeEnLettre;
/*     */   }
/*     */   
/*     */   public void setSommeEnLettre(String sommeEnLettre) {
/* 201 */     this.sommeEnLettre = sommeEnLettre;
/*     */   }
/*     */   
/*     */   public Double getMontantStock() {
/* 205 */     return this.montantStock;
/*     */   }
/*     */   
/*     */   public void setMontantStock(Double montantStock) {
/* 209 */     this.montantStock = montantStock;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<LigneFactBailleur> getLigneFactBailleurList() {
/* 214 */     return this.ligneFactBailleurList;
/*     */   }
/*     */   
/*     */   public void setLigneFactBailleurList(List<LigneFactBailleur> ligneFactBailleurList) {
/* 218 */     this.ligneFactBailleurList = ligneFactBailleurList;
/*     */   }
/*     */   
/*     */   public AnneeMois getIdmois() {
/* 222 */     return this.idmois;
/*     */   }
/*     */   
/*     */   public void setIdmois(AnneeMois idmois) {
/* 226 */     this.idmois = idmois;
/*     */   }
/*     */   
/*     */   public Bailleur getIdbailleur() {
/* 230 */     return this.idbailleur;
/*     */   }
/*     */   
/*     */   public void setIdbailleur(Bailleur idbailleur) {
/* 234 */     this.idbailleur = idbailleur;
/*     */   }
/*     */   
/*     */   public Famille getIdfamille() {
/* 238 */     return this.idfamille;
/*     */   }
/*     */   
/*     */   public void setIdfamille(Famille idfamille) {
/* 242 */     this.idfamille = idfamille;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<FactureBailleurArticle> getFactureBailleurArticleList() {
/* 247 */     return this.factureBailleurArticleList;
/*     */   }
/*     */   
/*     */   public void setFactureBailleurArticleList(List<FactureBailleurArticle> factureBailleurArticleList) {
/* 251 */     this.factureBailleurArticleList = factureBailleurArticleList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 256 */     int hash = 0;
/* 257 */     hash += (this.idFactureBailleur != null) ? this.idFactureBailleur.hashCode() : 0;
/* 258 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 264 */     if (!(object instanceof FactureBailleur)) {
/* 265 */       return false;
/*     */     }
/* 267 */     FactureBailleur other = (FactureBailleur)object;
/* 268 */     if ((this.idFactureBailleur == null && other.idFactureBailleur != null) || (this.idFactureBailleur != null && !this.idFactureBailleur.equals(other.idFactureBailleur))) {
/* 269 */       return false;
/*     */     }
/* 271 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 276 */     return "entities.FactureBailleur[ idFactureBailleur=" + this.idFactureBailleur + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\FactureBailleur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */