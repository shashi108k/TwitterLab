package org.lab.util;

public interface Constant {

	// public: for the accessibility across all the classes, just like the methods present in the interface
	// static: as interface cannot have an object, the interfaceName.variableName can be used to reference it or directly the variableName in the class implementing it.
	// final: to make them constants. If 2 classes implement the same interface and you give both of them the right to change the value, conflict will occur in the current value of the var, which is why only one time initialization is permitted.
	public static final String RESOURCES_FILES_PATH = "src/main/resources/files/";
	
	interface JSON {
		
		// Also all these modifiers are implicit for an interface, you don't really need to specify any of them.
		String EMPLOYEE_JSON = "employee.json";
		String SCHEDULE_JSON = "schedule.json";
		String SCHEDULE_DAY_JSON = "scheduleDay.json";
		String SCHEDULE_MONTH_JSON = "scheduleMonth.json";
		String SCHEDULE_MONTH_MAP_JSON = "scheduleMonthMap.json";
		
		String GAME_JSON = "game.json";
	}
	
	interface ErrorCode {
		
		String BAD_REQUEST = "Please enter valid input parameters.";
	}
}
