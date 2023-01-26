import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    DemoPanel dp;

    public KeyHandler(DemoPanel dp) {
        this.dp = dp;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == KeyEvent.VK_ENTER) {
            dp.autoSearch();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
