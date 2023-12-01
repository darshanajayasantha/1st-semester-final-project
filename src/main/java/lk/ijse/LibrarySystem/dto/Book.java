package lk.ijse.LibrarySystem.dto;

public class Book {
    private String id;
    private String name;
    private String Author;
    private int Qty;
    private String Discription;
 //   private String Qr;

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

//    public String getQr() {
//        return Qr;
//    }
//
//    public void setQr(String qr) {
//        Qr = qr;
//    }
}
