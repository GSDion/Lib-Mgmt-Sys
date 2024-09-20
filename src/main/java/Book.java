

public class Book {
    //class fields
    private String name;
    private String author;
    private int year_published;
    private String publisher;
    private int page_number;

    //
    public Book(String name, String author, int year_published, String publisher, int page_number) {
        this.name = name;
        this.author = author;
        this.year_published = year_published;
        this.publisher = publisher;
        this.page_number = page_number;
    }

    //get and set methods
    public String getName(){
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear_published() {
        return year_published;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPage_number() {
        return page_number;
    }


    //to string method
    public String toString() {
        return String.format("Book name: %s, Book author: %s, Year Published: %d, Publisher: %s, Number of Pages: %s", 
        this.getName(), this.getAuthor(), this.getYear_published(), this.getPublisher(), this.getPage_number() );
    }

    //equals method
    public boolean equals(Object other) {
		//Cast the Object parameter to a Character so we can access its name
		Book tempCast = (Book)other;
		if (this.name.equals(tempCast.getName())){
			return true;
		}
		else {
			return false;
		}
	}
    

}
