package DatabaseConnect;

import Entity.SanPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.logging.Logger;

public class Connect {
    private static Connect instance;
    private Connection con = null;
    private static final Logger logger = Logger.getLogger(Connect.class.getName());

    // Tự động kết nối khi tạo đối tượng Connect
    public Connect() {
        connectSQL();
    }

    // Phương thức kiểm tra và mở kết nối nếu chưa mở
    public static Connection getConnection() {
        if (instance == null) {
            instance = new Connect();
        }
        try {
            if (instance.con == null || instance.con.isClosed()) {
                instance.connectSQL();  // Tạo lại kết nối nếu nó đã bị đóng
            }
        } catch (SQLException ex) {
            logger.severe("Error checking connection: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error checking connection: " + ex.getMessage());
        }
        return instance.con;
    }

    // Phương thức thiết lập kết nối SQL Server
    public void connectSQL() {
        try {
            // Thông tin kết nối
            String sqlURL = "jdbc:sqlserver://localhost:1433;databaseName=HLCOFFEE;encrypt=false;useUnicode=true;characterEncoding=UTF-8;";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(sqlURL, "sa", "1235");
        } catch (Exception ex) {
            logger.severe("Connection error: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error(Connect): " + ex.getMessage());
        }
    }

    // Lấy đối tượng Connection hiện tại
    public static Connect getInstance() {
        if (instance == null) {
            instance = new Connect();
        }
        return instance;
    }

    // Phương thức để thực hiện các truy vấn SELECT và trả về ResultSet
    public ResultSet loadData(String sql) throws SQLException {
        Statement stmt = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery(sql);
    }

    // Phương thức để thực hiện các truy vấn INSERT, UPDATE, DELETE
    public void updateData(String sql, Object... params) {
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error(updateData): " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error(updateData): " + e.getMessage());
        }
    }

    // Lấy danh sách tất cả sản phẩm (cập nhật đầy đủ thuộc tính)
    public List<SanPham> getAllSanPham() {
        List<SanPham> sanPhams = new ArrayList<>();
        String sql = "SELECT s.maSanPham, s.tenSanPham, s.moTa, s.donGia, s.donVi, l.tenLoai " +
                     "FROM SanPham s " +
                     "JOIN LoaiSP l ON s.maLoai = l.maLoai " +
                     "ORDER BY s.maSanPham ASC";

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String tenSanPham = rs.getString("tenSanPham");
                String moTa = rs.getString("moTa");
                float donGia = rs.getFloat("donGia");
                String donVi = rs.getString("donVi");
                String tenLoai = rs.getString("tenLoai");

                // Tạo đối tượng SanPham và thêm vào danh sách
                sanPhams.add(new SanPham(maSanPham, tenSanPham, moTa, donGia, donVi, tenLoai));
            }
        } catch (SQLException e) {
            logger.severe("Error(getAllSanPham): " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error(getAllSanPham): " + e.getMessage());
        }
        return sanPhams;
    }

    // Lấy tên nhân viên dựa trên tài khoản
    public String getTenNhanVien(String tenTaiKhoan) {
        String tenNhanVien = null;
        String sql = "SELECT tenNhanVien FROM NhanVien WHERE tenTaiKhoan = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, tenTaiKhoan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tenNhanVien = rs.getString("tenNhanVien");
                }
            }
        } catch (SQLException e) {
            logger.severe("Error(getTenNhanVien): " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error(getTenNhanVien): " + e.getMessage());
        }
        return tenNhanVien;
    }

    // Lấy số lượng hóa đơn
    public int getSoLuongHoaDon() {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) FROM HoaDon";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                soLuong = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.severe("Error(getSoLuongHoaDon): " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error(getSoLuongHoaDon): " + e.getMessage());
        }
        return soLuong;
    }

    // Thêm hóa đơn mới
    public boolean themHoaDonMoi(String maHoaDon, String nguoiTao, String thoiGianTao, String trangThai, String ghiChu) {
        boolean isSuccess = false;
        String sql = "INSERT INTO HoaDon (MaHD, NguoiTao, KhachHang, TGTao, TrangThai, GhiChu) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, maHoaDon);
            ps.setString(2, nguoiTao);
            ps.setString(3, null); // Khách hàng tạm thời để trống
            ps.setString(4, thoiGianTao);
            ps.setString(5, trangThai);
            ps.setString(6, ghiChu);
            isSuccess = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.severe("Error(themHoaDonMoi): " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error(themHoaDonMoi): " + e.getMessage());
        }
        return isSuccess;
    }

    // Tìm kiếm sản phẩm
    public List<SanPham> timKiemSanPham(String tenSanPham, String tenLoai, Double giaMin, Double giaMax) {
        List<SanPham> sanPhams = new ArrayList<>();
        String sql = "SELECT s.maSanPham, s.tenSanPham, s.moTa, s.donGia, s.donVi, l.tenLoai " +
                     "FROM SanPham s " +
                     "JOIN LoaiSP l ON s.maLoai = l.maLoai WHERE 1=1";

        if (tenSanPham != null && !tenSanPham.isEmpty()) {
            sql += " AND s.tenSanPham LIKE ?";
        }
        if (tenLoai != null && !tenLoai.isEmpty()) {
            sql += " AND l.tenLoai = ?";
        }
        if (giaMin != null) {
            sql += " AND s.donGia >= ?";
        }
        if (giaMax != null) {
            sql += " AND s.donGia <= ?";
        }

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            int paramIndex = 1;
            if (tenSanPham != null && !tenSanPham.isEmpty()) {
                stmt.setString(paramIndex++, "%" + tenSanPham + "%");
            }
            if (tenLoai != null && !tenLoai.isEmpty()) {
                stmt.setString(paramIndex++, tenLoai);
            }
            if (giaMin != null) {
                stmt.setDouble(paramIndex++, giaMin);
            }
            if (giaMax != null) {
                stmt.setDouble(paramIndex++, giaMax);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String maSanPham = rs.getString("maSanPham");
                    String tenSP = rs.getString("tenSanPham");
                    String moTa = rs.getString("moTa");
                    float donGia = rs.getFloat("donGia");
                    String donVi = rs.getString("donVi");
                    String tenLoaiSP = rs.getString("tenLoai");

                    SanPham sanPham = new SanPham(maSanPham, tenSP, moTa, donGia, donVi, tenLoaiSP);
                    sanPhams.add(sanPham);
                }
            }
        } catch (SQLException e) {
            logger.severe("Error(timKiemSanPham): " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error(timKiemSanPham): " + e.getMessage());
        }
        return sanPhams;
    }
}
