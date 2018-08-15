package Test;

import java.io.IOException;
import org.apache.thrift.*;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.*;

public class Client {
    public static void main(String args[]) {
        try {
            TTransport transport = new TSocket("localhost", 7911);
            TProtocol protocol = new TBinaryProtocol(transport);
            Something.Client client = new Something.Client(protocol);
            transport.open();
            System.out.println("Client is ping()");
            client.ping();
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }
}
