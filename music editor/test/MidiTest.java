import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;


import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.IMusicEditorModelView;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.MusicEditorModelView;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.Pitch;
import cs3500.music.view.MidiView;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MockReceiver;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Owner on 11/7/2016.
 */
public class MidiTest {
  MidiView view = new MidiView();
  MusicEditorModel.Builder b = new MusicEditorModel.Builder();
  IMusicEditorModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), b);
  IMusicEditorModelView modelView = new MusicEditorModelView(model);
  Piece p = new Piece();
  IMusicEditorModel model2 = new MusicEditorModel(p);
  Note n = new Note(Pitch.A, 1, 1, 1);
  Note n1 = new Note(Pitch.C, 2, 2, 2);
  Note n2 = new Note(Pitch.ASHARP, 3, 3, 3);


  public MidiTest() throws FileNotFoundException {
    //not yet implemented.
  }

  @Test
  public void testRefresh() {
    assertEquals(0, this.view.getInstruments().size());
    for (Track t : this.view.getSeq().getTracks()) {
      assertEquals(1, t.size());
    }
    this.view.setModel(this.modelView);
    this.view.refresh();
    assertEquals(1, this.view.getInstruments().size());
    assertEquals(70, this.view.getTrack().size());
  }

  @Test
  public void testMidiNotes() {
    MusicEditorModelView modelView2 = new MusicEditorModelView(model);
    StringBuilder s = new StringBuilder();
    MidiView convenience = new MidiView(s);
    convenience.setModel(modelView2);
    MockReceiver r = null;

    assertEquals(s.toString(), "");
    convenience.refresh();

    for (int i = 0; i < convenience.getTrack().size() - 1; i++) {
      ShortMessage message1 = (ShortMessage) convenience.getTrack().get(i).getMessage();
      s.append("Command " + message1.getCommand());
      s.append("Channel " + message1.getChannel());
      s.append("Data 1 " + message1.getData1());
      s.append("Data 2 " + message1.getData2());
    }

    assertEquals(s.toString(), "Command 192Channel 0Data 1 1Data 2 0Command 144Channel 0Data 1 " +
            "55Data 2 70Command 144Channel 0Data 1 64Data 2 72Command 144Channel 0Data 1 62Data 2" +
            " 72Command 128Channel 0Data 1 64Data 2 72Command 144Channel 0Data 1 60Data 2 " +
            "71Command " +
            "128Channel 0Data 1 62Data 2 72Command 128Channel" +
            " 0Data 1 60Data 2 71Command 144Channel" +
            " 0Data 1 62Data 2 79Command 128Channel 0Data 1 55Data 2 70Command" +
            " 144Channel 0Data 1 55Data 2 79Command 128Channel 0Data 1 62Data 2 " +
            "79Command 144Channel 0Data 1 64Data 2 85Command 144Channel 0Data 1" +
            " 64Data 2 78Command 128Channel 0Data 1 64Data 2 85Command 144Channel" +
            " 0Data 1 64Data 2 74Command 128Channel 0Data 1 64Data 2 78Command " +
            "128Channel 0Data 1 55Data 2 79Command 128Channel 0Data 1 64Data 2 " +
            "74Command 144Channel 0Data 1 55Data 2 77Command 144Channel 0Data 1" +
            " 62Data 2 75Command 144Channel 0Data 1 62Data 2 77Command 128Channel " +
            "0Data 1 62Data 2 75Command 144Channel 0Data 1 62Data 2 75Command 128Channel" +
            " 0Data 1 62Data 2 77Command 144Channel 0Data 1 55Data 2 79Command 128Channel" +
            " 0Data 1 55Data 2 77Command 128Channel 0Data 1 62Data 2 75Command 144Channel " +
            "0Data 1 64Data 2 82Command 128Channel 0Data 1 55Data 2 79Command 128Channel " +
            "0Data 1 64Data 2 82Command 144Channel 0Data 1 67Data 2 84Command 144Channel " +
            "0Data 1 67Data 2 75Command 128Channel 0Data 1 67Data 2 84Command 144Channel " +
            "0Data 1 55Data 2 78Command 144Channel 0Data 1 64Data 2 73Command 128Channel " +
            "0Data 1 67Data 2 75Command 144Channel 0Data 1 62Data 2 69Command 128Channel " +
            "0Data 1 64Data 2 73Command 144Channel 0Data 1 60Data 2 71Command 128Channel " +
            "0Data 1 62Data 2 69Command 128Channel 0Data 1 60Data 2 71Command 144Channel " +
            "0Data 1 62Data 2 80Command 144Channel 0Data 1 55Data 2 79Command 128Channel " +
            "0Data 1 55Data 2 78Command 128Channel 0Data 1 62Data 2 80Command 144Channel " +
            "0Data 1 64Data 2 84Command 144Channel 0Data 1 64Data 2 76Command 128Channel " +
            "0Data 1 64Data 2 84Command 144Channel 0Data 1 64Data 2 74Command 128Channel " +
            "0Data 1 64Data 2 76Command 144Channel 0Data 1 64Data 2 77Command 128Channel " +
            "0Data 1 64Data 2 74Command 144Channel 0Data 1 55Data 2 78Command 128Channel " +
            "0Data 1 55Data 2 79Command 144Channel 0Data 1 62Data 2 75Command 128Channel " +
            "0Data 1 64Data 2 77Command 144Channel 0Data 1 62Data 2 74Command 128Channel " +
            "0Data 1 62Data 2 75Command 128Channel 0Data 1 62Data 2 74Command 144Channel " +
            "0Data 1 64Data 2 81Command 144Channel 0Data 1 62Data 2 70Command 128Channel " +
            "0Data 1 64Data 2 81Command 144Channel 0Data 1 52Data 2 72Command 128Channel " +
            "0Data 1 55Data 2 78Command 144Channel 0Data 1 60Data 2 73Command 128Channel" +
            " 0Data 1 62Data 2 70Command 128Channel 0Data 1 52Data 2 72Command 128Channel " +
            "0Data 1 60Data 2 73");


  }

}
