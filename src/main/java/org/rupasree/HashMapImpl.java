package org.rupasree;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapImpl {
    public static class HashMap<K,V> {
        private class Node {
            K key;
            V value;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int n; // size
        private int N; //buckets
        private LinkedList<Node> buckets[];

        public HashMap() {
            this.N=4;
            this.buckets = new LinkedList[this.N];
            for(int i=0;i<N;i++){
                this.buckets[i] = new LinkedList<>();
            }
        }

        public void put(K key, V value){
            int bi = hashFunction(key);
            int di = searchLLForKey(bi,key);
            if(di == -1){ // key does not exist
                buckets[bi].add(new Node(key,value));
                this.n++;
            }
            else{
                Node node = buckets[bi].get(di);
                node.value = value;
            }
            double lambda = (double) n/N;
            if (lambda>2.0){
                rehash();
            }
        }

        private void rehash() {
            LinkedList<Node> oldBuckets[] =buckets;
            buckets = new LinkedList[N*2];
            for(int i=0;i<buckets.length;i++){
                buckets[i] = new LinkedList<>();
            }
            for(int i=0;i<oldBuckets.length;i++){
                for(int j=0;j<oldBuckets[i].size();i++){
                    Node node = oldBuckets[i].get(j);
                    put(node.key, node.value);
                }
            }
        }


        private int searchLLForKey(int bi, K key) {
            LinkedList<Node> ll=buckets[bi];
            for (int i=0;i<ll.size();i++) {
                if(ll.get(i).key == key){
                    return i;
                }
            }
            return -1;
        }

        private int hashFunction(K key) {
            int bi = key.hashCode();
            return Math.abs(bi) % N;
        }

        public V get(K key){
            int bi = hashFunction(key);
            int di = searchLLForKey(bi,key);
            if(di == -1){ // key does not exist
                return null;
            }
            else{
                Node node = buckets[bi].get(di);
                return node.value;
            }
        }

        public boolean isEmpty(){
            if(n > 0){
                return false;
            }
            return true;
        }

        public V remove(K key){
            int bi = hashFunction(key);
            int di = searchLLForKey(bi,key);
            if(di == -1){ // key does not exist
                return null;
            }
            else{
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;
            }
        }
        public boolean containsKey(K key){
            int bi = hashFunction(key);
            int di = searchLLForKey(bi,key);
            if(di == -1){ // key does not exist
                return false;
            }
            else{
                return true;
            }
        }

        public ArrayList<K> keySet(){
            ArrayList<K> keys =new ArrayList<>();
            for(int i=0;i<buckets.length;i++){
                for(int j=0;j<buckets[i].size(); j++){
                    keys.add(buckets[i].get(j).key);
                }
            }
            return keys;
        }
    }
}
