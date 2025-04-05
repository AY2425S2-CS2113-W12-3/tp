package seedu.fintrack.commands;

import seedu.fintrack.utils.FinTrackException;

public interface Command {
    void execute() throws FinTrackException;
}
