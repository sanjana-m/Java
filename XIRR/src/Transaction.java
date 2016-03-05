import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Transaction implements Comparable<Transaction> {
	public double amount;
	public Date date;
	public String type;
	public double units;
	public String name;
	public String code;
	
	public Transaction(String name, String code, double amount, String date1, String type, double units){
		this.amount = amount;
		this.code = code;
		this.type = type;
		this.units = units;
		this.name = name;
		String delim = null;
		
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(date1.toString());
		if(matcher.find()){
			delim = matcher.group(0);
		}		
		SimpleDateFormat fmt = new SimpleDateFormat("dd"+delim.charAt(0)+"MM"+delim.charAt(0)+"yy");
		try {
			this.date = fmt.parse(date1);
//			date.setYear(date.getYear()+2000);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	// GETTER METHODS	
	public double getAmount(){
		return this.amount;
	}

	public String getCode(){
		return this.code;
	}

	public String getType(){
		return this.type;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public double getUnits(){
		return this.units;
	}
	
	public String getName(){
		return this.name;
	}
	
	// SETTER METHODS
	public void setAmount(double amt){
		this.amount = amt;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setCode(String code){
		this.code = code;
	}

	public void setDate(String date1){
		String delim = null;
		
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(date1.toString());
		if(matcher.find()){
			delim = matcher.group(0);
		}		
		SimpleDateFormat fmt = new SimpleDateFormat("dd"+delim.charAt(0)+"MM"+delim.charAt(0)+"yy");
		try {
			this.date = fmt.parse(date1);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void setUnits(double units){
		this.units = units;
	}
	public void setType(String type){
		this.type = type;
	}

	// IMPLEMENT DATE SORTING ON TRANSACTION ARRAYLISTS
	public int compareTo(Transaction other) {
        return date.compareTo(other.date);
    }

	// PRINT TRANSACTION DETAILS
	public void printTransaction(){
		System.out.println(this.code+"  "+this.name+"  "+this.date+"  "+this.amount+"  "+this.units+"   "+this.type);
	}
}
