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
		  
		String path=  JOptionPane.showInputDialog("Enter the directory path for text file: ");
	    //String String1 = readFile("/home/ascoo/Desktop/workspace/JavaWorkSpace/AlgoProject/src/resource/String1.txt"); 
	    String String1 = readFile(path); 
	    System.out.println(String1);
	          
	    String String2;
		String2="jumpy foxer";
	    String2 = JOptionPane.showInputDialog("Enter the second string: ");
		System.out.println(String2);
	    
		String k;
		k = JOptionPane.showInputDialog("Enter number of top highest score: ");
		//System.out.println(k);
		int k1=Integer.parseInt(k);
		//int k1=10;
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
		  	int dp[][] = new int[n + m + 1][n + m + 1]; 
		  	for (int[] String11 : dp) 
		  	Arrays.fill(String11, 0); 
		  	for (i = 0; i <= (n + m); i++) 
		  	{ 
		  		dp[i][0] =0 ; 
		  		dp[0][i] =0; 
		  	} 
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

		  	for(i=0;i<m+1;i++){
		  		for(j=0;j<n+1;j++){	  			
		  			System.out.print("\t"+dp[i][j]);
		  		}
		  		System.out.print("\n");
		  	}
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
		  		//System.out.println(array[k]+"afa"+position[k]+"ewjrewhjrwek"+k);
		  		k++;
		  	}
		  	int[] array1 = new int[k];
		  	int[] array2 = new int[k];
		  	
		  	for (int m1=0;m1<k;m1++) {
		  		array1[m1]=array[m1];
		  		//System.out.println(array1[m1]);
		  	}
		  	
		  	int jreverse = k; 
	        for (int ireverse = 0; ireverse < k; ireverse++) { 
	        	//System.out.println("AKash "+array2[jreverse-1]+"ireverse= "+ireverse+"   "+array1[ireverse]+" ireverse="+jreverse+" k ="+k);
	        	if(array1[ireverse]<=reward) {
	        		array2[jreverse - 1] = 0;
	        	}else {
	        		array2[jreverse - 1] = array1[ireverse]; 
	        	}
	        	//System.out.println("AKash 1111   "+array2[jreverse]+"ireverse= "+ireverse+"   "+array1[ireverse]+" ireverse="+jreverse+" k ="+k);
	        	position1[jreverse - 1] = position[ireverse]; 
	        	//System.out.println(array2[jreverse-1]);
	        	//System.out.println(array1[ireverse]);
	        	jreverse = jreverse - 1; 
	        }
	        k=array2.length-1;  

