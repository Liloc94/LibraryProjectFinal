package MainProgramFrame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BookSearchFrame extends JFrame {
    private JTable bookTable;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField priceField;
    private BookSearch bookSearch;

    public BookSearchFrame(BookSearch bookSearch) {
        setTitle("도서 검색");
        setSize(600, 400);
        setLocationRelativeTo(null); // 화면 중앙에 표시

        this.bookSearch = bookSearch;

        // 도서 리스트를 표시할 모델 생성
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("제목");
        model.addColumn("저자");
        model.addColumn("가격");
        model.addColumn("출판사");
        bookTable = new JTable(model);

        String filePath = "booklist.txt";

        // 파일에서 도서 데이터를 읽어와서 테이블에 추가
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 4) {
                    model.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3]});
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(bookTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // 확인 버튼 생성 및 리스너 추가
        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 선택된 행의 정보 가져오기
                int selectedRow = bookTable.getSelectedRow();
                if (selectedRow != -1) {
                    String title = (String) bookTable.getValueAt(selectedRow, 0);
                    String author = (String) bookTable.getValueAt(selectedRow, 1);
                    String price = (String) bookTable.getValueAt(selectedRow, 2);
                    String publisher = (String) bookTable.getValueAt(selectedRow, 3);

                    // BookSearch에 정보 설정
                    bookSearch.getTitleField().setText(title);
                    bookSearch.getAuthorField().setText(author);
                    bookSearch.getPriceField().setText(price);
                    bookSearch.getPublisherField().setText(publisher);

                    // 책 이미지 불러오기
                    SwingMain.loadBookImage("src/MainFrameImg/" + title + ".jpg");
                }
                // 도서 검색 창 닫기
                dispose();
            }
        });

        // 패널 생성 및 버튼 추가
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(confirmButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // 닫기 버튼 추가
        JButton closeButton = new JButton("닫기");
        closeButton.addActionListener(e -> dispose()); // 창 닫기
        buttonPanel.add(closeButton);

        setVisible(true); // 프레임을 보이도록 설정
    }

    public static void main(String[] args) {
        BookSearch bookSearch = new BookSearch();
        new BookSearchFrame(bookSearch);
    }
}