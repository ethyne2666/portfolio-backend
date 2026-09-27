package com.charankumar.portfolio.lab.postman;

import org.springframework.stereotype.Component;

import java.util.List;

// This is the fixed "menu" of safe operations visitors can execute.
// To add a new playable endpoint later, you just add one more line in getAll().
// No user input ever changes this list — it's entirely defined by you, the developer.
@Component
public class PlaygroundCatalog {

    public List<PlaygroundEndpoint> getAll() {
        return List.of(
                new PlaygroundEndpoint(
                        "get-all-projects",
                        "GET",
                        "/api/projects",
                        "Fetch all published portfolio projects"
                ),
                new PlaygroundEndpoint(
                        "get-all-snippets",
                        "GET",
                        "/api/snippets",
                        "Fetch all code snippets"
                ),
                new PlaygroundEndpoint(
                        "get-current-now",
                        "GET",
                        "/api/now",
                        "Fetch what I'm currently building/learning"
                )
        );
    }

    // Used later in Step 3 — finds ONE catalog entry by its id, or empty if the id is invalid.
    public java.util.Optional<PlaygroundEndpoint> findById(String id) {
        return getAll().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}