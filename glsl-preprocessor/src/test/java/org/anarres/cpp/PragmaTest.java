/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.anarres.cpp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.slf4j.*;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 *
 * @author shevek
 */
public class PragmaTest {
	private static final Logger LOG = LoggerFactory.getLogger(PragmaTest.class);

	@Test
	public void testPragma() throws Exception {
		File file = new File("build/resources/test/pragma.c");
		assertTrue(file.exists());

		// create a CharSource from a file
		Preprocessor pp = new Preprocessor(Files.newReader(file, Charsets.UTF_8));
		pp.setListener(new DefaultPreprocessorListener());
		String output = pp.printToString();
		pp.close();
		LOG.info("Output: " + output);
		// assertTrue(output.contains("absolute-result"));
	}
}
