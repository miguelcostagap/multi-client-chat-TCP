package org.thestudio.client.graphics.messages;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class OwnMessage extends MessageGraphic {

    public OwnMessage(String message) {
        super(message);
    }

    @Override
    public void init() {
        centerDesign = new Rectangle(570-(10*stringOnMessageBox.length()), 850, 10 * stringOnMessageBox.length(), 50);
        leftDesign = new Ellipse(550-(10*stringOnMessageBox.length()), 850, 50, 50);
        rightDesign = new Ellipse(590-(10*stringOnMessageBox.length()), 850, 50, 50);

        centerDesign.setColor(Color.CYAN);
        centerDesign.fill();
        leftDesign.setColor(Color.CYAN);
        leftDesign.fill();
        rightDesign.setColor(Color.CYAN);
        rightDesign.fill();

        text = new Text(590-(10*stringOnMessageBox.length()), 865, stringOnMessageBox);
        text.setColor(Color.WHITE);
        text.draw();
        text.grow(25, 10);

    }

}
