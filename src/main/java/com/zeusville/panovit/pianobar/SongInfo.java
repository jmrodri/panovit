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
package com.zeusville.panovit.pianobar;


public class SongInfo {

    private String artist;
    private String stationname;
    private String title;
    private String album;
    private long duration;
    private long played;
    private long rating;
    
    public SongInfo(String a, String sn, String t, String alb, long d, long p, long r) {
        artist = a;
        stationname = sn;
        title = t;
        album = alb;
        duration = d;
        played = p;
        rating = r;
    }

    
    public String getArtist() {
        return artist;
    }

    
    public String getStationname() {
        return stationname;
    }

    
    public String getTitle() {
        return title;
    }

    
    public String getAlbum() {
        return album;
    }

    
    public long getDuration() {
        return duration;
    }

    
    public long getPlayed() {
        return played;
    }

    
    public long getRating() {
        return rating;
    }
}
