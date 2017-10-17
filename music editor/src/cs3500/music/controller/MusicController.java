package cs3500.music.controller;


import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.IMusicEditorModelView;
import cs3500.music.model.MusicEditorModelView;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.Pitch;
import cs3500.music.view.IGuiView;
import cs3500.music.view.KeyboardHandler;


/**
 * the music controller.
 */
public class MusicController implements IController {

  private IGuiView view;
  private IMusicEditorModelView modelView;
  private IMusicEditorModel model;
  private KeyboardHandler handler;

  /**
   * contructor for a music controller.
   */
  public MusicController(IMusicEditorModel model, IGuiView view) {
    this.model = model;
    this.view = view;
    this.modelView = new MusicEditorModelView(this.model);
    this.handler = new KeyboardHandler();
    this.addHandlers();
    this.view.setListeners(null, handler);
  }

  /**
   * this controllers implementation of the 'go' method.
   */
  public void start() {
    this.view.setModel(this.modelView);
    this.view.refresh();
    this.view.makeVisible();
  }

  /**
   * gets this controller's handler.
   *
   * @return the keyboard handler.
   */
  public KeyboardHandler getKbHandler() {
    return this.handler;
  }


  /**
   * adds handlers to the controller for all the key commands.
   */
  private void addHandlers() {
    this.handler.addReleasedRunnable(KeyEvent.VK_A, new AddNote());
    this.handler.addReleasedRunnable(KeyEvent.VK_R, new RemoveNote());
    this.handler.addReleasedRunnable(KeyEvent.VK_LEFT, new ScrollLeft());
    this.handler.addReleasedRunnable(KeyEvent.VK_UP, new ScrollUp());
    this.handler.addReleasedRunnable(KeyEvent.VK_RIGHT, new ScrollRight());
    this.handler.addReleasedRunnable(KeyEvent.VK_DOWN, new ScrollDown());
    this.handler.addReleasedRunnable(KeyEvent.VK_SPACE, new Pause());
    this.handler.addReleasedRunnable(KeyEvent.VK_BACK_SPACE, new Restart());
  }

  /**
   * class to add a note from the view.
   */
  private class AddNote implements Runnable {

    @Override
    public void run() {

      JFrame frame2 = new JFrame("");
      String s = JOptionPane.showInputDialog(frame2, "Enter your input in the form " +
              ":Instrument :" +
              " Volume : Pitch : Octave : Beats : Start Time", JOptionPane.PLAIN_MESSAGE);


      Scanner scan = new Scanner(s);
      int instrument = scan.nextInt();
      int volume = scan.nextInt();
      int pitch = scan.nextInt();
      int octave = scan.nextInt();
      int beats = scan.nextInt();
      int startTime = scan.nextInt();

      Pitch actualPitch = Piece.numToPitch(pitch);
      model.add(new Note(instrument, volume, actualPitch, octave, beats, startTime));
      modelView = new MusicEditorModelView(model);
      start();
      view.restart();
    }
  }

  /**
   * class to remove a note from the view.
   */
  private class RemoveNote implements Runnable {


    @Override
    public void run() {
      JFrame frame2 = new JFrame("");
      String s = JOptionPane.showInputDialog(frame2, "Enter your input in the form " +
              ":Instrument :" +
              " Volume : Pitch : Octave : Beats : Start Time", JOptionPane.PLAIN_MESSAGE);
      Scanner scan = new Scanner(s);
      int instrument = scan.nextInt();
      int volume = scan.nextInt();
      int pitch = scan.nextInt();
      int octave = scan.nextInt();
      int beats = scan.nextInt();
      int startTime = scan.nextInt();
      Pitch actualPitch = Piece.numToPitch(pitch);
      model.remove(new Note(instrument, volume, actualPitch, octave, beats, startTime));
      modelView = new MusicEditorModelView(model);
      start();
      view.restart();
    }
  }

  /**
   * class to react to left arrow being pressed.
   */
  private class ScrollLeft implements Runnable {


    @Override
    public void run() {
      view.editXScroll(-1);
      view.refreshGui();
    }
  }

  /**
   * class to react to right arrow being pressed.
   */
  private class ScrollRight implements Runnable {


    @Override
    public void run() {
      view.editXScroll(1);
      view.refreshGui();
    }
  }

  /**
   * class to react to up arrow being pressed.
   */
  private class ScrollUp implements Runnable {


    @Override
    public void run() {
      view.editYScroll(-1);
      view.refreshGui();
    }
  }

  /**
   * class to react to down arrow being pressed.
   */
  private class ScrollDown implements Runnable {


    @Override
    public void run() {
      view.editYScroll(1);
      view.refreshGui();
    }
  }

  private class Pause implements Runnable {

    @Override
    public void run() {
      view.pause();
    }
  }

  private class Restart implements Runnable {

    @Override
    public void run() {
      view.restart();
    }
  }


}
