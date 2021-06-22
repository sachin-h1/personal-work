package interview.garbagecollector;

import interview.MarkSweep;

public class Application {

    private final static int STACK_MAX = 256;

    public CustomObject newObject(VirtualMachine vm, CustomObject.ObjectType type) {
        if (vm.numObjects == vm.maxObjects) {
            GarbageCollector garbageCollector = GarbageCollector.getInstance();
            garbageCollector.gc(vm);
        }
        CustomObject object = new CustomObject();
        object.type = type;
        object.next = vm.firstObject;
        vm.firstObject = object;
        object.marked = false;
        vm.numObjects++;
        return object;
    }

    public void push(VirtualMachine vm, CustomObject value) {
        if (vm.stackSize < STACK_MAX)
            vm.stack[vm.stackSize++] = value;
    }

    public CustomObject pop(VirtualMachine vm) {
        if (vm.stackSize > 0)
            return vm.stack[--vm.stackSize];
        else
            return null;
    }

    public void pushInt(VirtualMachine vm, int intVal) {
        CustomObject object = newObject(vm, CustomObject.ObjectType.OBJ_INT);
        object.value = intVal;
        push(vm, object);
    }

    public CustomObject pushPair(VirtualMachine vm) {
        CustomObject object = newObject(vm, CustomObject.ObjectType.OBJ_PAIR);
        object.tail = pop(vm);
        object.head = pop(vm);
        push(vm, object);
        return object;
    }

    public static void main(String args[]) {

        Application app = new Application();
        GarbageCollector garbageCollector = GarbageCollector.getInstance();

        VirtualMachine vm = new VirtualMachine();
        System.out.println("Objects on stack are preserved");
        app.pushInt(vm, 1);
        app.pushInt(vm, 2);
        garbageCollector.gc(vm);

        VirtualMachine vm1 = new VirtualMachine();
        System.out.println("Unreached objects are collected");
        app.pushInt(vm1, 1);
        app.pushInt(vm1, 2);
        app.pop(vm1);
        app.pop(vm1);
        garbageCollector.gc(vm1);


        VirtualMachine vm2 = new VirtualMachine();
        System.out.println("Cycle relation test");
        app.pushInt(vm2, 1);
        app.pushInt(vm2, 2);
        CustomObject a = app.pushPair(vm2);
        app.pushInt(vm2, 3);
        app.pushInt(vm2, 4);
        CustomObject b = app.pushPair(vm2);
        a.tail = b;
        b.tail = a;
        garbageCollector.gc(vm2);


    }

}
