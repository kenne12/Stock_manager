/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
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
/*     */ @NamedQueries({@NamedQuery(name = "Depense.findAll", query = "SELECT d FROM Depense d"), @NamedQuery(name = "Depense.findByIddepense", query = "SELECT d FROM Depense d WHERE d.iddepense = :iddepense"), @NamedQuery(name = "Depense.findByMotif", query = "SELECT d FROM Depense d WHERE d.motif = :motif"), @NamedQuery(name = "Depense.findByMontant", query = "SELECT d FROM Depense d WHERE d.montant = :montant"), @NamedQuery(name = "Depense.findByDateDepense", query = "SELECT d FROM Depense d WHERE d.dateDepense = :dateDepense"), @NamedQuery(name = "Depense.findByEtat", query = "SELECT d FROM Depense d WHERE d.etat = :etat"), @NamedQuery(name = "Depense.findByDepensee", query = "SELECT d FROM Depense d WHERE d.depensee = :depensee")})
/*     */ public class Depense
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Long iddepense;
/*     */   @Size(max = 2147483647)
/*     */   private String motif;
/*     */   private Double montant;
/*     */   @Column(name = "date_depense")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateDepense;
/*     */   private Boolean etat;
/*     */   private Boolean depensee;
/*     */   @JoinColumn(name = "idmois", referencedColumnName = "id_annee_mois")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private AnneeMois idmois;
/*     */   
/*     */   public Depense() {}
/*     */   
/*     */   public Depense(Long iddepense) {
/*  62 */     this.iddepense = iddepense;
/*     */   }
/*     */   
/*     */   public Long getIddepense() {
/*  66 */     return this.iddepense;
/*     */   }
/*     */   
/*     */   public void setIddepense(Long iddepense) {
/*  70 */     this.iddepense = iddepense;
/*     */   }
/*     */   
/*     */   public String getMotif() {
/*  74 */     return this.motif;
/*     */   }
/*     */   
/*     */   public void setMotif(String motif) {
/*  78 */     this.motif = motif;
/*     */   }
/*     */   
/*     */   public Double getMontant() {
/*  82 */     return this.montant;
/*     */   }
/*     */   
/*     */   public void setMontant(Double montant) {
/*  86 */     this.montant = montant;
/*     */   }
/*     */   
/*     */   public Date getDateDepense() {
/*  90 */     return this.dateDepense;
/*     */   }
/*     */   
/*     */   public void setDateDepense(Date dateDepense) {
/*  94 */     this.dateDepense = dateDepense;
/*     */   }
/*     */   
/*     */   public Boolean getEtat() {
/*  98 */     return this.etat;
/*     */   }
/*     */   
/*     */   public void setEtat(Boolean etat) {
/* 102 */     this.etat = etat;
/*     */   }
/*     */   
/*     */   public Boolean getDepensee() {
/* 106 */     return this.depensee;
/*     */   }
/*     */   
/*     */   public void setDepensee(Boolean depensee) {
/* 110 */     this.depensee = depensee;
/*     */   }
/*     */   
/*     */   public AnneeMois getIdmois() {
/* 114 */     return this.idmois;
/*     */   }
/*     */   
/*     */   public void setIdmois(AnneeMois idmois) {
/* 118 */     this.idmois = idmois;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 123 */     int hash = 0;
/* 124 */     hash += (this.iddepense != null) ? this.iddepense.hashCode() : 0;
/* 125 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 131 */     if (!(object instanceof Depense)) {
/* 132 */       return false;
/*     */     }
/* 134 */     Depense other = (Depense)object;
/* 135 */     if ((this.iddepense == null && other.iddepense != null) || (this.iddepense != null && !this.iddepense.equals(other.iddepense))) {
/* 136 */       return false;
/*     */     }
/* 138 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 143 */     return "entities.Depense[ iddepense=" + this.iddepense + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Depense.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */