package org.thestudio.client.graphics;

import org.thestudio.client.graphics.keyboard.MyKeyboard;

public class InitializeGeneralGraphics {
    private Background background;
    private InputBox inputBox;

    public InitializeGeneralGraphics(){
        background = new Background();
        inputBox = new InputBox();
    }

    public void start(){
        Thread thread = new Thread(inputBox);
        thread.start();
        background.init();
        Thread thread1 = new Thread(new MyKeyboard(inputBox));
        thread1.start();
    }
}
