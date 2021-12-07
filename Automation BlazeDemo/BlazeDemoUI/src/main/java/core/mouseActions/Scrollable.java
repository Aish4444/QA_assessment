package core.mouseActions;

import core.PageObject;

public interface Scrollable {
    PageObject scrollPageUp(int pixels);

    PageObject scrollPageDown(int pixels);
}
