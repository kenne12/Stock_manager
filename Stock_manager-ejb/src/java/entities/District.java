/*    */ package entities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.FetchType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.NamedQueries;
/*    */ import javax.persistence.NamedQuery;
/*    */ import javax.persistence.OneToMany;
/*    */ import javax.validation.constraints.NotNull;
/*    */ import javax.validation.constraints.Size;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ import javax.xml.bind.annotation.XmlTransient;
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
/*    */ @NamedQueries({@NamedQuery(name = "District.findAll", query = "SELECT d FROM District d"), @NamedQuery(name = "District.findByIddistrict", query = "SELECT d FROM District d WHERE d.iddistrict = :iddistrict"), @NamedQuery(name = "District.findByNom", query = "SELECT d FROM District d WHERE d.nom = :nom")})
/*    */ public class District
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @NotNull
/*    */   private Integer iddistrict;
/*    */   @Size(max = 2147483647)
/*    */   private String nom;
/*    */   @OneToMany(mappedBy = "iddistrict", fetch = FetchType.LAZY)
/*    */   private List<Client> clientList;
/*    */   
/*    */   public District() {}
/*    */   
/*    */   public District(Integer iddistrict) {
/* 47 */     this.iddistrict = iddistrict;
/*    */   }
/*    */   
/*    */   public Integer getIddistrict() {
/* 51 */     return this.iddistrict;
/*    */   }
/*    */   
/*    */   public void setIddistrict(Integer iddistrict) {
/* 55 */     this.iddistrict = iddistrict;
/*    */   }
/*    */   
/*    */   public String getNom() {
/* 59 */     return this.nom;
/*    */   }
/*    */   
/*    */   public void setNom(String nom) {
/* 63 */     this.nom = nom;
/*    */   }
/*    */   
/*    */   @XmlTransient
/*    */   public List<Client> getClientList() {
/* 68 */     return this.clientList;
/*    */   }
/*    */   
/*    */   public void setClientList(List<Client> clientList) {
/* 72 */     this.clientList = clientList;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 77 */     int hash = 0;
/* 78 */     hash += (this.iddistrict != null) ? this.iddistrict.hashCode() : 0;
/* 79 */     return hash;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 85 */     if (!(object instanceof District)) {
/* 86 */       return false;
/*    */     }
/* 88 */     District other = (District)object;
/* 89 */     if ((this.iddistrict == null && other.iddistrict != null) || (this.iddistrict != null && !this.iddistrict.equals(other.iddistrict))) {
/* 90 */       return false;
/*    */     }
/* 92 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 97 */     return "entities.District[ iddistrict=" + this.iddistrict + " ]";
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\District.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */