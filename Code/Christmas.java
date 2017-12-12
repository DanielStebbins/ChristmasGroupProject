import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Christmas
{
	public static void main(String[] args) throws FileNotFoundException
	{
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		ArrayList<Kids> kids = new ArrayList<Kids>();
		Scanner reader = new Scanner(System.in);
		Scanner fileReader = new Scanner(new File("Gifts.txt"));
		int daysTillChrist;

		while(fileReader.hasNextLine())
		{
			String name = fileReader.nextLine();
			gifts.add(new Gift(fileReader.nextLine(), fileReader.nextInt(), fileReader.nextInt(), fileReader.nextInt(), fileReader.nextDouble()));
		}
    
		System.out.println("How many days till Christmas?");
 		daysTillChrist = reader.nextInt();
 	}
}