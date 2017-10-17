package cs3500.music.model;


/**
 * Interface to represent a Music editor Model.
 */
public interface IMusicEditorModel<K> {

  /**
   * prints out the state of the piece.
   *
   * @param p the piece to be printed.
   * @return the string containing the printed data.
   */
  String display(Piece p);

  /**
   * replaces note 1 with note 2.
   *
   * @param n1 the note to be removed.
   * @param n2 the note to be added.
   */
  void edit(Note n1, Note n2);

  /**
   * adds a note to a piece.
   *
   * @param note the note to be added to the piece.
   */
  void add(Note note);

  /**
   * removes a node from a piece of cs3500.music.
   *
   * @param note the note to be removed from the piece.
   */
  void remove(Note note);

  /**
   * Combines 2 pieces together one after the other.
   *
   * @param p1 the first piece.
   * @param p2 the second piece.
   */
  void combineConsec(Piece p1, Piece p2);

  /**
   * combines two pieces at the same time.
   *
   * @param p1 one of the pieces.
   * @param p2 the one to be laid over it.
   */
  void combineSimul(Piece p1, Piece p2);

  /**
   * gets the piece from the model.
   *
   * @return the piece.
   */
  Piece getPiece();

  /**
   * sets the tempo.
   *
   * @param t the tempo.
   */
  void setTempoModel(int t);

  /**
   * gets the tempo of the piece.
   *
   * @return the tempo.
   */
  int getTempo();

}
