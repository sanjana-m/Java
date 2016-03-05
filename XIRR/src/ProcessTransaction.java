import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProcessTransaction {
	
	public List<Transaction> trans;
	public List<List<Transaction>> funds;
	public Map<String, List<Transaction>> map;
	
	public ProcessTransaction(){
		this.trans = new ArrayList<Transaction>();
		this.map = new HashMap<String, List<Transaction>>();
	}

	// Pass the filename and the columns for processing the Investment data, and storing them in a HashMap - 'map'
	public void readData(String file, int namecol, int typecol, int datecol, int amtcol, int unitcol, int codecol){
		String filename = file.replace("\\","\\\\");		
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		String date, type, name, amt1 = null, amt2 = null, code;
		Double units, amount;
		int count = 0;
		List<Transaction> list1;
		try {
			br = new BufferedReader(new FileReader(filename));

			while ((line = br.readLine()) != null) {
				if(count == 0) {
					count++;
					continue;
				}

/*
				TO BE USED WHEN AMOUNTS ARE USED IN B/W QUOTES
				
				Pattern pattern = Pattern.compile("(?<=\")(.*)(?=\")");
				Matcher matcher = pattern.matcher(line.toString());
				if(matcher.find()){
					amt2 = matcher.group(0);
					amt1 = amt2.replace(",","");
				}
				amount = new Double(amt1);
*/				
				line = line.replace(("\""+amt2+"\""),"");
				String[] flow = line.split(cvsSplitBy);
				amount = new Double(flow[amtcol].replace(",",""));
				date = new String(flow[datecol].replace("\\s",""));
				code = new String(flow[codecol].replace("\\s",""));
//				System.out.println("CODE - "+code);
				units = new Double(flow[unitcol]);
				name = new String(flow[namecol]);
				type = new String(flow[typecol].replace("\\s",""));
				
				if(type.equals("RED") && units > 0){
					units*=(-1);
				}
				else if(type.equals("DP")){
					// IGNORING DIVIDEND PAYOUTS
					continue;
				}
				else if(type.equals("SI")){
					type = "PURA";
				}
				else if(type.equals("SO")){
					type = "RED";
				}
				else if(type.equals("TI")){
					type = "PUR";
				}
				else if(flow[unitcol].replace("\\s","").equals("")){
					continue;
				}
				
				Transaction t = new Transaction(name,code,amount,date,type,units);
//				System.out.println("DATE1 - "+flow[datecol]+" dayt: "+t.getDate());
				if (map.containsKey(code)) {
					map.get(code).add(t);
				}
				else {
//					System.out.println("ADDING "+code);
					list1 = new ArrayList<Transaction>();
					list1.add(t);
					map.put(code,list1);
				}
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	// Returns a List of strings containing the names of the funds
	public List<String> fundNames(){
		List<String> fund = new ArrayList<String>();
		Set st = map.keySet();
		Iterator i = st.iterator();
		while(i.hasNext()){
			fund.add(i.next().toString());
		}
		return fund;
	}
	
	// Adding redemptions for each transaction
	public List<Transaction> addRedemptions(List<Transaction> t){
		double sum = 0.0;
		Transaction p = null;
//		System.out.println("HERE - "+t.size());
		int i=0;
		while(i < t.size()){
			if(t.get(i).getType().equals("RED")){
				p = t.get(i);
				sum = sum + t.get(i).getUnits();
				t.remove(i);
			}
			else{
				i = i+1;
			}
		}
		if(sum != 0.0){
			p.setUnits((double)Math.round(sum*10000)/10000d);
			System.out.println("AFTER ADDING REDEMPTION - ");
			p.printTransaction();
			t.add(p);
		}
		
		return t;
	}
	
	
	// Balancing redemptions with purchases
	public List<Transaction> knockOff(List<Transaction> t){
		List<Transaction> s = this.addRedemptions(t);
		int i=0;
		double unit = 0.0;
		double amount = 0.0;
		if(t.size() == 1){
			return t;
		}
		else if(t.get(t.size()-1).getUnits() > 0){
			System.out.println("No Need to Knock Off - "+t.get(0).getCode());
			return t;
		}
		Transaction a = t.remove(t.size()-1);
		Collections.sort(t);
		t.add(a);
		
		while((i < s.size()) && (s.get(i).getUnits() + s.get(s.size()-1).getUnits() < 0) && (!s.get(i).getType().equals("RED"))){
			unit = s.get(s.size()-1).getUnits() + s.get(i).getUnits();
			s.get(s.size()-1).setUnits(unit);
			s.remove(i);
		}
		if(s.get(i).getUnits() + s.get(s.size()-1).getUnits() > 0){
			System.out.println("KNOCKOFF DONE");
			unit = s.get(i).getUnits();
			amount = s.get(i).getAmount();
			s.get(i).setUnits((double)Math.round((Math.abs(unit + s.get(s.size()-1).getUnits()))*10000)/10000d);
			s.get(i).setAmount((double)Math.round((amount*(s.get(i).getUnits() / unit)*10000))/10000d);
			s.remove(s.size()-1);
		}
		else if(s.get(i).getUnits() + s.get(s.size()-1).getUnits() == 0){
			s.remove(i);
			s.remove(s.size()-1);
		}
		if(s.size() == 0){
			System.out.println("EMPTY");
			return null;
		}
		return s;
	}
		
	//Returns the sum of units from a series of transactions
	public double sumUnits(List<Transaction> list){
		double sum = 0.0;
		for(int i=0; i< list.size(); i++){
			sum = sum + list.get(i).getUnits();
		}
		sum = (double)Math.round(sum * 10000d) / 10000d;
		return sum;
	}

	
	// Initializes the 'funds' variable which creates a list of transactions for each fund
	public void getFunds(){
		funds = new ArrayList<List<Transaction>>();
		List<Transaction> tr;
		List<Transaction> knock;
		
		int fundcount=0;
		double sum = 0.0;
		
		Set st = map.keySet();
		Iterator i = st.iterator();
		String key;
		
		while(i.hasNext()){
			key = i.next().toString();
			tr = map.get(key);
			sum = sumUnits(tr);
			if(sum == 0.0){
				System.out.println("SUM IS 0 FOR - "+key);
			}
			else{
				knock = knockOff(tr);
				if(knock!=null){
					System.out.println("Adding this fund - "+key);
					funds.add(knock);
					fundcount++;
				}
			}
		}
	}

	public void printTransactions(){
		if(this.funds.size() > 0){
			for(List<Transaction> i : this.funds){
				System.out.println("FUND - "+i.get(0).getName());
				for(Transaction j : i)
					j.printTransaction();
				
			}
		}
	}
}
