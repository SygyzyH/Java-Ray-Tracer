package gui;

import mathematics.Functions;
import mathematics.Vec3;
import renderer.Camera;
import renderer.Light;
import shapes.*;

import java.awt.*;

public class View {

    public static Scene scene;
    public static Camera camera;
    public static Sphere sky;

    public static long sensitivity = (long) 4e17;

    public View() {

        Renderer.RENDERING_MODE = Renderer.IMAGE_MODE;

        camera = new Camera(Functions.asRadians(25), (double) Driver.WIDTH / Driver.HEIGHT,
                new Vec3(0, 1, 0), new Vec3(0, 0, 0), new Vec3(1, 1, 1));

        VisualProperty testImage = null, testImage2 = null;

        // default values for the sky sphere
        sky = new Sphere(new Vec3(0, 0, 0), 1, new VisualProperty(Driver.loadImage("res/sky.jpg")));
        testImage = new VisualProperty(new renderer.Color(1), Driver.loadImage("res/sky.png"), 0.15, 0.2, 0.1);

        scene = new Scene(.4, 3, 2, camera, sky,
                new Plane(new Vec3(0, -.5, 0), new Vec3(0, 1, 0), new VisualProperty(new renderer.Color(.578, .747, .802), .2, .3, .1)),
                new Plane(new Vec3(0, -.5, 0), new Vec3(0, 1, 0), new VisualProperty(new renderer.Color(.578, .747, .802), .5, .3, .1)),
                new Plane(new Vec3(0, 9.5, 0), new Vec3(0, 1, 0),new VisualProperty(new renderer.Color(0.313,0.552,0.941), .5, .3, .1)),
                new Plane(new Vec3(5, 0, 0), new Vec3(-1, 0, 0), new VisualProperty(new renderer.Color(0.686, 0.941, 0.313), .5, .3, .1)),
                new Plane(new Vec3(-5, 0, 0), new Vec3(-1, 0, 0), new VisualProperty(new renderer.Color(0.823, 0.313, 0.941), .5, .3, .1)),
                new Plane(new Vec3(0, 0, 5), new Vec3(0, 0, -1), new VisualProperty(new renderer.Color(0.313, 0.886, 0.941), .5, .3, .1)),
                new Plane(new Vec3(0, 0, -5), new Vec3(0, 0, -1), new VisualProperty(new renderer.Color(1, 1, 0), .3, .5, .1)),
                new Sphere(new Vec3(-2, 1, 2), 1, new VisualProperty(new renderer.Color(0.941, .529, 0.313), .6, .5, .1)),
                new Sphere(new Vec3(-3, 0, 1), 1, new VisualProperty(new renderer.Color(.5, 1, .9), .3, .2, .1))
        );

        scene.addLights(new Light(new Vec3(1, 2, 2), new renderer.Color(1), 1));
    }

    public static void render(Graphics g) {
        Renderer.render(g, scene);
    }

}
