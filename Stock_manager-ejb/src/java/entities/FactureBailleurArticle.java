/*    */ package entities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.NamedQueries;
/*    */ import javax.persistence.NamedQuery;
/*    */ import javax.persistence.Table;
/*    */ import javax.validation.constraints.NotNull;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "facture_bailleur_article")
/*    */ @XmlRootElement
/*    */ @NamedQueries({@NamedQuery(name = "FactureBailleurArticle.findAll", query = "SELECT f FROM FactureBailleurArticle f"), @NamedQuery(name = "FactureBailleurArticle.findByIdFactureBailleurArticle", query = "SELECT f FROM FactureBailleurArticle f WHERE f.idFactureBailleurArticle = :idFactureBailleurArticle")})
/*    */ public class FactureBailleurArticle
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @NotNull
/*    */   @Column(name = "id_facture_bailleur_article")
/*    */   private Long idFactureBailleurArticle;
/*    */   @JoinColumn(name = "idcommande", referencedColumnName = "idcommande")
/*    */   @ManyToOne(fetch = FetchType.LAZY)
/*    */   private Commande idcommande;
/*    */   @JoinColumn(name = "idfacture_bailleur", referencedColumnName = "id_facture_bailleur")
/*    */   @ManyToOne(fetch = FetchType.LAZY)
/*    */   private FactureBailleur idfactureBailleur;
/*    */   
/*    */   public FactureBailleurArticle() {}
/*    */   
/*    */   public FactureBailleurArticle(Long idFactureBailleurArticle) {
/* 50 */     this.idFactureBailleurArticle = idFactureBailleurArticle;
/*    */   }
/*    */   
/*    */   public Long getIdFactureBailleurArticle() {
/* 54 */     return this.idFactureBailleurArticle;
/*    */   }
/*    */   
/*    */   public void setIdFactureBailleurArticle(Long idFactureBailleurArticle) {
/* 58 */     this.idFactureBailleurArticle = idFactureBailleurArticle;
/*    */   }
/*    */   
/*    */   public Commande getIdcommande() {
/* 62 */     return this.idcommande;
/*    */   }
/*    */   
/*    */   public void setIdcommande(Commande idcommande) {
/* 66 */     this.idcommande = idcommande;
/*    */   }
/*    */   
/*    */   public FactureBailleur getIdfactureBailleur() {
/* 70 */     return this.idfactureBailleur;
/*    */   }
/*    */   
/*    */   public void setIdfactureBailleur(FactureBailleur idfactureBailleur) {
/* 74 */     this.idfactureBailleur = idfactureBailleur;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 79 */     int hash = 0;
/* 80 */     hash += (this.idFactureBailleurArticle != null) ? this.idFactureBailleurArticle.hashCode() : 0;
/* 81 */     return hash;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 87 */     if (!(object instanceof FactureBailleurArticle)) {
/* 88 */       return false;
/*    */     }
/* 90 */     FactureBailleurArticle other = (FactureBailleurArticle)object;
/* 91 */     if ((this.idFactureBailleurArticle == null && other.idFactureBailleurArticle != null) || (this.idFactureBailleurArticle != null && !this.idFactureBailleurArticle.equals(other.idFactureBailleurArticle))) {
/* 92 */       return false;
/*    */     }
/* 94 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 99 */     return "entities.FactureBailleurArticle[ idFactureBailleurArticle=" + this.idFactureBailleurArticle + " ]";
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\FactureBailleurArticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */