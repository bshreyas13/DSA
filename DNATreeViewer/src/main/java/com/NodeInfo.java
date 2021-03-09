package com;
import java.util.ArrayList;

public class NodeInfo {
	public String name, title;
	public ArrayList<NodeInfo> children;

	public NodeInfo() {
		children = new ArrayList();
	}
}
