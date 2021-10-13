package shapes;

import mathematics.Vec3;
import renderer.Color;
import renderer.Intersection;
import renderer.Ray;

public class PolygonMesh extends Shape {


    public PolygonMesh(Vec3 pos, VisualProperty visualProperty) {
        super(pos, visualProperty);
    }

    public Triangle[] triangulate() {
        return null;
    }

    @Override
    public double getIntersection(Ray r, Intersection intersection) {
        return 0;
    }

    @Override
    public boolean intersects(Ray r, Intersection intersection) {
        return false;
    }

    @Override
    public Vec3 getNormal(Vec3 pos) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public Color getTextureColor(Vec3 p) {
        return null;
    }
}
