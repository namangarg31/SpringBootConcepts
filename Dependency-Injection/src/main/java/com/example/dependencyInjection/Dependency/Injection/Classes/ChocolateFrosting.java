package com.example.dependencyInjection.Dependency.Injection.Classes;

import com.example.dependencyInjection.Dependency.Injection.Interfaces.Frosting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "flavour.env", havingValue = "CHOCOLATE")
public class ChocolateFrosting implements Frosting {
    @Override
    public String FrostingType() {
        return "Chocolate Frosting";
    }
}
