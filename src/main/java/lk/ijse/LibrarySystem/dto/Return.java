package lk.ijse.LibrarySystem.dto;

public class Return {
    private String ReturnId;
    private String IssuseId;
    private String RreturnQty;
    private String ReturnDate;
    private String BookId;
    private String IssuseDate;

    public String getReturnId() {
        return ReturnId;
    }

    public void setReturnId(String returnId) {
        ReturnId = returnId;
    }

    public String getIssuseId() {
        return IssuseId;
    }

    public void setIssuseId(String issuseId) {
        IssuseId = issuseId;
    }

    public String getRreturnQty() {
        return RreturnQty;
    }

    public void setRreturnQty(String rreturnQty) {
        RreturnQty = rreturnQty;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getIssuseDate() {
        return IssuseDate;
    }

    public void setIssuseDate(String issuseDate) {
        IssuseDate = issuseDate;
    }
}
