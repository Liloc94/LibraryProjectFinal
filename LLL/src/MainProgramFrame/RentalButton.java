package MainProgramFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.table.DefaultTableModel;

import AccountDB.LoginLogic;
import screen.AccountInfoFrame;

public class RentalButton extends JPanel {
    private JTable table;

    public RentalButton(JTable table) {
        this.table = table;
        // FlowLayout을 사용하여 패널 설정
        setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10)); // 왼쪽 정렬, 간격 10

        // 버튼 생성
        JButton rentalButton = new JButton("대여");
        JButton returnButton = new JButton("반납");
        JButton registButton = new JButton("도서 등록");
        JButton deleteButton = new JButton("도서 삭제");
        JButton accountInfoButton = new JButton("회원 정보");
        
        registButton.setEnabled(false);
        deleteButton.setEnabled(false);
        if(LoginLogic.getUserKeyValue().equals("000000")) {
        	registButton.setEnabled(true);
        	deleteButton.setEnabled(true);
        }

        // 대여 버튼 액션 리스너 추가
        rentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentBook();
            }
        });

        // 반납 버튼 액션 리스너 추가
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        // 도서 등록 버튼 액션 리스너 추가
        registButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 사용자 정의 다이얼로그 생성
                JTextField titleField = new JTextField();
                JTextField authorField = new JTextField();
                JTextField priceField = new JTextField();
                JTextField publisherField = new JTextField();

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("도서 제목:"));
                panel.add(titleField);
                panel.add(new JLabel("저자:"));
                panel.add(authorField);
                panel.add(new JLabel("가격:"));
                panel.add(priceField);
                panel.add(new JLabel("출판사:"));
                panel.add(publisherField);

                int result = JOptionPane.showConfirmDialog(null, panel, "도서 정보 입력", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String title = titleField.getText();
                    String author = authorField.getText();
                    String price = priceField.getText();
                    String publisher = publisherField.getText();

                    if (!title.isEmpty() && !author.isEmpty() && !price.isEmpty() && !publisher.isEmpty()) {
                        // 파일에 도서 정보 추가
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("booklist.txt", true))) {
                            // 각 정보를 구분자로 구분하여 한 줄에 출력
                            writer.write(title + ", " + author + ", " + price + ", " + publisher);
                            writer.newLine();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        // 테이블에 도서 정보 추가
                        TablePanel.getInstance().addBook(title);
                    } else {
                        JOptionPane.showMessageDialog(null, "도서 정보를 모두 입력하세요.");
                    }
                }
            }
        });

        // 도서 삭제 버튼 액션 리스너 추가
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 테이블에서 선택된 행 가져오기
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // 선택된 행이 있는 경우에만 삭제 작업 수행
                    // 선택된 도서 정보 가져오기
                    String title = (String) table.getValueAt(selectedRow, 0);

                    // 파일에서 도서 정보 삭제
                    try {
                        File inputFile = new File("booklist.txt");
                        File tempFile = new File("temp.txt");

                        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                        String currentLine;
                        while ((currentLine = reader.readLine()) != null) {
                            // 선택된 도서 제목과 같은 경우는 넘어가고, 그 외의 경우에만 temp 파일에 쓰기
                            String[] parts = currentLine.split(", ");
                            if (!parts[0].equals(title)) {
                                writer.write(currentLine);
                                writer.newLine();
                            }
                        }
                        writer.close();
                        reader.close();

                        // 원본 파일 삭제
                        if (!inputFile.delete()) {
                            throw new IOException("원본 파일을 삭제하는 데 실패했습니다.");
                        }

                        // 임시 파일을 원본 파일로 변경
                        if (!tempFile.renameTo(inputFile)) {
                            throw new IOException("임시 파일을 원본 파일로 변경하는 데 실패했습니다.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    // 테이블에서 도서 정보 삭제
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "삭제할 도서를 선택하세요.");
                }
            }
        });
        
        accountInfoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountInfoFrame aif = new AccountInfoFrame();
				aif.main(null);
			}
		});

        // 패널에 버튼 추가
        add(rentalButton);
        add(returnButton);
        add(registButton);
        add(deleteButton);
        add(accountInfoButton);
    }

    private void rentBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String status = (String) table.getValueAt(selectedRow, 4);
            if (status.equals("대여가능")) {
                JTextField nameField = new JTextField(20);
                nameField.setText(""); // 빈 칸으로 설정
                JTextField rentalDateField = new JTextField(20);
                JTextField returnDateField = new JTextField(20);

                JPanel panel = new JPanel(new GridLayout(6, 2));
                panel.add(new JLabel("이름:"));
                panel.add(nameField);
                panel.add(new JLabel("대여일자:"));
                panel.add(rentalDateField);
                panel.add(new JLabel("반납예정일:"));
                panel.add(returnDateField);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JButton okButton = new JButton("확인");
                okButton.addActionListener(e -> {
                    String name = nameField.getText();
                    String rentalDate = rentalDateField.getText();
                    String returnDate = returnDateField.getText();

                    // 입력 필드가 모두 비어 있는지 확인
                    if (!name.isEmpty() && !rentalDate.isEmpty() && !returnDate.isEmpty()) {
                        // 선택된 행의 데이터 수정
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setValueAt(name, selectedRow, 1); // 회원명 열 수정
                        model.setValueAt(rentalDate, selectedRow, 2); // 대여일자 열 수정
                        model.setValueAt(returnDate, selectedRow, 3); // 반납예정일 열 수정
                        model.setValueAt("대여중", selectedRow, 4); // 대여 상태를 "대여중"으로 변경

                        SwingUtilities.getWindowAncestor(panel).dispose();
                    } else {
                        JOptionPane.showMessageDialog(panel, "모든 정보를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                    }
                });
                buttonPanel.add(okButton);

                int result = showDialog(panel, buttonPanel, "대여 정보 입력");
                if (result == JOptionPane.OK_OPTION) {
                    // 대여 상태를 "대여중"으로 변경
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setValueAt("대여중", selectedRow, 4);
                }
            } else {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                        "이미 대여 중인 도서입니다.", "대여 불가", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "도서를 선택하세요.", "선택 오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void returnBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String status = (String) table.getValueAt(selectedRow, 4);
            if (status.equals("대여중")) {
                // 선택된 행의 데이터 수정
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setValueAt("", selectedRow, 1); // 회원명 비우기
                model.setValueAt("", selectedRow, 2); // 대여일자 비우기
                model.setValueAt("", selectedRow, 3); // 반납예정일 비우기
                model.setValueAt("대여가능", selectedRow, 4); // 대여 상태를 "대여가능"으로 변경
            } else {
                JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                        "반납할 수 없는 도서입니다.", "반납 불가", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "도서를 선택하세요.", "선택 오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int showDialog(JPanel panel, JPanel buttonPanel, String title) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this));
        dialog.setModal(true);
        dialog.setTitle(title);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        Font font = UIManager.getFont("OptionPane.messageFont");
        if (font == null) {
            font = new Font("SansSerif", Font.PLAIN, 12);
        }
        Font newFont = font.deriveFont(Font.PLAIN, 16);
        UIManager.put("OptionPane.messageFont", newFont);

        JLabel label = new JLabel();
        label.setFont(newFont);
        panel.add(label, BorderLayout.CENTER);

        dialog.pack();
        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
        return JOptionPane.OK_OPTION;
    }
}

//1.RentalButton 클래스는 JPanel을 상속하며, 대여와 반납 버튼을 포함합니다.
//2.rentBook() 메서드는 대여 버튼을 눌렀을 때 실행됩니다. 선택한 도서가 "대여가능" 상태인지 확인하고, 대여 정보를 입력하는 다이얼로그를 띄웁니다. 
//입력한 정보를 테이블에 업데이트하고, 대여 상태를 "대여중"으로 변경합니다.
//3.returnBook() 메서드는 반납 버튼을 눌렀을 때 실행됩니다. 선택한 도서가 "대여중" 상태인지 확인하고, 반납 정보를 입력하는 다이얼로그를 띄웁니다. 
//입력한 정보를 테이블에 업데이트하고, 대여 상태를 "대여가능"으로 변경합니다.
//4.showDialog() 메서드는 입력 다이얼로그를 생성하고 보여주는 역할을 합니다.
