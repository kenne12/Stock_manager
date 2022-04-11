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
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Recette.findAll", query = "SELECT r FROM Recette r"), @NamedQuery(name = "Recette.findByIdrecette", query = "SELECT r FROM Recette r WHERE r.idrecette = :idrecette"), @NamedQuery(name = "Recette.findByMotif", query = "SELECT r FROM Recette r WHERE r.motif = :motif"), @NamedQuery(name = "Recette.findByMontant", query = "SELECT r FROM Recette r WHERE r.montant = :montant"), @NamedQuery(name = "Recette.findByDateRecette", query = "SELECT r FROM Recette r WHERE r.dateRecette = :dateRecette")})
/*     */ public class Recette
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Long idrecette;
/*     */   @Size(max = 2147483647)
/*     */   private String motif;
/*     */   private Double montant;
/*     */   @Column(name = "date_recette")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateRecette;
/*     */   @JoinColumn(name = "idmois", referencedColumnName = "id_annee_mois")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private AnneeMois idmois;
/*     */   
/*     */   public Recette() {}
/*     */   
/*     */   public Recette(Long idrecette) {
/*  58 */     this.idrecette = idrecette;
/*     */   }
/*     */   
/*     */   public Long getIdrecette() {
/*  62 */     return this.idrecette;
/*     */   }
/*     */   
/*     */   public void setIdrecette(Long idrecette) {
/*  66 */     this.idrecette = idrecette;
/*     */   }
/*     */   
/*     */   public String getMotif() {
/*  70 */     return this.motif;
/*     */   }
/*     */   
/*     */   public void setMotif(String motif) {
/*  74 */     this.motif = motif;
/*     */   }
/*     */   
/*     */   public Double getMontant() {
/*  78 */     return this.montant;
/*     */   }
/*     */   
/*     */   public void setMontant(Double montant) {
/*  82 */     this.montant = montant;
/*     */   }
/*     */   
/*     */   public Date getDateRecette() {
/*  86 */     return this.dateRecette;
/*     */   }
/*     */   
/*     */   public void setDateRecette(Date dateRecette) {
/*  90 */     this.dateRecette = dateRecette;
/*     */   }
/*     */   
/*     */   public AnneeMois getIdmois() {
/*  94 */     return this.idmois;
/*     */   }
/*     */   
/*     */   public void setIdmois(AnneeMois idmois) {
/*  98 */     this.idmois = idmois;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 103 */     int hash = 0;
/* 104 */     hash += (this.idrecette != null) ? this.idrecette.hashCode() : 0;
/* 105 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 111 */     if (!(object instanceof Recette)) {
/* 112 */       return false;
/*     */     }
/* 114 */     Recette other = (Recette)object;
/* 115 */     if ((this.idrecette == null && other.idrecette != null) || (this.idrecette != null && !this.idrecette.equals(other.idrecette))) {
/* 116 */       return false;
/*     */     }
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 123 */     return "entities.Recette[ idrecette=" + this.idrecette + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Recette.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */