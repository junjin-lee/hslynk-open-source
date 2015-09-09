package com.servinglynk.hmis.warehouse.enums;
import java.util.Map;
import java.util.HashMap;
/**
 * Defines the NoncashbenefitsSnapEnum enumeration.
 * 
 * @author Sandeep Dolia
 *
 */
public enum NoncashbenefitsSnapEnum {

	/** Enum Constant. */
	ZERO("0"),
	/** Enum Constant. */
	ONE("1"),
	/** Enum Constant. */
	NINTY_NINE("99");
	/**
	 * Internal storage of status field value, see the Enum spec for
 	 * clarification.
 	 */
	private final String status;
	
	/**
	 * Enum constructor for ActiveState.
	 * @param state Value.
	 */
	NoncashbenefitsSnapEnum(final String state) {
		this.status = state;
	}
	
	/** Construct a map for reverse lookup. */
	private static Map<String, NoncashbenefitsSnapEnum> valueMap = new HashMap<String, NoncashbenefitsSnapEnum>();

    static {
    	// construct hashmap for later possible use.
        for (NoncashbenefitsSnapEnum unit : values()) {
            valueMap.put(unit.getValue(), unit);
        }
    }
    
	/**
	 * Current string value stored in the enum.
	 * 
	 * @return string value.
	 */
	public String getValue() {
		return this.status;
	}

	/**
     * Perform a reverse lookup (given a value, obtain the enum).
     * 
     * @param value to search
     * @return Enum object.
     */
    public static NoncashbenefitsSnapEnum lookupEnum(String value) {
        return NoncashbenefitsSnapEnum.valueMap.get(value);
    }

}
