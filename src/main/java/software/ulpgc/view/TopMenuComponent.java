package software.ulpgc.view;

import software.ulpgc.control.Command;

public interface TopMenuComponent {
    void addButton(String label, Command command);
    void setButtonAction(String label, Command command);
}
