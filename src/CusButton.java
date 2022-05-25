import javax.swing.*;


public class CusButton extends JButton {
    private boolean pressed = false;

    public CusButton(Icon icon) {
        super(icon);
    }

    public CusButton(String text) {
        super(text);
    }

    public CusButton(Action a) {
        super(a);
    }

    public CusButton(String text, Icon icon) {
        super(text, icon);
    }

    public boolean isPressed() {
        return pressed;
    }

    public void changePressed() {
        this.pressed = !(this.pressed);
    }
}
