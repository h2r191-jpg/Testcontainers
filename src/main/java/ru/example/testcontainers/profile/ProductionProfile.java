package ru.example.testcontainers.profile;

import ru.example.testcontainers.interfaces.SystemProfile;

public class ProductionProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}
