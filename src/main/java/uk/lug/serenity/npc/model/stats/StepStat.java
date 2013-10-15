/**
 * 
 */
package uk.lug.serenity.npc.model.stats;

/**
 * $Id$
 * @version $Revision$
 * @author $Author$
 * <p>Bean for holding a single step stat (D4 , D6 , D8 etc).
 * 
 */
/**
 * @author Luggy
 *
 */
public class StepStat {
	
	public static final int MINIMUM = 4;

	public static final int MAXIMUM = 16;

	public static final String[] DICE_STRINGS={
		"D4", "D6", "D8", "D10", "D12", "D12+D2","D12+D4"
	};
	
	protected int value = 0;
	
	/**
	 * @param value
	 */
	public StepStat(int v) {
		super();
		if ( v<MINIMUM || v>MAXIMUM ) {
			throw new IllegalArgumentException("Value "+(v)+" invalid.  Valid values must be in the range 4 to 16 .");
		} else if ( (2*(v/2))!=v ) {
			throw new IllegalArgumentException("Value "+(v)+" invalid.  Valid values must be even.");
		}
		this.value =v ;
	}

	/**
	 * @return Returns the value, always a multiple of two.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * @param v The value to set, must be a multiple of 2 and between 4 and 16 (inclusive).
	 */
	public void setValue(int v) {
		String err = validateValue( v );
		if ( err!=null ) {
			throw new IllegalArgumentException(err);
		}
		this.value = v;
	}
	
	/**
	 * Check value for validity.  
	 * @param v value to check.
	 * @return null if value is ok, otherwise the error with the value.
	 */
	protected String validateValue(int v) {
		if ( v<4 || v>16 ) {
			return "Value "+(v)+" invalid.  Valid values must be in the range 4 to 16 ." ;
		} else if ( (2*(v/2))!=v ) {
			return "Value "+(v)+" invalid.  Valid values must be even." ;
		}
		return null;
	}
	
	/**
	 * @return the dice used for this stat at this value.
	 */
	public String getDice() {
		return getDiceFor( value );
	}
	
	/**
	 * Return the dice for a given value.
	 * @param v
	 * @return
	 */
	public static String getDiceFor(int v) {
		StringBuilder sb = new StringBuilder();
		int tmp = v;
		while ( tmp>12 ) {
			sb.append("D12+");
			tmp=tmp-12;
		}
		sb.append("D");
		sb.append(tmp);
		return sb.toString();
	}
	
	/**
	 * Apply an increment (or decrement if negative) to the
	 * value of this stat.
	 * @param increment add the value to this stat.
	 */
	public void adjust( int increment ) {
		setValue( getValue()+increment );
	}
	
	/**
	 * @return if the stat is at it's maximum value.
	 */
	public boolean isMaximum() {
		return value==MAXIMUM;
	}
	
	/**
	 * @return if the stat is at it's minimum value.
	 */
	public boolean isMinimum() {
		return value==MINIMUM;
	}
}
