/*    */ package entities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.NamedQueries;
/*    */ import javax.persistence.NamedQuery;
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
/*    */ 
/*    */ @Entity
/*    */ @XmlRootElement
/*    */ @NamedQueries({@NamedQuery(name = "Caisse.findAll", query = "SELECT c FROM Caisse c"), @NamedQuery(name = "Caisse.findByIdcaisse", query = "SELECT c FROM Caisse c WHERE c.idcaisse = :idcaisse"), @NamedQuery(name = "Caisse.findByMontant", query = "SELECT c FROM Caisse c WHERE c.montant = :montant")})
/*    */ public class Caisse
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @NotNull
/*    */   private Integer idcaisse;
/*    */   private Integer montant;
/*    */   
/*    */   public Caisse() {}
/*    */   
/*    */   public Caisse(Integer idcaisse) {
/* 39 */     this.idcaisse = idcaisse;
/*    */   }
/*    */   
/*    */   public Integer getIdcaisse() {
/* 43 */     return this.idcaisse;
/*    */   }
/*    */   
/*    */   public void setIdcaisse(Integer idcaisse) {
/* 47 */     this.idcaisse = idcaisse;
/*    */   }
/*    */   
/*    */   public Integer getMontant() {
/* 51 */     return this.montant;
/*    */   }
/*    */   
/*    */   public void setMontant(Integer montant) {
/* 55 */     this.montant = montant;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 60 */     int hash = 0;
/* 61 */     hash += (this.idcaisse != null) ? this.idcaisse.hashCode() : 0;
/* 62 */     return hash;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 68 */     if (!(object instanceof Caisse)) {
/* 69 */       return false;
/*    */     }
/* 71 */     Caisse other = (Caisse)object;
/* 72 */     if ((this.idcaisse == null && other.idcaisse != null) || (this.idcaisse != null && !this.idcaisse.equals(other.idcaisse))) {
/* 73 */       return false;
/*    */     }
/* 75 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     return "entities.Caisse[ idcaisse=" + this.idcaisse + " ]";
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Caisse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */