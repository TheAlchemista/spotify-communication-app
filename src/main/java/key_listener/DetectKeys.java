package key_listener;

// Java imports
import java.util.logging.Level;

// Key Logger imports
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Local imports
import key_listener.DetectKeysRunMethods;


public class DetectKeys implements NativeKeyListener {

    private DetectKeysRunMethods detectKeysRunMethods;
    private final Logger logger = LoggerFactory.getLogger(DetectKeys.class);

    public DetectKeys(DetectKeysRunMethods detectKeysRunMethods) {
        this.detectKeysRunMethods = detectKeysRunMethods;
        init();
    }

    public void run(DetectKeysRunMethods detectKeysRunMethods) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println("There was a problem registering the native hook.");
            System.out.println(e.getMessage());
        }

        GlobalScreen.addNativeKeyListener(new DetectKeys(detectKeysRunMethods));
    }

    // Sets the logger messages to no longer showing
    private void init() {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        logger.setUseParentHandlers(false);
    }

    public void nativeKeyPressed(NativeKeyEvent event) {
        detectKeysRunMethods.chooseAndRun(event);
    }

    public void nativeKeyTyped(NativeKeyEvent event) {
        // Not used
    }

    public void nativeKeyReleased(NativeKeyEvent event) {
        // Not used
    }

}
