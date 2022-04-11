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
/*     */ @Table(name = "ligne_cmde_fournisseur")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "LigneCmdeFournisseur.findAll", query = "SELECT l FROM LigneCmdeFournisseur l"), @NamedQuery(name = "LigneCmdeFournisseur.findByIdLigneCmdeFournisseur", query = "SELECT l FROM LigneCmdeFournisseur l WHERE l.idLigneCmdeFournisseur = :idLigneCmdeFournisseur"), @NamedQuery(name = "LigneCmdeFournisseur.findByQuantite", query = "SELECT l FROM LigneCmdeFournisseur l WHERE l.quantite = :quantite"), @NamedQuery(name = "LigneCmdeFournisseur.findByMontant", query = "SELECT l FROM LigneCmdeFournisseur l WHERE l.montant = :montant"), @NamedQuery(name = "LigneCmdeFournisseur.findByTauxSatisfaction", query = "SELECT l FROM LigneCmdeFournisseur l WHERE l.tauxSatisfaction = :tauxSatisfaction")})
/*     */ public class LigneCmdeFournisseur
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   @Column(name = "id_ligne_cmde_fournisseur")
/*     */   private Long idLigneCmdeFournisseur;
/*     */   private Double quantite;
/*     */   private Double montant;
/*     */   @Column(name = "taux_satisfaction")
/*     */   private Double tauxSatisfaction;
/*     */   @JoinColumn(name = "id_cmde_fournisseur", referencedColumnName = "id_cmde_fournisseur")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private CommandeFournisseur idCmdeFournisseur;
/*     */   @JoinColumn(name = "idlot", referencedColumnName = "idlot")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Lot idlot;
/*     */   @JoinColumn(name = "idproduit", referencedColumnName = "idproduit")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Produit idproduit;
/*     */   
/*     */   public LigneCmdeFournisseur() {}
/*     */   
/*     */   public LigneCmdeFournisseur(Long idLigneCmdeFournisseur) {
/*  61 */     this.idLigneCmdeFournisseur = idLigneCmdeFournisseur;
/*     */   }
/*     */   
/*     */   public Long getIdLigneCmdeFournisseur() {
/*  65 */     return this.idLigneCmdeFournisseur;
/*     */   }
/*     */   
/*     */   public void setIdLigneCmdeFournisseur(Long idLigneCmdeFournisseur) {
/*  69 */     this.idLigneCmdeFournisseur = idLigneCmdeFournisseur;
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
/*     */   public CommandeFournisseur getIdCmdeFournisseur() {
/*  97 */     return this.idCmdeFournisseur;
/*     */   }
/*     */   
/*     */   public void setIdCmdeFournisseur(CommandeFournisseur idCmdeFournisseur) {
/* 101 */     this.idCmdeFournisseur = idCmdeFournisseur;
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
/* 123 */     hash += (this.idLigneCmdeFournisseur != null) ? this.idLigneCmdeFournisseur.hashCode() : 0;
/* 124 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 130 */     if (!(object instanceof LigneCmdeFournisseur)) {
/* 131 */       return false;
/*     */     }
/* 133 */     LigneCmdeFournisseur other = (LigneCmdeFournisseur)object;
/* 134 */     if ((this.idLigneCmdeFournisseur == null && other.idLigneCmdeFournisseur != null) || (this.idLigneCmdeFournisseur != null && !this.idLigneCmdeFournisseur.equals(other.idLigneCmdeFournisseur))) {
/* 135 */       return false;
/*     */     }
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 142 */     return "entities.LigneCmdeFournisseur[ idLigneCmdeFournisseur=" + this.idLigneCmdeFournisseur + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\LigneCmdeFournisseur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */