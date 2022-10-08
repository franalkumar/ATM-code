package ATM_process;
import java.io.*;
public class accounts implements Serializable {
	public int account_no;
	public String account_holder;
	private int pin;
	private int account_balance;
	accounts(int num,String name,int p,int bal)
	{
		this.account_no=num;
		this.account_holder=name;
		this.account_balance=bal;
		this.pin=p;
	}
	int getBalance(int p)
	{
		if (p==this.pin)
			return account_balance;
		else
			return -1;
	}
	boolean validate_pin(int p)
	{
		if(p==this.pin)
				return true;
		else
			return false;
	}
