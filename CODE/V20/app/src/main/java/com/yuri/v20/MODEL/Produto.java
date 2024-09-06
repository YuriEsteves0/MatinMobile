package com.yuri.v20.MODEL;
public class Produto {
    private String nomeProd;
    private double precoProd;
    private int imagemResId;

    public Produto(String nomeProd, double precoProd, int imagemResId) {
        this.nomeProd = nomeProd;
        this.precoProd = precoProd;
        this.imagemResId = imagemResId;
    }

    public Produto() {
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public double getPrecoProd() {
        return precoProd;
    }

    public void setPrecoProd(double precoProd) {
        this.precoProd = precoProd;
    }

    public int getImagemResId() {
        return imagemResId;
    }

    public void setImagemResId(int imagemResId) {
        this.imagemResId = imagemResId;
    }
}
