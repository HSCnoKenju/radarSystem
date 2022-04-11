package unibo.main;


import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import unibo.appl.ApplData;
import unibo.appl.LedActor;
import unibo.actor22comm.context.EnablerContextForActors;
import unibo.actor22comm.utils.ColorsOut;
import unibo.actor22comm.utils.CommSystemConfig;
import unibo.actor22comm.utils.CommUtils;
import unibo.appl.SonarActor;


public class LedActorOnRasp {
    private EnablerContextForActors ctx;

    public static void main(String[] args) {
        CommUtils.aboutThreads("Before start - ");
        new LedActorOnRasp().doJob();
        CommUtils.aboutThreads("Before end - ");
    }

    public void doJob() {
        ColorsOut.outappl("LedActorOnRasp | Start", ColorsOut.BLUE);
        configure();
        CommUtils.aboutThreads("Before execute - ");
        //CommUtils.waitTheUser();
        execute();
        terminate();
    }

    protected void configure() {
        DomainSystemConfig.simulation = true;
        DomainSystemConfig.ledGui = true;
        DomainSystemConfig.tracing = false;
        CommSystemConfig.tracing = true;

        ctx = new EnablerContextForActors("ctx", ApplData.ctxPort, ApplData.protocol);
        new LedActor(ApplData.ledName);
        new SonarActor(ApplData.sonarName);
        //Registrazione dei componenti presso il contesto: NO MORE ...
    }

    protected void execute() {
        ColorsOut.outappl("LedActorOnRasp | execute", ColorsOut.MAGENTA);
        ctx.activate();
    }

    public void terminate() {
        CommUtils.aboutThreads("Before exit - ");
// 	    CommUtils.delay(5000);
//		System.exit(0);
    }

}

/*
 * Threads:
 */
