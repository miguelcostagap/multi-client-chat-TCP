package org.thestudio.client.graphics.keyboard;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.thestudio.client.graphics.InputBox;

public class MyKeyboard implements KeyboardHandler, Runnable {
    private Keyboard keyboard;
    private InputBox inputBox;

    private String textInInputBox = "";

    private boolean enterPressed = false;

    public MyKeyboard(InputBox inputBox) {
        this.inputBox = inputBox;
    }

    public void init() {

        keyboard = new Keyboard(this);

        for (int i = 0; i < keys.length; i++) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            event.setKey(keys[i]);
            keyboard.addEventListener(event);
        }
    }

    @Override
    public void run() {
        init();
        while (true) {
            // currently this method is only used for testing
            System.out.printf("", "hello");
            if (enterPressed) {
                System.out.println("opop");
                inputBox.setStringOnInputBox(textInInputBox);
                System.out.println(inputBox.getStringOnInputBox());
                enterPressed = false;
            }
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        int ascii = 1;
        ascii = keyboardEvent.getKey();
        char character = (char) ascii;
        String str = Character.toString(character);

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_ENTER:
                System.out.println("here");
                enterPressed = true;
                break;
            case KeyboardEvent.KEY_BACK_SLASH:
                //delete last character entered
                textInInputBox = textInInputBox.substring(0, textInInputBox.length() - 1);
                if (!textInInputBox.isEmpty()) {
                    inputBox.setStringOnInputBox(textInInputBox);
                    inputBox.characterRemoved();
                }
                break;
            default:
                textInInputBox += str;
                inputBox.setStringOnInputBox(textInInputBox);
                inputBox.characterEntered();
                break;
        }
        System.out.println(textInInputBox);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

    // it won t have punctuation , only letters and numbers? find another "SIMPLER" way....
    //to erase you use backslash, there is no implementation of ascii no.8
    int[] keys = {
            KeyboardEvent.KEY_SPACE,
            KeyboardEvent.KEY_0,
            KeyboardEvent.KEY_1,
            KeyboardEvent.KEY_2,
            KeyboardEvent.KEY_3,
            KeyboardEvent.KEY_4,
            KeyboardEvent.KEY_5,
            KeyboardEvent.KEY_6,
            KeyboardEvent.KEY_7,
            KeyboardEvent.KEY_8,
            KeyboardEvent.KEY_9,
            KeyboardEvent.KEY_Q,
            KeyboardEvent.KEY_W,
            KeyboardEvent.KEY_E,
            KeyboardEvent.KEY_R,
            KeyboardEvent.KEY_T,
            KeyboardEvent.KEY_Y,
            KeyboardEvent.KEY_U,
            KeyboardEvent.KEY_I,
            KeyboardEvent.KEY_O,
            KeyboardEvent.KEY_P,
            KeyboardEvent.KEY_A,
            KeyboardEvent.KEY_S,
            KeyboardEvent.KEY_D,
            KeyboardEvent.KEY_F,
            KeyboardEvent.KEY_G,
            KeyboardEvent.KEY_H,
            KeyboardEvent.KEY_J,
            KeyboardEvent.KEY_K,
            KeyboardEvent.KEY_L,
            KeyboardEvent.KEY_Z,
            KeyboardEvent.KEY_X,
            KeyboardEvent.KEY_C,
            KeyboardEvent.KEY_V,
            KeyboardEvent.KEY_B,
            KeyboardEvent.KEY_N,
            KeyboardEvent.KEY_M,
            KeyboardEvent.KEY_ENTER,
            KeyboardEvent.KEY_BACK_SLASH,
    };

}
