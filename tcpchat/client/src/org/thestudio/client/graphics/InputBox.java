package org.thestudio.client.graphics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class InputBox implements Runnable {
    private Rectangle centerDesign;
    private Ellipse leftDesign;
    private Ellipse rightDesign;
    private Rectangle cursor;
    private Text text;
    private String stringOnInputBox = "";

    public InputBox() {
        centerDesign = new Rectangle(45, 930, 530, 50);
        leftDesign = new Ellipse(20, 930, 50, 50);
        rightDesign = new Ellipse(550, 930, 50, 50);
        cursor = new Rectangle(570, 940, 3, 30);
    }

    public void init() {
        centerDesign.setColor(Color.GRAY);
        centerDesign.fill();
        leftDesign.setColor(Color.GRAY);
        leftDesign.fill();
        rightDesign.setColor(Color.GRAY);
        rightDesign.fill();
        cursor.setColor(Color.DARK_GRAY);
        cursor.fill();
        text = new Text(550, 950, "");
        text.setColor(Color.WHITE);
        text.draw();
        text.grow(25, 10);
    }

    public void writeInRealTime() {
        text.setText(stringOnInputBox);
    }

    public void characterEntered() {
        text.translate(-5, 0);
    }

    public void characterRemoved() {
        text.translate(5, 0);
    }

    public void blinkCursor() {
        try {
            Thread.sleep(500);
            cursor.delete();
            Thread.sleep(500);
            cursor.setColor(Color.DARK_GRAY);
            cursor.fill();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getStringOnInputBox() {
        return stringOnInputBox;
    }

    public void setStringOnInputBox(String stringOnInputBox) {
        this.stringOnInputBox = stringOnInputBox;
    }

    @Override
    public void run() {
        init();
        while (true) {
            blinkCursor();
            writeInRealTime();
        }
    }
}
