package com.bpr.bintan_mobile.tabungan;

public class TabModel {
    private int id;
    private String cif, norek, nama, tabungan, saldo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = Integer.parseInt(String.valueOf(id));
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTabungan() {
        return tabungan;
    }

    public void setTabungan(String tabungan) {
        this.tabungan = tabungan;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
}
