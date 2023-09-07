package Digital_Library;

import java.util.List;

public class Borrow {

private String date_E;
private String date_R;

private Member member;
private Book book;



    public String getdate_E() {
        return date_E;
    }

    public void setDate_E(String date_E){
        this.date_E = date_E;
    }

    public String getDate_R(){
        return date_R;
    }

    public void setDate_R(String date_R) {
        this.date_R = date_R;
    }

    public Member getMember(){
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
