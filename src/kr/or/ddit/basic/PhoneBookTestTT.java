package kr.or.ddit.basic;
/*
 
 	문제) 이름, 전화번호, 주소를 멤버로 갖는 Phone클래스를 만들고
 		  Map을 이용해서 전화번호와 정보를 관리하는 프로그램을 작성하시오.
 		  (Map의 구조는 key값으로 입력받은 '이름'을 사용하고
 		   value값으로는 'Phone클래스의 인스턴스'로 한다.
 		   
 		   아래의 메뉴를 구현하시오.
 		   1. 전화번호 등록
 		   2. 전화번호 수정
 		   3. 전화번호 삭제
 		   4. 전화번호 검색
 		   5. 전화번호 전체 출력
 		   0. 프로그램 종료
 		   -----------------------
 		   삭제, 검색기능은 '이름'을 입력 받아 처리한다.
 		   -추가 조건
 		   1) '6. 전화번호 저장' 메뉴를 추가하고 구현한다. (저장파일명 : phoneData.dat로 한다.)
 		   		
 		   2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅한다.
 		   
 		   3) 프로그램을 종료할 때 Map의 데이터가 변경되거나 추가 또는 삭제되면 자동으로 저장한 후 종료되도록한다.
 		   
  실행예시)
   다음메뉴를 선택하세요
   1. 전화번호 등록
   2. 전화번호 수정
   3. 전화번호 삭제
   4. 전화번호 검색
   5. 전화번호 전체 출력
   0. 프로그램 종료
   -----------------------
   번호 입력 >> 1
   
   새롭게 등록할 전화번호 정보를 입력하세요.
   이  름 >> 홍길동
   전화번호 >> 010-1111-2222
   주  소 >> 대전시 중구 오류동
   
    '홍길동'님의 전화번호 정보를 등록했습니다.
    
     다음메뉴를 선택하세요
   1. 전화번호 등록
   2. 전화번호 수정
   3. 전화번호 삭제
   4. 전화번호 검색
   5. 전화번호 전체 출력
   0. 프로그램 종료
   -----------------------
   번호 입력 >> 1
   
   새롭게 등록할 전화번호 정보를 입력하세요.
   이름 >> 홍길동
     '홍길동'은 이미 등록된 사람입니다.
     
   다음메뉴를 선택하세요
   1. 전화번호 등록
   2. 전화번호 수정
   3. 전화번호 삭제
   4. 전화번호 검색
   5. 전화번호 전체 출력
   0. 프로그램 종료
   -----------------------
   번호 입력 >> 5
   
   -----------------------------------------------------
	번호	이름		전화번호			주소
   -----------------------------------------------------
     1  	홍길동	010-1111-2222	대전시 중구 오류동
     ~~~~
   -----------------------------------------------------
   출력완료
 */
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTestTT {
	private HashMap<String,Phone> phoneBookMap;
	private Scanner sc;
	private String fileName = "D:/highjava/D_other/phoneData.dat";
	boolean dataChange; // 데이터가 변경되었는지 여부를 나타내는 변수(데이터가 변경되면 이 변수값이 true가 된다.
	
	
	
	public PhoneBookTestTT() {
		sc = new Scanner(System.in);
		
		// 파일 내용을 읽어와 전화번호 정보를 관리하는 Map객체에 저장하기
//		phoneBookMap = new HashMap<String,Phone>();
		phoneBookMap = load();
		if(phoneBookMap == null) {
			phoneBookMap = new HashMap<String,Phone>();
		}
		
		
		
	}
	
	public static void main(String[] args) {
		new PhoneBookTestTT().start();
		
	
	}

	private void start() {
		System.out.println();
		System.out.println("==================================");
		System.out.println("      전화번호 관리 프로그램");
		System.out.println("==================================");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
		case 1:
			insertPhoneNum();
			break;
		case 2:
			update();
			break;
		case 3:
			delete();
			break;
		case 4:
			search();
			break;
		case 5:
			displayAll();
			break;
		case 6:
			save();
			break;
		case 0:
			if(dataChange == true) { // 데이터가 변경된 이력이 있는지 여부 검사...
				save();
				
			}
			System.out.println("프로그램을 종료합니다.");
			return;
		default:
			System.out.println("잘못 입력하였습니다.");
			
			}
		}
	}
	
	//파일로 저장된 전화번호 정보를 읽어와 Map형태로 반환하는 메서드 
	private HashMap<String, Phone> load(){
		HashMap<String, Phone> tempPhoneMap = null;		// 읽어온 데이터가 저장될 Map객체 변수 선언
		
		File f = new File(fileName);
		if(! f.exists()) {
			return null;
		}
		
		// 파일이 있으면 파일을 읽어와 처리한다.
		ObjectInputStream  oin = null;
		try {
			// 저장된 전화번호 정보를 읽어올 스트림 객체 생성
			oin = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(f)));
			
			// 파일 내용을 읽어와 Map에 셋팅하기
			
			// 방법1 ==> 저장하기를 '저장하는 방법1'로 했을 경우
			tempPhoneMap = (HashMap<String, Phone>)oin.readObject();
			
			// 방법2 ==> 저장하기를 '저장하는 방법2'로 했을 경우
