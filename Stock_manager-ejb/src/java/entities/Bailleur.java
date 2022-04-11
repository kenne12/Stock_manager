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
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Bailleur.findAll", query = "SELECT b FROM Bailleur b"), @NamedQuery(name = "Bailleur.findByIdbailleur", query = "SELECT b FROM Bailleur b WHERE b.idbailleur = :idbailleur"), @NamedQuery(name = "Bailleur.findByNom", query = "SELECT b FROM Bailleur b WHERE b.nom = :nom"), @NamedQuery(name = "Bailleur.findByPrctageStockage", query = "SELECT b FROM Bailleur b WHERE b.prctageStockage = :prctageStockage"), @NamedQuery(name = "Bailleur.findByPrctageDistribution", query = "SELECT b FROM Bailleur b WHERE b.prctageDistribution = :prctageDistribution")})
/*     */ public class Bailleur
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Integer idbailleur;
/*     */   @Size(max = 2147483647)
/*     */   private String nom;
/*     */   @Column(name = "prctage_stockage")
/*     */   private Double prctageStockage;
/*     */   @Column(name = "prctage_distribution")
/*     */   private Double prctageDistribution;
/*     */   @OneToMany(mappedBy = "idbailleur", fetch = FetchType.LAZY)
/*     */   private List<Lot> lotList;
/*     */   @OneToMany(mappedBy = "idbailleur", fetch = FetchType.LAZY)
/*     */   private List<FactureBailleur> factureBailleurList;
/*     */   
/*     */   public Bailleur() {}
/*     */   
/*     */   public Bailleur(Integer idbailleur) {
/*  57 */     this.idbailleur = idbailleur;
/*     */   }
/*     */   
/*     */   public Integer getIdbailleur() {
/*  61 */     return this.idbailleur;
/*     */   }
/*     */   
/*     */   public void setIdbailleur(Integer idbailleur) {
/*  65 */     this.idbailleur = idbailleur;
/*     */   }
/*     */   
/*     */   public String getNom() {
/*  69 */     return this.nom;
/*     */   }
/*     */   
/*     */   public void setNom(String nom) {
/*  73 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   public Double getPrctageStockage() {
/*  77 */     return this.prctageStockage;
/*     */   }
/*     */   
/*     */   public void setPrctageStockage(Double prctageStockage) {
/*  81 */     this.prctageStockage = prctageStockage;
/*     */   }
/*     */   
/*     */   public Double getPrctageDistribution() {
/*  85 */     return this.prctageDistribution;
/*     */   }
/*     */   
/*     */   public void setPrctageDistribution(Double prctageDistribution) {
/*  89 */     this.prctageDistribution = prctageDistribution;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Lot> getLotList() {
/*  94 */     return this.lotList;
/*     */   }
/*     */   
/*     */   public void setLotList(List<Lot> lotList) {
/*  98 */     this.lotList = lotList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<FactureBailleur> getFactureBailleurList() {
/* 103 */     return this.factureBailleurList;
/*     */   }
/*     */   
/*     */   public void setFactureBailleurList(List<FactureBailleur> factureBailleurList) {
/* 107 */     this.factureBailleurList = factureBailleurList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 112 */     int hash = 0;
/* 113 */     hash += (this.idbailleur != null) ? this.idbailleur.hashCode() : 0;
/* 114 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 120 */     if (!(object instanceof Bailleur)) {
/* 121 */       return false;
/*     */     }
/* 123 */     Bailleur other = (Bailleur)object;
/* 124 */     if ((this.idbailleur == null && other.idbailleur != null) || (this.idbailleur != null && !this.idbailleur.equals(other.idbailleur))) {
/* 125 */       return false;
/*     */     }
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 132 */     return "entities.Bailleur[ idbailleur=" + this.idbailleur + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Bailleur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */