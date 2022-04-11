/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
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
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Profession.findAll", query = "SELECT p FROM Profession p"), @NamedQuery(name = "Profession.findById", query = "SELECT p FROM Profession p WHERE p.id = :id"), @NamedQuery(name = "Profession.findByNom", query = "SELECT p FROM Profession p WHERE p.nom = :nom")})
/*     */ public class Profession
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Integer id;
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   @Size(min = 1, max = 2147483647)
/*     */   private String nom;
/*     */   @OneToMany(mappedBy = "idprofession", fetch = FetchType.LAZY)
/*     */   private List<Personnel> personnelList;
/*     */   
/*     */   public Profession() {}
/*     */   
/*     */   public Profession(Integer id) {
/*  49 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Profession(Integer id, String nom) {
/*  53 */     this.id = id;
/*  54 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  58 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  62 */     this.id = id;
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
/*     */   @XmlTransient
/*     */   public List<Personnel> getPersonnelList() {
/*  75 */     return this.personnelList;
/*     */   }
/*     */   
/*     */   public void setPersonnelList(List<Personnel> personnelList) {
/*  79 */     this.personnelList = personnelList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  84 */     int hash = 0;
/*  85 */     hash += (this.id != null) ? this.id.hashCode() : 0;
/*  86 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/*  92 */     if (!(object instanceof Profession)) {
/*  93 */       return false;
/*     */     }
/*  95 */     Profession other = (Profession)object;
/*  96 */     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
/*  97 */       return false;
/*     */     }
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     return "entities.Profession[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Profession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */