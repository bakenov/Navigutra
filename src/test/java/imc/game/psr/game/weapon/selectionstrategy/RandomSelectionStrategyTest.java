package imc.game.psr.game.weapon.selectionstrategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import imc.game.psr.game.model.event.EventType;
import imc.game.psr.game.model.event.IGameEvent;
import imc.game.psr.game.model.event.IGameEventListener;
import imc.game.psr.game.model.event.IWeaponSelectedEvent;
import it.unimi.dsi.fastutil.chars.CharArraySet;
import it.unimi.dsi.fastutil.chars.CharSet;
import mockit.Delegate;
import mockit.Expectations;
import mockit.Injectable;

/**
 * Tests for class {@link RandomSelectionStrategy}
 * 
 * @author Anvar Bakenov
 *
 */
public class RandomSelectionStrategyTest {

	private static final String PLAYER_ID = "player1";

	private CharSet weaponSymbolSet;

	@Injectable
	IGameEventListener selectionWeaponListener;

	private int numberCalls;

	@Before
	public void setUp() {
		weaponSymbolSet = new CharArraySet(new char[] { 'P', 'R', 'S' });
		numberCalls = 0;
	}

	@Test
	public void testLoad() {
		new Expectations() {
			{
				selectionWeaponListener.onGameEvent((IGameEvent) any);
				result = new Delegate<IGameEventListener>() {
					@SuppressWarnings("unused")
					public void onGameEvent(IGameEvent event) {
						assertEquals(EventType.WEAPON_SELECTED_BY_PLAYER, event.getType());
						IWeaponSelectedEvent selectionEvent = (IWeaponSelectedEvent) event;
						assertEquals(PLAYER_ID, selectionEvent.getPlayerName());
						assertTrue(
								weaponSymbolSet.contains(selectionEvent.getWeaponSymbol()));
						numberCalls++;
					}
				};
			}
		};

		RandomSelectionStrategy strategy = new RandomSelectionStrategy(PLAYER_ID,
				weaponSymbolSet, selectionWeaponListener);
		strategy.selectNewWeapon();
		strategy.selectNewWeapon();
		strategy.selectNewWeapon();
		strategy.selectNewWeapon();
		assertEquals(4, numberCalls);

	}
}
