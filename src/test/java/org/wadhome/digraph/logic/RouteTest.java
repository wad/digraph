package org.wadhome.digraph.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RouteTest {

    @Test
    public void testIt() {
        Route route = new Route();
        Assertions.assertTrue(route.isEmpty());
    }

}
