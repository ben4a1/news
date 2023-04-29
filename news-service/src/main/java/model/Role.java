package model;

public enum Role {

    /**
     * Admin user role (can perform CRUD operations on all entities).
     */
    ADMIN,

    /**
     * Journalist user role (can add and change/delete only his own news).
     */
    JOURNALIST,

    /**
     * Subscriber user role (can add and change/delete only his own comments).
     */
    SUBSCRIBER,


    /**
     * Unregistered user role (can only view news and comments).
     */
    UNREGISTERED
}
