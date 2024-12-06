import java.util.Scanner;

public class ads {
    private Library library = new Library();

    public static void main(String[] args) {
        new LibraryManagement().run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        Transaction transaction = Transaction.getInstance();
        boolean running = true;

        while (running) {
            System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowed Books");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();
                	System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    scanner.nextLine();
                    Member newMember = new Member(memberId , memberName);
                    library.addMember(newMember);
                    System.out.println("Member added successfully.");
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                	System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    Book newBook = new Book(bookId, title);
                    library.addBook(newBook);
                    System.out.println("Book added to library successfully.");
                    break;
                case 3:
                	System.out.println("\n--- Available Members ---");
                    for (Member member : library.getMembers()) {
                        System.out.println(member.getId() + ". " + member.getName()); }
                 System.out.print("Enter member ID: ");
                 int holderId = scanner.nextInt();
                 scanner.nextLine();
                    System.out.println("\n--- Available Books ---");
                    for (Book book : library.getBooks()) {
                        if (book.isAvailable())
                        {   System.out.println(book.getId() + ". " + book.getTitle()); }
            }
                    System.out.print("Enter book ID: ");
                    int lendId = scanner.nextInt();
                    scanner.nextLine();

                    Member holder = library.findMemberById(holderId);
                    Book held = library.findBookById(lendId);

                    if (holder != null && held != null) {
                    	Transaction.borrowBook(held, holder);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;
                case 4:
                	System.out.print("Enter member ID: ");
                   int returnId = scanner.nextInt();
                    
                    System.out.print("Enter book ID: ");
                   int returnedId = scanner.nextInt();
                    
                    scanner.nextLine();

                    Member returning = library.findMemberById(returnId);
                    Book returned = library.findBookById(returnedId);

                    if (returning != null && returned != null) {
                    	Transaction.returnBook( returned,returning);
                    } else {
                        System.out.println("Invalid member or book ID.");
                    }
                    break;
                case 5:
                	System.out.print("Enter member ID: ");
                   int  specificMemberId = scanner.nextInt();
                    scanner.nextLine();

                    Member specificMember = library.findMemberById(specificMemberId);
                    
                    if (specificMember != null) {
                        System.out.println("Books borrowed by " + specificMember.getName() + ":");
                        for (Book bk : specificMember.getBorrowedBooks()) {
                            System.out.println(" - " + bk.getTitle());
                        }
                    } else {
                        System.out.println("Invalid member ID.");
                    }
                    break;
                case 6:
                	Transaction.displayTransactionHistory();
                    break;
                case 7:
                    System.out.println("Exiting. Good Bye..");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }  scanner.close();
} 

public boolean addBook (Book book)
{ if (library.findBookById(book.getId()) != null)
{ System.out.println("Error: book" + book.getId() + "already exists.");
 return false;}
library.getBooks().add(book);
return true; } 

    public boolean addMember (Member member)
    { if (library.findMemberById(member.getId()) != null)
{ System.out.println("Error: member" + member.getId() + "already exists.");
 return false;
}
library.getMembers().add(member);
return true;
}
    }

