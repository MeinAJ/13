package com.aj.lfu;

public class FrequencyNode extends Node {

    int frequency;

    NodeList lfuCacheEntryList;

    public FrequencyNode(int frequency) {
        this.frequency = frequency;
        lfuCacheEntryList = new NodeList();
    }

    @Override
    public boolean equals(Object o) {
        return frequency == ((FrequencyNode) o).frequency;
    }

    @Override
    public int hashCode() {
        return frequency * 31;
    }

    @Override
    public String toString() {
        return Integer.toString(frequency);
    }

}
