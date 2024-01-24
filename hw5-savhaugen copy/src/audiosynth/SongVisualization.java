package audiosynth;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import audiosynth.audio.Waveform;
import audiosynth.model.Note;
import audiosynth.model.Song;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

/**
 * Visualizes a Song as a collection of multicolored rectangles.
 */
public class SongVisualization extends GraphicsGroup {
    public static final int MAX_PITCH = 120;

    private final double pixelsPerSecond, pixelsPerSemitone;
    private final Map<Waveform,Color> waveformColors = new HashMap<>();

    /**
     * Creates an empty song visualization.
     *
     * @param pixelsPerSecond   Horizontal distance of one second
     * @param pixelsPerSemitone Number of pixels per pitch unit
     */
    public SongVisualization(double pixelsPerSecond, double pixelsPerSemitone) {
        this.pixelsPerSecond = pixelsPerSecond;
        this.pixelsPerSemitone = pixelsPerSemitone;
    }

    /**
     * Shows the notes of the given song, removing any song already present.
     */
    public void showSong(Song song) {
        List<Note> notes = song.getNotes();
        for (Note note: notes){
            Rectangle noteRectangle = new Rectangle(
                    pixelsPerSecond*note.getStartTime(), 
                    (MAX_PITCH- note.getPitch()) * pixelsPerSemitone, 
                    note.getDuration()* pixelsPerSecond, 
                    pixelsPerSemitone);
            noteRectangle.setStrokeWidth(.5);
            noteRectangle.setFillColor(getNoteColor(note));
            this.add(noteRectangle);
        }
    }

    /**
 * Moves the visualization to show that the given time is the current time.
 *
 * @param seconds Time from the beginning of the song
 * @param done    True if the song is done playing
 */
public void setTime(double seconds, boolean done) {
    // TODO: move all notes horizontally (see instructions below)

    this.setPosition(100-seconds *pixelsPerSecond, 0); 

    if (getCanvas() != null) {
        getCanvas().draw();  // because we are staying on the main method here, no event loop
    }

}
    

    private Color getNoteColor(Note note) {
        Waveform waveform = note.getWaveform();
        Color color = waveformColors.get(waveform);
        if (color == null) {
            color = Color.getHSBColor(waveformColors.size() * 0.382f % 1, 1, 0.6f);
            waveformColors.put(waveform, color);
        }
        return color;
    }
}