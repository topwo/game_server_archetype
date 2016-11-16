#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.net.http;

import com.kidbear.${artifactId}.core.GameInit;

public class SessionKey {
	public static final String CACHE_ONLINE = "online_" + GameInit.serverId
			+ "_";
}
