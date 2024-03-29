package shapes;

import mathematics.Vec3;
import renderer.Intersection;
import renderer.Ray;
import renderer.Color;

public class Triangle extends Shape {

    private Vec3 pos2, pos3;

    public Triangle(Vec3 pos, Vec3 pos2, Vec3 pos3, VisualProperty visualProperty) {
        super(pos, visualProperty);
        this.pos2 = pos2;
        this.pos3 = pos3;
    }

    @Override
    public double getIntersection(Ray r, Intersection intersection) {
        // Möller–Trumbore intersection algorithm
        // TODO: epsilon is not properly defined, and this may be causing reflections to appear only on one side

        Vec3 edge1 = this.pos2.sub(this.pos), edge2 = this.pos3.sub(this.pos), h = r.direction.cross(edge2);
        double a = edge1.dot(h);

        if (a == 0.001)
            return Double.POSITIVE_INFINITY;

        Vec3 s = r.origin.sub(this.pos);
        double f = 1 / a, u = f * (s.dot(h));

        if (u < 0.0 || u > 1.0)
            return Double.POSITIVE_INFINITY;

        Vec3 q = s.cross(edge1);
        double v = f * r.direction.dot(q);

        if (v < 0.0 || u + v > 1.0)
            return Double.POSITIVE_INFINITY;

        double t = f * edge2.dot(q);
        if (t > 0.0001 && t < intersection.pointOfIntersection)
            return t;
        return Double.POSITIVE_INFINITY;

    }

    @Override
    public boolean intersects(Ray r, Intersection intersection) {
        // theres no point in redoing the same algorithm again. if the ray intersects at any point that isn't infinity,
        // then it must intersect somewhere. unfortunately not optimizations can be done

        return getIntersection(r, intersection) != Double.POSITIVE_INFINITY;
    }

    @Override
    public Vec3 getNormal(Vec3 pos) {
        // the normal of a triangle is the vector cross product of two edges of that triangle
        Vec3 v = this.pos2.sub(this.pos), w = this.pos3.sub(this.pos);

        return w.cross(v).get_normalized();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Triangle)
            if (this.pos == ((Triangle) o).pos && this.pos2 == ((Triangle) o).pos2 && this.pos3 == ((Triangle) o).pos3)
                return true;
        return false;
    }

    @Override
    public Color getTextureColor(Vec3 p) {
        // first, convert 3D cartesian to barycentric
        // get all mark points
        Vec3 v0 = this.pos2.sub(this.pos), v1 = this.pos3.sub(this.pos), v2 = p.sub(this.pos);
        double d00 = v0.dot(v0);
        double d01 = v0.dot(v1);
        double d11 = v1.dot(v1);
        double d20 = v2.dot(v0);
        double d21 = v2.dot(v1);

        // get denominator
        double denom = d00 * d11 - d01 * d01;

        // convert to points (u, v, w)
        double u, v, w;
        v = (d11 * d20 - d01 * d21) / denom;
        w = (d00 * d21 - d01 * d20) / denom;
        u = 1 - v - w;

        // now we can convert back to 2D cartesian
        double x, y;

        // assuming Auv(0, 0), Buv(0, 1), Cuv(1, 0)
        x = w;
        y = u;

        // convert relative point to be in the texture
        x *= this.visualProperty.texture.getWidth();
        y *= this.visualProperty.texture.getHeight();

        return this.visualProperty.getTextureColorAt(x, y);
    }
}
