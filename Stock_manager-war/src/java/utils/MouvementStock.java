package utils;

import entities.Famille;
import entities.Lot;
import entities.Produit;

public class MouvementStock {

    private Famille famille;
    private Produit produit;
    private Lot lot;
    private int quantiteIn;
    private int quantiteOut;

    public Famille getFamille() {
        /* 25 */ return this.famille;
    }

    public void setFamille(Famille famille) {
        /* 29 */ this.famille = famille;
    }

    public Produit getProduit() {
        /* 33 */ return this.produit;
    }

    public void setProduit(Produit produit) {
        /* 37 */ this.produit = produit;
    }

    public Lot getLot() {
        /* 41 */ return this.lot;
    }

    public void setLot(Lot lot) {
        /* 45 */ this.lot = lot;
    }

    public int getQuantiteIn() {
        /* 49 */ return this.quantiteIn;
    }

    public void setQuantiteIn(int quantiteIn) {
        /* 53 */ this.quantiteIn = quantiteIn;
    }

    public int getQuantiteOut() {
        /* 57 */ return this.quantiteOut;
    }

    public void setQuantiteOut(int quantiteOut) {
        /* 61 */ this.quantiteOut = quantiteOut;
    }
}
