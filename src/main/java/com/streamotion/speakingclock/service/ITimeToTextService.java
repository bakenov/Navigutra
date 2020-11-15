package com.streamotion.speakingclock.service;

/**
 * Contract for service that converts system time into its text representation.
 * Conversion is done on hour/minutes level
 * 
 * 
 * @author bakenov
 *
 */
public interface ITimeToTextService {
	
	/**
	 * Provides the text representation of the current system time 
	 * 
	 * @return the text representation of the current system time 
	 */
	default String convertCurrentTimeToText() {
        return convertTimeToText(System.currentTimeMillis());
    }

	/**
	 * Provides the text representation of the specified time in milliseconds
	 * 
	 * @param time the time in milliseconds to be converted to text
	 * @return the text representation of the specified time 
	 */
	String convertTimeToText(long time);
	
}
