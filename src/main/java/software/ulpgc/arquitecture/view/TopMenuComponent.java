package software.ulpgc.arquitecture.view;

import software.ulpgc.arquitecture.control.Command;

public interface TopMenuComponent {
    void addButton(String label, Command command);
    void setButtonAction(String label, Command command);
}
