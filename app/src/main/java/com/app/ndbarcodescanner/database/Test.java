package com.app.ndbarcodescanner.database;

public class Test {
    private static final Test ourInstance = new Test();

    public static Test getInstance() {
        return ourInstance;
    }

    private Test() {
    }
}
