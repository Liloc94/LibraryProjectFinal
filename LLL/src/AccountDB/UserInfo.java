package AccountDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import screen.InfoModifierFrame;

public class UserInfo {
	protected static List<String> userInfo;
	protected static HashMap<String, String> loadUserInfo;
	protected static final String FILE_PATH = "src/userData/member.txt";
	protected static final String TMP_PATH = "src/userData/member.tmp";
	protected String userAccNum;
	protected String userID;
	protected String userPW;
	protected String userName;
	protected String userAddress;
	protected String userPhoneNum;

	// 도서 대여여부 추적용
	protected boolean isBorrowed;
	protected String bookName;
	
	public void setUserAccNum(String userAccNum) {
		this.userAccNum = userAccNum;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public boolean isBorrowed() {
		return isBorrowed;
	}
	
	public static String getFilePath() {
		return FILE_PATH;
	}
	public String getUserAccNum() {
		return userAccNum;
	}
	public String getUserID() {
		return userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	

// ---------------------------------------- getter / setter --------------------------
	
//TODO:	1. 회원정보 수정 화면 디자인 -> 회원의 현재 정보를 표시 및 수정할 입력필드 포함
//TODO: 2. 수정할 회원정보 참조 및 화면에 표시 -> 회원의 아이디 입력 및 검색 기능 구현
//TODO:	3. 사용자가 입력한 정보로 회원 정보 수정 -> 입력정보 유효성 검사 및 수정된 정보 파일에 저장하는 기능 구현
//TODO:	4. 회원정보 수정 시 변경 사항 데이터에 갱신 -> 수정된 정보 파일에 다시 저장하는 기능 구현
	
	public UserInfo() {
	}
	
	public void loadUserInfoFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 한 줄을 필드로 분리
                String[] fields = line.split(","); // 예시: 회원정보가 쉼표로 구분되어 있음

                // 필드 값을 필드 멤버에 대입
                // 회원번호는 setAccountInfo 에서 추가
                setUserID(fields[1]);
                setUserPW(fields[2]);
                setUserName(fields[3]);
                setUserAddress(fields[4]);
                setUserPhoneNum(fields[5]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
	public void setAccountInfo(ArrayList<String> modifyArray/*수정할 값*/, String CurrentAccNum/*회원번호로 회원정보 특정*/){
	    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
	         FileWriter writer = new FileWriter(TMP_PATH)) {
	        String line;
	        String tempAccNum = modifyArray.get(0); // 수정값에서 검증할 회원번호값 호출 및 대입

	        // 파일을 한 줄씩 읽어오면서 처리
	        while ((line = reader.readLine()) != null) {
	        	System.out.println("현재 읽은 라인: " + line);
	        	System.out.println("현재 비교하는 회원번호: " + CurrentAccNum);
	            // 아이디 값 비교
	            if (line.trim().split(",")[0].equalsIgnoreCase(CurrentAccNum)) {
	                // 새로운 회원 정보로 덮어씀
	                for (String info : modifyArray) {
	                    writer.write(info + ",");
	                }
	                writer.write("\n");
	                System.out.println("회원정보 수정됨.");
	            } else {
	                // 해당 회원번호가 아닌 경우, 원래의 회원 정보를 유지
	                writer.write(line + "\n");
	            }
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }

	    try (BufferedWriter originalWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
	        try (BufferedReader tmpReader = new BufferedReader(new FileReader(TMP_PATH))) {
	            String line;
	            // 임시 파일에서 내용을 원래 파일에 덮어씌움
	            while ((line = tmpReader.readLine()) != null) {
	                originalWriter.write(line);
	                originalWriter.newLine();
	            }
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
}