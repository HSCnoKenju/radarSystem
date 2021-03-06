package unibo.appl;

import it.unibo.kactor.ApplMessage;
import it.unibo.kactor.ApplMessageType;
import it.unibo.kactor.IApplMessage;
import unibo.actor22comm.ProtocolType;
import unibo.actor22comm.utils.ColorsOut;

public class ApplData {
    public static final String ledName = "led";
    public static final String sonarName = "sonar";
    public static final String controllerName = "controller";
    public static final String radarName = "radar";

    public static final String observerName = "observer";

    // leds
    public static final String comdLedon = "turnOn";
    public static final String comdLedoff = "turnOff";
    public static final String reqLedState = "getState";

    // controller
    public static final String cmdActivate = "activate";
    public static final String cmdDectivate = "deactivate";

    public static final String evEndWork = "endWork";


    // sonar
    public static final String cmdSonarActivate = "activate";
    public static final String cmdSonarDeactivate = "deactivate";
    public static final String reqSonarIsActive = "isActive";
    public static final String reqSonarGetDistance = "getDistance";


    // radarDisplay

    public static final String cmdDisplayUpdate = "update";
    public static final String reqDisplayCurrent = "getCurrent";



    public static final int ctxPort = 8018;
    public static final ProtocolType protocol = ProtocolType.tcp;
    //String MSGID, String MSGTYPE, String SENDER, String RECEIVER, String CONTENT, String SEQNUM
    private static int msgNum = 0;

    // led
    public static final IApplMessage turnOnLed = buildDispatch(controllerName, "cmd", comdLedon, ledName);
    public static final IApplMessage turnOffLed = buildDispatch(controllerName, "cmd", comdLedoff, ledName);

    // controller
    public static final IApplMessage activateCrtl = buildDispatch("main", "cmd", cmdActivate, controllerName);
    public static final IApplMessage endWorkEvent = buildEvent(controllerName, evEndWork, evEndWork);


    // sonar

    public static final IApplMessage sonarActivate = buildDispatch(controllerName, "cmd", cmdSonarActivate, sonarName);
    public static final IApplMessage sonarDeactivate = buildDispatch(controllerName, "cmd", cmdSonarDeactivate, sonarName);



    public static IApplMessage buildDispatch(String sender, String msgId, String payload, String dest) {
        try {
            return new ApplMessage(msgId, ApplMessageType.dispatch.toString(), sender, dest, payload, "" + (msgNum++));
        } catch (Exception e) {
            ColorsOut.outerr("buildDispatch ERROR:" + e.getMessage());
            return null;
        }
    }

    public static IApplMessage buildRequest(String sender, String msgId, String payload, String dest) {
        try {
            return new ApplMessage(msgId, ApplMessageType.request.toString(), sender, dest, payload, "" + (msgNum++));
        } catch (Exception e) {
            ColorsOut.outerr("buildRequest ERROR:" + e.getMessage());
            return null;
        }
    }

    public static IApplMessage buildReply(String sender, String msgId, String payload, String dest) {
        try {
            return new ApplMessage(msgId, ApplMessageType.reply.toString(), sender, dest, payload, "" + (msgNum++));
        } catch (Exception e) {
            ColorsOut.outerr("buildReply ERROR:" + e.getMessage());
            return null;
        }
    }

    public static IApplMessage buildEvent(String emitter, String msgId, String payload) {
        try {
            return new ApplMessage(msgId, ApplMessageType.event.toString(), emitter, "ANY", payload, "" + (msgNum++));
        } catch (Exception e) {
            ColorsOut.outerr("buildEvent ERROR:" + e.getMessage());
            return null;
        }
    }


    public static IApplMessage prepareReply(IApplMessage requestMsg, String answer) {
        String sender = requestMsg.msgSender();
        String receiver = requestMsg.msgReceiver();
        String reqId = requestMsg.msgId();
        IApplMessage reply = null;
        if (requestMsg.isRequest()) { //DEFENSIVE
            //The msgId of the reply must be the id of the request !!!!
            reply = buildReply(receiver, reqId, answer, sender);
        } else {
            ColorsOut.outerr("Utils | prepareReply ERROR: message not a request");
        }
        return reply;
    }

}
