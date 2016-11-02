package networkcontents;

import java.util.Stack;

public class PathInfoFactory {
	private static PathInfoFactory instance;
	private PathInfoFactory(){}
	
	public static PathInfoFactory getInstance(){
		if (instance==null){instance=new PathInfoFactory();}
		return instance;
	}
	
	public PathInfo createPathInfo(int lengthIn, Stack<String> pathIn)
	{
		return new PathInfo(lengthIn,pathIn);
	}

}
