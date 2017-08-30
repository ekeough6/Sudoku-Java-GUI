import java.util.ArrayList;

public class Square{
	private String num;
	private ArrayList<String>exceptions;
	private boolean fixed;
	
	public Square(){
		num = " ";
		exceptions = new ArrayList<String>();
		fixed = false;
	}
	
	public Square(String n) {
		num = n;
		exceptions = new ArrayList<String>();
		if(!n.trim().isEmpty())
			fixed = true;
		else
			fixed = false;
	}
	
	public void change(String n){
		num = n;
	}
	
	public String getValue(){
		return num;
	}

	public void addException(String ex){
		exceptions.add(ex);
	}
	
	public ArrayList<String> getExceptions(){
		return exceptions;
	}
	
	public boolean meetsException(String n){
		for(String e:exceptions){
			if(e.equals(n))
				return false;
		}
		return true;
	}
	
	public void clearExceptions(){
		exceptions = new ArrayList<String>();
	}
	
	public boolean isFixed(){
		return fixed;
	}
	
}
