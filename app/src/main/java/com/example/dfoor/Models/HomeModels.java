package com.example.dfoor.Models;

import java.util.Comparator;

public class HomeModels {
    public String title,des;
    public int img;

    public HomeModels(String title, String des, int img) {
        this.title = title;
        this.des = des;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public static final Comparator<HomeModels> BY_TITLE_ASCENDING = new Comparator<HomeModels>() {
        @Override
        public int compare(HomeModels homeModels, HomeModels t1) {
            return homeModels.getTitle().compareTo(t1.getTitle());
        }
    };

    public static final Comparator<HomeModels> BY_TITLE_DESCENDING = new Comparator<HomeModels>() {
        @Override
        public int compare(HomeModels homeModels, HomeModels t1) {
            return t1.getTitle().compareTo(homeModels.getTitle());
        }
    };
}
