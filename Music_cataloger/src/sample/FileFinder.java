package sample;

import java.io.File;
import static sample.Controller.mp3List;
import static sample.Controller.mp3ArtistList;
import static sample.Controller.mp3AlbumList;

public class FileFinder {
    private String startPath=new String();
    public FileFinder(String start) {
        startPath=start;
    }

    public void start() {
        try{
            File root = new File(startPath);
            for (File currentFile : root.listFiles()) {
                if (currentFile.isFile()) {
                    if (currentFile.getName().endsWith(".mp3"))
                    {
                        mp3List.add(new AudioParser(currentFile));
                        if(!mp3ArtistList.contains(mp3List.get(mp3List.size()-1).artist))
                            mp3ArtistList.add(mp3List.get(mp3List.size()-1).artist);
                        if(!mp3AlbumList.contains(mp3List.get(mp3List.size()-1).album))
                            mp3AlbumList.add(mp3List.get(mp3List.size()-1).album);
                        // i++;
                        // if (i%100==0) System.out.println(i);
                    }
                } else if (currentFile.isDirectory()) {
                    new FileFinder(currentFile.getAbsolutePath()).start();
                }
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("filefinder error");
        }
        catch (Exception e)
        {
            System.out.println("mp3 error");
        }
    }

}
