package com.zeusville.panovit;


import com.tivo.hme.bananas.BApplication;
import com.tivo.hme.bananas.BView;
import com.tivo.hme.interfaces.IContext;

import java.awt.Color;


public class Pandora extends BApplication {    
    @Override
    public void init(IContext context) throws Exception {
        super.init(context);
        BView root = getBelow();
        root.setResource("blue.jpg");
        
        MainScreen mainscreen = new MainScreen(this);
        System.out.println(SAFE_TITLE_H + " " + SAFE_TITLE_V + " " + getWidth() + " " + 35);
        BView text = new BView(mainscreen.getNormal(), SAFE_TITLE_H, SAFE_TITLE_V, getWidth(), 35);
        text.setResource(createText("default-24-bold.font", Color.WHITE,
              "Panovit - Pandora for your TiVo"));
        push(mainscreen, TRANSITION_NONE);

    }    
}
