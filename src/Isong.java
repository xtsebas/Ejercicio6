package Model;

import controles.ICancion;

public class Isong implements ICancion {
    String title;
    String artist;
    String album;
    String duration;
    int id;

    public Isong(String title, String artist, String album, String duration, int id) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.id = id;
    }

    /**
     * @param _title
     */
    @Override
    public void setTitle(String _title) {
        this.title=title;
    }

    /**
     * @return
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * @param _artist
     */
    @Override
    public void setArtist(String _artist) {
        this.artist=_artist;
    }

    /**
     * @return
     */
    @Override
    public String getArtist() {
        return artist;
    }

    /**
     * @param _album
     */
    @Override
    public void setAlbum(String _album) {
        this.album=_album;
    }

    /**
     * @return
     */
    @Override
    public String getAlbum() {
        return album;
    }

    /**
     * @param _duration
     */
    @Override
    public void setDuration(String _duration) {
        this.duration=_duration;
    }

    /**
     * @return
     */
    @Override
    public String getDuration() {
        return duration;
    }

    /**
     * @param _id
     */
    @Override
    public void setID(int _id) {
        this.id=_id;
    }

    /**
     * @return
     */
    @Override
    public int getID() {
        return id;
    }
}

