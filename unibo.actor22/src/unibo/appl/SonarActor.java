package unibo.appl;

import it.unibo.kactor.IApplMessage;
import it.unibo.kactor.MsgUtil;
import it.unibo.radarSystem22.domain.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import org.jetbrains.annotations.NotNull;
import unibo.actor22.QakActor22;
import unibo.actor22comm.utils.ColorsOut;
import unibo.actor22comm.utils.CommUtils;

public class SonarActor extends QakActor22 {

    private ISonar sonar;
    public SonarActor(@NotNull String name) {
        super(name);
        sonar = DeviceFactory.createSonar();
    }

    @Override
    protected void handleMsg(IApplMessage msg) {
        CommUtils.aboutThreads(getName() + " |  Before doJob - ");
        ColorsOut.out(getName() + " | doJob " + msg, ColorsOut.RED);
        if (msg.isRequest()) elabRequest(msg);
        else elabCmd(msg);
    }

    private void elabCmd(IApplMessage msg) {

        String msgCmd = msg.msgContent();

        switch (msgCmd) {

            case ApplData.cmdSonarActivate:
                sonar.activate();
                break;

            case ApplData.cmdSonarDeactivate:
                sonar.deactivate();
                break;
            default:
                ColorsOut.outerr(getName() + " | unknown " + msgCmd);
        }

    }

    private void elabRequest(IApplMessage msg) {

        String msgReq = msg.msgContent();

        switch (msgReq) {

            case ApplData.reqSonarIsActive:
                boolean b = sonar.isActive();
                IApplMessage reply = MsgUtil.buildReply(getName(),ApplData.reqSonarIsActive,""+b,msg.msgSender());
                ColorsOut.out(getName() + " | reply= " + reply, ColorsOut.CYAN);
                sendReply(msg, reply);
                break;

            case ApplData.reqSonarGetDistance:
                int i = sonar.getDistance().getVal();
                reply = MsgUtil.buildReply(getName(),ApplData.reqSonarGetDistance,""+i,msg.msgSender());
                ColorsOut.out(getName() + " | reply= " + reply, ColorsOut.CYAN);
                sendReply(msg, reply);
                break;

            default:
                ColorsOut.outerr(getName() + " | unknown " + msgReq);

        }
    }
}
