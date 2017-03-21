/*
Cory Carvalho
Human Computer Interaction HCI Project
Fall 2016
*/

package braille;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.Stack;

/**
 *
 * @author jeffp_000
 */
public class Speaker implements Runnable {

    private Voice voice;
    private VoiceManager vm;
    private Stack<String> sentence;

    private Thread t;
    private String threadName;

    public Speaker(String name) {
        threadName = name;
        sentence = new Stack<>();
        vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin16");
        voice.allocate();
    }

    /**
     * Add a sentence to the reading queue
     *
     * @param value
     */
    public void AddSentence(String value) {
        sentence.push(value);
    }

    public void run() {
        // read sentences while they exist in the queue
        while (!sentence.empty()) {
            voice.speak(sentence.pop());
        }
        t = null;
    }

    public void start() {
        // create a new thread to speak
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
