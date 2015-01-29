package com.evrythng.evrythngtools;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class ConsoleUtils {

	/**
	 * @param table Must be rectangular.
	 */
	public static void printTable(final PrintStream ps, final List<List<String>> table) {

		List<Integer> columnWidth = new ArrayList<>();

		for (List<String> row : table) {
			int i = 0;
			for (String cell : row) {
				if (i >= columnWidth.size()) {
					columnWidth.add(cell.length());
				} else {
					if (columnWidth.get(i) < cell.length()) {
						columnWidth.set(i, cell.length());
					}
				}
				++i;
			}
		}
		for (List<String> row : table) {
			int i = 0;
			for (String cell : row) {
				ps.print(String.format("%1$-" + (columnWidth.get(i) + 1) + "s", cell));
				++i;
			}
			ps.println();
		}
	}
}
