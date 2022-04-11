/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigInteger;
/*     */ import java.util.Date;
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
/*     */ import javax.persistence.Table;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "commande_client")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "CommandeClient.findAll", query = "SELECT c FROM CommandeClient c"), @NamedQuery(name = "CommandeClient.findByIdCommandeClient", query = "SELECT c FROM CommandeClient c WHERE c.idCommandeClient = :idCommandeClient"), @NamedQuery(name = "CommandeClient.findByDateCommande", query = "SELECT c FROM CommandeClient c WHERE c.dateCommande = :dateCommande"), @NamedQuery(name = "CommandeClient.findByDateLivraisonPrevisionnelle", query = "SELECT c FROM CommandeClient c WHERE c.dateLivraisonPrevisionnelle = :dateLivraisonPrevisionnelle"), @NamedQuery(name = "CommandeClient.findByDateLivraisonEffective", query = "SELECT c FROM CommandeClient c WHERE c.dateLivraisonEffective = :dateLivraisonEffective"), @NamedQuery(name = "CommandeClient.findByLivre", query = "SELECT c FROM CommandeClient c WHERE c.livre = :livre"), @NamedQuery(name = "CommandeClient.findByMontantHt", query = "SELECT c FROM CommandeClient c WHERE c.montantHt = :montantHt"), @NamedQuery(name = "CommandeClient.findByMontantTva", query = "SELECT c FROM CommandeClient c WHERE c.montantTva = :montantTva"), @NamedQuery(name = "CommandeClient.findByMontantTtc", query = "SELECT c FROM CommandeClient c WHERE c.montantTtc = :montantTtc"), @NamedQuery(name = "CommandeClient.findByTauxTva", query = "SELECT c FROM CommandeClient c WHERE c.tauxTva = :tauxTva"), @NamedQuery(name = "CommandeClient.findByTauxRemise", query = "SELECT c FROM CommandeClient c WHERE c.tauxRemise = :tauxRemise"), @NamedQuery(name = "CommandeClient.findByMontantRemise", query = "SELECT c FROM CommandeClient c WHERE c.montantRemise = :montantRemise"), @NamedQuery(name = "CommandeClient.findByCalculTva", query = "SELECT c FROM CommandeClient c WHERE c.calculTva = :calculTva"), @NamedQuery(name = "CommandeClient.findByCalculRemise", query = "SELECT c FROM CommandeClient c WHERE c.calculRemise = :calculRemise"), @NamedQuery(name = "CommandeClient.findByIdfacture", query = "SELECT c FROM CommandeClient c WHERE c.idfacture = :idfacture"), @NamedQuery(name = "CommandeClient.findByCode", query = "SELECT c FROM CommandeClient c WHERE c.code = :code"), @NamedQuery(name = "CommandeClient.findByTauxSatisfaction", query = "SELECT c FROM CommandeClient c WHERE c.tauxSatisfaction = :tauxSatisfaction")})
/*     */ public class CommandeClient
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   @Column(name = "id_commande_client")
/*     */   private Long idCommandeClient;
/*     */   @Column(name = "date_commande")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateCommande;
/*     */   @Column(name = "date_livraison_previsionnelle")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateLivraisonPrevisionnelle;
/*     */   @Column(name = "date_livraison_effective")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateLivraisonEffective;
/*     */   private Boolean livre;
/*     */   @Column(name = "montant_ht")
/*     */   private Double montantHt;
/*     */   @Column(name = "montant_tva")
/*     */   private Double montantTva;
/*     */   @Column(name = "montant_ttc")
/*     */   private Double montantTtc;
/*     */   @Column(name = "taux_tva")
/*     */   private Double tauxTva;
/*     */   @Column(name = "taux_remise")
/*     */   private Double tauxRemise;
/*     */   @Column(name = "montant_remise")
/*     */   private Double montantRemise;
/*     */   @Column(name = "calcul_tva")
/*     */   private Boolean calculTva;
/*     */   @Column(name = "calcul_remise")
/*     */   private Boolean calculRemise;
/*     */   private BigInteger idfacture;
/*     */   @Size(max = 50)
/*     */   private String code;
/*     */   @Column(name = "taux_satisfaction")
/*     */   private Double tauxSatisfaction;
/*     */   @JoinColumn(name = "idmois", referencedColumnName = "id_annee_mois")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private AnneeMois idmois;
/*     */   @JoinColumn(name = "idclient", referencedColumnName = "idclient")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Client idclient;
/*     */   @OneToMany(mappedBy = "idCmdeClient", fetch = FetchType.LAZY)
/*     */   private List<LigneCommandeClient> ligneCommandeClientList;
/*     */   
/*     */   public CommandeClient() {}
/*     */   
/*     */   public CommandeClient(Long idCommandeClient) {
/* 107 */     this.idCommandeClient = idCommandeClient;
/*     */   }
/*     */   
/*     */   public Long getIdCommandeClient() {
/* 111 */     return this.idCommandeClient;
/*     */   }
/*     */   
/*     */   public void setIdCommandeClient(Long idCommandeClient) {
/* 115 */     this.idCommandeClient = idCommandeClient;
/*     */   }
/*     */   
/*     */   public Date getDateCommande() {
/* 119 */     return this.dateCommande;
/*     */   }
/*     */   
/*     */   public void setDateCommande(Date dateCommande) {
/* 123 */     this.dateCommande = dateCommande;
/*     */   }
/*     */   
/*     */   public Date getDateLivraisonPrevisionnelle() {
/* 127 */     return this.dateLivraisonPrevisionnelle;
/*     */   }
/*     */   
/*     */   public void setDateLivraisonPrevisionnelle(Date dateLivraisonPrevisionnelle) {
/* 131 */     this.dateLivraisonPrevisionnelle = dateLivraisonPrevisionnelle;
/*     */   }
/*     */   
/*     */   public Date getDateLivraisonEffective() {
/* 135 */     return this.dateLivraisonEffective;
/*     */   }
/*     */   
/*     */   public void setDateLivraisonEffective(Date dateLivraisonEffective) {
/* 139 */     this.dateLivraisonEffective = dateLivraisonEffective;
/*     */   }
/*     */   
/*     */   public Boolean getLivre() {
/* 143 */     return this.livre;
/*     */   }
/*     */   
/*     */   public void setLivre(Boolean livre) {
/* 147 */     this.livre = livre;
/*     */   }
/*     */   
/*     */   public Double getMontantHt() {
/* 151 */     return this.montantHt;
/*     */   }
/*     */   
/*     */   public void setMontantHt(Double montantHt) {
/* 155 */     this.montantHt = montantHt;
/*     */   }
/*     */   
/*     */   public Double getMontantTva() {
/* 159 */     return this.montantTva;
/*     */   }
/*     */   
/*     */   public void setMontantTva(Double montantTva) {
/* 163 */     this.montantTva = montantTva;
/*     */   }
/*     */   
/*     */   public Double getMontantTtc() {
/* 167 */     return this.montantTtc;
/*     */   }
/*     */   
/*     */   public void setMontantTtc(Double montantTtc) {
/* 171 */     this.montantTtc = montantTtc;
/*     */   }
/*     */   
/*     */   public Double getTauxTva() {
/* 175 */     return this.tauxTva;
/*     */   }
/*     */   
/*     */   public void setTauxTva(Double tauxTva) {
/* 179 */     this.tauxTva = tauxTva;
/*     */   }
/*     */   
/*     */   public Double getTauxRemise() {
/* 183 */     return this.tauxRemise;
/*     */   }
/*     */   
/*     */   public void setTauxRemise(Double tauxRemise) {
/* 187 */     this.tauxRemise = tauxRemise;
/*     */   }
/*     */   
/*     */   public Double getMontantRemise() {
/* 191 */     return this.montantRemise;
/*     */   }
/*     */   
/*     */   public void setMontantRemise(Double montantRemise) {
/* 195 */     this.montantRemise = montantRemise;
/*     */   }
/*     */   
/*     */   public Boolean getCalculTva() {
/* 199 */     return this.calculTva;
/*     */   }
/*     */   
/*     */   public void setCalculTva(Boolean calculTva) {
/* 203 */     this.calculTva = calculTva;
/*     */   }
/*     */   
/*     */   public Boolean getCalculRemise() {
/* 207 */     return this.calculRemise;
/*     */   }
/*     */   
/*     */   public void setCalculRemise(Boolean calculRemise) {
/* 211 */     this.calculRemise = calculRemise;
/*     */   }
/*     */   
/*     */   public BigInteger getIdfacture() {
/* 215 */     return this.idfacture;
/*     */   }
/*     */   
/*     */   public void setIdfacture(BigInteger idfacture) {
/* 219 */     this.idfacture = idfacture;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 223 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 227 */     this.code = code;
/*     */   }
/*     */   
/*     */   public Double getTauxSatisfaction() {
/* 231 */     return this.tauxSatisfaction;
/*     */   }
/*     */   
/*     */   public void setTauxSatisfaction(Double tauxSatisfaction) {
/* 235 */     this.tauxSatisfaction = tauxSatisfaction;
/*     */   }
/*     */   
/*     */   public AnneeMois getIdmois() {
/* 239 */     return this.idmois;
/*     */   }
/*     */   
/*     */   public void setIdmois(AnneeMois idmois) {
/* 243 */     this.idmois = idmois;
/*     */   }
/*     */   
/*     */   public Client getIdclient() {
/* 247 */     return this.idclient;
/*     */   }
/*     */   
/*     */   public void setIdclient(Client idclient) {
/* 251 */     this.idclient = idclient;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<LigneCommandeClient> getLigneCommandeClientList() {
/* 256 */     return this.ligneCommandeClientList;
/*     */   }
/*     */   
/*     */   public void setLigneCommandeClientList(List<LigneCommandeClient> ligneCommandeClientList) {
/* 260 */     this.ligneCommandeClientList = ligneCommandeClientList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 265 */     int hash = 0;
/* 266 */     hash += (this.idCommandeClient != null) ? this.idCommandeClient.hashCode() : 0;
/* 267 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 273 */     if (!(object instanceof CommandeClient)) {
/* 274 */       return false;
/*     */     }
/* 276 */     CommandeClient other = (CommandeClient)object;
/* 277 */     if ((this.idCommandeClient == null && other.idCommandeClient != null) || (this.idCommandeClient != null && !this.idCommandeClient.equals(other.idCommandeClient))) {
/* 278 */       return false;
/*     */     }
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 285 */     return "entities.CommandeClient[ idCommandeClient=" + this.idCommandeClient + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\CommandeClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */