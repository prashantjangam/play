package com.teamfive.play.utils;


import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class Sanitizer {

    PolicyFactory sanitizer = new HtmlPolicyBuilder().toFactory();

    /**
     * Sanitizes a provided String and returns the sanitized value.
     *
     * @param input String to be sanitized.] * @return sanitized string.
     */
    public String sanitizeInput(String input) {
        return input == null ? null : sanitizer.sanitize(input).replace("&#64;", "@");
    }


}
