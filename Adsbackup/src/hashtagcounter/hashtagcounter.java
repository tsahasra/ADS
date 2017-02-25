package hashtagcounter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class hashtagcounter {
    
	public static String getPattern(String input)
    {
		String pattern = "";
		
		if(input.startsWith("#"))
			pattern = "hashtag";
		else
		{
			if(input.startsWith("STOP"))
				pattern = "STOP";
			else
				pattern = "query";
		}
		
		/*String pattern = "";
		Pattern p;
	    Matcher m;
	    boolean b;
	    
	    
		p = Pattern.compile("#[a-z 0-9]$");
    	m = p.matcher(input);
    	b = m.matches();
    	System.out.println(b);
    	
    	if(b == true)
    	{
    		pattern = "hashtag";
    	}
    	else
    	{
    		p = Pattern.compile("^[0-9]+");
    		m = p.matcher("inputline");
    		b = m.matches();
    		if(b == true)
        	{
        		pattern =  "query";
        	}
    		else
    		{
    			p = Pattern.compile("\\STOP");
        		m = p.matcher("inputline");
        		b = m.matches();
        		if(b == true)
            	{
            		pattern =  "STOP";
            	}
    		}
    		}*/
		return pattern;
		//return pattern;
    	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub//
		try
		{
	    BufferedReader br = new BufferedReader(new FileReader(args[0]));
	    BufferedWriter bw = new BufferedWriter(new FileWriter("adsOutput.txt"));
	    
	    
	    Hashtable <String,fibNode> ht = new Hashtable<String, fibNode>();
	    
	    String inputline = "";
	    String hashtag;
	    
	    Integer key;
	    boolean flag = true;
	    fibHeap fh = new fibHeap();
	    
	    while((inputline = br.readLine()) != null && flag)
	    {
	    	
	    	switch(getPattern(inputline))
	    	{
	    	case "hashtag": 
	    					StringTokenizer str = new StringTokenizer(inputline," ");
	    					hashtag = str.nextToken();
	    					key = Integer.parseInt(str.nextToken());
	    					if(ht.containsKey(hashtag))
	    					{
	    						
	    						fh.increaseKey(ht.get(hashtag),key);
	    						//System.out.println("incremented the value of "+hashtag);
	    						
	    					}
	    					else
	    					{
	    						
	    						fibNode fn = new fibNode(key,hashtag);
	    						ht.put(hashtag,fn);
	    						fh.insert(fn);
	    						//System.out.println("inserted "+hashtag);
	    					}
	    					break;
	    	
	    	case "query":
	    					//System.out.println("hash2");
	    					key = Integer.parseInt(inputline);
	    					fibNode[] fnode = new fibNode[key];
	    					String outputline ="";
	    					for(int i=0;i<key && i<20;i++)
	    					{
	    						
	    						fnode[i] = fh.removeMax();
	    						//System.out.println(i+" "+fnode[i].hashname);
	    						if(i == (key-1))
	    						{
	    							outputline = outputline + (fnode[i].hashname).substring(1);
	    							
	    						}
	    						else	    						
	    							outputline = outputline + (fnode[i].hashname).substring(1);
	    						
	    						//System.out.println(outputline);
	    						
	    						
	    					}
	    					bw.write(outputline + "\n");
	    					for(int i=0;i<key;i++)
	    					{
	    						fh.insert(fnode[i]);
	    					}
	    					break;
	    		
	    	case "STOP":
	    					flag = false;
	    					break;
	    	
	    	default: 
	    					System.out.println("hash4");
	    					break;
	    	}
	    	
	    }
	    br.close();
	    bw.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(IOException e1)
		{
			System.out.println(e1.getMessage());
		}
		
		}
	}


