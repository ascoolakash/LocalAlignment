package resource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.swing.JOptionPane;

import org.w3c.dom.events.EventException;

public class LocalAlignment {
	public static String readFile(String fileName)throws Exception 
	  { 
	    String data = ""; 
	    data = new String(Files.readAllBytes(Paths.get(fileName))); 
	    return data; 
	  } 
	  
	  public static void main(String[] ar) throws Exception 
	  { 
		  
	    	String String1 = "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGCTTCTGAACTGGTTACCTGCCGTGAGTAAATTAAAATTTTATTGACTTAGGTCACTAAATACTTTAACCAATATAGGCATAGCGCACAGACAGATAAAAATTACAGAGTACACAACATCCATGAAACGCATTAGCACCACCATTACCACCACCATCA";
	    	System.out.println(String1);
	          
	    	String String2="CGTGAGTCAGCTATTGAACTGGCCGCGCAATGGAAGAGTTGTTAATCCGCAAAATCTGGCAAC";
	    	System.out.println(String2);
	    
		String k;
		//k = JOptionPane.showInputDialog("Enter number of top highest score: ");
		//System.out.println(k);
		//int k1=Integer.parseInt(k);
		int k1=10;
		//String Match;
		//Match =  JOptionPane.showInputDialog("Matching Character score : ");
		
		//int match = Integer.parseInt(Match);
		int match=20;
		//String Gap ;
		//Gap =  JOptionPane.showInputDialog("Gap Character score : ");
		//int gap = Integer.parseInt(Gap);
		int gap=-30;
		getScore(String2, String1, match, gap,k1); 
	  } 
	
