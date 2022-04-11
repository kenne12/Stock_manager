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
/*     */ import javax.persistence.Table;
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
/*     */ @Entity
/*     */ @Table(name = "user_p")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "UserP.findAll", query = "SELECT u FROM UserP u"), @NamedQuery(name = "UserP.findByIduserP", query = "SELECT u FROM UserP u WHERE u.iduserP = :iduserP"), @NamedQuery(name = "UserP.findByNom", query = "SELECT u FROM UserP u WHERE u.nom = :nom")})
/*     */ public class UserP
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   @Column(name = "iduser_p")
/*     */   private Integer iduserP;
/*     */   @Size(max = 2147483647)
/*     */   private String nom;
/*     */   @OneToMany(mappedBy = "iduserP", fetch = FetchType.LAZY)
/*     */   private List<Lot> lotList;
/*     */   
/*     */   public UserP() {}
/*     */   
/*     */   public UserP(Integer iduserP) {
/*  51 */     this.iduserP = iduserP;
/*     */   }
/*     */   
/*     */   public Integer getIduserP() {
/*  55 */     return this.iduserP;
/*     */   }
/*     */   
/*     */   public void setIduserP(Integer iduserP) {
/*  59 */     this.iduserP = iduserP;
/*     */   }
/*     */   
/*     */   public String getNom() {
/*  63 */     return this.nom;
/*     */   }
/*     */   
/*     */   public void setNom(String nom) {
/*  67 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Lot> getLotList() {
/*  72 */     return this.lotList;
/*     */   }
/*     */   
/*     */   public void setLotList(List<Lot> lotList) {
/*  76 */     this.lotList = lotList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  81 */     int hash = 0;
/*  82 */     hash += (this.iduserP != null) ? this.iduserP.hashCode() : 0;
/*  83 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/*  89 */     if (!(object instanceof UserP)) {
/*  90 */       return false;
/*     */     }
/*  92 */     UserP other = (UserP)object;
/*  93 */     if ((this.iduserP == null && other.iduserP != null) || (this.iduserP != null && !this.iduserP.equals(other.iduserP))) {
/*  94 */       return false;
/*     */     }
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 101 */     return "entities.UserP[ iduserP=" + this.iduserP + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\UserP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */