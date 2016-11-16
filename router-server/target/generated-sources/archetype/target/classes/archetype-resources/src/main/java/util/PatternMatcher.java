#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

public interface PatternMatcher {

    boolean matches(String pattern, String source);
}
