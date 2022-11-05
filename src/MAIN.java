import controles.IIpod;
import scala.util.parsing.combinator.testing.Str;

import java.util.Scanner;

public class MAIN {
    public static void main(String[] args) throws Exception {
        Scanner in= new Scanner(System.in);
        Model.Isong nothing= new Model.Isong("None", "None", "None", "0:00", 0);
        controles.IIpod ipod = new controles.IIpod();
        System.out.println("1. Prender\n2. Apagar");
        int turn = in.nextInt();
        boolean listen=false;
        while (turn ==1){
            if (turn == 1) {
                boolean ison = ipod.SwitchONOFF(false);
                System.out.println("#################################################");
                System.out.println("1. bloquear\n2. desbloquear");
                int block = in.nextInt();
                if (block == 1) {
                    System.out.println("#################################################");
                    ipod.getStatus(ison, false, listen, nothing);
                    System.out.println("Volumen: " + ipod.getVolume());
                    System.out.println("#################################################");
                    System.out.println("1. Subir volumen\n2. Bajar volumen\n3. Play/Pause\n4. Next\n5. Prev");
                    int optionblock= in.nextInt();
                    System.out.println("#################################################");
                    switch (optionblock){
                        case 1:
                            System.out.println("Cuantos clicks?");
                            int click= in.nextInt();
                            ipod.setVolume((float) (ipod.getVolume()+(click*0.5)));
                            if (ipod.getVolume()>=10){
                                ipod.setVolume(10);
                            }
                            System.out.println("#################################################");
                            ipod.getStatus(ison, false, listen, nothing);
                            System.out.println("Volumen: " + ipod.getVolume());
                            break;
                        case 2:
                            System.out.println("Cuantos clicks?");
                            int _click= in.nextInt();
                            ipod.setVolume((float) (ipod.getVolume()-(_click*0.5)));
                            if (ipod.getVolume()<=0){
                                ipod.setVolume(0);
                            }
                            System.out.println("#################################################");
                            ipod.getStatus(ison, false, listen, nothing);
                            System.out.println("Volumen: " + ipod.getVolume());
                            break;
                        case 3:
                            System.out.println("#################################################");
                            ipod.getStatus(ison, false, listen, nothing);
                            System.out.println("Volumen: " + ipod.getVolume());
                            break;
                        case 4:
                            int _new =ipod.Next(ipod.getActualIndex());
                            ipod.setActualIndex(_new);
                            System.out.println("#################################################");
                            ipod.getStatus(ison, false, listen, nothing);
                            System.out.println("Volumen: " + ipod.getVolume());
                            break;
                        case 5:
                            _new =ipod.Prev(ipod.getActualIndex());
                            ipod.setActualIndex(_new);
                            System.out.println("#################################################");
                            ipod.getStatus(ison, false, listen, nothing);
                            System.out.println("Volumen: " + ipod.getVolume());
                            break;
                    }
                } else {
                    System.out.println("#################################################");
                    ipod.getStatus(ison,true, listen,nothing);
                    System.out.println("Volumen: " + ipod.getVolume());
                    System.out.println("#################################################");
                    System.out.println("1. Agregar cancion\n2. Elegir cancion\n3. Agregar cancion a favoritas\n4. Ver playlist\n5. Eliminar cancion");
                    int option= in.nextInt();
                    in.nextLine();
                    switch (option){
                        case 1:
                            System.out.println("#################################################");
                            System.out.println("Nombre de la cancion: ");
                            String title = in.nextLine();
                            System.out.println("Nombre del artista: ");
                            String artist = in.nextLine();
                            System.out.println("Nombre del album: ");
                            String album= in.nextLine();
                            System.out.println("Duracion: ");
                            String duration= in.nextLine();
                            System.out.println("Id: ");
                            int id= in.nextInt();
                            ipod.addSongToList(title,artist,album,duration,id);
                            listen=true;
                            break;
                        case 2:
                            Model.Isong[] songs;
                            int election;
                            System.out.println("1. Playlist\n2. Favoritos");
                            int play= in.nextInt();
                            if (play==1){
                                songs= (Model.Isong[]) ipod.getAllSongs();
                                System.out.println("Cual cancion desea?");
                                election= in.nextInt();
                                ipod.setActualIndex(ipod.selectSpecificSong(election).getID());
                                System.out.println("#################################################");
                                ipod.getStatus(ison,true, listen,nothing);
                                System.out.println("Volumen: " + ipod.getVolume());
                                break;
                            }else {
                                songs= (Model.Isong[]) ipod.getFavoriteSongs();
                                System.out.println("Cual cancion desea?");
                                election= in.nextInt();
                                ipod.setActualIndex(ipod.selectSpecificFavoriteSong(election).getID());
                                System.out.println("#################################################");
                                ipod.getStatus(ison,true, listen,nothing);
                                System.out.println("Volumen: " + ipod.getVolume());
                                break;
                            }

                        case 3:
                            songs= (Model.Isong[]) ipod.getAllSongs();
                            System.out.println("Cual cancion desea agregar a favoritas?");
                            election= in.nextInt();
                            ipod.addToFavorite(ipod.selectSpecificSong(election));
                            break;
                        case 4:
                            System.out.println("1. Playlist\n2. Favoritos");
                            play= in.nextInt();
                            if (play==1){
                                songs= (Model.Isong[]) ipod.getAllSongs();
                                break;
                            } else {
                                songs= (Model.Isong[]) ipod.getFavoriteSongs();
                                break;
                            }
                        case 5:
                            System.out.println("1. Playlist\n2. Favoritos");
                            play= in.nextInt();
                            if (play==1){
                                songs= (Model.Isong[]) ipod.getAllSongs();
                                System.out.println("Cual cancion desea eliminar?");
                                option= in.nextInt()-1;
                                ipod.deleteSongFromList(option);
                                break;
                            } else {
                                songs= (Model.Isong[]) ipod.getFavoriteSongs();
                                System.out.println("Cual cancion desea eliminar?");
                                option= in.nextInt()-1;
                                ipod.deleteSongFromTop10(option);
                                break;
                            }
                    }

                }
            } else {
                System.out.println("#################################################");
                ipod.getStatus(false,false,false, nothing);
                break;
            }
            System.out.println("#################################################");
            System.out.println("Apagar? \n1.No \n2.Si");
            turn = in.nextInt();
        }
    }
}
