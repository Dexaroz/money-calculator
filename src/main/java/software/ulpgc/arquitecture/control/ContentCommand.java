package software.ulpgc.arquitecture.control;

import software.ulpgc.apps.swing.MainFrame;
import software.ulpgc.arquitecture.view.VisualComponent;

public class ContentCommand implements Command {
    private final MainFrame mainFrame;
    private final VisualComponent visualComponent;

    public ContentCommand(MainFrame mainFrame, VisualComponent visualComponent) {
        this.mainFrame = mainFrame;
        this.visualComponent = visualComponent;
    }

    @Override
    public void execute() {
        mainFrame.showContent(visualComponent.getComponent());
    }
}
