package controles;
import Model.Isong;

import java.util.ArrayList;
import java.util.Scanner;

public class IIpod implements IIpod_simulator {
    public static ArrayList<Isong> Songs = new ArrayList<>();
    public static ArrayList<Isong> Favorites= new ArrayList<>();
    Scanner in= new Scanner(System.in);
    float volume;
    int actualIndex;

    /***
     * Este método enciende / Apaga el dispositivo, recibe una variable booleana
     * que tiene el estado actual del dispositivo
     * @param actual_state el estado actual del dispositivo
     * @return el estado futuro del dipositivo
     */
    @Override
    public boolean SwitchONOFF(boolean actual_state) {
        if (actual_state){
            actual_state=false;
        }else {
            actual_state=true;
        }
        return actual_state;
    }

    /***
     * Este método bloquea / Desblocquea el dispositivo, para que no se ejecute ninguna
     * acción cuando esté bloqueado
     * @param actual_locked_state estado de bloqueo actual del dispositivo
     * @return estado de bloqueo futuro del dispositivo
     */
    @Override
    public boolean LockUnlockDevice(boolean actual_locked_state) {
        if (actual_locked_state){
            actual_locked_state=false;
        }else {
            actual_locked_state=true;
        }
        return actual_locked_state;
    }

    /***
     * Obtiene el volumen actual
     * @return valor que indica el volumen actual
     */
    @Override
    public float getVolume() {
        return volume;
    }

    /***
     * Establece el volumen actual
     * @param volume
     * @return
     */
    @Override
    public float setVolume(float volume) {
        this.volume=volume;
        return volume;
    }

    /***
     * Adelanta una cancion en la lista y la devuelve
     * @param actual_index el indice actual
     * @return el nuevo indice
     */
    @Override
    public int Next(int actual_index) {
        int new_index = 0;
        if (Songs.size() == 0) {
            System.out.println("No tienes ninguna cancion agregada");
        } else {
            new_index = actual_index + 1;
            if (new_index == Songs.size()) {
                new_index = 0;
            }
        }
        return new_index;
    }

    /***
     * Regresa una cancion en la lista y la devuelve
     * @param actual_index el indice actual
     * @return el nuevo indice
     */
    @Override
    public int Prev(int actual_index) {
        int new_index = 0;
        if (Songs.size() == 0) {
            System.out.println("No tienes ninguna cancion agregada");
        } else {
            new_index = actual_index - 1;
            if (new_index == -1) {
                new_index = Songs.size()-1;
            }
        }
        return new_index;
    }

    /***
     * Obtiene el indice actual de la cancion que se esta ejecutando
     * @return el indice actual
     */
    @Override
    public int getActualIndex() {
        return actualIndex;
    }

