package sample;

import com.threed.jpct.*;
import com.threed.jpct.util.KeyMapper;
import com.threed.jpct.util.KeyState;


import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class HelloWorldSoftware {
    private World world;
    private FrameBuffer buffer;
    private Object3D airplane;
    private Object3D radar;
    private Object3D plane;
    private Object3D shpere;
    private Object3D cube;
    private JFrame frame = new JFrame("3d models");

    public static void main(String[] args) throws Exception {
        HelloWorldSoftware s = new HelloWorldSoftware();
        s.loadScene();
        s.loop();
    }

    public HelloWorldSoftware() {

    }

    public void loadScene() {
        this.buffer = new FrameBuffer(800, 800, FrameBuffer.SAMPLINGMODE_NORMAL);
        buffer.disableRenderer(IRenderer.RENDERER_OPENGL);
        buffer.enableRenderer(IRenderer.RENDERER_SOFTWARE);

        Config.maxPolysVisible = 100000;
        loadTextureAirplane();

        shpere = Primitives.getSphere(1);
        shpere.setAdditionalColor(Color.BLACK);

        cube = Primitives.getCube(2);
        cube.translate(0, -2, 0);
        cube.setAdditionalColor(Color.red);
        cube.setTexture("ql8.jpg");

        plane = Primitives.getPlane(10, 10);
        plane.rotateX((float) Math.PI / 2f);
        plane.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);
        plane.setTexture("rocks.jpg");

        airplane = loadModelfrom3DS("./models/FW190/FW190.3DS", 0.05f);
        airplane.translate(20, -15, 0);

        radar = loadModelfrom3DS("./models/myAntenna.3DS", 0.03f);
        radar.translate(0, -4, 0);
        radar.setTexture("ql8.jpg");

        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(1);

        world = new World();
        world.setAmbientLight(0, 255, 0);

        world.addObject(cube);
        world.addObject(radar);
        world.addObject(plane);
        world.addObject(airplane);
        world.addObject(shpere);
        world.buildAllObjects();
        world.getCamera().setPosition(0F, -50.0F, -100.0F);
        world.getCamera().lookAt(shpere.getTransformedCenter());
        //  world.getCamera().lookAt(this.airplane.getTransformedCenter());
    }

    public void loop() throws Exception {
        int i=0;
        while (this.frame.isShowing()) {
            airplane.rotateY(0.05F);
            airplane.rotateX(0.05F);
            buffer.clear(Color.BLACK);
            world.renderScene(this.buffer);
            world.draw(this.buffer);
            buffer.update();
            buffer.display(this.frame.getGraphics());
            Thread.sleep(1L);
            i++;
        }

        //  buffer.disableRenderer(IRenderer.RENDERER_SOFTWARE);
        buffer.dispose();
        frame.dispose();
        System.exit(0);
    }

    //Подгружаем текстуры самолета
    private void loadTextureAirplane() {
        TextureManager textManager = TextureManager.getInstance();
        textManager.addTexture("FW190 0.png", new Texture("./models/FW190/FW190 0.png"));
        textManager.addTexture("FW190 1.png", new Texture("./models/FW190/FW190 1.png"));
        textManager.addTexture("FW190 2.png", new Texture("./models/FW190/FW190 2.png"));
        textManager.addTexture("FW190 3.png", new Texture("./models/FW190/FW190 3.png"));
        textManager.addTexture("FW190 4.png", new Texture("./models/FW190/FW190 4.png"));
        textManager.addTexture("FW190 5.png", new Texture("./models/FW190/FW190 5.png"));
        textManager.addTexture("FW190 6.png", new Texture("./models/FW190/FW190 6.png"));
        textManager.addTexture("FW190 7.png", new Texture("./models/FW190/FW190 7.png"));
        textManager.addTexture("FW190 8.png", new Texture("./models/FW190/FW190 8.png"));
        textManager.addTexture("rocks.jpg", new Texture("./models/res/rocks.jpg"));
        textManager.addTexture("ql8.jpg", new Texture("./models/res/ql8.jpg"));
    }

    //ПРОГРУЗКА МОДЕЛЬКИ
    private Object3D loadModelfrom3DS(String filename, float scale) {
        Object3D[] file = Loader.load3DS(filename, scale);
        Object3D ret = new Object3D(0);
        Object3D temp = null;
        for (int i = 0; i < file.length; i++) {
            temp = file[i];
            temp.setCenter(SimpleVector.ORIGIN);
            temp.rotateX((float) (-.5 * Math.PI));
            temp.rotateMesh();
            temp.setRotationMatrix(new Matrix());
            ret = Object3D.mergeObjects(ret, temp);
            ret.build();
        }
        return ret;
    }


}