	static void getScore(String String1, String String2, int reward, int penalitString2,int userInput) 
	  {
		  	int i, j = 0; 
		  	int m = String1.length(); 
		  	int n = String2.length(); 
			/*	
				Intialing dynamic programming table as dp.
			*/
		  	int dp[][] = new int[n + m + 1][n + m + 1]; 
		  	for (int[] String11 : dp) 
		  	Arrays.fill(String11, 0); 
			/*
				Initializing the first row and first column to zero.
			*/
		  	for (i = 0; i <= (n + m); i++) 
		  	{ 
		  		dp[i][0] =0 ; 
		  		dp[0][i] =0; 
		  	} 
			/*
				Filling dp value based on reward and penality.
			*/
		  	for (i = 1; i <= m; i++) 
		  	{ 
		  		for (j = 1; j <= n; j++) 
		  		{ 
		  			if (String1.charAt(i - 1) == String2.charAt(j - 1)) 
		  			{ 
		  				dp[i][j] = dp[i - 1][j - 1]+reward ; 
		  			} 
		  			else
		  			{ 
		  				dp[i][j] =Math.max(Math.max(Math.max(dp[i - 1][j - 1] - reward , dp[i - 1][j] + penalitString2), dp[i][j - 1] + penalitString2 ),0); 
		  			} 
		  		} 
		  	} 
			/*
				Printing Dynamic Programming table for testing and validation purpose.
			*/
			System.out.println("Printing Dynamic Programming table for testing and validation purpose.");
		  	for(i=0;i<m+1;i++){
		  		for(j=0;j<n+1;j++){	  			
		  			System.out.print("\t"+dp[i][j]);
		  		}
		  		System.out.print("\n");
		  	}
			
			/*
				Code trace back the score.
			*/

		  	int[] array = new int[100];
		  	int[] position = new int[100];
		  	int[] position1 = new int[100];
			int max = 0;
		  	int k=0;
		  	for(i=m;i>=0;i--){
			  	int p=0;
		  		max = dp[i][0]; 
		  		for(j=n;j>=0;j--){
				  if(dp[i][j]>max) {
					  	max=dp[i][j];
					  	p=j;
				  	  }
		  		}
		  		array[k]=max;	
		  		position[k]=p;
		  		k++;
		  	}
		  	int[] array1 = new int[k];
		  	int[] array2 = new int[k];
		  	
		  	for (int m1=0;m1<k;m1++) {
		  		array1[m1]=array[m1];
		  	}
		  	
		  	int jreverse = k; 
	        for (int ireverse = 0; ireverse < k; ireverse++) { 
	        	if(array1[ireverse]<=reward) {
	        		array2[jreverse - 1] = 0;
	        	}else {
	        		array2[jreverse - 1] = array1[ireverse]; 
	        	}
	        	position1[jreverse - 1] = position[ireverse]; 
	        	jreverse = jreverse - 1; 
	        }
	        k=array2.length-1;  
	        int stringMatch1[] = new int[n + 1]; 
	    	int stringMatch2[] = new int[n + 1]; 
	    	int lengthString1=m;
	    	int newParameter = n;
		/*
			Code till there is any match betweeen query and subject.
		*/
	    	try {
	    	for(int hit=1; hit<=userInput;hit++) {
	    		int iPosition = lengthString1; 
		    	int jPosition = n; 
	    		i=lengthString1;
	    		stringMatch1=replace(stringMatch1);
	    		stringMatch1=replace(stringMatch1);
	    		if(newParameter<n) {
	    			n=newParameter;
	    			String1=replace(String1,n);
	    		}
			  	
	    		while(i!=0) {
			  		j=n;
			  			if(array2[k]!=0 && position1[k]!=0) {
			  				jPosition=position1[k];
			  				while(j!=0) {
	
		    						if(array2[k]==dp[i][j]) {
				    			   		while ( !(i == 0 || j == 0)) 
			    					  	{ 
			    					  		if (String1.charAt(i - 1) == String2.charAt(j - 1)) 
			    					  		{ 
			    					  			stringMatch1[jPosition--] = (int)String1.charAt(i - 1); 
			    					  			i--; j--; 
			
			    					  		} 
			    					  		else if (dp[i - 1][j - 1] - reward == dp[i][j]) 
			    					  		{ 
			    					  			stringMatch1[jPosition--] = (int)'-'; 
			    					  			i--; j--; 
			    					  		} 
			    					  		else if (dp[i - 1][j] + penalitString2 == dp[i][j]) 
			    					  		{ 
			    					  			stringMatch1[jPosition--] = (int)'-'; 
			    					  			i--; 
			    					  		} 
			    					  		else if (dp[i][j - 1] + penalitString2 == dp[i][j]) 
			    					  		{ 
			    					  			stringMatch1[jPosition--] = (int)'-'; 
			    					  			j--; 
			    					  		} 
			    					  		else if (dp[i-1][j - 1]  == 0) 
			    					  		{ 
			    		    					//need to add some variable to store data/output to an array
			    					  			//System.out.println("inner while");
			    					  			break;
			    					  		} 
			    					  	} 
			    			} else {
		    			    				j--;
		    			    			}	
		    					}    					
		    			}else {
		    				i--;
		    				k--;
		    				lengthString1--;
		    			}
		    		try{
		    			if(dp[i][j]==0) {
			    			k--;
	    					i--;
			    			newParameter--;
			    			break;
			    		}
		    		}catch(EventException e) {
		    			System.out.println();
		    		}
		    	}
		    	lengthString1--;	    	
			while (jPosition > 0) 
			{ 
				if (i > 0) {
					stringMatch1[jPosition--] = (int)'-'; 
				}
				else {
					stringMatch1[jPosition--] = (int)'-'; 
				}
			} 
			int id = 1; 
			System.out.println("hit"+hit+": score"+array2[lengthString1+1]);
		    	for (i = id; i <= n; i++) 
		    	{ 
		    		System.out.print((char)stringMatch1[i]); 
		    	} 
		    	System.out.print("\n"); 
		    	System.out.print(String2); 
		    	System.out.print("\n");
		    	System.out.print("\n");
		    	
	    	
	    	}
	    	}catch(Exception e) {
	    		System.out.println("All the value of K has been checked");
	    	}
		  	
			  	return; 
	  
	  }
	  private static int[] replace(int[] stringMatch1) {
		  int m = stringMatch1.length;
		  for(int i=0;i<m;i++) {
			  stringMatch1[i] = (int)'-'; 
		  }
		  return stringMatch1;
	}

	public static String replace(String String2,int index) {
		  char ch = '-'; 
		  int m = String2.length(); 
		  for(int i=index;i<m;i++) {
		    	String2 = String2.substring(0, i) 
		                 + ch    + String2.substring(i + 1); 
		  }
		  return String2;		  
	  }
}
