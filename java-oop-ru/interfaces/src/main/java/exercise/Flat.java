package exercise;

// BEGIN
public class Flat implements Home {
    double area;
    double balconyArea;
    int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double area() {
        return area + balconyArea;
    }

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

    public int getFloor() {
        return floor;
    }

    @Override
    public String toString() {
        return String.format("Квартира площадью %s метров на %s этаже", this.area(), this.getFloor());
    }
}
// END
