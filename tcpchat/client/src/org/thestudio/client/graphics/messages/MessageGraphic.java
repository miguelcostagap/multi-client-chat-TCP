package org.thestudio.client.graphics.messages;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class MessageGraphic {
    protected Rectangle centerDesign;
    protected Ellipse leftDesign;
    protected Ellipse rightDesign;
    protected Text text;
    protected String stringOnMessageBox;

    public MessageGraphic(String message){
        stringOnMessageBox = message;
        init();
    }

    public void init() {
    }

    public void moveMessageBoxUp(){
        centerDesign.translate(0,-100);
        leftDesign.translate(0,-100);
        rightDesign.translate(0,-100);
        text.translate(0,-100);
    }
    public void moveMessageBoxDown(){
        centerDesign.translate(0,100);
        leftDesign.translate(0,100);
        rightDesign.translate(0,100);
        text.translate(0,100);

    }


}
