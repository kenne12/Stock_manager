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
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "commande_fournisseur")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "CommandeFournisseur.findAll", query = "SELECT c FROM CommandeFournisseur c"), @NamedQuery(name = "CommandeFournisseur.findByIdCmdeFournisseur", query = "SELECT c FROM CommandeFournisseur c WHERE c.idCmdeFournisseur = :idCmdeFournisseur"), @NamedQuery(name = "CommandeFournisseur.findByDateCmde", query = "SELECT c FROM CommandeFournisseur c WHERE c.dateCmde = :dateCmde"), @NamedQuery(name = "CommandeFournisseur.findByMontantHt", query = "SELECT c FROM CommandeFournisseur c WHERE c.montantHt = :montantHt"), @NamedQuery(name = "CommandeFournisseur.findByMontantTva", query = "SELECT c FROM CommandeFournisseur c WHERE c.montantTva = :montantTva"), @NamedQuery(name = "CommandeFournisseur.findByMontantTtc", query = "SELECT c FROM CommandeFournisseur c WHERE c.montantTtc = :montantTtc"), @NamedQuery(name = "CommandeFournisseur.findByMontantRemise", query = "SELECT c FROM CommandeFournisseur c WHERE c.montantRemise = :montantRemise"), @NamedQuery(name = "CommandeFournisseur.findByTauxTva", query = "SELECT c FROM CommandeFournisseur c WHERE c.tauxTva = :tauxTva"), @NamedQuery(name = "CommandeFournisseur.findByTauxRemise", query = "SELECT c FROM CommandeFournisseur c WHERE c.tauxRemise = :tauxRemise"), @NamedQuery(name = "CommandeFournisseur.findByLivre", query = "SELECT c FROM CommandeFournisseur c WHERE c.livre = :livre"), @NamedQuery(name = "CommandeFournisseur.findByIdstock", query = "SELECT c FROM CommandeFournisseur c WHERE c.idstock = :idstock"), @NamedQuery(name = "CommandeFournisseur.findByCode", query = "SELECT c FROM CommandeFournisseur c WHERE c.code = :code"), @NamedQuery(name = "CommandeFournisseur.findByDateLivraisonPrev", query = "SELECT c FROM CommandeFournisseur c WHERE c.dateLivraisonPrev = :dateLivraisonPrev"), @NamedQuery(name = "CommandeFournisseur.findByDateLivraisonEffective", query = "SELECT c FROM CommandeFournisseur c WHERE c.dateLivraisonEffective = :dateLivraisonEffective"), @NamedQuery(name = "CommandeFournisseur.findByCalculTva", query = "SELECT c FROM CommandeFournisseur c WHERE c.calculTva = :calculTva"), @NamedQuery(name = "CommandeFournisseur.findByCalculRemise", query = "SELECT c FROM CommandeFournisseur c WHERE c.calculRemise = :calculRemise"), @NamedQuery(name = "CommandeFournisseur.findByPaye", query = "SELECT c FROM CommandeFournisseur c WHERE c.paye = :paye"), @NamedQuery(name = "CommandeFournisseur.findByTauxSatisfaction", query = "SELECT c FROM CommandeFournisseur c WHERE c.tauxSatisfaction = :tauxSatisfaction")})
/*     */ public class CommandeFournisseur
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   @Column(name = "id_cmde_fournisseur")
/*     */   private Long idCmdeFournisseur;
/*     */   @Column(name = "date_cmde")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateCmde;
/*     */   @Column(name = "montant_ht")
/*     */   private Double montantHt;
/*     */   @Column(name = "montant_tva")
/*     */   private Double montantTva;
/*     */   @Column(name = "montant_ttc")
/*     */   private Double montantTtc;
/*     */   @Column(name = "montant_remise")
/*     */   private Double montantRemise;
/*     */   @Column(name = "taux_tva")
/*     */   private Double tauxTva;
/*     */   @Column(name = "taux_remise")
/*     */   private Double tauxRemise;
/*     */   private Boolean livre;
/*     */   private BigInteger idstock;
/*     */   @Size(max = 50)
/*     */   private String code;
/*     */   @Column(name = "date_livraison_prev")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateLivraisonPrev;
/*     */   @Column(name = "date_livraison_effective")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateLivraisonEffective;
/*     */   @Column(name = "calcul_tva")
/*     */   private Boolean calculTva;
/*     */   @Column(name = "calcul_remise")
/*     */   private Boolean calculRemise;
/*     */   private Boolean paye;
/*     */   @Column(name = "taux_satisfaction")
/*     */   private Double tauxSatisfaction;
/*     */   @JoinColumn(name = "idmois", referencedColumnName = "id_annee_mois")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private AnneeMois idmois;
/*     */   @JoinColumn(name = "idfournisseur", referencedColumnName = "idfournisseur")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Fournisseur idfournisseur;
/*     */   @OneToMany(mappedBy = "idCmdeFournisseur", fetch = FetchType.LAZY)
/*     */   private List<LigneCmdeFournisseur> ligneCmdeFournisseurList;
/*     */   
/*     */   public CommandeFournisseur() {}
/*     */   
/*     */   public CommandeFournisseur(Long idCmdeFournisseur) {
/* 109 */     this.idCmdeFournisseur = idCmdeFournisseur;
/*     */   }
/*     */   
/*     */   public Long getIdCmdeFournisseur() {
/* 113 */     return this.idCmdeFournisseur;
/*     */   }
/*     */   
/*     */   public void setIdCmdeFournisseur(Long idCmdeFournisseur) {
/* 117 */     this.idCmdeFournisseur = idCmdeFournisseur;
/*     */   }
/*     */   
/*     */   public Date getDateCmde() {
/* 121 */     return this.dateCmde;
/*     */   }
/*     */   
/*     */   public void setDateCmde(Date dateCmde) {
/* 125 */     this.dateCmde = dateCmde;
/*     */   }
/*     */   
/*     */   public Double getMontantHt() {
/* 129 */     return this.montantHt;
/*     */   }
/*     */   
/*     */   public void setMontantHt(Double montantHt) {
/* 133 */     this.montantHt = montantHt;
/*     */   }
/*     */   
/*     */   public Double getMontantTva() {
/* 137 */     return this.montantTva;
/*     */   }
/*     */   
/*     */   public void setMontantTva(Double montantTva) {
/* 141 */     this.montantTva = montantTva;
/*     */   }
/*     */   
/*     */   public Double getMontantTtc() {
/* 145 */     return this.montantTtc;
/*     */   }
/*     */   
/*     */   public void setMontantTtc(Double montantTtc) {
/* 149 */     this.montantTtc = montantTtc;
/*     */   }
/*     */   
/*     */   public Double getMontantRemise() {
/* 153 */     return this.montantRemise;
/*     */   }
/*     */   
/*     */   public void setMontantRemise(Double montantRemise) {
/* 157 */     this.montantRemise = montantRemise;
/*     */   }
/*     */   
/*     */   public Double getTauxTva() {
/* 161 */     return this.tauxTva;
/*     */   }
/*     */   
/*     */   public void setTauxTva(Double tauxTva) {
/* 165 */     this.tauxTva = tauxTva;
/*     */   }
/*     */   
/*     */   public Double getTauxRemise() {
/* 169 */     return this.tauxRemise;
/*     */   }
/*     */   
/*     */   public void setTauxRemise(Double tauxRemise) {
/* 173 */     this.tauxRemise = tauxRemise;
/*     */   }
/*     */   
/*     */   public Boolean getLivre() {
/* 177 */     return this.livre;
/*     */   }
/*     */   
/*     */   public void setLivre(Boolean livre) {
/* 181 */     this.livre = livre;
/*     */   }
/*     */   
/*     */   public BigInteger getIdstock() {
/* 185 */     return this.idstock;
/*     */   }
/*     */   
/*     */   public void setIdstock(BigInteger idstock) {
/* 189 */     this.idstock = idstock;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 193 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 197 */     this.code = code;
/*     */   }
/*     */   
/*     */   public Date getDateLivraisonPrev() {
/* 201 */     return this.dateLivraisonPrev;
/*     */   }
/*     */   
/*     */   public void setDateLivraisonPrev(Date dateLivraisonPrev) {
/* 205 */     this.dateLivraisonPrev = dateLivraisonPrev;
/*     */   }
/*     */   
/*     */   public Date getDateLivraisonEffective() {
/* 209 */     return this.dateLivraisonEffective;
/*     */   }
/*     */   
/*     */   public void setDateLivraisonEffective(Date dateLivraisonEffective) {
/* 213 */     this.dateLivraisonEffective = dateLivraisonEffective;
/*     */   }
/*     */   
/*     */   public Boolean getCalculTva() {
/* 217 */     return this.calculTva;
/*     */   }
/*     */   
/*     */   public void setCalculTva(Boolean calculTva) {
/* 221 */     this.calculTva = calculTva;
/*     */   }
/*     */   
/*     */   public Boolean getCalculRemise() {
/* 225 */     return this.calculRemise;
/*     */   }
/*     */   
/*     */   public void setCalculRemise(Boolean calculRemise) {
/* 229 */     this.calculRemise = calculRemise;
/*     */   }
/*     */   
/*     */   public Boolean getPaye() {
/* 233 */     return this.paye;
/*     */   }
/*     */   
/*     */   public void setPaye(Boolean paye) {
/* 237 */     this.paye = paye;
/*     */   }
/*     */   
/*     */   public Double getTauxSatisfaction() {
/* 241 */     return this.tauxSatisfaction;
/*     */   }
/*     */   
/*     */   public void setTauxSatisfaction(Double tauxSatisfaction) {
/* 245 */     this.tauxSatisfaction = tauxSatisfaction;
/*     */   }
/*     */   
/*     */   public AnneeMois getIdmois() {
/* 249 */     return this.idmois;
/*     */   }
/*     */   
/*     */   public void setIdmois(AnneeMois idmois) {
/* 253 */     this.idmois = idmois;
/*     */   }
/*     */   
/*     */   public Fournisseur getIdfournisseur() {
/* 257 */     return this.idfournisseur;
/*     */   }
/*     */   
/*     */   public void setIdfournisseur(Fournisseur idfournisseur) {
/* 261 */     this.idfournisseur = idfournisseur;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<LigneCmdeFournisseur> getLigneCmdeFournisseurList() {
/* 266 */     return this.ligneCmdeFournisseurList;
/*     */   }
/*     */   
/*     */   public void setLigneCmdeFournisseurList(List<LigneCmdeFournisseur> ligneCmdeFournisseurList) {
/* 270 */     this.ligneCmdeFournisseurList = ligneCmdeFournisseurList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 275 */     int hash = 0;
/* 276 */     hash += (this.idCmdeFournisseur != null) ? this.idCmdeFournisseur.hashCode() : 0;
/* 277 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 283 */     if (!(object instanceof CommandeFournisseur)) {
/* 284 */       return false;
/*     */     }
/* 286 */     CommandeFournisseur other = (CommandeFournisseur)object;
/* 287 */     if ((this.idCmdeFournisseur == null && other.idCmdeFournisseur != null) || (this.idCmdeFournisseur != null && !this.idCmdeFournisseur.equals(other.idCmdeFournisseur))) {
/* 288 */       return false;
/*     */     }
/* 290 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 295 */     return "entities.CommandeFournisseur[ idCmdeFournisseur=" + this.idCmdeFournisseur + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\CommandeFournisseur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */