package MainProgramFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

class MainPanel extends JPanel {
    public MainPanel(int imageHeight) {
    	 

         // 각 패널의 높이를 이미지 높이에 맞게 설정
         int panelHeight = imageHeight / 3;

         // 이름 정보 패널 생성 및 추가
         MemberInfoPanel namePanel = new MemberInfoPanel("이름");
         namePanel.setPreferredSize(new Dimension(200, panelHeight)); // 패널 크기 설정
         add(namePanel);

         // 생년월일 정보 패널 생성 및 추가
         MemberInfoPanel birthPanel = new MemberInfoPanel("생년월일");
         birthPanel.setPreferredSize(new Dimension(200, panelHeight)); // 패널 크기 설정
         add(birthPanel);

         // 연락처 정보 패널 생성 및 추가
         MemberInfoPanel phonePanel = new MemberInfoPanel("연락처");
         phonePanel.setPreferredSize(new Dimension(200, panelHeight)); // 패널 크기 설정
         add(phonePanel);
    }
}