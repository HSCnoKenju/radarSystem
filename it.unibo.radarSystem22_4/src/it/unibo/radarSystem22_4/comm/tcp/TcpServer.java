package it.unibo.radarSystem22_4.comm.tcp;

import it.unibo.radarSystem22_4.comm.interfaces.IApplMsgHandler;
import it.unibo.radarSystem22_4.comm.interfaces.Interaction2021;
import it.unibo.radarSystem22_4.comm.utils.ColorsOut;
import it.unibo.radarSystem22_4.comm.utils.CommSystemConfig;

import java.net.ServerSocket;
import java.net.Socket;


public class TcpServer extends Thread {
    protected IApplMsgHandler userDefHandler;
    protected String name;
    protected boolean stopped = true;
    private ServerSocket serversock;

    public TcpServer(String name, int port, IApplMsgHandler userDefHandler) {
        super(name);
        try {
            this.userDefHandler = userDefHandler;
            ColorsOut.out(getName() + " | costructor port=" + port, ColorsOut.BLUE);
            this.name = getName();
            serversock = new ServerSocket(port);
            serversock.setSoTimeout(CommSystemConfig.serverTimeOut);
        } catch (Exception e) {
            ColorsOut.outerr(getName() + " | costruct ERROR: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            ColorsOut.outappl(getName() + " | STARTING ... ", ColorsOut.BLUE);
            while (!stopped) {
                //Accept a connection
                ColorsOut.out(getName() + " | waits on server " + "serversock=" + serversock);
                Socket sock = serversock.accept();
                ColorsOut.out(getName() + " | accepted connection  ", ColorsOut.BLUE);
                Interaction2021 conn = new TcpConnection(sock);
                //Create a message handler on the connection
                new TcpApplMessageHandler(userDefHandler, conn);
            }//while
        } catch (Exception e) {  //Scatta quando la deactive esegue: serversock.close();
            ColorsOut.out(getName() + " | probably socket closed: " + e.getMessage(), ColorsOut.GREEN);
        }
    }

    public void activate() {
        if (stopped) {
            stopped = false;
            this.start();
        }//else already activated
    }

    public void deactivate() {
        try {
            ColorsOut.out(getName() + " |  DEACTIVATE serversock=" + serversock);
            stopped = true;
            serversock.close();
        } catch (Exception e) {
            ColorsOut.outerr(getName() + " | deactivate ERROR: " + e.getMessage());
        }
    }

}
