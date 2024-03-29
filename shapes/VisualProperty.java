package shapes;

import mathematics.Vec3;
import renderer.Color;

import java.awt.image.BufferedImage;

public class VisualProperty {

    public Color color;
    public double reflectivity, emission, roughness;
    public boolean isReflective, isTextured;
    public BufferedImage texture;

    public VisualProperty(Color color) {
        this.color = color;
        this.reflectivity = 0;
        this.emission = 0;
        this.roughness = 0;
        this.isReflective = false;
        this.isTextured = false;
    }

    public VisualProperty(Color color, double reflectivity, double emission, double roughness) {
        this.color = color;
        this.reflectivity = reflectivity;
        this.emission = emission;
        this.roughness = roughness;
        this.isReflective = true;
        this.isTextured = false;
    }

    public VisualProperty(BufferedImage texture) {
        this.color = new Color(0);
        this.reflectivity = 0;
        this.emission = 0;
        this.texture = texture;
        this.isReflective = false;
        this.isTextured = true;
    }

    public VisualProperty(Color color, BufferedImage texture, double reflectivity, double emission, double roughness) {
        this.color = color;
        this.texture = texture;
        this.reflectivity = reflectivity;
        this.emission = emission;
        this.roughness = roughness;
        this.isReflective = true;
        this.isTextured = true;
    }

    public Color getTextureColorAt(double u, double v) {
        return Color.convert(new java.awt.Color(texture.getRGB((int) u, (int) v), true));
    }

    public VisualProperty clone() {
        return new VisualProperty(this.color, this.texture, this.reflectivity, this.emission, this.roughness);
    }

}
