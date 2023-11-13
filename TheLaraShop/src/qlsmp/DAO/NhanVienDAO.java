/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsmp.DAO;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.parser.Entity;
import qlsmp.DB.DBHelper;
import qlsmp.Model.NhanVien;

/**
 *
 * @author Anh Thu
 */
public class NhanVienDAO {

    String INSERT_SQL = "SET IDENTITY_INSERT NhanVien ON; INSERT INTO NhanVien(MaNV,TenNV,GioiTinh,NgaySinh,SDT, DiaChi, CCCD, Email, HinhAnh, Username, Password, ChucVu)VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET TenNV =?, GioiTinh=?, NgaySinh =?, SDT=?, DiaChi=?, CCCD=?, Email =?, HinhAnh =?,  Username =?, Password=?, ChucVu=? WHERE MaNV=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV = ?";

    public void insert(NhanVien entity) {
        DBHelper.update(INSERT_SQL, entity.getMaNV(), entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getDiaChi(), entity.getCCCD(), entity.getEmail(), entity.getHinhAnh(), entity.getUsername(), entity.getPassword(), entity.isChucVu());
    }

    public void update(NhanVien entity) {
        DBHelper.update(UPDATE_SQL, entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getDiaChi(), entity.getCCCD(), entity.getEmail(), entity.getHinhAnh(), entity.getUsername(), entity.getPassword(), entity.isChucVu(), entity.getMaNV());
    }

    public void delete(String id) {
        DBHelper.update(DELETE_SQL, id);

    }

    public NhanVien selectById(int id) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getInt("MaNV"));
                entity.setHoTen(rs.getString("TenNV"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setNgaySinh(rs.getString("NgaySinh"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setDienThoai(rs.getString("SDT"));
                entity.setCCCD(rs.getString("CCCD"));
                entity.setEmail(rs.getString("Email"));
                entity.setHinhAnh(rs.getString("HinhAnh"));
                entity.setUsername(rs.getString("Username"));
                entity.setPassword(rs.getString("Password"));
                entity.setChucVu(rs.getBoolean("ChucVu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<NhanVien> selectByKeyWord(String keyword) {
        String sql = "SELECT * FROM NhanVien WHERE TenNV LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    public static String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
