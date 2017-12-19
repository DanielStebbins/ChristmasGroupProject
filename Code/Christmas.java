
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class Christmas
{
	public static void main(String[] args) throws FileNotFoundException
	{
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		ArrayList<Gift> currentGifts = new ArrayList<Gift>();
		ArrayList<Kids> kids = new ArrayList<Kids>();

		Scanner reader = new Scanner(System.in);
		Scanner fileReader1 = new Scanner(new File("Gifts.txt"));
		Scanner fileReader2 = new Scanner(new File("Kids.txt"));
		int daysTillChrist;
		int counter = 0;
		double budget = 0.00;

		while(fileReader1.hasNextLine())
		{
			gifts.add(new Gift(fileReader1.nextLine(),
							   Integer.parseInt(fileReader1.nextLine()),
							   Integer.parseInt(fileReader1.nextLine()),
							   Double.parseDouble(fileReader1.nextLine()),
							   Integer.parseInt(fileReader1.nextLine())));
			System.out.println(gifts.get(counter));
			counter++;
		}

		while(fileReader2.hasNextLine())
		{
			String current = fileReader2.nextLine();

			int firstComma = current.indexOf(",");
			String name = current.substring(0, firstComma);

			int secondComma = current.lastIndexOf(",");
			String niceness = current.substring(firstComma + 2, secondComma);

			boolean nice = false;
			if(niceness.equals("nice"))
				nice = true;
			else
				nice = false;

			int age = Integer.parseInt(current.substring(secondComma + 2));

			kids.add(new Kids(name, nice, age));
			//System.out.println(kids.get(counter));
			//counter++;
		}

		System.out.println("How many days till Christmas?");
 		daysTillChrist = reader.nextInt();
		
		System.out.println("What's your budget?");
		budget = reader.nextDouble();
		
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

 		for(Kids a: kids)
 		{
			currentGifts = gifts;
			System.out.println(a);
			int age = a.getAge();

			for(Gift b: currentGifts)
			{
				if(age > b.getHighAge() || age < b.getLowAge())
				{
					currentGifts.remove();
				}
			}
			System.out.println(currentGifts);

		}
		
		double range = 0.0;
		
		range = amountPerKid / .2;
		
		double amountPerKidLow = amountPerKid - range;
		int i = 0;
		while(i != kids.size)
		{
			int number;
			Random rand = new Random();

			number = rand.nextInt(currentGifts.size);
			i++;
		}
 	}
}
