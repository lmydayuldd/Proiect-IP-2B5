package DataRepresentationBackend.Logic.Representations;

/**
 * @author Procop Vladimir
 */
public class Window extends Wall {
    public Window(Point lpoint, Point rpoint) {
        super(lpoint, rpoint);
    }

    public Window(Window toCopy) {
        super(toCopy);
    }
}