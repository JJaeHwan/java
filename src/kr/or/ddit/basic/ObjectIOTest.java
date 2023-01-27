package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {

	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동", 27, "어딘가...");
		Member mem2 = new Member("홍길서", 27, "수원");
		Member mem3 = new Member("홍길남", 27, "대전");
		Member mem4 = new Member("홍길북", 27, "보령");
		
		try {
			// 객체를 파일에 저장하기
			FileOutputStream fout = new FileOutputStream("D:\\highjava\\D_other\\memObj.bin");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			//쓰기 작업
			oout.writeObject(mem1);
			oout.writeObject(mem2);
			oout.writeObject(mem3);
			oout.writeObject(mem4);
			oout.writeObject(null); // ==> 객체를 읽어올 때 EOFException이 발생하는 것을 방지할 수 있다.
			
			
			System.out.println("객체 저장 작업 끝...");
			
			oout.close();
			
			//-----------------------------------------------------------
			
			// 저장될 객체를 읽어와 그 내용을 화면에 출력하기
			
			// 입력용 스트림 객체 생성
			ObjectInputStream oin = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("D:\\highjava\\D_other\\memObj.bin")
							)
					);
			
			Object obj; // 읽어온 객체를 저장할 변수
			
			// readobject()메서드는 EOFException 처리를 해주어야 한다.
			System.out.println("객체 읽기 작업 시작...");
			while((obj = oin.readObject()) != null) {
				
				
				// 읽어온 데이터를 원래의 객체형으로 형 변환후 사용한다.
				Member mem = (Member) obj;
				System.out.println("이름: " + mem.getName());
				System.out.println("나이: " + mem.getAge());
				System.out.println("주소: " + mem.getAddr());
				System.out.println("--------------------------");
				
			}
			System.out.println("읽기 작업 끝..");
			oin.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}


class Member implements Serializable{
	private String name;
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
