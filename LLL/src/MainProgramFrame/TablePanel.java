package MainProgramFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class TablePanel extends JPanel {
    private static TablePanel instance;
    private JTable table;
    private long lastModified;

    public TablePanel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("도서제목");
        model.addColumn("회원명");
        model.addColumn("대여일자");
        model.addColumn("반납예정일");
        model.addColumn("대여여부");

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        scrollPane.setPreferredSize(new Dimension(950, 800));

        // 파일을 주기적으로 읽어오는 스레드 실행
        startFileWatcher();
    }

    public static TablePanel getInstance() {
        if (instance == null) {
            instance = new TablePanel();
        }
        return instance;
    }

    public JTable getTable() {
        return table;
    }

    public void addBook(String title) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{title, "", "", "", "대여가능"}); // 대여 여부를 "대여가능"으로 설정
    }
    
    public void removeBook(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(rowIndex);
    }

    private void startFileWatcher() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                File file = new File("booklist.txt");
                long lastModified = file.lastModified();

                // 파일이 변경된 경우에만 파일을 다시 읽어옴
                if (lastModified > TablePanel.this.lastModified) {
                    TablePanel.this.lastModified = lastModified;
                    readFile();
                }
            }
        };

        // 주기적으로 스케줄링
        Timer timer = new Timer();
        timer.schedule(task, 0, 5000); // 5초마다 실행
    }

    private void readFile() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // 기존 내용 삭제

        try (BufferedReader reader = new BufferedReader(new FileReader("booklist.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 쉼표를 기준으로 문자열을 분할하여 첫 번째 요소만 사용
                String[] parts = line.split(",");
                String title = parts[0];
                addBook(title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}