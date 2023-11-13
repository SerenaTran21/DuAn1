package qlsmp.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import qlsmp.DB.DBHelper;
import qlsmp.Model.MauSac;

public class MauSacDAO {

    String SELECT_ALL_SQL = "SELECT * FROM MAUSAC";
    String DELETE_SQL = "DELETE FROM MAUSAC WHERE MaMau=?";
    String INSERT_SQL = "INSERT INTO MAUSAC(TenMau,HinhAnh,MaSP) VALUES(?,NULL,?)";
    String SELECT_BY_MaSP = "SELECT * FROM MAUSAC WHERE MaSP=?";
    String SELECT_MAU_NOT_IN_MASP = "select min(MaMau) as MaMau, TenMau, HinhAnh, min(MaSP) as MaSP \n"
            + "from MAUSAC \n"
            + "where TenMau not in (select TenMau from MAUSAC where MaSP = ?) \n"
            + "group by TenMau,HinhAnh";
    String SELECT_BY_MASP_TEN = "SELECT * FROM MAUSAC WHERE MaSP=? and TenMau = ?";
    String SELECT_MAMAU_INSERT = "SELECT TOP 1 WITH TIES MaMau,TenMau,HinhAnh,MaSP FROM MAUSAC ORDER BY MaMau DESC";

    public List<MauSac> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<MauSac> selectByMaSP(int id) {
        return this.selectBySql(SELECT_BY_MaSP, id);
    }

    public List<MauSac> selectMauNotInMaSP(int id) {
        return this.selectBySql(SELECT_MAU_NOT_IN_MASP, id);
    }

    public MauSac selectByMaSP_Ten(int maSP, String ten) {
        List<MauSac> list = this.selectBySql(SELECT_BY_MASP_TEN, maSP, ten);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public MauSac selectMaMauNext () {
        List<MauSac> list = this.selectBySql(SELECT_MAMAU_INSERT);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void Insert(MauSac enity) {
        DBHelper.update(INSERT_SQL, enity.getTenMau(), enity.getMaSP());
    }

    public void Delete(int id) {
        DBHelper.update(DELETE_SQL, id);
    }

    public List<MauSac> selectBySql(String sql, Object... args) {
        List<MauSac> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                MauSac enity = new MauSac();
                enity.setMaMau(rs.getInt("MaMau"));
                enity.setTenMau(rs.getString("TenMau"));
                enity.setHinh(rs.getString("HinhAnh"));
                enity.setMaSP(rs.getInt("MaSP"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
