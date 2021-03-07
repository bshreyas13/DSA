
public class InternalNode implements Node {
	public static final int MIN_NODES = 1;
	public int level;

	private Node A;
	private Node C;
	private Node G;
	private Node T;
	private Node $;

	public InternalNode(LeafNode existingSequenceNode, Sequence newSequence) {

		A = new EmptyNode();
		C = new EmptyNode();
		G = new EmptyNode();
		T = new EmptyNode();
		$ = new EmptyNode();

		final Sequence existingSequence = existingSequenceNode.getSequence();

		Sequence prefix, suffix;
		if (existingSequence.length() < newSequence.length()) {
			prefix = newSequence;
			suffix = existingSequence;
		} else {
			prefix = existingSequence;
			suffix = newSequence;
		}

		insert(prefix);

		if (suffix.isPrefixOf(prefix)) {
			insertPrefix(suffix);
		} else {
			insert(suffix);
		}
	}

	protected Node getChild(char sequenceCharacter) {
		switch (sequenceCharacter) {
		case 'A':
			return A;
		case 'C':
			return C;
		case 'G':
			return G;
		case 'T':
			return T;
		}
		return null;
	}

	@Override
	public void print() {
		Print.node(this);
		A.print();
		C.print();
		G.print();
		T.print();
		$.print();
	}

	@Override
	public Node insert(Sequence sequence) {
		if (sequence.hasNext()) {
			final char sequenceChar = sequence.next();
			Node child = getChild(sequenceChar);
			if (($ instanceof LeafNode) && ((LeafNode) $).getSequence().length() > sequence.length()
					&& sequence.isPrefixOf(((LeafNode) $).getSequence())) {
				insert(swapPrefix(sequence));
			} else if (!sequence.hasNext() && (child instanceof LeafNode) && (occupiedNodes() < MIN_NODES)) {
				insertPrefix(sequence);
			} else {
				if (child != null) {
					setChild(sequenceChar, child.insert(sequence));
				}
			}
		} else {
			insertPrefix(sequence);
		}
		return this;
	}

	private Sequence swapPrefix(Sequence newSequence) {
		Sequence old = ((LeafNode) $).getSequence();
		((LeafNode) $).setSequence(newSequence);
		return old;
	}

	public void insertPrefix(Sequence sequence) {
		if ($ instanceof EmptyNode) {
			$ = $.insert(sequence);
		} else if (sequence.equals(((LeafNode) $).getSequence())) {
			DNAtree.sequenceAlreadyExists(sequence);
		} else {
			insert(swapPrefix(sequence));
		}
	}

	@Override
	public Node remove(Sequence sequence) {
		if (sequence.hasNext()) {
			final char sequenceChar = sequence.next();
			Node child = getChild(sequenceChar);
			setChild(sequenceChar, child.remove(sequence));
		} else {
			$ = $.remove(sequence);
		}

		Node collapsible = null;
		for (Node child : getChildren()) {
			if (child instanceof InternalNode) {
				return this;
			} else if (child instanceof LeafNode) {
				if (collapsible == null) {
					collapsible = child;
				} else {
					return this;
				}
			}
		}

		((LeafNode) collapsible).getSequence().prev();
		return collapsible;
	}

	@Override
	public void search(SearchSequence searchData) {
		searchData.incrementNodesVisited();
		final Sequence searchSequence = searchData.getSearchSequence();
		if (searchSequence.hasNext()) {
			final char currentSearchChar = searchSequence.next();
			Node child = getChild(currentSearchChar);
			if (child != null) {
				child.search(searchData);
			}
		} else if (searchData.matchExact()) {
			$.search(searchData);
		} else {
			A.search(searchData);
			C.search(searchData);
			G.search(searchData);
			T.search(searchData);
			$.search(searchData);
		}
	}

	public boolean setChild(char sequenceChar, Node child) {
		switch (sequenceChar) {
		case 'A':
			A = child;
			return true;
		case 'C':
			C = child;
			return true;
		case 'G':
			G = child;
			return true;
		case 'T':
			T = child;
			return true;
		}
		return false;
	}

	private int occupiedNodes() {
		int occupiedNodes = 0;
		if (A instanceof LeafNode) {
			occupiedNodes++;
		}
		if (C instanceof LeafNode) {
			occupiedNodes++;
		}
		if (G instanceof LeafNode) {
			occupiedNodes++;
		}
		if (T instanceof LeafNode) {
			occupiedNodes++;
		}
		return occupiedNodes;
	}

	private Node[] getChildren() {
		return new Node[] { A, C, G, T, $ };
	}

	public Node getA() {
		return A;
	}

	public void setA(Node a) {
		A = a;
	}

	public Node getC() {
		return C;
	}

	public void setC(Node c) {
		C = c;
	}

	public Node getG() {
		return G;
	}

	public void setG(Node g) {
		G = g;
	}

	public Node getT() {
		return T;
	}

	public void setT(Node t) {
		T = t;
	}

	public Node get$() {
		return $;
	}

	public void set$(Node $) {
		this.$ = $;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
		A.setLevel(level + 1);
		C.setLevel(level + 1);
		G.setLevel(level + 1);
		T.setLevel(level + 1);
		$.setLevel(level + 1);
	}

	@Override
	public int getLevel() {
		return level;
	}
}
