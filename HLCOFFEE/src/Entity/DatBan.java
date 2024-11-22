/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Huyen
 */public class DatBan {
    private String maDatBan;
    private String maKhachHang;
    private String maBan;
    private String tuGio;
    private String denGio;
    private String ghiChu;
    private String thanhToan;
    private String traTruoc;

    // Constructor
    public DatBan(String maDatBan, String maKhachHang, String maBan, String tuGio, String denGio, String ghiChu, String thanhToan, String traTruoc) {
        this.maDatBan = maDatBan;
        this.maKhachHang = maKhachHang;
        this.maBan = maBan;
        this.tuGio = tuGio;
        this.denGio = denGio;
        this.ghiChu = ghiChu;
        this.thanhToan = thanhToan;
        this.traTruoc = traTruoc;
    }

    // Getters và setters
    public String getMaDatBan() {
        return maDatBan;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getMaBan() {
        return maBan;
    }

    public String getTuGio() {
        return tuGio;
    }

    public String getDenGio() {
        return denGio;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public String getThanhToan() {
        return thanhToan;
    }

    public String getTraTruoc() {
        return traTruoc;
    }
}
