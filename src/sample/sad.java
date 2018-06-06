//import com.threed.jpct.*;
//import gram.engine.input.InputMap;
//import java.awt.Color;
//import java.awt.event.KeyEvent;
//import javax.swing.JFrame;
//import java.math.*;
//
//
///**
// *
// * @author Ruslan Nevedomy
// * jpct.ucoz.ru
// */
//public class sad {
//
//    private World world;
//    private FrameBuffer buffer;
//    private Object3D model;
//    private JFrame frame;
//    private float AFrame=0;
//    private int ASequence=1;
//    private InputMap inputMap;
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws InterruptedException {
//        new sad().loop();
//    }
//
//    public Main()
//    {
//        //Задаем название окна
//        Config.glWindowName = "Анимация и ввод";
//        //настраиваем Jpct
//        buffer = new FrameBuffer(800, 600, FrameBuffer.SAMPLINGMODE_NORMAL);
//        buffer.disableRenderer(IRenderer.RENDERER_SOFTWARE);
//        buffer.enableRenderer(IRenderer.RENDERER_OPENGL);
//        //создаем мир
//        world = new World();
//        world.setAmbientLight(150, 150, 150);
//        //загружаем ресурсы
//        LoadResource();
//
//    }
//    private Object3D loadModelfrom3DS(String filename, float scale) {
//        // Loader.setVertexOptimization(false);
//        Object3D[] file = Loader.load3DS(filename, scale);
//        Object3D ret = new Object3D(0);
//        Object3D temp = null;
//        for (int i = 0; i < file.length; i++) {
//            temp = file[i];
//            temp.setCenter(SimpleVector.ORIGIN);
//            temp.rotateX((float)( -.5*Math.PI));
//            temp.rotateMesh();
//            temp.setRotationMatrix(new Matrix());
//            ret = Object3D.mergeObjects(ret, temp);
//            ret.build();
//        }
//        return ret;
//    }
//
//    private void LoadResource() {
//
//        TextureManager.getInstance().addTexture("skin.jpg", new Texture(".\\res\\skin.jpg"));
//        //загрузка модели
//        model = loadModelfrom3DS(".\\res\\testanim2_01.3ds", 1.0f);
//        //Загрузка анимации
//        Animation animation = new Animation(3);
//        //Меняем метод расчета интерполяции вершин
//        // animation.setInterpolationMethod(Animation.BICUBIC);
//        //анимация стояния
//        animation.createSubSequence("стояние");
//        animation.addKeyFrame(model.getMesh());
//        //анимация движения
//        animation.createSubSequence("движение");
//        animation.addKeyFrame(loadModelfrom3DS(".\\res\\testanim2_1.3ds", 1.0f).getMesh());
//        animation.addKeyFrame(loadModelfrom3DS(".\\res\\testanim2_2.3ds", 1.0f).getMesh());
//        //добавлении анимации к объекту
//        model.setAnimationSequence(animation);
//
//        model.build();
//        //добавляем объект в мир
//        world.addObject(model);
//        //настраиваем камеру
//        world.getCamera().setPosition(0, 0, -20);
//        world.getCamera().lookAt(model.getTransformedCenter());
//        world.addLight(new SimpleVector(0,0,-25), Color.yellow);
//        //поворачиваем объект на 90 градусов чтобы он был виден с боку
//        model.rotateY(3.14f/2);
//    }
//    public void loop() throws InterruptedException
//    {
//        final long delay = 50;
//        long previous = System.currentTimeMillis();
//        inputMap = new  InputMap(buffer);
//
//        while (!org.lwjgl.opengl.Display.isCloseRequested()) {
//            final long current = System.currentTimeMillis();
//            if( current - previous > delay )
//            {
//                doInput();
//                doAnimation();
//            }
//            buffer.clear(java.awt.Color.BLUE);
//            world.renderScene(buffer);
//            world.draw(buffer);
//            buffer.update();
//            buffer.displayGLOnly();
//        }
//    }
//
//    private void doAnimation() {
//        AFrame+=0.001f;
//        if (AFrame>1) {AFrame=0;}
//        model.animate(AFrame, ASequence);
//    }
//    private void doInput()
//    {
//        //Чтание состояния клавиш
//        inputMap.evaluteInput();
//
//        if (inputMap.space_key)
//        {
//            //if ((inputMap.lastEvent == KeyEvent.VK_SPACE)&&)
//            if (ASequence==1)
//            {ASequence =2;AFrame=0;}
//            else
//            {ASequence =1;}
//            //Событие отработало возвращаем его в предыдущее состояниеж
//            inputMap.space_key = false;
//        }
//    }
//
//}
