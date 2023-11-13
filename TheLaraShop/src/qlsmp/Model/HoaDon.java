/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.Model;

import qlsmp.DAO.*;

/**
 *
 * @author My Laptop
 */
public class HoaDon {

    private int maHD;
    private String ngayTao;
    private float tongTien;
    private double sale;
    private float thanhTien;
    private boolean trangThai;
    private String IDAccount;
    private String IDKhachHang;


    public HoaDon() {
    }

    public HoaDon(int maHD, String ngayTao, float tongTien, double sale, float thanhTien, boolean trangThai, String IDAccount, String IDKhachHang) {
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.sale = sale;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
        this.IDAccount = IDAccount;
        this.IDKhachHang = IDKhachHang;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getIDAccount() {
        return IDAccount;
    }

    public void setIDAccount(String IDAccount) {
        this.IDAccount = IDAccount;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

}
