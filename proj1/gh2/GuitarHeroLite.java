package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHeroLite {
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static final int CONCERT_A = 440;
    public static GuitarString[] guitarStrings;

    public GuitarHeroLite(){
        guitarStrings = new GuitarString[37];
        for (int i = 0; i < 37; i++){
            guitarStrings[i] = new GuitarString(getFrequency(i));
        }
    }

    public static int getKeyIndex(char key){
        return keyboard.indexOf(key);
    }

    public static double getFrequency(int index){
        return 440 * Math.pow(2, (index - 24) / 12);
    }

    public static GuitarString keyPlayer(int index){
        return guitarStrings[index];
    }
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        new GuitarHeroLite();
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int indexOfKey = getKeyIndex(key);
                if (indexOfKey != -1){
                    GuitarString string = keyPlayer(indexOfKey);
                    string.pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < 37; i++){
                sample += guitarStrings[i].sample();
            }


            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; i++){
                guitarStrings[i].tic();
            }
        }
    }
}

