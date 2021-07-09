/**
 * LongestPrefixMatcher.java
 *
 *   Version: 2019-07-10
 * Copyright: University of Twente, 2015-2019
 *
 **************************************************************************
 *                            Copyright notice                            *
 *                                                                        *
 *             This file may ONLY be distributed UNMODIFIED.              *
 * In particular, a correct solution to the challenge must NOT be posted  *
 * in public places, to preserve the learning effect for future students. *
 **************************************************************************
 */

package lpm;

import java.util.ArrayList;

public class LongestPrefixMatcher {
	/**
	 * You can use this function to initialize variables.
	 * 
	 */
	private ArrayList<Integer> routeIpList = new ArrayList<>();
	private ArrayList<Byte> prefixLengthList = new ArrayList<>();
	private ArrayList<Integer> portNumberList = new ArrayList<>();
	int lowerBound;
	int upperBound;

	public LongestPrefixMatcher() {

	}

	/**
	 * Looks up an IP address in the routing tables
	 * 
	 * @param ip The IP address to be looked up in integer representation
	 * @return The port number this IP maps to
	 */
	public int lookup(int ip) {
		// TODO: Look up this route
		int portNumber = -1;

		int firstEightBits = (ip >> 24) & 0xff;
		
		if(firstEightBits < 12) {
			lowerBound = 0;
			upperBound = 5041;
		}else if(firstEightBits < 20) {
			lowerBound = 5040;
			upperBound = 8928;
		}else if(firstEightBits < 31) {
			lowerBound = 8927;
			upperBound = 16965;
		}else if(firstEightBits < 40) {
			lowerBound = 16964;
			upperBound = 23467;
		} else if (firstEightBits < 50) {
			lowerBound = 23466;
			upperBound = 35091;
		} else if(firstEightBits < 60) {
			lowerBound = 35090;
			upperBound = 40199;
		} else if (firstEightBits < 70) {
			lowerBound = 40198;
			upperBound = 81036;
		} else if (firstEightBits < 75) {
			lowerBound = 81035;
			upperBound = 94333;
		}else if(firstEightBits < 80) {
			lowerBound = 94332;
			upperBound = 102129;
		}else if(firstEightBits < 90) {
			lowerBound = 102128;
			upperBound = 121918;
		} else if (firstEightBits < 100) {
			lowerBound = 121917;
			upperBound = 146505;
		} else if (firstEightBits < 110) {
			lowerBound = 146504;
			upperBound = 155365;
		}else if(firstEightBits < 120) {
			lowerBound = 155364;
			upperBound = 173825;
		} else if (firstEightBits < 125) {
			lowerBound = 173824;
			upperBound = 184327;
		} else if(firstEightBits < 130) {
			lowerBound = 184326;
			upperBound = 188774;
		}else if(firstEightBits < 140) {
			lowerBound = 188443;
			upperBound = 194423;
		} else if (firstEightBits < 150) {
			lowerBound = 194422;
			upperBound = 202990;
		}else if(firstEightBits < 160) {
			lowerBound = 202989;
			upperBound = 208766;
		} else if(firstEightBits < 170) {
			lowerBound = 208765;
			upperBound = 216978;
		} else if (firstEightBits < 175) {
			lowerBound = 216977;
			upperBound = 224777;
		} else if(firstEightBits < 180) {
			lowerBound = 224776;
			upperBound = 235168;
		} else if (firstEightBits < 185) {
			lowerBound = 235167;
			upperBound = 243756;
		}else if(firstEightBits < 190) {
			lowerBound = 243755;
			upperBound = 262379;
		} else if (firstEightBits < 195) {
			lowerBound = 262378;
			upperBound = 295094;
		} else if (firstEightBits < 200) {
			lowerBound = 295093;
			upperBound = 316192;
		} else if (firstEightBits < 210) {
			lowerBound = 316191;
			upperBound = 384839;
		} else if (firstEightBits < 220) {
			lowerBound = 384838;
			upperBound = 415991;
		} else {
			lowerBound = 415990;
			upperBound = 420972;
		}

		for (int i = lowerBound; i < upperBound; i++) {
			int routeIP = routeIpList.get(i);
			int prefixLength = prefixLengthList.get(i);
			int a = routeIP >> (32 - prefixLength);
			int b = ip >> (32 - prefixLength);
			if (a == b) {
				portNumber = portNumberList.get(i);
			}
		}

		return portNumber;

	}

	/**
	 * Adds a route to the routing tables
	 * 
	 * @param ip           The IP the block starts at in integer representation
	 * @param prefixLength The number of bits indicating the network part of the
	 *                     address range (notation ip/prefixLength)
	 * @param portNumber   The port number the IP block should route to
	 */
	public void addRoute(int ip, byte prefixLength, int portNumber) {
		// TODO: Store this route for later use in lookup() method
		routeIpList.add(ip);
		prefixLengthList.add(prefixLength);
		portNumberList.add(portNumber);
	}

	/**
	 * This method is called after all routes have been added. You don't have to use
	 * this method but can use it to sort or otherwise organize the routing
	 * information, if your datastructure requires this.
	 */
	public void finalizeRoutes() {
		// TODO: Optionally do something
	}

	/**
	 * Converts an integer representation IP to the human readable form
	 * 
	 * @param ip The IP address to convert
	 * @return The String representation for the IP (as xxx.xxx.xxx.xxx)
	 */
	private String ipToHuman(int ip) {
		return Integer.toString(ip >> 24 & 0xff) + "." + Integer.toString(ip >> 16 & 0xff) + "."
				+ Integer.toString(ip >> 8 & 0xff) + "." + Integer.toString(ip & 0xff);
	}

	/**
	 * Parses an IP
	 * 
	 * @param ipString The IP address to convert
	 * @return The integer representation for the IP
	 */
	private int parseIP(String ipString) {
		String[] ipParts = ipString.split("\\.");

		int ip = 0;
		for (int i = 0; i < 4; i++) {
			ip |= Integer.parseInt(ipParts[i]) << (24 - (8 * i));
		}

		return ip;
	}
}
