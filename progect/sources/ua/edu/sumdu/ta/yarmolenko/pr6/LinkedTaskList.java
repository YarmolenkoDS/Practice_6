/*
 * Classname: LinkedTaskList
 *
 * Date: 2018/10/28
 *
 * Author: Dmitrij Yarmolenko
 * E-mail: dsyarmolenko@gmail.com
 *
 */

package ua.edu.sumdu.ta.yarmolenko.pr6;

import ua.edu.sumdu.ta.yarmolenko.pr6.*;

import java.util.*;
import java.util.List.*;
import java.util.LinkedList.*;

/**
 * Class LinkedTaskList describes the LinkedTaskList data type
 */
public class LinkedTaskList extends AbstractTaskList {

    private LinkedListNode firstElementOfList;

    /**
     * Constructor for creating a task list object
     */
    public LinkedTaskList() {
        super();
        this.firstElementOfList = new LinkedListNode(null);
    }
    
    public AbstractTaskList clone() {
        LinkedTaskList clonedTaskList = new LinkedTaskList();
//        for (Task el : this) {
//            clonedTaskList.add(el.clone());
//        }
        return clonedTaskList;
    }
    
    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {

            private LinkedListNode currentElementOfList = firstElementOfList;

            @Override
            public boolean hasNext() {
                return currentElementOfList.next != null;
            }

            @Override
            public Task next() {
                return currentElementOfList.listItemData;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
    /**
     * Method for adding non-unique tasks
     *
     * @param task is an object of type task added to task list
     */	
    public void add(Task task) throws NullPointerException {
        if (task != null) {   
            task.setTitle(START_OF_TASK_TITLE + task.getTitle());
            LinkedListNode lastElementOfList = new LinkedListNode(task);
            if (firstElementOfList.listItemData == null) {
                firstElementOfList = lastElementOfList;
                counterOfTasksInList++;
            } else {
                LinkedListNode currentElementOfList = firstElementOfList;
                while (currentElementOfList.next != null){
                    currentElementOfList = currentElementOfList.next;
                }
                currentElementOfList.next = lastElementOfList;
                counterOfTasksInList++; 
            }
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Method to delete all tasks equal input
     *
     * @param task is an object of type task to be deleted in the task list
     */	
    public void remove(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException();
        }
        if (firstElementOfList.listItemData == null){
            throw new NullPointerException();
        }
        task.setTitle(START_OF_TASK_TITLE + task.getTitle());
        while (firstElementOfList.listItemData.equals(task)) {
            if (firstElementOfList.next != null) {
                firstElementOfList = firstElementOfList.next;
                counterOfTasksInList--;
            } else {
                firstElementOfList.listItemData = null;
                counterOfTasksInList--;
                return;
            }
        }

        LinkedListNode prevElementOfList = firstElementOfList;
        LinkedListNode currentElementOfList = firstElementOfList;
        while (prevElementOfList != null) {
            currentElementOfList = prevElementOfList.next;
            while (currentElementOfList != null && currentElementOfList.listItemData.equals(task)){
                counterOfTasksInList--;
                currentElementOfList = currentElementOfList.next;
            }
            prevElementOfList.next = currentElementOfList;
            prevElementOfList = currentElementOfList;
        }


    }

    /**
     * The method returns an array of tasks from the list whose notification time 
     *  is between from (exclusively) and to (inclusive)
     *
     * @param from the beginning of the time span
     * @param to the ending of the time span
     * @return an array of tasks from the list whose notification time 
     *  is between from (exclusively) and to (inclusive)
     */	
    public Task[] incoming(int from, int to) {
        Task[] taskListFromTo = new Task[size()];
        int indexFromTo = 0;
        LinkedListNode currentElementOfList = firstElementOfList;
        while (currentElementOfList != null){
            if ((currentElementOfList.listItemData.isActive() == true) 
                    && (currentElementOfList.listItemData.nextTimeAfter(from) <= to)
                    && (currentElementOfList.listItemData.nextTimeAfter(from) != -1)) {
                taskListFromTo[indexFromTo] = currentElementOfList.listItemData;
                indexFromTo ++;
            }
            currentElementOfList = currentElementOfList.next;
        }
        Task[] tempTaskListFromTo = new Task[indexFromTo];
        for (int i = 0; i < indexFromTo; i++) {
            tempTaskListFromTo[i] = taskListFromTo[i];
        }
        taskListFromTo = tempTaskListFromTo;
        return taskListFromTo;
    }
}