//			tempPhoneMap = new HashMap<String, Phone>();
//			Object temp = null;		// 파일에서 읽어온 데이터가 저장될 변수
//			while( (temp = oin.readObject()) != null ) {
//				Phone pTemp = (Phone)temp;	// 읽어온 데이터를 형변환
//				tempPhoneMap.put(pTemp.getName(), pTemp);	// 읽어온 데이터를 Map에 셋팅하기
//			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			//사용했던 스트림 닫기
			if(oin != null) try{oin.close(); } catch(IOException e) {}
		
		}
		
		return tempPhoneMap;
	}
	
	
	private void save() {
		ObjectOutputStream oout = null;
		try {
			
			//객체를 파일로 출력할 출력용 스트림 객체 생성
			oout = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(fileName)) );

			//저장하는 방법1
			//Map 객체도 직렬화가 되어있는 객체이므로 이 Map객체를 직접 저장할 수 있다.
			oout.writeObject(phoneBookMap);
			
			// 저장하는 방법2
			// Map의 데이터를 하나씩 꺼내서 저장하기
//			for(String name : phoneBookMap.keySet()) {
//				Phone p = phoneBookMap.get(name);
//			}
//			oout.writeObject(null);
			
			dataChange = false; 	// 데이터를 저장했을 때는 최신데이터이기 때문에...
			
			System.out.println("전화번호 정보를 저장했습니다.");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally { //finally는 반드시 실행하기때문에 닫는것은 finally에서 해야함.
			// 사용했던 스트림 닫기
			if(oout != null) try { oout.close(); } catch (IOException e) {	}
			
		}
	}

	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요");
		
		System.out.print("이  름 >>");
		String name = sc.next();
		
		if(! phoneBookMap.containsKey(name)) {		//해당 사람이 없으면 검색작업을 못한다.
			System.out.println(name + "씨 전화번호 정보가 없습니다.");
			System.out.println();
			return;
		}
		
		Phone p = phoneBookMap.get(name);
		
		System.out.println("  검 색 결 과");
		System.out.println("-----------------------");
		System.out.println("이    름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주    소 : " + p.getAddr());
		System.out.println("-----------------------");
		System.out.println();
		
		
	}

	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호를 입력하세요...");
		System.out.print("이  름 >>");
		String name = sc.next();
		
		if(! phoneBookMap.containsKey(name)) {		//해당 사람이 없으면 삭제작업을 못한다.
			System.out.println(name + "씨 전화번호 정보가 없습니다.");
			System.out.println("삭제 작업을 마칩니다.");
			System.out.println();
			return;
		}
		
		phoneBookMap.remove(name);
		
		dataChange = true;
		
		System.out.println(name + "씨의 정보 삭제 성공!");
		System.out.println();
		
	}

	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");
		System.out.print("이  름 >>");
		String name = sc.next();
		
		if(! phoneBookMap.containsKey(name)) {		//해당 사람이 없으면 수정작업을 못한다.
			System.out.println(name + "씨 전화번호 정보가 없습니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		System.out.print("새로운 전화번호 >>");
		String newTel = sc.next();
		sc.nextLine();
		System.out.print("새로운 주소 >>");
		String newAddr = sc.nextLine();
		
		//같은 key값으로 새로운 전화번호 정보를 저장하면 데이터가 수정된다.
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		
		dataChange = true;
		
		System.out.println(name + "씨 전화번호 수정 완료");
		
	}

	private void displayAll() {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("번호   이름     전화번호    주소");
		System.out.println("---------------------------------");
		
		Set<String> phoneKeyset = phoneBookMap.keySet();
		
		if(phoneKeyset.size() == 0) {
			System.out.println("등록된 전화번호정보가 하나도 없습니다.");
		}else {
			int num = 0; // 번호가 저장될 변수
			for(String name : phoneKeyset) {
				num++;
				Phone p = phoneBookMap.get(name);
				System.out.println(num + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
			}
		}
		System.out.println("------------------------------------");
		System.out.println("출력 끝....");
		System.out.println();
	}

	private void insertPhoneNum() {
		System.out.println();
		System.out.println("새롭게 등록할 정보를 입력하세요. ");
		System.out.print("이름 >> ");
		String name = sc.next();
		// 이미 등록된 이름인지 검사 ==> containsKey()메서드 또는 get()메서드를 이용해서 검사할 수 있다.
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		
//		if(phoneBookMap.get(name) != null) {
//			System.out.println(name + "씨는 이미 등록된 사람입니다.");
//			return;
//		}
		
		
		System.out.print("전화번호 >> ");
		String tel = sc.next();
		
		sc.nextLine();
		
		System.out.print("주소 >> ");
//		String addr = sc.next();
		String addr = sc.nextLine();
		
//		Phone p = new Phone(name, tel, add);
		
		phoneBookMap.put(name, new Phone(name, tel, addr));
		
		dataChange = true;
		
		System.out.println(name + "씨의 전화번호를 등록했습니다.");
	}

	private int displayMenu() {
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("-------------------------");
		System.out.print("번호입력 >>");
		return sc.nextInt();
	}

}
class Phone implements Serializable{
	private String name;
	private String tel;
	private String addr;
	
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Override
	public String toString() {
		return "[name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}
	
}