package com.servinglynk.hmis.warehouse.enums;
import java.util.Map;
import java.util.HashMap;
/**
 * Defines the ProjectProjecttypeEnum enumeration.
 * 
 * @author Sandeep Dolia
 *
 */
public enum ProjectHousingTypeEnum {

	/** Enum Constant. */
	ONE("1"),
	/** Enum Constant. */
	TWO("2"),
	/** Enum Constant. */
	THREE("3");
	/**
	 * Internal storage of status field value, see the Enum spec for
 	 * clarification.
 	 */
	private final String status;
	
	/**
	 * Enum constructor for ActiveState.
	 * @param state Value.
	 */
	ProjectHousingTypeEnum(final String state) {
		this.status = state;
	}
	
	/** Construct a map for reverse lookup. */
	private static Map<String, ProjectHousingTypeEnum> valueMap = new HashMap<String, ProjectHousingTypeEnum>();

    static {
    	// construct hashmap for later possible use.
        for (ProjectHousingTypeEnum unit : values()) {
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
    public static ProjectHousingTypeEnum lookupEnum(String value) {
        return ProjectHousingTypeEnum.valueMap.get(value);
    }

}
