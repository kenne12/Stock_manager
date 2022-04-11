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
/*     */ @Entity
/*     */ @Table(name = "ligne_fact_bailleur")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "LigneFactBailleur.findAll", query = "SELECT l FROM LigneFactBailleur l"), @NamedQuery(name = "LigneFactBailleur.findByIdLigneFactBailleur", query = "SELECT l FROM LigneFactBailleur l WHERE l.idLigneFactBailleur = :idLigneFactBailleur"), @NamedQuery(name = "LigneFactBailleur.findByPrixUnitaire", query = "SELECT l FROM LigneFactBailleur l WHERE l.prixUnitaire = :prixUnitaire"), @NamedQuery(name = "LigneFactBailleur.findByQuantite", query = "SELECT l FROM LigneFactBailleur l WHERE l.quantite = :quantite")})
/*     */ public class LigneFactBailleur
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   @Column(name = "id_ligne_fact_bailleur")
/*     */   private Long idLigneFactBailleur;
/*     */   @Column(name = "prix_unitaire")
/*     */   private Double prixUnitaire;
/*     */   private Double quantite;
/*     */   @JoinColumn(name = "idfacture_bailleur", referencedColumnName = "id_facture_bailleur")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private FactureBailleur idfactureBailleur;
/*     */   @JoinColumn(name = "idproduit", referencedColumnName = "idproduit")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Produit idproduit;
/*     */   
/*     */   public LigneFactBailleur() {}
/*     */   
/*     */   public LigneFactBailleur(Long idLigneFactBailleur) {
/*  56 */     this.idLigneFactBailleur = idLigneFactBailleur;
/*     */   }
/*     */   
/*     */   public Long getIdLigneFactBailleur() {
/*  60 */     return this.idLigneFactBailleur;
/*     */   }
/*     */   
/*     */   public void setIdLigneFactBailleur(Long idLigneFactBailleur) {
/*  64 */     this.idLigneFactBailleur = idLigneFactBailleur;
/*     */   }
/*     */   
/*     */   public Double getPrixUnitaire() {
/*  68 */     return this.prixUnitaire;
/*     */   }
/*     */   
/*     */   public void setPrixUnitaire(Double prixUnitaire) {
/*  72 */     this.prixUnitaire = prixUnitaire;
/*     */   }
/*     */   
/*     */   public Double getQuantite() {
/*  76 */     return this.quantite;
/*     */   }
/*     */   
/*     */   public void setQuantite(Double quantite) {
/*  80 */     this.quantite = quantite;
/*     */   }
/*     */   
/*     */   public FactureBailleur getIdfactureBailleur() {
/*  84 */     return this.idfactureBailleur;
/*     */   }
/*     */   
/*     */   public void setIdfactureBailleur(FactureBailleur idfactureBailleur) {
/*  88 */     this.idfactureBailleur = idfactureBailleur;
/*     */   }
/*     */   
/*     */   public Produit getIdproduit() {
/*  92 */     return this.idproduit;
/*     */   }
/*     */   
/*     */   public void setIdproduit(Produit idproduit) {
/*  96 */     this.idproduit = idproduit;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 101 */     int hash = 0;
/* 102 */     hash += (this.idLigneFactBailleur != null) ? this.idLigneFactBailleur.hashCode() : 0;
/* 103 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 109 */     if (!(object instanceof LigneFactBailleur)) {
/* 110 */       return false;
/*     */     }
/* 112 */     LigneFactBailleur other = (LigneFactBailleur)object;
/* 113 */     if ((this.idLigneFactBailleur == null && other.idLigneFactBailleur != null) || (this.idLigneFactBailleur != null && !this.idLigneFactBailleur.equals(other.idLigneFactBailleur))) {
/* 114 */       return false;
/*     */     }
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 121 */     return "entities.LigneFactBailleur[ idLigneFactBailleur=" + this.idLigneFactBailleur + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\LigneFactBailleur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */