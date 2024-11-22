/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Huyen
 */

import DatabaseConnect.Connect;
import Entity.Ban;
import Entity.DatBan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BanDAO {
    /**
     * Lấy danh sách tất cả các bàn từ cơ sở dữ liệu.
     * @return List<Ban> Danh sách các đối tượng Ban
     */
    private Connection conn = Connect.getConnection(); 
    public static List<Ban> getAllBans() {
        List<Ban> list = new ArrayList<>();
        String query = "SELECT * FROM tblBan"; // Câu lệnh SQL

        try (ResultSet rs = Connect.getInstance().loadData(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                list.add(new Ban(id, status)); // Tạo đối tượng Ban từ dữ liệu trong ResultSet
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Thêm một bàn mới vào cơ sở dữ liệu.
     * @param ban Đối tượng Ban cần thêm
     */
    public static void save(Ban ban) {
        String query = "INSERT INTO tblBan (id, status) VALUES (?, ?)";
        try {
            Connect.getInstance().updateData(query, ban.getId(), ban.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cập nhật trạng thái của bàn.
     * @param ban Đối tượng Ban chứa thông tin cần cập nhật
     */
    public static void update(Ban ban) {
        String query = "UPDATE tblBan SET status = ? WHERE id = ?";
        try {
            Connect.getInstance().updateData(query, ban.getStatus(), ban.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Xóa một bàn khỏi cơ sở dữ liệu.
     * @param id ID của bàn cần xóa
     */
    public static void delete(int id) {
        String query = "DELETE FROM tblBan WHERE id = ?";
        try {
            Connect.getInstance().updateData(query, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Cập nhật trạng thái bàn sau khi đặt
public void insertDatBan(DatBan datBan) throws SQLException {
    if (conn == null || conn.isClosed()) {
        conn = Connect.getConnection(); // Mở lại kết nối nếu nó đã đóng
    }

    String query = "INSERT INTO DatBan (maDatBan, maKhachHang, maBan, tuGio, denGio, ghiChu, thanhToan, traTruoc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, datBan.getMaDatBan());
        pst.setString(2, datBan.getMaKhachHang());
        pst.setString(3, datBan.getMaBan());
        pst.setString(4, datBan.getTuGio());
        pst.setString(5, datBan.getDenGio());
        pst.setString(6, datBan.getGhiChu());
        pst.setString(7, datBan.getThanhToan());
        pst.setString(8, datBan.getTraTruoc());
        pst.executeUpdate();
    }
}

public void updateBanStatus(String maBan, String trangThai) throws SQLException {
    if (conn == null || conn.isClosed()) {
        conn = Connect.getConnection(); // Mở lại kết nối nếu nó đã đóng
    }

    String query = "UPDATE Ban SET trangThaiBan = ? WHERE maBan = ?";
    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, trangThai);
        pst.setString(2, maBan);
        pst.executeUpdate();
    }
}

    public String getMaKhachHangFromDatabase(String sdt) {
    String maKhachHang = null;
    String query = "SELECT maKhachHang FROM KhachHang WHERE sdt = ?";
    
    try (Connection conn = Connect.getConnection();
         PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, sdt);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            maKhachHang = rs.getString("maKhachHang");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return maKhachHang;
}

}


