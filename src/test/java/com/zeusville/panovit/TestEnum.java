/**
 * Copyright (c) 2010 jesus m. rodriguez
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 */
package com.zeusville.panovit;

import org.junit.Test;


public class TestEnum {
    enum EventType {
        ARTISTBOOKMARK("artistbookmark"),
        SONGBAN("songban"),
        SONGBOOKMARK("songbookmark"),
        SONGEXPLAIN("songexplain"),
        SONGFINISH("songfinish"),
        SONGLOVE("songlove"),
        SONGMOVE("songmove"),
        SONGSHELF("songshelf"),
        SONGSTART("songstart"),
        STATIONADD("stationadd"),
        STATIONADDMUSIC("stationaddmusic"),
        STATIONADDSHARED("stationaddshared"),
        STATIONCREATE("stationcreate"),
        STATIONDELETE("stationdelete"),
        STATIONFETCHPLAYLIST("stationfetchplaylist"),
        STATIONQUICKMIXTOGGLE("stationquickmixtoggle"),
        STATIONRENAME("stationrename");
        
        private String label;
        
        EventType(String str) {
            label = str;
        }
        
        public String getLabel() {
            return label;
        }
        
        public String toString() {
            return getLabel();
        }
    }
    
    @Test
    public void testFoo() {
        for(EventType et : EventType.values()) {
            foo(et);
        }
    }
    
    public void foo(EventType et) {
        switch(et) {
        case SONGBAN:
            System.out.println(et);
            break;
        case STATIONRENAME:
            System.out.println(et);
            break;
        }
    }
}
