
package boundary;

import java.util.Scanner;

/**
 *
 * @author thongjiayi
 */
public class courseControlUI {

   
    Scanner scanner = new Scanner(System.in);
    
    public int getMenuChoice() {
    System.out.println("\nMAIN MENU");
    System.out.println("1. Add a new course ");
    System.out.println("2. Remove a course");
    System.out.println("3. Search courses offered");
    System.out.println("4. Amend course details");
    System.out.println("5. List courses taken by different faculties ");
    System.out.println("6. List all courses for a programme");
    System.out.println("7. Add programme to a course");
    System.out.println("8. Remove a programme from a course");
    System.out.println("9. Generate summary reports");
    System.out.println("0.Exit the program");
    System.out.print("Please enter the choice:");
    int choice = scanner.nextInt();
    scanner.nextLine();
    System.out.println();
    return choice;
  }
    
    
}
    

    
    


