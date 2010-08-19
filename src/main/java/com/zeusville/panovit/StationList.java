package com.zeusville.panovit;

import com.tivo.hme.bananas.BList;
import com.tivo.hme.bananas.BText;
import com.tivo.hme.bananas.BView;


public class StationList extends BList {
    public StationList(BView parent, int x, int y, int width, int height,
            int rowHeight, boolean visible) {
        super(parent, x, y, width, height, rowHeight, visible);
        setBarAndArrows(BAR_HANG, BAR_DEFAULT, null, "push");
    }

    public StationList(BView parent, int x, int y, int width, int height,
            int rowHeight) {
        this(parent, x, y, width, height, rowHeight, true);
    }

    @Override
    protected void createRow(BView parent, int index) {
        BText text = new BText(parent, 60, 0, parent.getWidth()-70, parent.getHeight());
        text.setFlags(RSRC_HALIGN_LEFT);
        text.setShadow(true);
        
        //
        // set the value of the row to be the text that was 
        // passed in through add()
        //
        
        text.setValue(get(index));
    }

    @Override
    public void setFocus(int index, boolean animate) {
        super.setFocus(index, animate);
    }
}
