package qlsmp.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.LoaiSP;

public class LoaiSPDAO {

    String SELECT_ALL_SQL = "SELECT * FROM LOAISP";
    String SELECT_BY_ID = "SELECT * FROM LOAISP WHERE MaLoai=?";

    public List<LoaiSP> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<LoaiSP> selectByID(int id) {
        return this.selectBySql(SELECT_BY_ID, id);
    }

    public List<LoaiSP> selectBySql(String sql, Object... args) {
        List<LoaiSP> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                LoaiSP enity = new LoaiSP();
                enity.setMaLoai(rs.getInt("MaLoai"));
                enity.setTenLoai(rs.getString("TenLoai"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
