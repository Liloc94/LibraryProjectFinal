package MainProgramFrame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookSearchImg extends JPanel {
    private int imageHeight;

    public BookSearchImg(String imagePath) {
        // ImageIcon을 생성하여 이미지를 로드합니다.
        ImageIcon icon = new ImageIcon(imagePath);
        imageHeight = icon.getIconHeight();

        // JLabel을 생성하고 이미지 아이콘을 설정합니다.
        JLabel label = new JLabel(icon);

        // JLabel을 패널에 추가합니다.
        add(label);
    }

    public int getImageHeight() {
        return imageHeight;
    }
}