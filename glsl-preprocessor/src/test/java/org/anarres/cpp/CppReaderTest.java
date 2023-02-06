package org.anarres.cpp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.NonNull;

public class CppReaderTest {

	public static String testCppReader(@NonNull String in, Feature... f)
			throws Exception {
		System.out.println("Testing " + in);
		StringReader r = new StringReader(in);
		CppReader p = new CppReader(r);
		p.getPreprocessor().setSystemIncludePath(
				Collections.singletonList("src/test/resources"));
		p.getPreprocessor().addFeatures(f);
		BufferedReader b = new BufferedReader(p);

		StringBuilder out = new StringBuilder();
		String line;
		while ((line = b.readLine()) != null) {
			System.out.println(" >> " + line);
			out.append(line).append("\n");
		}

		return out.toString();
	}

	@Test
	public void testCppReader()
			throws Exception {
		testCppReader("#include <test0.h>\n", Feature.LINEMARKERS);
	}

	@Test
	public void testVarargs()
			throws Exception {
		// The newlines are irrelevant, We want exactly one "foo"
		testCppReader("#include <varargs.c>\n");
	}

	@Test
	public void testPragmaOnce()
			throws Exception {
		// The newlines are irrelevant, We want exactly one "foo"
		String out = testCppReader("#include <once.c>\n", Feature.PRAGMA_ONCE);
		assertEquals("foo", out.trim());
	}

	@Test
	public void testPragmaOnceWithMarkers()
			throws Exception {
		// The newlines are irrelevant, We want exactly one "foo"
		testCppReader("#include <once.c>\n", Feature.PRAGMA_ONCE, Feature.LINEMARKERS);
	}
}
