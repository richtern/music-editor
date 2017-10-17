import org.junit.Test;


import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.Pitch;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Created by lbakker on 10/19/16.
 */
public class MusicEditorModelTest {
  Piece p = new Piece();
  Piece p2 = new Piece();
  MusicEditorModel model = new MusicEditorModel(p);
  MusicEditorModel model2 = new MusicEditorModel(p2);

  @Test
  public void testAddNote() {
    assertEquals(p.getPiece().size(), 0);
    model.add(new Note(Pitch.A, 1, 1, 1));
    assertEquals(p.getPiece().size(), 1);
  }

  //also tests display.
  @Test
  public void testEditNote() {
    Note n1 = new Note(Pitch.C, 1, 1, 1);
    Note n2 = new Note(Pitch.CSHARP, 1, 1, 1);
    model.add(n1);
    assertEquals(model.display(p), "   C1 \n" +
            "1  X  \n" +
            "2  |  \n");
    model.edit(n1, n2);
    assertNotEquals(model.display(p), "   C1 \n" +
            "1  X  \n" +
            "2  |  \n");
    assertEquals(model.display(p), "  C#1 \n" +
            "1  X  \n" +
            "2  |  \n");
  }


  @Test
  public void testCombineSimul() {
    model.add(new Note(Pitch.A, 1, 1, 1));
    model.add(new Note(Pitch.B, 1, 2, 2));
    model.add(new Note(Pitch.A, 2, 1, 1));
    model2.add(new Note(Pitch.C, 1, 1, 1));
    model.add(new Note(Pitch.CSHARP, 1, 4, 1));
    model.combineSimul(p, p2);
    assertEquals(model.getPiece().displayPiece(), "   C1  C#1   D1  D#1   E1   F1  F#1  " +
            " G1  G#1   A1  A#1   B1   C2  C#2   D2  D#2   E2   F2  F#2   G2  G#2   A2 \n" +
            "1  X    X                                       X                           " +
            "                                X  \n" +
            "2  |    |                                       |         X                   " +
            "                              |  \n" +
            "3       |                                                 |                   " +
            "                                 \n" +
            "4       |                                                 |                    " +
            "                                \n" +
            "5       |                                                                      " +
            "                                \n");
  }

  @Test
  public void testCombineConsec() {
    model.add(new Note(Pitch.A, 1, 1, 1));
    model.add(new Note(Pitch.B, 1, 2, 2));
    model.add(new Note(Pitch.A, 1, 1, 2));
    model2.add(new Note(Pitch.C, 1, 1, 1));
    model2.add(new Note(Pitch.CSHARP, 1, 4, 3));
    model.combineConsec(p, p2);
    assertEquals(model.getPiece().displayPiece(), "  " +
            "  C1  C#1   D1  D#1   E1   F1  F#1   G1  G#1  " +
            " A1  A#1   B1 \n" +
            "1                                                X            \n" +
            "2                                                X    |         X  \n" +
            "3                                                |         |  \n" +
            "4                                                          |  \n" +
            "5   X                                                         \n" +
            "6   |                                                         \n" +
            "7        X                                                    \n" +
            "8        |                                                    \n" +
            "9        |                                                    \n" +
            "10       |                                                    \n" +
            "11       |                                                    \n");
  }

  @Test
  public void testRemoveNote() {

    model.add(new Note(Pitch.A, 1, 1, 1));
    Note n = new Note(Pitch.B, 2, 2, 2);
    model.add(n);
    model.remove(n);
    assertEquals(p.getPiece().size(), 1);
  }

  @Test
  public void testPrint() {
    model.add(new Note(Pitch.DSHARP, 1, 1, 1));
    model.add(new Note(Pitch.G, 1, 2, 2));
    model.add(new Note(Pitch.GSHARP, 1, 2, 7));
    model.add(new Note(Pitch.A, 1, 2, 8));
    assertEquals(model.display(p),
            "  D#1   E1   F1  F#1   G1  G#1   A1 \n" +
                    "1  X                                \n" +
                    "2  |                   X            \n" +
                    "3                      |            \n" +
                    "4                      |            \n" +
                    "5                                   \n" +
                    "6                                   \n" +
                    "7                           X       \n" +
                    "8                           |    X  \n" +
                    "9                           |    |  \n" +
                    "10                                |  \n");
  }

