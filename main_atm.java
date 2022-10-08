package ATM_process;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class main_atm{
	public static void main(String args[])
	{
	accounts a[]= null;
	denominations s= null;
	try
	{
		FileInputStream file1=new FileInputStream("acc_info.ser");
		ObjectInputStream obj1= new ObjectInputStream(file1);
		a=(accounts[])obj1.readObject();
		obj1.close();
		file1.close();
		System.out.println("Account data loaded..");
		FileInputStream file2= new FileInputStream("denominations.ser");
		ObjectInputStream obj2= new ObjectInputStream(file2);
		s=(denominations)obj2.readObject();
		obj2.close();
		file2.close();
		System.out.println("Cash storage data loaded..");
	}catch(IOException i)
	{
		i.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	while(true)
	{
	System.out.println("Enter the number of operation you want to chose-");
	System.out.println("1.Load Cash to ATM\n2.ATM operations");
	Scanner sc= new Scanner(System.in);
	int choice=sc.nextInt();
	System.out.println("\n***********************************************\n");
	switch(choice)
	{
	case 1:
		int d_2000,d_500,d_100;
		System.out.println("Enter Denominations of 2000:");
		d_2000=sc.nextInt();
		System.out.println("Enter Denominations of 500");
		d_500=sc.nextInt();
		System.out.println("Enter Denominations of 100");
		d_100=sc.nextInt();
		break;
	case 2:
		int choice2,acc_no,pin,i;
		System.out.println("Enter your account number:");
		acc_no=sc.nextInt();
		System.out.println("Enter your pin:");
		pin=sc.nextInt();
		for(i=0;i<a.length;i++)
		{
			if(a[i].account_no==acc_no)
				break;
		}
		if(a[i].validate_pin(pin))
		{
		System.out.println("1.Check Balance\n2.Withdraw Money\n3.Transfer Money\n4.Check ATM balance");
		int amount=0,acc_no2;
		choice2=sc.nextInt();
		switch(choice2)
		{
		case 1:
			System.out.println(a[i].getBalance(pin));
			break;
			///////////////////////////////////////////////
		case 2:
			System.out.println("Enter the amount you want to withdraw");
			amount=sc.nextInt();
			int a_2000=0,a_500=0,a_100=0;
			if(amount>2000&&amount%2000!=0)
			{
				a_2000=amount/2000;
				amount%=2000;
			}
			else if(amount>2000&&amount%2000==0)
			{
				a_2000=(amount/2000)-1;
				amount%=2000;
			}
			if(amount>500&&amount%500!=0)
			{
				a_500=amount/500;
				amount%=500;
			}
			else if(amount>500&&amount%500==0)
			{
				a_500=(amount/500-1);
				amount%=500;
			}
			if(amount>100)
			{
				a_100=(amount/100);
			}
			System.out.println("Cash withdrawed is:"+"\n 2000 X "+a_2000+"\n 500 X "+a_500+"\n 100 X "+a_100);
			s.withdraw_cash(a_2000, a_500, a_100);
			a[i].deductMoney(amount, pin);
			break;
			///////////////////////////////////////////////
		case 3:
			System.out.println("Enter the account no you want to transfer money to:");
			acc_no2=sc.nextInt();
			int j;
			for(j=0;j<a.length;j++)
			{
				if(a[j].account_no==acc_no2)
					break;
			}
			System.out.println("Enter the amount you want to transfer:");
			amount=sc.nextInt();
			if(amount>=1000&&amount<=1000&&a[i].getBalance(pin)>amount)
			{
			a[i].deductMoney(amount, pin);
			a[j].addMoney(amount, pin);
			System.out.println("Amount successfully transfered");
			}
			else
			{
				System.out.println("The amount transfer limit is between 1000-10000\nPlease change your amount.");
			}
			break;
			////////////////////////////////////////////////
		case 4:
			System.out.println("The ATM balance in denominations are:"+"\n2000 X "+s.d_2000+"\n500 X "+s.d_500+"\n100 X "+s.d_100);
			System.out.println("Total Balance:"+((2000*s.d_2000)+(500*s.d_100)+(100*s.d_100)));
			break;
		default:
		System.out.println("Invalid choice");
		}
		}
		else
		{
			System.out.println("You Entered the wrong pin number");
		}
		break;
		
	default:
		System.out.println("Invalid choice");
	}
	
	System.out.println("\n***********************************************\n");
	try
	{
		FileOutputStream file3=new FileOutputStream("acc_info.ser");
		ObjectOutputStream obj3= new ObjectOutputStream(file3);
		obj3.writeObject(a);
		obj3.close();
		file3.close();
		System.out.println("Account data saved..");
		FileOutputStream file4= new FileOutputStream("denominations.ser");
		ObjectOutputStream obj4= new ObjectOutputStream(file4);
		obj4.writeObject(s);
		obj4.close();
		file4.close();
		System.out.println("Cash storage data saved..\n");
	}catch(IOException i)
	{
		i.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	}
}
