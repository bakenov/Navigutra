package imc.game.psr.game.model;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import imc.game.psr.game.config.IGameConfig;
import imc.game.psr.game.model.event.BaseEvent;
import imc.game.psr.game.model.event.EventType;
import imc.game.psr.game.model.event.GameReportEvent;
import imc.game.psr.game.model.event.IGameEvent;
import imc.game.psr.game.model.event.IGameEventListener;
import imc.game.psr.game.model.event.IWeaponSelectedEvent;
import imc.game.psr.game.player.IPlayer;
import imc.game.psr.game.player.IPlayerBuilder;
import imc.game.psr.game.report.GameReport;
import imc.game.psr.game.report.GameResult;
import imc.game.psr.game.report.IGameResult;
import imc.game.psr.game.report.IReport;
import imc.game.psr.game.weapon.IBuilder;
import imc.game.psr.game.weapon.IWeapon;
import imc.game.psr.game.weapon.Weapon;

/**
 * The game's model
 * 
 * @author bakenov
 *
 */
public class GameModel<W extends IWeapon> implements IGameModel {

	private static final Logger log = LoggerFactory.getLogger(GameModel.class);

	private static final long DEFAULT_WAIT_PERIOD = 10000L;

	private final IBuilder<W> builder;
	private final IGameEventListener reportListener;
	private final IGameEventListener quiteCommandListener;
	private final IGameConfig config;
	private final int numberCycles;
	private final long waitPeriodInMillis;
	private PlayerData firstPlayerData;
	private PlayerData secondPlayerData;
	private IReport report;
	private int cycleIndex;
	private CountDownLatch latch;
	private boolean isRunning;

	public GameModel(IGameConfig config, IBuilder<W> builder,
			IGameEventListener reportListener,
			IGameEventListener quiteCommandListener) {
		this.builder = builder;
		this.config = config;
		this.reportListener = reportListener;
		this.quiteCommandListener = quiteCommandListener;
		this.numberCycles = config.getNumberGames();
		this.waitPeriodInMillis = DEFAULT_WAIT_PERIOD;
	}

	@Override
	public void setPlayerBuilder(IPlayerBuilder playerBuilder) {
		IPlayer firstPlayer = playerBuilder.buildPlayer(config.getFirstPlayerName(),
				config.getFirstPlayerType());
		firstPlayerData = new PlayerData(firstPlayer);
		IPlayer secondPlayer = playerBuilder.buildPlayer(
				config.getSecondPlayerName(), config.getSecondPlayerType());
		secondPlayerData = new PlayerData(secondPlayer);
		report = new GameReport(firstPlayerData.getName(),
				secondPlayerData.getName());
	}

	@Override
	public void start() {
		isRunning = true;
		while (++cycleIndex <= numberCycles && isRunning) {
			clearWeaponSelection();

			latch = new CountDownLatch(2);
			firstPlayerData.player.selectWeapon();
			secondPlayerData.player.selectWeapon();

			try {
				latch.await(waitPeriodInMillis, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			}
			// check
			if (firstPlayerData.weapon != null && secondPlayerData.weapon != null) {
				IGameResult gameResult = new GameResult(cycleIndex, firstPlayerData,
						secondPlayerData);
				report.registerResult(gameResult);
			}
		}
		gameFinished();
		isRunning = false;
	}

	private void gameFinished() {
		String reportString = report.buildReport();
		reportListener.onGameEvent(new GameReportEvent(reportString));
		quiteCommandListener.onGameEvent(new BaseEvent(EventType.QUIT_COMMAND));
	}

	private void clearWeaponSelection() {
		firstPlayerData.clearWeaponSelection();
		secondPlayerData.clearWeaponSelection();
	}

	@Override
	public void stop() {
		isRunning = false;
		reportListener.onGameEvent(new GameReportEvent(report.buildReport()));
		if (latch != null) {
			latch.countDown();
			latch.countDown();
		}
	}

	@Override
	public void onGameEvent(IGameEvent event) {
		if (event.getType() == EventType.WEAPON_SELECTED_BY_PLAYER) {
			IWeaponSelectedEvent selectionEvent = (IWeaponSelectedEvent) event;
			String name = selectionEvent.getPlayerName();
			if (firstPlayerData.player.getName().equals(name)) {
				latch.countDown();
				IWeapon weapon = builder.build(selectionEvent.getWeaponSymbol());
				firstPlayerData.setWeapon(weapon.getWeapon());
			} else if (secondPlayerData.player.getName().equals(name)) {
				latch.countDown();
				IWeapon weapon = builder.build(selectionEvent.getWeaponSymbol());
				secondPlayerData.setWeapon(weapon.getWeapon());
			}
		}
	}

	/**
	 * The player's game data
	 * 
	 * @author bakenov
	 *
	 */
	public static class PlayerData {

		private final IPlayer player;
		private Weapon weapon;

		private PlayerData(IPlayer player) {
			this.player = player;
		}

		private void setWeapon(Weapon weapon) {
			this.weapon = weapon;
		}

		private void clearWeaponSelection() {
			weapon = null;
		}

		public String getName() {
			return player.getName();
		}

		public Weapon getWeapon() {
			return weapon;
		}
	}
}
