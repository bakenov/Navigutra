package imc.game.psr.input;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Supplier;

import imc.game.psr.game.config.IGameConfig;
import imc.game.psr.game.report.IReport;

/**
 * Input IO component responsible for the receiving the messages from the user
 * 
 * @author bakenov
 *
 */
public class InputOutput implements IInputOutput {

	private Scanner scan;
	private boolean isRunning;
	private IMessageListener messageListener;
	private final String welcomeMessage;
	private final PrintStream out;
	private final Supplier<IReport> provider;

	/**
	 * Constructor for Input IO component
	 * 
	 * @param context     an application context
	 * @param distributer an input message distributor
	 * @param out         the output IO component
	 * @param config      an IO configuration component
	 */
	public InputOutput(IGameConfig config, Supplier<IReport> provider) {
		this.welcomeMessage = config.getConsoleMessage();
		this.provider = provider;
		this.out = System.out;
	}

	@Override
	public void start() {
		if (isRunning)
			return;
		isRunning = true;
		run();
	}

	private void stop() {
		if (isRunning) {
			isRunning = false;
			scan.close();
			IReport report = provider.get();
			displayConsoleMessage(report.toString());
		}
	}

	/**
	 * A run method that processes the user's input instructions and blocks the
	 * current thread while waiting the user input.
	 */
	private void run() {
		scan = new Scanner(System.in);
		String command = new String();
		while (isRunning) {
			displayConsoleMessage(welcomeMessage);
			command = scan.nextLine();
			processCommand(command);
		}
	}

	@Override
	public void setMessageListener(IMessageListener messageListener) {
		this.messageListener = messageListener;
	}

	private void processCommand(String input) {
		if (input.length() > 1) {
			// wrong command
			displayConsoleMessage(welcomeMessage);
		} else {
			char command = input.charAt(0);
			messageListener.onCommand(command);
		}
	}

	public void displayConsoleMessage(String message) {
		out.println(message);
	}

}
