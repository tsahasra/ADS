package hashtagcounter;

public class fibNode {

	fibNode child;
	fibNode left;
	fibNode right;
	fibNode parent;
	boolean childcut;
	int degree;
	int key;
	String hashname;
	
	public fibNode(int k , String hashtag )
	{
		childcut = false;
		key = k;
		hashname = hashtag;
		degree = 0;
		child = null;
		parent = null;
		right = null;
		left = null;
	}
	
	


}



