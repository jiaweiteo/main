package seedu.algobase.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.algobase.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.algobase.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.algobase.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.algobase.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.algobase.testutil.TypicalProblemSearchRules.getTypicalAlgoBase;

import org.junit.jupiter.api.Test;

import seedu.algobase.commons.core.Messages;
import seedu.algobase.commons.core.index.Index;
import seedu.algobase.model.Model;
import seedu.algobase.model.ModelManager;
import seedu.algobase.model.UserPrefs;
import seedu.algobase.model.searchrule.problemsearchrule.ProblemSearchRule;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteFindRuleCommand}.
 */
class DeleteFindRuleCommandTest {

    private Model model = new ModelManager(getTypicalAlgoBase(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        ProblemSearchRule ruleToDelete = model.getFilteredFindRuleList().get(INDEX_FIRST.getZeroBased());
        DeleteFindRuleCommand command = new DeleteFindRuleCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteFindRuleCommand.MESSAGE_SUCCESS, ruleToDelete);

        ModelManager expectedModel = new ModelManager(model.getAlgoBase(), new UserPrefs());
        expectedModel.deleteFindRule(ruleToDelete);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFindRuleList().size() + 1);
        DeleteFindRuleCommand command = new DeleteFindRuleCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_FIND_RULE_DISPLAYED_INDEX);
    }


    @Test
    void equals() {
        DeleteFindRuleCommand deleteFirstCommand = new DeleteFindRuleCommand(INDEX_FIRST);
        DeleteFindRuleCommand deleteSecondCommand = new DeleteFindRuleCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteFindRuleCommand deleteFirstCommandCopy = new DeleteFindRuleCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
