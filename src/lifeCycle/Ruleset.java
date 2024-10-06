package lifeCycle;

import lifeCycle.buttons.RulesetDropdown;

/**
 * Contains various alternate rulesets for LifeCycle as
 * individual functions. Some well known alternate rules can be found at
 * 'https://conwaylife.com/wiki/List_of_Life-like_rules'
 *
 * Each of these functions should be written to return true or false for one
 * particular cell.
 */

public class Ruleset {

    public static boolean applyCurrentRuleset(Cell cell) {

        String selectedRule = RulesetDropdown.getSelectedRule();

        return switch (selectedRule) {
            case "Conway's Game of Life" -> conwayLife(cell);
            case "Maze" -> maze(cell);
            case "Assimilation" -> assimilation(cell);
            case "Vote" -> vote(cell);
            default -> conwayLife(cell);
        };
    }

    /** RULE COLLECTION ************************************************************************************/
    public static boolean conwayLife(Cell cell) {
        boolean isLiving = cell.isLiving();
        int livingNeighbors = cell.getLivingNeighbors();

        // Underpopulated: cell dies
        if (isLiving && livingNeighbors < 2) {
            return false;
        }
        // Overpopulated: cell dies
        else if (isLiving && livingNeighbors > 3) {
            return false;
        }
        // The cell is empty and population is right: cell born
        else if (!isLiving && livingNeighbors == 3) {
            return true;
        }
        // Otherwise there's no change
        else {
            return isLiving;
        }
    }

    public static boolean maze(Cell cell) {
        boolean isLiving = cell.isLiving();
        int livingNeighbors = cell.getLivingNeighbors();

        // Underpopulated: cell dies
        if (isLiving && livingNeighbors < 1) {
            return false;
        }
        // Overpopulated: cell dies
        else if (isLiving && livingNeighbors > 4) {
            return false;
        }
        // The cell is empty and population is right: cell born
        else if (!isLiving && livingNeighbors == 3) {
            return true;
        }
        // Otherwise there's no change
        else {
            return isLiving;
        }
    }

    public static boolean assimilation(Cell cell) {
        boolean isLiving = cell.isLiving();
        int livingNeighbors = cell.getLivingNeighbors();

        // Underpopulated: cell dies
        if (isLiving && livingNeighbors < 4) {
            return false;
        }
        // Overpopulated: cell dies
        else if (isLiving && livingNeighbors > 7) {
            return false;
        }
        // The cell is empty and population is right: cell born
        else if (!isLiving) {
            if (livingNeighbors == 3 || livingNeighbors == 4 || livingNeighbors == 5) {
                return true;
            }
        }
        // Otherwise there's no change
            return isLiving;
    }

    public static boolean vote(Cell cell) {
        boolean isLiving = cell.isLiving();
        int livingNeighbors = cell.getLivingNeighbors();

        // Underpopulated: cell dies
        if (isLiving && livingNeighbors < 4) {
            return false;
        }
        // The cell is empty and population is right: cell born
        else if (!isLiving && livingNeighbors > 4) {
            return true;
        }
        // Otherwise there's no change
        else {
            return isLiving;
        }
    }



}
