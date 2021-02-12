/**
 * Rectangle object class
 */
class Rectangle {
    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }
    public int getX() {
    	return x;
    }
    public int getY() {
    	return y;
    }
    public int compare(Rectangle r1 , Rectangle r2 ) {
    	String r1Name = r1.getName();
    	String r2Name = r1.getName();
    	int out ;
    	out = r1Name.compareTo(r2Name);
    	return out; 
    }

    private String name;
    private int length;
    private int height;
    private int x;
    private int y;

    Rectangle(String name, int x , int y, int length, int height){
        this.name = name;
        this.x = x;
        this.y = y ;
        this.length = length;
        this.height = height;
    }

    @Override
    public String toString() {
        return "("+ getName() + ","+ getX()+","+getY()+","+ getLength() + ","+ getHeight()+")";
    }
}