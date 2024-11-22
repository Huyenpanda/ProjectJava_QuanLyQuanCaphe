package Controller;

import DAO.BanDAO;
import Entity.Ban;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Ban_Controller {

    // Mảng chứa đường dẫn các ảnh theo trạng thái
    private static final String[] ICON_PATHS = {
        "icon_BanTrong.png",      // Index 0: Trạng thái "trống"
        "icon_BanDangDat.png",   // Index 1: Trạng thái "đang chọn"
        "icon_BanDaCoKhach.png", // Index 2: Trạng thái "đang sử dụng"
        "icon_Ban_da_dat.png"         // Index 3: Trạng thái "đã đặt"
    };

    public static void updateButtonIcons(List<JButton> buttons) {
        List<Ban> bans = BanDAO.getAllBans(); // Lấy danh sách bàn từ DAO

        for (Ban ban : bans) {
            int index = ban.getId() - 1; // Tìm vị trí nút tương ứng với ID bàn
            if (index >= 0 && index < buttons.size()) {
                JButton button = buttons.get(index);

                // Chọn đường dẫn ảnh dựa vào trạng thái
                String iconPath = null;
                switch (ban.getStatus()) {
                    case "trống":
                        iconPath = ICON_PATHS[0];
                        break;
                    case "đang chọn":
                        iconPath = ICON_PATHS[1];
                        break;
                    case "đang sử dụng":
                        iconPath = ICON_PATHS[2];
                        break;
                    case "đã đặt":
                        iconPath = ICON_PATHS[3];
                        break;
                }

                // Nếu đường dẫn hợp lệ, đặt ảnh vào nút
                if (iconPath != null) {
                    ImageIcon icon = new ImageIcon(Ban_Controller.class.getResource(iconPath));
                    Image scaledImg = icon.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH);
                    button.setIcon(new ImageIcon(scaledImg));
                }
            }
        }
    }
}
