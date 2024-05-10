package com.bt.phonebookingsystem.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

public class ConsoleOutputCapturer {
	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream capturedOut = new PrintStream(outputStream);

	public void captureOutput(Runnable codeToExecute, Consumer<String> outputConsumer) {
		System.setOut(capturedOut);
		codeToExecute.run();
		System.setOut(originalOut);
		String capturedOutput = outputStream.toString();
		outputConsumer.accept(capturedOutput);
	}
}
