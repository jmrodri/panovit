package com.zeusville.panovit;

import com.tivo.hme.bananas.BApplication;
import com.tivo.hme.bananas.BButton;
import com.tivo.hme.bananas.BKeyboard;
import com.tivo.hme.bananas.BScreen;
import com.tivo.hme.bananas.BView;

import java.awt.Color;
import java.awt.Point;


public class NewStationScreen extends BScreen {

    private BKeyboard kb;

    public NewStationScreen(BApplication app) {
        super(app);
        // get the size of a default keyboard
        Point p = BKeyboard.getKeyboardSize(BKeyboard.PLAIN_KEYBOARD, true);
        kb = new BKeyboard(getNormal(), 100, 140, p.x, p.y);
       
        setFocus(kb);
        
        // create a "return to menu" button
        BButton add = new BButton(getNormal(), SAFE_TITLE_H, getNormal().getHeight() - SAFE_TITLE_H-55, 400, 30);
        add.setBarAndArrows(BAR_HANG, BAR_DEFAULT, "add", null, H_UP, H_DOWN, true);
        add.setResource(createText("default-24.font", Color.white, "Add station"));
        add.setFocusable(true);
        
        // create a "return to menu" button
        BButton button = new BButton(getNormal(), SAFE_TITLE_H, getNormal().getHeight() - SAFE_TITLE_H-15, 400, 30);
        button.setBarAndArrows(BAR_HANG, BAR_DEFAULT, "pop", null, H_UP, null, true);
        button.setResource(createText("default-24.font", Color.white, "Return to station list"));
        button.setFocusable(true);
    }
    
    public boolean handleAction(BView view, Object action) {
        System.out.println("handleAction:" + view.getClass().getName());
        System.out.println("handleAction.action: " + action.toString());
        if ("left".equals(action) || "pop".equals(action)) {
            System.out.println("lefting. kb = " + kb.getValue());
            getBApp().pop();
            return true;                
        }
        else if ("add".equals(action)) {
            getBApp().pop(kb.getValue());
            return true;
        }

        return super.handleAction(view, action);
    }
    
    /**
     * 
     */
    public boolean handleKeyPress(int code, long rawcode) {            
        if (code == KEY_SELECT || code == KEY_LEFT) {
            System.out.println("handling key press");
            getBApp().pop(kb.getValue());
            return true;
        }
        return super.handleKeyPress(code, rawcode);
    }

    @Override
    public boolean handleEnter(Object arg, boolean isReturn) {
        if (!isReturn) {
            kb.setValue("");
        }

        return super.handleEnter(arg, isReturn);
    }
}
