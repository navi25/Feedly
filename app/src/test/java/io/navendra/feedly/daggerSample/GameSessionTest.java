package io.navendra.feedly.daggerSample;

import org.junit.Assert;
import org.junit.Test;

public class GameSessionTest {

    @Test
    void testGameSession(){
        GameSession session = new GameSession();
        Assert.assertEquals("Navi",session.gameData.user);
    }
}
