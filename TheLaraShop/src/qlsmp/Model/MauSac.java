
package qlsmp.Model;

public class MauSac {
    private String tenMau,hinh;
    private int maMau,maSP;

    public MauSac(String tenMau, String hinh, int maMau, int maSP) {
        this.tenMau = tenMau;
        this.hinh = hinh;
        this.maMau = maMau;
        this.maSP = maSP;
    }

    public MauSac() {
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
    
}
