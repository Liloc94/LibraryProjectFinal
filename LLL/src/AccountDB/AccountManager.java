package AccountDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import screen.RegisterFrame;

public class AccountManager {
	
	private static final String FILE_PATH = "src/userData/member.txt";
    private ArrayList<String> members;
    private Map<String, String> IdPw;
    
    public Map<String, String> getIdPw() {
		return IdPw;
	}
    
    public void setIdPw(Map<String, String> idPw) {
		this.IdPw = idPw;
	}
    
    public ArrayList<String> getMembers() {
		return members;
	}
    
    public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
    
    // ----------------- getter setter -----------------------------
    
    public AccountManager() {
    }

    public AccountManager(ArrayList<String> accountMap) {
    	this.members = accountMap;
    }

    public void addMember(ArrayList<String> accountMap) {
    	LoginLogic login = new LoginLogic();
    	saveToFile(accountMap); // 파일에 저장
    	System.out.println("신규 사용자 등록완료.");
    	}
    
    public void modifyMember(ArrayList<String> accountMap) {
    	this.members = accountMap;
    }
    
    public void saveToFile(ArrayList<String> accountMap) { 
    	MemberProcessor mp = new MemberProcessor();

    	// 6자리 난수로 회원번호 생성
    	Random rd = new Random();
    	String accountNum = "";
    	outer: 
    		while(true) {
    			for (int i = 0; i < 6; i++) {
    	        accountNum += rd.nextInt(10); // 0부터 9까지의 숫자로 난수 생성하여 회원번호에 추가
    			}		
    		if(accountNum == mp.getAccountNum()) {
    		continue outer;
    		} else {
    			break;
    		}
    	}
    	
    	// 난수로 생성한 회원번호 삽입
        try (FileWriter writer = new FileWriter
        		(FILE_PATH, true)) {
        	while(true) {
        		writer.append(accountNum + ",");
        		if(accountMap.size() == 6) break;
        		break;
        	}
        	// 입력받은 회원정보 , 로 구분하여 텍스트 이어쓰기
        	for (String data : accountMap) {
                writer.append(data + ",");
            }
        	// 입력 끝난 후 줄바꿈으로 회원정보 구분
        	writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
 	
    public static class MemberProcessor {
	private String accountNum;
	private static boolean isIdDupChecked = false;
	
	// 오류나면 생성자 생성
	
	public static boolean getisIdDupChecked() {
		return isIdDupChecked;
	}
	public static void dupCheckTrue() {
		isIdDupChecked = true;
	}
	public static void dupCheckFalse() {
		isIdDupChecked = false;
	}
	
	public String getAccountNum() {
		return accountNum;
	}
	
	// 모든 검증이후 중복 시 true 중복값 없을 시 false 반환 
	public boolean checkDup(String idValue) {
		String[] data;
		
		// DB text 에서 저장되어있는 ID 읽어와서 리스트에 저장
		try (BufferedReader br = new BufferedReader(new FileReader
				(FILE_PATH))) {	
			String temp;
			String id = idValue;
			
			while ((temp = br.readLine()) != null) {
				// 쉼표를 기준으로 아이디를 추출
	            data = temp.split(",");
	            for (String ID : data) {
	                if (id.equals(ID)) {
	                    JOptionPane.showMessageDialog(null, "중복된 아이디가 존재합니다.", "이미 존재하는 ID", JOptionPane.WARNING_MESSAGE);
	                    dupCheckFalse();
	                    return false; // 중복된 아이디가 존재하므로 false 반환
			}
	            }
	        }
				// 중복된 아이디가 없을 경우
	        	JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
	        	dupCheckTrue();
	    	} 	catch (IOException e) {
	    		e.printStackTrace();
	    		// 파일 읽기 오류 처리
	    		JOptionPane.showMessageDialog(null, "파일을 읽는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
	    	}
		return true;
    }
    }
	
	
	
	
	
    
}

 