package per.pqr.startup;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;

public class Startup {
    public void onStart(@Observes StartupEvent event) {
        System.out.println("------ Startup Event here ------");
    }
}
