package features.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;

public class ConsistentHash {
    private int _ringSize;
    private int _nServers;
    private int _replicas;

    private Function<Long, Long> _hashFunc;

    private ConcurrentSkipListMap<Long, Integer> _ringSlotToServer = new ConcurrentSkipListMap<>();

    public ConsistentHash(int ringSize, int nSevers, int replicas, Function<Long, Long> hashFunc) {
        this._ringSize = ringSize;
        this._nServers = nSevers;
        this._replicas = replicas;
        this._hashFunc = hashFunc;
        this.initialize();
    }

    protected void initialize() {
        long seed = System.currentTimeMillis();

        for (int i = 0; i < this._nServers; i++) {
            for (int j = 0; j < this._replicas; j++) {
                Long hc = this._hashFunc.apply(seed++);
                Long slot = Math.abs(hc) % this._ringSize;
                this._ringSlotToServer.put(slot, i);
            }
        }
        System.out.println(this._ringSlotToServer);
    }

    public void addServer(int s) {
        long seed = System.currentTimeMillis();

        for (int j = 0; j < this._replicas; j++) {
            Long hc = this._hashFunc.apply(seed++);
            Long slot = Math.abs(hc) % this._ringSize;
            this._ringSlotToServer.put(slot, s);
        }
        System.out.println(this._ringSlotToServer);
    }

    public void removeServer(int s) {
        this._ringSlotToServer.entrySet().removeIf(r -> r.getValue() == s);
        System.out.println(this._ringSlotToServer);
    }

    public int route(Long requestId) {
        Long hc = Math.abs(this._hashFunc.apply(requestId)) % this._ringSize;
        System.out.println("Request hc is: " + hc);
        Map.Entry<Long, Integer> e = this._ringSlotToServer.higherEntry(hc);
        if (e == null) {
            return _ringSlotToServer.firstEntry().getValue();
        }
        return e.getValue();
    }

    public static Long defHashFunc(Long o) {
        HashFunction hf = Hashing.farmHashFingerprint64();
        return hf.newHasher()
                .putLong(o)
                .hash()
                .asLong();
    }

    public static void main(String[] argv) {
        int ringSize = 1000;
        int nServers = 4;
        int replicas = 1;
        ConsistentHash ch = new ConsistentHash(ringSize, nServers, replicas, ConsistentHash::defHashFunc);
        Long requestId = 1L;
        int s = ch.route(requestId);
        System.out.println("Routing to s: " + s);
        ch.removeServer(s);
        s = ch.route(requestId);
        System.out.println("Routing to s: " + s);
    }
}
