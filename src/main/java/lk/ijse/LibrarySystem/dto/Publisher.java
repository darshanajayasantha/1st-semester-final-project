package lk.ijse.LibrarySystem.dto;

public class Publisher {
    private String Id;
    private String Name;
    private String Year;
    private String publishedBook;

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

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getPublishedBook() {
        return publishedBook;
    }

    public void setPublishedBook(String publishedBook) {
        this.publishedBook = publishedBook;
    }
}
