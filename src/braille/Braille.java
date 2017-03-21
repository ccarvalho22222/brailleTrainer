/*
Cory Carvalho
Human Computer Interaction HCI Project
Fall 2016
*/

package braille;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Braille {

    JTextArea inputText;
    JTextArea feedbackText;

    // Braille interpreter object
    Interpreter interpreter;
    // Braille speaker object
    Speaker speaker;

    //Records the message returned from Interpreter class.
    String temp;
    //The recorded sentence for playback.
    String sentence = "";
    //Allows number entry to be triggered.
    int number = 0;

    public static void main(String[] args) {
        //Use the event dispatch thread for Swing components
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Braille();
            }
        });
    }

    public Braille() {

        this.interpreter = new Interpreter();
        this.speaker = new Speaker("speakerThread");

        //Set all key input statuses to untouched.
        int[] inputs = new int[6];
        inputs[0] = 0;  // dot 1
        inputs[1] = 0;  // dot 2
        inputs[2] = 0;  // dot 3
        inputs[3] = 0;  // dot 4
        inputs[4] = 0;  // dot 5
        inputs[5] = 0;  // dot 6

        //Creates Jframe.
        JFrame BrailleTrainer = new JFrame();

        //Program exits on closing the window.
        BrailleTrainer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Title of the window.
        BrailleTrainer.setTitle("Braille");
        //Size of the window.
        BrailleTrainer.setSize(300, 300);

        //This will center the JFrame in the middle of the screen
        BrailleTrainer.setLocationRelativeTo(null);

        //This area tells you what characters have been typed
        feedbackText = new JTextArea();
        feedbackText.setEditable(false);
        JScrollPane scrollText = new JScrollPane(feedbackText);

        // Adds key listener to text area
        feedbackText.addKeyListener(new KeyListener() {
            //Here out of neccessity by the KeyListener class.
            @Override
            public void keyTyped(KeyEvent e) {
            }

            //Lets us know which keys a user has pressed but not released yet.
            @Override
            public void keyPressed(KeyEvent e) {
                char p = e.getKeyChar();
                switch (p) {
                    case 'f':
                        inputs[0] = 3;
                        break;
                    case 'd':
                        inputs[1] = 3;
                        break;
                    case 's':
                        inputs[2] = 3;
                        break;
                    case 'j':
                        inputs[3] = 3;
                        break;
                    case 'k':
                        inputs[4] = 3;
                        break;
                    case 'l':
                        inputs[5] = 3;
                        break;
                    default:
                        break;
                }
            }

            //Lets us know which held-down keys have been released.
            @Override
            public void keyReleased(KeyEvent e) {
                char r = e.getKeyChar();
                switch (r) {
                    case 'f':
                        inputs[0] = 1;
                        break;
                    case 'd':
                        inputs[1] = 2;
                        break;
                    case 's':
                        inputs[2] = 4;
                        break;
                    case 'j':
                        inputs[3] = 8;
                        break;
                    case 'k':
                        inputs[4] = 16;
                        break;
                    case 'l':
                        inputs[5] = 32;
                        break;
                    case ' ':
                        sentence = sentence.concat(" ");
                        Speak("space");
                        break;
                    case ';':
                        sentence = sentence.substring(0, sentence.length() - 1);
                        Speak("backspace");
                        number = 0;
                        break;
                    case 'a':
                        Speak(sentence);
                        number = 0;
                        break;
                    default:
                        number = 0;
                        break;
                }

                CalculateInputs(inputs);
            }
        });

        //BrailleTrainer.add(inputText, BorderLayout.NORTH);
        BrailleTrainer.add(scrollText, BorderLayout.CENTER);
        BrailleTrainer.setVisible(true);
    }

    /**
     * Calculate values for inputs for interpreting.
     *
     * @param inputs The array of inputs
     */
    public void CalculateInputs(int[] inputs) {
        //Calculates the combination of braille inputs
        //only when all keys are released. It then adds
        //it to the sentence and speaks the input to the
        //user.
        if (inputs[0] != 3 && inputs[1] != 3 && inputs[2] != 3
                && inputs[3] != 3 && inputs[4] != 3 && inputs[5] != 3) {
            temp = interpreter.returnCharacter(inputs, number);
            switch (temp) {
                case "#":
                    Speak("enter number");
                    number = 64;
                    break;
                case ".":
                    sentence = sentence.concat(temp);
                    feedbackText.setText(sentence);
                    Speak("period");
                    number = 0;
                    break;
                case ":":
                    sentence = sentence.concat(temp);
                    feedbackText.setText(sentence);
                    Speak("colon");
                    number = 0;
                    break;
                case ";":
                    sentence = sentence.concat(temp);
                    feedbackText.setText(sentence);
                    Speak("semi-colon");
                    number = 0;
                    break;
                case "!":
                    sentence = sentence.concat(temp);
                    feedbackText.setText(sentence);
                    Speak("exclamation mark");
                    number = 0;
                    break;
                case "?":
                    sentence = sentence.concat(temp);
                    feedbackText.setText(sentence);
                    Speak("question mark");
                    number = 0;
                    break;
                case ",":
                    sentence = sentence.concat(temp);
                    feedbackText.setText(sentence);
                    Speak("comma");
                    number = 0;
                    break;
                default:
                    sentence = sentence.concat(temp);
                    feedbackText.setText(sentence);
                    Speak(temp);
                    number = 0;
            }
        }
    }

    /**
     * Speak the value passed in.
     *
     * @param value
     */
    private void Speak(String value) {
        speaker.AddSentence(value);
        speaker.start();
    }
}
