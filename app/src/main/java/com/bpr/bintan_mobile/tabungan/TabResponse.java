package com.bpr.bintan_mobile.tabungan;

import java.util.List;

public class TabResponse {
    private int kode;
    private String pesan;
    private List<TabModel> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<TabModel> getData() {
        return data;
    }

    public void setData(List<TabModel> data) {
        this.data = data;
    }
}
