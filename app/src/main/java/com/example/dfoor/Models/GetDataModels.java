package com.example.dfoor.Models;

public class GetDataModels {
    public int gImg;
    public int gHarga;

    public String gTitle, gDesc;

    public GetDataModels() {

    }

    public int getgImg() {
        return gImg;
    }

    public void setgImg(int gImg) {
        this.gImg = gImg;
    }

    public int getgHarga() {
        return gHarga;
    }

    public void setgHarga(int gHarga) {
        this.gHarga = gHarga;
    }

    public String getgTitle() {
        return gTitle;
    }

    public void setgTitle(String gTitle) {
        this.gTitle = gTitle;
    }

    public GetDataModels(int gImg, int gHarga, String gTitle, String gDesc) {
        this.gImg = gImg;
        this.gHarga = gHarga;
        this.gTitle = gTitle;
        this.gDesc = gDesc;
    }

    public String getgDesc() {
        return gDesc;
    }

    public void setgDesc(String gDesc) {
        this.gDesc = gDesc;
    }
}
