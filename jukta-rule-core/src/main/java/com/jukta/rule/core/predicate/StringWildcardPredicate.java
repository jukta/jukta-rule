package com.jukta.rule.core.predicate;

import com.jukta.rule.core.Predicate;

import java.util.regex.Pattern;

/**
 * @since 1.0
 */
public class StringWildcardPredicate implements Predicate<String> {
    private Pattern p;

    public StringWildcardPredicate(String value) {
        p = Pattern.compile(wildcardToRegex(value));
    }

    private String wildcardToRegex(String wildcard){
        StringBuffer pattern = new StringBuffer(wildcard.length());
        pattern.append('^');
        for (int i = 0, is = wildcard.length(); i < is; i++) {
            char c = wildcard.charAt(i);
            switch(c) {
                case '\\':
                    char ch = wildcard.charAt(i+1);
                    pattern.append("\\");
                    if (ch == '*' || ch == '?') {
                        pattern.append(ch);
                        i++;
                    } else {
                        pattern.append(c);
                    }
                    break;
                case '*':
                    pattern.append(".*");
                    break;
                case '?':
                    pattern.append(".");
                    break;
                case '(': case ')': case '[': case ']': case '$':
                case '^': case '.': case '{': case '}': case '|':
                    pattern.append("\\");
                    pattern.append(c);
                    break;
                default:
                    pattern.append(c);
                    break;
            }
        }
        pattern.append('$');
        return(pattern.toString());
    }

    @Override
    public boolean eval(String s) {
        return p.matcher(s).find();
    }

    @Override
    public int getRank() {
        return 1;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

}
