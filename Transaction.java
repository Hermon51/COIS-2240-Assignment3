import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction
{
  private static Transaction instance;
                                //singleton
 private Transaction() {}
    public static Transaction getInstance()
    {if (instance == null)
        {instance = new Transaction();}
     return instance;}
//save transaction
   
  public static void saveTransaction(String transactionDetails) 
   {
        try (FileWriter writer = new FileWriter("transactions.txt" , true))
        {writer.write(transactionDetails + "   ");}
        catch (IOException e)
        {System.out.println("Error saving" + e.getMessage());}
   }


//display transaction history
public void displayTransactionHistory() 
{ 
  System.out.println(" ");
  try (BufferedReader reader = new BufferedReader(new FileReader("transactions.txt")))
  { String line;
  while ((line = reader.readLine()) != null) 
     {System.out.println(line);} 
  } 
catch (IOException e) 
    {
  System.out.println("Error reading history" + e.getMessage());
    }
}
   // Perform the borrowing of a book
    public static boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
          saveTransaction(transactionDetails);
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
           saveTransaction(transactionDetails);
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
