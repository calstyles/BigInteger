import java.util.LinkedList;
import java.util.Collections;

/**
 * A class using Linked Lists to implement the LargeInteger interface
 * 
 * @author Caleb Styles
 * @version November 27, 2021
 */

public class LinkedLargeInteger implements LargeInteger {
    
	public boolean negCheck = false;
	public LinkedList<Integer> linkList = new LinkedList<Integer>();
	
	/**
	 * 
	 * @param linkList the constructor for the list that we will be editing
	 */
	
	private LinkedLargeInteger(LinkedList<Integer> linkList){
        this.linkList = linkList;
    }
	
	/**
	 * 
	 * @param b changes the state of the number so it can be used by negate and abs
	 * @param linkL the variable that will be used to represent the list being used
	 */
	
	private LinkedLargeInteger(boolean b, LinkedList<Integer> linkL) {
		this.negCheck = b;
		linkList = linkL;
	}
	
	/**
	 * 
	 * @param str Takes a string and converts it into an int and also determines if it's negative
	 */
	
	public LinkedLargeInteger(String str) {
		
		String[] inputArray = str.split("");
		
		int i = 0;
		
		if(inputArray[0].equals("-")) {
			
			inputArray[0] += 1;
			negCheck = true;
			i += 1;
		}
		
		for(; i < inputArray.length; i++) {
			linkList.add(Integer.parseInt(inputArray[i]));
		}
	}
	
	/**
	 * 
	 * @param str takes a string and will change it in order to get the linked list value
	 * @return the linked list value of the string
	 */
	
	public LinkedList<Integer> liGenerator(String str) {
		
		LinkedList<Integer> outputList = new LinkedList<Integer>();
		
		if(str.charAt(0) == '-'){
            negCheck = true;
        }
        
		int i;

        if(negCheck == true){
            i = 1;
        }else{
            i = 0;
        }
		
        while(i < str.length()) {
            outputList.add(Character.getNumericValue(str.charAt(i)));
            i++;
        }
		return outputList;
	}
	
	@Override
	public int compareTo(Object object) {	
		return 0;
	}

	@Override
	public LargeInteger add(LargeInteger largeInt) {	
		if((this.signum() > 0) && (largeInt.signum() > 0)) {
			
			LinkedList<Integer> linkList1 = new LinkedList<Integer>();
			LinkedList<Integer> linkList2 = liGenerator(largeInt.toString());
			
			if (linkList.size() > linkList2.size()){
		        LinkedList<Integer> templiList = linkList;
		        linkList = linkList2;
		        linkList2 = templiList;
		    }
		 
		    int n1 = linkList.size(), n2 = linkList2.size(), c = 0;
		    for (int i = 0; i < n2; i++){
		    	int i1 = n2-i-1;
		    	int i2 = n1-i-1;
		    	if(i2 >= 0) {
		    		int total = ((linkList.get(i2)) + (linkList2.get(i1)) + c);
			        linkList1.add(total % 10);
			        c = total / 10;
		    	}else {
		    		int total = ((linkList2.get(i1)) + c);
			        linkList1.add(total % 10);
			        c = total / 10;
		    	}
		    }
		 
		    if(c > 0) {
		    	linkList1.add(c);
		    }
		    
		    String total = "";
		    
		    for(int i = linkList1.size()-1; i >= 0; i--) {
		    	total += linkList1.get(i) + "";
		    }
		    
		    LargeInteger temp = new LinkedLargeInteger(total);
		    return temp;
		    
		}else if((this.signum() > 0) && (largeInt.signum() < 0)) {
			return (this.subtract(largeInt.negate()));
		}else if ((this.signum() < 0) && (largeInt.signum() > 0)) {
			return largeInt.subtract(this.negate());
		}else if((this.signum() < 0) && (largeInt.signum() < 0)) {
			return (this.negate().add(largeInt.negate())).negate();
		}else if (this.signum() == 0) {
			return largeInt;
		}else if (largeInt.signum() == 0) {
			return this;
		}
		return null;
	}

