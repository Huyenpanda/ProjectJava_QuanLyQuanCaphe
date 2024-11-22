package Entity;

import DatabaseConnect.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private String moTa;
    private float donGia;
    private String donVi;
    public String tenLoai;
    private String maLoai;
    private final Connect cn = new Connect();

    // Constructor
    public SanPham(String maSanPham, String tenSanPham, String moTa, float donGia, String donVi, String tenLoai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.donGia =  donGia;
        this.donVi = donVi;
        this.tenLoai = tenLoai;
    }
// Getter và Setter
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = (float) donGia;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }
     public String getTenLoai() {
        return tenLoai;
    }

    public void settenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    // Truy vấn tất cả loại sản phẩm
   public ResultSet ShowLoaiSP() throws SQLException {
    String sql = "SELECT * FROM LoaiSP";
    return Connect.getInstance().loadData(sql);
}


    // Truy vấn loại sản phẩm theo mã
    public ResultSet ShowLoaiSP(String ml) throws SQLException {
        String sql = "SELECT * FROM LoaiSP WHERE maLoai = ?";
        Connection conn = cn.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
          pst.setString(1, ml);
        return pst.executeQuery();
    }

    // Truy vấn tất cả sản phẩm
  public ResultSet ShowSanpham() throws SQLException {
    Connection conn = Connect.getConnection();
    if (conn == null) {
        throw new SQLException("Kết nối cơ sở dữ liệu thất bại");
    }
    String sql = "SELECT s.maLoai, s.maSanPham, s.tenSanPham, s.moTa, s.donVi, s.donGia, l.tenLoai " +
                 "FROM SanPham s " +
                 "JOIN LoaiSP l ON s.maLoai = l.maLoai";
    PreparedStatement ps = conn.prepareStatement(sql);
    return ps.executeQuery();
}



    // Truy vấn sản phẩm theo mã
  public ResultSet ShowSPTheoma(String ma) throws SQLException {
    String sql = "SELECT S.maSanPham, S.tenSanPham, S.moTa, S.donGia, S.donVi, L.maLoai, L.tenLoai " +
                 "FROM SanPham S INNER JOIN LoaiSP L ON S.maLoai = L.maLoai WHERE S.maSanPham = ?";
    Connection conn = cn.getConnection();
    PreparedStatement pst = conn.prepareStatement(sql);
    pst.setString(1, ma);
    return pst.executeQuery();
}


    // Truy vấn sản phẩm theo loại
    public ResultSet ShowSPTheoloai(String ml) throws SQLException {
        String sql = "SELECT S.maSanPham, S.tenSanPham, S.moTa, S.donGia, S.donVi, L.maLoai, L.tenLoai " +
                     "FROM SanPham S INNER JOIN LoaiSP L ON S.MaLoai = L.MaLoai WHERE L.MaLoai = ?";
        Connection conn = cn.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, ml);
        return pst.executeQuery();
    }

    // Truy vấn sản phẩm theo khoảng giá
    public ResultSet ShowSPTheogia(float giatu, float giaden) throws SQLException {
        String sql = "SELECT S.maSanPham, S.tenSanPham, S.moTa, S.donGia, S.donVi, L.maLoai, L.tenLoai " +
                     "FROM SanPham S INNER JOIN LoaiSP L ON S.MaLoai = L.MaLoai " +
                     "WHERE S.DonGia BETWEEN ? AND ?";
        Connection conn = cn.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setFloat(1,  giatu);
        pst.setFloat(2, giaden);
        return pst.executeQuery();
    }

    // Truy vấn sản phẩm theo tên
    public ResultSet ShowSPTheoten(String ten) throws SQLException {
        String sql = "SELECT S.maSanPham, S.tenSanPham, S.moTa, S.donGia, S.donVi, L.maLoai, L.tenLoai " +
                     "FROM SanPham S INNER JOIN LoaiSP L ON S.MaLoai = L.MaLoai WHERE S.tenSanPham LIKE ?";
        Connection conn = cn.getConnection();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + ten + "%");
        return pst.executeQuery();
    }

    // Thêm sản phẩm mới
    // Thêm một dòng dữ liệu vào bảng Sanpham
   public void InsertSanpham(String ma, String ten, float dg, String dv, String moTa, String ml) throws SQLException {
    String sql = "INSERT INTO SanPham (maSanPham, tenSanPham, donGia, donVi, moTa, maLoai) VALUES (?, ?, ?, ?, ?, ?)";
    Connection conn = cn.getConnection();
    PreparedStatement pst = conn.prepareStatement(sql);
    pst.setString(1, ma);
    pst.setString(2, ten);
    pst.setFloat(3, dg);
    pst.setString(4, dv);
    pst.setString(5, moTa);
    pst.setString(6, ml);
    pst.executeUpdate();
}




      // Cập nhật một dòng dữ liệu vào bảng Sanpham
   public void EditSanpham(String ma, String ten, float dg, String dv, String moTa, String ml) throws SQLException {
    String sql = "UPDATE SanPham SET tenSanPham = ?, donGia = ?, donVi = ?, moTa = ?, maLoai = ? WHERE maSanPham = ?";
    Connection conn = cn.getConnection();
    PreparedStatement pst = conn.prepareStatement(sql);
    pst.setString(1, ten);
    pst.setFloat(2, dg);
    pst.setString(3, dv);
    pst.setString(4, moTa);
    pst.setString(5, ml);
    pst.setString(6, ma);
    pst.executeUpdate();
}


    // Xóa sản phẩm
    public void DeleteSanpham(String ma) throws SQLException {
    String sql = "DELETE FROM SanPham WHERE maSanPham = ?";
    Connection conn = cn.getConnection();
    PreparedStatement pst = conn.prepareStatement(sql);
    pst.setString(1, ma);
    pst.executeUpdate();
}

}
