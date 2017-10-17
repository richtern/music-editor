package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

/**
 * Class for a music editor model.
 */
public class MusicEditorModel implements IMusicEditorModel<Note> {

  /**
   * a builder class to build a piece from a file.
   */
  public static final class Builder implements CompositionBuilder<IMusicEditorModel<Note>> {

    Piece p1 = new Piece();
    IMusicEditorModel model = new MusicEditorModel(p1);


    /**
     * returns the actual musiceditormodel with the piece from the file.
     *
     * @return the model with a correct piece.
     */
    public IMusicEditorModel build() {
      return this.model;
    }

    /**
     * @param start      The start time of the note, in beats
     * @param end        The end time of the note, in beats
     * @param instrument The instrument number (to be interpreted by MIDI)
     * @param pitch      The pitch (in the range [0, 127], where 60 represents C4, the middle-C on a
     *                   piano)
     * @param volume     The volume (in the range [0, 127])
     * @return a new note added.
     */
    public CompositionBuilder<IMusicEditorModel<Note>> addNote(int start, int end, int instrument,
                                                               int pitch, int volume) {

      this.model.add(new Note(instrument, volume, Piece.numToPitch(pitch % 12),
              pitch / 12, end - start, start));
      return this;
    }

    /**
     * @param tempo The speed, in microseconds per beat
     * @return the piece with a new tempo.
     */
    public CompositionBuilder<IMusicEditorModel<Note>> setTempo(int tempo) {
      this.model.setTempoModel(tempo);
      return this;
    }
  }


  /**
   * the piece.
   */
  private Piece p;
  private int tempo;


  /**
   * constructor for a cs3500.music editor.
   *
   * @param p the piece of cs3500.music.
   */
  public MusicEditorModel(Piece p) {
    this.p = p;
  }

  /**
   * displays the piece as a string.
   *
   * @param p the piece to be printed.
   * @return a string showing all the notes.
   */
  public String display(Piece p) {
    return p.displayPiece();
  }

  /**
   * replaces n1 with n2
   *
   * @param n1 the note to be removed.
   * @param n2 the note to be added.
   */
  public void edit(Note n1, Note n2) {
    p.editPiece(n1, n2);
  }

  /**
   * adds the note to the piece.
   *
   * @param note the note to be added to the piece.
   */
  public void add(Note note) {
    p = p.addNote(note);
  }

  /**
   * removes the note from the piece.
   *
   * @param note the note to be removed from the piece.
   */
  public void remove(Note note) {
    p = p.remove(note);
  }

  /**
   * combines two pieces one after the other.
   *
   * @param p1 the first piece.
   * @param p2 the second piece.
   */
  public void combineConsec(Piece p1, Piece p2) {
    p = p1.combinePieceConsec(p2);
  }

  /**
   * combines 2 pieces together.
   *
   * @param p1 one of the pieces.
   * @param p2 the one to be laid over it.
   */
  public void combineSimul(Piece p1, Piece p2) {
    p = p1.combinePieceSimul(p2);
  }

  /**
   * gets the piece.
   *
   * @return the piece in the model.
   */
  public Piece getPiece() {
    return this.p;
  }

  /**
   * sets the tempo.
   *
   * @param t the tempo.
   */
  public void setTempoModel(int t) {
    this.tempo = t;
  }

  /**
   * gets the tempo and returns it.
   *
   * @return the tempo.
   */
  public int getTempo() {
    return this.tempo;
  }


}
