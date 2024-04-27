import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Book class for Storing the Books
class Book {
	//Attributes of the Books in the libraryManagementSystem
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
//child classes of the Book class from inheritance 
class KidsBook extends Book {
    public KidsBook(String title, String author, String isbn, boolean available) {
        super(title, author, isbn, available);
    }
}

class ScientificBook extends Book {
    public ScientificBook(String title, String author, String isbn, boolean available) {
        super(title, author, isbn, available);
    }
}

class FictionalBook extends Book {
    public FictionalBook(String title, String author, String isbn, boolean available) {
        super(title, author, isbn, available);
    }
}

//Admin class to add new books to LibraryManagementSystem
class Admin {
    public void addBookToLibrary(Library library, Book book) {
        library.addBook(book);
        System.out.println("Book added to the library: " + book.getTitle());
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }
    //Method to display avalable books
    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " and the isbn is " + book.getIsbn());
            }
        }
    }
    //Method to borrow a book 
    public void borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("You have borrowed: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " is not available.");
    }
    //Method to return a book
    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("You have returned: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book with ISBN " + isbn + " was not borrowed or does not exist.");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Admin admin = new Admin();
		
		// Adding some Example books
        library.addBook(new Book("Magul Kaama", "Kumarathunga Munidasa", "11111", true));
        library.addBook(new Book("Madol Duuwa", "Martin Wickramasinghe", "11112", true));
        library.addBook(new Book("Harry Potter", "J.K.Rowling", "12344", true));
		library.addBook(new Book("Percy Jackson & the Olympians", "Rick Riordan", "13345", true));
		library.addBook(new Book("Kali Yugaya", "Martin Wickramasinghe", "14523", false));
		
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Display available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Add a book to the library(Admin Only)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            //method to call the functions that user request
            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter the ISBN of the book you want to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    library.borrowBook(borrowIsbn);
                    break;
                case 3:
                    System.out.print("Enter the ISBN of the book you want to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;
                case 4:
                    System.out.println("Choose the type of book: ");
                    System.out.println("1. Regular Book");
                    System.out.println("2. Kids Book");
                    System.out.println("3. Scientific Book");
                    System.out.println("4. Fictional Book");
                    System.out.print("Enter your choice: ");
                    int bookType = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("Enter the title of the book: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author of the book: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter the ISBN of the book: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Is the book available? (true/false): ");
                    boolean availability = scanner.nextBoolean();

                    Book newBook;
                    switch (bookType) {
                        case 1:
                            newBook = new Book(title, author, isbn, availability);
                            break;
                        case 2:
                            newBook = new KidsBook(title, author, isbn, availability);
                            break;
                        case 3:
                            newBook = new ScientificBook(title, author, isbn, availability);
                            break;
                        case 4:
                            newBook = new FictionalBook(title, author, isbn, availability);
                            break;
                        default:
                            System.out.println("Invalid book type. Adding as a regular book.");
                            newBook = new Book(title, author, isbn, availability);
                            break;
                    }
                    admin.addBookToLibrary(library, newBook);
                    break;
                case 5:
                    System.out.println("Exiting From Library... Thank you!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
