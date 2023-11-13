package qlsmp.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import qlsmp.Model.SanPham;
import qlsmp.DB.DBHelper;

public class SanPhamDAO {

    String SELECT_ALL_SQL = "SELECT * FROM SANPHAM";
    String INSERT_SQL = "INSERT INTO SANPHAM(TenSP,GiaBan,GiaNhap,Hinh,MaVachSP, MaLoai,MaThuongHieu)Values(?,?,?,?,NULL,?,?)";
    String UPDATE_SQL = "UPDATE SANPHAM SET TenSP=?, GiaBan=?, GiaNhap=?, Hinh=?, MaVachSP=NULL, MaLoai=?, MaThuongHieu=? WHERE MaSP=?";
    String DELETE_SQL = "DELETE FROM SANPHAM WHERE MaSP=?";
    String SELECT_BY_ID_SQL = "SELECT * FROM SANPHAM WHERE MaSP=?";

    public void insert(SanPham enity) {
        DBHelper.update(INSERT_SQL, enity.getTenSP(), enity.getGiaBan(), enity.getGiaNhap(), enity.getHinh(), enity.getMaVach(), enity.getMaLoai(), enity.getMaThuongHieu());
    }

    public void update(SanPham enity) {
        DBHelper.update(UPDATE_SQL, enity.getTenSP(), enity.getGiaBan(), enity.getGiaNhap(), enity.getHinh(), enity.getMaVach(), enity.getMaLoai(), enity.getMaThuongHieu(), enity.getMaSP());
    }

    public void delete(int id) {
        DBHelper.update(DELETE_SQL, id);
    }

    public SanPham selecteByID(int id) {
        List<SanPham> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<SanPham> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM SANPHAM WHERE TenSP LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    public List<SanPham> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                SanPham enity = new SanPham();
                enity.setMaSP(rs.getInt("MaSP"));
                enity.setTenSP(rs.getString("TenSP"));
                enity.setGiaBan(rs.getDouble("GiaBan"));
                enity.setGiaNhap(rs.getDouble("GiaNhap"));
                enity.setHinh(rs.getString("Hinh"));
                enity.setMaVach(rs.getString("MaVachSP"));
                enity.setMaLoai(rs.getInt("MaLoai"));
                enity.setMaThuongHieu(rs.getInt("MaThuongHieu"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
