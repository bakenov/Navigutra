package imc.game.psr.game.weapon;

/**
 * @author bakenov
 *
 * @param <W>
 */
public interface IBuilder<W extends IWeapon> {

	W build(char type);

}
