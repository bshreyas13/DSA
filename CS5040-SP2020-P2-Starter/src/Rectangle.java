public class Rectangle {
	
	String name;
	int x,y,width,height;
	public Rectangle (String name, int x,int y, int w,int h) {
		if (name != null) {
		this.name = name;
		}
		else {
			System.out.println("Name is required to insert a new Rectangle");
		}
		
		if (x>-1 && x<=1023 && y>-1 && y<=1023) {
			this.x = x;
			this.y = y;
		}
		else {
			System.out.println("Out of bounds (1024x1024) error ");
		}
	}
}
