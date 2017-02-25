package hashtagcounter;
/*import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;*/

import java.util.Hashtable;

public class fibHeap {
	
	public fibNode maxptr;
	
	public fibHeap()
	{
		maxptr = null;
	}
	
	public void insert(fibNode fn)
	{
		if(this.maxptr == null)
		{
			maxptr = fn;
			maxptr.right = maxptr;
			maxptr.left = maxptr;
			
		}
		else
		{
		//fibNode temp = maxptr.right;
		
		fn.left = maxptr;
		fn.right = maxptr.right;
		maxptr.right.left = fn;		
		maxptr.right = fn;
		
		
		if(maxptr.key < fn.key)
		{
			maxptr = fn;
		}
		}
		
		fn.childcut = false;
		
		fn.parent = null;
		if(fn.hashname.equals("#chloroquine") || fn.hashname.equals("#chlorothiazide"))
			System.out.println("inserted "+fn.hashname+" "+fn.key);
		
	}
	
		
	public fibNode removeMax()
	{
		Hashtable <Integer,fibNode> fnt = new Hashtable<Integer, fibNode>();
		fibNode temp = maxptr, tempmaxptr = maxptr;
		int i = 0;
		
		
		//ArrayList <fibNode> fna = new ArrayList();
		
		if(maxptr == null)
			throw new RuntimeException("Empty Fibonacci Heap");
		
				
		if(maxptr != null)
		{
			temp = maxptr.right;
			maxptr.right = temp.right;
			temp.right.left = maxptr;
			while(temp != maxptr)
			{
				
				temp.right = temp;				
				temp.left = temp;
				//System.out.println("parent "+ temp.hashname);
				meld(temp,fnt);
				temp = maxptr.right;
				maxptr.right = temp.right;
				temp.right.left = maxptr;
			}
			
			//System.out.println(maxptr.hashname);
						
			if(maxptr.child != null)
			{
				maxptr.left = maxptr.child.left;
				maxptr.child.left.right = maxptr;
				maxptr.right = maxptr.child;
				maxptr.child.left = maxptr;
				maxptr.child = null;
				
				temp = maxptr.right;
				maxptr.right = temp.right;
				temp.right.left = maxptr;
				while(temp != maxptr)
				{
					
					temp.right = temp;				
					temp.left = temp;
					//System.out.println("parent "+ temp.hashname);
					meld(temp,fnt);
					temp = maxptr.right;
					maxptr.right = temp.right;
					temp.right.left = maxptr;
				}
			}
			
		}
		
		
		
		//System.out.println(fna.size());
		i = 0;
		
		/*while(!fna.isEmpty())
		{
			meld(fna.get(i),fnt);
			fna.remove(i);
			
		}*/
		
		//fna.clear();
		maxptr = null;
		
		while(!fnt.isEmpty())
		{
		if(fnt.containsKey(i))
		{
			
			insert(fnt.get(i));
			fnt.remove(i);
			i++;
			
		}
		else
		{
			i++;
		}
		}
		
		fnt.clear();
		return tempmaxptr;
		
	}
	
	
	public void meld(fibNode fn1 , Hashtable <Integer,fibNode> fnt)
	{
		if(fnt.containsKey(fn1.degree))
		{
		fibNode fn2 = fnt.get(fn1.degree);
		//System.out.println(" "+fn2.hashname);
		//System.out.println("before"+" "+fnt.size());
		fnt.remove(fn2.degree);
		//System.out.println("after"+" "+fnt.size());
		fibNode temp3;
		
		
		if(fn1.key >= fn2.key)
		{
			if(fn1.child != null)
			{
				temp3 = fn1.child;
				fn2.left = temp3;
				fn2.right = temp3.right;
				temp3.right.left = fn2;
				temp3.right = fn2;
				fn2.parent = fn1;									
			}
			else
			{
				fn1.child = fn2;
				fn2.right = fn2;
				fn2.left = fn2;
				fn2.parent = fn1;
				
			}
			
			fn1.degree++;
			
			if(fnt.containsKey(fn1.degree))
			{
				meld(fn1,fnt);
			}
			else
			{
				fnt.put(fn1.degree, fn1);
				//System.out.println(fn1.hashname);
			}
		}
		                         
		else
		{
			if(fn2.child != null)
			{
				temp3 = fn2.child;
				fn1.left = temp3;
				fn1.right = temp3.right;
				temp3.right.left = fn1;
				temp3.right = fn1;
				fn1.parent = fn2;
				
				
			}
			else
			{
				fn2.child = fn1;
				fn1.right = fn1;
				fn1.left = fn1;
				fn1.parent = fn2;
				
			}
			
			fn2.degree++;
			
			if(fnt.containsKey(fn2.degree))
			{
				
				meld(fn2,fnt);
			}
			else
			{
				fnt.put(fn2.degree, fn2);
				//System.out.println(fn2.hashname);
			}
		}
		}
	else
	{
		fnt.put(fn1.degree, fn1);
		//System.out.println(fn1.hashname);
	}

	}
	
	public void increaseKey(fibNode fn , int key)
	{
		fn.key = fn.key + key;
		
		if(fn.hashname.equals("#chloroquine") || fn.hashname.equals("#chlorothiazide"))
			System.out.println("incremented "+fn.hashname+" "+fn.key);
		
		fibNode fnp = fn.parent;
		if(fnp != null)
		{
			if(fn.key > fn.parent.key)
			{
				
				cut(fn , fnp);
				cascadingcut(fnp);
			}
		}
		else
		{
			if(fn.key > maxptr.key)
			{
				maxptr = fn;
			}
		}
	}
	
	
	public void cut(fibNode fn , fibNode fnp)
	{
		fibNode temp;
		if(fn.right == fn && fn.left == fn)
		{
			fnp.child = null;
			
			fnp.degree--;
		}
		else
		{
		temp = fn.right;
		temp.left = fn.left;
		fn.left.right = temp;
		
		
		
		//System.out.println(fnp.hashname);
		if(fnp.child == fn)
			fnp.child = temp;
		fnp.degree--;
		}
		 
		
		fn.parent = null;	
				 
		insert(fn);
				
	}
	
	
	public void cascadingcut(fibNode fnp)
	{
		fibNode fnpp = fnp.parent;
		
		if(fnpp != null)
		{
		if(fnp.childcut == false)
			fnp.childcut = true;
		else
		{
				
				//System.out.println(fnp.hashname + " " + fnp.parent.hashname);
				cut(fnp,fnpp);
				cascadingcut(fnpp);
				
		}
		}
				
		
	}
	
	
}

