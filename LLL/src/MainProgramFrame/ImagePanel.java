package MainProgramFrame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ImagePanel extends JPanel {
	private int imageHeight;
	
	 public ImagePanel() {
	        // 이미지 경로 설정
	        String imagePath = "C:\\Users\\admin\\Desktop\\Book\\Book\\Image\\default (1).png";

	        // ImageIcon을 이용하여 이미지를 생성
	        ImageIcon icon = new ImageIcon(imagePath);

	        // 이미지 높이 설정
	        imageHeight = icon.getIconHeight();

	        // JLabel에 이미지 아이콘 설정
	        JLabel label = new JLabel(icon);

	        // 패널에 이미지 레이블 추가
	        add(label);
	    }

	    public int getImageHeight() {
	        return imageHeight;
	    }
	}
