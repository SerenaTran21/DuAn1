package qlsmp.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import qlsmp.DB.DBHelper;
import qlsmp.Model.ThuongHieu;

public class ThuongHieuDAO {

    String SELECT_ALL_SQL = "SELECT * FROM THUONGHIEU";
    String SELECT_BY_ID = "SELECT * FROM THUONGHIEU where MaThuongHieu=?";

    public List<ThuongHieu> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<ThuongHieu> selectByID(int id) {
        return this.selectBySql(SELECT_BY_ID, id);
    }

    public List<ThuongHieu> selectBySql(String sql, Object... args) {
        List<ThuongHieu> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                ThuongHieu enity = new ThuongHieu();
                enity.setMaTH(rs.getInt("MaThuongHieu"));
                enity.setTenTH(rs.getString("TenThuongHieu"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
