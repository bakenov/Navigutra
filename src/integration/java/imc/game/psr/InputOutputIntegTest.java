package imc.game.psr;

import java.io.PrintStream;

import imc.game.psr.game.config.IGameConfig;
import imc.game.psr.game.model.event.BaseEvent;
import imc.game.psr.game.model.event.EventType;
import imc.game.psr.game.model.event.GameReportEvent;
import imc.game.psr.game.model.event.IGameEvent;
import imc.game.psr.game.model.event.IGameEventListener;
import imc.game.psr.game.model.event.TakeInputEvent;
import imc.game.psr.game.model.event.WeaponSelectedEvent;
import imc.game.psr.input.IInputOutput;
import imc.game.psr.input.InputValidator;

/**
 * Input IO component responsible for the receiving the messages from the user
 * 
 * @author bakenov
 *
 */
public class InputOutputIntegTest implements IInputOutput, Runnable {

	private static final String INVALID_WEAPON = "Invalid symbol for a weapon";

	private volatile boolean isRunning;
	private final IGameEventListener quiteCommandListener;
	private final InputValidator inputValidator;
	private final String selectWeaponMessage;
	private final char quitCommand;
	private final PrintStream out;
	private Thread inputThread;

	private TakeInputEvent currentTakeInputEvent;

	/**
	 * Constructor for Input IO component
	 * 
	 * @param context     an application context
	 * @param distributer an input message distributor
	 * @param out         the output IO component
	 * @param config      an IO configuration component
	 */
	public InputOutputIntegTest(IGameConfig config,
			IGameEventListener quiteCommandListener) {
		this.selectWeaponMessage = config.getConsoleMessage();
		this.quitCommand = config.getQuitCommand();
		this.quiteCommandListener = quiteCommandListener;
		this.out = System.out;
		this.inputValidator = new InputValidator(config);
	}

	@Override
	public void start() {
		if (isRunning)
			return;
		isRunning = true;
		inputThread = new Thread(this);
		inputThread.start();
	}

	public void stop() {
		if (isRunning) {
			isRunning = false;
			displayConsoleMessage("Finish.");
		}
	}

	/**
	 * A run method that processes the user's input instructions and blocks the
	 * current thread while waiting the user input.
	 */
	public void run() {
		while (isRunning) {
			if (currentTakeInputEvent != null) {
				processCommand("R");
			}
		}
		System.out.println("InputOutput.run()   the run finishing");
	}

	private void processCommand(String input) {
		if (input.length() == 1) {
			char c = input.charAt(0);
			if (c == quitCommand) {
				quiteCommandListener.onGameEvent(new BaseEvent(EventType.QUIT_COMMAND));
			} else {
				if (inputValidator.isSelectionValid(c)
						&& currentTakeInputEvent != null) {
					WeaponSelectedEvent event = new WeaponSelectedEvent(
							currentTakeInputEvent.getPlayerName(), c);
					currentTakeInputEvent.getSelectionListener().onGameEvent(event);
					currentTakeInputEvent = null;
				} else {
					displayConsoleMessage(INVALID_WEAPON);
				}
			}
		} else {
			// wrong command
			displaySelectMessage();
		}
	}

	private void displayConsoleMessage(String message) {
		out.println(message);
	}

	private void displaySelectMessage() {
		out.println(selectWeaponMessage);
	}

	@Override
	public void onGameEvent(IGameEvent event) {
		if (currentTakeInputEvent != null)
			// ignore the request
			return;

		if (event.getType() == EventType.GET_INPUT) {
			currentTakeInputEvent = (TakeInputEvent) event;
			displaySelectMessage();
		} else if (event.getType() == EventType.REPORT) {
			GameReportEvent report = (GameReportEvent) event;
			displayConsoleMessage(report.getReport());
		}
	}

}
