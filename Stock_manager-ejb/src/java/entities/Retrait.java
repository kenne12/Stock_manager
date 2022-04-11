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
/*     */ @NamedQueries({@NamedQuery(name = "Retrait.findAll", query = "SELECT r FROM Retrait r"), @NamedQuery(name = "Retrait.findByIdretrait", query = "SELECT r FROM Retrait r WHERE r.idretrait = :idretrait"), @NamedQuery(name = "Retrait.findByMontant", query = "SELECT r FROM Retrait r WHERE r.montant = :montant"), @NamedQuery(name = "Retrait.findByDate", query = "SELECT r FROM Retrait r WHERE r.date = :date"), @NamedQuery(name = "Retrait.findByHeure", query = "SELECT r FROM Retrait r WHERE r.heure = :heure"), @NamedQuery(name = "Retrait.findByCommission", query = "SELECT r FROM Retrait r WHERE r.commission = :commission")})
/*     */ public class Retrait
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Long idretrait;
/*     */   private Integer montant;
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date date;
/*     */   @Temporal(TemporalType.TIME)
/*     */   private Date heure;
/*     */   private Integer commission;
/*     */   @JoinColumn(name = "idclient", referencedColumnName = "idclient")
/*     */   @ManyToOne(optional = false, fetch = FetchType.LAZY)
/*     */   private Client idclient;
/*     */   
/*     */   public Retrait() {}
/*     */   
/*     */   public Retrait(Long idretrait) {
/*  56 */     this.idretrait = idretrait;
/*     */   }
/*     */   
/*     */   public Long getIdretrait() {
/*  60 */     return this.idretrait;
/*     */   }
/*     */   
/*     */   public void setIdretrait(Long idretrait) {
/*  64 */     this.idretrait = idretrait;
/*     */   }
/*     */   
/*     */   public Integer getMontant() {
/*  68 */     return this.montant;
/*     */   }
/*     */   
/*     */   public void setMontant(Integer montant) {
/*  72 */     this.montant = montant;
/*     */   }
/*     */   
/*     */   public Date getDate() {
/*  76 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(Date date) {
/*  80 */     this.date = date;
/*     */   }
/*     */   
/*     */   public Date getHeure() {
/*  84 */     return this.heure;
/*     */   }
/*     */   
/*     */   public void setHeure(Date heure) {
/*  88 */     this.heure = heure;
/*     */   }
/*     */   
/*     */   public Integer getCommission() {
/*  92 */     return this.commission;
/*     */   }
/*     */   
/*     */   public void setCommission(Integer commission) {
/*  96 */     this.commission = commission;
/*     */   }
/*     */   
/*     */   public Client getIdclient() {
/* 100 */     return this.idclient;
/*     */   }
/*     */   
/*     */   public void setIdclient(Client idclient) {
/* 104 */     this.idclient = idclient;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 109 */     int hash = 0;
/* 110 */     hash += (this.idretrait != null) ? this.idretrait.hashCode() : 0;
/* 111 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 117 */     if (!(object instanceof Retrait)) {
/* 118 */       return false;
/*     */     }
/* 120 */     Retrait other = (Retrait)object;
/* 121 */     if ((this.idretrait == null && other.idretrait != null) || (this.idretrait != null && !this.idretrait.equals(other.idretrait))) {
/* 122 */       return false;
/*     */     }
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 129 */     return "entities.Retrait[ idretrait=" + this.idretrait + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Retrait.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */