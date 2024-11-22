package Entity;

import DatabaseConnect.Connect;
import java.sql.*;
import javax.swing.JOptionPane;

public class LoaiSanPham {
    private final Connect cn = new Connect();

    // Truy vấn mã loại trong bảng LoaiSP
    public ResultSet ShowLoaiSP() {
        String sql = "SELECT maLoai FROM LoaiSP";
        ResultSet rs = null;

        try {
            Connection conn = cn.getConnection();
            if (conn != null) {
                PreparedStatement pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
            } else {
                JOptionPane.showMessageDialog(null, "Không thể kết nối đến CSDL");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn: " + e.getMessage());
        }

        return rs; // Trả về ResultSet chứa mã loại
    }

    // Truy vấn sản phẩm theo mã loại
    public ResultSet ShowSanpham(String ml) {
        String sql = "SELECT maSanPham, tenSanPham, moTa, donGia,donVi,maLoai FROM SanPham WHERE maLoai = ?";
        ResultSet rs = null;

        try {
            Connection conn = cn.getConnection();
            if (conn != null) {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, ml); // Thiết lập giá trị cho tham số trong câu lệnh SQL
                rs = pst.executeQuery();
            } else {
                JOptionPane.showMessageDialog(null, "Không thể kết nối đến CSDL");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi truy vấn sản phẩm: " + e.getMessage());
        }

        return rs; // Trả về ResultSet chứa danh sách sản phẩm theo loại
    }
}
