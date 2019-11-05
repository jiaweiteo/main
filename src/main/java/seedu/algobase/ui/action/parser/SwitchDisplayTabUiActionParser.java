package seedu.algobase.ui.action.parser;

import static seedu.algobase.commons.core.Messages.MESSAGE_UNKNOWN_UI_ACTION_PROPERTY;

import seedu.algobase.commons.core.index.Index;
import seedu.algobase.logic.parser.exceptions.ParseException;
import seedu.algobase.ui.action.UiActionDetails;
import seedu.algobase.ui.action.UiParser;
import seedu.algobase.ui.action.actions.SwitchDisplayTabUiAction;

/**
 * UI Parser for the Switch Display Tab Action.
 */
public class SwitchDisplayTabUiActionParser implements UiParser<SwitchDisplayTabUiAction> {

    @Override
    public SwitchDisplayTabUiAction parse(UiActionDetails uiActionDetails) throws ParseException {
        Index index = parseIndex(uiActionDetails.get(0));
        return new SwitchDisplayTabUiAction(index);
    }

    /**
     * Converts an index of type {@Object} into an id of type {@Index}
     *
     * @throws ParseException if given object is not of type {@Index}
     */
    private Index parseIndex(Object index) throws ParseException {
        if (!(index instanceof Index)) {
            throw new ParseException(MESSAGE_UNKNOWN_UI_ACTION_PROPERTY);
        }

        return (Index) index;
    }
}