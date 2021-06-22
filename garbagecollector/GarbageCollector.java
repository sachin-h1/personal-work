package interview.garbagecollector;

import interview.MarkSweep;

public class GarbageCollector {

    private static GarbageCollector single_instance = null;

    private GarbageCollector() {

    }

    public static GarbageCollector getInstance() {
        if (single_instance == null)
            single_instance = new GarbageCollector();
        return single_instance;
    }

    public void mark(CustomObject object) {
        if (object.marked)
            return;
        object.marked = true;
        if (object.type == CustomObject.ObjectType.OBJ_PAIR) {
            mark(object.head);
            mark(object.tail);
        }
    }

    public void markAll(VirtualMachine vm) {
        for (int i = 0; i < vm.stackSize; ++i) {
            mark(vm.stack[i]);
        }
    }

    private void free(CustomObject unreached) {
        unreached = null;
    }

    public void sweep(VirtualMachine vm) {
        CustomObject object = vm.firstObject;
        while (object != null) {
            if (!object.marked) {
                CustomObject unreached = object;
                object = unreached.next;
                free(unreached);
                vm.numObjects--;
            } else {
                object.marked = false;
                object = object.next;
            }
        }
    }

    public void gc(VirtualMachine vm) {
        int numObjects = vm.numObjects;
        markAll(vm);
        sweep(vm);
        System.out.println("Collected " + (numObjects - vm.numObjects)
                + " object, " + vm.numObjects + " remaining.");
    }

}
