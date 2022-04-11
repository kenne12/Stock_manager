/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Basic;
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
/*     */ @NamedQueries({@NamedQuery(name = "Versement.findAll", query = "SELECT v FROM Versement v"), @NamedQuery(name = "Versement.findByIdversement", query = "SELECT v FROM Versement v WHERE v.idversement = :idversement"), @NamedQuery(name = "Versement.findByMontant", query = "SELECT v FROM Versement v WHERE v.montant = :montant"), @NamedQuery(name = "Versement.findByDate", query = "SELECT v FROM Versement v WHERE v.date = :date"), @NamedQuery(name = "Versement.findByHeure", query = "SELECT v FROM Versement v WHERE v.heure = :heure"), @NamedQuery(name = "Versement.findByCode", query = "SELECT v FROM Versement v WHERE v.code = :code"), @NamedQuery(name = "Versement.findByReste", query = "SELECT v FROM Versement v WHERE v.reste = :reste")})
/*     */ public class Versement
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Long idversement;
/*     */   private Double montant;
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date date;
/*     */   @Temporal(TemporalType.TIME)
/*     */   private Date heure;
/*     */   @Size(max = 35)
/*     */   private String code;
/*     */   private Double reste;
/*     */   @JoinColumn(name = "idfacture", referencedColumnName = "idfacture")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Facture idfacture;
/*     */   
/*     */   public Versement() {}
/*     */   
/*     */   public Versement(Long idversement) {
/*  61 */     this.idversement = idversement;
/*     */   }
/*     */   
/*     */   public Long getIdversement() {
/*  65 */     return this.idversement;
/*     */   }
/*     */   
/*     */   public void setIdversement(Long idversement) {
/*  69 */     this.idversement = idversement;
/*     */   }
/*     */   
/*     */   public Double getMontant() {
/*  73 */     return this.montant;
/*     */   }
/*     */   
/*     */   public void setMontant(Double montant) {
/*  77 */     this.montant = montant;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  81 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  85 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Date getHeure() {
/*  89 */     return this.heure;
/*     */   }
/*     */   
/*     */   public void setHeure(Date heure) {
/*  93 */     this.heure = heure;
/*     */   }
/*     */   
/*     */   public String getCode() {
/*  97 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 101 */     this.code = code;
/*     */   }
/*     */   
/*     */   public Double getReste() {
/* 105 */     return this.reste;
/*     */   }
/*     */   
/*     */   public void setReste(Double reste) {
/* 109 */     this.reste = reste;
/*     */   }
/*     */   
/*     */   public Facture getIdfacture() {
/* 113 */     return this.idfacture;
/*     */   }
/*     */   
/*     */   public void setIdfacture(Facture idfacture) {
/* 117 */     this.idfacture = idfacture;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 122 */     int hash = 0;
/* 123 */     hash += (this.idversement != null) ? this.idversement.hashCode() : 0;
/* 124 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 130 */     if (!(object instanceof Versement)) {
/* 131 */       return false;
/*     */     }
/* 133 */     Versement other = (Versement)object;
/* 134 */     if ((this.idversement == null && other.idversement != null) || (this.idversement != null && !this.idversement.equals(other.idversement))) {
/* 135 */       return false;
/*     */     }
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 142 */     return "entities.Versement[ idversement=" + this.idversement + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Versement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */