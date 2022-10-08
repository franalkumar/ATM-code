package ATM_package;
import java.io.*;
public class denominations implements Serializable{
	int d_2000=0;
	int d_500=0;
	int d_100=0;
	denominations(int v_2000, int v_500, int v_100)
	{
		d_2000=v_2000;
		d_500=v_500;
		d_100=v_100;
	}
	
	void load_cash(int v_2000,int v_500,int v_100)
	{
		d_2000+=v_2000;
		d_500+=v_500;
		d_100+=v_100;
	}
	
	void withdraw_cash(int v_2000, int v_500, int v_100)
	{
		d_2000-=v_2000;
		d_500-=v_500;
		d_100-=v_100;
	}
	public static void main(String args[])
	{
		denominations s= new denominations(20,100,100);
		try{
			FileOutputStream file= new FileOutputStream("denominations.ser");
			ObjectOutputStream obj= new ObjectOutputStream(file);
			obj.writeObject(s);
			obj.close();
			file.close();
			System.out.println("Serialized data of the denominations are stored in denominations.ser");
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
