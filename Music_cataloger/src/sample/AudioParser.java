package sample;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class AudioParser {
    public File sourceFile;
    MP3File mp3File;
    public String genre,album,artist;
    public static String[] genres=GenreList.genres();
    public AudioParser(File source)
    {
        sourceFile=source;
        try{
            mp3File = new MP3File(sourceFile);
            ID3v1 tag = mp3File.getID3v1Tag();
            album = tag.getAlbum();
            if (tag.getGenre()>=0&&tag.getGenre()<80)
                genre = genres[tag.getGenre()];
            else
                genre = genres[80];
            artist = tag.getArtist();
        }
        catch (FileNotFoundException | NullPointerException | TagException e)
        {
            genre = genres[80];
            artist = "Unknown Artist";
            album = "Unknown";
        }
        catch (IOException e)
        {e.printStackTrace();}
    }
}
