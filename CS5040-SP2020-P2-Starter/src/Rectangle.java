/**
 * Rectangle class template
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
