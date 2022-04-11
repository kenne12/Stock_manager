/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.CascadeType;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
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
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"), @NamedQuery(name = "Utilisateur.findByIdutilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idutilisateur = :idutilisateur"), @NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom"), @NamedQuery(name = "Utilisateur.findByPrenom", query = "SELECT u FROM Utilisateur u WHERE u.prenom = :prenom"), @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT u FROM Utilisateur u WHERE u.login = :login"), @NamedQuery(name = "Utilisateur.findByPassword", query = "SELECT u FROM Utilisateur u WHERE u.password = :password"), @NamedQuery(name = "Utilisateur.findByPhoto", query = "SELECT u FROM Utilisateur u WHERE u.photo = :photo"), @NamedQuery(name = "Utilisateur.findByActif", query = "SELECT u FROM Utilisateur u WHERE u.actif = :actif"), @NamedQuery(name = "Utilisateur.findByTemplate", query = "SELECT u FROM Utilisateur u WHERE u.template = :template"), @NamedQuery(name = "Utilisateur.findByTheme", query = "SELECT u FROM Utilisateur u WHERE u.theme = :theme"), @NamedQuery(name = "Utilisateur.findByDatecreation", query = "SELECT u FROM Utilisateur u WHERE u.datecreation = :datecreation"), @NamedQuery(name = "Utilisateur.findByDateDerniereConnexion", query = "SELECT u FROM Utilisateur u WHERE u.dateDerniereConnexion = :dateDerniereConnexion"), @NamedQuery(name = "Utilisateur.findByHeureDerniereConnexion", query = "SELECT u FROM Utilisateur u WHERE u.heureDerniereConnexion = :heureDerniereConnexion")})
/*     */ public class Utilisateur
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Integer idutilisateur;
/*     */   @Size(max = 254)
/*     */   private String nom;
/*     */   @Size(max = 254)
/*     */   private String prenom;
/*     */   @Size(max = 254)
/*     */   private String login;
/*     */   @Size(max = 254)
/*     */   private String password;
/*     */   @Size(max = 2147483647)
/*     */   private String photo;
/*     */   private Boolean actif;
/*     */   @Size(max = 2147483647)
/*     */   private String template;
/*     */   @Size(max = 2147483647)
/*     */   private String theme;
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date datecreation;
/*     */   @Column(name = "date_derniere_connexion")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateDerniereConnexion;
/*     */   @Column(name = "heure_derniere_connexion")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date heureDerniereConnexion;
/*     */   @OneToMany(mappedBy = "idutilisateurOuverture", fetch = FetchType.LAZY)
/*     */   private List<Journee> journeeList;
/*     */   @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "idutilisateur", fetch = FetchType.LAZY)
/*     */   private List<Privilege> privilegeList;
/*     */   @JoinColumn(name = "idpersonnel", referencedColumnName = "idpersonnel")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Personnel idpersonnel;
/*     */   @OneToMany(mappedBy = "idutilisateur", fetch = FetchType.LAZY)
/*     */   private List<Facture> factureList;
/*     */   @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "idutilisateur", fetch = FetchType.LAZY)
/*     */   private List<Mouchard> mouchardList;
/*     */   
/*     */   public Utilisateur() {}
/*     */   
/*     */   public Utilisateur(Integer idutilisateur) {
/*  94 */     this.idutilisateur = idutilisateur;
/*     */   }
/*     */   
/*     */   public Integer getIdutilisateur() {
/*  98 */     return this.idutilisateur;
/*     */   }
/*     */   
/*     */   public void setIdutilisateur(Integer idutilisateur) {
/* 102 */     this.idutilisateur = idutilisateur;
/*     */   }
/*     */   
/*     */   public String getNom() {
/* 106 */     return this.nom;
/*     */   }
/*     */   
/*     */   public void setNom(String nom) {
/* 110 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   public String getPrenom() {
/* 114 */     return this.prenom;
/*     */   }
/*     */   
/*     */   public void setPrenom(String prenom) {
/* 118 */     this.prenom = prenom;
/*     */   }
/*     */   
/*     */   public String getLogin() {
/* 122 */     return this.login;
/*     */   }
/*     */   
/*     */   public void setLogin(String login) {
/* 126 */     this.login = login;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/* 130 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/* 134 */     this.password = password;
/*     */   }
/*     */   
/*     */   public String getPhoto() {
/* 138 */     return this.photo;
/*     */   }
/*     */   
/*     */   public void setPhoto(String photo) {
/* 142 */     this.photo = photo;
/*     */   }
/*     */   
/*     */   public Boolean getActif() {
/* 146 */     return this.actif;
/*     */   }
/*     */   
/*     */   public void setActif(Boolean actif) {
/* 150 */     this.actif = actif;
/*     */   }
/*     */   
/*     */   public String getTemplate() {
/* 154 */     return this.template;
/*     */   }
/*     */   
/*     */   public void setTemplate(String template) {
/* 158 */     this.template = template;
/*     */   }
/*     */   
/*     */   public String getTheme() {
/* 162 */     return this.theme;
/*     */   }
/*     */   
/*     */   public void setTheme(String theme) {
/* 166 */     this.theme = theme;
/*     */   }
/*     */   
/*     */   public Date getDatecreation() {
/* 170 */     return this.datecreation;
/*     */   }
/*     */   
/*     */   public void setDatecreation(Date datecreation) {
/* 174 */     this.datecreation = datecreation;
/*     */   }
/*     */   
/*     */   public Date getDateDerniereConnexion() {
/* 178 */     return this.dateDerniereConnexion;
/*     */   }
/*     */   
/*     */   public void setDateDerniereConnexion(Date dateDerniereConnexion) {
/* 182 */     this.dateDerniereConnexion = dateDerniereConnexion;
/*     */   }
/*     */   
/*     */   public Date getHeureDerniereConnexion() {
/* 186 */     return this.heureDerniereConnexion;
/*     */   }
/*     */   
/*     */   public void setHeureDerniereConnexion(Date heureDerniereConnexion) {
/* 190 */     this.heureDerniereConnexion = heureDerniereConnexion;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Journee> getJourneeList() {
/* 195 */     return this.journeeList;
/*     */   }
/*     */   
/*     */   public void setJourneeList(List<Journee> journeeList) {
/* 199 */     this.journeeList = journeeList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Privilege> getPrivilegeList() {
/* 204 */     return this.privilegeList;
/*     */   }
/*     */   
/*     */   public void setPrivilegeList(List<Privilege> privilegeList) {
/* 208 */     this.privilegeList = privilegeList;
/*     */   }
/*     */   
/*     */   public Personnel getIdpersonnel() {
/* 212 */     return this.idpersonnel;
/*     */   }
/*     */   
/*     */   public void setIdpersonnel(Personnel idpersonnel) {
/* 216 */     this.idpersonnel = idpersonnel;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Facture> getFactureList() {
/* 221 */     return this.factureList;
/*     */   }
/*     */   
/*     */   public void setFactureList(List<Facture> factureList) {
/* 225 */     this.factureList = factureList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Mouchard> getMouchardList() {
/* 230 */     return this.mouchardList;
/*     */   }
/*     */   
/*     */   public void setMouchardList(List<Mouchard> mouchardList) {
/* 234 */     this.mouchardList = mouchardList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 239 */     int hash = 0;
/* 240 */     hash += (this.idutilisateur != null) ? this.idutilisateur.hashCode() : 0;
/* 241 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 247 */     if (!(object instanceof Utilisateur)) {
/* 248 */       return false;
/*     */     }
/* 250 */     Utilisateur other = (Utilisateur)object;
/* 251 */     if ((this.idutilisateur == null && other.idutilisateur != null) || (this.idutilisateur != null && !this.idutilisateur.equals(other.idutilisateur))) {
/* 252 */       return false;
/*     */     }
/* 254 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 259 */     return "entities.Utilisateur[ idutilisateur=" + this.idutilisateur + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Utilisateur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */