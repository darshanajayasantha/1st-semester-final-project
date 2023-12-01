package lk.ijse.LibrarySystem.dto;

public class Suplier {
    private String Id;
    private String Name;
    private String Contact;
    private String Address;
    private String SuplierBooks;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSuplierBooks() {
        return SuplierBooks;
    }

    public void setSuplierBooks(String suplierBooks) {
        SuplierBooks = suplierBooks;
    }
}