	@Override
	public LargeInteger subtract(LargeInteger largeInt) {
		
		if(((this.signum() == 1) && (largeInt.signum() == 1)) || ((this.signum() == 0) && (largeInt.signum() ==1 ))
				|| ((this.signum() == 1 ) && largeInt.signum() == 0) ){
			
			  	LargeInteger largeInt1 = this.min(largeInt);
	            LargeInteger largeInt2 = this.max(largeInt); 
	            LinkedList<Integer> liLinkedList1 = liGenerator(largeInt2.toString());
	            LinkedList<Integer> liLinkedList2 = liGenerator(largeInt1.toString());
	            LinkedList<Integer> finalList = new LinkedList<>();
	            int num1 = liLinkedList1.size();
	            int num2 = liLinkedList2.size();
	            int dif = num1 - num2;
	            int c = 0;
	            boolean tmp = false;
	            if(this.equals(largeInt1)) {
	            	tmp = true;
	            }
	            for(int i = num2 - 1; i >= 0; i--){
	                int answer = liLinkedList1.get(i + dif) - liLinkedList2.get(i) - c;
	                if(answer < 0) {
	                    answer = answer + 10;
	                    c = 1;
	                }else{
	                    c = 0;
	                }
	                finalList.add(answer);
	            }
	            for(int i = num1 - num2 - 1; i >= 0; i--){
	                if(liLinkedList1.get(i) == 0 && c > 0){
	                    finalList.add(9);
	                    continue;
	                }
	                int answer = liLinkedList1.get(i) - c;
	                if(i > 0 || answer > 0){
	                    finalList.add(answer);
	                }
	                c = 0;
	            }
	            
	            
	            Collections.reverse(finalList);
	            LargeInteger output = new LinkedLargeInteger(finalList);
	            if(tmp == true) {
	            	output = output.negate();
	            }
	            return output;
		
			}else if((this.signum() > 0) && (largeInt.signum() < 0)) {
	    		return this.add(largeInt.negate());
	    	}else if((this.signum() < 0) && (largeInt.signum() > 0)) {	    		
	    		return (largeInt.add(this.negate())).negate();
	    	}else if((this.signum() < 0) && (largeInt.signum() < 0)) {
	    		return (this.negate().subtract(largeInt.negate())).negate();
	    	}else if(this.signum() == 0) {
	    		return largeInt;
	    	}else if(largeInt.signum() == 0) {
	    		return this;
	    	}
	    	return null;
	}
	
	@Override
	public LargeInteger negate() {		
		if(this.signum()==0) {
			return this;
		}
		LargeInteger temp = new LinkedLargeInteger(!negCheck, linkList);
		return temp;
    }

	@Override
	public LargeInteger abs() {		
		LargeInteger temp = new LinkedLargeInteger(false, linkList);
		return temp;
	}

	@Override
	public LargeInteger max(LargeInteger largeInt) {
	
		if(this.signum() == 1 && largeInt.signum() == -1) {
			return this;
		}else if(this.signum() == -1 && largeInt.signum() == -1) {
			if((this.add(largeInt)).signum() == 1) {
				return this;
			}
			return largeInt; 
		}else if(this.signum() > largeInt.signum()) {
			return this;
		}else if(this.signum() < largeInt.signum()) {
			return largeInt;
		}
		
		LinkedList<Integer> linkList2 = liGenerator(largeInt.toString());
		
		if(linkList.size() > linkList2.size()) {
			return this;
		}else if(linkList.size() < linkList2.size()) {
			return largeInt;
		}else if(linkList.size() == linkList2.size()) {	
			for(int i = 0; i < linkList.size(); i++) {
				if(linkList.get(i) > linkList2.get(i)) {
					return this;
				}
				if(linkList2.get(i) > linkList.get(i)) {
					return largeInt;
				}
			}
		}
		return null;
	}

	@Override
	public LargeInteger min(LargeInteger largeInt) {
		
		if(this.signum() == 1 && largeInt.signum() == -1) {
			return largeInt;
		}else if(this.signum() == -1 && largeInt.signum() == -1) {
			if((this.add(largeInt)).signum() == 1) {
				return largeInt;
			}
			return this;
		}
		if(this.signum() > largeInt.signum()) {
			return largeInt;
		}else if(this.signum() < largeInt.signum()) {
			return this;
		}
		LinkedList<Integer> linkList2 = liGenerator(largeInt.toString());
		
		if(linkList.size() < linkList2.size()) {
			return this;
		}else if(linkList.size() > linkList2.size()) {
			return largeInt;
		}else if(linkList.size() == linkList2.size()) {		
			for(int i = 0; i < linkList.size(); i++) {
				
				if(linkList.get(i) > linkList2.get(i)) {
					return largeInt;
				}
				
				if(linkList2.get(i) > linkList.get(i)) {
					return this;
				}
			}			
		}				
		return null;
	}

	@Override
	public int signum() {
        boolean zCheck = true;
        for(int i = 0; i < linkList.size(); i++){
            if(linkList.get(i) != 0){
                zCheck = false;
            }
        }

        if(zCheck){
            return 0;
        }else if(!this.negCheck){
            return 1;
        }        
        return -1;    
	}
	
    @Override
	public int hashCode() {
		int solution = 1;
		int x = 1231, y = 0;
		if(negCheck) {
		}else {
			x += 6;
		}
		if(linkList == null) {
		}else {
			y += linkList.hashCode();
		}
		solution = 31 * solution + x;
		solution = 31 * solution + y;
		return solution;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		LinkedLargeInteger objArrLargeInt = (LinkedLargeInteger) obj;
		if(negCheck != objArrLargeInt.negCheck) {
			return false;
		}
		if(linkList == null) {
			if(objArrLargeInt.linkList != null) {
				return false;
			}
		}else if(!linkList.equals(objArrLargeInt.linkList)) {
			return false;
		}
		return true;
	}

	@Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	if(negCheck) {
    		sb.append("-");
    	}    	
    	for(int i = 0; i < linkList.size(); i++) {
    		sb.append(linkList.get(i));
    	}
        return sb.toString();
    }
}