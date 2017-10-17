package cs3500.music;

/**
 * Created by lbakker on 11/7/16.
 */

import cs3500.music.view.ConsoleGuiViewPanel;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.IView;
import cs3500.music.view.MidiView;

/**
 * view factory for choosing the view.
 */
public class ViewFactory {

  /**
   * chooses the view.
   *
   * @param name the name of the view.
   * @return the corresponding view.
   */
  public static IView create(String name) {
    switch (name) {
      case "console":
        return new ConsoleGuiViewPanel(System.out);
      case "visual":
        return new GuiViewFrame();
      case "midi":
        return new MidiView();
      default:
        throw new IllegalArgumentException("wrong input");
    }
  }
}
