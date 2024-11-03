public class SLList<LochNess> {
    private StuffNode sentinel;
    private int size;

    private class StuffNode {
        public LochNess item;
        public StuffNode next;

        public StuffNode(LochNess i, StuffNode n) {
            item = i;
            next = n;
        }
    }

    public SLList(LochNess x) {
        sentinel = new StuffNode(null, null);
        sentinel.next = new StuffNode(x, null);
        size = 1;
    }

    public SLList() {
        sentinel = new StuffNode(null, null);
        size = 0;
    }

    public void addFirst(LochNess x) {
        sentinel.next = new StuffNode(x, sentinel.next);
        size += 1;
    }

    public LochNess getFirst() {
        return sentinel.next.item;
    }

    public void addLast(LochNess x) {
        size += 1;
        StuffNode p = sentinel;

        while (p.next != null) {
            p = p.next;
        }
        p.next = new StuffNode(x, null);
    }

    public static void main(String[] args) {
        SLList<Integer> s1 = new SLList<>();
        s1.addFirst(10);

        SLList<String> s2 = new SLList<>();
        s2.addFirst("hello");

        int[] x = new int[3];
        int[] y = new int[]{1, 2, 3, 4, 5};
        int[] z = {9, 10, 11, 12, 13};
    }
}
