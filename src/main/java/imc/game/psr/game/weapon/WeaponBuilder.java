package imc.game.psr.game.weapon;

import java.util.Arrays;

import imc.game.psr.game.config.IGameConfig;
import it.unimi.dsi.fastutil.chars.Char2CharMap;
import it.unimi.dsi.fastutil.chars.Char2CharOpenHashMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.chars.CharSet;
import it.unimi.dsi.fastutil.objects.Object2CharMap;
import it.unimi.dsi.fastutil.objects.Object2CharOpenHashMap;

/**
 * The builder of the weapon, caches the weapons per weapon's symbol
 * 
 * @author bakenov
 *
 * @param <W> the weapon type
 */
public class WeaponBuilder<W extends IWeapon> implements IBuilder<W> {

	private final Char2CharMap conversionMap;
	private final Char2ObjectMap<W> weaponMap;
	private final Object2CharMap<W> weaponReverseMap;

	/**
	 * Constructor for the weapon builder
	 * 
	 * @param config the game configuration
	 */
	public WeaponBuilder(IGameConfig config) {
		conversionMap = new Char2CharOpenHashMap();
		weaponMap = new Char2ObjectOpenHashMap<>();
		weaponReverseMap = new Object2CharOpenHashMap<>();
		setStandardWeaponMap();
		setConversionMap(config.getSymbolToNameMap());
	}

	@SuppressWarnings("unchecked")
	private void setStandardWeaponMap() {
		Arrays.stream(Weapon.values()).forEach((w) -> {
			weaponMap.put(w.getCode(), (W) w);
			weaponReverseMap.put((W) w, w.getCode());
		});
	}

	private void setConversionMap(Char2ObjectMap<String> symbolToNameMap) {
		CharSet chars = symbolToNameMap.keySet();
		CharIterator iter = chars.iterator();
		while (iter.hasNext()) {
			char c = iter.nextChar();
			String s = symbolToNameMap.get(c);
			Weapon w = Weapon.valueOf(Weapon.class, s.toUpperCase());
			Character defaultChar = weaponReverseMap.get(w);
			conversionMap.put(c, defaultChar.charValue());
		}
	}

	@Override
	public W build(char type) {
		char defaultChar = conversionMap.get(type);
		return weaponMap.get(defaultChar);
	}

}
