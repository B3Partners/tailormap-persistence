/*
 * Copyright (C) 2015-2021 B3Partners B.V.
 */
package nl.tailormap.viewer.util;

/**
 *
 * @author Matthijs Laan
 */
public class DB {
    /* Maximum number of expressions in a SQL list expression, to avoid ORA-01795 error
     * and avoid hitting the limit on the number of bind variables (around 1000 for some
     * databases)
     */
    public static final int MAX_LIST_EXPRESSIONS = 500;
}
