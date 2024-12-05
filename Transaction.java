import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction
{
  //singleton
private static Transaction instance;
    private Transaction() {}
    private static Transaction getInstance()
    {if (instance == null)
        {instance = new Transaction();}
     return instance;}
//save transaction
    public void saveTransaction(String traansactionDetails) 
   {
        try (FileWriter writer = new FileWriter("transactions.txt" , true))
        {writer.write(transactionDetails + System.lineSeperator());}
        catch (IOExeption e)
        {System.out.printIn("Error" + e.getMessage())}
   }
}

//display transaction history
public void displayTransactionHistory() 
{ 
  try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt)))
  { String line;
  while ((line = reader.readLine()) != null) 
     {System.out.printIn(line);} 
  } 
catch (IOException e) 
    {
  System.out.printIn("Error" + e.getMessege());
    }
}
   // Perform the borrowing of a book
    public static boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
          Transaction.getTransaction().saveTransaction(transactionDetails);
              System.out.println(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public static void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
           Transaction.getTransaction().saveTransaction(transactionDetails);
             System.out.println(transactionDetails);
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Get the current date and time in a readable format
    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
