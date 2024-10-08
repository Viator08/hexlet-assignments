package exercise;

// BEGIN
class Segment {
    private Point start;
    private Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getBeginPoint() {
        return start;
    }


    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint() {
        var start = getBeginPoint();
        var end = getEndPoint();
        var x = (int)(start.getX() + end.getX()) / 2;
        var y = (int)(start.getY() + end.getY()) / 2;
        return new Point(x,y);
    }
}
// END
