/*Copyright (C) 2014 Red Hat, Inc.

This file is part of IcedTea.

IcedTea is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License as published by
the Free Software Foundation, version 2.

IcedTea is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with IcedTea; see the file COPYING.  If not, write to
the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version.
 */

package net.sourceforge.jnlp.security.policyeditor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Defines the set of actions required for the default permissions
 */
public enum PermissionActions {

    NONE(""),
    READ("read"),
    WRITE("write"),
    EXECUTE("execute"),
    DELETE("delete"),
    READLINK("readlink"),
    FILE_ALL("read,write,execute,delete,readlink"),
    ACCEPT("accept"),
    LISTEN("listen"),
    CONNECT("connect"),
    RESOLVE("resolve"),
    NETALL("accept,listen,connect,resolve");

    private final String rawActions;
    private final Set<String> actions;

    private PermissionActions(final String actions) {
        this.rawActions = actions;
        this.actions = setFromString(actions);
    }

    /**
     * If there is any default action set that matches the string, return it.
     * If no matches, return NONE.
     * @param string a comma-separated list of permission actions
     * @return the closest matching default actions value
     */
    public static PermissionActions fromString(final String string) {
        final Collection<String> actions = setFromString(string);
        for (final PermissionActions action : PermissionActions.values()) {
            if (actions.equals(action.actions)) {
                return action;
            }
        }
        return NONE;
    }

    public Collection<String> getActions() {
        return new HashSet<String>(this.actions);
    }

    private static Set<String> setFromString(final String string) {
        final Set<String> set = new HashSet<>();
        Collections.addAll(set, string.split(","));
        return set;
    }

    public String rawString() {
        return rawActions;
    }
}
