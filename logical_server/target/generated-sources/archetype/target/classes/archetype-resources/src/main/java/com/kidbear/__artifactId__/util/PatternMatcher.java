#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.util;

public interface PatternMatcher {

    boolean matches(String pattern, String source);
}
