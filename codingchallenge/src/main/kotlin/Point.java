public class Point {
    int x;
    int y;
    char tag;

    public Point(int x, int y, char tag) {
        this.x = x;
        this.y = y;
        this.tag = tag;
    }

    public char getTag() {
        return tag;
    }

    public double distance() {
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
}