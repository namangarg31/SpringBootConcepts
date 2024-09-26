package com.example.dependencyInjection.Dependency.Injection.Classes;

import com.example.dependencyInjection.Dependency.Injection.Interfaces.Syrup;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "flavour.env", havingValue = "CHOCOLATE")
public class ChocolateSyrup implements Syrup {
    @Override
    public String SyrupType() {
        return "Chocolate Syrup";
    }
}
