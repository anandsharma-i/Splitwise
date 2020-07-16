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
	        int i=0,c=0,c1=0,ce=0;
	        long x=0;
	        long[] ep =new long[50];	
	        long[] sp =new long[50];
	        String[] extra = new String[50]; 
	        String[] shortage = new String[50];
	        for(i=0;i<n;i++)
	        {
	        	System.out.println("\nEnter Name : ");	
	            String name = sc.next();
	            System.out.println("Amount paid : ");
	            int paid = sc.nextInt();
	            map.put(name,(long)paid);	            
	        }
	        i=0;
	        System.out.println("\n::PAID-LIST::");
	        for(Map.Entry<String,Long> map2 : map.entrySet())
	        {
	        	System.out.print(++i+"." +map2.getKey()+ " : ");
	        	System.out.println(map2.getValue());
	        }
	        for(Map.Entry<String,Long> map2 : map.entrySet())
	        {
	        	if(map2.getValue()>r)
	        	{
	        		extra[c++]=map2.getKey();	        		
	        	}
	        	else if(map2.getValue()<r)
	        	{
	        		shortage[c1++]=map2.getKey();
	        	}
	        }	        
	        System.out.println("\n::EXTRAS-LIST::");
	        for(i=0;i<c;i++)
	        {	    
	        	ep[i]=(map.get(extra[i])-r);
	        	System.out.println((i+1) + ". " + extra[i]+" - "+(ep[i]));	        		
	        }
	        System.out.println("\n::SHORTAGE-LIST::");
	        for(i=0;i<c1;i++)
	        {	        	
	        	sp[i]=(r-map.get(shortage[i]));	
	        	System.out.println((i+1) + ". " + shortage[i]+" - "+(sp[i]));
	        }	      
	          	for(i=0;i<c1;i++)
	        	{	
	          		again :
	          		{		
		        	    if(ce==c)
		        	    {
		        	    	System.out.println("\nThere are no extra payers left!,Clear your debts with the Owner.");
		        	    	break;
		        	    }
		        	    else
		        	    {	        	    	
		        	    	x=debt_repay(ep[ce],sp[i]);
		        	    }	        	    
		        		if(x==0)
		        		{
		        			System.out.println("\n"+shortage[i]+" - You owe "+sp[i]+" to "+ extra[ce]);
		        			ep[ce]=ep[ce]-sp[i];	        				        				        		
		        		}
		        		else if(x==1)
		        		{
		        			System.out.println("\n"+shortage[i]+" - You owe "+ep[ce]+" to "+ extra[ce]);
		        			sp[i]=sp[i]-ep[ce];
		        			ep[ce]=ep[++ce];
		        			break again;
		        		}
		        		else if(x==2)
		        		{
		        			System.out.println("\n"+shortage[i]+" - You owe "+sp[i]+" to "+ extra[ce]);
		        			ep[ce]=ep[++ce];	        			
		        		}
	          		}
	        	}	        
		sc.close();
	}
}
