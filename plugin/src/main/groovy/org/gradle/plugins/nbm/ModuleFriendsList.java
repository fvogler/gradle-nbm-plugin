package org.gradle.plugins.nbm;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.Project;

public final class ModuleFriendsList {

    private final Set<String> moduleFriends;
    private final Project project;

    public ModuleFriendsList(Project project) {
        this.project = project;
        this.moduleFriends = new HashSet<>();
    }

    public void add(final String moduleName) {
        Objects.requireNonNull(moduleName, "moduleName");
        
        // pattern copied from http://netbeans.org/ns/nb-module-project/3.xsd (code-name-base)
        // and add optional major version to pattern
        String pattern = "[a-zA-Z][a-zA-Z0-9_]*(\\.[a-zA-Z][a-zA-Z0-9_]*)*(/\\d+)?";
        if (!moduleName.matches(pattern)) {
            throw new InvalidUserDataException("Illegal module friend name - '" + moduleName + "' (must match '" + pattern + "'");
        }
        
        moduleFriends.add(moduleName);
    }

    public SortedSet<String> getEntries() {
        return new TreeSet<>(moduleFriends);
    }
}
