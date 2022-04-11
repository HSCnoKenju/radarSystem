package unibo.appl;

import it.unibo.kactor.IApplMessage;
import it.unibo.kactor.MsgUtil;
import it.unibo.radarSystem22.domain.DeviceFactory;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import org.jetbrains.annotations.NotNull;
import unibo.actor22.QakActor22;
import unibo.actor22comm.utils.ColorsOut;
import unibo.actor22comm.utils.CommUtils;

public class RadarActor extends QakActor22 {

    private IRadarDisplay display;

    public RadarActor(@NotNull String name) {
        super(name);
        display = DeviceFactory.createRadarGui();
    }

    @Override
    protected void handleMsg(IApplMessage msg) {
        CommUtils.aboutThreads(getName() + " |  Before doJob - ");
        ColorsOut.out(getName() + " | doJob " + msg, ColorsOut.RED);
        if (msg.isRequest()) elabRequest(msg);
        else elabCmd(msg);
    }
    protected void elabCmd(IApplMessage msg) {
        String msgId = msg.msgId();
        switch (msgId) {
            case ApplData.cmdDisplayUpdate:
                display.update(msg.msgContent(),"0");
                break;
            default:
                ColorsOut.outerr(getName() + " | unknown " + msgId);
        }

    }
    protected void elabRequest(IApplMessage msg) {

        String msgReq = msg.msgContent();

        switch (msgReq) {
            case ApplData.reqDisplayCurrent: {
                int i = display.getCurrentDistance();
                IApplMessage reply = MsgUtil.buildReply(getName(), ApplData.reqDisplayCurrent, "" + i, msg.msgSender());
                ColorsOut.out(getName() + " | reply= " + reply, ColorsOut.CYAN);
                sendReply(msg, reply);
                break;
            }
            default:
                ColorsOut.outerr(getName() + " | unknown " + msgReq);
        }
    }
    }
