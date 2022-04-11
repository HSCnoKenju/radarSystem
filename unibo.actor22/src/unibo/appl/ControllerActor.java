package unibo.appl;

import it.unibo.kactor.IApplMessage;
import unibo.actor22.QakActor22;
import unibo.actor22comm.utils.ColorsOut;
import unibo.actor22comm.utils.CommUtils;

/*
 * Il controller conosce SOLO I NOMI dei dispositivi
 * (non ha riferimenti ai dispositivi-attori)
 */
public class ControllerActor extends QakActor22 {
    protected int numIterLed = 1;
    protected int numIterSonar = 0;
    protected IApplMessage getStateRequest;
    protected IApplMessage getSonarActiveRequest;
    protected IApplMessage getDistanceRequest;
    protected IApplMessage cmdDisplayUpdate;
    protected boolean on = true;

    public ControllerActor(String name) {
        super(name);
        getStateRequest = ApplData.buildRequest(name, "ask", ApplData.reqLedState, ApplData.ledName);
        getSonarActiveRequest = ApplData.buildRequest(name, "ask", ApplData.reqSonarIsActive, ApplData.sonarName);
        getDistanceRequest = ApplData.buildRequest(name, "ask", ApplData.reqSonarGetDistance, ApplData.sonarName);
        ApplData.buildDispatch(name,ApplData.cmdDisplayUpdate,"0",ApplData.radarName);
    }

    @Override
    protected void handleMsg(IApplMessage msg) {
        if (msg.isReply()) {
            elabAnswer(msg);
        } else {
            elabCmd(msg);
        }
    }

    protected void elabCmd(IApplMessage msg) {
        String msgCmd = msg.msgContent();
        ColorsOut.outappl(getName() + " | elabCmd=" + msgCmd, ColorsOut.GREEN);
        switch (msgCmd) {
            case ApplData.cmdActivate: {
               doControllerWorkLed();
                doControllerWorkSonar();
                break;
            }
            default:
                break;
        }
    }

    protected void wrongBehavior() {
        //WARNING: Inviare un treno di messaggi VA EVITATO
        //mantiene il controllo del Thread degli attori (qaksingle)
        for (int i = 1; i <= 3; i++) {
            forward(ApplData.turnOffLed);
            CommUtils.delay(500);
            forward(ApplData.turnOnLed);
            CommUtils.delay(500);
        }
        forward(ApplData.turnOffLed);
    }

    protected void doControllerWorkLed() {
        CommUtils.aboutThreads(getName() + " |  Before doControllerWorkLed on=" + on);
        //wrongBehavior();
        ColorsOut.outappl( getName()  + " | numIterLed=" + numIterLed  , ColorsOut.GREEN);
        if (numIterLed++ < 5) { // modifico l'iterazione ad ogni esecuzione
            if (numIterLed % 2 == 1) forward(ApplData.turnOnLed); //accesione
            else forward(ApplData.turnOffLed); //spegnimento
            request(getStateRequest);
        } else {
            forward(ApplData.turnOffLed);
            //ColorsOut.outappl(getName() + " | emit " + ApplData.endWorkEvent, ColorsOut.MAGENTA);
            //emit( ApplData.endWorkEvent );
        }

    }

    protected void doControllerWorkSonar(){
        CommUtils.aboutThreads(getName() + " |  Before doControllerWorkSonar on=" + on);
        numIterSonar++;
        ColorsOut.outappl( getName()  + " | numIterSonar=" + numIterSonar  , ColorsOut.GREEN);

        if (numIterSonar == 1 ){
            forward(ApplData.sonarActivate);
            request(getSonarActiveRequest);

        }
        else if ( numIterSonar < 5) {
            request(getDistanceRequest);
        }
        else {
            forward(ApplData.sonarDeactivate);
            request(getSonarActiveRequest);
        }

    }

    protected void doControllerRadarWork(String value){
        CommUtils.aboutThreads(getName() + " |  Before doControllerWorkRadar on=" + on);
        cmdDisplayUpdate = ApplData.buildDispatch(getName(),ApplData.cmdDisplayUpdate,value,ApplData.radarName);
        forward(cmdDisplayUpdate);
    }


    protected void elabAnswer(IApplMessage msg) {
        ColorsOut.outappl(getName() + " | elabAnswer " + " " + msg, ColorsOut.MAGENTA);
        CommUtils.delay(300);

        String sender = msg.msgSender();
        switch (sender){

            case ApplData.ledName:
                doControllerWorkLed(); // ogni volta che ricevo il messaggio, rieseguo l'invio del comando
                break;

            case ApplData.sonarName:
                if(msg.msgId().equals(ApplData.reqSonarGetDistance))
                    doControllerRadarWork(msg.msgContent());
                doControllerWorkSonar();
                break;

            default:
                ColorsOut.outerr(getName() + " | unknown " + sender);

        }
    }

}
