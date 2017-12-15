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
		
				double amountPerKid = 0.00;

		for(int x = 0; x <= kids.size(); x++)
		{
			if (kids.get(age) >= 15 && nice = false)
				kids.remove(name);
		}

		amountPerKid = budget/kids.size();

		for(int x = 0; x <= gifts.size; x++)
		{
			if (gifts.get(giftPrice) > amountPerKid)
				gifts.remove(gift);

			if (gifts.get(giftTime) > daysTillChrist)
				gifts.remove(gift)
		}
 	}
}
