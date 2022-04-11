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
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Mouchard.findAll", query = "SELECT m FROM Mouchard m"), @NamedQuery(name = "Mouchard.findByIdmouchard", query = "SELECT m FROM Mouchard m WHERE m.idmouchard = :idmouchard"), @NamedQuery(name = "Mouchard.findByAction", query = "SELECT m FROM Mouchard m WHERE m.action = :action"), @NamedQuery(name = "Mouchard.findByDate", query = "SELECT m FROM Mouchard m WHERE m.date = :date"), @NamedQuery(name = "Mouchard.findByHeure", query = "SELECT m FROM Mouchard m WHERE m.heure = :heure")})
/*     */ public class Mouchard
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Long idmouchard;
/*     */   @Size(max = 254)
/*     */   private String action;
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date date;
/*     */   @Temporal(TemporalType.TIME)
/*     */   private Date heure;
/*     */   @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
/*     */   @ManyToOne(optional = false, fetch = FetchType.LAZY)
/*     */   private Utilisateur idutilisateur;
/*     */   
/*     */   public Mouchard() {}
/*     */   
/*     */   public Mouchard(Long idmouchard) {
/*  56 */     this.idmouchard = idmouchard;
/*     */   }
/*     */   
/*     */   public Long getIdmouchard() {
/*  60 */     return this.idmouchard;
/*     */   }
/*     */   
/*     */   public void setIdmouchard(Long idmouchard) {
/*  64 */     this.idmouchard = idmouchard;
/*     */   }
/*     */   
/*     */   public String getAction() {
/*  68 */     return this.action;
/*     */   }
/*     */   
/*     */   public void setAction(String action) {
/*  72 */     this.action = action;
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
/*     */   public Utilisateur getIdutilisateur() {
/*  92 */     return this.idutilisateur;
/*     */   }
/*     */   
/*     */   public void setIdutilisateur(Utilisateur idutilisateur) {
/*  96 */     this.idutilisateur = idutilisateur;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 101 */     int hash = 0;
/* 102 */     hash += (this.idmouchard != null) ? this.idmouchard.hashCode() : 0;
/* 103 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 109 */     if (!(object instanceof Mouchard)) {
/* 110 */       return false;
/*     */     }
/* 112 */     Mouchard other = (Mouchard)object;
/* 113 */     if ((this.idmouchard == null && other.idmouchard != null) || (this.idmouchard != null && !this.idmouchard.equals(other.idmouchard))) {
/* 114 */       return false;
/*     */     }
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 121 */     return "entities.Mouchard[ idmouchard=" + this.idmouchard + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Mouchard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */