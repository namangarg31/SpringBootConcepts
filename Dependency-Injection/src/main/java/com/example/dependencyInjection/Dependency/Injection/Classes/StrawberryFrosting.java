package com.example.dependencyInjection.Dependency.Injection.Classes;

import com.example.dependencyInjection.Dependency.Injection.Interfaces.Frosting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "flavour.env", havingValue = "STRAWBERRY")
public class StrawberryFrosting implements Frosting {
    @Override
    public String FrostingType() {
        return "Strawberry Frosting";
    }
}
