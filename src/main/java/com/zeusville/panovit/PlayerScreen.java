package com.zeusville.panovit;

import com.tivo.hme.bananas.BApplication;
import com.tivo.hme.bananas.BButton;
import com.tivo.hme.bananas.BScreen;
import com.tivo.hme.bananas.BView;

import java.awt.Color;


public class PlayerScreen extends BScreen {
    public PlayerScreen(BApplication app) {
        super(app);
        
        // create a "return to menu" button
        BButton button = new BButton(getNormal(), SAFE_TITLE_H, getNormal().getHeight() - SAFE_TITLE_H-30, 400, 30);
        button.setBarAndArrows(BAR_HANG, BAR_DEFAULT, "pop", null, H_UP, null, true);
        button.setResource(createText("default-24.font", Color.white, "Return to station list"));
        button.setFocusable(true);
        setFocus(button);
    }
    
    public boolean handleKeyPress(int code, long rawcode) {            
        if (code == KEY_SELECT || code == KEY_LEFT) {
            getBApp().pop();
            return true;
        }

        return super.handleKeyPress(code, rawcode);
    }
    
    public boolean handleAction(BView view, Object action) {
        System.out.println("PlayerScreen");
        System.out.println(view.getClass().getName());
        System.out.println(action.toString());
        if ("left".equals(action) || "pop".equals(action)) {
            getBApp().pop();
            return true;                
        }

        return super.handleAction(view, action);
    }
}
