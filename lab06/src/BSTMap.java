import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private int size = 0;
    private TreeSet<K> keySet = new TreeSet<>();


    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if (root != null) {
            BSTNode lookup = root.find(key);
            if (lookup.key.compareTo(key) == 0) {
                lookup.value = value;
            } else {
                lookup.quickInsert(key, value);
                size++;
            }
        } else {
            root = new BSTNode(key, value);
            size++;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        BSTNode lookup = root.get(key);
        if (lookup == null) {
            return null;
        }
        return lookup.value;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        return root.get(key) != null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        if (keySet.isEmpty()) {
            frontTravel(root);
        }
        return keySet;
    }




    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            BSTNode lookup = root.get(key);
            BSTNode parent = root.getParent(lookup);
            assert lookup != null;
            V value = lookup.value;

            // case 1: deletion key has no child
            if (lookup.isLeaf()) {
                if (parent == null) {
                    root = null;
                } else if (parent.left == lookup) {
                    parent.left = null;
                } else if (parent.right == lookup) {
                    parent.right = null;
                }
                size--;
            }
            // case 2: deletion key has two children
            else if (lookup.left != null && lookup.right != null) {
                BSTNode predecessor = lookup.left.getPredecessor();
                BSTNode successor = lookup.right.getSuccessor();
                BSTNode deletionNode = (predecessor == null ? successor : predecessor);
                K deletionKey = deletionNode.key;
                lookup.value = deletionNode.value;
                remove(deletionNode.key);
                lookup.key = deletionKey;
            }
            // case 3: deletion key has one child
            else {
                BSTNode lookupParent = root.getParent(lookup);
                if (lookupParent == null) {
                    root = lookup.left == null ? lookup.right : lookup.left;
                } else {
                    lookup.value = lookupParent.value;
                    if (lookupParent.left == lookup) {
                        lookupParent.left = (lookup.left == null ? lookup.right : lookup.left);
                    } else {
                        lookupParent.right = (lookup.left == null ? lookup.right : lookup.left);
                    }
                }
                size--;
            }
            return value;
        }
        return null;
    }



    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {

        public BSTIterator() {
            keySet();
            keys = new ArrayList<>(keySet);
            cur = 0;
        }

        @Override
        public boolean hasNext() {
            return cur < keys.size();
        }

        @Override
        public K next() {
            int index = cur;
            cur = index + 1;
            return keys.get(index);
        }

        private ArrayList<K> keys;
        private int cur;
    }

    private BSTNode root;

    private class BSTNode {

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        /**
         * recurse to match the corresponding BSTNode, if not, return the nearest node
         * @param k the key name
         * @return the Match BSTNode
         */
        public BSTNode find(K k) {
            if (k != null && k.compareTo(key) == 0) {
                return this;
            }
            if (left != null && k.compareTo(key) < 0) {
                return left.find(k);
            } else if (right != null && k.compareTo(key) > 0) {
                return right.find(k);
            } else {
                return this;
            }
        }

        public BSTNode get(K k) {
            BSTNode node = find(k);
            if (node.key.compareTo(k) == 0) {
                return node;
            }
            return null;
        }

        public void quickInsert(K key, V value) {
            if (key.compareTo(this.key) < 0) {
                left = new BSTNode(key, value);
            } else {
                right = new BSTNode(key, value);
            }
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public BSTNode getParent(BSTNode node) {
            if (node == null) {
                return null;
            }
            if (left == node || right == node) {
                return this;
            }
            if (left != null && node.key.compareTo(key) < 0) {
                return left.getParent(node);
            } else if (right != null && node.key.compareTo(key) > 0) {
                return right.getParent(node);
            }
            return null;
        }

        // get the predecessor of BST
        public BSTNode getPredecessor() {
            if (right != null) {
                return right.getPredecessor();
            }
            return this;
        }

        // get the successor of BST
        public BSTNode getSuccessor() {
            if (left != null) {
                return left.getSuccessor();
            }
            return this;
        }


        /** store the key, value and left or right node */
        K key;
        V value;
        BSTNode left, right;
    }

    // helper function
    private void frontTravel(BSTNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            frontTravel(root.left);
        }
        keySet.add(root.key);
        if (root.right != null) {
            frontTravel(root.right);
        }
    }
}
