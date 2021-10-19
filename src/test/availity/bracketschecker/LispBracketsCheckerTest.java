package availity.bracketschecker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LispBracketsCheckerTest {

    @Test
    void isBracketsBalanced() {
        assertTrue(LispBracketsChecker.isBracketsBalanced("(list 1 2 3)"));
        assertTrue(LispBracketsChecker.isBracketsBalanced("(list 1 2 (quote foo))"));
        assertFalse(LispBracketsChecker.isBracketsBalanced("((+ 2 2 )"));
    }
}