package imc.game.psr.game.weapon.selectionstrategy;

import java.util.function.Consumer;

import imc.game.psr.game.weapon.IBuilder;
import imc.game.psr.game.weapon.IWeapon;
import imc.game.psr.input.IMessageListener;
import imc.game.psr.input.ISelectionRequestListener;

/**
 * The Input selection strategy sends weapon selection request and wait for the
 * request.
 * 
 * @author bakenov
 *
 * @param <W> a type of the selection
 */
public class InputSelectionStrategy<W extends IWeapon>
		implements ISelectionStrategy<W>, IMessageListener {

	private final ISelectionRequestListener requestListener;
	private final IBuilder<W> builder;
	private Consumer<W> consumer;

	/**
	 * Constructor for the selection strategy
	 * 
	 * @param options the list of all available weapons
	 */
	public InputSelectionStrategy(ISelectionRequestListener requestListener,
			IBuilder<W> builder) {
		this.requestListener = requestListener;
		this.builder = builder;
	}

	@Override
	public void select(Consumer<W> consumer) {
		this.consumer = consumer;
		sendSelectionRequest();
	}

	@Override
	public void onCommand(char message) {
		W weapon = builder.build(message);
		consumer.accept(weapon);
	}

	private void sendSelectionRequest() {
		requestListener.selectionRequest();
	}

}
