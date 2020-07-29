package pkServer;

import java.io.Serializable;

/**
 *
 * @author Ahmad Fahmawi
 */
public class Server implements Serializable {

    private int maxCapacity;
    private int PORT;
    private String HOST;
    private String ServerName;
    private String serverID;
    private int load;

    public int getLoad() {
        return load;
    }

    public void increasLoad() {
        this.load++;
    }

    public void decreasLoad() {
        this.load--;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public Server(int maxCapacity, int PORT, String HOST, String ServerName) {
        this.maxCapacity = maxCapacity;
        this.PORT = PORT;
        this.HOST = HOST;
        this.ServerName = ServerName;
        this.load = 0;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getPORT() {
        return PORT;
    }

    public String getHOST() {
        return HOST;
    }

    public String getServerName() {
        return ServerName;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setServerName(String ServerName) {
        this.ServerName = ServerName;
    }

    @Override
    public String toString() {
        return "Server{  HOST=" + HOST + ", PORT=" + PORT + ", ServerName=" + ServerName + "maxCapacity=" + maxCapacity + '}';
    }

}
