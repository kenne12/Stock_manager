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
/*     */ @NamedQueries({@NamedQuery(name = "Salaire.findAll", query = "SELECT s FROM Salaire s"), @NamedQuery(name = "Salaire.findByIdsalaire", query = "SELECT s FROM Salaire s WHERE s.idsalaire = :idsalaire"), @NamedQuery(name = "Salaire.findByMontant", query = "SELECT s FROM Salaire s WHERE s.montant = :montant"), @NamedQuery(name = "Salaire.findByObservation", query = "SELECT s FROM Salaire s WHERE s.observation = :observation"), @NamedQuery(name = "Salaire.findByDateSalaire", query = "SELECT s FROM Salaire s WHERE s.dateSalaire = :dateSalaire")})
/*     */ public class Salaire
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Long idsalaire;
/*     */   private Double montant;
/*     */   @Size(max = 2147483647)
/*     */   private String observation;
/*     */   @Column(name = "date_salaire")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateSalaire;
/*     */   @JoinColumn(name = "idmois", referencedColumnName = "id_annee_mois")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private AnneeMois idmois;
/*     */   @JoinColumn(name = "idpersonnel", referencedColumnName = "idpersonnel")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Personnel idpersonnel;
/*     */   
/*     */   public Salaire() {}
/*     */   
/*     */   public Salaire(Long idsalaire) {
/*  61 */     this.idsalaire = idsalaire;
/*     */   }
/*     */   
/*     */   public Long getIdsalaire() {
/*  65 */     return this.idsalaire;
/*     */   }
/*     */   
/*     */   public void setIdsalaire(Long idsalaire) {
/*  69 */     this.idsalaire = idsalaire;
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
/*     */   public String getObservation() {
/*  81 */     return this.observation;
/*     */   }
/*     */   
/*     */   public void setObservation(String observation) {
/*  85 */     this.observation = observation;
/*     */   }
/*     */   
/*     */   public Date getDateSalaire() {
/*  89 */     return this.dateSalaire;
/*     */   }
/*     */   
/*     */   public void setDateSalaire(Date dateSalaire) {
/*  93 */     this.dateSalaire = dateSalaire;
/*     */   }
/*     */   
/*     */   public AnneeMois getIdmois() {
/*  97 */     return this.idmois;
/*     */   }
/*     */   
/*     */   public void setIdmois(AnneeMois idmois) {
/* 101 */     this.idmois = idmois;
/*     */   }
/*     */   
/*     */   public Personnel getIdpersonnel() {
/* 105 */     return this.idpersonnel;
/*     */   }
/*     */   
/*     */   public void setIdpersonnel(Personnel idpersonnel) {
/* 109 */     this.idpersonnel = idpersonnel;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 114 */     int hash = 0;
/* 115 */     hash += (this.idsalaire != null) ? this.idsalaire.hashCode() : 0;
/* 116 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 122 */     if (!(object instanceof Salaire)) {
/* 123 */       return false;
/*     */     }
/* 125 */     Salaire other = (Salaire)object;
/* 126 */     if ((this.idsalaire == null && other.idsalaire != null) || (this.idsalaire != null && !this.idsalaire.equals(other.idsalaire))) {
/* 127 */       return false;
/*     */     }
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 134 */     return "entities.Salaire[ idsalaire=" + this.idsalaire + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Salaire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */