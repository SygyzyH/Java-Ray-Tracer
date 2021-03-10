package mathematics;

public class Functions {

    public static boolean inRange(double value, double min, double max) {
        if (value > min && value < max)
            return true;
        return false;
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double asRadians(double degree) {
        return degree * Math.PI / 180;
    }

    public static double fastInverseSqrt(double x) {
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);

        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x = Double.longBitsToDouble(i);
        x *= (1.5d - xhalf * x * x);
        return x;
    }

}
