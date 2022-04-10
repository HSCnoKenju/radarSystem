package unibo.main;

import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import unibo.actor22.Qak22Util;
import unibo.actor22comm.utils.ColorsOut;
import unibo.actor22comm.utils.CommSystemConfig;
import unibo.actor22comm.utils.CommUtils;
import unibo.appl.ApplData;
import unibo.appl.ControllerActor;
import unibo.appl.LedActor;
import unibo.appl.SonarActor;


/*
 * Sistema che usa led e controller come attori locali
 */

public class RSActor22onPC {


    public static void main(String[] args) {
        CommUtils.aboutThreads("Before start - ");
        new RSActor22onPC().doJob();
        CommUtils.aboutThreads("Before end - ");
    }

    public void doJob() {
        ColorsOut.outappl(getClass().getName() + "| Start", ColorsOut.BLUE);
        configure();
        CommUtils.aboutThreads("Before execute - ");
        //CommUtils.waitTheUser();
        execute();
        terminate();
    }

    protected void configure() {
        DomainSystemConfig.simulation = true;
        DomainSystemConfig.ledGui = true;
        DomainSystemConfig.tracing = true;
        CommSystemConfig.tracing = true;

        new LedActor(ApplData.ledName);
        new SonarActor(ApplData.sonarName);
        new ControllerActor(ApplData.controllerName);


    }



    protected void execute() {
        Qak22Util.sendAMsg(ApplData.activateCrtl);
    }

    public void terminate() {
        CommUtils.aboutThreads("Before exit - ");
        CommUtils.delay(3000); // quando muore tutto
        System.exit(0);
    }


}

/*
 * Threads:
 */