//	        for(int akash=0;akash<m+1;akash++) {
//	        	System.out.println(array2[akash]+"\t"+position1[akash]+"\t"+array1[akash]+"\t"+position[akash]+"\t"+n);
//	        }
	        int stringMatch1[] = new int[n + 1]; 
	    	int stringMatch2[] = new int[n + 1]; 
	    	int lengthString1=m;
	    	//int iPosition = lengthString1; 
	    	//int jPosition = n; 
	    	int newParameter = n;
    		//jPosition=position[k];
    		//System.out.println("Akash111="+jPosition+"\t"+position1[k]);
	    	
	    	//int userInput=5;
	    	try {
	    	for(int hit=1; hit<=userInput;hit++) {
	    		int iPosition = lengthString1; 
		    	int jPosition = n; 
		    	//System.out.println("Akash111="+jPosition+"\t"+position1[k]);
	  			//if(position1[k]!=0)
		  			//jPosition=position1[k];
	  		
		    	//System.out.println("Akash="+jPosition+"\t"+position1[k]);
	  		
	    		i=lengthString1;
	    		//System.out.println("I value =   "+i);
	    		stringMatch1=replace(stringMatch1);
	    		stringMatch1=replace(stringMatch1);
				/*
				 * for (int i1 = 2; i1 <= m; i1++) {
				 * System.out.print("duplicate string		"+(int)stringMatch1[i1]);
				 * System.out.print("Duplicte string		"+(char)stringMatch1[i1]); }
				 */
	    		if(newParameter<n) {
	    			n=newParameter;
	    			String1=replace(String1,n);
	    			//System.out.println("Akash New String    = "+String1+n);
	    			
	    		}
	    		//jPosition=position[k];
	    		//System.out.println("Akash111="+jPosition+"\t"+position1[k]);
			  	
	    		while(i!=0) {
		    		//j=position1[k];
			  		j=n;
			  		//System.out.println("Akash111  before if="+jPosition+"\t"+position1[k]);
				  	
			  			if(array2[k]!=0 && position1[k]!=0) {
			  				jPosition=position1[k];
			  			//	System.out.println("Akash111 after if ="+jPosition+"\t"+position1[k]);
						  	
		    					//System.out.println("ARRAY==DP"+array2[k]+"---k="+k+"--------j="+j+"---------dp="+dp[i][j]+"--------i="+i);
		    					while(j!=0) {
	
		    						if(array2[k]==dp[i][j]) {
				    					//System.out.println(array2[k]+"---k="+k+"--------j="+j+"---------dp="+dp[i][j]+"--------i="+i);
				    			   		while ( !(i == 0 || j == 0)) 
			    					  	{ 
			    					  		
			    					  		//System.out.println("while     i=   "+i+"    j=   "+j);
			    					  		if (String1.charAt(i - 1) == String2.charAt(j - 1)) 
			    					  		{ 
			    					  			stringMatch1[jPosition--] = (int)String1.charAt(i - 1); 
			    					  			//stringMatch2[jPosition--] = (int)String2.charAt(j - 1);
			    					  			/*
												 * System.out.println( "stringMatch1[iPosition] "+stringMatch1[iPosition] +
												 * "	iPosition=	"+iPosition +"String1.charAt(i="+i+")	"+ String1.charAt(i
												 * - 1) + "	stringMatch2[jPosition]	"+stringMatch2[jPosition] +
												 * "	jPosition=	"+jPosition
												 * +"	String2.charAt(j="+j+")	"+String2.charAt(j-1) );
												 */
			    					  			i--; j--; 
			
			    					  		} 
			    					  		else if (dp[i - 1][j - 1] - reward == dp[i][j]) 
			    					  		{ 
			    					  			//stringMatch1[iPosition--] = (int)String1.charAt(i - 1); 
			    					  			stringMatch1[jPosition--] = (int)'-'; 
			    					  			//stringMatch2[jPosition--] = (int)String2.charAt(j - 1); 
			    					  			//System.out.println("2stringMatch1[iPosition] "+stringMatch1[iPosition]+"String1.charAt(i="+i+")"+String1.charAt(i)+"stringMatch2[jPosition]"+stringMatch2[jPosition] +"String2.charAt(j="+j+")"+String2.charAt(j) );
			    					  			i--; j--; 
			    					  		} 
			    					  		else if (dp[i - 1][j] + penalitString2 == dp[i][j]) 
			    					  		{ 
			    					  			//stringMatch1[iPosition--] = (int)String1.charAt(i - 1); 
			    					  			stringMatch1[jPosition--] = (int)'-'; 
			    					  			//System.out.println("AKASH Help me"+String1.charAt(i - 1));
			    					  			//stringMatch2[jPosition--] = (int)'-'; 
			    					  			//System.out.println("3stringMatch1[iPosition] "+stringMatch1[iPosition]+"String1.charAt(i="+i+")"+String1.charAt(i)+"stringMatch2[jPosition]"+stringMatch2[jPosition] +"String2.charAt(j="+j+")"+String2.charAt(j) );
			    					  			i--; 
			    					  		} 
			    					  		else if (dp[i][j - 1] + penalitString2 == dp[i][j]) 
			    					  		{ 
			    					  			//System.out.println("AKASH Help me1"+String1.charAt(i - 1));
			    					  			stringMatch1[jPosition--] = (int)'-'; 
			    					  			//stringMatch2[jPosition--] = (int)String2.charAt(j - 1); 
			    					  			//System.out.println("4stringMatch1[iPosition] "+stringMatch1[iPosition]+"String1.charAt(i="+i+")"+String1.charAt(i-1)+"stringMatch2[jPosition]"+stringMatch2[jPosition] +"String2.charAt(j="+j+")"+String2.charAt(j-1) );
			    					  			j--; 
			    					  		} 
			    					  		else if (dp[i-1][j - 1]  == 0) 
			    					  		{ 
			    		    					//need to add some variable to store data/output to an array
			    					  			//System.out.println("inner while");
			    					  			break;
			    					  		} 
			    					  	} 
			    						 /*if(dp[i][j]==0) {
						    					System.out.println("j while while");
						    					System.out.println("k= "+k+" dp[i][j]="+dp[i][j]+" i= "+i+" j= "+j);
						    					//k--;
						    					//i--;
						    					System.out.println("k= "+k+" dp[i][j]="+dp[i][j]+" i= "+i+" j= "+j);
						    					//need to add some variable to store data/output to an array
						    					break;
						    			}*/
						} /*
							 * else if(dp[i][j]==0) { //System.out.println("2nd while"); //need to add some
							 * variable to store data/output to an array break; }
							 */else {
		    			    				j--;
		    			    			}	
		    					}    					
		    			}else {
		    				i--;
		    				k--;
		    				//System.out.println("k= "+k+" dp[i][j]="+dp[i][j]+" i= "+i+" j= "+j);
		    				lengthString1--;
		    				//newParameter--;
		    			}
		    		try{
		    			if(dp[i][j]==0) {
			    			//System.out.println("Need to set the variable for kth possible term");
			    			k--;
	    					i--;
			    			newParameter--;
			    			break;
			    		}
		    		}catch(EventException e) {
		    			System.out.println();
		    		}
		    		
		    		//System.out.println("k="+k);
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
			} /*
				 * while (jPosition > 0) { if (j > 0) { stringMatch2[jPosition--] =
				 * (int)String2.charAt(--j); } else { stringMatch2[jPosition--] = (int)'-'; } }
				 */
			
			int id = 1; 
				/*
				 * for (i = l; i >= 1; i--) { if ((char)stringMatch2[i] == '-' &&
				 * (char)stringMatch1[i] == '-') {
				 * 
				 * id = i + 1; break; } }
				 */	 
				//System.out.println("hit"+lengthString1+": score"+array2[lengthString1+1]);
		    	System.out.println("hit"+hit+": score"+array2[lengthString1+1]);
		    	for (i = id; i <= n; i++) 
		    	{ 
		    		System.out.print((char)stringMatch1[i]); 
		    	} 
		    	System.out.print("\n"); 
		    	/*for (i = id; i <= n; i++) 
		    	{ 
		    	*/	System.out.print(String2); 
		    	//} 
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
		  //String ch = "-";
		  for(int i=0;i<m;i++) {
			  stringMatch1[i] = (int)'-'; 
			  //System.out.print("-");
			  //stringMatch1[i] = 450; 
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
