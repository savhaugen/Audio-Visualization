package audiosynth.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import audiosynth.audio.AudioBuffer;
import audiosynth.audio.Signal;
import audiosynth.audio.Utils;

/**
 * A collection of notes that (perhaps) make music together.
 * A newly created song starts out empty — no notes.
 */
public class Song {
    private final List<Note> notes = new ArrayList<>();

    /**
     * Adds the given note to the notes already in the piece.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Returns an unmodifiable collection of all the notes in the piece.
     */
    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }

    /**
     * Returns maximum end time of any note in the song. Returns 0 if the song is empty.
     */
    public double getDuration() {
        return notes.stream()
            .mapToDouble(Note::getEndTime)
            .max().orElse(0);
    }

    /**
 * Renders all the notes in the piece to a normalized audio buffer.
 */
    public AudioBuffer renderAudio() {
        AudioBuffer audioBuffer = new AudioBuffer(Utils.convertSecondsToSamples(getDuration()));
        for (Note note: notes){
            //Signal signal =note.getWaveform().createSignal(Utils.convertPitchToWavelength(note.getPitch()));
            audioBuffer.mix(
                note.getWaveform().createSignal(
                        Utils.convertPitchToWavelength(note.getPitch())), 
                Utils.convertSecondsToSamples(note.getStartTime()), 
                Utils.convertSecondsToSamples(note.getDuration()));
        }
        audioBuffer.normalize();
        return audioBuffer;
    }
}
