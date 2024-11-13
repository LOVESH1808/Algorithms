import java.util.HashMap;

class node1 {
    int key, val;
    node1 next, prev;
    int freq = 1;
    node1(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
class DoublyLinkedList {
    node1 head;
    node1 tail;
    DoublyLinkedList() {
        head = new node1(-1, -1);
        tail = new node1(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    void addNode(node1 v) {
        node1 next = head.next;
        head.next = v;
        v.prev = head;
        v.next = next;
        next.prev = v;
    }
    node1 removeNode() {
        node1 node = tail.prev;
        node.prev.next = tail;
        tail.prev = node.prev;
        return node;
    }
    node1 removeNode(node1 v) {
        node1 prev = v.prev;
        node1 next = v.next;
        prev.next = next;
        next.prev = prev;
        return v;
    }

    boolean isEmpty() {
        if(head.next == tail) {
            return true;
        }
        return false;
    }

}
public class LFU_CACHE {
    HashMap<Integer, DoublyLinkedList> freqList = new HashMap<Integer, DoublyLinkedList>();
    HashMap<Integer, node1> lfuCache = new HashMap<>();
    int capacity;
    int minFreq;
    LFU_CACHE(int capacity) {
        this.capacity = capacity;
        minFreq = 1;
    }
    public int get(int key) {
        if(lfuCache.get(key) == null) {
            return -1;
        }
        node1 v = lfuCache.get(key);
        freqList.get(v.freq).removeNode(v);
        if(freqList.get(v.freq).isEmpty()) {
            if(minFreq == v.freq) {
                minFreq = v.freq + 1;
            }
        }
        v.freq++;
        if(freqList.get(v.freq).isEmpty()) {
            DoublyLinkedList list = new DoublyLinkedList();
            list.addNode(v);
            freqList.put(v.freq, list);
        } else {
            freqList.get(v.freq).addNode(v);
        }
        return v.val;

    }

    void put(int key, int val) {
        if(capacity == 0)
            return;
        if(lfuCache.get(key) != null) {
            node1 v = lfuCache.get(key);
            freqList.get(v.freq).removeNode(v);
            if(freqList.get(v.freq).isEmpty()) {
                if(minFreq == v.freq) {
                    minFreq = v.freq + 1;
                }
            }
            v.freq++;
            if(freqList.get(v.freq).isEmpty()) {
                DoublyLinkedList list = new DoublyLinkedList();
                list.addNode(v);
                freqList.put(v.freq, list);
            } else {
                freqList.get(v.freq).addNode(v);
            }
            v.val = val;
        } else {
            if(lfuCache.size() == capacity) {
                node1 v = freqList.get(minFreq).removeNode();
                lfuCache.remove(v.key);
            }
            node1 newNode = new node1(key, val);
            lfuCache.put(key, newNode);

            if(freqList.get(1) != null) {
                freqList.get(1).addNode(newNode);
            } else {
                DoublyLinkedList list = new DoublyLinkedList();
                list.addNode(newNode);
                freqList.put(1, list);
            }
            minFreq = 1;
        }
    }
}

