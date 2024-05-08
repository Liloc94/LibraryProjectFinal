package MainProgramFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

import AccountDB.LoginLogic;

public class SwingMain {
    private static String accountNum;
    private static JFrame frame;
    private static BookSearchImg currentImage;


    public static String getAccountNum() {
        return accountNum;
    }
    
    public SwingMain(String loggedinAccountNum) {
    	accountNum = loggedinAccountNum;
    }
    
    public SwingMain(BookSearch bookSearch) {
        frame = new JFrame("도서 관리 프로그램");
        frame.setSize(1500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // 레이아웃 매니저를 사용하지 않음

        LineBorder border = new LineBorder(Color.BLACK);

        // 테이블 패널 생성 및 위치 지정
        TablePanel tablePanel = new TablePanel();
        tablePanel.setBounds(10, 70, 950, 800);
        frame.add(tablePanel);

        JButton bookSearchButton = new JButton("도서 상세 정보");
        bookSearchButton.setBounds(1075, 75, 300, 30); // 이미지 바로 위에 위치하도록 지정
        frame.add(bookSearchButton);

        bookSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 새로운 BookSearchFrame 인스턴스 생성 및 표시
                new BookSearchFrame(bookSearch);
            }
        });

        bookSearch.setBounds(1010, 600, 400, 170);
        frame.add(bookSearch);

        // BookTitleSearch 패널 생성 및 위치 지정
        BookTitleSearch bookTitleSearch = new BookTitleSearch(tablePanel.getTable());
        bookTitleSearch.setBounds(10, 20, 200, 100);
        frame.add(bookTitleSearch);

        RentalButton rentalButton = new RentalButton(tablePanel.getTable());
        rentalButton.setBounds(970, 820, 500, 50);
        frame.add(rentalButton);

        // 종료 버튼 생성 및 위치 지정
        ExitButton exitButton = new ExitButton();
        frame.add(exitButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        BookSearch bookSearch = new BookSearch();
        new SwingMain(bookSearch);
    }

    // 종료 버튼 클래스
    public class ExitButton extends JButton implements ActionListener {
        public ExitButton() {
            super("종료");
            addActionListener(this);
            setBounds(1350, 900, 80, 25); // 버튼의 크기와 위치 설정
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void loadBookImage(String imagePath) {
    	if(currentImage != null) {
    		frame.remove(currentImage);
    		frame.revalidate();
    		frame.repaint();
    	}
        currentImage = new BookSearchImg(imagePath);
        currentImage.setBounds(1100, 150, 250, 380); // 이미지를 원하는 위치로 이동
        frame.add(currentImage); // 이미지 패널을 프레임의 콘텐츠 패널로 설정
        frame.revalidate(); // 프레임을 다시 그리도록 갱신
        frame.repaint();
    }
}