package Test;

import org.apache.thrift.TException;

class SomethingImpl implements Something.Iface {
    public SomethingImpl() {}
    public int ping() throws TException {
        System.out.println("Receieve ping from client...");
        return 0;
    }
}
