package org.kh.library.view;

import java.util.*;

import org.kh.library.controller.BookController;
import org.kh.library.controller.CustomerController;
import org.kh.library.model.vo.Book;
import org.kh.library.model.vo.Customer;
import org.kh.library.model.vo.Library;

public class LibraryView implements LibraryViewI{
	public Scanner sc = new Scanner(System.in);
	
	private BookController bController;
	private CustomerController cController;
	
	public LibraryView() {
		bController = new BookController();
		cController = new CustomerController();
	}
	
	public void startLibrary() {
		end :
		while(true) {
			int choice = this.mainMenu();
			switch(choice) {
			case 1: // <책 관리>
				this.bookMenu();
				//System.out.println("1번");
				break;
			case 2: // <회원 관리>
				this.customerMenu();
				//System.out.println("2번");
				break;
			case 3: // <대여 관리>
				this.libraryMenu();
				//System.out.println("3번");
				break;
			case 0:
				this.displayMessage("프로그램을 종료합니다.");
				break end;
			}
		}
	}
	
	
	@Override
	public int mainMenu() {
		System.out.println("메인메뉴");
		System.out.println("1. 책 관리");
		System.out.println("2. 회원 관리");
		System.out.println("3. 대여 관리");
		System.out.println("0. 종료");
		System.out.print("메뉴 선택 : ");
		return sc.nextInt();
	}
	@Override
	public void bookMenu() {
		while(true) {
			System.out.println("책관리 서브메뉴");
			System.out.println("1. 전체 책 조회");
			System.out.println("2. 책 코드로 조회");
			System.out.println("3. 책 추가하기");
			System.out.println("4. 책 삭제하기");
			System.out.println("5. 메인 메뉴로 이동");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				List<Book> bList = bController.selectAllBook(); 
				if(bList.size() > 0) {
					this.displayBookList(bList);
				}else {
					this.displayError("존재하지 않는 정보입니다.");
				}
				break;
			case 2:
				 int bookNo = this.inputBookNo("조회");
				 Book book = bController.selectBookOne(bookNo);
				 if(book != null) {
					 this.displayBook(book);
				 }else {
					 this.displayError("존재하지 않는 정보입니다.");
				 }
				break;
			case 3: // 책 정보 입력받기
				book = this.inputBook();
				// 책 정보 전달 - 컨트롤러
				int result = bController.insertBook(book);
				if(result > 0) {
					this.displaySuccess("책 등록 완료!");
				}else {
					this.displayError("책 등록이 완료되지 않았습니다.");
				}
				break;
			case 4:
				bookNo = this.inputBookNo("삭제");
				result = bController.deleteBook(bookNo);
				if(result > 0) {
					this.displaySuccess("책 삭제 완료!");
				}else {
					this.displayError("책 삭제가 완료되지 않았습니다.");
				}
				break;
			case 5:
				this.startLibrary();
				return;
			}
		}
		
	}

	@Override
	public void customerMenu() {
		while(true) {
			System.out.println("회원관리 서브메뉴");
			System.out.println("1. 전체 회원 조회");
			System.out.println("2. 회원 이름으로 조회");
			System.out.println("3. 회원 아이디로 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("7. 메인 메뉴로 이동");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				List<Customer> cList = cController.selectAllCustomer();
				if(cList.size() > 0) {
					this.displayCustomerList(cList);
				}else {
					this.displayError("존재하지 않는 정보입니다.");
				}
				break;
			case 2:
				String cName = this.inputCName();
				Customer cInfo = cController.selectNameSearch(cName);
				if(cInfo != null) {
					this.displayCutomerOne(cInfo);
				}else {
					this.displayError("존재하지 않는 정보입니다.");
				}
				break;
			case 3:
				String cId = this.inputCId();
				cInfo = cController.selectIdSearch(cId);
				if(cInfo != null) {
					this.displayCutomerOne(cInfo);
				}else {
					this.displayError("존재하지 않는 정보입니다.");
				}
				break;
			case 4:
				cInfo = this.inputCustomer();
				int result = cController.insertCustomer(cInfo);
				if(result > 0) {
					this.displaySuccess("회원 등록 완료!");
				}else {
					this.displayError("회원 등록이 완료되지 않았습니다.");
				}
				break;
			case 5:
				cId = this.inputUserId();
				cInfo = cController.selectIdSearch(cId);
				if(cInfo != null) {
					cInfo = this.modifyCustomer();
					cInfo.setUserId(cId);
					result = cController.updateCustomer(cInfo);
					if(result > 0) {
						this.displaySuccess("회원 정보 수정 완료!");
					}else {
						this.displayError("회원 정보 수정이 완료되지 않았습니다.");
					}
				}
				break;
			case 6:
				cId = this.inputUserId();
				result = cController.deleteCustomer(cId);
				if(result > 0) {
					this.displaySuccess("회원 삭제 완료!");
				}else {
					this.displayError("회원 삭제가 완료되지 않았습니다.");
				}
				break;
			case 7:
				this.startLibrary();
				return;
			}
		}
	}

	@Override
	public void libraryMenu() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.println("대여관리 서브메뉴");
			System.out.println("1. 전체 대여 조회");
			System.out.println("2. 대여 아이디로 조회");
			System.out.println("3. 대여 책이름으로 조회");
			System.out.println("4. 대여정보 추가");
			System.out.println("5. 메인 메뉴로 이동");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				return;
			}
		}
	}
	

	@Override
	public void displayLibraryList(List<Library> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayLibraryList(Library library) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayBookList(List<Book> bList) {
		System.out.println("====== 전체 책 정보 ======");
		for(Book book : bList) {
			System.out.printf("번호 : %d, 제목 : %s, 저자 : %s, 가격 : %d, 출판사 : %s, 장르 : %s\n"
					, book.getBookNo(), book.getBookName(), book.getBookWriter()
					, book.getBookPrice(), book.getPublisher(), book.getGenre());
		}
	}

	@Override
	public void displayBook(Book book) {
		System.out.println("====== 책 정보 ======");
		System.out.printf("번호 : %d, 제목 : %s, 저자 : %s, 가격 : %d, 출판사 : %s, 장르 : %s\n"
				, book.getBookNo(), book.getBookName(), book.getBookWriter()
				, book.getBookPrice(), book.getPublisher(), book.getGenre());
	}

	@Override
	public void displayCustomerList(List<Customer> cList) {
		System.out.println("====== 고객 정보 ======");
		for(Customer ctm : cList) {
			System.out.printf("고객번호 : %d, 아이디 : %s, 이름 : %s, 나이 : %d, 주소 : %s, 성별 : %s, 가입일 : %s\n"
					, ctm.getUserNo(), ctm.getUserId(), ctm.getUserName(), ctm.getUserAge()
					, ctm.getAddr(), ctm.getGender(), ctm.getEnrollDate());
		}
	}

	@Override
	public void displayCutomerOne(Customer ctm) {
		System.out.println("====== 고객 정보 ======");
		System.out.printf("고객번호 : %d, 아이디 : %s, 이름 : %s, 나이 : %d, 주소 : %s, 성별 : %s, 가입일 : %s\n"
				, ctm.getUserNo(), ctm.getUserId(), ctm.getUserName(), ctm.getUserAge()
				, ctm.getAddr(), ctm.getGender(), ctm.getEnrollDate());
	
	}

	@Override
	public void displayMessage(String message) {
		System.out.println("[안내] : " + message);
	}

	@Override
	public void displayError(String message) {
		System.out.println("[서비스 에러] : " + message);
	}

	@Override
	public void displaySuccess(String message) {
		System.out.println("[서비스 성공] : " + message);
	}

	@Override
	public String inputUserId() {
		System.out.print("고객 아이디 : ");
		return sc.next();
	}

	@Override
	public String inputBookName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Library insertLibrary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String inputCName() {
		System.out.print("조회할 고객 이름 : ");
		return sc.next();
	}

	@Override
	public String inputCId() {
		System.out.print("조회할 고객 아이디 : ");
		return sc.next();
	}

	@Override
	public Customer inputCustomer() {
		System.out.println("====== 회원 정보 입력 ======");
		System.out.print("아이디 : ");
		String userId = sc.next();
		System.out.print("이름 : ");
		String userName = sc.next();
		System.out.print("나이 : ");
		int userAge = sc.nextInt();
		sc.nextLine();
		System.out.print("주소 : ");
		String addr = sc.nextLine();
		System.out.print("성별 : ");
		String gender = sc.next();
		Customer cInfo = new Customer();
		cInfo.setUserId(userId);
		cInfo.setUserName(userName);
		cInfo.setUserAge(userAge);
		cInfo.setAddr(addr);
		cInfo.setGender(gender);
		return cInfo;
	}

	@Override
	public Customer modifyCustomer() {
		System.out.println("====== 수정할 정보 입력 ======");
		System.out.print("나이 : ");
		int userAge = sc.nextInt();
		sc.nextLine();
		System.out.print("주소 : ");
		String addr = sc.nextLine();
		Customer cInfo = new Customer();
		cInfo.setUserAge(userAge);
		cInfo.setAddr(addr);
		return cInfo;
	}

	@Override
	public int inputBookNo(String message) {
		System.out.print(message + "할 책 번호 : ");
		return sc.nextInt();
	}

	@Override
	public Book inputBook() {
		System.out.println("====== 책 정보 등록 ======");
		System.out.print("책 제목 : ");
		String bookName = sc.nextLine();
		System.out.print("책 저자 : ");
		String bookWriter = sc.nextLine();
		System.out.print("책 가격 : ");
		int bookPrice = sc.nextInt();
		sc.nextLine();
		System.out.print("출판사 : ");
		String publisher = sc.nextLine();
		System.out.print("장르 : ");
		String genre = sc.nextLine();
		Book book = new Book();
		book.setBookName(bookName);
		book.setBookWriter(bookWriter);
		book.setBookPrice(bookPrice);
		book.setPublisher(publisher);
		book.setGenre(genre);
		return book;
	}

}
