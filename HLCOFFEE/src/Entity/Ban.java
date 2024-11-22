/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Huyen
 */
public class Ban {
    private int id; // ID của bàn, tương ứng với jButtonBan1 đến jButtonBan16
    private String status; // Trạng thái của bàn: "trống", "có khách", "đang chọn", "đặt trước"

    // Constructor
    public Ban(int id, String status) {
        this.id = id;
        this.status = status;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
