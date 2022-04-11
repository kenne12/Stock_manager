 package controllers.district;
 
 import controllers.district.AbstractDistrictController;
 import entities.District;
 import java.io.Serializable;
 import javax.annotation.PostConstruct;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ViewScoped;
 import org.primefaces.context.RequestContext;
 import utils.SessionMBean;
 import utils.Utilitaires;
 
 
 
 
 
 
 
 
 
 
 
 
 
 @ManagedBean
 @ViewScoped
 public class DistrictController
   extends AbstractDistrictController
   implements Serializable
 {
   @PostConstruct
   private void init() {
/*  33 */     this.district = new District();
   }
   
   public void prepareCreate() {
/*  37 */     if (!Utilitaires.isAccess(Long.valueOf(7L))) {
/*  38 */       notifyError("acces_refuse");
       
       return;
     } 
/*  42 */     this.mode = "Create";
/*  43 */     this.district = new District();
/*  44 */     RequestContext.getCurrentInstance().execute("PF('DistrictCreerDialog').show()");
   }
   
   public void prepareEdit() {
/*  48 */     if (!Utilitaires.isAccess(Long.valueOf(8L))) {
/*  49 */       notifyError("acces_refuse");
       
       return;
     } 
/*  53 */     if (this.district != null) {
/*  54 */       this.mode = "Edit";
/*  55 */       RequestContext.getCurrentInstance().execute("PF('DistrictCreerDialog').show()");
       return;
     } 
/*  58 */     notifyError("not_row_selected");
   }
   
   public void create() {
     try {
/*  63 */       if (this.mode.equals("Create")) {
/*  64 */         this.district.setIddistrict(this.districtFacadeLocal.nextVal());
/*  65 */         this.districtFacadeLocal.create(this.district);
         
/*  67 */         Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du district : " + this.district.getNom(), SessionMBean.getUserAccount());
/*  68 */         this.district = null;
/*  69 */         RequestContext.getCurrentInstance().execute("PF('DistrictCreerDialog').hide()");
/*  70 */         notifySuccess();
       }
/*  72 */       else if (this.district != null) {
/*  73 */         this.districtFacadeLocal.edit(this.district);
/*  74 */         RequestContext.getCurrentInstance().execute("PF('DistrictCreerDialog').hide()");
/*  75 */         notifySuccess();
       } else {
/*  77 */         notifyError("not_row_selected");
       }
     
/*  80 */     } catch (Exception e) {
/*  81 */       notifyFail(e);
     } 
   }
   
   public void delete() {
     try {
/*  87 */       if (this.district != null) {
/*  88 */         if (!Utilitaires.isAccess(Long.valueOf(9L))) {
/*  89 */           notifyError("acces_refuse");
           
           return;
         } 
/*  93 */         this.districtFacadeLocal.remove(this.district);
/*  94 */         Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de l'utilisateur : " + this.district.getNom(), SessionMBean.getUserAccount());
/*  95 */         this.district = null;
/*  96 */         notifySuccess();
       } else {
/*  98 */         notifyError("not_row_selected");
       } 
/* 100 */     } catch (Exception e) {
/* 101 */       notifyFail(e);
     } 
   }
   
   public void print() {
     try {
/* 107 */       if (!Utilitaires.isAccess(Long.valueOf(9L))) {
/* 108 */         notifyError("acces_refuse");
         return;
       } 
/* 111 */       RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
/* 112 */       RequestContext.getCurrentInstance().execute("PF('DistrictImprimerDialog').show()");
/* 113 */     } catch (Exception e) {
/* 114 */       notifyFail(e);
     } 
   }
   
   public void notifyError(String message) {
/* 119 */     RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
/* 120 */     this.routine.feedBack("avertissement", "/resources/tool_images/success.png", this.routine.localizeMessage(message));
/* 121 */     RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
   }
   
   public void notifySuccess() {
/* 125 */     RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
/* 126 */     this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
/* 127 */     RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
   }
   
   public void notifyFail(Exception e) {
/* 131 */     RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').show()");
/* 132 */     this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
/* 133 */     RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
   }
 }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\district\DistrictController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */