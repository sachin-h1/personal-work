package interview.garbagecollector;

import interview.MarkSweep;

public class VirtualMachine {

    private final static int STACK_MAX = 256;

    CustomObject[] stack;
    CustomObject firstObject;

    int stackSize;
    int numObjects;
    int maxObjects;

    public VirtualMachine() {
        this.stack = new CustomObject[STACK_MAX];
        this.stackSize = 0;
        this.firstObject = null;
        this.numObjects = 0;
        this.maxObjects = 16;
    }

}
