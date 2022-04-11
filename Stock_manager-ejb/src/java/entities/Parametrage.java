/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
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
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Parametrage.findAll", query = "SELECT p FROM Parametrage p"), @NamedQuery(name = "Parametrage.findById", query = "SELECT p FROM Parametrage p WHERE p.id = :id"), @NamedQuery(name = "Parametrage.findByNomStructure", query = "SELECT p FROM Parametrage p WHERE p.nomStructure = :nomStructure"), @NamedQuery(name = "Parametrage.findByBoitePostale", query = "SELECT p FROM Parametrage p WHERE p.boitePostale = :boitePostale"), @NamedQuery(name = "Parametrage.findByContact1", query = "SELECT p FROM Parametrage p WHERE p.contact1 = :contact1"), @NamedQuery(name = "Parametrage.findByContact2", query = "SELECT p FROM Parametrage p WHERE p.contact2 = :contact2"), @NamedQuery(name = "Parametrage.findByContact3", query = "SELECT p FROM Parametrage p WHERE p.contact3 = :contact3"), @NamedQuery(name = "Parametrage.findByDescriptif", query = "SELECT p FROM Parametrage p WHERE p.descriptif = :descriptif"), @NamedQuery(name = "Parametrage.findByRcm", query = "SELECT p FROM Parametrage p WHERE p.rcm = :rcm"), @NamedQuery(name = "Parametrage.findByNoContrib", query = "SELECT p FROM Parametrage p WHERE p.noContrib = :noContrib"), @NamedQuery(name = "Parametrage.findByLocalisation", query = "SELECT p FROM Parametrage p WHERE p.localisation = :localisation"), @NamedQuery(name = "Parametrage.findByRepertoireLogo", query = "SELECT p FROM Parametrage p WHERE p.repertoireLogo = :repertoireLogo"), @NamedQuery(name = "Parametrage.findByLogo", query = "SELECT p FROM Parametrage p WHERE p.logo = :logo"), @NamedQuery(name = "Parametrage.findByRepertoireSauvegardre", query = "SELECT p FROM Parametrage p WHERE p.repertoireSauvegardre = :repertoireSauvegardre"), @NamedQuery(name = "Parametrage.findByTauxTva", query = "SELECT p FROM Parametrage p WHERE p.tauxTva = :tauxTva"), @NamedQuery(name = "Parametrage.findByCapital", query = "SELECT p FROM Parametrage p WHERE p.capital = :capital"), @NamedQuery(name = "Parametrage.findByTauxRemise", query = "SELECT p FROM Parametrage p WHERE p.tauxRemise = :tauxRemise"), @NamedQuery(name = "Parametrage.findByCheminTemplate", query = "SELECT p FROM Parametrage p WHERE p.cheminTemplate = :cheminTemplate"), @NamedQuery(name = "Parametrage.findByEtatQuantiteDosage", query = "SELECT p FROM Parametrage p WHERE p.etatQuantiteDosage = :etatQuantiteDosage"), @NamedQuery(name = "Parametrage.findByEtatFormeProduit", query = "SELECT p FROM Parametrage p WHERE p.etatFormeProduit = :etatFormeProduit"), @NamedQuery(name = "Parametrage.findByEtatFormeStockage", query = "SELECT p FROM Parametrage p WHERE p.etatFormeStockage = :etatFormeStockage"), @NamedQuery(name = "Parametrage.findByCalcultva", query = "SELECT p FROM Parametrage p WHERE p.calcultva = :calcultva"), @NamedQuery(name = "Parametrage.findByCalculRemise", query = "SELECT p FROM Parametrage p WHERE p.calculRemise = :calculRemise"), @NamedQuery(name = "Parametrage.findByFax", query = "SELECT p FROM Parametrage p WHERE p.fax = :fax"), @NamedQuery(name = "Parametrage.findByNbreJrAlertePeremption", query = "SELECT p FROM Parametrage p WHERE p.nbreJrAlertePeremption = :nbreJrAlertePeremption"), @NamedQuery(name = "Parametrage.findByPourcentageBailleur", query = "SELECT p FROM Parametrage p WHERE p.pourcentageBailleur = :pourcentageBailleur"), @NamedQuery(name = "Parametrage.findByBanque", query = "SELECT p FROM Parametrage p WHERE p.banque = :banque"), @NamedQuery(name = "Parametrage.findByNumeroCompte", query = "SELECT p FROM Parametrage p WHERE p.numeroCompte = :numeroCompte"), @NamedQuery(name = "Parametrage.findByEtatbailleur", query = "SELECT p FROM Parametrage p WHERE p.etatbailleur = :etatbailleur"), @NamedQuery(name = "Parametrage.findByEtatuser", query = "SELECT p FROM Parametrage p WHERE p.etatuser = :etatuser")})
/*     */ public class Parametrage
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Integer id;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "nom_structure")
/*     */   private String nomStructure;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "boite_postale")
/*     */   private String boitePostale;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "contact_1")
/*     */   private String contact1;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "contact_2")
/*     */   private String contact2;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "contact_3")
/*     */   private String contact3;
/*     */   @Size(max = 2147483647)
/*     */   private String descriptif;
/*     */   @Size(max = 2147483647)
/*     */   private String rcm;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "no_contrib")
/*     */   private String noContrib;
/*     */   @Size(max = 2147483647)
/*     */   private String localisation;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "repertoire_logo")
/*     */   private String repertoireLogo;
/*     */   @Size(max = 2147483647)
/*     */   private String logo;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "repertoire_sauvegardre")
/*     */   private String repertoireSauvegardre;
/*     */   @Column(name = "taux_tva")
/*     */   private Double tauxTva;
/*     */   private Double capital;
/*     */   @Column(name = "taux_remise")
/*     */   private Double tauxRemise;
/*     */   @Size(max = 2147483647)
/*     */   @Column(name = "chemin_template")
/*     */   private String cheminTemplate;
/*     */   @Column(name = "etat_quantite_dosage")
/*     */   private Boolean etatQuantiteDosage;
/*     */   @Column(name = "etat_forme_produit")
/*     */   private Boolean etatFormeProduit;
/*     */   @Column(name = "etat_forme_stockage")
/*     */   private Boolean etatFormeStockage;
/*     */   private Boolean calcultva;
/*     */   @Column(name = "calcul_remise")
/*     */   private Boolean calculRemise;
/*     */   @Size(max = 2147483647)
/*     */   private String fax;
/*     */   @Column(name = "nbre_jr_alerte_peremption")
/*     */   private Integer nbreJrAlertePeremption;
/*     */   @Column(name = "pourcentage_bailleur")
/*     */   private Double pourcentageBailleur;
/*     */   @Size(max = 50)
/*     */   private String banque;
/*     */   @Size(max = 60)
/*     */   @Column(name = "numero_compte")
/*     */   private String numeroCompte;
/*     */   private Boolean etatbailleur;
/*     */   private Boolean etatuser;
/*     */   
/*     */   public Parametrage() {}
/*     */   
/*     */   public Parametrage(Integer id) {
/* 131 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 135 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 139 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getNomStructure() {
/* 143 */     return this.nomStructure;
/*     */   }
/*     */   
/*     */   public void setNomStructure(String nomStructure) {
/* 147 */     this.nomStructure = nomStructure;
/*     */   }
/*     */   
/*     */   public String getBoitePostale() {
/* 151 */     return this.boitePostale;
/*     */   }
/*     */   
/*     */   public void setBoitePostale(String boitePostale) {
/* 155 */     this.boitePostale = boitePostale;
/*     */   }
/*     */   
/*     */   public String getContact1() {
/* 159 */     return this.contact1;
/*     */   }
/*     */   
/*     */   public void setContact1(String contact1) {
/* 163 */     this.contact1 = contact1;
/*     */   }
/*     */   
/*     */   public String getContact2() {
/* 167 */     return this.contact2;
/*     */   }
/*     */   
/*     */   public void setContact2(String contact2) {
/* 171 */     this.contact2 = contact2;
/*     */   }
/*     */   
/*     */   public String getContact3() {
/* 175 */     return this.contact3;
/*     */   }
/*     */   
/*     */   public void setContact3(String contact3) {
/* 179 */     this.contact3 = contact3;
/*     */   }
/*     */   
/*     */   public String getDescriptif() {
/* 183 */     return this.descriptif;
/*     */   }
/*     */   
/*     */   public void setDescriptif(String descriptif) {
/* 187 */     this.descriptif = descriptif;
/*     */   }
/*     */   
/*     */   public String getRcm() {
/* 191 */     return this.rcm;
/*     */   }
/*     */   
/*     */   public void setRcm(String rcm) {
/* 195 */     this.rcm = rcm;
/*     */   }
/*     */   
/*     */   public String getNoContrib() {
/* 199 */     return this.noContrib;
/*     */   }
/*     */   
/*     */   public void setNoContrib(String noContrib) {
/* 203 */     this.noContrib = noContrib;
/*     */   }
/*     */   
/*     */   public String getLocalisation() {
/* 207 */     return this.localisation;
/*     */   }
/*     */   
/*     */   public void setLocalisation(String localisation) {
/* 211 */     this.localisation = localisation;
/*     */   }
/*     */   
/*     */   public String getRepertoireLogo() {
/* 215 */     return this.repertoireLogo;
/*     */   }
/*     */   
/*     */   public void setRepertoireLogo(String repertoireLogo) {
/* 219 */     this.repertoireLogo = repertoireLogo;
/*     */   }
/*     */   
/*     */   public String getLogo() {
/* 223 */     return this.logo;
/*     */   }
/*     */   
/*     */   public void setLogo(String logo) {
/* 227 */     this.logo = logo;
/*     */   }
/*     */   
/*     */   public String getRepertoireSauvegardre() {
/* 231 */     return this.repertoireSauvegardre;
/*     */   }
/*     */   
/*     */   public void setRepertoireSauvegardre(String repertoireSauvegardre) {
/* 235 */     this.repertoireSauvegardre = repertoireSauvegardre;
/*     */   }
/*     */   
/*     */   public Double getTauxTva() {
/* 239 */     return this.tauxTva;
/*     */   }
/*     */   
/*     */   public void setTauxTva(Double tauxTva) {
/* 243 */     this.tauxTva = tauxTva;
/*     */   }
/*     */   
/*     */   public Double getCapital() {
/* 247 */     return this.capital;
/*     */   }
/*     */   
/*     */   public void setCapital(Double capital) {
/* 251 */     this.capital = capital;
/*     */   }
/*     */   
/*     */   public Double getTauxRemise() {
/* 255 */     return this.tauxRemise;
/*     */   }
/*     */   
/*     */   public void setTauxRemise(Double tauxRemise) {
/* 259 */     this.tauxRemise = tauxRemise;
/*     */   }
/*     */   
/*     */   public String getCheminTemplate() {
/* 263 */     return this.cheminTemplate;
/*     */   }
/*     */   
/*     */   public void setCheminTemplate(String cheminTemplate) {
/* 267 */     this.cheminTemplate = cheminTemplate;
/*     */   }
/*     */   
/*     */   public Boolean getEtatQuantiteDosage() {
/* 271 */     return this.etatQuantiteDosage;
/*     */   }
/*     */   
/*     */   public void setEtatQuantiteDosage(Boolean etatQuantiteDosage) {
/* 275 */     this.etatQuantiteDosage = etatQuantiteDosage;
/*     */   }
/*     */   
/*     */   public Boolean getEtatFormeProduit() {
/* 279 */     return this.etatFormeProduit;
/*     */   }
/*     */   
/*     */   public void setEtatFormeProduit(Boolean etatFormeProduit) {
/* 283 */     this.etatFormeProduit = etatFormeProduit;
/*     */   }
/*     */   
/*     */   public Boolean getEtatFormeStockage() {
/* 287 */     return this.etatFormeStockage;
/*     */   }
/*     */   
/*     */   public void setEtatFormeStockage(Boolean etatFormeStockage) {
/* 291 */     this.etatFormeStockage = etatFormeStockage;
/*     */   }
/*     */   
/*     */   public Boolean getCalcultva() {
/* 295 */     return this.calcultva;
/*     */   }
/*     */   
/*     */   public void setCalcultva(Boolean calcultva) {
/* 299 */     this.calcultva = calcultva;
/*     */   }
/*     */   
/*     */   public Boolean getCalculRemise() {
/* 303 */     return this.calculRemise;
/*     */   }
/*     */   
/*     */   public void setCalculRemise(Boolean calculRemise) {
/* 307 */     this.calculRemise = calculRemise;
/*     */   }
/*     */   
/*     */   public String getFax() {
/* 311 */     return this.fax;
/*     */   }
/*     */   
/*     */   public void setFax(String fax) {
/* 315 */     this.fax = fax;
/*     */   }
/*     */   
/*     */   public Integer getNbreJrAlertePeremption() {
/* 319 */     return this.nbreJrAlertePeremption;
/*     */   }
/*     */   
/*     */   public void setNbreJrAlertePeremption(Integer nbreJrAlertePeremption) {
/* 323 */     this.nbreJrAlertePeremption = nbreJrAlertePeremption;
/*     */   }
/*     */   
/*     */   public Double getPourcentageBailleur() {
/* 327 */     return this.pourcentageBailleur;
/*     */   }
/*     */   
/*     */   public void setPourcentageBailleur(Double pourcentageBailleur) {
/* 331 */     this.pourcentageBailleur = pourcentageBailleur;
/*     */   }
/*     */   
/*     */   public String getBanque() {
/* 335 */     return this.banque;
/*     */   }
/*     */   
/*     */   public void setBanque(String banque) {
/* 339 */     this.banque = banque;
/*     */   }
/*     */   
/*     */   public String getNumeroCompte() {
/* 343 */     return this.numeroCompte;
/*     */   }
/*     */   
/*     */   public void setNumeroCompte(String numeroCompte) {
/* 347 */     this.numeroCompte = numeroCompte;
/*     */   }
/*     */   
/*     */   public Boolean getEtatbailleur() {
/* 351 */     return this.etatbailleur;
/*     */   }
/*     */   
/*     */   public void setEtatbailleur(Boolean etatbailleur) {
/* 355 */     this.etatbailleur = etatbailleur;
/*     */   }
/*     */   
/*     */   public Boolean getEtatuser() {
/* 359 */     return this.etatuser;
/*     */   }
/*     */   
/*     */   public void setEtatuser(Boolean etatuser) {
/* 363 */     this.etatuser = etatuser;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 368 */     int hash = 0;
/* 369 */     hash += (this.id != null) ? this.id.hashCode() : 0;
/* 370 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 376 */     if (!(object instanceof Parametrage)) {
/* 377 */       return false;
/*     */     }
/* 379 */     Parametrage other = (Parametrage)object;
/* 380 */     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
/* 381 */       return false;
/*     */     }
/* 383 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 388 */     return "entities.Parametrage[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Parametrage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */