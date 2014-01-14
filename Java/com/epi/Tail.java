package com.epi;

import static com.epi.utils.Utils.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class Tail {
	// @include
	public static String tail(String file_name, int tail_count)
			throws IOException {
		
		RandomAccessFile file_ptr = new RandomAccessFile(file_name, "r");

		file_ptr.seek(file_ptr.length() - 1);
		long file_size = file_ptr.length(), newline_count = 0;
		StringBuilder output = new StringBuilder(); // stores the last
													// tail_count lines.
		// Reads file in reverse looking for '\n'.
		for (long i = file_size - 1; i != -1; i--) {
			file_ptr.seek(i);
			int readByte = file_ptr.readByte();
			char c = (char) readByte;
			if (c == '\n') {
				++newline_count;
				if (newline_count > tail_count) {
					break;
				}
			}
			output.append(c);
		}

		// Close "file_ptr" silently
		close(file_ptr);

		// Reverse the output string using the reverse() function.
		// The arguments are iterators to the start and end of string object.
		output.reverse();
		return output.toString();
	}
	// @exclude

	public static void main(String[] args) throws IOException {
		System.out.println("Usage: file name and tail count");

		  int tail_count = 10;
		  String file_name;
		  if (args.length == 1) {
		    file_name = args[0];
		  } else if (args.length == 2) {
		    file_name = args[0];
		    tail_count = Integer.valueOf(args[1]);
		  } else {
		    return;
		  }
		  
		  String output = tail(file_name, tail_count);
		  System.out.println(output);
		  
		  System.out.println();
		  System.out.println(String.format("Show last %d lines from file %s", tail_count, file_name));
		  System.out.println();
		  
		  show(file_name, tail_count);
	}

	/*
	 * Show a number of last lines from a file.
	 * 
	 * This is a pretty naive implementation. It first counts the total number
	 * of new lines, then reads the file again, this time reading last X lines.
	 */
	private static void show(String file_name, int lines) throws IOException {
		BufferedReader br = null;
		String currentLine;

		int totalLines = countNewLines(file_name);
		br = new BufferedReader(new FileReader(file_name));

		int lineCounter = 0;
		while ((currentLine = br.readLine()) != null) {
			lineCounter++;
			if (lineCounter > totalLines - lines) {
				System.out.println(currentLine);
			}
		}
		
		close(br);
	}
	
	// A slightly modified version of answer given here: http://stackoverflow.com/a/453067/1059744
	private static int countNewLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		byte[] c = new byte[1024];
		int count = 0;
		int readChars = 0;
		boolean empty = true;
		
		while ((readChars = is.read(c)) != -1) {
			empty = false;
			for (int i = 0; i < readChars; ++i) {
				if (c[i] == '\n') {
					++count;
				}
			}
		}
		
		close(is);
		return (count == 0 && !empty) ? 1 : count;
	}
}
