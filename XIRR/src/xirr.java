import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.Math;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class xirr {
	public double[] cash;
	public Date[] dates;
	public double xirr1=0.0;
	
	public xirr(List<Transaction> t){
		int j=0;
		this.cash = new double[t.size()+1];
		this.dates = new Date[t.size()+1];
		Transaction p;
		for(j=0; j<t.size(); j++){
			p = t.get(j);
			this.cash[j] = p.getAmount();
			this.dates[j] = p.getDate();
			System.out.println(this.cash[j]+"   "+this.dates[j]);
		}
		double newamt = 0.0;
		System.out.println("Enter total amount - ");
		Scanner k = new Scanner(System.in);
		newamt = k.nextDouble();
		this.cash[j] = (newamt<0 ? (newamt) : (newamt*(-1)));
		this.dates[j] = new Date();
		System.out.println(this.cash[j]+"   "+this.dates[j]);
	}
	
	public double sppv(double i, double n){
		double sum=0.0;
		sum =  Math.pow((1+(i/100)),(-n));
		return sum;
	}
	
	public double npv(Double[][] amt, double i){
		double npv = 0.0;
		double pvs = 0.0;
		for(int j=0; j<amt.length; j++ ){
			pvs = pvs + (amt[j][0]*(sppv(i,amt[j][1])));
		}
		npv = pvs;
		System.out.println("NPV IS "+npv);
		return npv;
	}
	
	public int dateDiff(Date date1, Date date2){
		long diff = 0;
		int days = 0;
		try{
			diff = date2.getTime() - date1.getTime();
			days = (int) (diff/(1000 * 60 * 60 * 24));
		} catch (Exception e) {
		    e.printStackTrace();
		}			
		return days;
	}
	
	public double calcXirr(){
		
		if(this.cash.length != this.dates.length){
			System.out.println("INSUFFICIENT DATA");
			return 0.0;
		}
		int i=0,j=0;
		ArrayList<ArrayList<Double>> cashflow_adj1 = new ArrayList<ArrayList<Double>>();
		int diff = 0;
		for(i=0; i<(cash.length); i++){
			diff = this.dateDiff(dates[0],dates[i]);
			ArrayList<Double> row = new ArrayList<Double>();
			cashflow_adj1.add(row);
			cashflow_adj1.get(i).add((Double)cash[i]);
			cashflow_adj1.get(i).add(new Double(diff));
		}
		
		System.out.println(cashflow_adj1.size()+" IS THE LENGTH NOW!!");
		
		Double[][] cashflow_adj = new Double[cashflow_adj1.size()][2];
		i=0;
		for (ArrayList<Double> l : cashflow_adj1) 
			  cashflow_adj[i++] = l.toArray(new Double[l.size()]);
		
//		for(i=0; i< cashflow_adj.length; i++){
//			System.out.println(cashflow_adj[i][0] + "\t"+ cashflow_adj[i][1]);
//		}
				
		double left = -10.0;
		double right = 10.0;
		double epsilon = (double) Math.pow(10, -8);
		double midpoint = 0.0;
		int count=0;
		
		while(Math.abs(right-left) > (2*epsilon)){
			System.out.println("LEFT: "+left+" RIGHT:"+right+" COUNT: "+count);
			midpoint = (right+left)/2;
			if((npv(cashflow_adj,left)*npv(cashflow_adj,midpoint))>0){
				left = midpoint;
			}
			else{
				right = midpoint;
			}
			count++;
		}
		
		double irr = ((right+left)/2)/100;
		irr = irr *365.0;
		double xirr = (1+(irr/365));
		xirr = Math.pow(xirr, 365);
		xirr = xirr-1.0;
		System.out.println("THE XIRR IS: "+xirr);
		return (double)xirr;
	}
	
	public static void main(String[] args){
		ProcessTransaction p = new ProcessTransaction();
		p.readData(args[0], 5, 8, 4, 7, 6, 2);
		SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
//		List<String> fundnames = p.fundNames();
		p.getFunds();
//		
		try {
			PrintWriter writer = new PrintWriter("19352_java.csv", "UTF-8");

			for(int i=0; i<p.funds.size(); i++){
				List<Transaction> trans1 = p.funds.get(i);
				for(Transaction j : trans1){
					j.printTransaction();
					try {
						writer.println(j.getCode()+","+fmt.format((Date)j.getDate())+","+j.getName()+","+j.getUnits()+","+j.getAmount()+","+j.getType());
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
					p.trans.add(j);
				}
			}
			writer.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(p.trans);

//		Double[] cash = new Double[]{2600.0,4000.0,2000.0,3000.0,4000.0,4000.0,-25000.0};
//		String[] dates = new String[]{"01-01-2016","01-03-2016","01-05-2016","01-07-2016","01-08-2016","01-11-2016","01-12-2016"};
//		
//		Double[] cash = null;
//		String[] dates = null;

		System.out.println("\n\n\n --------------------------------------------\n\n\n");
		xirr x = new xirr(p.trans);
		x.calcXirr();
	}
}
