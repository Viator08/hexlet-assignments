package exercise;

// BEGIN
public record Cottage(double area, int floorCount) implements Home {

    @Override
    public int compareTo(Flat flat) {
        double dif = this.area() - flat.area();
        if (dif > 0) {
            return 1;
        } else if (dif < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s этажный коттедж площадью %s метров", this.floorCount(), this.area());
    }
}
// END
