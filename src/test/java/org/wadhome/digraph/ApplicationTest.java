package org.wadhome.digraph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wadhome.digraph.setup.Output;
import org.wadhome.digraph.setup.Request;

import java.io.File;

public class ApplicationTest {

    @Test
    public void testLoadingFile() {
        Application.main(
                new File("src/test/resources/sample.csv").getAbsolutePath(),
                Request.LoadAndDisplay.getMenuIndicator());
        Assertions.assertEquals("""
                A:B5,C77
                B:C11
                C:A3
                X:X11

                """, Output.getCacheValue());
    }
}
