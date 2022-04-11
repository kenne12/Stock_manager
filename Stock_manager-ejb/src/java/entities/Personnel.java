/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Personnel.findAll", query = "SELECT p FROM Personnel p"), @NamedQuery(name = "Personnel.findByIdpersonnel", query = "SELECT p FROM Personnel p WHERE p.idpersonnel = :idpersonnel"), @NamedQuery(name = "Personnel.findByNom", query = "SELECT p FROM Personnel p WHERE p.nom = :nom"), @NamedQuery(name = "Personnel.findByPrenom", query = "SELECT p FROM Personnel p WHERE p.prenom = :prenom"), @NamedQuery(name = "Personnel.findByNumeroCni", query = "SELECT p FROM Personnel p WHERE p.numeroCni = :numeroCni"), @NamedQuery(name = "Personnel.findByAdresse", query = "SELECT p FROM Personnel p WHERE p.adresse = :adresse"), @NamedQuery(name = "Personnel.findBySalaireDefault", query = "SELECT p FROM Personnel p WHERE p.salaireDefault = :salaireDefault"), @NamedQuery(name = "Personnel.findByContact", query = "SELECT p FROM Personnel p WHERE p.contact = :contact"), @NamedQuery(name = "Personnel.findByMatricule", query = "SELECT p FROM Personnel p WHERE p.matricule = :matricule"), @NamedQuery(name = "Personnel.findByEtat", query = "SELECT p FROM Personnel p WHERE p.etat = :etat")})
/*     */ public class Personnel
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Integer idpersonnel;
/*     */   @Size(max = 2147483647)
/*     */   private String nom;
/*     */   @Size(max = 2147483647)
/*     */   private String prenom;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "numero_cni")
/*     */   private String numeroCni;
/*     */   @Size(max = 2147483647)
/*     */   private String adresse;
/*     */   @Column(name = "salaire_default")
/*     */   private Double salaireDefault;
/*     */   @Size(max = 2147483647)
/*     */   private String contact;
/*     */   @Size(max = 2147483647)
/*     */   private String matricule;
/*     */   private Boolean etat;
/*     */   @OneToMany(mappedBy = "idpersonnel", fetch = FetchType.LAZY)
/*     */   private List<Utilisateur> utilisateurList;
/*     */   @OneToMany(mappedBy = "idpersonnel", fetch = FetchType.LAZY)
/*     */   private List<Salaire> salaireList;
/*     */   @JoinColumn(name = "idprofession", referencedColumnName = "id")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Profession idprofession;
/*     */   
/*     */   public Personnel() {}
/*     */   
/*     */   public Personnel(Integer idpersonnel) {
/*  77 */     this.idpersonnel = idpersonnel;
/*     */   }
/*     */   
/*     */   public Integer getIdpersonnel() {
/*  81 */     return this.idpersonnel;
/*     */   }
/*     */   
/*     */   public void setIdpersonnel(Integer idpersonnel) {
/*  85 */     this.idpersonnel = idpersonnel;
/*     */   }
/*     */   
/*     */   public String getNom() {
/*  89 */     return this.nom;
/*     */   }
/*     */   
/*     */   public void setNom(String nom) {
/*  93 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   public String getPrenom() {
/*  97 */     return this.prenom;
/*     */   }
/*     */   
/*     */   public void setPrenom(String prenom) {
/* 101 */     this.prenom = prenom;
/*     */   }
/*     */   
/*     */   public String getNumeroCni() {
/* 105 */     return this.numeroCni;
/*     */   }
/*     */   
/*     */   public void setNumeroCni(String numeroCni) {
/* 109 */     this.numeroCni = numeroCni;
/*     */   }
/*     */   
/*     */   public String getAdresse() {
/* 113 */     return this.adresse;
/*     */   }
/*     */   
/*     */   public void setAdresse(String adresse) {
/* 117 */     this.adresse = adresse;
/*     */   }
/*     */   
/*     */   public Double getSalaireDefault() {
/* 121 */     return this.salaireDefault;
/*     */   }
/*     */   
/*     */   public void setSalaireDefault(Double salaireDefault) {
/* 125 */     this.salaireDefault = salaireDefault;
/*     */   }
/*     */   
/*     */   public String getContact() {
/* 129 */     return this.contact;
/*     */   }
/*     */   
/*     */   public void setContact(String contact) {
/* 133 */     this.contact = contact;
/*     */   }
/*     */   
/*     */   public String getMatricule() {
/* 137 */     return this.matricule;
/*     */   }
/*     */   
/*     */   public void setMatricule(String matricule) {
/* 141 */     this.matricule = matricule;
/*     */   }
/*     */   
/*     */   public Boolean getEtat() {
/* 145 */     return this.etat;
/*     */   }
/*     */   
/*     */   public void setEtat(Boolean etat) {
/* 149 */     this.etat = etat;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Utilisateur> getUtilisateurList() {
/* 154 */     return this.utilisateurList;
/*     */   }
/*     */   
/*     */   public void setUtilisateurList(List<Utilisateur> utilisateurList) {
/* 158 */     this.utilisateurList = utilisateurList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Salaire> getSalaireList() {
/* 163 */     return this.salaireList;
/*     */   }
/*     */   
/*     */   public void setSalaireList(List<Salaire> salaireList) {
/* 167 */     this.salaireList = salaireList;
/*     */   }
/*     */   
/*     */   public Profession getIdprofession() {
/* 171 */     return this.idprofession;
/*     */   }
/*     */   
/*     */   public void setIdprofession(Profession idprofession) {
/* 175 */     this.idprofession = idprofession;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 180 */     int hash = 0;
/* 181 */     hash += (this.idpersonnel != null) ? this.idpersonnel.hashCode() : 0;
/* 182 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 188 */     if (!(object instanceof Personnel)) {
/* 189 */       return false;
/*     */     }
/* 191 */     Personnel other = (Personnel)object;
/* 192 */     if ((this.idpersonnel == null && other.idpersonnel != null) || (this.idpersonnel != null && !this.idpersonnel.equals(other.idpersonnel))) {
/* 193 */       return false;
/*     */     }
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 200 */     return "entities.Personnel[ idpersonnel=" + this.idpersonnel + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Personnel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */