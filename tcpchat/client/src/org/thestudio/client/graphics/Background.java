package org.thestudio.client.graphics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Background {

    private Picture background;

    public Background() {
        background = new Picture(10, 10, "resources/background.png");
    }

    public void init() {
        background.draw();
    }

}
