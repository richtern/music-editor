README for music editor.

Changes from hwk 5-6: added getters and setters to most of the classes so that our ModelView could access some of the fields (but not edit them).

Our design stayed pretty similar to Luke’s model with a Model which has a piece and a builder class for reading the file.

A piece which has a list of notes, and a measure. We added an interface IGuiView that was implemented by our combo view.

A note has an int Start time, beats, int octave, Pitch pitch, int instrument, and int volume.

I had an enum for pitch which also had a toString() method. I chose this design because I thought it would be most simple to just have an array list of notes that would be simple to manage and keep track of (especially since when I add note they are added in a sorted manner).

we then this week added a controller and several views including GUI, console, and MIDI, the views either take a ModelView (which we also added. This was similar to a model, but it surpassed mutating methods) or a copy of a piece.

We created a new interface IModelViewEditor to represent an interface for our ModelViewEditor. Our ModelViewEditor is what gets passed to our views from our controller so that our view can see the data stored there but it cannot change it.

Finally we created a factory class to determine what view to display and what file to use depending on the input from the user.

We tried to test using mock objects but the application would close before we could get the information from the synthesizer.

That is why we still have all of our mock classes.

MORE CHANGES: HW7

We added a bunch of methods in the gui and midi view to handle ticks from a timer to be able to refresh and redraw.

We added a keyboard handler to handle our key events based on when the buttons were released.

We added a class "ComboView" to link our midi and gui views together.

We added a method in MidiView that adds MetaMessages to the track.

HOW TO USE:

Press A to add a note. Follow pop up instructions.

Press R to add a note. Follow pop up instructions.

Press Spacebar to pause/play.

Press backspace to restart the piece.

Use the arrow keys to scroll through the piece.

When the red bar reaches the end of a frame it should automatically scroll to rhe right.

When you hit restart the scrolling will go back to the origin.

When you add or remove a note the piece will restart.




