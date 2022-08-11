import java.util.ArrayList;
import java.util.Collections;

/**
 * A class using ArrayLists to implement the LargeInteger interface
 * 
 * @author Caleb Styles
 * @version November 27, 2021
 */

public class ArrayLargeInteger implements LargeInteger{
	
	public boolean negCheck = false;
	public ArrayList<Integer> arrList = new ArrayList<Integer>();
	
	/**
	 * 
	 * @param arrList the constructor for the list that we will be editing
	 */
	
	private ArrayLargeInteger(ArrayList<Integer> arrList){
        this.arrList = arrList;
    }
	
	/**
	 * 
	 * @param b changes the state of the number so it can be used by negate and abs
	 * @param arrL the variable that will be used to represent the list being used
	 */
	
	public ArrayLargeInteger(boolean b, ArrayList<Integer> arrL) {
		this.negCheck = b;
		arrList = arrL;
	}
	
	/**
	 * 
	 * @param str Takes a string and converts it into an int and also determines if it's negative
	 */
	
	public ArrayLargeInteger(String str) {
		
		String[] inputArray = str.split("");
		
		int i = 0;
		
		if(inputArray[0].equals("-")) {
			
			inputArray[0] += 1;
			negCheck = true;
			i += 1;
		}
		
		for(; i < inputArray.length; i++) {
			arrList.add(Integer.parseInt(inputArray[i]));
		}
	}

	/**
	 * 
	 * @param str takes a string and will change it in order to get the linked list value
	 * @return the linked list value of the string
	 */
	
	public ArrayList<Integer> alGenerator(String str) {
		
		ArrayList<Integer> outputList = new ArrayList<Integer>();
		
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
		if((this.signum() == 1) && (largeInt.signum() == 1)) {
			
			ArrayList<Integer> arrList1 = new ArrayList<Integer>();
			ArrayList<Integer> arrList2 = alGenerator(largeInt.toString());
			
			if (arrList.size() > arrList2.size()){
		        ArrayList<Integer> tempArrList = arrList;
		        arrList = arrList2;
		        arrList2 = tempArrList;
		    }
		 
		    int n1 = arrList.size(), n2 = arrList2.size(), c = 0;
		    for (int i = 0; i < n2; i++){
		    	int i1 = n2-i-1;
		    	int i2 = n1-i-1;
		    	if(i2 >= 0) {
		    		int total = ((arrList.get(i2)) + (arrList2.get(i1)) + c);
			        arrList1.add(total % 10);
			        c = total / 10;
		    	}else {
		    		int total = ((arrList2.get(i1)) + c);
			        arrList1.add(total % 10);
			        c = total / 10;
		    	}
		    }
		 
		    if(c > 0) {
		    	arrList1.add(c);
		    }
		    
		    String total = "";
		    
		    for(int i = arrList1.size()-1; i >= 0; i--) {
		    	total += arrList1.get(i) + "";
		    }
		    
		    LargeInteger temp = new ArrayLargeInteger(total);
		    return temp;		    
		}
		else if((this.signum() > 0) && (largeInt.signum() < 0)) {
			return (this.subtract(largeInt.negate()));
		}
		else if ((this.signum() < 0) && (largeInt.signum() > 0)) {
			return largeInt.subtract(this.negate());
		}
		else if((this.signum() < 0) && (largeInt.signum() < 0)) {
			return (this.negate().add(largeInt.negate())).negate();
		}
		else if (this.signum() == 0) {
			return largeInt;
		}
		else if (largeInt.signum() == 0) {
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
	            ArrayList<Integer> liArrayList1 = alGenerator(largeInt2.toString());
	            ArrayList<Integer> liArrayList2 = alGenerator(largeInt1.toString());
	            ArrayList<Integer> finalList = new ArrayList<>();
	            int num1 = liArrayList1.size();
	            int num2 = liArrayList2.size();
	            int dif = num1 - num2;
	            int c = 0;
	            boolean tmp = false;
	            if(this.equals(largeInt1)) {
	            	tmp = true;
	            }
	            for(int i = num2 - 1; i >= 0; i--){
	                int answer = liArrayList1.get(i + dif) - liArrayList2.get(i) - c;
	                if(answer < 0) {
	                    answer = answer + 10;
	                    c = 1;
	                }else{
	                    c = 0;
	                }
	                finalList.add(answer);
	            }
	            for(int i = num1 - num2 - 1; i >= 0; i--){
	                if(liArrayList1.get(i) == 0 && c > 0){
	                    finalList.add(9);
	                    continue;
	                }
	                int answer = liArrayList1.get(i) - c;
	                if(i > 0 || answer > 0){
	                    finalList.add(answer);
	                }
	                c = 0;
	            }
	            Collections.reverse(finalList);
	            LargeInteger output = new ArrayLargeInteger(finalList);
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
		if(this.signum() == 0) {
			return this;
		}      
		LargeInteger temp = new ArrayLargeInteger(!negCheck, arrList);
		
		return temp;
    }

	@Override
	public LargeInteger abs() {		
		LargeInteger temp = new ArrayLargeInteger(false, arrList);
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
		}
		
		if(this.signum() > largeInt.signum()) {
			return this;
		}else if(this.signum() < largeInt.signum()) {
			return largeInt;
		}
		
		ArrayList<Integer> arrList2 = alGenerator(largeInt.toString());
		
		if(arrList.size() > arrList2.size()) {
			return this;
		}else if(arrList.size() < arrList2.size()) {
			return largeInt;
		}else if(arrList.size() == arrList2.size()) {	
			for(int i = 0; i < arrList.size(); i++) {
				if(arrList.get(i) > arrList2.get(i)) {
					return this;
				}
				if(arrList2.get(i) > arrList.get(i)) {
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
		ArrayList<Integer> arrList2 = alGenerator(largeInt.toString());
		
		if(arrList.size() < arrList2.size()) {
			return this;
		}else if(arrList.size() > arrList2.size()) {
			return largeInt;
		}else if(arrList.size() == arrList2.size()) {		
			for(int i = 0; i < arrList.size(); i++) {
				
				if(arrList.get(i) > arrList2.get(i)) {
					return largeInt;
				}
				
				if(arrList2.get(i) > arrList.get(i)) {
					return this;
				}
			}			
		}				
		return null;
	}

	@Override
	public int signum() {
		
        boolean zCheck = true;

        for(int i = 0; i < arrList.size(); i++){
            if(arrList.get(i) != 0){
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
		if(arrList == null) {
		}else {
			y += arrList.hashCode();
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
		ArrayLargeInteger objArrLargeInt = (ArrayLargeInteger) obj;
		if(negCheck != objArrLargeInt.negCheck) {
			return false;
		}
		if(arrList == null) {
			if(objArrLargeInt.arrList != null) {
				return false;
			}
		}else if(!arrList.equals(objArrLargeInt.arrList)) {
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
    	for(int i = 0; i < arrList.size(); i++) {
    		sb.append(arrList.get(i));
    	}
        return sb.toString();
    }
}