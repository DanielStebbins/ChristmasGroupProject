import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Christmas
{
	public static void main(String[] args) throws FileNotFoundException
	{
		int days = 0;
		ArrayList<Gift> gifts = new ArrayList<Gift>();
		ArrayList<Kids> kids = new ArrayList<Kids>();
		Scanner reader = new Scanner(System.in);
		Scanner fileReader = new Scanner(new File("Gifts.txt"));
		double date;

 		System.out.println("What is the date?");
 		date = reader.nextInt();

		while(fileReader.hasNextLine())
		{
			String name = fileReader.nextLine();
			Gift  = new Gift(name, fileReader.nextInt(), fileReader.nextInt(), fileReader.nextInt(), fileReader.nextInt());
		}
		
		System.out.println("What is the date? (ex. December 4th == 12.04)");
 +		date = reader.nextDouble();
 +
 +		for(date = date + 0.00; date < 12.24; days++)
 +		{
 +			if(date == 1.31 || date == 3.31 || date == 5.31 || date == 7.31 || date == 8.31 || date == 10.31  || date == 12.31)
 +			{
 +				date = date + .69;
 +			}
 +
 +			if(date == 2.28 )
 +			{
 +				date = date + .82;
 +			}
 +
 +			if(date == 4.30 || date == 6.30 || date == 9.30 || date == 11.30)
 +			{
 +				date = date + .70;
 +			}
 +			date = date + .01;
 +		}
 +
 +		System.out.println("There are " + days + " till Christmas");
 	}
}
