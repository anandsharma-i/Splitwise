package splitwise;
import java.util.*;
public class Splitwise
{
	//Debt-Clearing method
	static long debt_repay(long ep,long sp)
	{
		if(ep>sp)//if,extra-paid amount > short-paid amount.
		{
			return 0;
		}
		else if(ep<sp)//if,extra-paid amount < short-paid amount.
		{
			
			return(1);
		}
		else//if,extra-paid amount==short-paid amount.
		{			
			return(2);
		}
		
	}	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		 Map<String,Long> map = new HashMap<String,Long>(); 
		 	System.out.println("Enter no. of entries : ");//No. of persons. 
		 	int n = sc.nextInt();
		 	System.out.println("Enter the rate : ");//Rate:The required amount an individual needs to pay. 
	        int r = sc.nextInt();      	        
	        int i=0,c=0,c1=0,ce=0,flag=0,t=0;//counter variables for different arrays.
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
	        	for(i=0;i<c1;i++)//Running loop over shortage[] only(This will update only sp and not ep).
	        	{	
	        		//ce will be used to update ep.Initially ce=0.
	          		again :  
	          		{		
		        	    if(ce==c)//If the, No. of shortage payers > No. of extra payers. 
		        	    {
		        	    	System.out.println("\nThere are no extra payers left!,Clear your debts with the Owner.");
		        	    	flag=1;
		        	    	t=i;//storing current position of shortage[] in 't'.
		        	    	break;//Coming Out of loop.
		        	    }
		        	    else
		        	    {	        	    	
		        	    	x=debt_repay(ep[ce],sp[i]);//Calling debt_repay()
		        	    }	        	    
		        		if(x==0)//Condition 1: ep>sp
		        		{
		        			/*In this condition :
		        			  1.Since sp<ep ,so that ep can be used to fulfill that particular sp.
		        			  2.So we'll assume sp=0.
		        			  3.And update ep to ep=ep-sp 
		        			  */
		        			System.out.println("\n"+shortage[i]+" - You owe "+sp[i]+" to "+ extra[ce]);
		        			ep[ce]=ep[ce]-sp[i];	        				        				        		
		        		}
		        		else if(x==1)//Condition 2: ep<sp
		        		{
		        			/*In this condition :
		        			  1.Since ep<sp ,so that ep can't be used to fulfill that particular sp.
		        			  2.So we'll assume ep=0 and update ep to the next position of extra[].
		        			  3.And update sp to sp=sp-ep 
		        			  */
		        			System.out.println("\n"+shortage[i]+" - You owe "+ep[ce]+" to "+ extra[ce]);
		        			sp[i]=sp[i]-ep[ce];
		        			ep[ce]=ep[++ce];
		        			break again;
		        			/*1.Using break as a form of goto to check for all conditions again without
		        			    iterating 'i' or without jumping to next position of shortage[].
		        			  2.We do that bcoz this particular sp is not fulfilled yet from the particular ep
		        			  3.So we fulfill sp till whatever point that particular ep can.
		        			  4.then update sp and ep as as discussed above. */
		        		}
		        		else if(x==2)//Condition 3: ep==sp
		        		{
		        			/*In this condition :
		        			  1.Since sp==ep ,so that ep can be used to fulfill that particular sp.
		        			  2.So we'll assume sp=0 and ep=0.
		        			  3.And update ep to the next position of extra[].		        			  
		        			  */
		        			System.out.println("\n"+shortage[i]+" - You owe "+sp[i]+" to "+ extra[ce]);
		        			ep[ce]=ep[++ce];	        			
		        		}
	          		}	        	
	        	}	 
	        	//Printing the rest of left-out short-payers.
	        	if(flag==1)
	        	{
	        		for(i=t;i<c1;i++)
	        		{
	        			System.out.println("\n"+shortage[i]+" - You owe "+sp[i]+" to owner.");
	        		}
	        	}
	        	sc.close();
	}
}