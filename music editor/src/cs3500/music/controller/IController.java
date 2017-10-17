package cs3500.music.controller;

import cs3500.music.view.KeyboardHandler;

/**
 * interface for a controller.
 */
public interface IController {


  /**
   * starts the program.
   */
  void start();

  /**
   * gets the controller's keyboard handler.
   * @return the keyboard handler.
   */
  KeyboardHandler getKbHandler();

}





