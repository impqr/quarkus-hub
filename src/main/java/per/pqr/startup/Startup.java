package per.pqr.startup;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class Startup {
    public void onStart(@Observes StartupEvent event) {
        System.out.println("------ Startup Event here ------");
    }
}
