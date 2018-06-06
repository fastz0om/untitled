package sample;

import javafx.event.ActionEvent;

public class Controller {
private HelloWorldSoftware helloWorldSoftware = new HelloWorldSoftware();


    public void tdsb(ActionEvent actionEvent) throws Exception {
        helloWorldSoftware.loadScene();
        helloWorldSoftware.loop();
    }
}
