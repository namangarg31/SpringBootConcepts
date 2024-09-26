package com.example.dependencyInjection.Dependency.Injection.Classes;

import com.example.dependencyInjection.Dependency.Injection.Interfaces.Frosting;
import com.example.dependencyInjection.Dependency.Injection.Interfaces.Syrup;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    private final Syrup syrup;
    private final Frosting frosting;

    /**
     * Constructor Injection
     * @param syrup - interface
     * @param frosting - interface
     */
    public CakeBaker(Syrup syrup, Frosting frosting) {
        this.syrup = syrup;
        this.frosting = frosting;
    }

    public void bakeCake() {
        System.out.println("Cake baked by adding "+ syrup.SyrupType()+ " and doing "+ frosting.FrostingType());
    }

}