    /***
     * Establece el indice Actual
     * @param actual_index
     * @exception Exception Cuando el indice esta fuera de rango
     */
    @Override
    public void setActualIndex(int actual_index) throws Exception {
        try {
            isValidIndex(actual_index);
            if (isValidIndex(actual_index)){
                this.actualIndex=actual_index;
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * Este metodo guarda una cancion en listado de favoritos
     *
     * @param _song la cancion que se desea guardar
     * @throws Exception cuando la lista ya esta llena
     */
    @Override
    public void addToFavorite(ICancion _song) throws Exception {
        if (Favorites.size()==10){
            System.out.println("Ya no puede agregar mas canciones a su playlist de favoritos");
        }else {
            Favorites.add((Isong) _song);
        }
    }

    /***
     * Devolvera una cancion especifica del listado general
     * @param index el indice en el que se encuentra la cancion
     * @return El objeto Cancion
     * @exception Exception Cuando el indice esta fuera de rango
     */
    @Override
    public ICancion selectSpecificSong(int index) throws Exception {
        int newindex= index-1;
        try {
            isValidIndex(newindex);
        } catch (Exception exception) {
            System.out.println("Su indice esta fuera del rango ");
            throw exception;
        }
        Isong song = null;
        if (isValidIndex(newindex)) {
            String title = Songs.get(newindex).getTitle();
            String artist = Songs.get(newindex).getArtist();
            String album = Songs.get(newindex).getAlbum();
            String duration = Songs.get(newindex).getDuration();
            int id = Songs.get(newindex).getID();
            song = new Isong(title, artist, album, duration, id);
        }
        return song;
    }

    /***
     * Devolvera una cancion especifica del listado de las favoritas
     * @param index el indice en el que se encuentra la cancion
     * @return El objeto Cancion
     * @exception Exception Cuando el indice esta fuera de rango
     */
    @Override
    public ICancion selectSpecificFavoriteSong(int index) throws Exception {
        int i=1;
        for (Isong song: Favorites){
            System.out.println(i + ". Titulo: " + song.getTitle() + "\n Artistia: " + song.getArtist() + "\n Duracion: " + song.getDuration());
        }
        System.out.println("Cual cancion desea agregar a favoritos?");
        int newindex= in.nextInt() -1;
        try {
            isValidIndex(newindex);
        } catch (Exception exception) {
            System.out.println("Su indice esta fuera del rango ");
            throw exception;
        }
        Isong song = null;
        if (isValidIndex(newindex)) {
            String title = Favorites.get(newindex).getTitle();
            String artist = Favorites.get(newindex).getArtist();
            String album = Favorites.get(newindex).getAlbum();
            String duration = Favorites.get(newindex).getDuration();
            int id = Songs.get(newindex).getID();
            song = new Isong(title, artist, album, duration, id);
        }
        return song;
    }

    /**
     * Este metodo devuelve el listado completo de canciones
     *
     * @return el listado de canciones
     */
    @Override
    public ICancion[] getAllSongs() {
        int i=1;
        int j=0;
        Isong[] songs = new Model.Isong[50];
        for (Isong song: Songs){
            System.out.println("###############################" + "\n"+ i + ". Titulo: " + song.getTitle() + "\n Artista: " + song.getArtist() + "\n Duracion: " + song.getDuration());
            i=i+1;
            songs[j]=song;
            j=j+1;
        }
        return songs;
    }

    /***
     * ESte metodo devuelve el estado general del ipod
     * @param _isON valor logico que indica si esta encendido o apagado
     * @param _isLocked valor logico que indica si esta bloqueado o desbloqueado
     * @param _isPlaying valor logico que indica si esta sonando una cancion o no
     * @param _actualSong valor que indica que cancion esta sonando
     * @return Un cadena que contien e el estado de la informacion
     */
    @Override
    public String getStatus(boolean _isON, boolean _isLocked, boolean _isPlaying, ICancion _actualSong) {
        if (Songs.size()!=0){
            if (_isON && _isLocked){
                System.out.println("////IPOD////\nESTADO: " + " Encendido" + " \nPantalla: " + " Desbloqueado" + "\nEscuchando: " + _isPlaying + "\nCancion: " + Songs.get(actualIndex).getTitle());
            } else if (_isON == true && _isLocked== false) {
                System.out.println("////IPOD////\nESTADO: " + " Encendido" + " \nPantalla: " + " Bloqueado" + "\nEscuchando: " + _isPlaying + "\nCancion: " + Songs.get(actualIndex).getTitle());
            } else if (_isON == false && _isLocked == false) {
                System.out.println("////IPOD////\nESTADO: " + " Apagado" + " \nPantalla: " + " Bloqueado" + "\nEscuchando: " + _isPlaying + "\nCancion: " + _actualSong.getTitle());
            }
        } else {
            if (_isON && _isLocked){
                System.out.println("////IPOD////\nESTADO: " + " Encendido" + " \nPantalla: " + " Desbloqueado" + "\nEscuchando: " + _isPlaying + "\nCancion: " + _actualSong.getTitle());
            } else if (_isON == true && _isLocked== false) {
                System.out.println("////IPOD////\nESTADO: " + " Encendido" + " \nPantalla: " + " Bloqueado" + "\nEscuchando: " + _isPlaying + "\nCancion: " + _actualSong.getTitle());
            } else if (_isON == false && _isLocked == false) {
                System.out.println("////IPOD////\nESTADO: " + " Apagado" + " \nPantalla: " + " Bloqueado" + "\nEscuchando: " + _isPlaying + "\nCancion: " + _actualSong.getTitle());
            }
        }
        return null;
    }

    /***
     * Verifica si un indice es valido o no
     * @param index el indice que se desea verificar
     * @return true si el indice contiene cancion, false de lo cointrario
     */
    @Override
    public boolean isValidIndex(int index) {
        boolean valid;
        if (index<0 || index>50){
            valid = false;
        }else {
            valid = true;
        }
        return valid;
    }

    /***
     * Se agrega una cancion para ser instanciada en el metodo y agregada a la lista
     * @param _titulo
     * @param _artista
     * @param _album
     * @param _duracion
     * @param _id
     * @exception Exception si el listado esta lleno
     */
    @Override
    public void addSongToList(String _titulo, String _artista, String _album, String _duracion, int _id) throws Exception {
        try {
            if (Songs.size()!=50){
                if (Songs.size()==0){
                    int Id= Songs.size();
                    Isong song= new Isong(_titulo, _artista, _album, _duracion, _id);
                    Songs.add(song);
                }else {
                    int Id= Songs.size();
                    Isong song= new Isong(_titulo, _artista, _album, _duracion, Id);
                    Songs.add(song);
                }
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * Este metodo elimina una cancion de una posicion determinada
     *
     * @param index
     * @throws Exception Cuando el indice esta fuera de rango
     */
    @Override
    public void deleteSongFromList(int index) throws Exception {
        try {
            isValidIndex(index);
        }catch (Exception e){
            throw e;
        }
        if (isValidIndex(index)){
            Songs.remove(index);
        }
    }

    /**
     * Elimina una cancion de la lista de favoritas pero no del listado general
     *
     * @param index
     * @throws Exception Cuando el indice esta fuera de rango
     */
    @Override
    public void deleteSongFromTop10(int index) throws Exception {
        try {
            isValidIndex(index);
        }catch (Exception e){
            throw e;
        }
        if (isValidIndex(index)){
            Favorites.remove(index);
        }
    }

    public Isong[] getFavoriteSongs(){
        int i=1;
        int j=0;
        Isong[] songs = new Model.Isong[10];
        for (Isong song: Favorites){
            System.out.println("#################################################");
            System.out.println(i + ". Titulo: " + song.getTitle()+ "\n Artista: " + song.getArtist() + "\n Duracion: " + song.getDuration());
            i=i+1;
            songs[j]=song;
            j=j+1;
        }
        return songs;
    }
}