  @Test
  public void testPrintLongBeats() {
    model.add(new Note(Pitch.C, 1, 99, 1));
    model.add(new Note(Pitch.CSHARP, 1, 2, 2));
    model.add(new Note(Pitch.GSHARP, 2, 2, 7));
    model.add(new Note(Pitch.A, 1, 80, 8));
    model.add(new Note(Pitch.A, 1, 2, 110));
    model.add(new Note(Pitch.ASHARP, 3, 2, 42));
    model.add(new Note(Pitch.B, 1, 2, 8));
    model.add(new Note(Pitch.GSHARP, 2, 2, 73));
    model.add(new Note(Pitch.D, 1, 2, 12));
    assertEquals(model.display(p), "     C1  C#1   D1  D#1   E1   F1  F#1   G1  G#1   A1  A#1 " +
            "  B1 \n" +
            "1    X                                                         \n" +
            "2    |    X                                                    \n" +
            "3    |    |                                                    \n" +
            "4    |    |                                                    \n" +
            "5    |                                                         \n" +
            "6    |                                                         \n" +
            "7    |                                                         \n" +
            "8    |                                            X         X  \n" +
            "9    |                                            |         |  \n" +
            "10   |                                            |         |  \n" +
            "11   |                                            |            \n" +
            "12   |         X                                  |            \n" +
            "13   |         |                                  |            \n" +
            "14   |         |                                  |            \n" +
            "15   |                                            |            \n" +
            "16   |                                            |            \n" +
            "17   |                                            |            \n" +
            "18   |                                            |            \n" +
            "19   |                                            |            \n" +
            "20   |                                            |            \n" +
            "21   |                                            |            \n" +
            "22   |                                            |            \n" +
            "23   |                                            |            \n" +
            "24   |                                            |            \n" +
            "25   |                                            |            \n" +
            "26   |                                            |            \n" +
            "27   |                                            |            \n" +
            "28   |                                            |            \n" +
            "29   |                                            |            \n" +
            "30   |                                            |            \n" +
            "31   |                                            |            \n" +
            "32   |                                            |            \n" +
            "33   |                                            |            \n" +
            "34   |                                            |            \n" +
            "35   |                                            |            \n" +
            "36   |                                            |            \n" +
            "37   |                                            |            \n" +
            "38   |                                            |            \n" +
            "39   |                                            |            \n" +
            "40   |                                            |            \n" +
            "41   |                                            |            \n" +
            "42   |                                            |            \n" +
            "43   |                                            |            \n" +
            "44   |                                            |            \n" +
            "45   |                                            |            \n" +
            "46   |                                            |            \n" +
            "47   |                                            |            \n" +
            "48   |                                            |            \n" +
            "49   |                                            |            \n" +
            "50   |                                            |            \n" +
            "51   |                                            |            \n" +
            "52   |                                            |            \n" +
            "53   |                                            |            \n" +
            "54   |                                            |            \n" +
            "55   |                                            |            \n" +
            "56   |                                            |            \n" +
            "57   |                                            |            \n" +
            "58   |                                            |            \n" +
            "59   |                                            |            \n" +
            "60   |                                            |            \n" +
            "61   |                                            |            \n" +
            "62   |                                            |            \n" +
            "63   |                                            |            \n" +
            "64   |                                            |            \n" +
            "65   |                                            |            \n" +
            "66   |                                            |            \n" +
            "67   |                                            |            \n" +
            "68   |                                            |            \n" +
            "69   |                                            |            \n" +
            "70   |                                            |            \n" +
            "71   |                                            |            \n" +
            "72   |                                            |            \n" +
            "73   |                                            |            \n" +
            "74   |                                            |            \n" +
            "75   |                                            |            \n" +
            "76   |                                            |            \n" +
            "77   |                                            |            \n" +
            "78   |                                            |            \n" +
            "79   |                                            |            \n" +
            "80   |                                            |            \n" +
            "81   |                                            |            \n" +
            "82   |                                            |            \n" +
            "83   |                                            |            \n" +
            "84   |                                            |            \n" +
            "85   |                                            |            \n" +
            "86   |                                            |            \n" +
            "87   |                                            |            \n" +
            "88   |                                            |            \n" +
            "89   |                                                         \n" +
            "90   |                                                         \n" +
            "91   |                                                         \n" +
            "92   |                                                         \n" +
            "93   |                                                         \n" +
            "94   |                                                         \n" +
            "95   |                                                         \n" +
            "96   |                                                         \n" +
            "97   |                                                         \n" +
            "98   |                                                         \n" +
            "99   |                                                         \n" +
            "100  |                                                         \n" +
            "101                                                            \n" +
            "102                                                            \n" +
            "103                                                            \n" +
            "104                                                            \n" +
            "105                                                            \n" +
            "106                                                            \n" +
            "107                                                            \n" +
            "108                                                            \n" +
            "109                                                            \n" +
            "110                                               X            \n" +
            "111                                               |            \n" +
            "112                                               |            \n");
  }

}