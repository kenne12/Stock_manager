/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
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
/*     */ @NamedQueries({@NamedQuery(name = "Boutique.findAll", query = "SELECT b FROM Boutique b"), @NamedQuery(name = "Boutique.findByIdboutique", query = "SELECT b FROM Boutique b WHERE b.idboutique = :idboutique"), @NamedQuery(name = "Boutique.findByNom", query = "SELECT b FROM Boutique b WHERE b.nom = :nom"), @NamedQuery(name = "Boutique.findByAdresse", query = "SELECT b FROM Boutique b WHERE b.adresse = :adresse"), @NamedQuery(name = "Boutique.findByContact", query = "SELECT b FROM Boutique b WHERE b.contact = :contact"), @NamedQuery(name = "Boutique.findByContact2", query = "SELECT b FROM Boutique b WHERE b.contact2 = :contact2"), @NamedQuery(name = "Boutique.findByFax", query = "SELECT b FROM Boutique b WHERE b.fax = :fax")})
/*     */ public class Boutique
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Integer idboutique;
/*     */   @Size(max = 2147483647)
/*     */   private String nom;
/*     */   @Size(max = 2147483647)
/*     */   private String adresse;
/*     */   @Size(max = 2147483647)
/*     */   private String contact;
/*     */   @Size(max = 2147483647)
/*     */   private String contact2;
/*     */   @Size(max = 2147483647)
/*     */   private String fax;
/*     */   
/*     */   public Boutique() {}
/*     */   
/*     */   public Boutique(Integer idboutique) {
/*  54 */     this.idboutique = idboutique;
/*     */   }
/*     */   
/*     */   public Integer getIdboutique() {
/*  58 */     return this.idboutique;
/*     */   }
/*     */   
/*     */   public void setIdboutique(Integer idboutique) {
/*  62 */     this.idboutique = idboutique;
/*     */   }
/*     */   
/*     */   public String getNom() {
/*  66 */     return this.nom;
/*     */   }
/*     */   
/*     */   public void setNom(String nom) {
/*  70 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   public String getAdresse() {
/*  74 */     return this.adresse;
/*     */   }
/*     */   
/*     */   public void setAdresse(String adresse) {
/*  78 */     this.adresse = adresse;
/*     */   }
/*     */   
/*     */   public String getContact() {
/*  82 */     return this.contact;
/*     */   }
/*     */   
/*     */   public void setContact(String contact) {
/*  86 */     this.contact = contact;
/*     */   }
/*     */   
/*     */   public String getContact2() {
/*  90 */     return this.contact2;
/*     */   }
/*     */   
/*     */   public void setContact2(String contact2) {
/*  94 */     this.contact2 = contact2;
/*     */   }
/*     */   
/*     */   public String getFax() {
/*  98 */     return this.fax;
/*     */   }
/*     */   
/*     */   public void setFax(String fax) {
/* 102 */     this.fax = fax;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 107 */     int hash = 0;
/* 108 */     hash += (this.idboutique != null) ? this.idboutique.hashCode() : 0;
/* 109 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 115 */     if (!(object instanceof Boutique)) {
/* 116 */       return false;
/*     */     }
/* 118 */     Boutique other = (Boutique)object;
/* 119 */     if ((this.idboutique == null && other.idboutique != null) || (this.idboutique != null && !this.idboutique.equals(other.idboutique))) {
/* 120 */       return false;
/*     */     }
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 127 */     return "entities.Boutique[ idboutique=" + this.idboutique + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Boutique.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */