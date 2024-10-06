package lifeCycle;

/**
 * Contains various alternate rulesets for Conways Game Of Life as
 * individual functions. Some well known alternate rules can be found at
 * 'https://conwaylife.com/wiki/List_of_Life-like_rules'
 *
 * Each of these functions should be written to return true or false for one
 * particular cell.
 */

public class RuleSet {
    public static boolean classicLife(Cell cell) {
        boolean isLiving = cell.isLiving();
        int livingNeighbors = cell.getLivingNeighbors();

        // Underpopulated: cell dies
        if (isLiving && livingNeighbors < 2){
            return false;
        }
        // Overpopulated: cell dies
        else if (isLiving && livingNeighbors > 3){
            return false;
        }
        // The cell is empty and population is right: cell born
        else if (!isLiving && livingNeighbors == 3){
            return true;
        }
        // Otherwise there's no change
        else {
            return isLiving;
        }
    }
}
