package cs3500.music;

/**
 * Created by lbakker on 10/31/16.
 */


import cs3500.music.controller.IController;
import cs3500.music.controller.MusicController;
import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ComboView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiView;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;


/**
 * music editor class (runs the views).
 */
public final class MusicEditor {
  /**
   * runs the entire program.
   *
   * @param args does nothing.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    String fileName = args[0];
    // would need for previous assignment : String viewName = args[1];
    MusicEditorModel.Builder b = new MusicEditorModel.Builder();
    IMusicEditorModel model = MusicReader.parseFile(new FileReader(fileName), b);
    IController controller = new MusicController(model, new ComboView(new MidiView(),
            new GuiViewFrame()));
    controller.start();
  }
}
