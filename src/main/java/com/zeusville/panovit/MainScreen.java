package com.zeusville.panovit;

import com.tivo.hme.bananas.BApplication;
import com.tivo.hme.bananas.BScreen;
import com.tivo.hme.bananas.BView;
import com.tivo.hme.sdk.HmeEvent;
import com.zeusville.panovit.pianobar.Callback;
import com.zeusville.panovit.pianobar.EventType;

import java.util.List;

public class MainScreen extends BScreen implements Callback {
    
    private StationList stations;
    private NewStationScreen nss;
    private PlayerScreen player;
    
    public MainScreen(BApplication app) {
        super(app);
        
        stations = new StationList(getNormal(), SAFE_TITLE_H+10, (getHeight()-SAFE_TITLE_V)-290, 300, 280, 35);

        stations.add("Create New Station...");
//        stations.add("Timbaland Radio");
//        stations.add("Lords of Acid Radio");
        setFocusDefault(stations);
        nss = new NewStationScreen(getBApp());
        player = new PlayerScreen(getBApp());
    }
    
    public boolean handleAction(BView view, Object action) {
        System.out.println(view.getClass().getName());
        System.out.println("action: " + action);
        
        if ("push".equals(action)) {
            int idx = stations.getFocus();
            switch(idx) {
                case 0:
                    getBApp().push(nss, TRANSITION_LEFT);
                    break;
                default:
                    getBApp().push(player, TRANSITION_LEFT);
                    break;
                    
            }
            return true;
        }        
        return super.handleAction(view, action);
    }
    
    

    @Override
    public boolean handleKeyPress(int code, long rawcode) {
        switch (code) {
            case KEY_ENTER:
            case KEY_SELECT:
                if (stations.getFocus() == 0) {
                    getBApp().push(nss, TRANSITION_LEFT);
                }
                else {
                    getBApp().push(player, TRANSITION_LEFT);
                }
                break;
        }
        return super.handleKeyPress(code, rawcode);
    }

    @Override
    public boolean handleEnter(Object arg, boolean isReturn) {
        if (isReturn) {
            System.out.println("returning to the MainScreen cuz of a pop");
        }
        else {
            System.out.println("this is not a return");
            
        }
        
        if (arg != null) {
            String station = arg.toString();
            System.out.println("arg: " + station);
            if (!"".equals(station) && !stations.contains(station)) {
                stations.add(station);
            }
        }

        return super.handleEnter(arg, isReturn);
    }
    
    @Override
    public boolean handleEvent(HmeEvent event) {
        System.out.println(event.getClass().getName());
        
        System.out.println("opcode: " + event.getOpCode());
        System.out.println("id: " + event.getID());
        switch(event.getOpCode()) {
            case HmeEvent.EVT_KEY:
                System.out.println("key pressed");
                break;
        }
        return super.handleEvent(event);
    }
    
    @SuppressWarnings("unchecked")
    public void callback(EventType type, Object data) {
        switch(type) {
        case STATIONADD:
            List<String> stations = (List<String>) data;
            for (String station : stations) {
                this.stations.add(station);
            }
            break;
        case STATIONCREATE:
            this.stations.add((String) data);
            break;
        case STATIONDELETE:
            this.stations.remove((String) data);
            break;
            
        case SONGSTART:
            //player
            break;
        }
    }
}
