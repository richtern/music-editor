/**
 * Created by lbakker on 11/21/16.
 */

import org.junit.Test;


import java.awt.event.KeyEvent;

import cs3500.music.controller.IMockRunnable;
import cs3500.music.controller.MusicController;
import cs3500.music.controller.MockRunnable;
import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.Pitch;
import cs3500.music.view.ComboView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.KeyboardHandler;
import cs3500.music.view.MidiView;

import static junit.framework.TestCase.assertEquals;

/**
 * tests for the comboviewController.
 */
public class ComboControllerTest {
  MidiView midi = new MidiView();
  GuiViewFrame gui = new GuiViewFrame();
  ComboView view = new ComboView(midi, gui);
  IMusicEditorModel model = new MusicEditorModel(new Piece());
  MusicController controller = new MusicController(model, view);
  KeyboardHandler handler = controller.getKbHandler();
  IMockRunnable mock = new MockRunnable();

  KeyEvent e = new KeyEvent(view, 1, 1, 1, KeyEvent.VK_M, 'm');

  //tests to see if our key handlers work.
  @Test
  public void testMethod() {
    handler.addReleasedRunnable(KeyEvent.VK_M, mock);

    handler.keyReleased(e);
    assertEquals(mock.getString(), "M key released!");
  }

  //tests the public methods in the controller class.
  @Test
  public void testContoller() {
    assertEquals(this.controller.getKbHandler(), this.handler);
    assertEquals(this.midi.getTrack().size(), 1);
    this.model.add(new Note(1, 1, Pitch.A, 1, 1, 1));
    assertEquals(this.midi.getTrack().size(), 1);
    this.controller.start();
    assertEquals(this.midi.getTrack().size(), 4);
  }


}
