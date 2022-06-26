package org.wadhome.digraph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ApplicationTest {

    @Test
    public void testLoadingFile() {
        Application.main(new File("src/test/resources/sample.csv").getAbsolutePath());
        Assertions.assertEquals("""
                Directed Graph Experiment
                A:B5
                B:C11
                C:A3
                X:X11

                """, Application.outputCache.toString());
    }
}
