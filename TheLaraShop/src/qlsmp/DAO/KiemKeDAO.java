/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.KiemKe;

public class KiemKeDAO extends ShopMyPhamDAO<KiemKe, String> {

    String INSERT_SQL = "INSERT INTO KIEMKE(KyKiemKe,NgayTaoKiemKe,MaKiemKe,MucDich, KetLuan,DaXuLy)Values(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KIEMKE SET NgayTaoKiemKe=?,KyKiemKe=?,MucDich=?,KetLuan=?,DaXuLy=? WHERE MaKiemKe=?,";
    String DELETE_SQL = " DELETE FROM CHITIETKIEMKE WHERE MaKiemKe = ?"
                      + " DELETE FROM KIEMKE WHERE MaKiemKe = ? ";
    String SELECT_ALL_SQL = "SELECT MaKiemKe, NgayTaoKiemKe, KyKiemKe, MucDich, KetLuan,DaXuLy FROM KIEMKE";
    String SELECT_BY_NGAYTAO = "SELECT * FROM KIEMKE WHERE NgayTaoKiemKe=?";
    String SELECT_BY_ID_SQL = "SELECT * FROM KIEMKE WHERE MaKiemKe=?";
    String SELECT_CHITIET = "SELECT ";

    @Override
    public void insert(KiemKe enity) {
        DBHelper.update(INSERT_SQL, enity.getKyKiemKe(), enity.getNgayTaoKiemKe(), enity.getMaKiemKe(), enity.getMucDich(), enity.getKetLuan(), enity.isDaXuLy());
    }

    @Override
    public void update(KiemKe enity) {
        DBHelper.update(UPDATE_SQL, enity.getNgayTaoKiemKe(), enity.getKyKiemKe(), enity.getMucDich(), enity.getKetLuan(), enity.isDaXuLy(), enity.getMaKiemKe());
    }

    @Override
    public void delete(int id) {
        DBHelper.update(DELETE_SQL, id, id);
    }

    @Override
    public KiemKe selecteByID(int id) {
        List<KiemKe> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
// để tìm kiếm

    @Override
    public List<KiemKe> selectBySql(String sql, Object... args) {
        List<KiemKe> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                KiemKe enity = new KiemKe();
                enity.setMaKiemKe(rs.getInt("MaKiemKe"));
                enity.setNgayTaoKiemKe(rs.getString("NgayTaoKiemKe"));
                enity.setKyKiemKe(rs.getString("KyKiemKe"));
                enity.setMucDich(rs.getString("MucDich"));
                enity.setKetLuan(rs.getString("KetLuan"));
                enity.setDaXuLy(rs.getBoolean("DaXuLy"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    @Override
    public List<KiemKe> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KIEMKE WHERE TenSP LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    @Override
    public List<KiemKe> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<KiemKe> selectByNgayTao(String ngayTao) {
        return this.selectBySql(SELECT_BY_NGAYTAO, ngayTao);
    }

//    public List<Object[]> getChiTietKiemKe(int IDKiemKe) {
//        String SQL = "{CALL sp_ChiTietKiemKe(?)}";
//        String[] cols = {"MaSP", "TenSP","VungKiemKe","SoLuongKiemToan","SoLuongThuc" ,"ChenhLech"};
//        return this.getListOfArray(SQL, cols, IDKiemKe);
//    }
    //NHÁP
    public List<Object[]> getChiTietKiemKe(int IDKiemKe) {
        String SQL = "{CALL sp_ChiTietKiemKe(?)}";
        String[] cols = {"MaSP", "TenSP", "VungKiemKe", "SoLuongThuc"};
        return this.getListOfArray(SQL, cols, IDKiemKe);
    }
}
