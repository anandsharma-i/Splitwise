package splitwise;
import java.util.*;
public class Splitwise
{
	static long debt_repay(long ep,long sp)
	{
		if(ep>sp)
		{
			return 0;
		}
		else if(ep<sp)
		{
			
			return(1);
		}
		else
		{			
			return(2);
		}
		
	}
	static long flag=0;
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		 Map<String,Long> map = new HashMap<String,Long>(); 
		 	System.out.println("Enter no. of entries : ");//No. of persons. 
		 	int n = sc.nextInt();
		 	System.out.println("Enter the rate : ");//Rate:The required amount an individual needs to pay. 
	        int r = sc.nextInt();      	        
	        int i=0,c=0,c1=0,ce=0;//counter variables for different arrays.
	        long x=0;
	        long[] ep =new long[50];//Array to store all extra-payments(ep) of respective extra payers.	
	        long[] sp =new long[50];//Array to store all shortage-payments(sp) of respective shortage payers.
	        String[] extra = new String[50];//Array to store all extra payers. 
	        String[] shortage = new String[50];//Array to store all shortage payers.
	        //Mapping for all the persons with their amount payed.
	        for(i=0;i<n;i++)
	        {
	        	System.out.println("\nEnter Name : ");	
	            String name = sc.next();
	            System.out.println("Amount paid : ");
	            int paid = sc.nextInt();
	            map.put(name,(long)paid);	            
	        }
	        i=0;//Reset i=0 for going to the top of the Map "map".
	        System.out.println("\n::PAID-LIST::");
	        //Printing the contents of "map".
	        for(Map.Entry<String,Long> map2 : map.entrySet())
	        {
	        	System.out.print(++i+"." +map2.getKey()+ " : ");
	        	System.out.println(map2.getValue());
	        }
	        //Sorting extra and shortage payers to their respective arrays.
	        for(Map.Entry<String,Long> map2 : map.entrySet())
	        {
	        	if(map2.getValue()>r)
	        	{
	        		extra[c++]=map2.getKey();//Pay close attention to variable 'c'.	        		
	        	}
	        	else if(map2.getValue()<r)
	        	{
	        		shortage[c1++]=map2.getKey();//Pay close attention to variable 'c1'.
	        	}
	        }	        
	        //Printing the contents of extra and shortage list.
	        System.out.println("\n::EXTRAS-LIST::");
	        for(i=0;i<c;i++)//Using c as the array-length for extra[].
	        {	    
	        	ep[i]=(map.get(extra[i])-r);//Storing the extra-paid amount of the respective persons. 
	        	System.out.println((i+1) + ". " + extra[i]+" - "+(ep[i]));	        		
	        }
	        System.out.println("\n::SHORTAGE-LIST::");
	        for(i=0;i<c1;i++)//Using c1 as the array-length for shortage[].
	        {	        	
	        	sp[i]=(r-map.get(shortage[i]));//Storing the short-paid amount of the respective persons.	
	        	System.out.println((i+1) + ". " + shortage[i]+" - "+(sp[i]));
	        }	      
	          	//Debt-Clearing
	        	for(i=0;i<c1;i++)//Running loop over shortage[] only.
	        	{	
	          		again :
	          		{		
		        	    if(ce==c)//If the, No. of shortage payers < No. of extra payers. 
		        	    {
		        	    	System.out.println("\nThere are no extra payers left!,Clear your debts with the Owner.");
		        	    	break;
		        	    }
		        	    else
		        	    {	        	    	
		        	    	x=debt_repay(ep[ce],sp[i]);//Calling debt_repay()
		        	    }	        	    
		        		if(x==0)//Condition 1:
		        		{
		        			System.out.println("\n"+shortage[i]+" - You owe "+sp[i]+" to "+ extra[ce]);
		        			ep[ce]=ep[ce]-sp[i];	        				        				        		
		        		}
		        		else if(x==1)//Condition 2:
		        		{
		        			System.out.println("\n"+shortage[i]+" - You owe "+ep[ce]+" to "+ extra[ce]);
		        			sp[i]=sp[i]-ep[ce];
		        			ep[ce]=ep[++ce];
		        			break again;//Using break as a form of goto to check for all conditions again
		        			//without iterating 'i' or without jumping to next position of shortage[].
		        		}
		        		else if(x==2)//Condition 3:
		        		{
		        			System.out.println("\n"+shortage[i]+" - You owe "+sp[i]+" to "+ extra[ce]);
		        			ep[ce]=ep[++ce];	        			
		        		}
	          		}
	        	}	        
		sc.close();
	}
}
