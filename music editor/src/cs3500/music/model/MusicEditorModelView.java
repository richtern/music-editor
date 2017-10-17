package cs3500.music.model;


/**
 * class to be passed to a view.
 * does not allow mutation.
 */
public class MusicEditorModelView implements IMusicEditorModelView {
  private final Piece p;
  private IMusicEditorModel model;

  /**
   * constructor for a music editor model view.
   */
  public MusicEditorModelView(IMusicEditorModel model) {
    this.model = model;
    this.p = model.getPiece();
  }

  /**
   * displays the piece as a string.
   *
   * @param p the piece to be printed.
   */
  @Override
  public String display(Piece p) {
    return p.displayPiece();
  }

  /**
   * gets the piece and returns it.
   *
   * @return this piece.
   */
  @Override
  public Piece getPiece() {
    return new Piece(p.getPiece(), p.getMeasure());
  }

  /**
   * gets the Tempo and returns it.
   *
   * @return the tempo of this piece.
   */
  @Override
  public int getTempo() {
    return this.model.getTempo();
  }


}
