package interview.garbagecollector;

public class CustomObject {

    enum ObjectType {
        OBJ_INT, OBJ_PAIR
    }

    ObjectType type;

    CustomObject next;

    /* OBJ_INT */
    int value;

    /* OBJ_PAIR */
    CustomObject head;
    CustomObject tail;


    /* For marking when we run garbage collector */
    boolean marked;

}
