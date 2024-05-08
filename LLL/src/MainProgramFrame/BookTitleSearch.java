package MainProgramFrame;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class BookTitleSearch extends JPanel{
	 private JTextField searchField;
	    private TableRowSorter sorter;

	    public BookTitleSearch(JTable table) {
	        setLayout(new FlowLayout(FlowLayout.LEFT));
	        searchField = new JTextField(15);
	        add(new JLabel("검색 : "));
	        add(searchField);

	        // TableRowSorter 생성
	        sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
	        table.setRowSorter(sorter);

	        // DocumentListener 추가
	        searchField.getDocument().addDocumentListener(new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                search(searchField.getText());
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                search(searchField.getText());
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                // Plain text components do not fire these events
	            }

	            private void search(String text) {
	                if (text.trim().length() == 0) {
	                    sorter.setRowFilter(null);
	                } else {
	                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	                }
	            }
	        });
	    }
	}