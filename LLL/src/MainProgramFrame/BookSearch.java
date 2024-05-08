package MainProgramFrame;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class BookSearch extends JPanel {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField priceField;
    private JTextField publisherField;

    public BookSearch() {
        setLayout(new GridLayout(2, 2, 50, 10));

        // 제목 정보 패널 생성 및 추가
        BookInfoPanel titlePanel = new BookInfoPanel("제목");
        add(titlePanel);

        // 저자 정보 패널 생성 및 추가
        BookInfoPanel authorPanel = new BookInfoPanel("저자");
        add(authorPanel);

        // 가격 정보 패널 생성 및 추가
        BookInfoPanel pricePanel = new BookInfoPanel("가격");
        add(pricePanel);

        BookInfoPanel publisherPanel = new BookInfoPanel("출판사");
        add(publisherPanel);

        // 각 패널에서 필드를 가져와서 설정
        titleField = titlePanel.getTextField();
        authorField = authorPanel.getTextField();
        priceField = pricePanel.getTextField();
        publisherField = publisherPanel.getTextField();
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getAuthorField() {
        return authorField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getPublisherField() {
        return publisherField;
    }
}