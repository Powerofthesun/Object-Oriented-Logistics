package networkcontents;

import java.util.Stack;

public class PathInfo {
	private int pathLength;
	private Stack<String> path;
	public PathInfo (int lengthIn, Stack<String> pathIn)
	{
		pathLength=lengthIn;
		path=pathIn;
	}
	public int getLength()
	{
		return pathLength;
	}
	public Stack<String> getPath()
	{
		Stack<String> tempStack = new Stack<String>();
		tempStack.addAll(path);
		return tempStack;
	}
}
