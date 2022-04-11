/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.Table;
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
/*     */ @Table(name = "ligne_commande_client")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "LigneCommandeClient.findAll", query = "SELECT l FROM LigneCommandeClient l"), @NamedQuery(name = "LigneCommandeClient.findByIdLigneCmdeClient", query = "SELECT l FROM LigneCommandeClient l WHERE l.idLigneCmdeClient = :idLigneCmdeClient"), @NamedQuery(name = "LigneCommandeClient.findByQuantite", query = "SELECT l FROM LigneCommandeClient l WHERE l.quantite = :quantite"), @NamedQuery(name = "LigneCommandeClient.findByMontant", query = "SELECT l FROM LigneCommandeClient l WHERE l.montant = :montant"), @NamedQuery(name = "LigneCommandeClient.findByTauxSatisfaction", query = "SELECT l FROM LigneCommandeClient l WHERE l.tauxSatisfaction = :tauxSatisfaction")})
/*     */ public class LigneCommandeClient
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   @Column(name = "id_ligne_cmde_client")
/*     */   private Long idLigneCmdeClient;
/*     */   private Double quantite;
/*     */   private Double montant;
/*     */   @Column(name = "taux_satisfaction")
/*     */   private Double tauxSatisfaction;
/*     */   @JoinColumn(name = "id_cmde_client", referencedColumnName = "id_commande_client")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private CommandeClient idCmdeClient;
/*     */   @JoinColumn(name = "idlot", referencedColumnName = "idlot")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Lot idlot;
/*     */   @JoinColumn(name = "idproduit", referencedColumnName = "idproduit")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Produit idproduit;
/*     */   
/*     */   public LigneCommandeClient() {}
/*     */   
/*     */   public LigneCommandeClient(Long idLigneCmdeClient) {
/*  61 */     this.idLigneCmdeClient = idLigneCmdeClient;
/*     */   }
/*     */   
/*     */   public Long getIdLigneCmdeClient() {
/*  65 */     return this.idLigneCmdeClient;
/*     */   }
/*     */   
/*     */   public void setIdLigneCmdeClient(Long idLigneCmdeClient) {
/*  69 */     this.idLigneCmdeClient = idLigneCmdeClient;
/*     */   }
/*     */   
/*     */   public Double getQuantite() {
/*  73 */     return this.quantite;
/*     */   }
/*     */   
/*     */   public void setQuantite(Double quantite) {
/*  77 */     this.quantite = quantite;
/*     */   }
/*     */   
/*     */   public Double getMontant() {
/*  81 */     return this.montant;
/*     */   }
/*     */   
/*     */   public void setMontant(Double montant) {
/*  85 */     this.montant = montant;
/*     */   }
/*     */   
/*     */   public Double getTauxSatisfaction() {
/*  89 */     return this.tauxSatisfaction;
/*     */   }
/*     */   
/*     */   public void setTauxSatisfaction(Double tauxSatisfaction) {
/*  93 */     this.tauxSatisfaction = tauxSatisfaction;
/*     */   }
/*     */   
/*     */   public CommandeClient getIdCmdeClient() {
/*  97 */     return this.idCmdeClient;
/*     */   }
/*     */   
/*     */   public void setIdCmdeClient(CommandeClient idCmdeClient) {
/* 101 */     this.idCmdeClient = idCmdeClient;
/*     */   }
/*     */   
/*     */   public Lot getIdlot() {
/* 105 */     return this.idlot;
/*     */   }
/*     */   
/*     */   public void setIdlot(Lot idlot) {
/* 109 */     this.idlot = idlot;
/*     */   }
/*     */   
/*     */   public Produit getIdproduit() {
/* 113 */     return this.idproduit;
/*     */   }
/*     */   
/*     */   public void setIdproduit(Produit idproduit) {
/* 117 */     this.idproduit = idproduit;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 122 */     int hash = 0;
/* 123 */     hash += (this.idLigneCmdeClient != null) ? this.idLigneCmdeClient.hashCode() : 0;
/* 124 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 130 */     if (!(object instanceof LigneCommandeClient)) {
/* 131 */       return false;
/*     */     }
/* 133 */     LigneCommandeClient other = (LigneCommandeClient)object;
/* 134 */     if ((this.idLigneCmdeClient == null && other.idLigneCmdeClient != null) || (this.idLigneCmdeClient != null && !this.idLigneCmdeClient.equals(other.idLigneCmdeClient))) {
/* 135 */       return false;
/*     */     }
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 142 */     return "entities.LigneCommandeClient[ idLigneCmdeClient=" + this.idLigneCmdeClient + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\LigneCommandeClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */