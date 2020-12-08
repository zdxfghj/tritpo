package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import static sample.AudioParser.genres;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    public ListView<String> trackList_Tracks = new ListView<>();
    public ListView<String> trackList_Genres = new ListView<>();
    public ListView<String> trackList_Artists = new ListView<>();
    public ListView<String> trackList_Albums = new ListView<>();
    public ListView<String> currentTagTrackList = new ListView<>();
    @FXML
    TextFlow albumLabel;
    @FXML
    TextFlow genreLabel;
    @FXML
    TextFlow artistLabel;
    @FXML
    Label loadingLabel;
    @FXML
    TextField loadPath;


    public static ArrayList<AudioParser> mp3List = new ArrayList<>();
    public static ArrayList<String> mp3ArtistList = new ArrayList<>();
    public static ArrayList<String> mp3AlbumList = new ArrayList<>();
    public ObservableList<String> trackNameList;
    private void convertFilesToListView()
    {
        ArrayList<String> trackNameTempList = new ArrayList<>();

        for (AudioParser fil : mp3List) {
            trackNameTempList.add(fil.sourceFile.getName());
        }
        trackNameList = FXCollections.observableArrayList(trackNameTempList);
        trackList_Tracks.setItems(trackNameList);
    }

    public void createGenresListView()
    {
        ArrayList<String> trackNameTempList = new ArrayList<>();

        for (int i=0;i<81;i++) {
            trackNameTempList.add(genres[i]);
        }
        trackNameList = FXCollections.observableArrayList(trackNameTempList);
        trackList_Genres.setItems(trackNameList);
    }

    public void createAlbumsListView()
    {
        ArrayList<String> trackNameTempList = new ArrayList<>();

        for (String album : mp3AlbumList) {
            trackNameTempList.add(album);
        }
        trackNameList = FXCollections.observableArrayList(trackNameTempList);
        trackList_Albums.setItems(trackNameList);
    }

    public void createArtistsListView()
    {
        ArrayList<String> trackNameTempList = new ArrayList<>();

        for (String artist : mp3ArtistList) {
            trackNameTempList.add(artist);
        }
        trackNameList = FXCollections.observableArrayList(trackNameTempList);
        trackList_Artists.setItems(trackNameList);
    }

    public void trackList_Tracks_OnClick()
    {
        AudioParser selectedTrack = mp3List.get(trackList_Tracks.getSelectionModel().getSelectedIndex());
        albumLabel.getChildren().clear();
        albumLabel.getChildren().add(new Text(selectedTrack.album));
        artistLabel.getChildren().clear();
        artistLabel.getChildren().add(new Text(selectedTrack.artist));
        genreLabel.getChildren().clear();
        genreLabel.getChildren().add(new Text(selectedTrack.genre));
    }

    public void trackList_Genres_OnClick()
    {
        ArrayList<String> trackNameTempList = new ArrayList<>();
        String selectedGenre = trackList_Genres.getSelectionModel().getSelectedItem();
        for (AudioParser file : mp3List) {
            if(file.genre.equals(selectedGenre))
                trackNameTempList.add(file.sourceFile.getName());
        }
        trackNameList = FXCollections.observableArrayList(trackNameTempList);
        currentTagTrackList.setItems(trackNameList);
    }

    public void trackList_Albums_OnClick()
    {
        ArrayList<String> trackNameTempList = new ArrayList<>();
        String selectedGenre = trackList_Albums.getSelectionModel().getSelectedItem();
        for (AudioParser file : mp3List) {
            if(file.album.equals(selectedGenre))
                trackNameTempList.add(file.sourceFile.getName());
        }
        trackNameList = FXCollections.observableArrayList(trackNameTempList);
        currentTagTrackList.setItems(trackNameList);
    }

    public void trackList_Artists_OnClick()
    {
        ArrayList<String> trackNameTempList = new ArrayList<>();
        String selectedGenre = trackList_Artists.getSelectionModel().getSelectedItem();
        for (AudioParser file : mp3List) {
            if(file.artist.equals(selectedGenre))
                trackNameTempList.add(file.sourceFile.getName());
        }
        trackNameList = FXCollections.observableArrayList(trackNameTempList);
        currentTagTrackList.setItems(trackNameList);
    }

    public void trackList_CurrentTagTracks_OnClick()
    {
        AudioParser selectedTrack = mp3List.get(0);
        for (AudioParser currentMp3 : mp3List)
            if (currentMp3.sourceFile.getName().equals(currentTagTrackList.getSelectionModel().getSelectedItem()))
                selectedTrack = currentMp3;
        albumLabel.getChildren().clear();
        albumLabel.getChildren().add(new Text(selectedTrack.album));
        artistLabel.getChildren().clear();
        artistLabel.getChildren().add(new Text(selectedTrack.artist));
        genreLabel.getChildren().clear();
        genreLabel.getChildren().add(new Text(selectedTrack.genre));
    }

    public void trackList_Load_OnClick()
    {
        loadingLabel.setText("Loading...");
        if(loadPath.getText().isEmpty()) {
            loadingLabel.setText("Empty path");
            return;
        }
        tryToFindAllFiles(loadPath.getText());
        loadingLabel.setText("Loaded");
        init();
    }

    public void tryToFindAllFiles(String startString) {
        new FileFinder(startString + "\\").start();
    }

    public void init()
    {
        convertFilesToListView();
        createGenresListView();
        createAlbumsListView();
        createArtistsListView();
    }

    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

}
