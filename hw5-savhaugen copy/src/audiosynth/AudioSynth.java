package audiosynth;

import audiosynth.audio.SineWave;
import audiosynth.audio.SquareWave;
import audiosynth.audio.TriangularWave;
import audiosynth.model.Note;
import audiosynth.model.Song;
import audiosynth.model.SongReader;
import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;

/**
 * Generates and visualizes music.
 */
public class AudioSynth {
    public static void main(String[] args) {
        // 3-note test song

        String fileName = "kondo.csv";  // Also try "bach.csv"
        Song song = new SongReader().readSong(fileName);

        SongVisualization visualization = new SongVisualization(80, 6);
        visualization.showSong(song);

        CanvasWindow window = new CanvasWindow(
            fileName,
            (int) visualization.getBounds().getMaxX(),
            (int) visualization.getBounds().getMaxY());
        window.add(visualization);
        window.setBackground(Color.BLACK);

      
        song.renderAudio().play(visualization::setTime);
        
        // Song song = new Song();
        // song.addNote(new Note(new SineWave(), 51, 0, 2.0));
        // song.addNote(new Note(new TriangularWave(), 58, 0.5, 1.5));
        // song.addNote(new Note(new SquareWave(), 67, 1.0, 1.0));

        // Longer test song

//        String fileName = "kondo.csv";
//        Song song = new SongReader().readSong(fileName);
//
//        song.renderAudio().play();
    }
}
